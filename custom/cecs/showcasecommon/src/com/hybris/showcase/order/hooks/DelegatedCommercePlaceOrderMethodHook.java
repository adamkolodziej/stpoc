package com.hybris.showcase.order.hooks;

import de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.apache.log4j.Logger;

/**
 * This class allows to toggle between two delegates so that one can enforce the use of one delegate (the default) through a boolean flag
 */
public class DelegatedCommercePlaceOrderMethodHook implements CommercePlaceOrderMethodHook {

    CommercePlaceOrderMethodHook delegate;
    CommercePlaceOrderMethodHook defaultDelegate;

    String delegationEnabledPropertyKey;
    ConfigurationService configurationService;

    private static final Logger LOG = Logger.getLogger(DelegatedCommercePlaceOrderMethodHook.class);

    @Override
    public void afterPlaceOrder(CommerceCheckoutParameter parameter, CommerceOrderResult orderModel) throws InvalidCartException {
        getDelegate().afterPlaceOrder(parameter, orderModel);
    }

    @Override
    public void beforePlaceOrder(CommerceCheckoutParameter parameter) throws InvalidCartException {
        getDelegate().beforePlaceOrder(parameter);
    }

    @Override
    public void beforeSubmitOrder(CommerceCheckoutParameter parameter, CommerceOrderResult result) throws InvalidCartException {
        getDelegate().beforeSubmitOrder(parameter, result);
    }

    public CommercePlaceOrderMethodHook getDelegate() {
        if (isDelegationEnabled()) {
            LOG.debug(String.format("using delegate {}", delegate));
            return delegate;
        }

        LOG.debug(String.format("using default delegate {}", defaultDelegate));
        return defaultDelegate;
    }

    public boolean isDelegationEnabled(){
        return configurationService.getConfiguration().getBoolean(delegationEnabledPropertyKey, false);
    }

    public void setDelegate(CommercePlaceOrderMethodHook delegate) {

        this.delegate = delegate;
    }

    public void setDefaultDelegate(CommercePlaceOrderMethodHook defaultDelegate) {
        this.defaultDelegate = defaultDelegate;
    }

    public void setDelegationEnabledPropertyKey(String delegationEnabledPropertyKey) {
        this.delegationEnabledPropertyKey = delegationEnabledPropertyKey;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
