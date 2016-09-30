package com.hybris.showcase.cecs.ymarketingintegration.services;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;

/**
 * Created by miroslaw.szot@sap.com on 2016-02-05.
 */
public interface YConvertMockService {
    void onAddToCart(CartModel cart, UserModel customer, CommerceCartModification result);
    void onLogin(CartModel cart);
}
