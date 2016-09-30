package com.hybris.showcase.order.hooks;

import de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.order.InvalidCartException;
import org.apache.log4j.Logger;

/**
 * Implementation of Null pattern for CommercePlaceOrderMethodHook.
 * <p>
 * This is to be used when an instance is mandatory but NOOP is performed
 */
public class NullCommercePlaceOrderMethodHook implements CommercePlaceOrderMethodHook {
    private static final Logger LOG = Logger.getLogger(NullCommercePlaceOrderMethodHook.class);

    @Override
    public void afterPlaceOrder(CommerceCheckoutParameter parameter, CommerceOrderResult orderModel) throws InvalidCartException {
        LOG.debug("afterPlaceOrder NOOP");
    }

    @Override
    public void beforePlaceOrder(CommerceCheckoutParameter parameter) throws InvalidCartException {
        LOG.debug("beforePlaceOrder NOOP");
    }

    @Override
    public void beforeSubmitOrder(CommerceCheckoutParameter parameter, CommerceOrderResult result) throws InvalidCartException {
        LOG.debug("beforeSubmitOrder NOOP");
    }
}
