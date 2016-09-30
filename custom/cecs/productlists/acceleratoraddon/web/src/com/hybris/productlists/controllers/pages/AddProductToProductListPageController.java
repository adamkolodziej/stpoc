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
package com.hybris.productlists.controllers.pages;

import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hybris.productlists.data.ProductListData;
import com.hybris.productlists.enums.ProductListType;
import com.hybris.productlists.facades.ProductListsFacade;


/** @author cwayman */
@Controller
public class AddProductToProductListPageController extends AbstractAddOnPageController
{

	private ProductListsFacade productListsFacade;

	@RequestMapping(value = "/productlists/prodlist/additem", method = RequestMethod.POST)
	public @ResponseBody
	String addToProductList(@RequestParam final String productCodePost, @RequestParam final Integer quantity, final Model model,
			final HttpServletRequest request) throws CMSItemNotFoundException
	{
		final ProductListData productListData = productListsFacade.getOrCreateSessionProductLists(ProductListType.WISHLIST, null)
				.get(0);
		productListsFacade.addToProductList(productListData.getGuid(), productCodePost, quantity);
		return productListData.getGuid();
	}

	@Autowired
	public void setProductListsFacade(final ProductListsFacade productListsFacade)
	{
		this.productListsFacade = productListsFacade;
	}

}
