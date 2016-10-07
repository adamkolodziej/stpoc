package com.hybris.showcase.guidedselling.controllers.pages;

import com.hybris.showcase.facades.CreditCheckFacade;
import com.hybris.showcase.guidedselling.facades.QuoteFacade;
import com.hybris.showcase.guidedselling.services.QuoteService;
import com.hybris.showcase.guidedselling.controllers.GuidedsellingaddonControllerConstants;
import com.hybris.showcase.guidedselling.data.BundleOfferData;
import com.hybris.showcase.guidedselling.facades.OnePageGuidedSellingFacade;
import com.hybris.showcase.guidedselling.facades.impl.BundleAcceleratorCheckoutFacade;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.CheckoutStep;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.subscriptionfacades.SubscriptionFacade;
import de.hybris.platform.subscriptionfacades.exceptions.SubscriptionFacadeException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by miroslaw.szot@sap.com on 2015-03-16.
 */
@Controller
@RequestMapping("/guidedselling/order")
public class BundleOrderPageController extends AbstractAddOnPageController {
    private static final String ORDER_RECAP_PAGE = "order-recap";
    private static final Logger LOG = Logger.getLogger(BundleOrderPageController.class);

    @Resource(name = "guidedSellingFacade")
    private OnePageGuidedSellingFacade guidedSellingFacade;

    @Resource(name = "acceleratorCheckoutFacade")
    private BundleAcceleratorCheckoutFacade checkoutFacade;

    // CECS-220: Send product info to SBG on placing order
    @Resource(name = "subscriptionFacade")
    private SubscriptionFacade subscriptionFacade;

    // CECS-220: Send product info to SBG on placing order
    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "cartService")
    private CartService cartService;

    @Resource(name = "creditCheckFacade")
    private CreditCheckFacade creditCheckFacade;

    @Resource(name = "themeSource")
    private MessageSource themeSource;

    @Resource(name = "showOrderCheckoutStep")
    private CheckoutStep showOrderCheckoutStep;

    @Resource(name = "quoteFacade")
    private QuoteFacade quoteFacade;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String placeOrder(final Model model, @ModelAttribute final BundleOrderForm bundleOrderForm,
                             final RedirectAttributes redirectAttributes) {
        if (!creditCheckFacade.checkCreditForCurrentCustomer()) {
            final String paymentSelectionError = themeSource.getMessage(
                    "guidedselling.creditcheck.insufficientfunds", null,
                    "Insufficient funds!", getI18nService().getCurrentLocale());
            GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER, paymentSelectionError, null);
            return REDIRECT_PREFIX + "/guidedselling/" + bundleOrderForm.getRootBundleTemplateId() + "/package/"
                    + bundleOrderForm.getSourcePackageCode();
        }

        String orderPlacedFlashMessageKey = "guidedselling.order.placed";
        CartModel sessionCart = cartService.getSessionCart();
        if (sessionCart.getOrderChanges() != null) {
            orderPlacedFlashMessageKey = "guidedselling.order.updated";
        }
        setAddressesForCart(sessionCart);

        checkoutFacade.authorizePayment("");
        OrderData orderData = null;
        try {
            updateCartPackageInfo(bundleOrderForm);
//            orderData = checkoutFacade.placeOrder();
            orderData = quoteFacade.createQuote();
            // CECS-220: Send product info to SBG on placing order - START
            subscriptionFacade.createSubscriptions(orderData, new HashMap<String, String>());
            // CECS-220: Send product info to SBG on placing order - END

            GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER, orderPlacedFlashMessageKey, null);

            return REDIRECT_PREFIX + "/guidedselling/order/show/" + orderData.getCode();
        } catch (final InvalidCartException e) {
            LOG.error("Unable to place bundle order", e);
            GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER, e.getMessage(), null);

            if (StringUtils.isNotBlank(bundleOrderForm.getSourcePackageCode())) {
                return REDIRECT_PREFIX + "/guidedselling/" + bundleOrderForm.getRootBundleTemplateId() + "/package/"
                        + bundleOrderForm.getSourcePackageCode();
            } else {
                return REDIRECT_PREFIX + "/guidedselling/" + bundleOrderForm.getRootBundleTemplateId() + "/conditional/"
                        + bundleOrderForm.getSourceProductCode();
            }
        } catch (final SubscriptionFacadeException e) {
            // CECS-220: Send product info to SBG on placing order
            LOG.error("Unable to create subscriptions for order", e);

            GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER, orderPlacedFlashMessageKey, null);

            return REDIRECT_PREFIX + "/guidedselling/order/show/" + orderData.getCode();
        }
    }

    private void setAddressesForCart(CartModel sessionCart) {
        sessionCart.setDeliveryAddress(sessionCart.getUser().getDefaultShipmentAddress());
        sessionCart.setPaymentAddress(sessionCart.getUser().getDefaultPaymentAddress());
    }

    private void updateCartPackageInfo(BundleOrderForm bundleOrderForm) {
        CartModel sessionCart = cartService.getSessionCart();
        sessionCart.setSourcePackageCode(bundleOrderForm.getRootBundleTemplateId());
        sessionCart.setSourceProductCode(bundleOrderForm.getSourcePackageCode());
        cartService.setSessionCart(sessionCart);
    }

    @RequestMapping(value = "/show/{orderCode}", method = RequestMethod.GET)
    @RequireHardLogIn
    public String orderRecap(final Model model, @PathVariable("orderCode") final String orderCode) throws CMSItemNotFoundException {
        final ContentPageModel page = getContentPageForLabelOrId(ORDER_RECAP_PAGE);
        storeCmsPageInModel(model, page);
        setUpMetaDataForContentPage(model, page);

        final BundleOfferData offer = guidedSellingFacade.getBundleOffer(orderCode, 1);
        model.addAttribute("offer", offer);

        final Map<String, String> transitions = showOrderCheckoutStep.getTransitions();
        final Map<String, String> transitionsNew = new HashMap<>();
        for (final Map.Entry<String, String> entry : transitions.entrySet()) {
            if (entry.getKey().toString().equals("next")) {
                transitionsNew.put(entry.getKey(), "redirect:/guidedselling/order/show/" + orderCode);
            } else {
                transitionsNew.put(entry.getKey(), entry.getValue());
            }
        }
        showOrderCheckoutStep.setTransitions(transitionsNew);

        return GuidedsellingaddonControllerConstants.Views.Pages.Order.RecapPage;
    }

}
