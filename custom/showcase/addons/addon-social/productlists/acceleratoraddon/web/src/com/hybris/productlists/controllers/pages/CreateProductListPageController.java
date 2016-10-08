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
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hybris.productlists.constants.ProductlistsConstants;
import com.hybris.productlists.controllers.pages.create.CreateProductListForm;
import com.hybris.productlists.facades.ProductListsFacade;


/**
 * @author cwayman
 * 
 */
@Controller
public class CreateProductListPageController extends AbstractAddOnPageController
{
	private ProductListsFacade productListsFacade;
	private ProductFacade productFacade;
	private UserFacade userFacade;

	@RequestMapping(value = "/productlists/create", method = RequestMethod.GET)
	public String createProductListGet(final Model model, final HttpServletRequest request) throws CMSItemNotFoundException
	{
		final List<AddressData> addresses = userFacade.getAddressBook();

		final CreateProductListForm form = new CreateProductListForm();
		//form.setName("Enter Wishlist Name");
		form.setAddresses(addresses);
		model.addAttribute("createForm", form);


		final ContentPageModel page = getContentPageForLabelOrId(ProductlistsConstants.Views.Pages.ProductLists.create);
		storeCmsPageInModel(model, page);
		setUpMetaData(model, page.getKeywords(), page.getDescription());
		storeContentPageTitleInModel(model, page.getTitle());
		storeCmsPageInModel(model, getContentPageForLabelOrId(null));

		return "addon:/productlists/pages/createProductList";
	}

	@RequestMapping(value = "/productlists/create", method = RequestMethod.POST)
	public String createProductListPost(@ModelAttribute final CreateProductListForm form, final Model model,
			final HttpServletRequest request) throws CMSItemNotFoundException
	{
		final String guid = productListsFacade.createProductList(form.toProductListData());
		return REDIRECT_PREFIX + "/productlists/view/" + guid;
	}

	@Autowired
	public void setProductListsFacade(final ProductListsFacade productListsFacade)
	{
		this.productListsFacade = productListsFacade;
	}

	@Autowired
	public void setUserFacade(final UserFacade userFacade)
	{
		this.userFacade = userFacade;
	}

	@Autowired
	public void setProductFacade(final ProductFacade productFacade)
	{
		this.productFacade = productFacade;
	}
}
