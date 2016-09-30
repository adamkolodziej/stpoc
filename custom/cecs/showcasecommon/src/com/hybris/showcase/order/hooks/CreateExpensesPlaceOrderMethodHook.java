/**
 *
 */
package com.hybris.showcase.order.hooks;

import de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.user.UserService;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.dao.ContractDao;
import com.hybris.showcase.model.ContractModel;
import com.hybris.showcase.services.ExpenseService;
import com.hybris.showcase.utils.ShowcaseDateUtils;


/**
 * @author Rafal Zymla
 *
 */
public class CreateExpensesPlaceOrderMethodHook implements CommercePlaceOrderMethodHook
{

	private ExpenseService expenseService;

	private UserService userService;

	private ContractDao contractDao;

	private TimeService timeService;

	@Override
	public void afterPlaceOrder(final CommerceCheckoutParameter parameter, final CommerceOrderResult commerceOrderResult)
			throws InvalidCartException
	{
		final CustomerModel customer = (CustomerModel) userService.getCurrentUser();
		final CartModel payNowCart = parameter.getCart();
		final ContractModel contract = contractDao.getContractForCode(commerceOrderResult.getOrder().getCode());

		if (payNowCart.getOrderChanges() != null)
		{
			//EDIT MODE
			for (final ProductModel addedProduct : payNowCart.getOrderChanges().getProductsAdded())
			{
				createExpensesFromCartsForProduct(customer, payNowCart, contract, addedProduct);

			}

			for (final ProductModel removedProduct : payNowCart.getOrderChanges().getProductsRemoved())
			{
				expenseService.removeExpenses(customer, removedProduct, ShowcaseDateUtils.getMonth(timeService.getCurrentTime()) + 1,
						ShowcaseDateUtils.getYear(timeService.getCurrentTime()));
			}

		}
		else
		{

			createExpensesFromCarts(customer, payNowCart, contract);

		}
	}


	private void createExpensesFromCarts(final CustomerModel customer, final CartModel payNowCart, final ContractModel contract)
	{
		createExpensesFromCartsForProduct(customer, payNowCart, contract, null);
	}

	private void createExpensesFromCartsForProduct(final CustomerModel customer, final CartModel payNowCart,
			final ContractModel contract, final ProductModel product)
	{
		//for carts except main cart which has paynow billing time
		for (final AbstractOrderModel child : payNowCart.getChildren())
		{
			if (child instanceof CartModel)
			{
				final CartModel cart = (CartModel) child;
				if (product != null)
				{
					expenseService.createExpensesForProduct(cart, customer, contract, product);
				}
				else
				{
					expenseService.createExpenses(cart, customer, contract);
				}
			}
		}
	}


	@Override
	public void beforePlaceOrder(final CommerceCheckoutParameter parameter) throws InvalidCartException
	{

		// no op
	}


	@Override
	public void beforeSubmitOrder(final CommerceCheckoutParameter parameter, final CommerceOrderResult result)
			throws InvalidCartException
	{
		// no op

	}


	@Required
	public void setExpenseService(final ExpenseService expenseService)
	{
		this.expenseService = expenseService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}


	@Required
	public void setContractDao(final ContractDao contractDao)
	{
		this.contractDao = contractDao;
	}

	public TimeService getTimeService()
	{
		return timeService;
	}


	@Required
	public void setTimeService(final TimeService timeService)
	{
		this.timeService = timeService;
	}




}
