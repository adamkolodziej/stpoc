package com.hybris.showcase.guidedselling.facades.impl;

import com.hybris.showcase.guidedselling.facades.ApprovalFacade;
import com.hybris.showcase.guidedselling.services.ApprovalService;
import de.hybris.platform.commercefacades.order.OrderFacade;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 07.10.2016.
 */
public class DefaultApprovalFacade implements ApprovalFacade {
    private ApprovalService approvalService;
    private OrderService orderService;
    private Converter<OrderModel, OrderData> orderConverter;

    @Override
    public void approveOrder(String orderCode) throws InvalidCartException {
        getApprovalService().approveQuote(orderCode);
    }

    @Override
    public boolean isCurrentUserApprover() {
        return approvalService.isCurrentUserApprover();
    }

    @Override
    public List<OrderData> getOrdersToApprove() {
        return Converters.convertAll(approvalService.getOrdersToApprove(), getOrderConverter());
    }

    protected ApprovalService getApprovalService() {
        return approvalService;
    }

    @Required
    public void setApprovalService(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    protected OrderService getOrderService() {
        return orderService;
    }

    @Required
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    protected Converter<OrderModel, OrderData> getOrderConverter()
    {
        return orderConverter;
    }

    @Required
    public void setOrderConverter(final Converter<OrderModel, OrderData> orderConverter)
    {
        this.orderConverter = orderConverter;
    }
}
