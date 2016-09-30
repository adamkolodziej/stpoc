/**
 *
 */
package com.hybris.showcase.jobs;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.model.ContractModel;
import com.hybris.showcase.model.InvoicingCronJobModel;
import com.hybris.showcase.services.ContractService;
import com.hybris.showcase.services.CustomerService;
import com.hybris.showcase.services.ExpenseService;
import com.hybris.showcase.services.InvoiceService;
import com.hybris.showcase.utils.ShowcaseDateUtils;


/**
 * @author Rafal Zymla
 *
 */
public class InvoicingJob extends AbstractJobPerformable<InvoicingCronJobModel>
{

	private InvoiceService invoiceService;
	private ContractService contractService;
	private ExpenseService expenseService;
	private CustomerAccountService customerAccountService;
	private CustomerService customerService;

	@Override
	public PerformResult perform(final InvoicingCronJobModel cronJob)
	{
		final List<CustomerModel> customers = customerService.getCustomersWithLoginEnabled();

		for (final CustomerModel customer : customers)
		{
			final ContractModel contract = contractService.getLatestContractForUser(customer);
			if (contract != null)
			{
				final OrderModel order = customerAccountService.getOrderForCode(customer, contract.getCode(), cronJob.getBaseStore());
				final Date invoiceUntilDate = cronJob.getInvoiceDate() != null ? cronJob.getInvoiceDate() : new Date();
				Date operationDate = DateUtils.truncate(ShowcaseDateUtils.getFirstDayOfMonth(DateUtils.addMonths(order.getDate(), 1)),
						Calendar.DAY_OF_MONTH);
				while (!operationDate.after(invoiceUntilDate))
				{
					createInvoicesAndExpenses(order, customer, contract, operationDate);
					operationDate = DateUtils.addMonths(operationDate, 1);
				}
			}
		}

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}


	private void createInvoicesAndExpenses(final OrderModel order, final CustomerModel customer, final ContractModel contract,
			final Date date)
	{
		invoiceService.createInvoiceForCustomer(customer, contract, date);

		for (final AbstractOrderModel childOrder : order.getChildren())
		{
			expenseService.createExpenses(childOrder, customer, contract, date);
		}
	}


	@Required
	public void setInvoiceService(final InvoiceService invoiceService)
	{
		this.invoiceService = invoiceService;
	}

	@Required
	public void setContractService(final ContractService contractService)
	{
		this.contractService = contractService;
	}

	@Required
	public void setExpenseService(final ExpenseService expenseService)
	{
		this.expenseService = expenseService;
	}


	@Required
	public void setCustomerAccountService(final CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}


	@Required
	public void setCustomerService(final CustomerService customerService)
	{
		this.customerService = customerService;
	}




}
