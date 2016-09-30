package com.hybris.showcase.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.showcase.controllers.ShowcasecommonaddonControllerConstants;
import com.hybris.showcase.model.components.AbstractProductCollectionComponentModel;
import com.hybris.showcase.model.components.FixedProductSetComponentModel;


/**
 * CECS-79 ProductCarouselComponent and FixedProductSetComponent
 * 
 * Created by mgolubovic on 4.2.2015..
 */
@Controller("FixedProductSetComponentController")
@RequestMapping(value = ShowcasecommonaddonControllerConstants.FixedProductSetComponentController)
public class FixedProductSetComponentController extends
		AbstractProductCollectionComponentController<FixedProductSetComponentModel>
{
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final AbstractProductCollectionComponentModel component)
	{
		super.fillModel(request, model, component);
		model.addAttribute("actions", component.getActions());
	}
}
