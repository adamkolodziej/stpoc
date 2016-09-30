/**
 * 
 */
package com.hybris.showcase.services.impl;

import de.hybris.platform.btg.enums.BTGConditionEvaluationScope;
import de.hybris.platform.btg.enums.BTGEvaluationMethod;
import de.hybris.platform.btg.enums.BTGResultScope;
import de.hybris.platform.btg.enums.BTGRuleType;
import de.hybris.platform.btg.model.BTGRuleModel;
import de.hybris.platform.btg.model.BTGSegmentModel;
import de.hybris.platform.btg.segment.SegmentEvaluationException;
import de.hybris.platform.btg.segment.SegmentService;
import de.hybris.platform.btg.services.BTGEvaluationService;
import de.hybris.platform.btg.services.BTGResultService;
import de.hybris.platform.btg.services.impl.BTGEvaluationContext;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.services.OfflineBTGEvaluationService;


/**
 * @author n.pavlovic
 * 
 */
public class DefaultOfflineBTGEvaluationService implements OfflineBTGEvaluationService
{
	private static final Logger LOG = Logger.getLogger(DefaultOfflineBTGEvaluationService.class);

	private static final String ALL_CUSTOMERS_QUERY = "select {pk} from {Customer}";

	private static final String QUERY_USER_SEGMENT_RESULTS = "SELECT PK FROM ("
			+ "{{ SELECT {r1.PK} AS PK "
			+ "  FROM {User as u1 JOIN BTGRuleResult as r1 ON {r1.user}={u1.PK} JOIN BTGRule as rule1 ON {r1.rule}={rule1.PK} JOIN BTGSegment as seg1  ON {seg1.PK}={rule1.segment} JOIN CatalogVersion AS cv1 ON {cv1.PK}={seg1.catalogVersion}} "
			+ "  WHERE {u1.uid}=?userUid AND {seg1.uid}=?segUid AND {cv1.PK}=?cv"
			+ "}} UNION ALL "
			+ "{{ SELECT {r2.PK} AS PK "
			+ "   FROM {User as u2 JOIN BTGSegmentResult as r2 ON {r2.user}={u2.PK} JOIN BTGSegment as seg2 ON {seg2.PK}={r2.segment} JOIN CatalogVersion AS cv2 ON {cv2.PK}={seg2.catalogVersion}} "
			+ "   WHERE {u2.uid}=?userUid AND {seg2.uid}=?segUid AND {cv2.PK}=?cv"
			+ "}} UNION ALL "
			+ "{{ SELECT {r3.PK} AS PK "
			+ "   FROM {User as u3 JOIN BTGConditionResult as r3 ON {r3.user}={u3.PK} JOIN BTGCondition AS c3 ON {r3.condition}={c3.PK} JOIN BTGRule as rule3 ON {rule3.PK}={c3.rule} JOIN BTGSegment as seg3 ON {seg3.PK}={rule3.segment} JOIN CatalogVersion AS cv3 ON {cv3.PK}={seg3.catalogVersion}} "
			+ "   WHERE {u3.uid}=?userUid AND {seg3.uid}=?segUid AND {cv3.PK}=?cv" + "}}) x";

	private CatalogVersionService catalogVersionService;
	private SegmentService segmentService;
	private CMSSiteService cmsSiteService;
	private BTGResultService btgResultService;
	private BTGEvaluationService btgEvaluationService;
	private FlexibleSearchService flexibleSearchService;
	private ModelService modelService;
	private SearchRestrictionService searchRestrictionService;

	private SessionService sessionService;
	private BaseSiteService baseSiteService;

	private BTGRuleType[] ruleTypes = new BTGRuleType[]
	{ BTGRuleType.USER };

	@Override
	public void evaluateSegments(final CMSSiteModel... sites)
	{
		final Collection<CMSSiteModel> siteList;
		if (sites.length == 0)
		{
			siteList = cmsSiteService.getSites();
		}
		else
		{
			siteList = Arrays.asList(sites);
		}
		final Collection<CustomerModel> customers = getAllCustomers();

		evaluateSegments(customers, siteList, false);
	}


	@Override
	public void evaluateSegments(final Collection<CustomerModel> customers, final Collection<CMSSiteModel> siteList,
			final boolean invalidateResults)
	{
		for (final CMSSiteModel site : siteList)
		{
			getSessionService().executeInLocalView(new SessionExecutionBody()
			{
				@Override
				public void executeWithoutResult()
				{
					getBaseSiteService().setCurrentBaseSite(site, false);
					evaluateSiteSegments(site, customers, invalidateResults);
				}
			});
		}
	}

	@Override
	public void evaluateSiteSegments(final CMSSiteModel site, final Collection<CustomerModel> customers,
			final boolean invalidateResults)
	{
		final BTGEvaluationContext context = new BTGEvaluationContext(BTGConditionEvaluationScope.OFFLINE,
				BTGEvaluationMethod.FULL, BTGResultScope.PERMANENT);
		final List<CMSSiteModel> siteList = Arrays.asList(site);

		for (final UserModel user : customers)
		{
			if (invalidateResults)
			{
				for (final BTGRuleType ruleType : getRuleTypes())
				{
					getBtgResultService().invalidateEvaluationResults(siteList, user, context, ruleType);
				}
			}

			final List<BTGSegmentModel> segmentsToEvaluate = getApplicableBtgSegments(siteList);
			for (final BTGSegmentModel segment : segmentsToEvaluate)
			{
				try
				{
					getBtgEvaluationService().evaluateSegment(user, segment, context);
				}
				catch (final SegmentEvaluationException e)
				{
					LOG.error("Unable to evaluate segment " + segment.getUid() + " for customer: " + user.getUid(), e);
				}
			}
		}
	}

	private Collection<CustomerModel> getAllCustomers()
	{
		final SearchResult<CustomerModel> result = getFlexibleSearchService().search(ALL_CUSTOMERS_QUERY);
		return result.getResult();
	}

	protected List<BTGSegmentModel> getApplicableBtgSegments(final List<CMSSiteModel> cmsSiteModels)
	{
		final List<BTGRuleType> ruleTypesList = Arrays.asList(getRuleTypes());
		final List<BTGSegmentModel> result = new LinkedList<BTGSegmentModel>();

		final Collection<CatalogVersionModel> catalogVersions = getCatalogVersions(cmsSiteModels);

		final Collection<BTGSegmentModel> segments = getSegmentService().getSegments(cmsSiteModels,
				BTGConditionEvaluationScope.OFFLINE, catalogVersions);
		for (final BTGSegmentModel segment : segments)
		{
			for (final BTGRuleModel rule : segment.getRules())
			{
				if (ruleTypesList.contains(rule.getRuleType()))
				{
					result.add(segment);
					break;
				}
			}
		}
		return result;
	}

	private Collection<CatalogVersionModel> getCatalogVersions(final List<CMSSiteModel> sites)
	{
		final Collection<CatalogVersionModel> catalogVersions = new HashSet<>();
		for (final CMSSiteModel site : sites)
		{
			for (final ContentCatalogModel contentCatalog : site.getContentCatalogs())
			{
				final CatalogVersionModel catalogVersion = contentCatalog.getActiveCatalogVersion();
				catalogVersions.add(catalogVersion);
			}
		}
		return catalogVersions;
	}

	@Override
	public void evaluateSegment(final BTGSegmentModel segment, final Collection<CustomerModel> customers,
			final Collection<CMSSiteModel> siteList, final boolean invalidateResults)
	{
		for (final CMSSiteModel site : siteList)
		{
			getSessionService().executeInLocalView(new SessionExecutionBody()
			{
				@Override
				public void executeWithoutResult()
				{
					searchRestrictionService.disableSearchRestrictions();
					getBaseSiteService().setCurrentBaseSite(site, false);
					evaluateSiteSegment(segment, customers, invalidateResults);
				}
			});
		}
	}

	private void evaluateSiteSegment(final BTGSegmentModel segment, final Collection<CustomerModel> customers,
			final boolean invalidateResults)
	{
		final BTGEvaluationContext context = new BTGEvaluationContext(BTGConditionEvaluationScope.OFFLINE,
				BTGEvaluationMethod.FULL, BTGResultScope.PERMANENT);

		for (final UserModel user : customers)
		{
			if (invalidateResults)
			{
				for (final BTGRuleType ruleType : getRuleTypes())
				{
					getBtgResultService().invalidateSegment(user, segment, context, ruleType);
					removeAllResultsForUser(user.getUid(), segment.getUid(), segment.getCatalogVersion());
				}
			}

			try
			{
				getBtgEvaluationService().evaluateSegment(user, segment, context);
			}
			catch (final SegmentEvaluationException e)
			{
				LOG.error("Unable to evaluate segment " + segment.getUid() + " for customer: " + user.getUid(), e);
			}
		}
	}

	protected void removeAllResultsForUser(final String userId, final String segUid, final CatalogVersionModel catalogVersion)
	{
		final FlexibleSearchQuery query1 = new FlexibleSearchQuery(QUERY_USER_SEGMENT_RESULTS);
		query1.addQueryParameter("userUid", userId);
		query1.addQueryParameter("segUid", segUid);
		query1.addQueryParameter("cv", catalogVersion);

		final SearchResult<Object> searchResult = flexibleSearchService.search(query1);
		if (searchResult.getCount() > 0)
		{
			modelService.removeAll(searchResult.getResult());
		}
	}

	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	@Required
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	public SegmentService getSegmentService()
	{
		return segmentService;
	}

	@Required
	public void setSegmentService(final SegmentService segmentService)
	{
		this.segmentService = segmentService;
	}

	public CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}

	@Required
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	public BTGRuleType[] getRuleTypes()
	{
		return this.ruleTypes;
	}

	public void setRuleTypes(final BTGRuleType[] ruleTypes)
	{
		this.ruleTypes = ruleTypes;
	}

	public BTGResultService getBtgResultService()
	{
		return btgResultService;
	}

	@Required
	public void setBtgResultService(final BTGResultService btgResultService)
	{
		this.btgResultService = btgResultService;
	}

	public BTGEvaluationService getBtgEvaluationService()
	{
		return btgEvaluationService;
	}

	@Required
	public void setBtgEvaluationService(final BTGEvaluationService btgEvaluationService)
	{
		this.btgEvaluationService = btgEvaluationService;
	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public SearchRestrictionService getSearchRestrictionService()
	{
		return searchRestrictionService;
	}

	@Required
	public void setSearchRestrictionService(final SearchRestrictionService searchRestrictionService)
	{
		this.searchRestrictionService = searchRestrictionService;
	}

	public SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}
}
