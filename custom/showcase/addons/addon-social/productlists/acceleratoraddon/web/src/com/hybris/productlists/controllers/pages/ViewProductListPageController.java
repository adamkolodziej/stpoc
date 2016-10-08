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
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;

import java.util.Arrays;
import java.util.Collections;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hybris.productlists.constants.ProductlistsConstants;
import com.hybris.productlists.data.ProductListData;
import com.hybris.productlists.facades.ProductListsFacade;
import com.hybris.productlists.forms.MoveToCartForm;


/** @author cwayman */
@Controller
public class ViewProductListPageController extends AbstractAddOnPageController
{
	private static final String TYPE_MISMATCH_ERROR_CODE = "typeMismatch";
	private static final String ERROR_MSG_TYPE = "errorMsg";
	private static final String QUANTITY_INVALID_BINDING_MESSAGE_KEY = "basket.error.quantity.invalid.binding";

	@Resource
	private ProductListsFacade productListsFacade;


	@Resource(name = "accProductFacade")
	private ProductFacade productFacade;

	@RequestMapping(value = "/productlists/view/{guid}", method = RequestMethod.GET)
	public String viewProductList(@PathVariable final String guid, final Model model, final HttpServletRequest request)
			throws CMSItemNotFoundException
	{
		final ProductListData productListData = productListsFacade.getProductList(guid,
				Arrays.asList(ProductOption.BASIC, ProductOption.PRICE, ProductOption.SUMMARY, ProductOption.STOCK));

		model.addAttribute("productListData", productListData);

		//		final List<ProductListData> productListsToMoveTo = productListsFacade.getAllSessionProductLists(null);
		//		model.addAttribute("productListsToMoveTo", productListsToMoveTo);


		final ContentPageModel contentPage = getContentPageForLabelOrId(ProductlistsConstants.Views.Pages.ProductLists.view);
		storeCmsPageInModel(model, contentPage);
		setUpMetaData(model, contentPage.getKeywords(), productListData.getName());
		storeContentPageTitleInModel(model, productListData.getName());

		return "addon:/productlists/pages/viewProductList";
	}


	@RequestMapping(value = "/productlists/move/cart/{guid}", method = RequestMethod.POST, produces = "application/json")
	public String addToCart(@PathVariable final String guid, @RequestParam("productCodePost") final String code,
			final Model model, @Valid final MoveToCartForm form, final BindingResult bindingErrors)
	{
		if (bindingErrors.hasErrors())
		{
			return getViewWithBindingErrorMessages(model, bindingErrors);
		}

		final int qty = form.getQty();

		if (qty <= 0)
		{
			model.addAttribute(ERROR_MSG_TYPE, "basket.error.quantity.invalid");
			model.addAttribute("quantity", Long.valueOf(0L));
		}
		else
		{
			try
			{
				final CartModificationData cartModification = productListsFacade.moveToCart(guid, code, Integer.valueOf(qty))
						.getCartModification();
				model.addAttribute("quantity", Long.valueOf(cartModification.getQuantityAdded()));
				model.addAttribute("entry", cartModification.getEntry());

				if (cartModification.getQuantityAdded() == 0L)
				{
					model.addAttribute(ERROR_MSG_TYPE, "basket.information.quantity.noItemsAdded." + cartModification.getStatusCode());
				}
				else if (cartModification.getQuantityAdded() < qty)
				{
					model.addAttribute(ERROR_MSG_TYPE,
							"basket.information.quantity.reducedNumberOfItemsAdded." + cartModification.getStatusCode());
				}
			}
			catch (final CommerceCartModificationException ex)
			{
				model.addAttribute(ERROR_MSG_TYPE, "basket.error.occurred");
				model.addAttribute("quantity", Long.valueOf(0L));
			}
		}

		final ProductData productForCodeAndOptions = productFacade.getProductForCodeAndOptions(code,
				Arrays.asList(ProductOption.BASIC));
		model.addAttribute("product", productForCodeAndOptions);
		// B2B addtoCart popup workaround.
		model.addAttribute("numberShowing", Integer.valueOf(3));
		model.addAttribute("products", Collections.singletonList(productForCodeAndOptions));

		return "fragments/cart/addToCartPopup";
	}

	protected String getViewWithBindingErrorMessages(final Model model, final BindingResult bindingErrors)
	{
		for (final ObjectError error : bindingErrors.getAllErrors())
		{
			if (isTypeMismatchError(error))
			{
				model.addAttribute(ERROR_MSG_TYPE, QUANTITY_INVALID_BINDING_MESSAGE_KEY);
			}
			else
			{
				model.addAttribute(ERROR_MSG_TYPE, error.getDefaultMessage());
			}
		}
		return "fragments/cart/addToCartPopup";
	}

	protected boolean isTypeMismatchError(final ObjectError error)
	{
		return error.getCode().equals(TYPE_MISMATCH_ERROR_CODE);
	}

	@ResponseBody
	@RequestMapping(value = "/productlists/reorder", method = RequestMethod.GET)
	public String reorder(@RequestParam("guid") final String guid, @RequestParam("before") final String before,
			@RequestParam("move") final String move, final Model model, final HttpServletRequest request)
	{
		productListsFacade.updateProductOrder(guid, before, move);
		return guid;
	}

	@Autowired
	public void setProductListsFacade(final ProductListsFacade productListsFacade)
	{
		this.productListsFacade = productListsFacade;
	}

	@Autowired
	public void setProductFacade(final ProductFacade productFacade)
	{
		this.productFacade = productFacade;
	}

}
