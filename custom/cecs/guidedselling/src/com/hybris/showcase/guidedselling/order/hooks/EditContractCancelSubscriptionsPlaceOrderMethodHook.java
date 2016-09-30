package com.hybris.showcase.guidedselling.order.hooks;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.hybris.showcase.guidedselling.order.exceptions.PlaceOrderException;
import com.hybris.showcase.guidedselling.services.OrderChangesUnitOfWork;
import de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.subscriptionfacades.SubscriptionFacade;
import de.hybris.platform.subscriptionfacades.action.SubscriptionUpdateActionEnum;
import de.hybris.platform.subscriptionfacades.data.SubscriptionData;
import de.hybris.platform.subscriptionfacades.exceptions.SubscriptionFacadeException;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collection;
import java.util.Collections;


/*
 * CECS-88 guidedselling: edit existing contract
 */
public class EditContractCancelSubscriptionsPlaceOrderMethodHook implements CommercePlaceOrderMethodHook
{

	private SubscriptionFacade subscriptionFacade;
	private OrderChangesUnitOfWork orderChangesUnitOfWork;

	@Override
	public void afterPlaceOrder(final CommerceCheckoutParameter parameter, final CommerceOrderResult orderModel)
	{
		if (!orderChangesUnitOfWork.isInEditMode())
		{
			return;
		}
		cancelSubscriptions();
	}

	private void cancelSubscriptions()
	{
		try
		{
			OrderChanges orderChanges = orderChangesUnitOfWork.getUnitOfWork();

			if (CollectionUtils.isEmpty(orderChanges.getProductsRemoved()))
			{
				return;
			}

			final Collection<SubscriptionData> allSubscriptions = subscriptionFacade.getSubscriptions();

			for (final ProductModel product : orderChanges.getProductsRemoved())
			{
				if (product instanceof SubscriptionProductModel)
				{
					final SubscriptionProductModel subscriptionProduct = (SubscriptionProductModel) product;
					final SubscriptionData subscriptionData = getSubscriptionForProduct(subscriptionProduct, allSubscriptions);
					getSubscriptionFacade().updateSubscription(subscriptionData.getId(), true, SubscriptionUpdateActionEnum.CANCEL,
							Collections.<String, String> emptyMap());
				}
			}
		}
		catch (final SubscriptionFacadeException e)
		{
			throw new PlaceOrderException("Failed to update subscriptions.", e);
		}
	}

	private static SubscriptionData getSubscriptionForProduct(final SubscriptionProductModel subscriptionProduct,
			final Iterable<SubscriptionData> allSubscriptions)
	{
		return Iterables.find(allSubscriptions, new Predicate<SubscriptionData>()
		{
			@Override
			public boolean apply(final SubscriptionData subscriptionData)
			{
				return StringUtils.equals(subscriptionData.getProductCode(), subscriptionProduct.getCode());
			}
		});
	}

	@Override
	public void beforePlaceOrder(final CommerceCheckoutParameter parameter)
	{

	}

	@Override
	public void beforeSubmitOrder(final CommerceCheckoutParameter parameter, final CommerceOrderResult result)
	{

	}

	public OrderChangesUnitOfWork getOrderChangesUnitOfWork()
	{
		return orderChangesUnitOfWork;
	}

	@Required
	public void setOrderChangesUnitOfWork(OrderChangesUnitOfWork orderChangesUnitOfWork)
	{
		this.orderChangesUnitOfWork = orderChangesUnitOfWork;
	}

	protected SubscriptionFacade getSubscriptionFacade()
	{
		return subscriptionFacade;
	}

	@Required
	public void setSubscriptionFacade(final SubscriptionFacade subscriptionFacade)
	{
		this.subscriptionFacade = subscriptionFacade;
	}
}
