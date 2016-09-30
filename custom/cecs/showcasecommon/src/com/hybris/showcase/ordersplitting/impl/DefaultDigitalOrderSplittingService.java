package com.hybris.showcase.ordersplitting.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.ConsignmentCreationException;
import de.hybris.platform.ordersplitting.impl.DefaultOrderSplittingService;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * CECS-66 Product and AbstractOrder digital-delivery
 *
 * Created by mgolubovic on 30.1.2015..
 */
public class DefaultDigitalOrderSplittingService extends DefaultOrderSplittingService
{
    @Override
    public List<ConsignmentModel> splitOrderForConsignmentNotPersist(final AbstractOrderModel order,
            final List<AbstractOrderEntryModel> orderEntryList) throws ConsignmentCreationException
    {
        final List<AbstractOrderEntryModel> shipEntries = new ArrayList<>();
        for (final AbstractOrderEntryModel entry : orderEntryList)
        {
            if (isNotDigital(entry) && isNewProduct(order, entry))
            {
                shipEntries.add(entry);
            }
        }
        return super.splitOrderForConsignmentNotPersist(order, shipEntries);
    }

	private static boolean isNotDigital(final AbstractOrderEntryModel entry)
	{
		return !entry.getProduct().getDigitalDelivery().booleanValue();
	}

	private static boolean isNewProduct(final AbstractOrderModel order, final AbstractOrderEntryModel entry)
	{
		if (order.getOrderChanges() != null)
		{
			final Collection<ProductModel> productsAdded = order.getOrderChanges().getProductsAdded();
			return CollectionUtils.isNotEmpty(productsAdded) && productsAdded.contains(entry.getProduct());
		}
		else
		{
			return true;
		}
	}
}
