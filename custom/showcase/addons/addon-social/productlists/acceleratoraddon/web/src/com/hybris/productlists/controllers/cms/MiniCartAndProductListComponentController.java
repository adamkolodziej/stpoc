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
package com.hybris.productlists.controllers.cms;

import de.hybris.platform.acceleratorcms.model.components.MiniCartComponentModel;
import de.hybris.platform.addonsupport.controllers.cms.GenericCMSAddOnComponentController;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.site.BaseSiteService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.productlists.facades.ProductListsFacade;


/**
 * Controller for CMS Mini Cart with Product List Icon
 */
@Controller("MiniCartAndProductListComponentController")
@RequestMapping(value = "/view/MiniCartAndProductListComponentController")
public class MiniCartAndProductListComponentController extends GenericCMSAddOnComponentController
{
	public static final String TOTAL_ITEMS = "totalItems";
	public static final String TOTAL_NO_DELIVERY = "totalNoDelivery";
	public static final String IS_VISIBLE = "isVisible";
	public static final String TOTAL_PRICE = "totalPrice";
	public static final String TOTAL_DISPLAY = "totalDisplay";
	public static final String SUB_TOTAL = "subTotal";
	public static final String HAS_PRODUCT_LISTS = "hasProductLists";


	@Resource(name = "productListsFacade")
	private ProductListsFacade productListsFacade;

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final AbstractCMSComponentModel component)
	{
		final CartData cartData = cartFacade.getMiniCart();
		model.addAttribute(SUB_TOTAL, cartData.getSubTotal());
		if (cartData.getDeliveryCost() != null)
		{
			final PriceData withoutDelivery = cartData.getDeliveryCost();
			withoutDelivery.setValue(cartData.getTotalPrice().getValue().subtract(cartData.getDeliveryCost().getValue()));
			model.addAttribute(TOTAL_NO_DELIVERY, withoutDelivery);
		}
		else
		{
			model.addAttribute(TOTAL_NO_DELIVERY, cartData.getTotalPrice());
		}
		model.addAttribute(TOTAL_PRICE, cartData.getTotalPrice());
		model.addAttribute(TOTAL_DISPLAY, ((MiniCartComponentModel) component).getTotalDisplay());
		model.addAttribute(TOTAL_ITEMS, cartData.getTotalUnitCount());
		model.addAttribute(HAS_PRODUCT_LISTS, Boolean.valueOf(productListsFacade.hasSessionProductLists()));
	}



}
