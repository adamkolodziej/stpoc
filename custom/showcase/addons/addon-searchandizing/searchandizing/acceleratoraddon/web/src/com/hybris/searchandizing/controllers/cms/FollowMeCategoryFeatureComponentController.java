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

import de.hybris.platform.acceleratorcms.model.components.CategoryFeatureComponentModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.searchandizing.cms.model.FollowMeCategoryFeatureComponentModel;
import com.hybris.searchandizing.controllers.SearchandizingControllerConstants;


/**
 * @author rmcotton
 * 
 */
@Controller("FollowMeCategoryFeatureComponentController")
@RequestMapping("/view/FollowMeCategoryFeatureComponentController")
public class FollowMeCategoryFeatureComponentController extends
		AbstractFollowMeComponentController<FollowMeCategoryFeatureComponentModel>
{
	@Resource(name = "categoryUrlConverter")
	private Converter<CategoryModel, CategoryData> categoryUrlConverter;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final AbstractCMSComponentModel comp)

	{
		final FollowMeCategoryFeatureComponentModel component = (FollowMeCategoryFeatureComponentModel) comp;
		if (component.getCategory() != null)
		{

			final String url;
			final SearchStateData searchStateData = getSearchStateData(request);
			if (searchStateData != null)
			{
				final String currcategory = getCurrentCategory(request);
				if (StringUtils.isNotBlank(currcategory))
				{
					url = followMeFacade.getUrlForCategoryPageCategoryRefinement(searchStateData, currcategory,
							component.getCategory());
				}
				else
				{
					url = followMeFacade.getUrlForCategoryRefinement(searchStateData, component.getCategory());
				}

			}
			else
			{
				url = categoryUrlConverter.convert(component.getCategory()).getUrl();
			}

			model.addAttribute("url", url);
		}
	}

	@Override
	protected String getView(final AbstractCMSComponentModel component)
	{
		return SearchandizingControllerConstants.Views.Cms.ComponentPrefix
				+ StringUtils.lowerCase(CategoryFeatureComponentModel._TYPECODE);
	}
}
