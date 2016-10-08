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
package com.hybris.searchandizing.controllers.cms;

import de.hybris.platform.addonsupport.controllers.cms.GenericCMSAddOnComponentController;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.FacetSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hybris.searchandizing.facades.FollowMeSearchandizingFacade;


/**
 * @author rmcotton
 * 
 */
public abstract class AbstractFollowMeComponentController<T extends AbstractCMSComponentModel> extends
		GenericCMSAddOnComponentController
{

	@Resource(name = "followMeSearchandizingFacade")
	protected FollowMeSearchandizingFacade followMeFacade;

	protected String getCurrentCategory(final HttpServletRequest request)
	{
		final CategoryModel currentCategory = getRequestContextData(request).getCategory();
		if (currentCategory != null)
		{
			return currentCategory.getCode();
		}
		return null;
	}

	protected SearchStateData getSearchStateData(final HttpServletRequest request)
	{
		final SearchPageData searchPageData = getRequestContextData(request).getSearch();
		if (searchPageData instanceof FacetSearchPageData)
		{
			return ((FacetSearchPageData<SearchStateData, ProductData>) searchPageData).getCurrentQuery();
		}
		return null;
	}


}
