package com.hybris.showcase.guidedselling.controllers.pages;

import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CardTypeData;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.subscriptionfacades.SubscriptionFacade;
import de.hybris.platform.subscriptionfacades.billing.CreditCardFacade;
import de.hybris.platform.subscriptionfacades.data.SubscriptionPaymentData;
import de.hybris.platform.subscriptionfacades.exceptions.SubscriptionFacadeException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hybris.showcase.guidedselling.controllers.validation.CCPaymentInfoDataValidator;
import com.hybris.showcase.guidedselling.data.CustomerPaymentInfos;
import com.hybris.showcase.guidedselling.data.GuidedsellingSelectOption;
import com.hybris.showcase.guidedselling.facades.impl.BundleAcceleratorCheckoutFacade;


/**
 * Created by mgolubovic on 8.5.2015..
 */
@Controller
@RequestMapping("/guidedselling/paymentInfos")
public class GuidedsellingPaymentInfoController extends AbstractAddOnPageController
{
	protected static final Logger LOG = Logger.getLogger(GuidedsellingPaymentInfoController.class);

	@Resource(name = "acceleratorCheckoutFacade")
	private BundleAcceleratorCheckoutFacade checkoutFacade;

	@Resource(name = "userFacade")
	private UserFacade userFacade;

	@Resource(name = "creditCardFacade")
	private CreditCardFacade creditCardFacade;

	@Resource(name = "subscriptionFacade")
	private SubscriptionFacade subscriptionFacade;

	@Resource(name = "ccPaymentInfoDataValidator")
	private CCPaymentInfoDataValidator ccPaymentInfoDataValidator;

	@Resource(name = "themeSource")
	private MessageSource themeSource;


	@RequestMapping(value = "/customerPaymentInfos", method = RequestMethod.GET)
	@ResponseBody
	public CustomerPaymentInfos getCustomerPaymentInfos()
	{
		final CustomerPaymentInfos customerPaymentInfos = new CustomerPaymentInfos();
		customerPaymentInfos.setCartPaymentInfo(checkoutFacade.getCheckoutCart().getPaymentInfo());
		customerPaymentInfos.setAllPaymentInfos(userFacade.getCCPaymentInfos(true));

		final CustomerData customerData = getUser();
		customerPaymentInfos.setFirstName(customerData.getFirstName());
		customerPaymentInfos.setLastName(customerData.getLastName());

		customerPaymentInfos.setCardTypes(getCardTypes());
		customerPaymentInfos.setExpiryYears(getExpiryYears());
		customerPaymentInfos.setMonths(getMonths());

		return customerPaymentInfos;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String addCustomerPaymentInfo(@RequestBody final CCPaymentInfoData newPaymentInfo, final HttpServletRequest request,
			final BindingResult bindingResult, final HttpServletResponse response)
	{
		ccPaymentInfoDataValidator.validate(newPaymentInfo, bindingResult);
		if (bindingResult.hasErrors())
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			if (bindingResult.getErrorCount() > 1)
			{
				return themeSource.getMessage("guidedselling.multiple.empty.fields", null, "All fields must be filled out properly",
						getI18nService().getCurrentLocale());
			}
			else
			{
				return bindingResult.getAllErrors().get(0).getDefaultMessage();
			}
		}

		final AddressData addressData = checkoutFacade.getCheckoutCart().getDeliveryAddress();
		addressData.setBillingAddress(true);
		newPaymentInfo.setBillingAddress(addressData);
		newPaymentInfo.setSaved(true);

		CCPaymentInfoData newPaymentSubscription = null;
		try
		{
			final String authorizationRequestId = request.getRemoteAddr();
			final SubscriptionPaymentData initResult = subscriptionFacade.initializeTransaction(authorizationRequestId, "NOTSET",
					"NOTSET", new HashMap<String, String>());
			final String sessionToken = initResult.getParameters().get("sessionTransactionToken");

			final Map<String, String> createPaymentDetailsMap = createPaymentDetailsMap(newPaymentInfo);
			final SubscriptionPaymentData result = subscriptionFacade.finalizeTransaction(authorizationRequestId, sessionToken,
					createPaymentDetailsMap);

			newPaymentSubscription = subscriptionFacade.createPaymentSubscription(createPaymentDetailsMap);

		}
		catch (final SubscriptionFacadeException e)
		{
			LOG.error("There was an error while trying to create a payment subscription.", e);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return e.getMessage();
		}

		if (checkoutFacade.hasNoPaymentInfo())
		{
			userFacade.setDefaultPaymentInfo(newPaymentSubscription);
		}

		userFacade.updateCCPaymentInfo(newPaymentSubscription);
		checkoutFacade.setPaymentDetails(newPaymentSubscription.getId());

		return null;
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Boolean selectCustomerPaymentInfo(@RequestParam(value = "paymentInfoId", required = true) final String paymentInfoId)
	{
		return checkoutFacade.setPaymentDetails(paymentInfoId);
	}

	protected Map<String, String> createPaymentDetailsMap(final CCPaymentInfoData newPaymentInfo)
	{
		final Map<String, String> map = new HashMap<>();

		// Mask the card number
		String maskedCardNumber = "************";
		if (newPaymentInfo.getCardNumber().length() >= 4)
		{
			final String endPortion = newPaymentInfo.getCardNumber().trim().substring(newPaymentInfo.getCardNumber().length() - 4);
			maskedCardNumber = maskedCardNumber + endPortion;
		}

		map.put("cardNumber", maskedCardNumber);
		map.put("cardType", newPaymentInfo.getCardType());
		map.put("expiryMonth", newPaymentInfo.getExpiryMonth());
		map.put("expiryYear", newPaymentInfo.getExpiryYear());
		map.put("issueNumber", newPaymentInfo.getIssueNumber());
		map.put("nameOnCard", newPaymentInfo.getAccountHolderName());
		map.put("startMonth", newPaymentInfo.getStartMonth());
		map.put("startYear", newPaymentInfo.getStartYear());

		map.put("billingAddress_countryIso", newPaymentInfo.getBillingAddress().getCountry().getIsocode());
		map.put("billingAddress_titleCode", newPaymentInfo.getBillingAddress().getTitleCode());
		map.put("billingAddress_firstName", newPaymentInfo.getBillingAddress().getFirstName());
		map.put("billingAddress_lastName", newPaymentInfo.getBillingAddress().getLastName());
		map.put("billingAddress_line1", newPaymentInfo.getBillingAddress().getLine1());
		map.put("billingAddress_line2", newPaymentInfo.getBillingAddress().getLine2());
		map.put("billingAddress_postcode", newPaymentInfo.getBillingAddress().getPostalCode());
		map.put("billingAddress_townCity", newPaymentInfo.getBillingAddress().getTown());

		return map;
	}

	protected List<CountryData> getBillingCountries()
	{
		return checkoutFacade.getBillingCountries();
	}

	protected List<CardTypeData> getCardTypes()
	{
		final List<CardTypeData> creditCards = checkoutFacade.getSupportedCardTypes();
		creditCardFacade.mappingStrategy(creditCards);

		return creditCards;
	}

	protected List<GuidedsellingSelectOption> getMonths()
	{
		final List<GuidedsellingSelectOption> months = new ArrayList<GuidedsellingSelectOption>();

		for (int i = 1; i <= 12; i++)
		{
			final GuidedsellingSelectOption option = new GuidedsellingSelectOption();
			String optionValue;
			if (i < 10)
			{
				optionValue = "0" + String.valueOf(i);
			}
			else
			{
				optionValue = String.valueOf(i);
			}
			option.setCode(optionValue);
			option.setName(optionValue);
			months.add(option);
		}

		return months;
	}

	protected List<GuidedsellingSelectOption> getExpiryYears()
	{
		final List<GuidedsellingSelectOption> expiryYears = new ArrayList<GuidedsellingSelectOption>();
		final Calendar calender = new GregorianCalendar();

		for (int i = calender.get(Calendar.YEAR); i < (calender.get(Calendar.YEAR) + 11); i++)
		{
			final GuidedsellingSelectOption option = new GuidedsellingSelectOption();
			option.setCode(String.valueOf(i));
			option.setName(String.valueOf(i));
			expiryYears.add(option);
		}

		return expiryYears;
	}
}
