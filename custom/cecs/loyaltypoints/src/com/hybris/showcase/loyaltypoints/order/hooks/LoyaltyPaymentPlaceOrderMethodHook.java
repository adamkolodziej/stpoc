package com.hybris.showcase.loyaltypoints.order.hooks;

import com.hybris.showcase.loyaltypoints.services.LoyaltyPointsService;
import de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by mgolubovic on 6.4.2015..
 */
public class LoyaltyPaymentPlaceOrderMethodHook implements CommercePlaceOrderMethodHook
{
    private LoyaltyPointsService loyaltyPointsService;

    @Override
    public void afterPlaceOrder(CommerceCheckoutParameter commerceCheckoutParameter, CommerceOrderResult commerceOrderResult)
    {
        final OrderModel orderModel = commerceOrderResult.getOrder();
        boolean isLoyaltyPayment = false;
        for(final AbstractOrderEntryModel orderEntryModel : orderModel.getEntries())
        {
            if(orderEntryModel.isLoyaltyPayment())
            {
                isLoyaltyPayment = true;
                break;
            }
        }

        if(isLoyaltyPayment)
        {
            loyaltyPointsService.useLoyaltyPointsOnEms();
        }
        loyaltyPointsService.invalidateRemainingLoyaltyPointsCache();
    }

    @Override
    public void beforePlaceOrder(CommerceCheckoutParameter commerceCheckoutParameter) {
        // no op
    }

    @Override
    public void beforeSubmitOrder(CommerceCheckoutParameter commerceCheckoutParameter, CommerceOrderResult commerceOrderResult) {
        // no op
    }

    public LoyaltyPointsService getLoyaltyPointsService() {
        return loyaltyPointsService;
    }

    @Required
    public void setLoyaltyPointsService(LoyaltyPointsService loyaltyPointsService) {
        this.loyaltyPointsService = loyaltyPointsService;
    }
}
