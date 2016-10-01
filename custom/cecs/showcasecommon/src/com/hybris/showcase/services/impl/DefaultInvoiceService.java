/**
 *
 */
package com.hybris.showcase.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.time.TimeService;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.fest.util.Collections;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.dao.InvoiceDao;
import com.hybris.showcase.model.ContractModel;
import com.hybris.showcase.model.ExpenseModel;
import com.hybris.showcase.model.InvoiceModel;
import com.hybris.showcase.services.ExpenseService;
import com.hybris.showcase.services.InvoiceService;


/**
 * @author Rafal Zymla
 *
 */
public class DefaultInvoiceService implements InvoiceService
{

	private static final Logger LOG = Logger.getLogger(DefaultInvoiceService.class);

	private InvoiceDao invoiceDao;

	private ModelService modelService;

	private ExpenseService expenseService;

	private KeyGenerator invoiceNumberGenerator;

	private TimeService timeService;

	private CommonI18NService commonI18NService;


	@Override
	public List<InvoiceModel> getInvoices(final CustomerModel customer)
	{
		validateParameterNotNull(customer, "Customer cannot be null");
		return invoiceDao.getInvoices(customer);
	}

	@Override
	public SearchPageData<InvoiceModel> getInvoices(final CustomerModel customer, final String orderBy,
			final PageableData pageableData)
	{
		validateParameterNotNull(customer, "Customer cannot be null");
		return invoiceDao.getInvoices(customer, orderBy, pageableData);
	}

	@Override
	public void createInvoiceForCustomer(final CustomerModel customer, final ContractModel contract)
	{
		createInvoiceForCustomer(customer, contract, null);
	}


	@Override
	public void createInvoiceForCustomer(final CustomerModel customer, final ContractModel contract, Date date)
	{
		validateParameterNotNull(contract, "Contract must not be null");
		validateParameterNotNull(customer, "Customer must not be null");


		if (date == null)
		{
			date = timeService.getCurrentTime();
		}
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);

		if (checkIfInvoiceAlreadyGeneratedForCurrentMonth(customer, month, year))
		{
			return;
		}


		if (month == 0)
		{
			month = 12;
			year = year - 1;
		}

		//expenses for previous month
		final List<ExpenseModel> expenses = expenseService.getExpensesForContract(contract, Integer.valueOf(month),
				Integer.valueOf(year));

		if (!Collections.isEmpty(expenses))
		{

			final InvoiceModel invoice = createInvoice(customer, date, month, year, expenses);
			modelService.save(invoice);
		}


	}

	private InvoiceModel createInvoice(final CustomerModel customer, final Date date, final int month, final int year,
			final List<ExpenseModel> expenses)
	{
		final InvoiceModel invoice = modelService.create(InvoiceModel.class);
		invoice.setInvoiceDate(date);
		invoice.setCurrency(customer.getSessionCurrency());
		invoice.setCustomer(customer);
		invoice.setInvoiceNumber(invoiceNumberGenerator.generate().toString());

		invoice.setExpenses(expenses);
		final Double total = Double.valueOf(calculateTotalAmount(expenses));
		invoice.setAmountDue(total);
		invoice.setAmountRemaining(total);
		invoice.setAmountPaid(Double.valueOf(0d));
		invoice.setBillingPeriodStart(getFirstDayOfMonth(year, month - 1));
		invoice.setBillingPeriodEnd(getLastDayOfMonth(year, month - 1));
		//invoice.setPdfUrl("/yacceleratorstorefront/sptel/en/my-account/exportInvoice?invoiceNumber="+invoice.getInvoiceNumber());
		return invoice;
	}

	private boolean checkIfInvoiceAlreadyGeneratedForCurrentMonth(final CustomerModel customer, int month, int year)
	{
		if (month == 11)
		{
			month = 1;
			year = year + 1;
		}
		else
		{
			month = month + 1;
		}

		return checkIfInvoiceAlreadyGenerated(customer, month, year);
	}

	private boolean checkIfInvoiceAlreadyGenerated(final CustomerModel customer, final int month, final int year)
	{
		//validation if invoices for this period is already created

		final List<InvoiceModel> invoices = invoiceDao.getInvoices(customer, month, year);
		if (invoices != null && invoices.size() > 0)
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Invoice already exists for " + month + "month of " + year);
			}
			return true;
		}
		return false;
	}

	public BigDecimal getInvoiceBalance(final CustomerModel customer)
	{
		BigDecimal balance = BigDecimal.ZERO;

		final CurrencyModel currency = commonI18NService.getCurrentCurrency();
		final List<InvoiceModel> invoices = getInvoices(customer);

		for (final InvoiceModel invoice : invoices)
		{
			if (invoice.getCurrency() != null && !invoice.getCurrency().equals(currency))
			{
				final double remainingAmount = commonI18NService.convertCurrency(invoice.getCurrency().getConversion(),
						currency.getConversion(), invoice.getAmountRemaining());
				balance = balance.subtract(BigDecimal.valueOf(remainingAmount));
			}
			else
			{
				balance = balance.subtract(BigDecimal.valueOf(invoice.getAmountRemaining()));
			}
		}
		return balance;
	}

	@Override
	public InvoiceModel getInvoice(final String invoiceNumber)
	{
		validateParameterNotNull(invoiceNumber, "Invoice number cannot be null");
		return invoiceDao.getInvoice(invoiceNumber);
	}

	private double calculateTotalAmount(final List<ExpenseModel> expenses)
	{
		double total = 0;
		for (final ExpenseModel expense : expenses)
		{
			total = total + expense.getAmount().doubleValue();
		}
		return total;
	}

	private Date getLastDayOfMonth(final int year, final int month)
	{

		final Calendar calendar = Calendar.getInstance();

		calendar.set(year, month, 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		final Date date = calendar.getTime();
		return date;
	}

	private Date getFirstDayOfMonth(final int year, final int month)
	{
		final Calendar calendar = Calendar.getInstance();

		calendar.set(year, month, 1);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		final Date date = calendar.getTime();
		return date;
	}



	@Required
	public void setInvoiceDao(final InvoiceDao invoiceDao)
	{
		this.invoiceDao = invoiceDao;
	}


	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}


	@Required
	public void setExpenseService(final ExpenseService expenseService)
	{
		this.expenseService = expenseService;
	}

	@Required
	public void setInvoiceNumberGenerator(final KeyGenerator invoiceNumberGenerator)
	{
		this.invoiceNumberGenerator = invoiceNumberGenerator;
	}

	/**
	 * @return the invoiceNumberGenerator
	 */
	public KeyGenerator getInvoiceNumberGenerator()
	{
		return invoiceNumberGenerator;
	}

	@Required
	public void setTimeService(final TimeService timeService)
	{
		this.timeService = timeService;
	}


	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}







}
