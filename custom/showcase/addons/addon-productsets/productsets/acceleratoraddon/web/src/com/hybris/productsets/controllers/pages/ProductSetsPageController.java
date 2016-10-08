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
package com.hybris.productsets.controllers.pages;

import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.product.ProductOption;

import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hybris.productsets.facades.ProductSetFacade;
import com.hybris.productsets.facades.data.ProductSetData;
import com.hybris.productsets.services.CMSProductSetPageService;


/**
 * Controller for product set page.
 */
@Controller
@RequestMapping(value = "/sets")
public class ProductSetsPageController extends AbstractAddOnPageController
{
	private static final Logger LOG = Logger.getLogger(ProductSetsPageController.class);

	@Resource
	private ProductSetFacade productSetFacade;

	@Resource
	private CMSProductSetPageService cmsProductSetPageService;

	@RequestMapping(value = "/{productSetCode}", method = RequestMethod.GET)
	public String get(@PathVariable("productSetCode") final String productSetCode, final Model model)
			throws CMSItemNotFoundException
	{
		final ProductSetData productSet = productSetFacade.getProductSetByCode(productSetCode, Arrays.asList(ProductOption.BASIC,
				ProductOption.PRICE, ProductOption.VARIANT_FULL, ProductOption.STOCK, ProductOption.GALLERY));
		model.addAttribute("productSet", productSet);
		storeContentPageTitleInModel(model, "Product Set Details");
		storeCmsPageInModel(model, cmsProductSetPageService.getPageForProductSetCode(productSetCode));
		return getViewForPage(model);
	}
}
