package com.hybris.showcase.strategy;

import de.hybris.platform.core.model.product.ProductModel;

/**
 * CECS-66 Product and AbstractOrder digital-delivery
 *
 * Created by mgolubovic on 29.1.2015..
 */
public interface DigitalDeliveryStrategy
{
    Boolean isDigitalDelivery(ProductModel product);
}
