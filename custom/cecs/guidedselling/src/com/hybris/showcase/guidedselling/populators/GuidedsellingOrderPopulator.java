package com.hybris.showcase.guidedselling.populators;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * Created by miroslaw.szot@sap.com on 2015-03-27.
 */
public class GuidedsellingOrderPopulator implements Populator<AbstractOrderModel, OrderData> {

    @Override
    public void populate(AbstractOrderModel source, OrderData target) throws ConversionException {
        if( source.getOrderChanges() != null ) {
            target.setOriginalOrderCode(source.getOrderChanges().getOrder().getCode());
        }
    }
}

