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
package com.hybris.social.twitter.component.renderer;

import de.hybris.platform.acceleratorcms.component.renderer.CMSComponentRenderer;
import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.twitter.model.AbstractTwitterComponentModel;
import com.hybris.social.urlshortening.service.UrlShorteningService;


/**
 * @author rmcotton
 * 
 */
public abstract class AbstractTwitterButtonComponentRenderer<C extends AbstractTwitterComponentModel> implements
		CMSComponentRenderer<C>
{
	private UrlShorteningService urlShorteningService;
	private ConfigurationService configurationService;
	private CommerceCommonI18NService commerceCommonI18nService;


	@Override
	public void renderComponent(final PageContext pageContext, final C component) throws ServletException, IOException
	{
		final JspWriter out = pageContext.getOut();
		out.write(buildButtonHTML(pageContext, component));
	}

	protected String buildButtonHTML(final PageContext pageContext, final C component)
	{
		final StringBuilder html = new StringBuilder();
		html.append("<a href=\"").append(getBaseHref(component)).append(getHrefParameterString(pageContext, component))
				.append("\"");
		html.append(" class=\"").append(getCssClass(component)).append("\"");
		html.append(getDataParameterString(pageContext, component));
		html.append(">");
		html.append("</a>");
		return html.toString();
	}


	protected abstract String getBaseHref(final C component);

	protected abstract String getHrefParameterString(final PageContext pageContext, final C component);

	protected abstract String getCssClass(final C component);

	protected String getDataParameterString(final PageContext pageContext, final C component)
	{
		final List<KeyValue> values = getDataParameters(pageContext, component);
		if (CollectionUtils.isEmpty(values))
		{
			return StringUtils.EMPTY;
		}

		final StringBuilder output = new StringBuilder();
		for (final ListIterator<KeyValue> li = values.listIterator(); li.hasNext();)
		{
			final KeyValue keyval = li.next();
			output.append(" ").append(keyval.getKey()).append("=").append("\"").append(keyval.getValue()).append("\"");
		}
		return output.toString();
	}

	protected abstract List<KeyValue> getDataParameters(final PageContext pageContext, final C component);


	protected void addIfNotBlank(final List<KeyValue> values, final String key, final String value)
	{
		if (StringUtils.isNotBlank(value))
		{
			values.add(new KeyValue(key, value));
		}
	}

	protected Locale getLocale()
	{
		return getCommerceCommonI18NService().getLocaleForLanguage(getCommerceCommonI18NService().getCurrentLanguage());
	}

	protected String getLang()
	{
		return getLocale().toString();
	}

	public static class KeyValue
	{
		private final String key;
		private final String value;

		public KeyValue(final String key, final String value)
		{
			this.key = key;
			this.value = value;
		}

		public String getKey()
		{
			return this.key;
		}

		public String getValue()
		{
			return this.value;
		}
	}

	protected String buildParameterString(final List<KeyValue> values)
	{
		if (CollectionUtils.isEmpty(values))
		{
			return StringUtils.EMPTY;
		}
		final StringBuilder output = new StringBuilder("?");
		for (final ListIterator<KeyValue> li = values.listIterator(); li.hasNext();)
		{
			final KeyValue keyval = li.next();
			output.append(keyval.getKey()).append("=").append(keyval.getValue());
			if (li.hasNext())
			{
				output.append("&");
			}
		}
		return output.toString();
	}

	@Required
	public void setUrlShorteningService(final UrlShorteningService urlShorteningService)
	{
		this.urlShorteningService = urlShorteningService;
	}

	protected UrlShorteningService getUrlShorteningService()
	{
		return this.urlShorteningService;
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

	/**
	 * @return the configurationService
	 */
	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	/**
	 * @param configurationService
	 *           the configurationService to set
	 */
	@Required
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}


	protected Configuration getConfiguration()
	{
		return getConfigurationService().getConfiguration();
	}
}
