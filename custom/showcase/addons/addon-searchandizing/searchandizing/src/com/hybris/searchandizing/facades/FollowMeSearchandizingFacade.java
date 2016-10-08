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
package com.hybris.searchandizing.facades;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.model.solrsearch.config.SolrSortModel;

import java.util.List;


/**
 * @author rmcotton
 * 
 */
public interface FollowMeSearchandizingFacade
{
	String getUrlForCategoryPageCategoryRefinement(SearchStateData currentSearchState, String currentCategoryCode,
			CategoryModel refinementCategory);

	String getUrlForCategoryRefinement(SearchStateData currentSearchState, CategoryModel refinementCategory);

	String getUrlForCategoryPage(CategoryModel category);

	List<ProductData> getSpotlightProducts(SearchStateData searchState, String currentCategoryCode, SolrSortModel primarySort,
			SolrSortModel secondarySort, CategoryModel stickyCategory);

	List<ProductData> getSpotlightProducts(SearchStateData searchState, String currentCategoryCode, SolrSortModel primarySort,
			SolrSortModel secondarySort, CategoryModel stickyCategory, Boolean inStock);

	List<ProductData> getProducts(SearchStateData searchState, String currentCategoryCode, SolrSortModel sort,
			CategoryModel stickyCategory, int numberOfProducts);
}
