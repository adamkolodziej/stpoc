package com.hybris.showcase.dao;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.Date;
import java.util.List;

import com.hybris.showcase.model.ContractModel;
import com.hybris.showcase.model.ExpenseModel;


/**
 *
 * @author marius.bogdan.ionescu@sap.com
 *
 */
public interface ExpenseDao
{
	ExpenseModel getExpenseForCode(String code);

	SearchPageData<ExpenseModel> getExpensesForUser(UserModel user, final String orderBy, final PageableData pageableData);

	SearchPageData<ExpenseModel> getExpensesForMonth(final UserModel user, ContractModel contract, final Integer month,
			final Integer year, final String orderBy, final PageableData pageableData);

	ExpenseModel save(CustomerModel customer, ContractModel contract, String productCode, Date date, String type, double amount,
			String currencyIso, ProductModel product);

	List<ExpenseModel> getExpensesForMonth(UserModel currentUser, Integer month, Integer year);


	List<ExpenseModel> getExpenses(CustomerModel customer, ProductModel product, int month, int year);

	void removeExpenses(final CustomerModel customer, final ProductModel product, int month, int year);


	List<ExpenseModel> getExpensesForContract(ContractModel contract, Integer month, Integer year);

	List<ExpenseModel> getExpensesForInvoice(String invoiceNumber);

	List<ExpenseModel> getExpensesForContractByType(ContractModel contract, Integer month, Integer year, String type);


	List<ExpenseModel> getExpenses(String type, ContractModel contract, ProductModel product);

	/**
	 * @param expenseDisputeCode
	 * @return
	 */
	ExpenseModel getExpenseForExpenseDispute(String expenseDisputeCode);


}
