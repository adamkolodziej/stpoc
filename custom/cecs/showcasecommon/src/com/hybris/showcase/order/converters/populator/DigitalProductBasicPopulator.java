package com.hybris.showcase.order.converters.populator;

import de.hybris.platform.commercefacades.product.converters.populator.AbstractProductPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * CECS-66 Product and AbstractOrder digital-delivery
 *
 * Created by mgolubovic on 30.1.2015..
 */
public class DigitalProductBasicPopulator <SOURCE extends ProductModel, TARGET extends ProductData> extends
        AbstractProductPopulator<SOURCE, TARGET>
{
    @Override
    public void populate(final SOURCE source, final TARGET target) throws ConversionException
    {
        target.setDigitalDelivery(source.getDigitalDelivery());
    }
}