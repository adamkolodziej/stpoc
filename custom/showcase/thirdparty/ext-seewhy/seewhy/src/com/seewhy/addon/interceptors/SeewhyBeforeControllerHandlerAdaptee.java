/**
 *
 */
package com.seewhy.addon.interceptors;

import de.hybris.platform.addonsupport.interceptors.BeforeControllerHandlerAdaptee;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;

import com.seewhy.addon.common.SeewhyEmailData;
import com.seewhy.addon.common.SeewhyInMemoryRepository;
import com.seewhy.addon.common.SeewhyReviewData;
import com.seewhy.addon.constants.SeewhyConstants;


/**
 * @author Mike Tinnion
 *
 */
public class SeewhyBeforeControllerHandlerAdaptee implements BeforeControllerHandlerAdaptee
{
	private static final Logger LOG = Logger.getLogger(SeewhyBeforeControllerHandlerAdaptee.class);
	private static final Pattern REVIEW_PATTERN = Pattern.compile(SeewhyConstants.CORE_REVIEW_PATTERN);
	private static final Pattern GUESTLOGIN_PATTERN = Pattern.compile(SeewhyConstants.CORE_GUESTLOGIN_PATTERN);

	private SessionService sessionService = null;
	private SeewhyInMemoryRepository seewhyInMemoryRepository = null;

	/**
	 * We need to identify when a product review has taken place. when this occurs we gather the product code and review
	 * rating and store them in the Seewhy 'product reviewed' context variables.
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @throws Exception
	 * @return
	 */
	@Override
	public boolean beforeController(final HttpServletRequest request, final HttpServletResponse response,
			final HandlerMethod handler) throws Exception
	{
		String strServletPath = null;

		strServletPath = request.getServletPath();

		final Matcher reviewMatcher = REVIEW_PATTERN.matcher(strServletPath);

		//Determine whether the current request is for a product review.
		if (reviewMatcher.find() == true)
		{
			if (LOG.isDebugEnabled() == true)
			{
				LOG.debug("SeewhyBeforeControllerHandlerAdaptee beforeController. review matched: " + reviewMatcher.group());
				logHybrisRequestParameters(request);
			}

			reviewPatternFound(request, reviewMatcher);
		}

		final Matcher guestLoginMatcher = GUESTLOGIN_PATTERN.matcher(strServletPath);

		//Determine whether the current request is for a product review.
		if (guestLoginMatcher.find() == true)
		{
			if (LOG.isDebugEnabled() == true)
			{
				LOG.debug("SeewhyBeforeControllerHandlerAdaptee beforeController. guest login matched: " + guestLoginMatcher.group());
				logHybrisRequestParameters(request);
			}

			guestLoginPatternFound(request, guestLoginMatcher);
		}

		return true;
	}


	/**
	 *
	 * @param request
	 * @param matcher
	 */
	private void guestLoginPatternFound(final HttpServletRequest request, final Matcher matcher)
	{
		if (sessionService != null && sessionService.getCurrentSession() != null)
		{
			String strContext = null;

			//Retrieve the 'context' (session-based, though not session scope) information.
			strContext = sessionService.getCurrentSession().getSessionId();

			final SeewhyEmailData seewhyEmailData = new SeewhyEmailData();
			String strEmail = null;

			strEmail = request.getParameter(SeewhyConstants.CORE_GUESTLOGIN_EMAIL);

			if (LOG.isDebugEnabled() == true)
			{
				LOG.debug("SeeWhyBeforeControllerHandlerAdaptee guestLoginPatternFound. strContext: " + strContext);
				LOG.debug("SeewhyBeforeControllerHandlerAdaptee guestLoginPatternFound. Email: " + strEmail);
			}

			//Set the various attributes of the email found action.
			seewhyEmailData.setEmailAddress(strEmail);
			seewhyEmailData.setFirstName("");
			seewhyEmailData.setLastName("");

			//Add the action to the in-memory repository.
			this.seewhyInMemoryRepository.setVariable(strContext, SeewhyConstants.SEEWHY_ACTION_EMAIL_CAPTURED, seewhyEmailData);
		}
	}

	/**
	 *
	 * @param request
	 * @param matcher
	 */
	private void reviewPatternFound(final HttpServletRequest request, final Matcher matcher)
	{
		if (sessionService != null && sessionService.getCurrentSession() != null)
		{
			String strContext = null;

			//Retrieve the 'context' (session-based, though not session scope) information.
			strContext = sessionService.getCurrentSession().getSessionId();

			final SeewhyReviewData seewhyReviewData = new SeewhyReviewData();
			String strRating = null;
			String strProductCode = null;

			strRating = request.getParameter(SeewhyConstants.CORE_REVIEW_RATING);
			strProductCode = matcher.group(1);

			if (LOG.isDebugEnabled() == true)
			{
				LOG.debug("SeeWhyBeforeControllerHandlerAdaptee reviewPatternFound. strContext: " + strContext);
				LOG.debug("SeewhyBeforeControllerHandlerAdaptee reviewPatternFound. Rating: " + strRating);
				LOG.debug("SeewhyBeforeControllerHandlerAdaptee reviewPatternFound. strProductCode: " + strProductCode);
			}

			//Set the various attributes of the product reviewed action.
			seewhyReviewData.setCode(strProductCode);
			seewhyReviewData.setRating(strRating);

			//Add the action to the in-memory repository.
			this.seewhyInMemoryRepository.setVariable(strContext, SeewhyConstants.SEEWHY_ACTION_PRODUCT_REVIEWED, seewhyReviewData);
		}
	}

	/**
	 *
	 * @return
	 */
	protected SessionService getSessionService()
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeeWhyBeforeControllerHandlerAdaptee getSessionService called: " + sessionService);
		}

		return sessionService;
	}

	/**
	 *
	 * @param sessionService
	 */
	public void setSessionService(final SessionService sessionService)
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeeWhyBeforeControllerHandlerAdaptee setSessionService called: " + sessionService);
		}

		this.sessionService = sessionService;
	}

	/**
	 *
	 * @return
	 */
	protected SeewhyInMemoryRepository getSeewhyInMemoryRepository()
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeControllerHandlerAdaptee getSeewhyInMemoryRepository called: " + seewhyInMemoryRepository);
		}

		return seewhyInMemoryRepository;
	}

	/**
	 *
	 * @param seeWhyInMemoryRepository
	 */
	public void setSeewhyInMemoryRepository(final SeewhyInMemoryRepository seewhyInMemoryRepository)
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeControllerHandlerAdaptee setSeewhyInMemoryRepository called: " + seewhyInMemoryRepository);
		}

		this.seewhyInMemoryRepository = seewhyInMemoryRepository;
	}

	/**
	 *
	 * @param request
	 */
	private void logHybrisRequestParameters(final HttpServletRequest request)
	{
		Enumeration enumeration = null;
		enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements() == true)
		{
			Object obj = null;
			Object objValue = null;

			obj = enumeration.nextElement();
			LOG.debug("SeewhyBeforeControllerHandlerAdaptee beforeController objkey  : " + obj + "/"
					+ (obj == null ? "null" : obj.getClass().getName()));

			objValue = request.getParameter((String) obj);
			LOG.debug("SeewhyBeforeControllerHandlerAdaptee beforeController objvalue: " + objValue + "/"
					+ (objValue == null ? "null" : objValue.getClass().getName()));
		}
	}
}
