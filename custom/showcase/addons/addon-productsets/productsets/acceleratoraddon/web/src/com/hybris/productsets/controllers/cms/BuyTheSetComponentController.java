package com.hybris.productsets.controllers.cms;

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


import de.hybris.platform.addonsupport.controllers.cms.GenericCMSAddOnComponentController;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.productsets.facades.ProductSetFacade;
import com.hybris.productsets.facades.data.ProductSetData;
import com.hybris.productsets.model.components.BuyTheSetComponentModel;


/**
 * Controller for CMS BuyTheSetComponent
 * 
 * @author dominik.strzyzewski
 */
@Controller("BuyTheSetComponentController")
@RequestMapping("/view/BuyTheSetComponentController")
public class BuyTheSetComponentController extends GenericCMSAddOnComponentController
{
	@Resource(name = "productSetOptionsList")
	private List<ProductOption> productSetOptionsList;

	@Autowired
	private ProductSetFacade productSetFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final AbstractCMSComponentModel component)
	{
		super.fillModel(request, model, component);

		final ProductModel currentProduct = getRequestContextData(request).getProduct();
		final BuyTheSetComponentModel buyTheSetComponent = (BuyTheSetComponentModel) component;

		if (currentProduct != null)
		{
			final List<ProductSetData> productSets = productSetFacade.getProductSetsForProductAndType(currentProduct.getCode(),
					buyTheSetComponent.getProductSetType(), productSetOptionsList);

			model.addAttribute("title", buyTheSetComponent.getTitle());
			model.addAttribute("productSets", productSets);
			model.addAttribute("multipleSets", Boolean.valueOf(productSets.size() > 1));
			model.addAttribute("clickAction", buyTheSetComponent.getClickAction());
			model.addAttribute("currentProduct", currentProduct);
		}
	}

}
