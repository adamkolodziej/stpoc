package com.hybris.showcase.strategy.impl;

import com.hybris.showcase.strategy.DigitalDeliveryStrategy;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;

/**
 * CECS-66 Product and AbstractOrder digital-delivery
 *
 * Created by mgolubovic on 29.1.2015..
 */
public class SubscriptionDigitalDeliveryStrategy implements DigitalDeliveryStrategy
{
    @Override
    public Boolean isDigitalDelivery(ProductModel product)
    {
        if(product instanceof SubscriptionProductModel)
        {
            return Boolean.TRUE;
        }
        else
        {
            return null;
        }
    }
}
