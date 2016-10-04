/**
 * 
 */
package com.hybris.solrrollup.solr.populators;

import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrDocumentData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchResponse;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SearchResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Required;


/**
 * @author wojciech.piotrowiak
 * 
 */
public class SearchResponseGroupedResultsPopulator<FACET_SEARCH_CONFIG_TYPE, INDEXED_TYPE_TYPE, INDEXED_PROPERTY_TYPE, INDEXED_TYPE_SORT_TYPE, ITEM>
		implements
		Populator<SolrSearchResponse<FACET_SEARCH_CONFIG_TYPE, INDEXED_TYPE_TYPE, INDEXED_PROPERTY_TYPE, SearchQuery, INDEXED_TYPE_SORT_TYPE, SearchResult>, SearchPageData<ITEM>>
{
	private Converter<SolrDocumentData, ITEM> searchResultConverter;

	protected Converter<SolrDocumentData, ITEM> getSearchResultConverter()
	{
		return searchResultConverter;
	}

	@Required
	public void setSearchResultConverter(final Converter<SolrDocumentData, ITEM> searchResultConverter)
	{
		this.searchResultConverter = searchResultConverter;
	}

	@Override
	public void populate(
			final SolrSearchResponse<FACET_SEARCH_CONFIG_TYPE, INDEXED_TYPE_TYPE, INDEXED_PROPERTY_TYPE, SearchQuery, INDEXED_TYPE_SORT_TYPE, de.hybris.platform.solrfacetsearch.search.SearchResult> source,
			final SearchPageData<ITEM> target)
	{
		target.setResults(buildResults(source.getSearchResult(), source.getRequest().getSearchQuery()));
	}

	protected List<ITEM> buildResults(final SearchResult solrSearchResult, final SearchQuery searchQuery)
	{
		if (solrSearchResult != null)
		{
			final List<ITEM> result = new ArrayList<ITEM>(solrSearchResult.getPageSize());

			if (solrSearchResult.getSolrObject().getResults() != null)
			{
				for (final SolrDocument solrDocument : solrSearchResult.getSolrObject().getResults())
				{
					final SolrDocumentData<SearchQuery, SolrDocument> documentData = createSolrDocumentData();
					documentData.setSearchQuery(searchQuery);
					documentData.setSolrDocument(solrDocument);
					result.add(getSearchResultConverter().convert(documentData));
				}
			}
			else
			{
				for (final GroupCommand command : solrSearchResult.getSolrObject().getGroupResponse().getValues())
				{
					for (final Group group : command.getValues())
					{
						for (final SolrDocument solrDocument : group.getResult())
						{
							final SolrDocumentData<SearchQuery, SolrDocument> documentData = createSolrDocumentData();
							documentData.setSearchQuery(searchQuery);
							documentData.setSolrDocument(solrDocument);
							result.add(getSearchResultConverter().convert(documentData));
						}
					}
				}

			}
			return result;
		}
		return Collections.emptyList();
	}

	protected SolrDocumentData<SearchQuery, SolrDocument> createSolrDocumentData()
	{
		return new SolrDocumentData<SearchQuery, SolrDocument>();
	}
}