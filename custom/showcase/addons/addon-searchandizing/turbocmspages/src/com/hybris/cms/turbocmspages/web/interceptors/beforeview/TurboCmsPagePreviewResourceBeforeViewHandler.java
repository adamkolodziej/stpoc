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
package com.hybris.cms.turbocmspages.web.interceptors.beforeview;

import static com.hybris.addon.common.interceptors.helper.JavaScriptVariableHelper.createJavaScriptVariable;
import static com.hybris.addon.common.interceptors.helper.JavaScriptVariableHelper.getVariables;

import de.hybris.platform.acceleratorservices.data.RequestContextData;
import de.hybris.platform.acceleratorservices.storefront.data.JavaScriptVariableData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.FacetSearchPageData;
import de.hybris.liveeditaddon.web.interceptors.beforeview.PreviewResourceBeforeViewHandler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;


/**
 * @author rmcotton
 * 
 */
public class TurboCmsPagePreviewResourceBeforeViewHandler extends PreviewResourceBeforeViewHandler
{
	public String pathPropertyPrefix = "turbopreview";


	@Override
	public String beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelMap model,
			final String viewName) throws Exception
	{
		final String viewNameReturned = super.beforeView(request, response, model, viewName);
		if (isIncludeResource(request, response, model, viewName))
		{
			final RequestContextData requestContextData = getRequestContextData(request);
			if (requestContextData != null)
			{
				if (requestContextData.getSearch() instanceof FacetSearchPageData)
				{
					final FacetSearchPageData<SearchStateData, ProductData> searchPageData = (FacetSearchPageData<SearchStateData, ProductData>) requestContextData
							.getSearch();
					final List<JavaScriptVariableData> variables = getVariables(model);
					variables.add(createJavaScriptVariable("previewSearchStateUrl", searchPageData.getCurrentQuery().getUrl()));
					variables.add(createJavaScriptVariable("previewSearchStateQuery", searchPageData.getCurrentQuery().getQuery()
							.getValue()));
				}
			}
		}
		return viewNameReturned;
	}

	@Override
	public void setPathPropertyPrefix(final String pathPropertyPrefix)
	{
		this.pathPropertyPrefix = pathPropertyPrefix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.addon.common.interceptors.beforeview.AbstractConditionalResourceBeforeHandler#getPathPropertyPrefix()
	 */
	@Override
	public String getPathPropertyPrefix()
	{
		return pathPropertyPrefix;
	}
}
