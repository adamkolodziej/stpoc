/**
 *
 */
package com.hybris.showcase.dao.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.dao.InvoiceDao;
import com.hybris.showcase.model.InvoiceModel;


/**
 * @author Rafal Zymla
 *
 */
public class DefaultInvoiceDao implements InvoiceDao
{
	private static final String FIND_INVOICES_BY_CUSTOMER = " select {inv.pk} from {Invoice as inv} " + //
			"  where {inv.customer} = ?customer";

	public static final String FIND_INVOICES_BY_CUSTOMER_AND_MONTH = "" + //
			" select {e.pk} from {Invoice as e} " + //
			"  where {e.customer} = ?customer " + //
			" and MONTH({e.invoiceDate}) = ?month and YEAR({e.invoiceDate}) =?year";

	private PagedFlexibleSearchService pagedFlexibleSearchService;

	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<InvoiceModel> getInvoices(final CustomerModel customer)
	{
		final InvoiceModel example = new InvoiceModel();
		example.setCustomer(customer);
		return getFlexibleSearchService().getModelsByExample(example);

	}

	@Override
	public SearchPageData<InvoiceModel> getInvoices(final CustomerModel customer, final String orderBy,
			final PageableData pageableData)
	{

		validateParameterNotNull(customer, "Customer must not be null");


		final Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("customer", customer);

		return pagedFlexibleSearchService.search(FIND_INVOICES_BY_CUSTOMER, queryParams, pageableData);
	}

	@Override
	public List<InvoiceModel> getInvoices(final CustomerModel customer, final Integer month, final Integer year)
	{

		validateParameterNotNull(customer, "Customer must not be null");

		final Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("customer", customer);
		queryParams.put("month", month);
		queryParams.put("year", year);

		final SearchResult<InvoiceModel> searchResult = getFlexibleSearchService().search(FIND_INVOICES_BY_CUSTOMER_AND_MONTH,
				queryParams);

		return searchResult.getResult();

	}

	@Override
	public InvoiceModel getInvoice(final String invoiceNumber)
	{
		final InvoiceModel example = new InvoiceModel();
		example.setInvoiceNumber(invoiceNumber);
		return flexibleSearchService.getModelByExample(example);
	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	/**
	 * @return the pagedFlexibleSearchService
	 */
	public PagedFlexibleSearchService getPagedFlexibleSearchService()
	{
		return pagedFlexibleSearchService;
	}

	@Required
	public void setPagedFlexibleSearchService(final PagedFlexibleSearchService pagedFlexibleSearchService)
	{
		this.pagedFlexibleSearchService = pagedFlexibleSearchService;
	}



}
