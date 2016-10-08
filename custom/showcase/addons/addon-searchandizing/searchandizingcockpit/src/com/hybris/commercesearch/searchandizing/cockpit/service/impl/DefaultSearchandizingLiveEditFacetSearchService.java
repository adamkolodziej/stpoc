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
package com.hybris.commercesearch.searchandizing.cockpit.service.impl;

import de.hybris.platform.acceleratorservices.urldecoder.FrontendUrlDecoder;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.classification.ClassificationSystemVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commercesearch.searchandizing.navigationalstate.NavigationalStateService;
import de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SearchQueryPageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchRequest;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchResponse;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.commercesearch.searchandizing.cockpit.service.SearchandizingLiveEditFacetSearchService;


/**
 * This service requires searchandizing features activated.
 * 
 * @author rmcotton
 * 
 */
public class DefaultSearchandizingLiveEditFacetSearchService implements SearchandizingLiveEditFacetSearchService
{
	private ModelService modelService;
	private TypeService typeService;
	private UserService userService;
	private CatalogVersionService catalogVersionService;
	private CronJobService cronJobService;
	private NavigationalStateService navigationalStateService;
	private Converter<SearchQueryData, SolrSearchQueryData> solrSearchQueryDecoder;
	private FrontendUrlDecoder<CategoryModel> categoryUrlDecoder;

	private Converter<SearchQueryPageableData<SolrSearchQueryData>, SolrSearchRequest> searchQueryPageableConverter;
	private Converter<SolrSearchRequest, SolrSearchResponse> searchRequestConverter;
	private Converter<SolrSearchResponse, ProductCategorySearchPageData> searchResponseConverter;


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.steroidsliveedit.facades.SteroidsLiveEditSearchAndNavigationFacade#isExactCategoryLocation(de.hybris
	 * .platform.commercefacades.search.data.SearchStateData)
	 */
	@Override
	public boolean isExactCategoryLocation(final SearchStateData searchState)
	{
		final CategoryModel category = getCategoryUrlDecoder().decode(searchState.getUrl());
		if (category == null)
		{
			return false;
		}

		final SolrSearchQueryData searchQuery = getSolrSearchQueryDecoder().convert(searchState.getQuery());
		if (CollectionUtils.isEmpty(searchQuery.getFilterTerms()))
		{
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.steroidsliveedit.facades.LiveEditFacetSearchFacade#getCategory(de.hybris.platform.commercefacades.search
	 * .data.SearchStateData)
	 */
	@Override
	public String getCategoryCode(final SearchStateData searchState)
	{
		final CategoryModel category = getCategoryUrlDecoder().decode(searchState.getUrl());
		if (category == null)
		{
			return null;
		}
		return category.getCode();
	}



	protected SearchQueryPageableData<SolrSearchQueryData> buildSearchQueryPageableData(final SolrSearchQueryData searchQueryData,
			final PageableData pageableData)
	{
		final SearchQueryPageableData<SolrSearchQueryData> searchQueryPageableData = createSearchQueryPageableData();
		searchQueryPageableData.setSearchQueryData(searchQueryData);
		searchQueryPageableData.setPageableData(pageableData);
		return searchQueryPageableData;
	}

	// Create methods for data object - can be overridden in spring config

	protected SearchQueryPageableData<SolrSearchQueryData> createSearchQueryPageableData()
	{
		return new SearchQueryPageableData<SolrSearchQueryData>();
	}




	/**
	 * Select the first product catalog version that has a SolrFacetSearchConfig
	 * 
	 * @return the selected CatalogVersionModel
	 */
	protected Set<SolrFacetSearchConfigModel> getFacetSearchConfigs(final Collection<CatalogVersionModel> catalogVersions)
	{
		final Set<SolrFacetSearchConfigModel> configs = new LinkedHashSet<SolrFacetSearchConfigModel>();
		for (final CatalogVersionModel catalogVersion : catalogVersions)
		{
			final List<SolrFacetSearchConfigModel> facetSearchConfigs = catalogVersion.getFacetSearchConfigs();
			configs.addAll(facetSearchConfigs);
		}
		return configs;
	}

	/**
	 * Get all the session catalog versions that belong to product catalogs of the current site.
	 * 
	 * @return the list of session catalog versions
	 */
	protected Collection<CatalogVersionModel> getSessionProductCatalogVersions()
	{

		final Collection<CatalogVersionModel> sessionCatalogVersions = getCatalogVersionService().getSessionCatalogVersions();

		final Collection<CatalogVersionModel> result = new ArrayList<CatalogVersionModel>();
		for (final CatalogVersionModel sessionCatalogVersion : sessionCatalogVersions)
		{
			if (!(sessionCatalogVersion instanceof ClassificationSystemVersionModel)
					&& !(sessionCatalogVersion.getCatalog() instanceof ContentCatalogModel))
			{
				result.add(sessionCatalogVersion);
			}
		}
		return result;
	}

	/**
	 * @return the solrSearchQueryDecoder
	 */
	public Converter<SearchQueryData, SolrSearchQueryData> getSolrSearchQueryDecoder()
	{
		return solrSearchQueryDecoder;
	}


	/**
	 * @param solrSearchQueryDecoder
	 *           the solrSearchQueryDecoder to set
	 */
	@Required
	public void setSolrSearchQueryDecoder(final Converter<SearchQueryData, SolrSearchQueryData> solrSearchQueryDecoder)
	{
		this.solrSearchQueryDecoder = solrSearchQueryDecoder;
	}


	/**
	 * @return the categoryUrlDecoder
	 */
	public FrontendUrlDecoder<CategoryModel> getCategoryUrlDecoder()
	{
		return categoryUrlDecoder;
	}


	/**
	 * @param categoryUrlDecoder
	 *           the categoryUrlDecoder to set
	 */
	@Required
	public void setCategoryUrlDecoder(final FrontendUrlDecoder<CategoryModel> categoryUrlDecoder)
	{
		this.categoryUrlDecoder = categoryUrlDecoder;
	}

	protected Converter<SearchQueryPageableData<SolrSearchQueryData>, SolrSearchRequest> getSearchQueryPageableConverter()
	{
		return searchQueryPageableConverter;
	}

	@Required
	public void setSearchQueryPageableConverter(
			final Converter<SearchQueryPageableData<SolrSearchQueryData>, SolrSearchRequest> searchQueryPageableConverter)
	{
		this.searchQueryPageableConverter = searchQueryPageableConverter;
	}

	protected Converter<SolrSearchRequest, SolrSearchResponse> getSearchRequestConverter()
	{
		return searchRequestConverter;
	}

	@Required
	public void setSearchRequestConverter(final Converter<SolrSearchRequest, SolrSearchResponse> searchRequestConverter)
	{
		this.searchRequestConverter = searchRequestConverter;
	}

	protected Converter<SolrSearchResponse, ProductCategorySearchPageData> getSearchResponseConverter()
	{
		return searchResponseConverter;
	}

	@Required
	public void setSearchResponseConverter(
			final Converter<SolrSearchResponse, ProductCategorySearchPageData> searchResponseConverter)
	{
		this.searchResponseConverter = searchResponseConverter;
	}

	/**
	 * @return the navigationalStateService
	 */
	public NavigationalStateService getNavigationalStateService()
	{
		return navigationalStateService;
	}

	/**
	 * @param navigationalStateService
	 *           the navigationalStateService to set
	 */
	@Required
	public void setNavigationalStateService(final NavigationalStateService navigationalStateService)
	{
		this.navigationalStateService = navigationalStateService;
	}

	@Required
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	public CatalogVersionService getCatalogVersionService()
	{
		return this.catalogVersionService;
	}

	/**
	 * @return the cronJobService
	 */
	public CronJobService getCronJobService()
	{
		return cronJobService;
	}

	/**
	 * @param cronJobService
	 *           the cronJobService to set
	 */
	@Required
	public void setCronJobService(final CronJobService cronJobService)
	{
		this.cronJobService = cronJobService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	@Required
	public void setTypeService(final TypeService typeService)
	{
		this.typeService = typeService;
	}

	public TypeService getTypeService()
	{
		return this.typeService;
	}

}
