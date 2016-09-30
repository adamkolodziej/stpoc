package com.hybris.showcase.order.populators;

import de.hybris.platform.commercefacades.order.converters.populator.DeliveryOrderEntryGroupPopulator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;

/**
 * CECS-66 Product and AbstractOrder digital-delivery
 *
 * Created by mgolubovic on 30.1.2015..
 */
public class DeliveryDigitalOrderEntryGroupPopulator extends DeliveryOrderEntryGroupPopulator
{
    @Override
    public long sumDeliveryItemsQuantity(final AbstractOrderModel source)
    {
        long sum = 0;
        for (final AbstractOrderEntryModel entryModel : source.getEntries())
        {
            if (entryModel.getDeliveryPointOfService() == null && !entryModel.getProduct().getDigitalDelivery().booleanValue())
            {
                sum += entryModel.getQuantity().longValue();
            }
        }
        return sum;
    }
}
