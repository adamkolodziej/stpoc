/**
 *
 */
package com.hybris.showcase.servicescore.setup.impl;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.cronjob.model.JobModel;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.model.ContractModel;
import com.hybris.showcase.model.InvoiceModel;
import com.hybris.showcase.model.InvoicingCronJobModel;
import com.hybris.showcase.services.ContractService;
import com.hybris.showcase.services.CustomerService;
import com.hybris.showcase.services.InvoiceService;
import com.hybris.showcase.setup.PostInitHook;



/**
 * @author Rafal Zymla, Adrian Sbarcea
 *
 */
public class CreateInvoicesPostInitHook implements PostInitHook
{
	private static final Logger LOG = Logger.getLogger(CreateInvoicesPostInitHook.class);

	private CronJobService cronJobService;
	private ModelService modelService;
	private BaseStoreService baseStoreService;

	private InvoiceService invoiceService;
	private ContractService contractService;
	private CustomerService customerService;

	@Override
	public void performPostInitHooks(final AbstractSystemSetup systemSetup, final SystemSetupContext context,
			final List<ImportData> importDataList)
	{

		try
		{
			for (final ImportData importData : importDataList)
			{
				for (final String store : importData.getStoreNames())
				{
					final JobModel jobModel = cronJobService.getJob("invoicingJob");

					final InvoicingCronJobModel cronJob = modelService.create(InvoicingCronJobModel.class);
					cronJob.setActive(Boolean.TRUE);
					cronJob.setJob(jobModel);
					cronJob.setInvoiceDate(new Date());
					cronJob.setBaseStore(baseStoreService.getBaseStoreForUid(store));
					cronJob.setRemoveOnExit(Boolean.TRUE);
					modelService.save(cronJob);

					cronJobService.performCronJob(cronJob, true);

					//after the cronjob generates the invoices for each basestore (and active customers with contracts),
					//we need to set all the invoices to status "PAID" and amountPaid to the value of amountDue,
					//and amountRemaining to 0

					final List<CustomerModel> customers = customerService.getCustomersWithLoginEnabled();

					for (final CustomerModel customer : customers)
					{
						final ContractModel contract = contractService.getLatestContractForUser(customer);
						if (contract != null)
						{
							final List<InvoiceModel> invoices = invoiceService.getInvoices(customer);

							//sort the list based on invoice dateinvoices
							final List<InvoiceModel> sortedInvoices = invoices.stream()
									.sorted((inv1, inv2) -> inv1.getInvoiceDate().compareTo(inv2.getInvoiceDate()))
									.collect(Collectors.toList());
							//remove the last invoice, becasue we need to leave it to status unpaid
							sortedInvoices.remove(sortedInvoices.size() - 1);

							for (final InvoiceModel inv : sortedInvoices)
							{
								inv.setAmountPaid(inv.getAmountDue());
								inv.setAmountRemaining(new Double(0));
								inv.setInvoiceStatus(PaymentStatus.PAID.getCode());
								modelService.save(inv);
							}
						}
					}
				}
			}
		}
		catch (

		final UnknownIdentifierException e)

		{
			LOG.error("Error occured", e);
		}
	}

	public CronJobService getCronJobService()
	{
		return cronJobService;
	}

	@Required
	public void setCronJobService(final CronJobService cronJobService)
	{
		this.cronJobService = cronJobService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}


	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}


	/**
	 * @return the baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}



	@Required
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/**
	 * @param invoiceService
	 *           the invoiceService to set
	 */
	@Required
	public void setInvoiceService(final InvoiceService invoiceService)
	{
		this.invoiceService = invoiceService;
	}

	/**
	 * @param contractService
	 *           the contractService to set
	 */
	@Required
	public void setContractService(final ContractService contractService)
	{
		this.contractService = contractService;
	}

	/**
	 * @param customerService
	 *           the customerService to set
	 */
	@Required
	public void setCustomerService(final CustomerService customerService)
	{
		this.customerService = customerService;
	}

}

