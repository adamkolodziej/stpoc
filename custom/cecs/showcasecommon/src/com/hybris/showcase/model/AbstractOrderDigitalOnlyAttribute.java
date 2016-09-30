package com.hybris.showcase.model;

import com.hybris.showcase.services.DigitalDeliveryService;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * CECS-66 Product and AbstractOrder digital-delivery
 *
 * Created by mgolubovic on 30.1.2015..
 */
public class AbstractOrderDigitalOnlyAttribute implements DynamicAttributeHandler<Boolean, AbstractOrderModel>
{
    private DigitalDeliveryService digitalDeliveryService;
    @Override
    public Boolean get(final AbstractOrderModel order)
    {
        final List<AbstractOrderEntryModel> orderEntries = order.getEntries();
        if(orderEntries == null)
        {
            return Boolean.TRUE;
        }

        for(AbstractOrderEntryModel entry : orderEntries)
        {
            boolean digitalDelivery = entry.getProduct().getDigitalDelivery().booleanValue();
            if(!digitalDelivery)
            {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public void set(AbstractOrderModel model, Boolean aBoolean)
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
