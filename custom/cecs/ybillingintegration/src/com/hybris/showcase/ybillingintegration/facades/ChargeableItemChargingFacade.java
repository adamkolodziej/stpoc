/**
 *
 */
package com.hybris.showcase.ybillingintegration.facades;

import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;


/**
 * @author I307113
 */
public interface ChargeableItemChargingFacade
{
	boolean registerItemCharge(final String productCode);

	boolean registerGrantedEntitlement(final ProductEntitlementModel productEntitlement);
}
