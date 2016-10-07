package com.hybris.showcase.guidedselling.facades;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.InvalidCartException;

/**
 * Created by admin on 06.10.2016.
 */
public interface QuoteFacade {

    OrderData createQuote() throws InvalidCartException;
}
