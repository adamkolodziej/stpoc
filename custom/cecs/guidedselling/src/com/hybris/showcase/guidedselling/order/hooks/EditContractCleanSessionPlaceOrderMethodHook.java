package com.hybris.showcase.guidedselling.order.hooks;

import com.hybris.showcase.guidedselling.services.OrderChangesUnitOfWork;
import de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import org.springframework.beans.factory.annotation.Required;


/*
 * CECS-88 guidedselling: edit existing contract
 */
public class EditContractCleanSessionPlaceOrderMethodHook implements CommercePlaceOrderMethodHook
{
	private OrderChangesUnitOfWork orderChangesUnitOfWork;

	@Override
	public void afterPlaceOrder(final CommerceCheckoutParameter parameter, final CommerceOrderResult orderModel)
	{
		orderChangesUnitOfWork.cleanup();
	}

	@Override
	public void beforePlaceOrder(final CommerceCheckoutParameter parameter)
	{

	}

	@Override
	public void beforeSubmitOrder(final CommerceCheckoutParameter parameter, final CommerceOrderResult result)
	{

	}

	public OrderChangesUnitOfWork getOrderChangesUnitOfWork() {
		return orderChangesUnitOfWork;
	}

	@Required
	public void setOrderChangesUnitOfWork(OrderChangesUnitOfWork orderChangesUnitOfWork) {
		this.orderChangesUnitOfWork = orderChangesUnitOfWork;
	}
}
