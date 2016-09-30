/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package de.hybris.platform.yacceleratorstorefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.servicelayer.user.UserService;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hybris.showcase.AccountPagePaymentDetailsControllerBillingForm;
import com.hybris.showcase.facades.ServicesSubscriptionFacade;


/**
 * Controller for home page
 */
@Controller
@Scope("tenant")
@RequestMapping("/my-account")
/**
 *
 * CECS-132 billing options improvements CECS-69 component to switch billing options
 */
public class AccountBillingOptionsController extends AbstractSearchPageController
{
	// Internal Redirects
	public static final String REDIRECT_PREFIX = "redirect:";
	protected static final String REDIRECT_URL_ORDER_CONFIRMATION = REDIRECT_PREFIX + "/my-account/change-billing-options/";

	private static final String ACCOUNT_CHANGE_BILLING_OPTIONS_CMS_PAGE = "account-change-billing-options";
	private static final Logger LOG = Logger.getLogger(AccountBillingOptionsController.class);

	@Resource(name = "accountBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;

	@Resource
	private UserService userService;

	@Resource
	private ServicesSubscriptionFacade subscriptionFacade;


	@RequestMapping(value = "/change-billing-options", method = RequestMethod.GET)
	@RequireHardLogIn
	public String changeBillingOptions(final Model model) throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(ACCOUNT_CHANGE_BILLING_OPTIONS_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ACCOUNT_CHANGE_BILLING_OPTIONS_CMS_PAGE));
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.change-billing-options"));
		model.addAttribute("metaRobots", "noindex,nofollow");

		return getViewForPage(model);
	}

	@RequestMapping(value = "/edit/billing", method = RequestMethod.POST)
	@RequireHardLogIn
	public String editAccountBillingSetup(
			@ModelAttribute("BillingForm") final AccountPagePaymentDetailsControllerBillingForm billingForm, final Model model,
			final RedirectAttributes redirectAttributes)
	{
		final boolean isBillingOption = billingForm.isBillingOption();
		final boolean isRemainder = billingForm.isRemainder();
		subscriptionFacade.setEmailRemindersAndBillingEpaper(isRemainder, isBillingOption);

		model.addAttribute("billingForm", billingForm);

		GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER,
				"text.account.profile.confirmationUpdated", null);

		return REDIRECT_URL_ORDER_CONFIRMATION;
	}

}
