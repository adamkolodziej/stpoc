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
package com.hybris.searchandizing.wildwords.service.impl;

import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.impl.FacetSearchConfigDao;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrStopWordModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrSynonymConfigModel;
import de.hybris.platform.solrfacetsearch.model.redirect.SolrFacetSearchKeywordRedirectModel;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.searchandizing.wildwords.service.WildWordsAdminService;


/**
 * @author rmcotton
 * 
 */
public class DefaultWildWordsAdminService implements WildWordsAdminService
{

	private FacetSearchConfigDao facetSearchConfigDao;

	@Override
	public Collection<SolrFacetSearchKeywordRedirectModel> getKeywordRedirectModelList(final FacetSearchConfig facetSearchConfig,
			final Locale loc)
	{
		return getFacetSearchConfigDao().findSolrFacetSearchConfigByName(facetSearchConfig.getName())
				.getLanguageKeywordRedirectMapping(loc);
	}

	@Override
	public List<SolrSynonymConfigModel> getSynonyms(final FacetSearchConfig facetSearchConfig, final Locale loc)
	{
		return getFacetSearchConfigDao().findSolrFacetSearchConfigByName(facetSearchConfig.getName())
				.getLanguageSynonymMapping(loc);
	}

	@Override
	public List<SolrStopWordModel> getStopWords(final FacetSearchConfig facetSearchConfig, final Locale loc)
	{
		return getFacetSearchConfigDao().findSolrFacetSearchConfigByName(facetSearchConfig.getName()).getLanguageStopWordMapping(
				loc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.searchandizing.wildwords.service.WildWordsAdminService#getSolrFacetSearchConfigModel(de.hybris.platform
	 * .solrfacetsearch.config.FacetSearchConfig)
	 */
	@Override
	public SolrFacetSearchConfigModel getSolrFacetSearchConfigModel(final FacetSearchConfig facetSearchConfig)
	{
		return getFacetSearchConfigDao().findSolrFacetSearchConfigByName(facetSearchConfig.getName());
	}

	/**
	 * @return the facetSearchConfigDao
	 */
	public FacetSearchConfigDao getFacetSearchConfigDao()
	{
		return facetSearchConfigDao;
	}

	/**
	 * @param facetSearchConfigDao
	 *           the facetSearchConfigDao to set
	 */
	@Required
	public void setFacetSearchConfigDao(final FacetSearchConfigDao facetSearchConfigDao)
	{
		this.facetSearchConfigDao = facetSearchConfigDao;
	}

}
