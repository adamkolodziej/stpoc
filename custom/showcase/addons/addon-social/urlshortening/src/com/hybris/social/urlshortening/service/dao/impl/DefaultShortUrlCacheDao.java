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
package com.hybris.social.urlshortening.service.dao.impl;

import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collections;

import org.apache.log4j.Logger;

import com.hybris.social.model.urlshorterning.ShortUrlCacheModel;
import com.hybris.social.urlshortening.enums.UrlShorteningProvider;
import com.hybris.social.urlshortening.service.dao.ShortUrlCacheDao;


/**
 * @author rmcotton
 * 
 */
public class DefaultShortUrlCacheDao extends AbstractItemDao implements ShortUrlCacheDao
{
	private static final Logger LOG = Logger.getLogger(DefaultShortUrlCacheDao.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.urlshortening.service.dao.ShortUrlCacheDao#cache(java.lang.String,
	 * java.lang.String, de.hybris.platform.social.enums.UrlShorteningProvider)
	 */
	@Override
	public void cache(final String longUrl, final String shortUrl, final UrlShorteningProvider provider)
	{
		final ShortUrlCacheModel cache = getModelService().create(ShortUrlCacheModel.class);
		cache.setShortenedUrl(shortUrl);
		cache.setOriginalUrl(longUrl);
		cache.setProvider(provider);

		try
		{
			getModelService().save(cache);
		}
		catch (final ModelSavingException mse)
		{
			// possible a concurrency issue since we do no pre-checking to see if url is actually cached or not already
			LOG.warn("failed to cache url [" + longUrl + "] [" + shortUrl + "] provider [" + provider + "]", mse);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.urlshortening.service.dao.ShortUrlCacheDao#getShortUrlFromCache(java.lang.String,
	 * de.hybris.platform.social.enums.UrlShorteningProvider)
	 */
	@Override
	public String getShortUrlFromCache(final String longUrl, final UrlShorteningProvider provider)
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery(
				"select {shortenedUrl} from {ShortUrlCache} where {originalUrl} = ?originalUrl and {provider} = ?provider");
		query.setResultClassList(Collections.singletonList(String.class));
		query.addQueryParameter("originalUrl", longUrl);
		query.addQueryParameter("provider", provider);

		final SearchResult<String> result = getFlexibleSearchService().search(query);
		if (result.getCount() > 0)
		{
			return result.getResult().get(0);
		}
		return null;

	}
}
