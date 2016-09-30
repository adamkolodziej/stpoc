/**
 *
 */
package com.hybris.showcase.facades;

import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import java.util.List;

import com.hybris.showcase.data.InvoiceData;


/**
 * @author Rafal Zymla
 *
 */
public interface InvoiceFacade
{

	public List<InvoiceData> getInvoicesForCurrentUser();

	public InvoiceData getInvoiceDetails(String invoiceNumber);


	SearchPageData<InvoiceData> getInvoicesForCurrentUser(String orderBy, PageableData pageableData);

	PriceData getInvoiceBalanceForCurrentUser();
}
