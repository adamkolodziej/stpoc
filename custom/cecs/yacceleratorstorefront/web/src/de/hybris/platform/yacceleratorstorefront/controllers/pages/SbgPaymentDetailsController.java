package de.hybris.platform.yacceleratorstorefront.controllers.pages;

import de.hybris.platform.acceleratorservices.urlresolver.SiteBaseUrlResolutionService;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.AddressForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CardTypeData;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.TitleData;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.subscriptionfacades.SubscriptionFacade;
import de.hybris.platform.subscriptionfacades.billing.CreditCardFacade;
import de.hybris.platform.subscriptionfacades.data.SubscriptionPaymentData;
import de.hybris.platform.subscriptionfacades.exceptions.SubscriptionFacadeException;
import de.hybris.platform.yacceleratorstorefront.forms.SopPaymentDetailsForm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by m.golubovic on 5.6.2015..
 */
@Controller
@RequestMapping("/my-account")
public class SbgPaymentDetailsController extends AbstractPageController
{
	private static final String ADD_PAYMENT_DETAILS_CMS_PAGE = "add-payment-details";
	private static final String REDIRECT_URL_PAYMENT_INFO = REDIRECT_PREFIX + "/my-account/payment-details";

	@Resource(name = "accountBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;

	@Resource(name = "userFacade")
	protected UserFacade userFacade;

	@Resource(name = "acceleratorCheckoutFacade")
	private CheckoutFacade checkoutFacade;

	@Resource(name = "creditCardFacade")
	private CreditCardFacade creditCardFacade;

	@Resource(name = "subscriptionFacade")
	private SubscriptionFacade subscriptionFacade;

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;

	@Resource(name = "siteBaseUrlResolutionService")
	private SiteBaseUrlResolutionService siteBaseUrlResolutionService;

	@RequestMapping(value = "/payment-details/add", method = RequestMethod.GET)
	@RequireHardLogIn
	public String addPaymentDetails(final HttpServletRequest request, final Model model) throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(ADD_PAYMENT_DETAILS_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ADD_PAYMENT_DETAILS_CMS_PAGE));
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.paymentDetails"));
		model.addAttribute("metaRobots", "noindex,nofollow");

		model.addAttribute("isEditMode", Boolean.FALSE);

		final String clientIp = getClientIpAddr(request);

		final SopPaymentDetailsForm sopPaymentDetailsForm = getSopPaymentDetailsForm(model);
		try
		{
			setupSilentOrderPostPage(sopPaymentDetailsForm, model, clientIp, null);
		}
		catch (final Exception e)
		{
			LOG.error("Failed to setup payment details form", e);
			GlobalMessages.addErrorMessage(model, "checkout.multi.paymentMethod.addPaymentDetails.generalError");
			return REDIRECT_URL_PAYMENT_INFO;
		}

		return getViewForPage(model);
	}

	@RequestMapping(value = "/payment-details/edit", method = RequestMethod.GET)
	@RequireHardLogIn
	public String editPaymentDetails(final HttpServletRequest request, final Model model,
			@RequestParam("paymentInfoId") final String paymentInfoId) throws CMSItemNotFoundException
	{
		if (StringUtils.isBlank(paymentInfoId))
		{
			LOG.warn("Payment method id may not be blank.");
			return REDIRECT_URL_PAYMENT_INFO;
		}
		storeCmsPageInModel(model, getContentPageForLabelOrId(ADD_PAYMENT_DETAILS_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ADD_PAYMENT_DETAILS_CMS_PAGE));
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.paymentDetails"));
		model.addAttribute("metaRobots", "noindex,nofollow");

		model.addAttribute("isEditMode", Boolean.TRUE);
		model.addAttribute("isFirstPaymentMethod", Boolean.valueOf(userFacade.getCCPaymentInfos(true).size() == 1));

		final String clientIp = getClientIpAddr(request);
		final CCPaymentInfoData ccPaymentInfoData = userFacade.getCCPaymentInfoForCode(paymentInfoId);

		if (null == ccPaymentInfoData)
		{
			GlobalMessages.addErrorMessage(model, "text.account.paymentDetails.nonExisting.error");
			return REDIRECT_URL_PAYMENT_INFO;
		}

		final SopPaymentDetailsForm sopPaymentDetailsForm = getSopPaymentDetailsForm(model);
		try
		{
			setupSilentOrderPostPage(sopPaymentDetailsForm, model, clientIp, ccPaymentInfoData);
			sopPaymentDetailsForm.setNewBillingAddress(Boolean.TRUE);
			return getViewForPage(model);
		}
		catch (final Exception e)
		{
			LOG.error("Failed to setup payment details form", e);
			GlobalMessages.addErrorMessage(model, "checkout.multi.paymentMethod.addPaymentDetails.generalError");
			model.addAttribute("sopPaymentDetailsForm", sopPaymentDetailsForm);

			return REDIRECT_URL_PAYMENT_INFO;
		}
	}

	@ModelAttribute("titles")
	public Collection<TitleData> getTitles()
	{
		return userFacade.getTitles();
	}

	@ModelAttribute("countries")
	public Collection<CountryData> getCountries()
	{
		return checkoutFacade.getDeliveryCountries();
	}

	@ModelAttribute("billingCountries")
	public Collection<CountryData> getBillingCountries()
	{
		return checkoutFacade.getBillingCountries();
	}

	@ModelAttribute("cardTypes")
	public Collection<CardTypeData> getCardTypes()
	{
		final Collection<CardTypeData> creditCards = checkoutFacade.getSupportedCardTypes();
		creditCardFacade.mappingStrategy(creditCards);

		return creditCards;
	}

	@ModelAttribute("months")
	public List<SelectOption> getMonths()
	{
		final List<SelectOption> months = new ArrayList<SelectOption>();

		months.add(new SelectOption("01", "01"));
		months.add(new SelectOption("02", "02"));
		months.add(new SelectOption("03", "03"));
		months.add(new SelectOption("04", "04"));
		months.add(new SelectOption("05", "05"));
		months.add(new SelectOption("06", "06"));
		months.add(new SelectOption("07", "07"));
		months.add(new SelectOption("08", "08"));
		months.add(new SelectOption("09", "09"));
		months.add(new SelectOption("10", "10"));
		months.add(new SelectOption("11", "11"));
		months.add(new SelectOption("12", "12"));

		return months;
	}

	@ModelAttribute("startYears")
	public List<SelectOption> getStartYears()
	{
		final List<SelectOption> startYears = new ArrayList<SelectOption>();
		final Calendar calender = new GregorianCalendar();

		for (int i = calender.get(Calendar.YEAR); i > (calender.get(Calendar.YEAR) - 6); i--)
		{
			startYears.add(new SelectOption(String.valueOf(i), String.valueOf(i)));
		}

		return startYears;
	}

	@ModelAttribute("expiryYears")
	public List<SelectOption> getExpiryYears()
	{
		final List<SelectOption> expiryYears = new ArrayList<SelectOption>();
		final Calendar calender = new GregorianCalendar();

		for (int i = calender.get(Calendar.YEAR); i < (calender.get(Calendar.YEAR) + 11); i++)
		{
			expiryYears.add(new SelectOption(String.valueOf(i), String.valueOf(i)));
		}

		return expiryYears;
	}

	public static class SelectOption
	{
		private final String code;
		private final String name;

		public SelectOption(final String code, final String name)
		{
			this.code = code;
			this.name = name;
		}

		public String getCode()
		{
			return code;
		}

		public String getName()
		{
			return name;
		}
	}

	protected void setupSilentOrderPostPage(final SopPaymentDetailsForm sopPaymentDetailsForm, final Model model,
			final String clientIpAddress, final CCPaymentInfoData ccPaymentInfoData)
	{
		try
		{
			final SubscriptionPaymentData initResult = subscriptionFacade.initializeTransaction(clientIpAddress, "NOTSET", "NOTSET",
					new HashMap<String, String>());
			final String sessionToken = initResult.getParameters().get("sessionTransactionToken");

			Assert.notNull(sessionToken, "Session token may not be null");

			getSessionService().setAttribute("authorizationRequestId", clientIpAddress);
			getSessionService().setAttribute("authorizationRequestToken", sessionToken);

			model.addAttribute("sessionToken", sessionToken);
		}
		catch (final SubscriptionFacadeException e)
		{
			model.addAttribute("postUrl", null);
			model.addAttribute("sessionToken", null);
			LOG.warn("Failed to initialize session for silent order post page", e);
			GlobalMessages.addErrorMessage(model, "checkout.multi.sop.globalError");
		}
		catch (final IllegalArgumentException e)
		{
			model.addAttribute("postUrl", null);
			model.addAttribute("sessionToken", null);
			LOG.warn("Failed to set up silent order post page", e);
			GlobalMessages.addErrorMessage(model, "checkout.multi.sop.globalError");
		}

		if (!model.containsAttribute("accErrorMsgs"))
		{
			setupSopPaymentDetailsForm(sopPaymentDetailsForm, ccPaymentInfoData);
		}

		model.addAttribute("sopPaymentDetailsForm", sopPaymentDetailsForm);
		model.addAttribute("paymentInfo", ccPaymentInfoData);
	}

	private void setupSopPaymentDetailsForm(final SopPaymentDetailsForm sopPaymentDetailsForm,
			final CCPaymentInfoData ccPaymentInfoData)
	{
		if (null != ccPaymentInfoData)
		{
			sopPaymentDetailsForm.setCardNumber(ccPaymentInfoData.getCardNumber());
			sopPaymentDetailsForm.setCardTypeCode(ccPaymentInfoData.getCardType());
			sopPaymentDetailsForm.setExpiryMonth(ccPaymentInfoData.getExpiryMonth());
			sopPaymentDetailsForm.setExpiryYear(ccPaymentInfoData.getExpiryYear());
			sopPaymentDetailsForm.setIssueNumber(ccPaymentInfoData.getIssueNumber());
			sopPaymentDetailsForm.setNameOnCard(ccPaymentInfoData.getAccountHolderName());
			sopPaymentDetailsForm.setStartMonth(ccPaymentInfoData.getStartMonth());
			sopPaymentDetailsForm.setStartYear(ccPaymentInfoData.getStartYear());

			setupBillingAddress(sopPaymentDetailsForm, ccPaymentInfoData.getBillingAddress());
		}
		else
		{
			setCustomerFullNameInAddress(sopPaymentDetailsForm);
		}
	}

	private void setCustomerFullNameInAddress(final SopPaymentDetailsForm sopPaymentDetailsForm)
	{
		final AddressForm addressForm = new AddressForm();
		final CustomerData customerData = getUser();
		addressForm.setFirstName(customerData.getFirstName());
		addressForm.setLastName(customerData.getLastName());
		addressForm.setTitleCode(customerData.getTitleCode());
		sopPaymentDetailsForm.setNameOnCard(addressForm.getFirstName() + " " + addressForm.getLastName());
		sopPaymentDetailsForm.setBillingAddress(addressForm);
	}

	private void setupBillingAddress(final SopPaymentDetailsForm sopPaymentDetailsForm, final AddressData address)
	{
		sopPaymentDetailsForm.setNewBillingAddress(Boolean.FALSE);

		if (null != address)
		{
			final AddressForm addressForm = new AddressForm();
			addressForm.setTitleCode(address.getTitleCode());
			addressForm.setFirstName(address.getFirstName());
			addressForm.setLastName(address.getLastName());
			addressForm.setLine1(address.getLine1());
			addressForm.setLine2(address.getLine2());
			addressForm.setTownCity(address.getTown());
			addressForm.setPostcode(address.getPostalCode());

			if (null != address.getCountry())
			{
				addressForm.setCountryIso(address.getCountry().getIsocode());
			}

			sopPaymentDetailsForm.setBillingAddress(addressForm);
		}
	}

	protected String getClientIpAddr(final HttpServletRequest request)
	{
		String clientIp = request.getHeader("X-Forwarded-For");
		if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp))
		{
			clientIp = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp))
		{
			clientIp = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp))
		{
			clientIp = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp))
		{
			clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp))
		{
			clientIp = request.getRemoteAddr();
		}
		return clientIp;
	}

	private SopPaymentDetailsForm getSopPaymentDetailsForm(final Model model)
	{
		SopPaymentDetailsForm sopPaymentDetailsForm = new SopPaymentDetailsForm();
		if (model.containsAttribute("sopPaymentDetailsForm"))
		{
			sopPaymentDetailsForm = (SopPaymentDetailsForm) model.asMap().get("sopPaymentDetailsForm");
		}

		return sopPaymentDetailsForm;
	}
}
