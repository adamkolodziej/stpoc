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
package com.hybris.vivocha.components.renderer;

import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.user.data.CustomerData;

import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;

import com.hybris.addon.common.renderer.GenericAddOnCMSComponentRenderer;
import com.hybris.vivocha.model.VivochaComponentModel;


public class VivochaComponentRenderer extends GenericAddOnCMSComponentRenderer<VivochaComponentModel>
{

	private static final Logger LOG = Logger.getLogger(VivochaComponentRenderer.class); //NOPMD

	private static final String USER_ID = "userid";
	private static final String USER_NAME_ID = "userName";
	private static final String CART_ID = "cartid";

	private CartFacade cartFacade;
	private CustomerFacade customerFacade;

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final VivochaComponentModel component)
	{
		final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);

		final CartData sessionCart = getCartFacade().getSessionCart();
		String cartid = "";
		if (sessionCart != null)
		{
			cartid = sessionCart.getCode();
		}
		variables.put(CART_ID, cartid);

		final CustomerData currentCustomer = getCustomerFacade().getCurrentCustomer();
		variables.put(USER_ID, currentCustomer.getUid());
		variables.put(USER_NAME_ID, currentCustomer.getName());

		return variables;
	}

	public CartFacade getCartFacade()
	{
		return cartFacade;
	}

	public void setCartFacade(final CartFacade cartFacade)
	{
		this.cartFacade = cartFacade;
	}

	public CustomerFacade getCustomerFacade()
	{
		return customerFacade;
	}

	public void setCustomerFacade(final CustomerFacade customerFacade)
	{
		this.customerFacade = customerFacade;
	}

}
