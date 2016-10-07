package com.hybris.showcase.guidedselling.services.impl;

import com.hybris.showcase.guidedselling.services.QuoteService;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

/**
 * Created by admin on 05.10.2016.
 */
public class DefaultQuoteService implements QuoteService {

    private ModelService modelService;

    private OrderService orderService;

    @Override
    public OrderModel saveAsQuote(CartModel cart) throws InvalidCartException {
        Assert.notNull(cart);
        OrderModel order = getOrderService().createOrderFromCart(cart);
        order.setStatus(OrderStatus.PENDING_QUOTE);
        getModelService().save(order);
        getModelService().refresh(order);
        return order;
    }

    @Required
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    protected ModelService getModelService() {
        return modelService;
    }

    @Required
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    protected OrderService getOrderService() {
        return orderService;
    }
}
