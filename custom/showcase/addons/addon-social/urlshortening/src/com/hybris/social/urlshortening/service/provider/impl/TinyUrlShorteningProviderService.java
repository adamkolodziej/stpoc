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

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

import com.hybris.social.urlshortening.service.provider.UrlShorteningProviderException;
import com.hybris.social.urlshortening.service.provider.UrlShorterningProviderService;


/**
 * @author rmcotton
 * 
 */
public class TinyUrlShorteningProviderService implements UrlShorterningProviderService
{
	private static final Logger LOG = Logger.getLogger(TinyUrlShorteningProviderService.class);

	private String baseUrl = "http://tinyurl.com/api-create.php";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.social.urlshortening.service.provider.UrlShorterningProviderService#shortern(java.lang.String)
	 */
	@Override
	public String shortern(final String url)
	{
		final HttpClient httpclient = new HttpClient();

		// Prepare a request object
		final HttpMethod method = new GetMethod(getBaseUrl());
		method.setQueryString(new NameValuePair[]
		{ new NameValuePair("url", url) });
		try
		{
			httpclient.executeMethod(method);
			return method.getResponseBodyAsString();
		}
		catch (final HttpException e)
		{
			LOG.error("HTTP error shortening url", e);
			throw new UrlShorteningProviderException("HTTP error shortening url using TinyURL", e);
		}
		catch (final IOException e)
		{
			LOG.error("HTTP error shortening url", e);
			throw new UrlShorteningProviderException("IOException shortening url using TinyURL", e);
		}
		finally
		{
			method.releaseConnection();
		}
	}

	public void setBaseUrl(final String baseUrl)
	{
		this.baseUrl = baseUrl;
	}

	public String getBaseUrl()
	{
		return this.baseUrl;
	}

}
