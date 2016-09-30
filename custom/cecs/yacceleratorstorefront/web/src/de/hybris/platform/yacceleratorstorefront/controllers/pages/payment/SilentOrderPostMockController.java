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
package de.hybris.platform.yacceleratorstorefront.controllers.pages.payment;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.AbstractController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.subscriptionfacades.SubscriptionFacade;
import de.hybris.platform.subscriptionfacades.data.SubscriptionPaymentData;
import de.hybris.platform.subscriptionfacades.exceptions.SubscriptionFacadeException;
import de.hybris.platform.yacceleratorstorefront.forms.SopPaymentDetailsForm;
import de.hybris.platform.yacceleratorstorefront.forms.impl.SbgSopPaymentDetailsValidator;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/*
 * CECS-468 Restored temporarily - check if we should use the one from b2ctelcocheckoutaddon or just to update this class
 */
/**
 * Created by mgolubovic on 08.06.2015.. partly migrated from telco
 */
@Controller
@RequestMapping("sbg-sop-mock")
public class SilentOrderPostMockController extends AbstractController
{
	private static final Logger LOG = Logger.getLogger(SilentOrderPostMockController.class);

	private static final String REDIRECT_URL_SUMMARY = REDIRECT_PREFIX + "/checkout/multi/summary";
	private static final String REDIRECT_URL_ADD_PAYMENT_METHOD = REDIRECT_PREFIX + "/my-account/payment-details/add";
	private static final String REDIRECT_URL_EDIT_PAYMENT_DETAILS = REDIRECT_PREFIX
			+ "/my-account/payment-details/edit?paymentInfoId=";
	private static final String REDIRECT_URL_PAYMENT_INFO = REDIRECT_PREFIX + "/my-account/payment-details";

	@Resource(name = "sessionService")
	private SessionService sessionService;

	@Resource(name = "subscriptionFacade")
	private SubscriptionFacade subscriptionFacade;

	@Resource(name = "userFacade")
	private UserFacade userFacade;

	@Resource(name = "sbgSopPaymentDetailsValidator")
	private SbgSopPaymentDetailsValidator sopPaymentDetailsValidator;


	protected SessionService getSessionService()
	{
		return sessionService;
	}

	protected SubscriptionFacade getSubscriptionFacade()
	{
		return subscriptionFacade;
	}

	protected UserFacade getUserFacade()
	{
		return userFacade;
	}

	protected SbgSopPaymentDetailsValidator getSopPaymentDetailsValidator()
	{
		return sopPaymentDetailsValidator;
	}

	@RequestMapping(value = "/handle-form-post", method = RequestMethod.POST)
	public String handleFormPost(@Valid final SopPaymentDetailsForm form, final BindingResult bindingResult,
			@RequestParam("editMode") final Boolean editMode, @RequestParam("paymentInfoId") final String paymentInfoId,
			final RedirectAttributes redirectAttributes)
	{
		getSopPaymentDetailsValidator().validate(form, bindingResult);
		if (bindingResult.hasErrors())
		{
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER,
					"checkout.error.paymentmethod.formentry.invalid", null);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.sopPaymentDetailsForm",
					bindingResult);
			redirectAttributes.addFlashAttribute("sopPaymentDetailsForm", bindingResult.getTarget());

			return Boolean.TRUE.equals(editMode) ? REDIRECT_URL_EDIT_PAYMENT_DETAILS + paymentInfoId
					: REDIRECT_URL_ADD_PAYMENT_METHOD;

		}
		else
		{
			final String authorizationRequestId = (String) getSessionService().getAttribute("authorizationRequestId");
			final String authorizationRequestToken = (String) getSessionService().getAttribute("authorizationRequestToken");

			try
			{
				if (BooleanUtils.isTrue(editMode))
				{
					final CCPaymentInfoData ccPaymentInfoData = setupCCPaymentInfoData(form, paymentInfoId);
					if (null != ccPaymentInfoData)
					{
						final CCPaymentInfoData result = getSubscriptionFacade().changePaymentMethod(ccPaymentInfoData, null, true,
								null);

						// enrich result data with form data, which is not provided from the facade call
						result.setId(paymentInfoId);
						result.getBillingAddress().setTitleCode(ccPaymentInfoData.getBillingAddress().getTitleCode());
						result.setStartMonth(ccPaymentInfoData.getStartMonth());
						result.setStartYear(ccPaymentInfoData.getStartYear());
						result.setIssueNumber(ccPaymentInfoData.getIssueNumber());


						if (form.getMakeAsDefault().booleanValue())
						{
							getUserFacade().setDefaultPaymentInfo(result);
						}

						GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER,
								"text.account.paymentDetails.editSuccessful", null);
					}
					else
					{
						GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER,
								"text.account.paymentDetails.nonExisting.error", null);
					}
				}
				else
				{
					final Map<String, String> params = createPaymentDetailsMap(form);
					final SubscriptionPaymentData result = getSubscriptionFacade().finalizeTransaction(authorizationRequestId,
							authorizationRequestToken, params);

					final CCPaymentInfoData newPaymentSubscription = getSubscriptionFacade().createPaymentSubscription(params);

					// enrich result data with form data, which is not provided from the facade call
					newPaymentSubscription.setStartMonth(form.getStartMonth());
					newPaymentSubscription.setStartYear(form.getStartYear());
					newPaymentSubscription.setIssueNumber(form.getIssueNumber());
					newPaymentSubscription.setSaved(true);



					if (form.getMakeAsDefault().booleanValue())
					{
						getUserFacade().setDefaultPaymentInfo(newPaymentSubscription);
					}


					// Disabled because it causes hidden dependency to ybillingintegration
					//					if (StringUtils.isEmpty(newPaymentSubscription.getYBillingCardId()))
					//					{
					//						GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.INFO_MESSAGES_HOLDER,
					//								"text.account.paymentDetails.integration.addUnsuccessfull", null);
					//					}


					GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER,
							"text.account.paymentDetails.addSuccessful", null);
				}
			}
			catch (final SubscriptionFacadeException e)
			{
				LOG.error("Creating a new payment method failed", e);
				GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER,
						"checkout.multi.paymentMethod.addPaymentDetails.incomplete", null);
				return REDIRECT_URL_ADD_PAYMENT_METHOD;
			}
		}

		return REDIRECT_URL_PAYMENT_INFO;
	}

	private Map<String, String> createPaymentDetailsMap(final SopPaymentDetailsForm form)
	{
		final Map<String, String> map = new HashMap<>();

		// Mask the card number
		String maskedCardNumber = "************";
		if (form.getCardNumber().length() >= 4)
		{
			final String endPortion = form.getCardNumber().trim().substring(form.getCardNumber().length() - 4);
			maskedCardNumber = maskedCardNumber + endPortion;
		}


		map.put("cardNumber", maskedCardNumber);
		map.put("cardType", form.getCardTypeCode());
		map.put("expiryMonth", form.getExpiryMonth());
		map.put("expiryYear", form.getExpiryYear());
		map.put("issueNumber", form.getIssueNumber());
		map.put("nameOnCard", form.getNameOnCard());
		map.put("startMonth", form.getStartMonth());
		map.put("startYear", form.getStartYear());

		if (form.getBillingAddress() != null && Boolean.TRUE.equals(form.getNewBillingAddress()))
		{
			map.put("billingAddress_countryIso", form.getBillingAddress().getCountryIso());
			map.put("billingAddress_titleCode", form.getBillingAddress().getTitleCode());
			map.put("billingAddress_firstName", form.getBillingAddress().getFirstName());
			map.put("billingAddress_lastName", form.getBillingAddress().getLastName());
			map.put("billingAddress_line1", form.getBillingAddress().getLine1());
			map.put("billingAddress_line2", form.getBillingAddress().getLine2());
			map.put("billingAddress_postcode", form.getBillingAddress().getPostcode());
			map.put("billingAddress_townCity", form.getBillingAddress().getTownCity());
		}
		else
		{
			map.put("billingAddress_countryIso", userFacade.getDefaultAddress().getCountry().getIsocode());
			map.put("billingAddress_titleCode", userFacade.getDefaultAddress().getTitleCode());
			map.put("billingAddress_firstName", userFacade.getDefaultAddress().getFirstName());
			map.put("billingAddress_lastName", userFacade.getDefaultAddress().getLastName());
			map.put("billingAddress_line1", userFacade.getDefaultAddress().getLine1());
			map.put("billingAddress_line2", userFacade.getDefaultAddress().getLine2());
			map.put("billingAddress_postcode", userFacade.getDefaultAddress().getPostalCode());
			map.put("billingAddress_townCity", userFacade.getDefaultAddress().getTown());
		}

		return map;
	}

	private CCPaymentInfoData setupCCPaymentInfoData(final SopPaymentDetailsForm form, final String paymentInfoId)
	{
		final CCPaymentInfoData ccPaymentInfoData = getUserFacade().getCCPaymentInfoForCode(paymentInfoId);

		if (null != form && null != ccPaymentInfoData)
		{
			ccPaymentInfoData.setCardType(form.getCardTypeCode());
			ccPaymentInfoData.setCardNumber(form.getCardNumber());
			ccPaymentInfoData.setAccountHolderName(form.getNameOnCard());
			ccPaymentInfoData.setStartMonth(form.getStartMonth());
			ccPaymentInfoData.setStartYear(form.getStartYear());
			ccPaymentInfoData.setExpiryMonth(form.getExpiryMonth());
			ccPaymentInfoData.setExpiryYear(form.getExpiryYear());
			ccPaymentInfoData.setIssueNumber(form.getIssueNumber());

			if (Boolean.TRUE.equals(form.getNewBillingAddress()))
			{
				final AddressData addressData = new AddressData();
				addressData.setId(form.getBillingAddress().getAddressId());
				addressData.setFirstName(form.getBillingAddress().getFirstName());
				addressData.setLastName(form.getBillingAddress().getLastName());
				addressData.setLine1(form.getBillingAddress().getLine1());
				addressData.setLine2(form.getBillingAddress().getLine2());
				addressData.setTown(form.getBillingAddress().getTownCity());
				addressData.setPostalCode(form.getBillingAddress().getPostcode());
				addressData.setTitleCode(form.getBillingAddress().getTitleCode());

				if (StringUtils.isNotEmpty(form.getBillingAddress().getCountryIso()))
				{
					final CountryData countryData = new CountryData();
					countryData.setIsocode(form.getBillingAddress().getCountryIso());
					addressData.setCountry(countryData);
				}

				ccPaymentInfoData.setBillingAddress(addressData);
			}
		}

		return ccPaymentInfoData;
	}
}
