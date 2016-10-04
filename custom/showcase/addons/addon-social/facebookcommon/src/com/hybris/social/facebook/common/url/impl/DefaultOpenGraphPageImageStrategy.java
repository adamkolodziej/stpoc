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
package com.hybris.social.facebook.common.url.impl;

import de.hybris.platform.commercefacades.product.data.ImageData;

import javax.servlet.http.HttpServletRequest;

import com.hybris.addon.common.url.MediaAbsoluteUrlResolver;
import com.hybris.social.facebook.common.url.OpenGraphPageImageStrategy;


public class DefaultOpenGraphPageImageStrategy implements OpenGraphPageImageStrategy
{
	protected MediaAbsoluteUrlResolver mediaAbsoluteUrlResolver;

	@Override
	public String getUrl(final HttpServletRequest request, final ImageData imageData)
	{
		return mediaAbsoluteUrlResolver.resolve(request, imageData, request.isSecure());
	}

	public void setMediaAbsoluteUrlResolver(final MediaAbsoluteUrlResolver mediaAbsoluteUrlResolver)
	{
		this.mediaAbsoluteUrlResolver = mediaAbsoluteUrlResolver;
	}
}
