/**
 * 
 */
package de.hybris.platform.yacceleratorstorefront.controllers.pages;

import de.hybris.platform.acceleratorservices.urlresolver.SiteBaseUrlResolutionService;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.forms.LoginForm;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.yacceleratorstorefront.controllers.ControllerConstants;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author npavlovic
 * 
 *         CECS-101: Login dropdown
 */
@Controller
@RequestMapping("/loginDropdown")
public class LoginDropdownController extends AbstractPageController
{
	protected static final String SPRING_SECURITY_LAST_USERNAME = "SPRING_SECURITY_LAST_USERNAME";

	@Autowired
	private SiteBaseUrlResolutionService siteBaseUrlResolutionService;

	@RequestMapping(method = RequestMethod.GET)
	public String loginDropdownGet(final Model model, final HttpSession session)
	{
		final LoginForm loginForm = new LoginForm();

		final String username = (String) session.getAttribute(SPRING_SECURITY_LAST_USERNAME);
		if (username != null)
		{
			session.removeAttribute(SPRING_SECURITY_LAST_USERNAME);
		}
		loginForm.setJ_username(username);

		model.addAttribute("loginForm", loginForm);
		model.addAttribute("actionUrl", getLoginUrl());

		return ControllerConstants.Views.Fragments.LoginDropdown;
	}

	protected String getLoginUrl()
	{
		final CMSSiteModel site = getCmsSiteService().getCurrentSite();
		return siteBaseUrlResolutionService.getWebsiteUrlForSite(site, true, "/j_spring_security_check");
	}
}
