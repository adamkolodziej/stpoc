package com.hybris.showcase.controllers.cms;

/**
 * CECS-79 ProductCarouselComponent and FixedProductSetComponent
 *
 * Created by mgolubovic on 4.2.2015..
 */

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.showcase.controllers.ShowcasecommonaddonControllerConstants;
import com.hybris.showcase.model.components.AbstractProductCollectionComponentModel;
import com.hybris.showcase.model.components.SlickProductCarouselComponentModel;


@Controller("SlickProductCarouselComponentController")
@RequestMapping(value = ShowcasecommonaddonControllerConstants.SlickProductCarouselComponentController)
public class SlickProductCarouselComponentController extends
		AbstractProductCollectionComponentController<SlickProductCarouselComponentModel>
{

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final AbstractProductCollectionComponentModel component)
	{
		final SlickProductCarouselComponentModel slickProductCarouselComponentModel = (SlickProductCarouselComponentModel) component;

		super.fillModel(request, model, component);
		model.addAttribute("actions", component.getActions());
		model.addAttribute("classificationCollection", slickProductCarouselComponentModel.getClassificationCollection());
		model.addAttribute("classificationValue", slickProductCarouselComponentModel.getClassificationValue());
	}
}
