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
package com.hybris.social.facebook.opengraphmine.controllers.cms;

import com.restfb.FacebookClient;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.social.facebook.opengraphmine.model.facebook.FacebookSuggestedProductCarouselComponentModel;
import com.hybris.social.facebook.opengraphmine.service.FacebookAuthenticationService;


/**
 * @author rmcotton
 * @author kbaranski
 */
@Controller("FacebookSuggestedProductCarouselComponentController")
@RequestMapping(value = "/view/FacebookSuggestedProductCarouselComponentController")
public class FacebookSuggestedProductCarouselComponentController extends
		AbstractCMSAddOnComponentController<FacebookSuggestedProductCarouselComponentModel>
{
	@Resource(name = "facebookAuthenticationService")
	FacebookAuthenticationService facebookAuthenticationService;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final FacebookSuggestedProductCarouselComponentModel component)
	{
		if (facebookAuthenticationService.getCurrentFacebookUser()!= null)
		{
			model.addAttribute("componentId", component.getUid());
            model.addAttribute("facebookUser", facebookAuthenticationService.getCurrentFacebookUser().getDisplayName());
		}
		return;
	}
}