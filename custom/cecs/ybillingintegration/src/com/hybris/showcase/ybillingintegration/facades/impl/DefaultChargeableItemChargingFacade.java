/**
 *
 */
package com.hybris.showcase.ybillingintegration.facades.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.user.UserService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.ybillingintegration.facades.ChargeableItemChargingFacade;
import com.hybris.showcase.ybillingintegration.services.ChargeableItemChargingService;


public class DefaultChargeableItemChargingFacade implements ChargeableItemChargingFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultChargeableItemChargingFacade.class);
	private UserService userService;
	private ChargeableItemChargingService chargeableItemChargingService;
	private TimeService timeService;

	@Override
	public boolean registerItemCharge(final String productCode)
	{
		final CustomerModel customer = getCustomer();
		if (customer == null)
		{
			LOG.warn("Unable to find out current customer. skipping charging to yBilling");
			return false;
		}

		final String userID = customer.getYBillingCustomerId();

		if (StringUtils.isBlank(userID))
		{
			LOG.warn("Customer " + customer.getUid() + " is not linked to yBilling Business Partner, skipping");
			return false;
		}

		//yBillingCustomerId used as TechnicalAttributeId
		return chargeableItemChargingService.registerItemCharge(userID, productCode, timeService.getCurrentTime());
	}

	private CustomerModel getCustomer()
	{
		final UserModel currentUser = userService.getCurrentUser();
		if (currentUser instanceof CustomerModel)
		{
			return (CustomerModel) currentUser;
		}
		return null;
	}

	@Override
	public boolean registerGrantedEntitlement(final ProductEntitlementModel productEntitlement)
	{
		final CustomerModel customer = getCustomer();
		if (customer == null)
		{
			LOG.warn("Unable to find out current customer. skipping charging to yBilling");
			return false;
		}

		final String userID = customer.getYBillingCustomerId();

		if (StringUtils.isBlank(userID))
		{
			LOG.warn("Customer " + customer.getUid() + " is not linked to yBilling Business Partner, skipping");
			return false;
		}

		return chargeableItemChargingService.registerFreeProductEntitlement(userID,
				productEntitlement.getSubscriptionProduct().getCode(), timeService.getCurrentTime());
	}





	public ChargeableItemChargingService getChargeableItemChargingService()
	{
		return chargeableItemChargingService;
	}

	public void setChargeableItemChargingService(final ChargeableItemChargingService chargeableItemChargingService)
	{
		this.chargeableItemChargingService = chargeableItemChargingService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @return the timeService
	 */
	public TimeService getTimeService()
	{
		return timeService;
	}

	@Required
	public void setTimeService(final TimeService timeService)
	{
		this.timeService = timeService;
	}
}
