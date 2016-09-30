/**
 * 
 */
package com.hybris.showcase.controllers.cms;

import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.showcase.controllers.ShowcasecommonaddonControllerConstants;
import com.hybris.showcase.model.SimpleNavigationBarComponentModel;


/**
 * @author npavlovic
 * 
 */
@Controller("SimpleNavigationBarComponentController")
@RequestMapping(value = ShowcasecommonaddonControllerConstants.SimpleNavigationBarComponentController)
public class SimpleNavigationBarComponentController extends
		AbstractCMSAddOnComponentController<SimpleNavigationBarComponentModel>
{
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final SimpleNavigationBarComponentModel component)
	{
		// YTODO Auto-generated method stub
	}
}
