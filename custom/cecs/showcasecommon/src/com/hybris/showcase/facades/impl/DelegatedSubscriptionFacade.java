/**
 *
 */
package com.hybris.showcase.facades.impl;

import com.hybris.showcase.facades.ServicesSubscriptionFacade;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.subscriptionfacades.exceptions.SubscriptionFacadeException;
import de.hybris.platform.subscriptionfacades.impl.DefaultSubscriptionFacade;
import org.apache.log4j.Logger;

import java.util.Map;


public class DelegatedSubscriptionFacade extends DefaultSubscriptionFacade implements ServicesSubscriptionFacade {

    ServicesSubscriptionFacade delegate;
    ServicesSubscriptionFacade defaultDelegate;

    String delegationEnabledPropertyKey;
    ConfigurationService configurationService;

    private static final Logger LOG = Logger.getLogger(DelegatedSubscriptionFacade.class);

    @Override
    public CCPaymentInfoData createPaymentSubscription(final Map<String, String> paymentParameters)
            throws SubscriptionFacadeException {
        return getDelegate().createPaymentSubscription(paymentParameters);
    }

    @Override
    public CCPaymentInfoData changePaymentMethod(final CCPaymentInfoData paymentInfo, final String action, final boolean propagate,
                                                 final Map<String, String> parameters) throws SubscriptionFacadeException {
        return getDelegate().changePaymentMethod(paymentInfo, action, propagate, parameters);
    }

    @Override
    public boolean setEmailRemindersAndBillingEpaper(final Boolean isEmailEnabled, final Boolean isBillingEpaper) {
        return getDelegate().setEmailRemindersAndBillingEpaper(isEmailEnabled, isBillingEpaper);
    }

    public ServicesSubscriptionFacade getDelegate() {
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

    public void setDelegate(ServicesSubscriptionFacade delegate) {
        this.delegate = delegate;
    }

    public void setDefaultDelegate(ServicesSubscriptionFacade defaultDelegate) {
        this.defaultDelegate = defaultDelegate;
    }

    public void setDelegationEnabledPropertyKey(String delegationEnabledPropertyKey) {
        this.delegationEnabledPropertyKey = delegationEnabledPropertyKey;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
