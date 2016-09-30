/**
 *
 */
package com.hybris.showcase.services;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Date;
import java.util.List;

import com.hybris.showcase.model.ContractModel;
import com.hybris.showcase.model.ExpenseModel;


/**
 * @author marius.bogdan.ionescu@sap.com, Adrian Sbarcea (SAP - i309441)
 */
public interface ExpenseService
{
	ExpenseModel getExpenseForCode(final String code);

	SearchPageData<ExpenseModel> getExpensesForUser(final String orderBy, final PageableData pageableData);

	SearchPageData<ExpenseModel> getExpensesForMonth(final Integer month, final Integer year, final String orderBy,
			final PageableData pageableData);

	List<ExpenseModel> getExpensesForMonth(Integer month, Integer year);

	public ExpenseModel persistExpense(final CustomerModel customer, ContractModel contract, final String productCode,
			final Date date, final String type, final double amount, final String currencyIso, ProductModel product);

	/**
	 * Creates expenses for {@code customer} based on order for contract on current date
	 *
	 * @param cart
	 * @param customer
	 * @param contract
	 */
	void createExpenses(AbstractOrderModel cart, CustomerModel customer, ContractModel contract);

	void removeExpenses(CustomerModel customer, ProductModel product, int month, int year);


	void createExpensesForProduct(CartModel cart, CustomerModel customer, ContractModel contract, ProductModel addedProduct);


	List<ExpenseModel> getExpensesForContract(ContractModel contract, Integer month, Integer year);

	List<ExpenseModel> getExpensesForInvoice(String invoiceNumber);

	/**
	 * Creates expenses for {@code customer} based on order for contract on specified date
	 *
	 * @param order
	 * @param customer
	 * @param contract
	 * @param date
	 */
	void createExpenses(AbstractOrderModel order, CustomerModel customer, ContractModel contract, Date date);

	List<ExpenseModel> getExpensesForContract(Integer month, Integer year);

	public ExpenseModel getExpenseForExpenseDispute(String expenseDisputeCode);

}
