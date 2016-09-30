/**
 *
 */
package com.hybris.showcase.ybillingintegration.customer.services.impl;

import com.hybris.showcase.facades.ServicesSubscriptionFacade;
import com.hybris.showcase.ybillingintegration.services.BusinessPartnerService;
import com.sap.hybris.sapcustomerb2c.outbound.DefaultB2CSapCustomerAccountService;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.apache.log4j.Logger;
import reactor.util.StringUtils;

/**
 * Togable implementation of Delegate pattern that allows one to disable yBillingIntegration through a boolean flag
 */
public class DelegatedCustomerAccountService extends DefaultB2CSapCustomerAccountService
{
	private static final Logger LOG = Logger.getLogger(DelegatedCustomerAccountService.class);

	DefaultB2CSapCustomerAccountService delegate;
	DefaultB2CSapCustomerAccountService defaultDelegate;

	String delegationEnabledPropertyKey;
	ConfigurationService configurationService;

	@Override
	public void saveAddressEntry(final CustomerModel customerModel, final AddressModel addressModel)
	{
		getDelegate().saveAddressEntry(customerModel, addressModel);
	}

	public DefaultB2CSapCustomerAccountService getDelegate() {
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

	public void setDelegate(DefaultB2CSapCustomerAccountService delegate) {
		this.delegate = delegate;
	}

	public void setDefaultDelegate(DefaultB2CSapCustomerAccountService defaultDelegate) {
		this.defaultDelegate = defaultDelegate;
	}

	public void setDelegationEnabledPropertyKey(String delegationEnabledPropertyKey) {
		this.delegationEnabledPropertyKey = delegationEnabledPropertyKey;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
}
