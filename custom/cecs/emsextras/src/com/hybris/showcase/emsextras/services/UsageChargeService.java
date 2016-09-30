package com.hybris.showcase.emsextras.services;

import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.subscriptionservices.model.UsageUnitModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionPricePlanModel;
import de.hybris.platform.subscriptionservices.model.UsageChargeModel;

/**
 * CECS-243 usage submit (to ems/brim) - charge customer
 * Created by miroslaw.szot@sap.com on 2015-06-02.
 */
public interface UsageChargeService {
    UsageChargeModel getUsageChargeForPricePlan(SubscriptionPricePlanModel pricePlan, UsageUnitModel unit);

    UsageChargeModel getUsageChargeForProductEntitlement(ProductEntitlementModel productEntitlementModel);
}
