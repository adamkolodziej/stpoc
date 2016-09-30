package com.hybris.showcase.strategy.impl;

import com.hybris.showcase.strategy.DigitalDeliveryStrategy;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.product.ProductModel;

/**
 * Created by mgolubovic on 12.2.2015..
 */
public class DefaultDigitalDeliveryStrategy implements DigitalDeliveryStrategy
{
    private static final String digitalDeliveryMode = "digital-delivery";

    @Override
    public Boolean isDigitalDelivery(ProductModel product)
    {
        if (product.getDeliveryModes() != null)
        {
            for (final DeliveryModeModel deliveryMode : product.getDeliveryModes())
            {
                if (deliveryMode.getCode().equals(digitalDeliveryMode))
                {
                    return true;
                }
            }
        }

        return false;
    }
}
