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
package com.hybris.social.googleplus.web.interceptors;

import static com.hybris.addon.common.interceptors.helper.JavaScriptVariableHelper.createJavaScriptVariable;
import static com.hybris.addon.common.interceptors.helper.JavaScriptVariableHelper.getVariables;

import de.hybris.platform.acceleratorservices.storefront.data.JavaScriptVariableData;
import de.hybris.platform.addonsupport.interceptors.BeforeViewHandlerAdaptee;
import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.ui.ModelMap;




/**
 * @author rmcotton
 * 
 */
public class GooglePlusBeforeViewHandler implements BeforeViewHandlerAdaptee
{
	private CommerceCommonI18NService commerceCommonI18NService;


	protected Locale getLocale()
	{
		return getCommerceCommonI18NService().getLocaleForLanguage(getCommerceCommonI18NService().getCurrentLanguage());
	}

	protected String getLang()
	{
		return StringUtils.replaceChars(getLocale().toString(), "_", "-");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.addon.common.interceptors.BeforeViewHandlerAdaptee#beforeView(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, java.lang.String)
	 */
	@Override
	public String beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelMap model,
			final String viewName) throws Exception
	{
		final List<JavaScriptVariableData> variables = getVariables(model);
		variables.add(createJavaScriptVariable("googlelocale", getLang()));

		return viewName;
	}




	/**
	 * @return the commerceCommonI18NService
	 */
	public CommerceCommonI18NService getCommerceCommonI18NService()
	{
		return commerceCommonI18NService;
	}

	/**
	 * @param commerceCommonI18NService
	 *           the commerceCommonI18NService to set
	 */
	@Required
	public void setCommerceCommonI18NService(final CommerceCommonI18NService commerceCommonI18NService)
	{
		this.commerceCommonI18NService = commerceCommonI18NService;
	}

}
