/**
 * 
 */
package com.hybris.campaigns.addon.site;

import de.hybris.platform.acceleratorcms.preview.strategies.PreviewContextInformationLoaderStrategy;
import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.cms2.model.contents.CMSItemModel;
import de.hybris.platform.cms2.model.preview.PreviewDataModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.search.restriction.session.SessionSearchRestriction;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.addon.common.strategies.SiteContextInformationLoaderStrategy;
import com.hybris.campaigns.enums.Timezone;
import com.hybris.campaigns.model.PublicationPeriodModel;
import com.hybris.campaigns.services.PublicationPeriodsService;


/**
 * 
 * @author miroslaw.szot
 */
public class PublicationPeriodContextInformationLoaderStrategy implements SiteContextInformationLoaderStrategy,
		PreviewContextInformationLoaderStrategy
{
	public static final String SESSION_PUBLICATION_PERIODS = "activePublicationPeriods";
	private static final Logger LOG = Logger.getLogger(PublicationPeriodContextInformationLoaderStrategy.class);

	private SearchRestrictionService searchRestrictionService;
	private TypeService typeService;
	private SessionService sessionService;
	private TimeService timeService;
	private UserService userService;
	private PublicationPeriodsService publicationPeriodsService;

	@Override
	public void loadContextInformation(final CMSSiteModel site)
	{
		if (site == null)
		{
			LOG.error("Site is null, unable to load context information");
		}
		else
		{
			// cache it
			final List<String> sessionPublicationPeriods = sessionService.getAttribute(SESSION_PUBLICATION_PERIODS);
			if (sessionPublicationPeriods == null)
			{
				final String baseSiteId = site.getUid();
				final String userId = userService.getCurrentUser().getUid();
				final List<PublicationPeriodModel> activePublicationPeriods = getActivePublicationPeriods(baseSiteId, userId, null);
				sessionService.setAttribute(SESSION_PUBLICATION_PERIODS, getActivePublicationPeriodsCodes(activePublicationPeriods));
			}
			loadRestrictions();
		}
	}

	@Override
	public void initContextFromPreview(final PreviewDataModel preview)
	{
		// load it every time
		final String baseSiteId = preview.getActiveSite().getUid();
		final String userId = preview.getUser() != null ? preview.getUser().getUid() : userService.getCurrentUser().getUid();
		final Timezone timezone = preview.getTimezone();
		List<PublicationPeriodModel> activePublicationPeriods = new ArrayList<PublicationPeriodModel>();

		if (preview.getPublicationPeriod() != null)
		{
			activePublicationPeriods.add(preview.getPublicationPeriod());
		}
		else
		{
			activePublicationPeriods = getActivePublicationPeriods(baseSiteId, userId, timezone);
		}
		sessionService.setAttribute(SESSION_PUBLICATION_PERIODS, getActivePublicationPeriodsCodes(activePublicationPeriods));

		loadRestrictions();
	}

	protected List<PublicationPeriodModel> getActivePublicationPeriods(final String baseSiteId, final String userId,
			final Timezone timezone)
	{
		final Calendar calendar = Calendar.getInstance();
		final Date currentTime = timeService.getCurrentTime();
		calendar.setTime(currentTime);

		final Integer timeOfDay = Integer.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		final WeekDay dayOfWeek = WeekDay.values()[calendar.get(Calendar.DAY_OF_WEEK) - 1];

		return publicationPeriodsService.getCurrentActivePublicationPeriods(userId, currentTime, timezone, timeOfDay, dayOfWeek,
				baseSiteId);
	}

	protected List<String> getActivePublicationPeriodsCodes(final List<PublicationPeriodModel> activePublicationPeriods)
	{
		final List<String> activePublicationPeriodsCodes = new ArrayList<String>();
		for (final PublicationPeriodModel publicationPeriod : activePublicationPeriods)
		{
			activePublicationPeriodsCodes.add(publicationPeriod.getCode());
		}
		if (activePublicationPeriodsCodes.isEmpty())
		{
			activePublicationPeriodsCodes.add("<empty-value>");
		}
		return activePublicationPeriodsCodes;
	}


	private void loadRestrictions()
	{
		final SessionSearchRestriction cmsItemRestriction = createCMSItemSearchRestriciton();
		final SessionSearchRestriction promotionRestriction = createPromotionSearchRestriciton();

		// remove previous restrictions if exist to avoid duplicated ContextQueryFilter's
		if (CollectionUtils.isNotEmpty(searchRestrictionService.getSessionSearchRestrictions()))
		{
			searchRestrictionService.removeSessionSearchRestrictions(Arrays.asList(cmsItemRestriction, promotionRestriction));
		}
		searchRestrictionService.addSessionSearchRestrictions(cmsItemRestriction, promotionRestriction);
	}

	protected SessionSearchRestriction createCMSItemSearchRestriciton()
	{
		final ComposedTypeModel type = typeService.getComposedTypeForCode(CMSItemModel._TYPECODE);
		final String query = "{item:pk} = ANY ("// 
				+ "{{ SELECT {ppr:target} FROM {PublicationPeriod2CMSItemRelation AS ppr JOIN PublicationPeriod as pp ON {pp:pk} = {ppr:source} } "// 
				+ " WHERE {pp:code} IN (?session.activePublicationPeriods) "// 
				+ "}})"// 
				+ " OR NOT EXISTS ({{ "// 
				+ " SELECT {ppr:source} FROM {PublicationPeriod2CMSItemRelation AS ppr} WHERE {ppr:target} = {item:pk} "// 
				+ "}})";// 

		final SessionSearchRestriction cmsItemSearchRestriction = new SessionSearchRestriction("cmsItemPubPeriods", query, type);
		return cmsItemSearchRestriction;
	}

	protected SessionSearchRestriction createPromotionSearchRestriciton()
	{
		final ComposedTypeModel type = typeService.getComposedTypeForCode(AbstractPromotionModel._TYPECODE);
		final String query = "{item:pk} = ANY ("// 
				+ "{{ SELECT {ppr:target} FROM {PublicationPeriod2PromotionRelation AS ppr JOIN PublicationPeriod as pp ON {pp:pk} = {ppr:source} } "// 
				+ " WHERE {pp:code} IN (?session.activePublicationPeriods) "// 
				+ "}})"// 
				+ " OR NOT EXISTS ({{ "// 
				+ " SELECT {ppr:source} FROM {PublicationPeriod2PromotionRelation AS ppr} WHERE {ppr:target} = {item:pk} "// 
				+ "}})";// 

		final SessionSearchRestriction cmsItemSearchRestriction = new SessionSearchRestriction("promotionPubPeriods", query, type);
		return cmsItemSearchRestriction;
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

	public TypeService getTypeService()
	{
		return typeService;
	}

	@Required
	public void setTypeService(final TypeService typeService)
	{
		this.typeService = typeService;
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

	public TimeService getTimeService()
	{
		return timeService;
	}

	@Required
	public void setTimeService(final TimeService timeService)
	{
		this.timeService = timeService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public PublicationPeriodsService getPublicationPeriodsService()
	{
		return publicationPeriodsService;
	}

	@Required
	public void setPublicationPeriodsService(final PublicationPeriodsService publicationPeriodsService)
	{
		this.publicationPeriodsService = publicationPeriodsService;
	}

}
