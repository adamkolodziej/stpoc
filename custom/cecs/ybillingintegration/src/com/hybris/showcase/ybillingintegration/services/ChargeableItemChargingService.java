/**
 *
 */
package com.hybris.showcase.ybillingintegration.services;

import java.util.Date;


/**
 * @author I307113
 */
public interface ChargeableItemChargingService
{
	boolean registerFreeProductEntitlement(final String userTechnicalId, final String productCode,
			final Date freeProductEntitlementDate);

	boolean registerItemCharge(final String userTechnicalId, final String productCode, final Date consumptionDate);
}
