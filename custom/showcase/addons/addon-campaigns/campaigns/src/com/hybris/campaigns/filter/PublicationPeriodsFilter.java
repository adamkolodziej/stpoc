/**
 * 
 */
package com.hybris.campaigns.filter;

import de.hybris.platform.cms2.misc.CMSFilter;
import de.hybris.platform.servicelayer.session.SessionService;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hybris.campaigns.addon.site.PublicationPeriodContextInformationLoaderStrategy;


/**
 * @author miroslaw.szot
 * 
 */
public class PublicationPeriodsFilter extends OncePerRequestFilter
{
	private SessionService sessionService;

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain filterChain) throws ServletException, IOException
	{
		final String queryString = request.getQueryString();

		if (StringUtils.contains(queryString, CMSFilter.CLEAR_CMSSITE_PARAM))
		{
			sessionService.removeAttribute(PublicationPeriodContextInformationLoaderStrategy.SESSION_PUBLICATION_PERIODS);
		}
		filterChain.doFilter(request, response);
	}

	public SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}
}
