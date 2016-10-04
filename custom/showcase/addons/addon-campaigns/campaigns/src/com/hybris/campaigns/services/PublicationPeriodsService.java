/**
 * 
 */
package com.hybris.campaigns.services;

import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.Date;
import java.util.List;

import com.hybris.campaigns.enums.Timezone;
import com.hybris.campaigns.model.PublicationPeriodModel;


/**
 * @author npavlovic
 * 
 */
public interface PublicationPeriodsService
{
	/**
	 * Gets current active publication periods
	 * 
	 * @param userId
	 *           - id of user for which usergroups gets related publication periods
	 * @param currentTime
	 *           - current date object, can be null
	 * @param timezone
	 *           - current time zone, can be null
	 * @param timeOfDay
	 *           - hour of day (Integer value) for which gets publication periods, can be null
	 * @param dayOfWeek
	 *           - day for which gets publication periods, can be null
	 * @param baseSiteId
	 *           - base site id, can be null
	 * @return - list of PublicationPeriodModel objects
	 */
	List<PublicationPeriodModel> getCurrentActivePublicationPeriods(String userId, Date currentTime, Timezone timezone,
			Integer timeOfDay, WeekDay dayOfWeek, String baseSiteId);

	/**
	 * @param catalogId
	 * @return
	 */
	List<PublicationPeriodModel> getAllActivePublicationPeriods(CatalogVersionModel catalogVersion);
}
