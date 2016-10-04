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
package com.hybris.social.lockerzshare.component.renderer;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;

import com.hybris.social.lockerzshare.enums.LockerzShareServiceEnum;
import com.hybris.social.lockerzshare.model.LockerzShareComponentModel;


/**
 * Created with IntelliJ IDEA. User: Zdary Date: 12/03/13 Time: 16:26 To change this template use File | Settings | File
 * Templates.
 */
public class LockerzShareComponentRenderer<C extends LockerzShareComponentModel> extends DefaultAddOnCMSComponentRenderer<C>
{
	private MessageSource messageSource;
	private CommerceCommonI18NService commerceCommonI18nService;

	private C component;

	private final String MESSAGE_LINK_TEXT_PATH = "lockerzShare.linkText";

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
	{
		this.component = component;
		final Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("config", setupConfig());
		variables.put("style", component.getLockerzShareLayoutStyle().toString());
		variables.put("linkText", getLinkText());
		variables.put("buttons", setupButtons());
		return variables;
	}

	private Map<String, String> setupConfig()
	{
		final Map<String, String> configMap = new HashMap<String, String>();
		configMap.put("locale", getCommerceCommonI18NService().getCurrentLocale().toString().replace("_", "-"));
		return configMap;
	}

	private String getLinkText()
	{
		if (StringUtils.isNotBlank(component.getLockerzShareLinkText()))
		{
			return component.getLockerzShareLinkText();
		}
		return getMessageSource().getMessage(MESSAGE_LINK_TEXT_PATH, null, "Share", getLocale());
	}

	private List<String> setupButtons()
	{
		final List<LockerzShareServiceEnum> lockerzShareServiceList = component.getLockerzShareServiceList();
		final List<String> buttonList = new ArrayList<String>();
		for (final LockerzShareServiceEnum shareService : lockerzShareServiceList)
		{
			buttonList.add(shareService.getCode());
		}
		return buttonList;
	}

	protected Locale getLocale()
	{
		return getCommerceCommonI18NService().getLocaleForLanguage(getCommerceCommonI18NService().getCurrentLanguage());
	}

	/**
	 * @return the messageSource
	 */
	public MessageSource getMessageSource()
	{
		return messageSource;
	}

	/**
	 * @param messageSource
	 *           the messageSource to set
	 */
	@Required
	public void setMessageSource(final MessageSource messageSource)
	{
		this.messageSource = messageSource;
	}

	/**
	 * @return the commerceCommonI18nService
	 */
	public CommerceCommonI18NService getCommerceCommonI18NService()
	{
		return commerceCommonI18nService;
	}

	/**
	 * @param commerceCommonI18nService
	 *           the commerceCommonI18nService to set
	 */
	@Required
	public void setCommerceCommonI18NService(final CommerceCommonI18NService commerceCommonI18nService)
	{
		this.commerceCommonI18nService = commerceCommonI18nService;
	}
}
