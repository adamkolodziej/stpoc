/**
 *
 */
package com.hybris.showcase.ybillingintegration.facades.impl;

import de.hybris.platform.core.model.order.CartModel;

import com.hybris.showcase.ybillingintegration.facades.YBillingOrderFacade;
import com.hybris.showcase.ybillingintegration.services.YBillingOrderService;
import org.springframework.beans.factory.annotation.Required;


/**
 * @author Sebastian Weiner
 *
 */
public class DefaultYBillingOrderFacade implements YBillingOrderFacade
{

	private YBillingOrderService yBillingOrderService;



	@Override
	public String createOrder(final CartModel cartModel)
	{
		return getyBillingOrderService().createOrder(cartModel);
	}


	public YBillingOrderService getyBillingOrderService() {
		return yBillingOrderService;
	}

	@Required
	public void setyBillingOrderService(YBillingOrderService yBillingOrderService) {
		this.yBillingOrderService = yBillingOrderService;
	}
}
