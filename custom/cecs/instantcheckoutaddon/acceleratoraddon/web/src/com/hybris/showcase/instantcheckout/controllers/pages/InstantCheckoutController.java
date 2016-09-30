/**
 *
 */
package com.hybris.showcase.instantcheckout.controllers.pages;

import static com.hybris.showcase.instantcheckout.enums.InstantCheckoutStatus.AUTH_REQUIRED;
import static com.hybris.showcase.instantcheckout.enums.InstantCheckoutStatus.FAIL;
import static com.hybris.showcase.instantcheckout.enums.InstantCheckoutStatus.MISSING_DELIVERY_INFO;
import static com.hybris.showcase.instantcheckout.enums.InstantCheckoutStatus.MISSING_PAYMENT_INFO;
import static com.hybris.showcase.instantcheckout.enums.InstantCheckoutStatus.SUCCESS;

import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.servicelayer.user.UserService;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpHeaders;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hybris.showcase.instantcheckout.constants.InstantcheckoutaddonWebConstants;
import com.hybris.showcase.instantcheckout.facades.InstantCheckoutFacade;


/**
 * @author mgolubovic
 *
 */
@Controller
@RequestMapping("/instantcheckout")
public class InstantCheckoutController extends AbstractAddOnPageController
{
	private static final Logger LOG = Logger.getLogger(InstantCheckoutController.class);
	private static final String URL_CHOOSE_DELIVERY_METHOD = "/my-account/address-book";
	private static final String URL_ADD_PAYMENT_METHOD = "/my-account/payment-details";
	private static final String URL_ORDER_DETAILS = "/my-account/order/";
	private static final String URL_LOGIN = "/login";
	private static final String URL_PRODUCT_PAGE = "/p/";

	@Autowired
	private InstantCheckoutFacade instantCheckoutFacade;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/single/{productCode}/ajax", method = RequestMethod.GET)
	public String instantCheckoutAjax(@PathVariable("productCode") final String productCode, final Model model,
			@RequestParam("loyaltyPayment") Boolean loyaltyPayment)
	{
		if (userService.isAnonymousUser(userService.getCurrentUser()))
		{
			model.addAttribute("status", AUTH_REQUIRED);
			model.addAttribute("link", URL_LOGIN);
			return InstantcheckoutaddonWebConstants.InstantCheckoutFragment;
		}

		try
		{
			if (loyaltyPayment == null)
			{
				loyaltyPayment = Boolean.FALSE;
			}
			final CartModificationData modification = instantCheckoutFacade.purchase(productCode, loyaltyPayment);

			final String statusCode = modification.getStatusCode();

			model.addAttribute("status", statusCode);
			model.addAttribute("modification", modification);
			switch (statusCode)
			{
				case MISSING_DELIVERY_INFO:
					model.addAttribute("link", URL_CHOOSE_DELIVERY_METHOD);
					break;
				case MISSING_PAYMENT_INFO:
					model.addAttribute("link", URL_ADD_PAYMENT_METHOD);
					break;
				case FAIL:
					model.addAttribute("link", URL_PRODUCT_PAGE + productCode);
					model.addAttribute("status", "Unable to create/modify cart");
					break;
				case SUCCESS:
					model.addAttribute("link", URL_ORDER_DETAILS + modification.getEntry().getOriginalOrderCode());
					break;
			}
		}
		catch (final CommerceCartModificationException e)
		{
			LOG.error("unable to complete request", e);
			model.addAttribute("status", e.getMessage());
		}

		return InstantcheckoutaddonWebConstants.InstantCheckoutFragment;
	}

	@RequestMapping(value = "/single/{productCode}", method = RequestMethod.GET)
	public String instantCheckout(@PathVariable("productCode") final String productCode, final Model model,
			@RequestParam(value = "loyaltyPayment", required = false) Boolean loyaltyPayment, final HttpServletRequest request)
	{
		if (userService.isAnonymousUser(userService.getCurrentUser()))
		{
			return REDIRECT_PREFIX + URL_LOGIN;
		}

		try
		{
			if (loyaltyPayment == null)
			{
				loyaltyPayment = Boolean.FALSE;
			}
			final CartModificationData modification = instantCheckoutFacade.purchase(productCode, loyaltyPayment);
			final String statusCode = modification.getStatusCode();

			switch (statusCode)
			{
				case MISSING_DELIVERY_INFO:
					return REDIRECT_PREFIX + URL_CHOOSE_DELIVERY_METHOD;
				case MISSING_PAYMENT_INFO:
					return REDIRECT_PREFIX + URL_ADD_PAYMENT_METHOD;
				case FAIL:
					request.setAttribute("message", "Unable to create/modify cart");
					return FORWARD_PREFIX + "/500";
				case SUCCESS:
					return REDIRECT_PREFIX + request.getHeader(HttpHeaders.REFERER);
			}
		}
		catch (final CommerceCartModificationException ex)
		{
			LOG.error("unable to complete request", ex);
			request.setAttribute("message", ex.getMessage());
		}
		return FORWARD_PREFIX + "/500";
	}
}
