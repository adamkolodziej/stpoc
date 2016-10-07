package com.hybris.showcase.guidedselling.facades;

import de.hybris.platform.order.InvalidCartException;

/**
 * Created by admin on 07.10.2016.
 */
public interface ApprovalFacade {
    void approveOrder(String orderCode) throws InvalidCartException;
}
