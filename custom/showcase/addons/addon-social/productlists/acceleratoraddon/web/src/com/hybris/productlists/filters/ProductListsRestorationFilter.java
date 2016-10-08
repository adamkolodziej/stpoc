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
package com.hybris.productlists.filters;

import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.CookieGenerator;

import com.hybris.productlists.data.ProductListData;
import com.hybris.productlists.enums.ProductListType;
import com.hybris.productlists.facades.ProductListsFacade;


/**
 * Filter that the restores the user's Product List. This is a spring configured filter that is executed by the
 * PlatformFilterChain.
 */
public class ProductListsRestorationFilter extends OncePerRequestFilter
{
	private CookieGenerator productListRestoreCookieGenerator;
	private ProductListsFacade productListsFacade;
	private BaseSiteService baseSiteService;
	private UserService userService;
	private SessionService sessionService;

	@Override
	public void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain filterChain) throws IOException, ServletException
	{


		if (getUserService().isAnonymousUser(getUserService().getCurrentUser()))
		{
			if (getProductListsFacade().hasSessionProductLists()
					&& getBaseSiteService().getCurrentBaseSite().equals(
							getBaseSiteService().getBaseSiteForUID(
									getProductListsFacade().getSessionProductLists(ProductListType.WISHLIST, null).get(0).getSiteUid())))
			{
				final List<ProductListData> productLists = getProductListsFacade().getSessionProductLists(ProductListType.WISHLIST,
						null);

				if (productLists != null && !productLists.isEmpty())
				{
					getProductListRestoreCookieGenerator().addCookie(response, productLists.get(0).getGuid());
				}
			}
			else if (request.getSession().isNew()
					|| (getProductListsFacade().hasSessionProductLists() && !getBaseSiteService().getCurrentBaseSite().equals(
							getBaseSiteService().getBaseSiteForUID(
									getProductListsFacade().getSessionProductLists(ProductListType.WISHLIST, null).get(0).getSiteUid()))))
			{
				Cookie productListCookie = null;

				if (request.getCookies() != null)
				{
					final String anonymousCartCookieName = getProductListRestoreCookieGenerator().getCookieName();

					for (final Cookie cookie : request.getCookies())
					{
						if (anonymousCartCookieName.equals(cookie.getName()))
						{
							productListCookie = cookie;
							break;
						}
					}
				}

				if (productListCookie != null && !StringUtils.isEmpty(productListCookie.getValue()))
				{
					final ProductListData result = getProductListsFacade().restoreSavedProductList(productListCookie.getValue(), null);
					if (result == null)
					{
						getProductListRestoreCookieGenerator().removeCookie(response);
					}
				}
			}

		}
		else
		{
			final List<ProductListData> productLists = getProductListsFacade()
					.getSessionProductLists(ProductListType.WISHLIST, null);

			if (null != productLists
					&& !productLists.isEmpty()
					&& (!getProductListsFacade().hasSessionProductLists())
					|| (null != productLists && !productLists.isEmpty() && getProductListsFacade().hasSessionProductLists() && !getBaseSiteService()
							.getCurrentBaseSite().equals(getBaseSiteService().getBaseSiteForUID(productLists.get(0).getSiteUid()))))
			{
				getProductListsFacade().restoreSavedProductList(productLists.get(0).getGuid(), null);
			}
		}


		filterChain.doFilter(request, response);
	}

	protected SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}



	public ProductListsFacade getProductListsFacade()
	{
		return productListsFacade;
	}

	@Required
	public void setProductListsFacade(final ProductListsFacade productListsFacade)
	{
		this.productListsFacade = productListsFacade;
	}

	protected BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public CookieGenerator getProductListRestoreCookieGenerator()
	{
		return productListRestoreCookieGenerator;
	}

	@Required
	public void setProductListRestoreCookieGenerator(final CookieGenerator productListRestoreCookieGenerator)
	{
		this.productListRestoreCookieGenerator = productListRestoreCookieGenerator;
	}


}
