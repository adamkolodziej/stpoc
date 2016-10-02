package com.hybris.showcase.cecs.sptelstore;

import de.hybris.platform.commercefacades.order.converters.populator.AbstractOrderPopulator;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class SpTelOrderPopulator extends AbstractOrderPopulator<OrderModel, OrderData> {
    @Override
    public void populate(OrderModel orderModel, OrderData orderData) throws ConversionException {
        orderData.setDeliveryDate(orderModel.getDeliveryDate());
    }
}
