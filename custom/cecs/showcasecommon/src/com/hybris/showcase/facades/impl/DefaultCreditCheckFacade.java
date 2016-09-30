package com.hybris.showcase.facades.impl;

import com.hybris.showcase.facades.CreditCheckFacade;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by m.golubovic on 23.7.2015..
 */
public class DefaultCreditCheckFacade implements CreditCheckFacade
{
    private CartFacade cartFacade;
    private UserService userService;

    @Override
    public boolean checkCreditForCurrentCustomer()
    {
        final CustomerModel customerModel = (CustomerModel)userService.getCurrentUser();
        if(customerModel.getWallet() == null)
        {
            return false;
        }
        final Double customerMoney = customerModel.getWallet();
        final PriceData totalPriceData = cartFacade.getSessionCart().getTotalPrice();

        return (totalPriceData.getValue().doubleValue() < customerMoney.doubleValue());
    }

    public CartFacade getCartFacade() {
        return cartFacade;
    }

    @Required
    public void setCartFacade(CartFacade cartFacade) {
        this.cartFacade = cartFacade;
    }

    public UserService getUserService() {
        return userService;
    }

    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
