package com.hybris.campaigns.services.impl;

import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.campaigns.cms.timezone.CmsTimeZoneService;
import com.hybris.campaigns.daos.PublicationPeriodsDao;
import com.hybris.campaigns.enums.Timezone;
import com.hybris.campaigns.model.PublicationPeriodModel;
import com.hybris.campaigns.services.PublicationPeriodsService;


/**
 * @author npavlovic
 * 
 */
public class DefaultPublicationPeriodsService implements PublicationPeriodsService
{
	private UserService userService;
	private CmsTimeZoneService cmsTimeZoneService;
	private BaseSiteService baseSiteService;
	private PublicationPeriodsDao publicationPeriodsDao;

	@Override
	public List<PublicationPeriodModel> getCurrentActivePublicationPeriods(final String userId, final Date currentTime,
			final Timezone timezone, final Integer timeOfDay, final WeekDay dayOfWeek, final String baseSiteId)
	{
		List<PublicationPeriodModel> results = new ArrayList<PublicationPeriodModel>();
		final List<PublicationPeriodModel> finalResults = new ArrayList<PublicationPeriodModel>();

		final UserModel user = getUserService().getUserForUID(userId);
		if (user != null)
		{
			final Set<UserGroupModel> userGroups = getUserService().getAllUserGroupsForUser(user);
			if (userGroups != null && !userGroups.isEmpty())
			{
				Date newCurrentTime = currentTime;
				if (currentTime != null && timezone != null)
				{
					newCurrentTime = getCmsTimeZoneService().getTimeForTimeZone(timezone, currentTime);
				}
				BaseSiteModel baseSite = null;
				if (baseSiteId != null && !baseSiteId.isEmpty())
				{
					baseSite = getBaseSiteService().getBaseSiteForUID(baseSiteId);
				}

				results = getPublicationPeriodsDao().findCurrentActivePublicationPeriods(userGroups, newCurrentTime, timeOfDay,
						baseSite);
				finalResults.addAll(results);

				// Remove publicationPeriods which don't have given dayOfWeek from results
				if (dayOfWeek != null)
				{
					for (final Iterator<PublicationPeriodModel> itr = finalResults.iterator(); itr.hasNext();)
					{
						final PublicationPeriodModel publicationPeriod = itr.next();
						if (!publicationPeriod.getDaysOfWeek().isEmpty())
						{
							if (!publicationPeriod.getDaysOfWeek().contains(dayOfWeek))
							{
								itr.remove();
							}
						}
					}
				}
			}
		}

		return finalResults;
	}

	@Override
	public List<PublicationPeriodModel> getAllActivePublicationPeriods(final CatalogVersionModel catalogVersion)
	{
		List<PublicationPeriodModel> results = new ArrayList<PublicationPeriodModel>();
		final List<PublicationPeriodModel> finalResults = new ArrayList<PublicationPeriodModel>();

		if (catalogVersion != null)
		{
			results = getPublicationPeriodsDao().findAllActivePublicationPeriods(catalogVersion);
		}
		finalResults.addAll(results);

		return finalResults;
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	protected CmsTimeZoneService getCmsTimeZoneService()
	{
		return cmsTimeZoneService;
	}

	@Required
	public void setCmsTimeZoneService(final CmsTimeZoneService cmsTimeZoneService)
	{
		this.cmsTimeZoneService = cmsTimeZoneService;
	}

	protected BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	protected PublicationPeriodsDao getPublicationPeriodsDao()
	{
		return publicationPeriodsDao;
	}

	@Required
	public void setPublicationPeriodsDao(final PublicationPeriodsDao publicationPeriodsDao)
	{
		this.publicationPeriodsDao = publicationPeriodsDao;
	}
}
