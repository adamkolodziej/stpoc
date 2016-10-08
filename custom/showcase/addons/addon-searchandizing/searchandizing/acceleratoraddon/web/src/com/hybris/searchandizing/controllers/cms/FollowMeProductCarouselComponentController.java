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

import de.hybris.platform.acceleratorservices.uiexperience.UiExperienceService;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cms2lib.model.components.ProductCarouselComponentModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.site.BaseSiteService;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.fest.util.Collections;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.searchandizing.cms.model.FollowMeProductCarouselComponentModel;
import com.hybris.searchandizing.constants.SearchandizingWebConstants;
import com.hybris.searchandizing.controllers.SearchandizingControllerConstants;


/**
 * Show results in carousel according to users nav state
 * 
 * @author rmcotton
 */
@Controller("FollowMeProductCarouselComponentController")
@RequestMapping("/view/FollowMeProductCarouselComponentController")
public class FollowMeProductCarouselComponentController extends
		AbstractFollowMeComponentController<FollowMeProductCarouselComponentModel>
{

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;

	@Resource(name = "uiExperienceService")
	private UiExperienceService uiExperienceService;



	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.hybris.prototype.storefront.controllers.cms.AbstractCMSComponentController#fillModel(javax.servlet.http.
	 * HttpServletRequest, org.springframework.ui.Model,
	 * de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel)
	 */
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final AbstractCMSComponentModel comp)

	{
		final SearchStateData sqd = getSearchStateData(request);

		final String currentCategoryCode = getCurrentCategory(request);
		final FollowMeProductCarouselComponentModel component = (FollowMeProductCarouselComponentModel) comp;

		final List<ProductData> results = followMeFacade.getProducts(sqd, currentCategoryCode, component.getSort(),
				component.getStickyCategory(), component.getMaxNumberOfResults().intValue());

		if (!Collections.isEmpty(results))
		{
			model.addAttribute("title", component.getTitle());
			model.addAttribute("productData", results);
		}
	}

	@Override
	protected String getCmsComponentFolder()
	{
		return super.getCmsComponentFolder() + "/"
				+ StringUtils.lowerCase(this.baseSiteService.getCurrentBaseSite().getChannel().getCode());
	}

	@Override
	protected String getView(final AbstractCMSComponentModel component)
	{
		final String uiExp = StringUtils.lowerCase(uiExperienceService.getUiExperienceLevel().name());
		if (SearchandizingWebConstants.CHANNEL_MOBILE.equals(uiExp))
		{
			return super.getView(component);
		}
		return SearchandizingControllerConstants.Views.Cms.ComponentPrefix
				+ StringUtils.lowerCase(ProductCarouselComponentModel._TYPECODE);
	}

}
