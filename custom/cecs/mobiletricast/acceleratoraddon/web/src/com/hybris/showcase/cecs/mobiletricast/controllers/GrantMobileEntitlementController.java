package com.hybris.showcase.cecs.mobiletricast.controllers;

import com.hybris.servicesshowcase.device.impl.ServicesSessionConstants;
import com.hybris.showcase.cecs.mobiletricast.data.GrantMobileEntitlementResultData;
import com.hybris.showcase.cecs.mobiletricast.facade.MobileEntitlementsFacade;
import de.hybris.platform.addonsupport.controllers.AbstractAddOnController;
import de.hybris.platform.commerceservices.enums.UiExperienceLevel;
import de.hybris.platform.servicelayer.session.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Objects;


@Controller
@RequestMapping("/mobile/entitlements")
public class GrantMobileEntitlementController extends AbstractAddOnController
{

	@Resource(name = "mobileEntitlementsFacade")
	private MobileEntitlementsFacade mobileEntitlementsFacade;

	@Resource(name = "sessionService")
	private SessionService sessionService;

	@RequestMapping(value = "/grant", method = RequestMethod.GET)
	public String grant(@RequestParam("authToken") final String authToken)
	{
		final GrantMobileEntitlementResultData resultData = mobileEntitlementsFacade.grantMobileEntitlement(authToken);

		final UiExperienceLevel uiExperienceLevel = sessionService
				.getAttribute(ServicesSessionConstants.REAL_UI_EXPERIENCE_LEVEL_SESSION_ATTRIBUTE);
		if (Objects.equals(uiExperienceLevel, UiExperienceLevel.MOBILE))
		{
			return REDIRECT_PREFIX + resultData.getAppUrl();
		}
		else
		{
			return REDIRECT_PREFIX + "/";
		}
	}

	@RequestMapping(value = "/token/generate", method = RequestMethod.GET)
	@ResponseBody
	public String grant()
	{
		return mobileEntitlementsFacade.generateToken();
	}

}
