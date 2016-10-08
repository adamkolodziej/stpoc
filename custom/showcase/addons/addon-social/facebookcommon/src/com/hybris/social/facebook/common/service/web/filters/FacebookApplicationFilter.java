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
package com.hybris.social.facebook.common.service.web.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hybris.social.facebook.common.service.FacebookApplicationService;


/**
 * @author rmcotton
 * 
 */
public class FacebookApplicationFilter extends OncePerRequestFilter
{
	private FacebookApplicationService facebookApplicationService;


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain filterChain) throws ServletException, IOException
	{
		getFacebookApplicationService().initApplication(request.getServerName());
		filterChain.doFilter(request, response);
	}

	/**
	 * @return the facebookApplicationService
	 */
	public FacebookApplicationService getFacebookApplicationService()
	{
		return facebookApplicationService;
	}


	/**
	 * @param facebookApplicationService
	 *           the facebookApplicationService to set
	 */
	@Required
	public void setFacebookApplicationService(final FacebookApplicationService facebookApplicationService)
	{
		this.facebookApplicationService = facebookApplicationService;
	}

}
