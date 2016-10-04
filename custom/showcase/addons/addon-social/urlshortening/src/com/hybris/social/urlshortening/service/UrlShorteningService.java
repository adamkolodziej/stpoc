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
package com.hybris.social.urlshortening.service;

import com.hybris.social.urlshortening.enums.UrlShorteningProvider;
import com.hybris.social.urlshortening.service.provider.UrlShorteningProviderException;


/**
 * @author rmcotton
 * 
 */
public interface UrlShorteningService
{
	String shorten(final String url, UrlShorteningProvider provider) throws UrlShorteningProviderException;

	String shorten(final String url) throws UrlShorteningProviderException;
}
