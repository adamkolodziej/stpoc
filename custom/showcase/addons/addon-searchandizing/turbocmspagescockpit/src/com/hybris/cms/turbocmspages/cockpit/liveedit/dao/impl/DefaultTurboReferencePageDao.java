/**
 * 
 */
package com.hybris.cms.turbocmspages.cockpit.liveedit.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.cms.turbocmspages.cockpit.liveedit.dao.TurboReferencePageDao;


/**
 * @author rmcotton
 * 
 */
public class DefaultTurboReferencePageDao implements TurboReferencePageDao
{

	private FlexibleSearchService flexibleSearchService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.cms.turbocmspages.cockpit.liveedit.dao.TurboReferencePageDao#findObjectTemplateCodesForCode(java.lang
	 * .String)
	 */
	@Override
	public Set<String> findObjectTemplateCodesForCode(final String code)
	{
		final StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT DISTINCT {objectTemplateCode} ");
		queryBuilder.append("FROM {").append("CockpitUIComponentConfiguration").append("} ");
		queryBuilder.append("WHERE {").append("code").append("} = ?code ");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryBuilder.toString(), Collections.singletonMap("code", code));
		query.setResultClassList(Collections.singletonList(String.class));
		query.setNeedTotal(false);
		final SearchResult<String> result = getFlexibleSearchService().search(query);
		return new HashSet<String>(result.getResult());
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
