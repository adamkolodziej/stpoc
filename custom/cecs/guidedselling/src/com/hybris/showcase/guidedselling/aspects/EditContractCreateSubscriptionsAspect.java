package com.hybris.showcase.guidedselling.aspects;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.hybris.showcase.guidedselling.model.OrderChangesModel;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/*
 * CECS-88 guidedselling: edit existing contract
 */
@Aspect
public class EditContractCreateSubscriptionsAspect
{

	private CustomerAccountService customerAccountService;
	private UserService userService;
	private BaseStoreService baseStoreService;

	@Around(value = "execution(* de.hybris.platform.subscriptionfacades.SubscriptionFacade.createSubscriptions(..)) && args(order, parameters)", argNames = "pjp,order,parameters")
	public Object createSubscriptions(final ProceedingJoinPoint pjp, final OrderData order, final Map<String, String> parameters)
			throws Throwable
	{
		if (StringUtils.isBlank(order.getOriginalOrderCode()))
		{
			return pjp.proceed();
		}

		final List<OrderEntryData> originalEntries = order.getEntries();
		try
		{
			final List<OrderEntryData> onlyNewEntries = getNewEntries(order.getEntries(), order);
			order.setEntries(onlyNewEntries);
			return pjp.proceed();
		}
		finally
		{
			order.setEntries(originalEntries);
		}
	}

	private List<OrderEntryData> getNewEntries(final Iterable<OrderEntryData> entries, final OrderData order)
	{
		final CustomerModel currentUser = (CustomerModel) getUserService().getCurrentUser();
		final BaseStoreModel currentStore = getBaseStoreService().getCurrentBaseStore();
		final OrderModel orderModel = getCustomerAccountService().getOrderForCode(currentUser, order.getCode(), currentStore);
		final OrderChangesModel orderChanges = orderModel.getOrderChanges();
		if (CollectionUtils.isEmpty(orderChanges.getProductsAdded()))
		{
			return Collections.emptyList();
		}
		final Collection<String> addedProductsCodes = Collections2.transform(orderChanges.getProductsAdded(),
				new Function<ProductModel, String>()
				{
					@Nullable
					@Override
					public String apply(final ProductModel product)
					{
						return product.getCode();
					}
				});

		final List<OrderEntryData> newEntries = new ArrayList<>();
		for (final OrderEntryData entry : entries)
		{
			if (addedProductsCodes.contains(entry.getProduct().getCode()))
			{
				newEntries.add(entry);
			}
		}
		return newEntries;
	}

	protected CustomerAccountService getCustomerAccountService()
	{
		return customerAccountService;
	}

	@Required
	public void setCustomerAccountService(final CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	protected BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	@Required
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}
}
