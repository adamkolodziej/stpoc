package com.hybris.showcase.guidedselling.controllers.pages;

import static com.hybris.showcase.guidedselling.controllers.pages.GuidedsellingPagesController.FOCUSED_COMPONENT_ID;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.configurablebundlefacades.order.BundleCartFacade;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hybris.showcase.facades.CreditCheckFacade;
import com.hybris.showcase.guidedselling.controllers.GuidedsellingaddonControllerConstants;
import com.hybris.showcase.guidedselling.data.BundleOfferData;
import com.hybris.showcase.guidedselling.data.ConfigMessage;
import com.hybris.showcase.guidedselling.data.PaymentSelectionData;
import com.hybris.showcase.guidedselling.data.Severity;
import com.hybris.showcase.guidedselling.facades.ContractOptionsFacade;
import com.hybris.showcase.guidedselling.facades.OnePageGuidedSellingFacade;
import com.hybris.showcase.guidedselling.facades.impl.BundleAcceleratorCheckoutFacade;
import com.hybris.showcase.loyaltypoints.services.LoyaltyPointsService;


/**
 * Created by mgolubovic on 27.3.2015..
 */
@Controller
@RequestMapping("/guidedselling")
public class PaymentSelectionController extends AbstractAddOnPageController
{
	protected static final Logger LOG = Logger.getLogger(PaymentSelectionController.class);
	private static final String GUIDED_SELLING_PAGE = "guidedselling-summary";

	@Resource(name = "guidedSellingFacade")
	private OnePageGuidedSellingFacade guidedSellingFacade;

	@Resource(name = "themeSource")
	private MessageSource themeSource;

	@Resource(name = "cartFacade")
	private BundleCartFacade cartFacade;

	@Resource(name = "acceleratorCheckoutFacade")
	private BundleAcceleratorCheckoutFacade checkoutFacade;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "loyaltyPointsService")
	private LoyaltyPointsService loyaltyPointsService;

	@Resource(name = "creditCheckFacade")
	private CreditCheckFacade creditCheckFacade;

	@Resource(name = "contractOptionsFacade")
	private ContractOptionsFacade contractOptionsFacade;

	@RequestMapping(value = "/order/summary", method = RequestMethod.GET)
	@RequireHardLogIn
	public String bundleSummary(final Model model, final RedirectAttributes redirectAttributes, final HttpServletRequest request,
			final HttpSession session) throws CMSItemNotFoundException
	{
		if (!cartFacade.isCartValid())
		{
			LOG.error("Cart contains invalid component(s)");
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER, "guidedselling.order.invalid",
					null);
			final String referer = request.getHeader("Referer");
			return REDIRECT_PREFIX + referer;
		}
		// CECS-304: Order fulfillment fails with error - START
		addPaymentInfoToOrder();
		// CECS-304: Order fulfillment fails with error - END

		final ContentPageModel page = getContentPageForLabelOrId(GUIDED_SELLING_PAGE);
		storeCmsPageInModel(model, page);
		setUpMetaDataForContentPage(model, page);

		final BundleOfferData offer = guidedSellingFacade.getBundleOffer(1);
		model.addAttribute("offer", offer);

		if (loyaltyPointsService.isEligibleForLoyaltyPayment(offer))
		{
			model.addAttribute("eligibleForLoyaltyPayment", true);
			GlobalMessages.addInfoMessage(model, "guidedselling.order.EligibleForLoyaltyPayment");
		}

		final boolean creditCheckOkay = creditCheckFacade.checkCreditForCurrentCustomer();
		model.addAttribute("creditCheckOkay", creditCheckOkay);
		model.addAttribute("downloadOnly", cartFacade.getSessionCart().isDownloadOnly());
		final String focusedId = (String) session.getAttribute(FOCUSED_COMPONENT_ID + offer.getRootBundleTemplateId());
		if (StringUtils.isNotEmpty(focusedId))
		{
			model.addAttribute("isEditMode", true);
		}

		if (contractOptionsFacade.isStartDateSetForCart())
		{
			model.addAttribute("currentDate", contractOptionsFacade.getContractStartDate().getTime());
		}
		else
		{
			final Date contractStartDate = contractOptionsFacade.initContractStartDate();
			model.addAttribute("currentDate", contractStartDate.getTime());
		}

		return GuidedsellingaddonControllerConstants.Views.Pages.Order.BundleSummaryPage;
	}

	@RequestMapping(value = "/order/payment-selection", method = RequestMethod.POST)
	@ResponseBody
	public BundleOfferData paymentSelection(@RequestBody final PaymentSelectionData request)
	{
		final boolean success = guidedSellingFacade.selectLoyaltyPayment(request.getProductCode(), request.getOrderEntryNumber(),
				request.isIsLoyaltyPayment());

		final BundleOfferData offerData = guidedSellingFacade.getBundleOffer(request.getBundleNo());
		if (!success)
		{
			final ConfigMessage msg = new ConfigMessage();
			msg.setSeverity(Severity.ERROR);
			final String paymentSelectionError = themeSource.getMessage("guidedselling.not.enough.loyalty.points", null,
					"You don't have enough loyalty points!", getI18nService().getCurrentLocale());
			msg.setContent(paymentSelectionError);
			offerData.getMessages().add(msg);
		}

		return offerData;
	}

	// CECS-304: Order fulfillment fails with error
	private void addPaymentInfoToOrder()
	{
		final CustomerModel currentCustomer = (CustomerModel) userService.getCurrentUser();
		if (currentCustomer.getDefaultPaymentInfo() != null)
		{
			final PaymentInfoModel paymentModel = currentCustomer.getDefaultPaymentInfo();
			if (paymentModel instanceof CreditCardPaymentInfoModel)
			{
				checkoutFacade.setPaymentInfoIfAvailable();
				checkoutFacade.setDeliveryAddressIfAvailable();
				checkoutFacade.setDeliveryModeIfAvailable();
			}
		}
	}

}
