package com.hybris.showcase.guidedselling.facades.impl;

import com.hybris.showcase.guidedselling.facades.QuoteFacade;
import com.hybris.showcase.guidedselling.services.QuoteService;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commerceservices.strategies.CheckoutCustomerStrategy;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by admin on 06.10.2016.
 */
public class DefaultQuoteFacade implements QuoteFacade {

    private Converter<OrderModel, OrderData> orderConverter;
    private QuoteService quoteService;
    private CartFacade cartFacade;
    private CartService cartService;
    private CheckoutCustomerStrategy checkoutCustomerStrategy;

    protected boolean hasCheckoutCart()
    {
        return getCartFacade().hasSessionCart();
    }

    protected CartModel getCart()
    {
        if (hasCheckoutCart())
        {
            return getCartService().getSessionCart();
        }

        return null;
    }

    protected CustomerModel getCurrentUserForCheckout()
    {
        return getCheckoutCustomerStrategy().getCurrentUserForCheckout();
    }

    @Override
    public OrderData createQuote() throws InvalidCartException
    {
        final CartModel cartModel = getCart();
        if (cartModel != null)
        {
            final UserModel currentUser = getCurrentUserForCheckout();
            if (cartModel.getUser().equals(currentUser) || getCheckoutCustomerStrategy().isAnonymousCheckout())
            {
                final OrderModel orderModel = quoteService.saveAsQuote(cartModel);

                if (orderModel != null)
                {
                    return getOrderConverter().convert(orderModel);
                }
            }
        }

        return null;
    }

    protected Converter<OrderModel, OrderData> getOrderConverter()
    {
        return orderConverter;
    }

    @Required
    public void setOrderConverter(final Converter<OrderModel, OrderData> orderConverter)
    {
        this.orderConverter = orderConverter;
    }

    protected QuoteService getQuoteService() {
        return quoteService;
    }

    @Required
    public void setQuoteService(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @Required
    public void setCartFacade(CartFacade cartFacade) {
        this.cartFacade = cartFacade;
    }

    protected CartFacade getCartFacade() {
        return cartFacade;
    }

    @Required
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    protected CartService getCartService() {
        return cartService;
    }

    @Required
    public void setCheckoutCustomerStrategy(CheckoutCustomerStrategy checkoutCustomerStrategy) {
        this.checkoutCustomerStrategy = checkoutCustomerStrategy;
    }

    protected CheckoutCustomerStrategy getCheckoutCustomerStrategy() {
        return checkoutCustomerStrategy;
    }
}
