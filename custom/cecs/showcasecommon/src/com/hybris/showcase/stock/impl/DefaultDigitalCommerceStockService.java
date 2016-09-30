package com.hybris.showcase.stock.impl;

import de.hybris.platform.commerceservices.stock.impl.DefaultCommerceStockService;
import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.store.BaseStoreModel;

/**
 * CECS-66 Product and AbstractOrder digital-delivery
 *
 * Created by mgolubovic on 30.1.2015..
 */
public class DefaultDigitalCommerceStockService extends DefaultCommerceStockService
{
    @Override
    public StockLevelStatus getStockLevelStatusForProductAndBaseStore(final ProductModel product, final BaseStoreModel baseStore)
    {
        if (product.getDigitalDelivery().booleanValue())
        {
            return StockLevelStatus.INSTOCK;
        }

        return super.getStockLevelStatusForProductAndBaseStore(product, baseStore);
    }

    @Override
    public Long getStockLevelForProductAndBaseStore(final ProductModel product, final BaseStoreModel baseStore)
    {
        if (product.getDigitalDelivery().booleanValue())
        {
            return null;
        }
        return super.getStockLevelForProductAndBaseStore(product, baseStore);
    }
}
