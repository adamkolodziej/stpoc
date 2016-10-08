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
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hybris.productlists.controllers.pages.edit.EditProductListForm;
import com.hybris.productlists.facades.ProductListsFacade;
import com.hybris.productlists.forms.UpdateQuantityForm;


/**
 * @author cwayman
 * 
 */
@Controller
public class EditProductListPageController extends AbstractAddOnPageController
{
	private ProductListsFacade productListsFacade;


	@RequestMapping(value = "/productlists/edit/{guid}/entry/{productCode}/update", method = RequestMethod.POST)
	public String updateQuantity(@PathVariable final String guid, @PathVariable final String productCode,
			@Valid final UpdateQuantityForm updateQtyForm, final Model model, final HttpServletRequest request,
			final BindingResult bindingErrors)
	{
		if (!bindingErrors.hasErrors())
		{

			productListsFacade.updateProductList(guid, productCode, Integer.valueOf(updateQtyForm.getQuantity().intValue()));

		}
		return "redirect:/productlists/view/" + guid;
	}

	@ResponseBody
	@RequestMapping(value = "/productlists/edit/{guid}/entry/{productCode}/notes", method = RequestMethod.POST, produces = "application/json")
	public NotesUpdateResult editProductListEntryNotes(@PathVariable final String guid, @PathVariable final String productCode,
			@RequestParam("notes") final String notes, final Model model, final HttpServletRequest request)
	{
		return new NotesUpdateResult(productListsFacade.updateNotes(guid, productCode, notes), guid, productCode);
	}

	public static class NotesUpdateResult
	{
		boolean success = false;
		String listGuid;
		String productCode;

		public NotesUpdateResult(final boolean success, final String listGuid, final String productCode)
		{
			this.success = success;
			this.listGuid = listGuid;
			this.productCode = productCode;
		}


		public boolean isSuccess()
		{
			return this.success;
		}

		public String getListGuid()
		{
			return this.listGuid;
		}

		public String getProductCode()
		{
			return this.productCode;
		}
	}

	@RequestMapping(value = "/productlists/edit/{guid}", method = RequestMethod.POST)
	public String editProductListPost(@ModelAttribute final EditProductListForm editProductListForm, final Model model,
			final HttpServletRequest request) throws CMSItemNotFoundException
	{
		productListsFacade.updateProductListName(editProductListForm.getGuid(), editProductListForm.getName());
		return REDIRECT_PREFIX + "productlists/view/" + editProductListForm.getGuid();
	}

	@Autowired
	public void setProductListsFacade(final ProductListsFacade productListsFacade)
	{
		this.productListsFacade = productListsFacade;
	}

}
