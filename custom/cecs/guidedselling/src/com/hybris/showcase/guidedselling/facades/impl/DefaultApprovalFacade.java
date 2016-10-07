package com.hybris.showcase.guidedselling.facades.impl;

import com.hybris.showcase.guidedselling.facades.ApprovalFacade;
import com.hybris.showcase.guidedselling.services.ApprovalService;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.OrderService;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * Created by admin on 07.10.2016.
 */
public class DefaultApprovalFacade implements ApprovalFacade {
    private ApprovalService approvalService;
    private OrderService orderService;

    @Override
    public void approveOrder(String orderCode) throws InvalidCartException {
        approvalService.approveQuote(orderCode);
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
}
