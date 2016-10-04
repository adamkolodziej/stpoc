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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hybris.productlists.constants.ProductlistsConstants;
import com.hybris.productlists.data.ProductListData;
import com.hybris.productlists.facades.ProductListsFacade;


/** @author cwayman */
@Controller
public class ListProductListsPageController extends AbstractAddOnPageController
{
	private ProductListsFacade productListsFacade;


	@RequestMapping(value = "/productlists/list", method = RequestMethod.GET)
	public String listProductLists(final Model model, final HttpServletRequest request) throws CMSItemNotFoundException
	{
		final List<ProductListData> productLists = productListsFacade.getAllSessionProductLists(null);
		model.addAttribute("productLists", productLists);

		final ContentPageModel page = getContentPageForLabelOrId(ProductlistsConstants.Views.Pages.ProductLists.list);
		storeCmsPageInModel(model, page);
		setUpMetaData(model, page.getKeywords(), page.getDescription());
		storeContentPageTitleInModel(model, page.getTitle());


		if (productLists != null && productLists.size() == 1)
		{
			// redirect if we have just one product list
			return "redirect:/productlists/view/" + productLists.get(0).getGuid();
		}

		return "addon:/productlists/pages/listProductLists";
	}

	@Autowired
	public void setProductListsFacade(final ProductListsFacade productListsFacade)
	{
		this.productListsFacade = productListsFacade;
	}

}
