package com.hybris.showcase.emsextras.facades;

import com.hybris.services.entitlements.api.ExecuteResult;
import de.hybris.platform.subscriptionfacades.data.UsageChargeData;
import de.hybris.platform.subscriptionfacades.data.UsageChargeEntryData;

/**
 * CECS-243 usage submit (to ems/brim) - charge customer
 * Created by miroslaw.szot@sap.com on 2015-06-02.
 */
public interface UsageChargeFacade {
    UsageChargeData getUsageChargeForGrant(String productCode, String entitlementType);

    UsageChargeData getUsageChargeDataForExecuteResult(ExecuteResult emsResult);

    UsageChargeData getUsageChargeDataForProductEntitlementId(String grantSource);

    UsageChargeEntryData getUsageChargeValue(ExecuteResult emsResult);

    boolean submitUsage(String productCode, String entitlementType, int quantity);
}
