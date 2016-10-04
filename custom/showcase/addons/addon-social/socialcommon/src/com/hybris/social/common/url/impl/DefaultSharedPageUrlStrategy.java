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
package com.hybris.social.common.url.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.util.UrlPathHelper;

import com.hybris.social.common.url.SharedPageUrlStrategy;


/**
 * Simply returns the page url the user entered in the browser. Nothing Stripped, nothing removed.
 * 
 * @author rmcotton
 */
public class DefaultSharedPageUrlStrategy implements SharedPageUrlStrategy
{
	private UrlPathHelper urlPathHelper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.social.metadata.url.SocialPageUrlStrategy#determineUrl(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String getUrl(final HttpServletRequest request, final HttpServletResponse response)
	{
		final String queryString = urlPathHelper.getOriginatingQueryString(request);
		final String currentRequestURI = urlPathHelper.getOriginatingRequestUri(request);
		final String url = UrlUtils.buildFullRequestUrl(request.getScheme(), request.getServerName(), request.getServerPort(),
				currentRequestURI, queryString);
		return response.encodeURL(url);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.social.common.url.SharedPageUrlStrategy#getUrl(javax.servlet.jsp.PageContext)
	 */
	@Override
	public String getUrl(final PageContext pageContext)
	{
		return getUrl((HttpServletRequest) pageContext.getRequest(), (HttpServletResponse) pageContext.getResponse());
	}

	/**
	 * @return the urlPathHelper
	 */
	public UrlPathHelper getUrlPathHelper()
	{
		return urlPathHelper;
	}

	/**
	 * @param urlPathHelper
	 *           the urlPathHelper to set
	 */
	@Required
	public void setUrlPathHelper(final UrlPathHelper urlPathHelper)
	{
		this.urlPathHelper = urlPathHelper;
	}
}