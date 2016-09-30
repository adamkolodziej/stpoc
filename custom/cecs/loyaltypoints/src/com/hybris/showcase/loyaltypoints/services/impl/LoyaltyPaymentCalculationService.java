package com.hybris.showcase.loyaltypoints.services.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.order.impl.DefaultCalculationService;

/**
 * Created by mgolubovic on 1.4.2015..
 */
public class LoyaltyPaymentCalculationService extends DefaultCalculationService
{
    @Override
    public void calculateEntries(final AbstractOrderModel order, final boolean forceRecalculate) throws CalculationException
    {
        super.calculateEntries(order,forceRecalculate);

        double subtotalLoyaltyPoints = 0.0;
        for (final AbstractOrderEntryModel e : order.getEntries())
        {
            subtotalLoyaltyPoints += e.getLoyaltyPoints().doubleValue();
        }
        order.setTotalLoyaltyPoints(Double.valueOf(subtotalLoyaltyPoints));
    }
}
