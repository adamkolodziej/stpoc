package com.hybris.showcase.guidedselling.facades.impl;

import com.hybris.showcase.loyaltypoints.services.LoyaltyPointsService;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.configurablebundlefacades.order.impl.DefaultBundleCartFacade;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.CartService;
import org.springframework.beans.factory.annotation.Required;

public class GuidedSellingCartFacade extends DefaultBundleCartFacade {

    private CartService cartService;
    private LoyaltyPointsService loyaltyPointsService;

    @Override
    public CartModificationData updateCartEntry(final long entryNumber, final long quantity)
            throws CommerceCartModificationException
    {
        final CartModel cartModel = getCartService().getSessionCart();
        final CommerceCartParameter parameter = new CommerceCartParameter();
        parameter.setEnableHooks(true);
        parameter.setCart(cartModel);
        parameter.setEntryNumber(entryNumber);
        parameter.setQuantity(quantity);

        // THAT IS THE REAL CHANGE
        // *******************
        final ProductModel product = getCartService().getEntryForNumber(cartModel, (int) entryNumber).getProduct();
        parameter.setProduct(product);
        // *******************

        final CommerceCartModification modification = getCommerceCartService().updateQuantityForCartEntry(parameter);

        return getCartModificationConverter().convert(modification);
    }

    @Override
    public void removeSessionCart()
    {
        loyaltyPointsService.invalidateRemainingLoyaltyPointsCache();
        super.removeSessionCart();
    }

    public LoyaltyPointsService getLoyaltyPointsService() {
        return loyaltyPointsService;
    }

    @Required
    public void setLoyaltyPointsService(LoyaltyPointsService loyaltyPointsService) {
        this.loyaltyPointsService = loyaltyPointsService;
    }
}
