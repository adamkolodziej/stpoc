package com.hybris.showcase.order.converters.populator;

import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * CECS-66 Product and AbstractOrder digital-delivery
 *
 * Created by mgolubovic on 30.1.2015..
 */
public class DigitalOrderEntryPopulator implements Populator<AbstractOrderEntryModel, OrderEntryData>
{
    @Override
    public void populate(AbstractOrderEntryModel abstractOrderEntryModel, OrderEntryData orderEntryData) throws ConversionException
    {
        orderEntryData.setDigitalDelivery(abstractOrderEntryModel.getProduct().getDigitalDelivery().booleanValue());
    }
}
