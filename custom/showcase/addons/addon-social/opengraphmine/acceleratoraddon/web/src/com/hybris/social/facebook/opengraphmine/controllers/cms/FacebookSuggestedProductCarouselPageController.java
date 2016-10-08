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
package com.hybris.social.facebook.opengraphmine.controllers.cms;

import de.hybris.platform.acceleratorcms.component.slot.CMSPageSlotComponentService;
import de.hybris.platform.acceleratorcms.data.CmsPageRequestContextData;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Stopwatch;
import com.hybris.social.facebook.opengraphmine.enums.CategoriesOperator;
import com.hybris.social.facebook.opengraphmine.facade.FacebookSuggestionFacade;
import com.hybris.social.facebook.opengraphmine.facade.data.FacebookSuggestedProductData;
import com.hybris.social.facebook.opengraphmine.model.facebook.FacebookSuggestedProductCarouselComponentModel;
import com.hybris.social.facebook.opengraphmine.service.FacebookAuthenticationService;


/**
 * @author rmcotton
 * @author kbaranski
 */
@Controller
@RequestMapping(value = "/FacebookSuggestedProductCarouselPageController")
public class FacebookSuggestedProductCarouselPageController extends AbstractAddOnPageController
{
	private static final Logger LOG = Logger.getLogger(FacebookSuggestedProductCarouselPageController.class);

	@Resource(name = "facebookSuggestionFacade")
	FacebookSuggestionFacade facebookSuggestionFacade;

	@Resource(name = "categoryService")
	CategoryService categoryService;

	@Resource(name = "modelService")
	ModelService modelService;

	@Resource(name = "facebookAuthenticationService")
	FacebookAuthenticationService facebookAuthenticationService;

	@Resource(name = "cmsPageSlotComponentService")
	CMSPageSlotComponentService cmsPageSlotComponentService;

	@Resource(name = "cmsPageRequestContextData")
	CmsPageRequestContextData cmsPageRequestContextData;

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;

	@RequestMapping(value = "/ajax/{componentId}", method = RequestMethod.GET)
	public String renderData(@PathVariable("componentId") final String componentId, final Model model)
	{
		final AbstractCMSComponentModel abstractComponent = cmsPageSlotComponentService.getComponentForId(componentId);
		if (abstractComponent instanceof FacebookSuggestedProductCarouselComponentModel)
		{
			final FacebookSuggestedProductCarouselComponentModel component = (FacebookSuggestedProductCarouselComponentModel) abstractComponent;

			if (cmsPageSlotComponentService.isComponentVisible(cmsPageRequestContextData, component, true))
			{
				model.addAttribute("component", component);
				final List<FacebookSuggestedProductData> facebookSuggestedProducts = getFacebookSuggestedProducts(component);
				if (facebookSuggestedProducts != null && CollectionUtils.isNotEmpty(facebookSuggestedProducts))
				{
					model.addAttribute("title", component.getTitle());
					model.addAttribute("facebookSuggestedProducts", facebookSuggestedProducts);
				}
			}
		}
		return "addon:/opengraphmine/cms/facebooksuggestedproductcarousel";
	}

	protected List<FacebookSuggestedProductData> getFacebookSuggestedProducts(
			final FacebookSuggestedProductCarouselComponentModel component)
	{
		final Stopwatch stopwatch = Stopwatch.createUnstarted();
		stopwatch.start();
		List<FacebookSuggestedProductData> facebookSuggestedProducts = null;
		try
		{
			final CategoriesOperator categoriesOperator = component.getCategoriesOperator() != null ? component
					.getCategoriesOperator() : CategoriesOperator.OR;
			final boolean fullCategoryPath = component.getFullCategoryPath() != null ? component.getFullCategoryPath()
					.booleanValue() : false;

			facebookSuggestedProducts = facebookSuggestionFacade.getSuggestions(component.getSocialContentPopulationRule(),
					component.getCategories(), categoriesOperator, fullCategoryPath);
		}
		finally
		{
			stopwatch.stop();
			LOG.info("Took [" + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "] millis to produce ["
					+ CollectionUtils.size(facebookSuggestedProducts) + "] suggestions for component [" + component.getName() + "]");
		}
		return facebookSuggestedProducts;
	}
}
