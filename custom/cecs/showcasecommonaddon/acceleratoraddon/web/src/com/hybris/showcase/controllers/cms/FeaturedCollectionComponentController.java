/**
 *
 */
package com.hybris.showcase.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.showcase.cecs.sptelstore.model.components.FeaturedCollectionComponentModel;
import com.hybris.showcase.controllers.ShowcasecommonaddonControllerConstants;
import com.hybris.showcase.model.components.AbstractProductCollectionComponentModel;


/**
 * @author lmachnik
 *
 */
@Controller("FeaturedCollectionComponentController")
@RequestMapping(value = ShowcasecommonaddonControllerConstants.FeaturedCollectionComponentController)
public class FeaturedCollectionComponentController
		extends AbstractProductCollectionComponentController<FeaturedCollectionComponentModel>
{
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final AbstractProductCollectionComponentModel component)
	{
		super.fillModel(request, model, component);

		final FeaturedCollectionComponentModel featuredComponent = (FeaturedCollectionComponentModel) component;
		model.addAttribute("backgroundColor", featuredComponent.getBackgroundColor());
		model.addAttribute("bannerUrl", featuredComponent.getBannerUrl());
		model.addAttribute("actions", component.getActions());
	}
}
