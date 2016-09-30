package com.hybris.showcase.guidedselling.services;

import com.hybris.showcase.guidedselling.order.hooks.OrderChanges;
import de.hybris.platform.core.model.order.OrderModel;

/**
 * CECS-88 guidedselling: edit existing contract
 * Created by miroslaw.szot@sap.com on 2015-03-26.
 */
public interface OrderChangesUnitOfWork {
    OrderChanges getUnitOfWork();
    boolean isInEditMode();

    void save(OrderChanges orderChanges);

    void cleanup();

    void enterEditingMode(OrderModel order);
}

