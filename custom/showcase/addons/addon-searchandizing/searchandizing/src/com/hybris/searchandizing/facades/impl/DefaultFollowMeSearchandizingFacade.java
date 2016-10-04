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
package com.hybris.searchandizing.facades.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.ProductSearchFacade;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.category.CommerceCategoryService;
import de.hybris.platform.commerceservices.model.solrsearch.config.SolrSortModel;
import de.hybris.platform.commerceservices.model.user.StoreEmployeeGroupModel;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryTermData;
import de.hybris.platform.commerceservices.threadcontext.ThreadContextService;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.searchandizing.constants.SearchandizingConstants;
import com.hybris.searchandizing.facades.FollowMeSearchandizingFacade;
import com.hybris.searchandizing.services.TypedCategoryService;



/**
 * @author rmcotton
 * 
 */
public class DefaultFollowMeSearchandizingFacade implements FollowMeSearchandizingFacade
{

	private Converter<CategoryModel, CategoryData> categoryUrlConverter;
	private Converter<SearchQueryData, SolrSearchQueryData> solrSearchQueryDecoder;
	private Converter<SolrSearchQueryData, SearchQueryData> solrSearchQueryEncoder;

	private CommerceCategoryService commerceCategoryService;
	private TypedCategoryService brandCategoryService;
	private ProductSearchFacade<ProductData> productSearchFacade;

	private ThreadContextService threadContextService;
	private UserService userService;

	private String brandsFacetId = "brand";
	private String allCategoriesFacetId = "allCategories";
	private final String availableInStoresFacetId = "availableInStores";


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.showcase.yacceleratorfacades.search.FollowMeSearchandisingFacade#getUrlForCategoryPageCategoryRefinment
	 * (de.hybris.platform.commercefacades.search.data.SearchStateData, de.hybris.platform.category.model.CategoryModel,
	 * de.hybris.platform.category.model.CategoryModel)
	 */
	@Override
	public String getUrlForCategoryPageCategoryRefinement(final SearchStateData currentSearchState,
			final String currentCategoryCode, final CategoryModel refinementCategory)
	{
		if (StringUtils.isNotBlank(currentCategoryCode))
		{
			final CategoryModel currentCategory = getCommerceCategoryService().getCategoryForCode(currentCategoryCode);
			final String baseUrl = getCategoryUrlConverter().convert(currentCategory).getUrl();

			final SolrSearchQueryData baseQuery = getSolrSearchQueryDecoder().convert(currentSearchState.getQuery());
			final SolrSearchQueryTermData solrSearchQueryTermData = new SolrSearchQueryTermData();
			solrSearchQueryTermData.setKey(getFacetIdForCategory(refinementCategory));
			solrSearchQueryTermData.setValue(refinementCategory.getCode());
			baseQuery.getFilterTerms().add(solrSearchQueryTermData);

			return baseUrl + "?q=" + getSolrSearchQueryEncoder().convert(baseQuery).getValue();
		}
		else
		{
			final SolrSearchQueryData baseQuery = getSolrSearchQueryDecoder().convert(currentSearchState.getQuery());
			final SolrSearchQueryTermData solrSearchQueryTermData = new SolrSearchQueryTermData();
			solrSearchQueryTermData.setKey(getFacetIdForCategory(refinementCategory));
			solrSearchQueryTermData.setValue(refinementCategory.getCode());
			baseQuery.getFilterTerms().add(solrSearchQueryTermData);

			return "?q=" + getSolrSearchQueryEncoder().convert(baseQuery).getValue();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.showcase.yacceleratorfacades.search.FollowMeSearchandisingFacade#getUrlForCategoryRefinment(de.hybris
	 * .platform.commercefacades.search.data.SearchStateData, de.hybris.platform.category.model.CategoryModel)
	 */
	@Override
	public String getUrlForCategoryRefinement(final SearchStateData currentSearchState, final CategoryModel refinementCategory)
	{
		return getUrlForCategoryPageCategoryRefinement(currentSearchState, null, refinementCategory);
	}

	/**
	 * Bit cheesey. Should be a cleaner way of figuring out a facet id. Problem currently is that root categories are
	 * spring bean configured rather than attached to a category facet item.
	 */
	protected String getFacetIdForCategory(final CategoryModel category)
	{
		if (getBrandCategoryService().isCategoryType(category))
		{
			return getBrandsFacetId();
		}
		return getAllCategoriesFacetId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.showcase.yacceleratorfacades.search.FollowMeSearchandisingFacade#getUrlForCategoryPage(de.hybris.platform
	 * .category.model.CategoryModel)
	 */
	@Override
	public String getUrlForCategoryPage(final CategoryModel category)
	{
		return getCategoryUrlConverter().convert(category).getUrl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.showcase.yacceleratorfacades.search.FollowMeSearchandisingFacade#getSpotlightProducts(de.hybris.platform
	 * .commercefacades.search.data.SearchStateData, java.lang.String,
	 * de.hybris.platform.commerceservices.model.solrsearch.config.SolrSortModel,
	 * de.hybris.platform.commerceservices.model.solrsearch.config.SolrSortModel)
	 */
	@Override
	public List<ProductData> getSpotlightProducts(final SearchStateData searchStateIn, final String currentCategoryCode,
			final SolrSortModel primarySort, final SolrSortModel secondarySort, final CategoryModel stickyCategory)
	{
		return getSpotlightProducts(searchStateIn, currentCategoryCode, primarySort, secondarySort, stickyCategory, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.showcase.yacceleratorfacades.search.FollowMeSearchandisingFacade#getSpotlightProducts(de.hybris.platform
	 * .commercefacades.search.data.SearchStateData, java.lang.String,
	 * de.hybris.platform.commerceservices.model.solrsearch.config.SolrSortModel,
	 * de.hybris.platform.commerceservices.model.solrsearch.config.SolrSortModel)
	 */
	@Override
	public List<ProductData> getSpotlightProducts(final SearchStateData searchStateIn, final String currentCategoryCode,
			final SolrSortModel primarySort, final SolrSortModel secondarySort, final CategoryModel stickyCategory,
			final Boolean inStock)
	{
		final SearchStateData searchState = new SearchStateData();
		searchState.setQuery(searchStateIn.getQuery());
		searchState.setUrl(searchStateIn.getUrl());

		final boolean secondarySortEnabled = secondarySort != null;

		final PageableData primaryPageableData = new PageableData();
		primaryPageableData.setCurrentPage(0);
		primaryPageableData.setPageSize(secondarySortEnabled ? 1 : 2); // only want one result, if secondary sort is enabled
		primaryPageableData.setSort(primarySort.getCode());

		final List<ProductData> results = new ArrayList<ProductData>(2);

		if (inStock != null && inStock.booleanValue())
		{
			addInStockFilter(searchState);
		}

		final String baseCategoryCode = prepareSearch(searchState, currentCategoryCode, stickyCategory);

		// execute primary result search
		final List<ProductData> primaryResults = executeSearch(searchState, primaryPageableData, baseCategoryCode);
		if (primaryResults.isEmpty())
		{
			// no results so give up here.
			return results;
		}
		results.add(primaryResults.get(0));

		if (!secondarySortEnabled)
		{
			if (primaryResults.size() > 1)
			{
				results.add(primaryResults.get(1));
			}
			return results;
		}
		else
		{
			final PageableData secondaryPageableData = new PageableData();
			secondaryPageableData.setCurrentPage(0);
			secondaryPageableData.setPageSize(2); // extra one in case the first result is the same as the primary result
			secondaryPageableData.setSort(secondarySort.getCode());

			final List<ProductData> secondaryResults = executeSearch(searchState, secondaryPageableData, baseCategoryCode);
			if (secondaryResults.isEmpty())
			{
				return results;
			}
			for (final ProductData r : secondaryResults)
			{
				// loop thru results until we get a new product
				if (!primaryResults.get(0).getCode().equals(r.getCode()))
				{
					results.add(r);
					break;
				}
			}
			return results;
		}
	}

	protected void addInStockFilter(final SearchStateData searchState)
	{
		final SolrSearchQueryData baseQuery = getSolrSearchQueryDecoder().convert(searchState.getQuery());
		if (CollectionUtils.isEmpty(baseQuery.getFilterTerms()))
		{
			baseQuery.setFilterTerms(new ArrayList<SolrSearchQueryTermData>());
		}

		final List<PointOfServiceModel> posList = getPointsOfServiceForCurrentUser();

		if (posList != null && !posList.isEmpty())
		{
			final SolrSearchQueryTermData solrSearchQueryTermData = new SolrSearchQueryTermData();
			solrSearchQueryTermData.setKey(availableInStoresFacetId);
			solrSearchQueryTermData.setValue(posList.get(0).getName());
			baseQuery.getFilterTerms().add(solrSearchQueryTermData);

			// re-encode the modified query
			searchState.setQuery(getSolrSearchQueryEncoder().convert(baseQuery));
		}
	}

	private List<PointOfServiceModel> getPointsOfServiceForCurrentUser() throws IllegalStateException
	{

		final List<PointOfServiceModel> result = new ArrayList<PointOfServiceModel>();
		final UserModel currentUser = userService.getCurrentUser();
		if (!(currentUser instanceof EmployeeModel))
		{
			throw new IllegalStateException("Current user '" + currentUser + "' is not an Employee.");
		}

		for (final PrincipalGroupModel principalGroup : currentUser.getGroups())
		{
			if (principalGroup instanceof StoreEmployeeGroupModel)
			{
				final PointOfServiceModel store = ((StoreEmployeeGroupModel) principalGroup).getStore();
				if (store != null)
				{
					result.add(store);
				}
			}
		}
		if (CollectionUtils.isEmpty(result))
		{
			throw new IllegalStateException("No 'points of service' assigned to the current employee.");
		}
		return result;
	}

	protected String prepareSearch(final SearchStateData searchState, final String currentCategoryCode,
			final CategoryModel stickyCategory)
	{
		if (stickyCategory != null)
		{
			// decode the current query and remove all facets that are the same facet as our sticky category
			final SolrSearchQueryData baseQuery = getSolrSearchQueryDecoder().convert(searchState.getQuery());
			final String facetId = this.getFacetIdForCategory(stickyCategory);
			if (CollectionUtils.isNotEmpty(baseQuery.getFilterTerms()))
			{
				for (final ListIterator<SolrSearchQueryTermData> li = baseQuery.getFilterTerms().listIterator(); li.hasNext();)
				{
					if (li.next().getKey().equals(facetId))
					{
						li.remove();
					}
				}
			}
			else
			{
				baseQuery.setFilterTerms(new ArrayList<SolrSearchQueryTermData>(1));
			}

			// create the query for our sticky category
			final SolrSearchQueryTermData solrSearchQueryTermData = new SolrSearchQueryTermData();
			solrSearchQueryTermData.setKey(facetId);
			solrSearchQueryTermData.setValue(stickyCategory.getCode());
			baseQuery.getFilterTerms().add(solrSearchQueryTermData);

			// re-encode the modified query
			searchState.setQuery(getSolrSearchQueryEncoder().convert(baseQuery));

			// if we have started from a category landing page, then the current category will need to be removed if it belongs to the same
			// facet as our sticky category
			if (StringUtils.isNotBlank(currentCategoryCode))
			{
				final String facetIdForCurrentCategory = this.getFacetIdForCategory(getCommerceCategoryService().getCategoryForCode(
						currentCategoryCode));
				if (facetIdForCurrentCategory.equals(facetId))
				{
					return null;
				}
				else
				{
					return currentCategoryCode;
				}
			}
		}
		return currentCategoryCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.showcase.yacceleratorfacades.search.FollowMeSearchandisingFacade#getProducts(de.hybris.platform.
	 * commercefacades.search.data.SearchStateData, java.lang.String,
	 * de.hybris.platform.commerceservices.model.solrsearch.config.SolrSortModel,
	 * de.hybris.platform.category.model.CategoryModel, int)
	 */
	@Override
	public List<ProductData> getProducts(final SearchStateData searchState, final String currentCategoryCode,
			final SolrSortModel sort, final CategoryModel stickyCategory, final int maxNumberOfProducts)
	{
		final PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(0);
		pageableData.setPageSize(maxNumberOfProducts);
		pageableData.setSort(sort.getCode());

		if (searchState == null)
		{
			final SearchStateData newSearchState = new SearchStateData();
			final String baseCategoryCode = prepareSearch(newSearchState, currentCategoryCode, stickyCategory);
			return executeSearch(newSearchState, pageableData, baseCategoryCode);
		}
		else
		{
			final String baseCategoryCode = prepareSearch(searchState, currentCategoryCode, stickyCategory);
			return executeSearch(searchState, pageableData, baseCategoryCode);
		}
	}



	protected List<ProductData> executeSearch(final SearchStateData searchState, final PageableData pageableData,
			final String baseCategoryCode)
	{

		final ProductSearchPageData<SearchStateData, ProductData> result = getThreadContextService()
				.executeInContext(
						new ThreadContextService.Executor<ProductSearchPageData<SearchStateData, ProductData>, ThreadContextService.Nothing>()
						{

							@Override
							public ProductSearchPageData<SearchStateData, ProductData> execute()
							{
								getThreadContextService().setAttribute(SearchandizingConstants.SESSION_OBSERVE_SORT_VISIBILTY_FLAG,
										Boolean.FALSE);
								if (baseCategoryCode != null)
								{
									// If we are on a category landing page the search query data will not contain the category selection in
									// the query. we therefore must do a category search in these situations.
									return getProductSearchFacade().categorySearch(baseCategoryCode, searchState, pageableData);
								}
								else
								{
									return getProductSearchFacade().textSearch(searchState, pageableData);
								}
							}
						});

		return result.getResults();
	}

	@Required
	public void setCategoryUrlConverter(final Converter<CategoryModel, CategoryData> categoryUrlConverter)
	{
		this.categoryUrlConverter = categoryUrlConverter;
	}

	public Converter<CategoryModel, CategoryData> getCategoryUrlConverter()
	{
		return this.categoryUrlConverter;
	}

	@Required
	public void setCommerceCategoryService(final CommerceCategoryService commerceCategoryService)
	{
		this.commerceCategoryService = commerceCategoryService;
	}

	public CommerceCategoryService getCommerceCategoryService()
	{
		return this.commerceCategoryService;
	}

	@Required
	public void setProductSearchFacade(final ProductSearchFacade<ProductData> productSearchFacade)
	{
		this.productSearchFacade = productSearchFacade;
	}

	public ProductSearchFacade<ProductData> getProductSearchFacade()
	{
		return this.productSearchFacade;
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
	 * @return the solrSearchQueryDecoder
	 */
	public Converter<SearchQueryData, SolrSearchQueryData> getSolrSearchQueryDecoder()
	{
		return solrSearchQueryDecoder;
	}

	/**
	 * @param solrSearchQueryEncoder
	 *           the solrSearchQueryEncoder to set
	 */
	@Required
	public void setSolrSearchQueryEncoder(final Converter<SolrSearchQueryData, SearchQueryData> solrSearchQueryEncoder)
	{
		this.solrSearchQueryEncoder = solrSearchQueryEncoder;
	}

	/**
	 * @return the solrSearchQueryEncoder
	 */
	public Converter<SolrSearchQueryData, SearchQueryData> getSolrSearchQueryEncoder()
	{
		return solrSearchQueryEncoder;
	}

	/**
	 * @param brandCategoryService
	 *           the showcaseCategoryService to set
	 */
	@Required
	public void setBrandCategoryService(final TypedCategoryService brandCategoryService)
	{
		this.brandCategoryService = brandCategoryService;
	}

	/**
	 * @return the showcaseCategoryService
	 */
	public TypedCategoryService getBrandCategoryService()
	{
		return this.brandCategoryService;
	}

	/**
	 * @return the threadContextService
	 */
	public ThreadContextService getThreadContextService()
	{
		return threadContextService;
	}

	/**
	 * @param threadContextService
	 *           the threadContextService to set
	 */
	@Required
	public void setThreadContextService(final ThreadContextService threadContextService)
	{
		this.threadContextService = threadContextService;
	}

	/**
	 * @return the brandsFacetId
	 */
	public String getBrandsFacetId()
	{
		return brandsFacetId;
	}

	/**
	 * @param brandsFacetId
	 *           the brandsFacetId to set
	 */
	public void setBrandsFacetId(final String brandsFacetId)
	{
		this.brandsFacetId = brandsFacetId;
	}

	/**
	 * @return the allCategoriesFacetId
	 */
	public String getAllCategoriesFacetId()
	{
		return allCategoriesFacetId;
	}

	/**
	 * @param allCategoriesFacetId
	 *           the allCategoriesFacetId to set
	 */
	public void setAllCategoriesFacetId(final String allCategoriesFacetId)
	{
		this.allCategoriesFacetId = allCategoriesFacetId;
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
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

}
