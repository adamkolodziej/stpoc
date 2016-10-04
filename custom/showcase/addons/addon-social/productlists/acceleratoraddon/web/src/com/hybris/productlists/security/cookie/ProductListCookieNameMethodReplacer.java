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
package com.hybris.productlists.security.cookie;

import de.hybris.platform.site.BaseSiteService;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.support.MethodReplacer;

import com.hybris.productlists.enums.ProductListType;


/**
 * @author rmcotton
 * 
 */
public class ProductListCookieNameMethodReplacer implements MethodReplacer
{

	private BaseSiteService baseSiteService;
	private ProductListType listType;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.support.MethodReplacer#reimplement(java.lang.Object,
	 * java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object reimplement(final Object arg0, final Method arg1, final Object[] arg2) throws Throwable
	{
		return StringUtils.deleteWhitespace(getBaseSiteService().getCurrentBaseSite().getUid()) + "-" + getListType().name();
	}

	protected BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	public ProductListType getListType()
	{
		return listType;
	}

	@Required
	public void setListType(final ProductListType listType)
	{
		this.listType = listType;
	}

}
