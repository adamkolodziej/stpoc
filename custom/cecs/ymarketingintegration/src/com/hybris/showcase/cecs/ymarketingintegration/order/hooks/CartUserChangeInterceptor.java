package com.hybris.showcase.cecs.ymarketingintegration.order.hooks;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.cecs.ymarketingintegration.services.YConvertMockService;


/**
 * Created by miroslaw.szot@sap.com on 2016-02-05.
 */
public class CartUserChangeInterceptor implements PrepareInterceptor<CartModel>
{
	private YConvertMockService yConvertMockService;

	@Override
	public void onPrepare(CartModel cartModel, InterceptorContext ctx) throws InterceptorException
	{

		if (!ctx.isNew(cartModel) && ctx.isModified(cartModel, CartModel.USER))
		{
			yConvertMockService.onLogin(cartModel);
		}
	}

	public YConvertMockService getyConvertMockService()
	{
		return yConvertMockService;
	}

	@Required
	public void setyConvertMockService(YConvertMockService yConvertMockService)
	{
		this.yConvertMockService = yConvertMockService;
	}
}
