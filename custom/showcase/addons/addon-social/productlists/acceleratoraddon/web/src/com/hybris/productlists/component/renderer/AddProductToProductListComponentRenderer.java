/*
 * [y] hybris Platform
 * 
 * Copyright (c) 2000-2013 hybris AG All rights reserved.
 * 
 * This software is the confidential and proprietary information of hybris ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the license agreement
 * you entered into with hybris.
 */

package com.hybris.productlists.component.renderer;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.cms2.model.contents.components.SimpleCMSComponentModel;
import de.hybris.platform.enumeration.EnumerationService;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.hybris.cms.productlists.model.AddProductToProductListComponentModel;
import com.hybris.productlists.enums.ProductListType;


/**
 * @author simonhuggins
 * 
 */



public class AddProductToProductListComponentRenderer<C extends SimpleCMSComponentModel> extends
		DefaultAddOnCMSComponentRenderer<C>
{
	@Autowired
	private EnumerationService enumerationService;


	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
	{

		final AddProductToProductListComponentModel addToProdListComponent = (AddProductToProductListComponentModel) component;
		final ProductListType productListType = addToProdListComponent.getProductListType();

		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("addToProdListButtonText", addToProdListComponent.getButtonLabel());
		params.put("listType", enumerationService.getEnumerationName(productListType));

		return params;
	}
}
