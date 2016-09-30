package com.hybris.showcase.guidedselling.interceptors;

import de.hybris.platform.addonsupport.interceptors.BeforeControllerHandlerAdaptee;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class GuidedSellingBeforeCartControllerHandlerAdaptee implements BeforeControllerHandlerAdaptee
{

	public static final String GUIDEDSELLING_CART_REDIRECT = "/guidedselling/cart";
	public static final String CART_PATH = "/cart";

	@Override
	public boolean beforeController(final HttpServletRequest request, final HttpServletResponse response,
			final HandlerMethod handler) throws IOException
	{
		if (isCartPage(request))
		{
			final String encodedRedirectUrl = response.encodeRedirectURL(request.getContextPath() + GUIDEDSELLING_CART_REDIRECT);
			response.sendRedirect(encodedRedirectUrl);
			return false;
		}
		else
		{
			return true;
		}
	}

	private static boolean isCartPage(final HttpServletRequest request)
	{
		return request.getServletPath().startsWith(CART_PATH);
	}
}
