/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 * 
 */
package com.hybris.searchandizing.wildwords.service;

import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrStopWordModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrSynonymConfigModel;
import de.hybris.platform.solrfacetsearch.model.redirect.SolrFacetSearchKeywordRedirectModel;

import java.util.Collection;
import java.util.List;
import java.util.Locale;


/**
 * @author rmcotton
 * 
 */
public interface WildWordsAdminService
{


	Collection<SolrFacetSearchKeywordRedirectModel> getKeywordRedirectModelList(final FacetSearchConfig facetSearchConfig,
			final Locale loc);

	List<SolrSynonymConfigModel> getSynonyms(final FacetSearchConfig facetSearchConfig, final Locale loc);

	List<SolrStopWordModel> getStopWords(final FacetSearchConfig facetSearchConfig, final Locale loc);

	SolrFacetSearchConfigModel getSolrFacetSearchConfigModel(final FacetSearchConfig facetSearchConfig);
}
