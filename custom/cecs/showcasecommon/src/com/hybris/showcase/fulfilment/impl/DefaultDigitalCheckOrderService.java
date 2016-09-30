package com.hybris.showcase.fulfilment.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.yacceleratorfulfilmentprocess.impl.DefaultCheckOrderService;

/**
 * CECS-66 Product and AbstractOrder digital-delivery
 *
 * Created by mgolubovic on 30.1.2015..
 */
public class DefaultDigitalCheckOrderService extends DefaultCheckOrderService
{
    @Override
    protected boolean checkDeliveryOptions(final OrderModel order)
    {
        final boolean ok = super.checkDeliveryOptions(order);

        final boolean reasonIsNoDeliveryAddress = order.getDeliveryAddress() == null;
        final boolean downloadOnly = order.getDownloadOnly().booleanValue();
        if (!ok && reasonIsNoDeliveryAddress && downloadOnly)
        {
            return true;
        }

        return ok;
    }
}
