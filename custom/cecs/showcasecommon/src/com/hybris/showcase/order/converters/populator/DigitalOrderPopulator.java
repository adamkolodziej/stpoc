package com.hybris.showcase.order.converters.populator;

import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * CECS-66 Product and AbstractOrder digital-delivery
 *
 * Created by mgolubovic on 30.1.2015..
 */
public class DigitalOrderPopulator<SOURCE extends AbstractOrderModel, TARGET extends AbstractOrderData> implements
        Populator<SOURCE, TARGET>
{
    @Override
    public void populate(final SOURCE source, final TARGET target) throws ConversionException
    {
        target.setDownloadOnly(source.getDownloadOnly().booleanValue());
        target.setShipOnly(source.getShipOnly().booleanValue());
    }
}
