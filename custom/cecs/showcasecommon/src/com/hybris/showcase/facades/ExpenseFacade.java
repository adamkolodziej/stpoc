/**
 *
 */
package com.hybris.showcase.facades;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import java.util.List;

import com.hybris.showcase.data.CurrentExpensesData;
import com.hybris.showcase.data.InvoiceData;


/**
 * @author marius.bogdan.ionescu@sap.com
 *
 */
public interface ExpenseFacade
{
	CurrentExpensesData getExpenseForCode(final String code);

	List<CurrentExpensesData> getExpensesForInvoice(final String invoiceNumber);

	SearchPageData<CurrentExpensesData> getExpensesForUser(final String orderBy, final PageableData pageableData);

	SearchPageData<CurrentExpensesData> getExpensesForMonth(final Integer month, final Integer year, final String orderBy,
			final PageableData pageableData);

	SearchPageData<CurrentExpensesData> getExpensesForCurrentMonth(final String orderBy, final PageableData pageableData);

	InvoiceData getCurrentPeriodExpenseBalance();
}
