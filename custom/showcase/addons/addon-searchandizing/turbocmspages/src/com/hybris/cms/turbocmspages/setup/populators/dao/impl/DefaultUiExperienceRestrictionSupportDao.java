/**
 * 
 */
package com.hybris.cms.turbocmspages.setup.populators.dao.impl;

import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.commerceservices.enums.UiExperienceLevel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.hybris.cms.turbocmspages.setup.populators.dao.UiExperienceSupportDao;


/**
 * @author rmcotton
 * 
 */
public class DefaultUiExperienceRestrictionSupportDao extends AbstractItemDao implements UiExperienceSupportDao
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.cms.turbocmspages.setup.populators.dao.UiExperienceSupportDao#hasUiExperienceRestrictions(de.hybris
	 * .platform.cms2.model.contents.ContentCatalogModel, de.hybris.platform.acceleratorservices.enums.UiExperienceLevel)
	 */
	@Override
	public boolean hasUiExperienceRestrictions(final ContentCatalogModel contentCatalog, final UiExperienceLevel uiExperience)
	{

		final String QUERY = "select count({uie.PK}) from {CMSUiExperienceRestriction as uie}, {CatalogVersion as cv}, {Catalog as c}, {RestrictionsForPages as r}, {AbstractPage as p} "
				+ "where {c.PK} = ?catalog and {uie.catalogVersion} = {cv.pk} and {cv.catalog} = {c.PK} and {r.target} = {uie.PK} and {r.source} = {p.PK} and {uie.uiExperience} = ?uiExperience ";

		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("catalog", contentCatalog);
		params.put("uiExperience", uiExperience);
		final FlexibleSearchQuery query = new FlexibleSearchQuery(QUERY, params);
		query.setResultClassList(Collections.singletonList(Long.class));
		final SearchResult<Long> result = getFlexibleSearchService().search(query);
		return (result.getResult().get(0).longValue() > 0);
	}
}
