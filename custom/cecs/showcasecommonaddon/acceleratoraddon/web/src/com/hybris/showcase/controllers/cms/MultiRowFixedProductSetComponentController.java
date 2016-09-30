/**
 * 
 */
package com.hybris.showcase.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.showcase.controllers.ShowcasecommonaddonControllerConstants;
import com.hybris.showcase.model.components.AbstractProductCollectionComponentModel;
import com.hybris.showcase.model.components.MultiRowFixedProductSetComponentModel;


/**
 * @author npavlovic
 * 
 *         CECS-151: Most Viewed Component
 */
@Controller("MultiRowFixedProductSetComponentController")
@RequestMapping(value = ShowcasecommonaddonControllerConstants.MultiRowFixedProductSetComponentController)
public class MultiRowFixedProductSetComponentController extends
		AbstractProductCollectionComponentController<MultiRowFixedProductSetComponentModel>
{
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final AbstractProductCollectionComponentModel component)
	{
		super.fillModel(request, model, component);
		model.addAttribute("actions", component.getActions());
	}
}
