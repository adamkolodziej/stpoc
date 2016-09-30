package com.hybris.showcase.guidedselling.hooks;

import com.hybris.showcase.guidedselling.services.OrderChangesUnitOfWork;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.entitlementservices.data.EmsGrantData;
import de.hybris.platform.entitlementservices.exception.EntitlementFacadeException;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.entitlementservices.order.hook.EntitlementPlaceOrderMethodHook;
import de.hybris.platform.order.InvalidCartException;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collection;

/**
 * Created by m.golubovic on 16.7.2015..
 */
public class GuidedsellingEntitlementPlaceOrderMethodHook extends EntitlementPlaceOrderMethodHook
{
    private OrderChangesUnitOfWork orderChangesUnitOfWork;

    @Override
    protected CommerceOrderResult createGrants(final CommerceOrderResult commerceOrderResult) throws InvalidCartException
    {
        if (getOrderChangesUnitOfWork().isInEditMode())
        {
            return createGrantsOnlyForNewEntries(commerceOrderResult);
        }
        else
        {
            try {
                return super.createGrants(commerceOrderResult);
            }
            catch (Exception e){
                return null;
            }
        }
    }

    private CommerceOrderResult createGrantsOnlyForNewEntries(final CommerceOrderResult commerceOrderResult)
            throws InvalidCartException
    {
        // copy from DefaultEntitlementCheckoutService
        final OrderModel orderModel = commerceOrderResult.getOrder();
        final Collection<ProductModel> productsAdded = getOrderChangesUnitOfWork().getUnitOfWork().getProductsAdded();
        try
        {
            for (final AbstractOrderEntryModel entry : orderModel.getEntries())
            {
                // that's if statement is a modification to the original code from DefaultEntitlementCheckoutService
                if (!productsAdded.contains(entry.getProduct()))
                {
                    continue;
                }

                final ProductModel productModel = entry.getProduct();
                for (final ProductEntitlementModel productEntitlementModel : productModel.getProductEntitlements())
                {
                    final EmsGrantData emsGrantData = convert(productEntitlementModel);
                    emsGrantData.setUserId(orderModel.getUser().getUid());
                    emsGrantData.setOrderCode(orderModel.getCode());
                    emsGrantData.setCreatedAt(orderModel.getDate());
                    emsGrantData.setBaseStoreUid(getBaseStoreService().getCurrentBaseStore().getUid());
                    emsGrantData.setOrderEntryNumber(String.valueOf(entry.getEntryNumber()));
                    for (long index = 1; index <= entry.getQuantity().longValue(); index++)
                    {
                        getEntitlementFacadeDecorator().createEntitlement(emsGrantData);
                    }
                }
            }
        }
        catch (final EntitlementFacadeException e)
        {
            throw new InvalidCartException("Error creating entitlements for order", e);
        }
        return commerceOrderResult;
    }

    public OrderChangesUnitOfWork getOrderChangesUnitOfWork() {
        return orderChangesUnitOfWork;
    }

    @Required
    public void setOrderChangesUnitOfWork(OrderChangesUnitOfWork orderChangesUnitOfWork) {
        this.orderChangesUnitOfWork = orderChangesUnitOfWork;
    }
}
