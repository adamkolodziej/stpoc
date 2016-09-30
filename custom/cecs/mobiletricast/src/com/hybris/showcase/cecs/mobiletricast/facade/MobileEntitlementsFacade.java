package com.hybris.showcase.cecs.mobiletricast.facade;

import de.hybris.platform.core.model.user.CustomerModel;

import com.hybris.showcase.cecs.mobiletricast.data.GrantMobileEntitlementResultData;


public interface MobileEntitlementsFacade
{

	GrantMobileEntitlementResultData grantMobileEntitlement(String authToken);

	String generateToken();

	String generateToken(CustomerModel currentCustomer);
}
