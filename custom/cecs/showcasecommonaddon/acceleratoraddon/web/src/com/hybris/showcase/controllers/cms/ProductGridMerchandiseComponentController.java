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
import com.hybris.showcase.model.ProductGridMerchandiseComponentModel;


/**
 * @author Sebastian Weiner
 *
 */
@Controller("ProductGridMerchandiseComponentController")
@RequestMapping(value = ShowcasecommonaddonControllerConstants.ProductGridMerchandiseComponentController)
public class ProductGridMerchandiseComponentController extends
		AbstractCMSAddOnComponentController<ProductGridMerchandiseComponentModel>
{


	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController#fillModel(javax.servlet.http
	 * .HttpServletRequest, org.springframework.ui.Model,
	 * de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel)
	 */
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final ProductGridMerchandiseComponentModel component)
	{

		// empty controller - the default controller will be used.

	}

}
