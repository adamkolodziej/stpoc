/**
 * 
 */
package com.hybris.showcase.cecs.tricaststore.solrfacetsearch.provider.impl;

import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractFacetValueDisplayNameProvider;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;


/**
 * @author npavlovic
 * 
 *         CECS-65 Add solr config for new product types
 */
public class ReleaseYearFacetDisplayNameProvider extends AbstractFacetValueDisplayNameProvider
{
	@Override
	public String getDisplayName(final SearchQuery searchQuery, final IndexedProperty indexedProperty, final String paramString)
	{
		return String.valueOf((int) Float.parseFloat(paramString));
	}
}
