package com.hybris.showcase.model;

import com.hybris.showcase.services.DigitalDeliveryService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import org.springframework.beans.factory.annotation.Required;

/**
 * CECS-66 Product and AbstractOrder digital-delivery
 *
 * Created by mgolubovic on 29.1.2015..
 */
public class ProductDigitalDeliveryAttribute implements DynamicAttributeHandler<Boolean, ProductModel>
{
    private DigitalDeliveryService digitalDeliveryService;

    @Override
    public Boolean get(ProductModel model)
    {
        return digitalDeliveryService.isDigitalDelivery(model);
    }

    @Override
    public void set(ProductModel model, Boolean aBoolean)
    {
        throw new UnsupportedOperationException();
    }

    public DigitalDeliveryService getDigitalDeliveryService() {
        return digitalDeliveryService;
    }

    @Required
    public void setDigitalDeliveryService(DigitalDeliveryService digitalDeliveryService) {
        this.digitalDeliveryService = digitalDeliveryService;
    }
}
