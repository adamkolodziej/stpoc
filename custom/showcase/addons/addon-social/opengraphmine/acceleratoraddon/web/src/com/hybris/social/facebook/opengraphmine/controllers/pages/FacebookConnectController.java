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
package com.hybris.social.facebook.opengraphmine.controllers.pages;


import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Version;
import com.restfb.scope.ScopeBuilder;
import com.restfb.scope.UserDataPermissions;
import de.hybris.platform.acceleratorservices.urlresolver.SiteBaseUrlResolutionService;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.site.BaseSiteService;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import com.hybris.social.facebook.common.model.FacebookApplicationModel;
import com.hybris.social.facebook.common.service.FacebookApplicationService;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.service.FacebookAuthenticationService;
import com.hybris.social.facebook.opengraphmine.service.FacebookEventService;
import com.hybris.social.facebook.opengraphmine.service.exception.FacebookServiceException;

/**
 * Controller for facebook OAuth2 authentication
 * 
 * (Adapted Spring Social Connect Controller)
 * 
 * @author franciszek.bieg
 * @author rmcotton
 */
@Controller
@RequestMapping("/facebook")
public class FacebookConnectController extends AbstractAddOnPageController
{
	private static final Logger LOG = Logger.getLogger(FacebookConnectController.class.getName());

	@Autowired
	private FacebookApplicationService facebookApplicationService;

	@Autowired
	private SiteBaseUrlResolutionService siteBaseUrlResolutionService;

	@Autowired
	private BaseSiteService baseSiteService;

	@Autowired
	FacebookAuthenticationService hybrisFacebookAuthenticationService;

	@Autowired
	FacebookEventService facebookEventService;

	/**
	 * Process a connect form submission by commencing the process of establishing a connection to the provider on behalf
	 * of the member. For OAuth1, fetches a new request token from the provider, temporarily stores it in the session,
	 * then redirects the member to the provider's site for authorization. For OAuth2, redirects the user to the
	 * provider's site for authorization.
	 */
	@ResponseBody
	@RequestMapping(value = "/connect", method = RequestMethod.POST)
	public String connect(final NativeWebRequest request)
	{
		final FacebookApplicationModel currentApplication = facebookApplicationService.getCurrentApplication();

		if (currentApplication == null)
		{
			LOG.error("No facebook application. Check facebook application configuration");
			return "";
		}

        final ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(UserDataPermissions.USER_STATUS);
		scopeBuilder.addPermission(UserDataPermissions.USER_ABOUT_ME);
		scopeBuilder.addPermission(UserDataPermissions.USER_FRIENDS);
		scopeBuilder.addPermission(UserDataPermissions.USER_PHOTOS);
		scopeBuilder.addPermission(UserDataPermissions.USER_BIRTHDAY);
		scopeBuilder.addPermission(UserDataPermissions.USER_LIKES);
		scopeBuilder.addPermission(UserDataPermissions.USER_HOMETOWN);
        final FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_0);
        return  client.getLoginDialogUrl(currentApplication.getApplicationId().toString(), getRedirectUrl(), scopeBuilder);
    }

	@RequestMapping(value = "/connect", method = RequestMethod.GET, params = "code")
	public String oauth2Callback(final HttpServletRequest request) throws FacebookServiceException {
        final FacebookApplicationModel currentApplication = facebookApplicationService.getCurrentApplication();
        FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_0);
        final AccessToken token = client.obtainUserAccessToken(currentApplication.getApplicationId().toString(), currentApplication.getApplicationSecret(), getRedirectUrl(), request.getParameter("code"));
        client = new DefaultFacebookClient(token.getAccessToken(),Version.VERSION_2_0);
        hybrisFacebookAuthenticationService.authenticateHybrisUser(client,token);
        return "addon:/opengraphmine/pages/facebookconnected";
	}


    @RequestMapping(value = "/login", method = RequestMethod.GET, params = "code")
    public String oauth2LoginCallback(final HttpServletRequest request)
    {
        final FacebookApplicationModel currentApplication = facebookApplicationService.getCurrentApplication();
        final FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_0);
		final AccessToken token = client.obtainUserAccessToken(currentApplication.getApplicationId().toString(), currentApplication.getApplicationSecret(), getRedirectUrl(), request.getParameter("code"));
        return siteBaseUrlResolutionService.getWebsiteUrlForSite(getBaseSiteService().getCurrentBaseSite(), true, "");
    }

    private String getRedirectUrl() {
        final BaseSiteModel currentBaseSite = getBaseSiteService().getCurrentBaseSite();
        return siteBaseUrlResolutionService.getWebsiteUrlForSite(currentBaseSite, true, "")+"/facebook/connect/";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.GET, params = "error")
	public String oauth2FailedCallback()
	{
		return "addon:/opengraphmine/pages/facebookfailed";
	}


	/**
	 * Remove a single provider connection associated with a user account. The user has decided they no longer wish to
	 * use the service provider account from this application. Note: requires {@link HiddenHttpMethodFilter} to be
	 * registered with the '_method' request parameter set to 'DELETE' to convert web browser POSTs to DELETE requests.
	 */
	@ResponseBody
	@RequestMapping(value = "/disconnect", method = RequestMethod.POST)
	public String removeConnection()
	{
		hybrisFacebookAuthenticationService.logout();
		return "";
	}


	@RequestMapping(value = "/like", method = RequestMethod.GET)
	public void like(@RequestParam(value = "accesstoken", required = true) final String accessToken,@RequestParam(value = "url", required = true) final String url)
	{
        final FacebookApplicationModel currentApplication = facebookApplicationService.getCurrentApplication();
        final FacebookClient client = new DefaultFacebookClient(accessToken,Version.VERSION_2_0);
		final FacebookUserModel facebookUser = hybrisFacebookAuthenticationService.getOrCreateFacebookUserForConnection(client);
		facebookEventService.like(client, currentApplication, facebookUser, url);
	}


	@RequestMapping(value = "/unlike", method = RequestMethod.GET)
	public void unlike(@RequestParam(value = "accesstoken", required = true) final String accessToken,
			@RequestParam(value = "url", required = true) final String url)

	{
		final FacebookApplicationModel currentApplication = facebookApplicationService.getCurrentApplication();
		final FacebookClient client = new DefaultFacebookClient(accessToken,Version.VERSION_2_0);
		final FacebookUserModel facebookUser = hybrisFacebookAuthenticationService.getOrCreateFacebookUserForConnection(client);
		facebookEventService.unlike(client, currentApplication, facebookUser, url);
	}

	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}
}
