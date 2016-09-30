package com.hybris.showcase.guidedselling.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.forms.AddressForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.verification.AddressVerificationResultHandler;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.commercefacades.address.AddressVerificationFacade;
import de.hybris.platform.commercefacades.address.data.AddressVerificationResult;
import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.order.data.DeliveryModeData;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.commerceservices.address.AddressVerificationDecision;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hybris.showcase.guidedselling.controllers.validation.GuidedsellingDeliveryAddressValidator;
import com.hybris.showcase.guidedselling.data.CustomerDeliveryInfo;
import com.hybris.showcase.guidedselling.data.GuidedsellingSelectOption;


/**
 * Created by m.golubovic on 22.7.2015..
 */
@Controller
@RequestMapping("/guidedselling/deliveryInfo")
public class GuidedsellingDeliveryInfoController extends AbstractAddOnPageController
{
	@Resource(name = "acceleratorCheckoutFacade")
	private CheckoutFacade checkoutFacade;

	@Resource(name = "userFacade")
	protected UserFacade userFacade;

	@Resource(name = "addressVerificationFacade")
	private AddressVerificationFacade addressVerificationFacade;

	@Resource(name = "i18NFacade")
	private I18NFacade i18NFacade;

	@Resource(name = "addressVerificationResultHandler")
	private AddressVerificationResultHandler addressVerificationResultHandler;

	@Resource(name = "guidedsellingDeliveryAddressValidator")
	private GuidedsellingDeliveryAddressValidator guidedsellingDeliveryAddressValidator;

	@Resource(name = "themeSource")
	private MessageSource themeSource;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public CustomerDeliveryInfo getCustomerDeliveryInfo()
	{
		final CustomerDeliveryInfo customerDeliveryInfo = new CustomerDeliveryInfo();
		customerDeliveryInfo.setCountries(checkoutFacade.getDeliveryCountries());
		customerDeliveryInfo.setTitles(userFacade.getTitles());
		customerDeliveryInfo.setAllDeliveryAddresses(userFacade.getAddressBook());
		customerDeliveryInfo.setDeliveryAddress(checkoutFacade.getCheckoutCart().getDeliveryAddress());

		final CustomerData customerData = getUser();
		customerDeliveryInfo.setFirstName(customerData.getFirstName());
		customerDeliveryInfo.setLastName(customerData.getLastName());

		final List<GuidedsellingSelectOption> deliveryModeOptions = getDeliveryModes();
		customerDeliveryInfo.setAllDeliveryModes(deliveryModeOptions);
		final DeliveryModeData cartDeliveryModeData = checkoutFacade.getCheckoutCart().getDeliveryMode();
		if (cartDeliveryModeData != null)
		{
			customerDeliveryInfo.setCartDeliveryModeCode(cartDeliveryModeData.getCode());
		}
		else if (CollectionUtils.isNotEmpty(deliveryModeOptions))
		{
			checkoutFacade.setDeliveryMode(deliveryModeOptions.get(0).getCode());
			customerDeliveryInfo.setCartDeliveryModeCode(deliveryModeOptions.get(0).getCode());
		}

		return customerDeliveryInfo;
	}

	protected List<GuidedsellingSelectOption> getDeliveryModes()
	{
		final List<? extends DeliveryModeData> deliveryModes = checkoutFacade.getSupportedDeliveryModes();
		final List<GuidedsellingSelectOption> deliveryModeOptions = new ArrayList<>();
		for (final DeliveryModeData deliveryModeData : deliveryModes)
		{
			final GuidedsellingSelectOption deliveryModeOption = new GuidedsellingSelectOption();
			deliveryModeOption.setCode(deliveryModeData.getCode());
			deliveryModeOption.setName(deliveryModeData.getName() + " - " + deliveryModeData.getDeliveryCost().getFormattedValue());
			deliveryModeOptions.add(deliveryModeOption);
		}
		return deliveryModeOptions;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String addDeliveryAddress(@RequestBody final AddressForm addressForm, final HttpServletRequest request,
			final BindingResult bindingResult, final HttpServletResponse response, final Model model,
			final RedirectAttributes redirectModel)
	{
		final AddressData addressData = mapToAddressData(addressForm);

		final AddressVerificationResult<AddressVerificationDecision> verificationResult = addressVerificationFacade
				.verifyAddressData(addressData);
		final boolean addressRequiresReview = addressVerificationResultHandler.handleResult(verificationResult, addressData, model,
				redirectModel, bindingResult, addressVerificationFacade.isCustomerAllowedToIgnoreAddressSuggestions(),
				"checkout.multi.address.added");
		if (addressRequiresReview)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			final String errorMessage = bindingResult.getFieldError().getCode();
			return themeSource.getMessage(errorMessage, null, "", getI18nService().getCurrentLocale());
		}

		addressData.setDefaultAddress(true);
		addressData.setShippingAddress(true);
		addressData.setVisibleInAddressBook(true);
		userFacade.addAddress(addressData);
		checkoutFacade.setDeliveryAddress(addressData);

		return null;
	}

	private AddressData mapToAddressData(final AddressForm addressForm)
	{
		final AddressData addressData = new AddressData();
		addressData.setTitleCode(addressForm.getTitleCode());
		addressData.setFirstName(addressForm.getFirstName());
		addressData.setLastName(addressForm.getLastName());
		addressData.setLine1(addressForm.getLine1());
		addressData.setLine2(addressForm.getLine2());
		addressData.setTown(addressForm.getTownCity());
		addressData.setPostalCode(addressForm.getPostcode());
		addressData.setBillingAddress(false);
		addressData.setShippingAddress(true);
		addressData.setVisibleInAddressBook(true);
		if (addressForm.getCountryIso() != null)
		{
			addressData.setCountry(i18NFacade.getCountryForIsocode(addressForm.getCountryIso()));
			if (addressForm.getRegionIso() != null)
			{
				addressData.setRegion(i18NFacade.getRegion(addressForm.getCountryIso(), addressForm.getRegionIso()));
			}
		}
		return addressData;
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Boolean selectDeliveryAddress(
			@RequestParam(value = "deliveryAddressId", required = true) final String deliveryAddressId)
	{
		final AddressData addressData = new AddressData();
		addressData.setId(deliveryAddressId);
		return checkoutFacade.setDeliveryAddress(addressData);
	}

	@RequestMapping(value = "/selectDeliveryMode", method = RequestMethod.GET)
	@ResponseBody
	public Boolean selectDeliveryMode(@RequestParam(value = "deliveryModeCode", required = true) final String deliveryModeCode)
	{
		return checkoutFacade.setDeliveryMode(deliveryModeCode);
	}

	@RequestMapping(value = "/selectRegions", method = RequestMethod.GET)
	@ResponseBody
	public List<RegionData> getRegions(@RequestParam(value = "isoCountryCode", required = true) final String isoCountryCode)
	{
		return i18NFacade.getRegionsForCountryIso(isoCountryCode);
	}
}
