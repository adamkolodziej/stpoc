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
package com.hybris.social.urlshortening.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.Preconditions;
import com.hybris.social.urlshortening.enums.UrlShorteningProvider;
import com.hybris.social.urlshortening.service.UrlShorteningService;
import com.hybris.social.urlshortening.service.dao.ShortUrlCacheDao;
import com.hybris.social.urlshortening.service.provider.UrlShorteningProviderException;
import com.hybris.social.urlshortening.service.provider.UrlShorterningProviderService;


/**
 * @author rmcotton
 * 
 */
public class DefaultUrlShorteningService implements UrlShorteningService, InitializingBean
{
	private static final Logger LOG = Logger.getLogger(DefaultUrlShorteningService.class);
	private Map<UrlShorteningProvider, UrlShorterningProviderService> urlShorteningProviderRegistry;
	private ShortUrlCacheDao shortUrlCacheDao;
	private List<String> ignoreUrlRegexPatterns = new ArrayList<String>(Arrays.asList(new String[]
	{ "https?://localhost.*$" }));
	private final List<Pattern> ignoreUrlRegexPatternCache = new ArrayList<Pattern>();
	private UrlShorteningProvider defaultUrlShorteningProvider = UrlShorteningProvider.BITLY;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.urlshortening.service.UrlShorteningService#shortern(java.lang.String,
	 * de.hybris.platform.social.enums.UrlShorteningProvider)
	 */
	@Override
	public String shorten(final String url, final UrlShorteningProvider provider)
	{
		if (ignore(url))
		{
			return url;
		}
		final String shortUrl = getShortUrlCacheDao().getShortUrlFromCache(url, provider);
		if (StringUtils.isNotBlank(shortUrl))
		{
			return shortUrl;
		}
		Preconditions.checkArgument(getUrlShorteningProviderRegistry().containsKey(provider),
				"no provider service registered for [" + provider + "]");
		try
		{
			final String newShortUrl = getUrlShorteningProviderRegistry().get(provider).shortern(url);
			getShortUrlCacheDao().cache(url, newShortUrl, provider);
			return newShortUrl;
		}
		catch (final UrlShorteningProviderException e)
		{
			LOG.warn("failed to shortern url [" + url + "] using original", e);
			return url;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.urlshortening.service.UrlShorteningService#shortern(java.lang.String)
	 */
	@Override
	public String shorten(final String url) throws UrlShorteningProviderException
	{
		return shorten(url, getDefaultUrlShorteningProvider());
	}

	protected boolean ignore(final String url)
	{
		for (final Pattern pattern : ignoreUrlRegexPatternCache)
		{
			if (pattern.matcher(url).matches())
			{
				return true;
			}
		}
		return false;
	}

	@Required
	public void setUrlShorteningProviderRegistry(
			final Map<UrlShorteningProvider, UrlShorterningProviderService> urlShorteningProviderRegistry)
	{
		this.urlShorteningProviderRegistry = urlShorteningProviderRegistry;
	}

	/**
	 * @return the urlShorteningProviderMap
	 */
	public Map<UrlShorteningProvider, UrlShorterningProviderService> getUrlShorteningProviderRegistry()
	{
		return urlShorteningProviderRegistry;
	}

	/**
	 * @param shortUrlCacheDao
	 *           the shortUrlCacheDao to set
	 */
	@Required
	public void setShortUrlCacheDao(final ShortUrlCacheDao shortUrlCacheDao)
	{
		this.shortUrlCacheDao = shortUrlCacheDao;
	}

	/**
	 * @return the shortUrlCacheDao
	 */
	public ShortUrlCacheDao getShortUrlCacheDao()
	{
		return shortUrlCacheDao;
	}

	/**
	 * @param regexFilterPatterns
	 *           the regexFilterPatterns to set
	 */
	public void setIgnoreUrlRegexPatterns(final List<String> regexFilterPatterns)
	{
		this.ignoreUrlRegexPatterns = regexFilterPatterns;
	}

	/**
	 * @return the regexFilterPatterns
	 */
	public List<String> getRegexFilterPatterns()
	{
		return this.ignoreUrlRegexPatterns;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception
	{
		for (final String pattern : getRegexFilterPatterns())
		{
			this.ignoreUrlRegexPatternCache.add(Pattern.compile(pattern));
		}

	}



	public void setDefaultUrlShorteningProvider(final UrlShorteningProvider urlShorteningProvider)
	{
		this.defaultUrlShorteningProvider = urlShorteningProvider;
	}

	public UrlShorteningProvider getDefaultUrlShorteningProvider()
	{
		return this.defaultUrlShorteningProvider;
	}

}
