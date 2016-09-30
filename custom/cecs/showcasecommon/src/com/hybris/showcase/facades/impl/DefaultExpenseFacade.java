/**
 *
 */
package com.hybris.showcase.facades.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.time.TimeService;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.hybris.showcase.data.CurrentExpensesData;
import com.hybris.showcase.data.InvoiceData;
import com.hybris.showcase.facades.ExpenseFacade;
import com.hybris.showcase.model.ExpenseModel;
import com.hybris.showcase.services.ExpenseService;


/**
 * @author marius.bogdan.ionescu@sap.com
 *
 */
public class DefaultExpenseFacade implements ExpenseFacade
{
	@Resource
	private ExpenseService expenseService;

	@Resource
	private TimeService timeService;

	@Resource
	private PriceDataFactory priceDataFactory;

	@Resource
	private CommonI18NService commonI18NService;

	@Resource
	private Converter<ExpenseModel, CurrentExpensesData> expenseConverter;

	@Override
	public CurrentExpensesData getExpenseForCode(final String code)
	{
		final ExpenseModel model = expenseService.getExpenseForCode(code);
		if (model == null)
		{
			return null;
		}
		return expenseConverter.convert(model);
	}

	@Override
	public List<CurrentExpensesData> getExpensesForInvoice(final String invoiceNumber)
	{
		final List<ExpenseModel> models = expenseService.getExpensesForInvoice(invoiceNumber);
		if (models == null)
		{
			return null;
		}
		return Converters.convertAll(models, expenseConverter);
	}

	@Override
	public SearchPageData<CurrentExpensesData> getExpensesForUser(final String orderBy, final PageableData pageableData)
	{
		return convertPageData(expenseService.getExpensesForUser(orderBy, pageableData), expenseConverter);
	}

	@Override
	public SearchPageData<CurrentExpensesData> getExpensesForMonth(final Integer month, final Integer year, final String orderBy,
			final PageableData pageableData)
	{
		validateParameterNotNull(month, "Month cannot be null");
		validateParameterNotNull(year, "Year cannot be null");

		return convertPageData(expenseService.getExpensesForMonth(month, year, orderBy, pageableData), expenseConverter);
	}

	@Override
	public SearchPageData<CurrentExpensesData> getExpensesForCurrentMonth(final String orderBy, final PageableData pageableData)
	{
		final Calendar c = Calendar.getInstance();
		c.setTime(timeService.getCurrentTime());

		return getExpensesForMonth(c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR), orderBy, pageableData);
	}

	@Override
	public InvoiceData getCurrentPeriodExpenseBalance()
	{
		final Calendar c = Calendar.getInstance();
		c.setTime(timeService.getCurrentTime());
		Double totalExpensesForMonth = 0.0;

		final CurrencyModel currency = commonI18NService.getCurrentCurrency();

		final List<ExpenseModel> currentExpenses = expenseService.getExpensesForContract(c.get(Calendar.MONTH) + 1,
				c.get(Calendar.YEAR));

		for (final ExpenseModel expense : currentExpenses)
		{
			if (!expense.getCurrencyIso().equals(currency.getIsocode()))
			{
				final CurrencyModel sourceCurrency = commonI18NService.getCurrency(expense.getCurrencyIso());
				final double v = commonI18NService.convertCurrency(sourceCurrency.getConversion(), currency.getConversion(),
						expense.getAmount());
				totalExpensesForMonth += v;
			}
			else
			{
				totalExpensesForMonth += expense.getAmount();
			}
		}

		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

		final InvoiceData totalBilling = new InvoiceData();
		totalBilling.setInvoiceDate(c.getTime());
		totalBilling.setAmountDue(priceDataFactory.create(PriceDataType.BUY, new BigDecimal(totalExpensesForMonth), currency));
		return totalBilling;
	}


	protected <S, T> SearchPageData<T> convertPageData(final SearchPageData<S> source, final Converter<S, T> converter)
	{
		final SearchPageData<T> result = new SearchPageData<T>();
		result.setPagination(source.getPagination());
		result.setSorts(source.getSorts());
		result.setResults(Converters.convertAll(source.getResults(), converter));
		return result;
	}

}
