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
package com.hybris.social.urlshortening.service.provider.impl;

import de.hybris.platform.servicelayer.config.ConfigurationService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.urlshortening.service.provider.UrlShorteningProviderConfigurationException;
import com.hybris.social.urlshortening.service.provider.UrlShorteningProviderException;
import com.hybris.social.urlshortening.service.provider.UrlShorterningProviderService;
import com.rosaloves.bitlyj.Bitly;
import com.rosaloves.bitlyj.Url;


/**
 * @author rmcotton
 * 
 */
public class BitlyUrlShorteningProviderService implements UrlShorterningProviderService
{

	private ConfigurationService configurationService;
	private String apiKey;
	private String username;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.urlshortening.service.provider.UrlShorterningProvider#shortern(java.lang.String)
	 */
	@Override
	public String shortern(final String url)
	{
		final String apiKey = getApiKey();
		final String username = getUsername();

		if (StringUtils.isEmpty(apiKey))
		{
			throw new UrlShorteningProviderConfigurationException("bitly api key is not set");
		}

		if (StringUtils.isEmpty(username))
		{
			throw new UrlShorteningProviderConfigurationException("bitly username is not set");
		}

		try
		{
			final Url shortenedUrl = Bitly.as(username, apiKey).call(Bitly.shorten(url));
			return shortenedUrl.getShortUrl();
		}
		catch (final com.rosaloves.bitlyj.BitlyException e)
		{
			throw new UrlShorteningProviderException("failed to shorten url [ " + url + "]", e);
		}
	}

	public ConfigurationService getConfigurationService()
	{
		return this.configurationService;
	}

	@Required
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}

	/**
	 * @param apiKey
	 *           the apiKey to set
	 */
	public void setApiKey(final String apiKey)
	{
		this.apiKey = apiKey;
	}

	/**
	 * @return the apiKey
	 */
	protected String getApiKey()
	{
		if (StringUtils.isBlank(this.apiKey))
		{
			return getConfigurationService().getConfiguration().getString("bitly.apiKey", null);
		}
		else
		{
			return this.apiKey;
		}
	}

	/**
	 * @param username
	 *           the username to set
	 */
	public void setUsername(final String username)
	{
		this.username = username;
	}

	/**
	 * @return the username
	 */
	protected String getUsername()
	{
		if (StringUtils.isBlank(this.username))
		{
			return getConfigurationService().getConfiguration().getString("bitly.username", null);
		}
		else
		{
			return this.username;
		}
	}

}
