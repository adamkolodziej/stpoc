package com.hybris.showcase.guidedselling.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.configurablebundlefacades.order.BundleCartFacade;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.subscriptionfacades.SubscriptionFacade;
import de.hybris.platform.subscriptionfacades.exceptions.SubscriptionFacadeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hybris.showcase.data.ContractData;
import com.hybris.showcase.facades.ContractFacade;
import com.hybris.showcase.guidedselling.data.BundleOfferData;
import com.hybris.showcase.guidedselling.data.ConfigMessage;
import com.hybris.showcase.guidedselling.data.Severity;
import com.hybris.showcase.guidedselling.facades.ContractOptionsFacade;
import com.hybris.showcase.guidedselling.facades.OnePageGuidedSellingFacade;
import com.hybris.showcase.guidedselling.facades.impl.BundleAcceleratorCheckoutFacade;


/**
 * Created by mgolubovic on 14.4.2015..
 */
@Controller
@RequestMapping("/guidedselling")
public class InstantEditContractController extends AbstractAddOnPageController
{
	private static final Logger LOG = Logger.getLogger(InstantEditContractController.class);

	@Resource(name = "guidedSellingFacade")
	private OnePageGuidedSellingFacade guidedSellingFacade;

	@Resource(name = "cartFacade")
	private BundleCartFacade cartFacade;

	@Resource(name = "contractFacade")
	private ContractFacade contractFacade;

	@Resource(name = "acceleratorCheckoutFacade")
	private BundleAcceleratorCheckoutFacade checkoutFacade;

	@Resource(name = "subscriptionFacade")
	private SubscriptionFacade subscriptionFacade;

	@Resource(name = "contractOptionsFacade")
	private ContractOptionsFacade contractOptionsFacade;

	@Resource(name = "creditCardPaymentInfoConverter")
	private Converter<CreditCardPaymentInfoModel, CCPaymentInfoData> creditCardPaymentInfoConverter;

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(value = "/instantEdit", method = RequestMethod.GET)
	public String editContract(final Model model, @RequestParam("productCode") final String productCode,
			@RequestParam("bundleTemplateId") final String bundleTemplateId, @RequestParam("bundleNo") final Integer bundleNo,
			@RequestParam("removeOthers") final Boolean removeOthers, final RedirectAttributes redirectAttributes)
			throws CMSItemNotFoundException
	{
		final ContractData contract = contractFacade.getLatestContract();
		if (contract != null)
		{
			cartFacade.removeSessionCart();
			final BundleOfferData offerData = guidedSellingFacade.loadOrder(contract.getCode());

			OrderData orderData = null;
			try
			{
				cartFacade.addToCart(productCode, 1, bundleNo, bundleTemplateId, removeOthers);

				checkoutFacade.authorizePayment("");

				contractOptionsFacade.initContractStartDate();

				orderData = checkoutFacade.placeOrder();
				addPaymentInfoToOrder(orderData);
				subscriptionFacade.createSubscriptions(orderData, new HashMap<String, String>());

				GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER, "guidedselling.order.placed",
						null);
				return REDIRECT_PREFIX + "/guidedselling/order/show/" + orderData.getCode();
			}
			catch (final CommerceCartModificationException e)
			{
				LOG.error("Unable to add product with code " + productCode + "to cart", e);
				GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER, e.getMessage(), null);
				addMessageForComponent(e.getMessage(), bundleTemplateId, offerData);
				return REDIRECT_PREFIX + "/guidedselling/order/show/" + contract.getCode();
			}
			catch (final InvalidCartException e)
			{
				LOG.error("Unable to place bundle order", e);
				GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER, e.getMessage(), null);
				addMessageForComponent(e.getMessage(), bundleTemplateId, offerData);
				return REDIRECT_PREFIX + "/guidedselling/order/show/" + contract.getCode();
			}
			catch (final SubscriptionFacadeException e)
			{
				LOG.error("Unable to create subscriptions for order", e);
				GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER, "guidedselling.order.placed",
						null);
				addMessageForComponent(e.getMessage(), bundleTemplateId, offerData);
				return REDIRECT_PREFIX + "/guidedselling/order/show/" + orderData.getCode();
			}
		}
		else
		{
			throw new CMSItemNotFoundException("No existing contract found.");
		}
	}

	private void addPaymentInfoToOrder(final OrderData orderData)
	{
		final CustomerModel currentCustomer = (CustomerModel) userService.getCurrentUser();
		if (currentCustomer.getDefaultPaymentInfo() != null)
		{
			final PaymentInfoModel paymentModel = currentCustomer.getDefaultPaymentInfo();
			if (paymentModel instanceof CreditCardPaymentInfoModel)
			{
				orderData.setPaymentInfo(creditCardPaymentInfoConverter.convert((CreditCardPaymentInfoModel) paymentModel));
			}
		}
	}

	private void addMessageForComponent(final String message, final String componentId, final BundleOfferData offerData)
	{
		final List<ConfigMessage> messages = new ArrayList<>();
		final ConfigMessage msg = new ConfigMessage();
		msg.setSeverity(Severity.ERROR);
		msg.setContent(message);
		messages.add(msg);
		guidedSellingFacade.addMessagesForComponent(messages, componentId, offerData);
	}
}
