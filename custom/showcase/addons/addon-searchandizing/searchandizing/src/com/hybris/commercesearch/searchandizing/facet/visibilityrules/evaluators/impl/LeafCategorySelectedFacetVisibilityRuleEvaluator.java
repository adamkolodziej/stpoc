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
package com.hybris.commercesearch.searchandizing.facet.visibilityrules.evaluators.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercesearch.searchandizing.facet.visibilityrules.evaluators.FacetVisibilityRuleEvaluator;
import de.hybris.platform.commerceservices.category.CommerceCategoryService;
import de.hybris.platform.commerceservices.search.facetdata.BreadcrumbData;
import de.hybris.platform.commerceservices.search.facetdata.FacetData;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchResponse;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.IndexedType;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.hybris.commercesearch.searchandizing.facet.visibilityrules.config.LeafCategorySelectedFacetVisibilityRule;


/**
 * 
 */
public class LeafCategorySelectedFacetVisibilityRuleEvaluator<NATIVESEARCHRESULT> implements
		FacetVisibilityRuleEvaluator<SolrSearchResponse, ProductSearchPageData, LeafCategorySelectedFacetVisibilityRule>

{

	private CommerceCategoryService commerceCategoryService;

	@Override
	public boolean evaluateFilter(final SolrSearchResponse searchResponse, final ProductSearchPageData searchPageData,
			final LeafCategorySelectedFacetVisibilityRule rule, final FacetData facetToTest)
	{
		final Set<CategoryModel> selectedCategories = new HashSet<CategoryModel>();
		if (StringUtils.isNotBlank(searchPageData.getCategoryCode()))
		{
			selectedCategories.add(getCategoryForCode(searchPageData.getCategoryCode()));
		}
		final List<String> categoryFacetCodes = getCategoryFacetCodes(searchResponse);

		final List<BreadcrumbData> breadcrumbs = searchPageData.getBreadcrumbs();

		for (final BreadcrumbData breadcrumb : breadcrumbs)
		{
			if (categoryFacetCodes.contains(breadcrumb.getFacetCode()))
			{
				selectedCategories.add(getCategoryForCode(breadcrumb.getFacetValueCode()));
			}
		}

		for (final CategoryModel selectedCategory : selectedCategories)
		{
			// The acceptable category pool contains a leaf category. So we have a pass.
			if (CollectionUtils.isEmpty(selectedCategory.getCategories()))
			{
				return false;
			}
		}

		return true;
	}

	private List<String> getCategoryFacetCodes(final SolrSearchResponse solrSearchResponse)
	{
		final List<String> facets = new LinkedList<String>();
		for (final IndexedProperty property : ((IndexedType) (solrSearchResponse.getRequest().getIndexedType()))
				.getIndexedProperties().values())
		{
			if (property.isFacet())
			{
				if (property.isCategoryField())
				{
					facets.add(property.getName());
				}
			}
		}
		return facets;
	}

	private CategoryModel getCategoryForCode(final String code)
	{
		return getCommerceCategoryService().getCategoryForCode(code);
	}

	/**
	 * @return the commerceCategoryService
	 */
	public CommerceCategoryService getCommerceCategoryService()
	{
		return commerceCategoryService;
	}

	/**
	 * @param commerceCategoryService
	 *           the commerceCategoryService to set
	 */
	public void setCommerceCategoryService(final CommerceCategoryService commerceCategoryService)
	{
		this.commerceCategoryService = commerceCategoryService;
	}
}
