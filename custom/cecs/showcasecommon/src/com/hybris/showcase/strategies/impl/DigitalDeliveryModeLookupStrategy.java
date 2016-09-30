package com.hybris.showcase.strategies.impl;

import de.hybris.platform.commerceservices.strategies.impl.DefaultDeliveryModeLookupStrategy;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.order.DeliveryModeService;
import org.springframework.beans.factory.annotation.Required;

import java.util.Arrays;
import java.util.List;

/**
 * CECS-66 Product and AbstractOrder digital-delivery
 *
 * Created by mgolubovic on 30.1.2015..
 */
public class DigitalDeliveryModeLookupStrategy extends DefaultDeliveryModeLookupStrategy
{
    private DeliveryModeService deliveryModeService;

    @Override
    public List<DeliveryModeModel> getSelectableDeliveryModesForOrder(final AbstractOrderModel abstractOrderModel)
    {
        if (abstractOrderModel.getDownloadOnly().booleanValue())
        {
            return Arrays.asList(getDigitalDeliveryMode());
        }

        return super.getSelectableDeliveryModesForOrder(abstractOrderModel);
    }

    protected DeliveryModeModel getDigitalDeliveryMode()
    {
        return getDeliveryModeService().getDeliveryModeForCode("digital-delivery");
    }

    public DeliveryModeService getDeliveryModeService()
    {
        return deliveryModeService;
    }

    @Required
    public void setDeliveryModeService(final DeliveryModeService deliveryModeService)
    {
        this.deliveryModeService = deliveryModeService;
    }
}
