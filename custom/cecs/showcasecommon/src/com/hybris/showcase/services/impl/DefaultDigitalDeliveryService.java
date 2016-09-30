package com.hybris.showcase.services.impl;

import com.hybris.showcase.services.DigitalDeliveryService;
import com.hybris.showcase.strategy.DigitalDeliveryStrategy;
import de.hybris.platform.core.model.product.ProductModel;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * CECS-66 Product and AbstractOrder digital-delivery
 *
 * Created by mgolubovic on 29.1.2015..
 */
public class DefaultDigitalDeliveryService implements DigitalDeliveryService
{
    private List<DigitalDeliveryStrategy> digitalDeliveryStrategies;

    @Override
    public boolean isDigitalDelivery(ProductModel product)
    {
        for(DigitalDeliveryStrategy strategy : digitalDeliveryStrategies)
        {
            final Boolean isDigitalDelivery = strategy.isDigitalDelivery(product);
            if(isDigitalDelivery != null)
            {
                return isDigitalDelivery.booleanValue();
            }
        }
        return false;
    }

    @Override
    public boolean isDownloadable(ProductModel product)
    {
        return false;
    }

    public List<DigitalDeliveryStrategy> getDigitalDeliveryStrategies() {
        return digitalDeliveryStrategies;
    }

    @Required
    public void setDigitalDeliveryStrategies(List<DigitalDeliveryStrategy> digitalDeliveryStrategies) {
        this.digitalDeliveryStrategies = digitalDeliveryStrategies;
    }
}
