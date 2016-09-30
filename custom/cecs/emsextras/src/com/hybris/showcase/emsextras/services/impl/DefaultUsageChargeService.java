package com.hybris.showcase.emsextras.services.impl;

import com.hybris.showcase.emsextras.services.UsageChargeService;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.subscriptionservices.model.UsageUnitModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionPricePlanModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import de.hybris.platform.subscriptionservices.model.UsageChargeModel;
import de.hybris.platform.subscriptionservices.price.SubscriptionCommercePriceService;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collection;

/**
 * CECS-243 usage submit (to ems/brim) - charge customer
 * Created by miroslaw.szot@sap.com on 2015-06-02.
 */
public class DefaultUsageChargeService implements UsageChargeService {
    private SubscriptionCommercePriceService commercePriceService;

    @Override
    public UsageChargeModel getUsageChargeForPricePlan(SubscriptionPricePlanModel pricePlan, UsageUnitModel usageUnit) {
        final Collection<UsageChargeModel> usageCharges = pricePlan.getUsageCharges();
        for (UsageChargeModel usageCharge : usageCharges) {
            if(usageCharge.getUsageUnit().equals(usageUnit)) {
                return usageCharge;
            }
        }

        return null;
    }

    @Override
    public UsageChargeModel getUsageChargeForProductEntitlement(ProductEntitlementModel productEntitlementModel) {
        UsageUnitModel unit = productEntitlementModel.getBillingUsageUnit();

        if( unit == null ) {
            return null; // no billing unit
        }

        if( productEntitlementModel.getSubscriptionProduct() instanceof SubscriptionProductModel) {
            final SubscriptionPricePlanModel pricePlan = commercePriceService.getSubscriptionPricePlanForProduct((SubscriptionProductModel) productEntitlementModel.getSubscriptionProduct());
            return getUsageChargeForPricePlan(pricePlan, unit);
        } else {
            return null; // not a Subscription - Only SubscriptionProduct can define usage charges.
            // in 5.4 SubscriptionPricePlan is allowed only for SubscriptionProducts
        }
    }

    public SubscriptionCommercePriceService getCommercePriceService() {
        return commercePriceService;
    }

    @Required
    public void setCommercePriceService(SubscriptionCommercePriceService commercePriceService) {
        this.commercePriceService = commercePriceService;
    }

}
