package com.hybris.showcase.guidedselling.services;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.core.model.user.UserModel;

import java.util.List;

/**
 * Created by admin on 05.10.2016.
 */
public interface ApprovalService {
    OrderModel approveQuote(String orderCode) throws InvalidCartException;

    List<OrderModel> getOrdersToApprove();

    boolean isCurrentUserApprover();
}