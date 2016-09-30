/**
 *
 */
package com.hybris.showcase.services.impl;


import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.dao.ContractDao;
import com.hybris.showcase.dao.ExpenseDao;
import com.hybris.showcase.model.ContractModel;
import com.hybris.showcase.model.ExpenseModel;
import com.hybris.showcase.services.ExpenseService;


/**
 * @author marius.bogdan.ionescu@sap.com, Adrian Sbarcea (SAP - I309441)
 */
public class DefaultExpenseService implements ExpenseService
{
	private static final Logger LOG = Logger.getLogger(DefaultExpenseService.class);

	private static final String ON_FIRST_BILL_TYPE = "On First Bill";

	@Resource
	private ExpenseDao expenseDao;

	@Resource
	private ContractDao contractDao;

	private UserService userService;


	TimeService timeService;

	@Override
	public ExpenseModel getExpenseForCode(final String code)
	{
		validateParameterNotNull(code, "Expense code cannot be null");

		return expenseDao.getExpenseForCode(code);
	}

	@Override
	public List<ExpenseModel> getExpensesForContract(final ContractModel contract, final Integer month, final Integer year)
	{

		return expenseDao.getExpensesForContract(contract, month, year);
	}

	@Override
	public List<ExpenseModel> getExpensesForContract(final Integer month, final Integer year)
	{
		final UserModel currentUser = userService.getCurrentUser();
		final ContractModel contract = contractDao.getLatestContractForUser(currentUser);
		if (contract == null)
		{
			return Collections.emptyList();
		}
		return expenseDao.getExpensesForContract(contract, month, year);
	}

	@Override
	public List<ExpenseModel> getExpensesForInvoice(final String invoiceNumber)
	{
		return expenseDao.getExpensesForInvoice(invoiceNumber);
	}

	@Override
	public SearchPageData<ExpenseModel> getExpensesForUser(final String orderBy, final PageableData pageableData)
	{

		final UserModel currentUser = userService.getCurrentUser();
		return expenseDao.getExpensesForUser(currentUser, orderBy, pageableData);
	}

	@Override
	public SearchPageData<ExpenseModel> getExpensesForMonth(final Integer month, final Integer year, final String orderBy,
			final PageableData pageableData)
	{
		validateParameterNotNull(month, "Month cannot be null");
		validateParameterNotNull(year, "Year cannot be null");

		final UserModel currentUser = userService.getCurrentUser();

		final ContractModel contract = contractDao.getLatestContractForUser(currentUser);
		if (contract == null)
		{
			return new SearchPageData<>();
		}

		return expenseDao.getExpensesForMonth(currentUser, contract, month, year, orderBy, pageableData);
	}

	@Override
	public List<ExpenseModel> getExpensesForMonth(final Integer month, final Integer year)
	{
		validateParameterNotNull(month, "Month cannot be null");
		validateParameterNotNull(year, "Year cannot be null");
		final UserModel currentUser = userService.getCurrentUser();

		return expenseDao.getExpensesForMonth(currentUser, month, year);
	}

	@Override
	public ExpenseModel persistExpense(final CustomerModel customer, final ContractModel contract, final String productCode,
			final Date date, final String type, final double amount, final String currencyIso, final ProductModel product)
	{
		return expenseDao.save(customer, contract, productCode, date, type, amount, currencyIso, product);
	}

	@Override
	public void createExpenses(final AbstractOrderModel order, final CustomerModel customer, final ContractModel contract)
	{
		createExpenses(order, customer, contract, null);
	}

	@Override
	public void createExpenses(final AbstractOrderModel order, final CustomerModel customer, final ContractModel contract,
			Date date)
	{
		if (date == null)
		{
			date = timeService.getCurrentTime();
		}

		if (checkIfAlreadyGeneratedExpensesForCurrentMonth(contract, date, order.getBillingTime().getNameInCart()))
		{
			return;
		}


		for (final AbstractOrderEntryModel orderEntry : order.getEntries())
		{
			final ProductModel product = orderEntry.getProduct();

			if (ON_FIRST_BILL_TYPE.equals(order.getBillingTime().getNameInCart()))
			{
				final List<ExpenseModel> expenses = expenseDao.getExpenses(ON_FIRST_BILL_TYPE, contract, product);
				if (expenses.size() > 0)
				{
					continue;
				}
			}

			if (orderEntry.getTotalPrice().doubleValue() > 0)
			{
				persistExpense(customer, contract, product.getName(), date, order.getBillingTime().getNameInCart(),
						orderEntry.getTotalPrice().doubleValue(), order.getCurrency().getIsocode(), product);
			}

		}

	}


	private boolean checkIfAlreadyGeneratedExpensesForCurrentMonth(final ContractModel contract, final Date date,
			final String type)
	{

		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		final int month = calendar.get(Calendar.MONTH);
		final int year = calendar.get(Calendar.YEAR);

		final List<ExpenseModel> expenses = expenseDao.getExpensesForContractByType(contract, month + 1, year, type);
		if (expenses != null && expenses.size() > 0)
		{
			return true;
		}
		return false;

	}

	@Override
	public void createExpensesForProduct(final CartModel cart, final CustomerModel customer, final ContractModel contract,
			final ProductModel addedProduct)
	{
		for (final AbstractOrderEntryModel orderEntry : cart.getEntries())
		{
			final ProductModel product = orderEntry.getProduct();

			if (product.getCode().equals(addedProduct.getCode()) && orderEntry.getTotalPrice().doubleValue() > 0)
			{
				persistExpense(customer, contract, product.getName(), timeService.getCurrentTime(),
						cart.getBillingTime().getNameInCart(), orderEntry.getTotalPrice().doubleValue(),
						cart.getCurrency().getIsocode(), product);
			}

		}

	}

	/**
	 *
	 */
	@Override
	public ExpenseModel getExpenseForExpenseDispute(final String expenseDisputeCode)
	{
		validateParameterNotNull(expenseDisputeCode, "Expense Dispute code cannot be null");

		return expenseDao.getExpenseForExpenseDispute(expenseDisputeCode);
	}


	@Override
	public void removeExpenses(final CustomerModel customer, final ProductModel product, final int month, final int year)
	{

		expenseDao.removeExpenses(customer, product, month, year);
	}


	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
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
