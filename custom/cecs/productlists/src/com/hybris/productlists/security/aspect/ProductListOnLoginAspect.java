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
package com.hybris.productlists.security.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.productlists.facades.ProductListsFacade;


/**
 * @author rmcotton
 * 
 */
public class ProductListOnLoginAspect
{
	private ProductListsFacade productListsFacade;

	public Object onLogin(final ProceedingJoinPoint joinPoint) throws Throwable
	{
		final Object result = joinPoint.proceed();
		getProductListsFacade().onLogin();
		return result;
	}

	public ProductListsFacade getProductListsFacade()
	{
		return productListsFacade;
	}

	@Required
	public void setProductListsFacade(final ProductListsFacade productListsFacade)
	{
		this.productListsFacade = productListsFacade;
	}
}
