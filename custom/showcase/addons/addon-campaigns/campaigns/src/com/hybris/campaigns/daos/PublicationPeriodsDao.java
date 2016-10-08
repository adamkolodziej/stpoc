/**
 * 
 */
package com.hybris.campaigns.daos;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.user.UserGroupModel;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.hybris.campaigns.model.PublicationPeriodModel;


/**
 * @author npavlovic
 * 
 */
public interface PublicationPeriodsDao
{
	/**
	 * Finds current active publication periods
	 * 
	 * @param userGroups
	 *           - user groups
	 * @param currentTime
	 *           - current Date object
	 * @param timeOfDay
	 *           - current hour
	 * @param baseSite
	 *           - base site
	 * @return - list of PublicationPeriodModel objects
	 */
	List<PublicationPeriodModel> findCurrentActivePublicationPeriods(Set<UserGroupModel> userGroups, Date currentTime,
			Integer timeOfDay, BaseSiteModel baseSite);

	/**
	 * @param catalogVersion
	 * @return
	 */
	List<PublicationPeriodModel> findAllActivePublicationPeriods(CatalogVersionModel catalogVersion);
}
