/**
 *
 */
package com.hybris.showcase.dao;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

import com.hybris.showcase.model.InvoiceModel;


/**
 * @author Rafal Zymla
 *
 */
public interface InvoiceDao
{
	List<InvoiceModel> getInvoices(CustomerModel customer);

	InvoiceModel getInvoice(String invoiceNumber);


	SearchPageData<InvoiceModel> getInvoices(CustomerModel customer, String orderBy, PageableData pageableData);

	/**
	 * @param customer
	 * @param month
	 * @param year
	 * @return
	 */
	List<InvoiceModel> getInvoices(CustomerModel customer, Integer month, Integer year);
}
