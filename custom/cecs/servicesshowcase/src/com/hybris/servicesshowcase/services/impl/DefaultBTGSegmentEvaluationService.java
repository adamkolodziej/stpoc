package com.hybris.servicesshowcase.services.impl;

import de.hybris.platform.btg.enums.BTGConditionEvaluationScope;
import de.hybris.platform.btg.enums.BTGRuleType;
import de.hybris.platform.btg.model.BTGRuleModel;
import de.hybris.platform.btg.model.BTGSegmentModel;
import de.hybris.platform.btg.segment.SegmentService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.servicesshowcase.services.BTGSegmentEvaluationService;
import com.hybris.showcase.cecs.ymarketingintegration.services.InitiativesService;
import com.hybris.showcase.services.CustomerService;
import com.hybris.showcase.services.OfflineBTGEvaluationService;


/**
 * Created by m.golubovic on 29.6.2015..
 */
public class DefaultBTGSegmentEvaluationService implements BTGSegmentEvaluationService
{
	private final BTGRuleType[] ruleTypes = new BTGRuleType[]
	{ BTGRuleType.USER };
	private SegmentService segmentService;
	private InitiativesService initiativesService;
	private OfflineBTGEvaluationService offlineBTGEvaluationService;
	private CMSSiteService cmsSiteService;
	private CustomerService customerService;

	@Override
	public List<BTGSegmentModel> getApplicableBtgSegments(final List<CMSSiteModel> cmsSiteModels)
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

	@Override
	public void evaluateSegment(final BTGSegmentModel segment)
	{
		final Collection<CustomerModel> customers = getCustomerService().getCustomers();
		for (final CustomerModel customer : customers)
		{
			getInitiativesService().retrieveInitiativesForCustomer(customer);
		}
		final Collection<CMSSiteModel> sites = getCmsSiteService().getSites();
		getOfflineBTGEvaluationService().evaluateSegment(segment, customers, sites, true);
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

	public BTGRuleType[] getRuleTypes()
	{
		return ruleTypes;
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

	public InitiativesService getInitiativesService()
	{
		return initiativesService;
	}

	@Required
	public void setInitiativesService(final InitiativesService initiativesService)
	{
		this.initiativesService = initiativesService;
	}

	public OfflineBTGEvaluationService getOfflineBTGEvaluationService()
	{
		return offlineBTGEvaluationService;
	}

	@Required
	public void setOfflineBTGEvaluationService(final OfflineBTGEvaluationService offlineBTGEvaluationService)
	{
		this.offlineBTGEvaluationService = offlineBTGEvaluationService;
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

	/**
	 * @return the customerService
	 */
	public CustomerService getCustomerService()
	{
		return customerService;
	}

	/**
	 * @param customerService
	 *           the customerService to set
	 */
	@Required
	public void setCustomerService(final CustomerService customerService)
	{
		this.customerService = customerService;
	}

}