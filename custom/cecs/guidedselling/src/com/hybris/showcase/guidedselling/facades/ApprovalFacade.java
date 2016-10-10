package com.hybris.showcase.guidedselling.facades;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.order.InvalidCartException;

import java.util.List;

/**
 * Created by admin on 07.10.2016.
 */
public interface ApprovalFacade {
    void approveOrder(String orderCode) throws InvalidCartException;
    boolean isCurrentUserApprover();
    List<OrderData> getOrdersToApprove();
}
