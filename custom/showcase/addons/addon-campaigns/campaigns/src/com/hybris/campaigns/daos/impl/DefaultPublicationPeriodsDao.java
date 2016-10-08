/**
 * 
 */
package com.hybris.campaigns.daos.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.hybris.campaigns.daos.PublicationPeriodsDao;
import com.hybris.campaigns.model.PublicationPeriodModel;


/**
 * @author npavlovic
 * 
 */
public class DefaultPublicationPeriodsDao extends AbstractItemDao implements PublicationPeriodsDao
{
	private static final String FIND_ACTIVE_PUBLICATION_PERIODS = "SELECT {pp:" + PublicationPeriodModel.PK + "} FROM {"
			+ PublicationPeriodModel._TYPECODE + " AS pp JOIN PublicationPeriod2UserGroupRelation AS ppug "
			+ "ON {ppug:SOURCE} = {pp:" + PublicationPeriodModel.PK + "} JOIN " + UserGroupModel._TYPECODE + " AS ug ON {ug:"
			+ UserGroupModel.PK + "} = {ppug:TARGET}} WHERE {ug:" + UserGroupModel.PK + "} IN (?userGroups)";

	private static final String FIND_ACTIVE_PUBLICATION_PERIODS_DATE_SUFIX = " AND {pp:" + PublicationPeriodModel.STARTDATE
			+ "} < ?currentTime AND {pp:" + PublicationPeriodModel.ENDDATE + "} > ?currentTime";

	private static final String FIND_ACTIVE_PUBLICATION_PERIODS_HOUR_SUFIX = " AND ({pp:" + PublicationPeriodModel.FROMHOUR
			+ "} < ?timeOfDay OR {pp:" + PublicationPeriodModel.FROMHOUR + "} IS NULL) AND ({pp:" + PublicationPeriodModel.TOHOUR
			+ "} > ?timeOfDay OR {pp:" + PublicationPeriodModel.TOHOUR + "} IS NULL)";

	private static final String FIND_ACTIVE_PUBLICATION_PERIODS_SITE_SUFIX = " AND {pp:" + PublicationPeriodModel.BASESITE
			+ "} = ?baseSite";

	private static final String FIND_ALL_ACTIVE_PUBLICATION_PERIODS = "SELECT {pp:" + PublicationPeriodModel.PK + "} FROM {"
			+ PublicationPeriodModel._TYPECODE + " AS pp JOIN PublicationPeriod2UserGroupRelation AS ppug "
			+ "ON {ppug:SOURCE} = {pp:" + PublicationPeriodModel.PK + "} JOIN " + UserGroupModel._TYPECODE + " AS ug ON {ug:"
			+ UserGroupModel.PK + "} = {ppug:TARGET}} WHERE {pp:" + PublicationPeriodModel.CATALOGVERSION + "} = ?catalogVersion";

	@Override
	public List<PublicationPeriodModel> findCurrentActivePublicationPeriods(final Set<UserGroupModel> userGroups,
			final Date currentTime, final Integer timeOfDay, final BaseSiteModel baseSite)
	{
		validateParameterNotNullStandardMessage("userGroups", userGroups);

		String query = FIND_ACTIVE_PUBLICATION_PERIODS;
		if (currentTime != null)
		{
			query += FIND_ACTIVE_PUBLICATION_PERIODS_DATE_SUFIX;
		}
		if (timeOfDay != null)
		{
			query += FIND_ACTIVE_PUBLICATION_PERIODS_HOUR_SUFIX;
		}
		if (baseSite != null)
		{
			query += FIND_ACTIVE_PUBLICATION_PERIODS_SITE_SUFIX;
		}

		final FlexibleSearchQuery flexibleQuery = new FlexibleSearchQuery(query);
		flexibleQuery.addQueryParameter("userGroups", userGroups);
		if (currentTime != null)
		{
			flexibleQuery.addQueryParameter("currentTime", currentTime);
		}
		if (timeOfDay != null)
		{
			flexibleQuery.addQueryParameter("timeOfDay", timeOfDay);
		}
		if (baseSite != null)
		{
			flexibleQuery.addQueryParameter("baseSite", baseSite);
		}

		final SearchResult<PublicationPeriodModel> results = getFlexibleSearchService().search(flexibleQuery);
		return results.getResult();
	}

	@Override
	public List<PublicationPeriodModel> findAllActivePublicationPeriods(final CatalogVersionModel catalogVersion)
	{
		validateParameterNotNullStandardMessage("catalogVersion", catalogVersion);

		final String query = FIND_ALL_ACTIVE_PUBLICATION_PERIODS;

		final FlexibleSearchQuery flexibleQuery = new FlexibleSearchQuery(query);

		if (catalogVersion != null)
		{
			flexibleQuery.addQueryParameter("catalogVersion", catalogVersion);
		}

		final SearchResult<PublicationPeriodModel> results = getFlexibleSearchService().search(flexibleQuery);
		return results.getResult();
	}
}
