/**
 * 
 */
package com.hybris.productlists.controllers.cms;

import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.site.BaseSiteService;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.cms.productlists.model.components.ProductListsNavigationComponentModel;
import com.hybris.productlists.data.ProductListData;
import com.hybris.productlists.facades.ProductListsFacade;


/** @author dilic */
@Controller("ProductListsNavigationComponentController")
@RequestMapping("/view/ProductListsNavigationComponentController")
public class ProductListsNavigationComponentController extends
		AbstractCMSAddOnComponentController<ProductListsNavigationComponentModel>
{

	@Autowired
	private ProductListsFacade productListsFacade;

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;

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
			final ProductListsNavigationComponentModel component)
	{
		final List<ProductListData> productLists = productListsFacade.getAllSessionProductLists(null);
		model.addAttribute("productLists", productLists);
	}


}
