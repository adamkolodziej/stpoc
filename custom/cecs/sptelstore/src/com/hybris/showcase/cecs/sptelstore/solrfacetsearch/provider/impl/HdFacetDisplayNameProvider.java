/**
 * 
 */
package com.hybris.showcase.cecs.sptelstore.solrfacetsearch.provider.impl;

import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractFacetValueDisplayNameProvider;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;


/**
 * @author npavlovic
 * 
 *         CECS-65 Add solr config for new product types
 */
public class HdFacetDisplayNameProvider extends AbstractFacetValueDisplayNameProvider
{
	@Override
	public String getDisplayName(final SearchQuery searchQuery, final IndexedProperty indexedProperty, final String name)
	{
		if (name.equals("true"))
		{
			return "Yes";
		}
		else
		{
			return "No";
		}
	}
}
