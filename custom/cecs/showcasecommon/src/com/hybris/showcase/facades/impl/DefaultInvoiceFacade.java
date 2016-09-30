/**
 *
 */
package com.hybris.showcase.facades.impl;

import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.user.UserService;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.data.InvoiceData;
import com.hybris.showcase.facades.InvoiceFacade;
import com.hybris.showcase.model.InvoiceModel;
import com.hybris.showcase.services.InvoiceService;


/**
 * @author Rafal Zymla
 */
public class DefaultInvoiceFacade implements InvoiceFacade
{

	private InvoiceService invoiceService;
	private UserService userService;
	private Converter<InvoiceModel, InvoiceData> invoiceConverter;
	private CommonI18NService commonI18NService;
	private PriceDataFactory priceDataFactory;

	public List<InvoiceData> getInvoicesForCurrentUser()
	{
		final CustomerModel currentUser = (CustomerModel) userService.getCurrentUser();

		final List<InvoiceModel> invoices = invoiceService.getInvoices(currentUser);


		return Converters.convertAll(invoices, invoiceConverter);

	}

	@Override
	public SearchPageData<InvoiceData> getInvoicesForCurrentUser(final String orderBy, final PageableData pageableData)
	{
		final CustomerModel currentUser = (CustomerModel) userService.getCurrentUser();

		final SearchPageData<InvoiceModel> invoices = invoiceService.getInvoices(currentUser, orderBy, pageableData);


		return convertPageData(invoices, invoiceConverter);

	}


	@Override
	public InvoiceData getInvoiceDetails(final String invoiceNumber)
	{

		final InvoiceModel invoiceModel = invoiceService.getInvoice(invoiceNumber);
		return invoiceConverter.convert(invoiceModel);
	}

	@Override
	public PriceData getInvoiceBalanceForCurrentUser()
	{

		final CustomerModel currentUser = (CustomerModel) userService.getCurrentUser();
		final CurrencyModel currency = commonI18NService.getCurrentCurrency();
		final BigDecimal invoiceBalance = invoiceService.getInvoiceBalance(currentUser);

		final PriceData priceData = priceDataFactory.create(PriceDataType.BUY, invoiceBalance, currency);
		return priceData;
	}

	protected <S, T> SearchPageData<T> convertPageData(final SearchPageData<S> source, final Converter<S, T> converter)
	{
		final SearchPageData<T> result = new SearchPageData<T>();
		result.setPagination(source.getPagination());
		result.setSorts(source.getSorts());
		result.setResults(Converters.convertAll(source.getResults(), converter));
		return result;
	}


	/**
	 * @return the invoiceService
	 */
	public InvoiceService getInvoiceService()
	{
		return invoiceService;
	}

	/**
	 * @param invoiceService
	 *           the invoiceService to set
	 */
	public void setInvoiceService(final InvoiceService invoiceService)
	{
		this.invoiceService = invoiceService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @return the invoiceConverter
	 */
	public Converter<InvoiceModel, InvoiceData> getInvoiceConverter()
	{
		return invoiceConverter;
	}

	/**
	 * @param invoiceConverter
	 *           the invoiceConverter to set
	 */
	public void setInvoiceConverter(final Converter<InvoiceModel, InvoiceData> invoiceConverter)
	{
		this.invoiceConverter = invoiceConverter;
	}

	/**
	 * @return the commonI18NService
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	/**
	 * @return the priceDataFactory
	 */
	public PriceDataFactory getPriceDataFactory()
	{
		return priceDataFactory;
	}

	@Required
	public void setPriceDataFactory(final PriceDataFactory priceDataFactory)
	{
		this.priceDataFactory = priceDataFactory;
	}







}
