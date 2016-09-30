package com.hybris.showcase.cecs.ymarketingintegration.order.hooks;

import com.hybris.showcase.cecs.ymarketingintegration.services.YConvertMockService;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.hook.CommerceAddToCartMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;
import org.springframework.beans.factory.annotation.Required;


/**
 * CECS-549 start mocked seewhy campaign<br/>
 * Created by miroslaw.szot@sap.com on 2015-09-03.
 */
public class YConvertMockAddToCartMethodHook implements CommerceAddToCartMethodHook
{
    private YConvertMockService yConvertMockService;

	@Override
	public void beforeAddToCart(CommerceCartParameter parameters) throws CommerceCartModificationException
	{

	}

	@Override
	public void afterAddToCart(CommerceCartParameter parameters, CommerceCartModification result)
			throws CommerceCartModificationException
	{
        final CartModel cart = parameters.getCart();
        final UserModel customer = cart.getUser();

        yConvertMockService.onAddToCart(cart, customer, result);
	}

    public YConvertMockService getyConvertMockService() {
        return yConvertMockService;
    }

    @Required
    public void setyConvertMockService(YConvertMockService yConvertMockService) {
        this.yConvertMockService = yConvertMockService;
    }
}
