/**
 *
 */
package com.hybris.showcase.ybillingintegration.facades.impl;

import de.hybris.platform.entitlementservices.exception.EntitlementFacadeException;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;

import com.hybris.services.entitlements.api.GrantData;
import com.hybris.showcase.emsextras.facades.impl.DefaultGrantingEntitlementsFacade;
import com.hybris.showcase.ybillingintegration.facades.ChargeableItemChargingFacade;


/**
 * @author I307113
 */
public class YBillingGrantingEntitlementsFacade extends DefaultGrantingEntitlementsFacade
{
	private ChargeableItemChargingFacade chargeableItemChargingFacade;

	@Override
	public GrantData grantProductEntitlement(final String userId, final ProductEntitlementModel productEntitlementModel)
			throws EntitlementFacadeException
	{
		final GrantData grantProductEntitlement = super.grantProductEntitlement(userId, productEntitlementModel);

		chargeableItemChargingFacade.registerGrantedEntitlement(productEntitlementModel);

		return grantProductEntitlement;
	}



	public ChargeableItemChargingFacade getChargeableItemChargingFacade()
	{
		return chargeableItemChargingFacade;
	}


	public void setChargeableItemChargingFacade(final ChargeableItemChargingFacade chargeableItemChargingFacade)
	{
		this.chargeableItemChargingFacade = chargeableItemChargingFacade;
	}
}
