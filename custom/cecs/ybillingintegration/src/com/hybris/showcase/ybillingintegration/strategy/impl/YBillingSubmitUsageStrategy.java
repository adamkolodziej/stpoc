/**
 *
 */
package com.hybris.showcase.ybillingintegration.strategy.impl;

import de.hybris.platform.subscriptionfacades.data.UsageChargeEntryData;

import com.hybris.showcase.emsextras.strategy.SubmitUsageStrategy;
import com.hybris.showcase.ybillingintegration.facades.ChargeableItemChargingFacade;


/**
 * @author I307113
 *
 */
public class YBillingSubmitUsageStrategy implements SubmitUsageStrategy
{
	private ChargeableItemChargingFacade chargeableItemChargingFacade;

	@Override
	public Boolean submitUsage(final String productCode, final String entitlementType, final int quantity,
			final UsageChargeEntryData charge)
	{
		return chargeableItemChargingFacade.registerItemCharge(productCode);
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
