package com.hybris.showcase.guidedselling.services;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.InvalidCartException;

/**
 * Created by admin on 05.10.2016.
 */
public interface QuoteService {
    OrderModel saveAsQuote(CartModel cart) throws InvalidCartException;
}
