/**
 *
 */
package com.hybris.showcase.services;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.hybris.showcase.model.ContractModel;
import com.hybris.showcase.model.InvoiceModel;


/**
 * @author Rafal Zymla
 *
 */
public interface InvoiceService
{


	List<InvoiceModel> getInvoices(CustomerModel customer);

	InvoiceModel getInvoice(String invoiceNumber);

	/**
	 * Creates invoice for {@code customer} based on {@code contract} on current date for previous month
	 *
	 * @param customer
	 * @param contract
	 */
	void createInvoiceForCustomer(CustomerModel customer, ContractModel contract);

	/**
	 * Creates invoice for {@code customer} based on {@code contract} on specified {@code date} for previous month
	 *
	 * @param customer
	 * @param contract
	 * @param date
	 */
	void createInvoiceForCustomer(CustomerModel customer, ContractModel contract, Date date);

	SearchPageData<InvoiceModel> getInvoices(CustomerModel customer, String orderBy, PageableData pageableData);

	public BigDecimal getInvoiceBalance(final CustomerModel customer);



}
