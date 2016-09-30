/**
 *
 */
package com.hybris.showcase.emsextras.strategy;

import de.hybris.platform.subscriptionfacades.data.UsageChargeEntryData;

/**
 * @author I307113
 *
 */
public interface SubmitUsageStrategy
{
	Boolean submitUsage(String productCode, String entitlementType, int quantity, UsageChargeEntryData charge);
}
