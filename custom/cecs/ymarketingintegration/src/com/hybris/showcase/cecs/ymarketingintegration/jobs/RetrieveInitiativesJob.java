package com.hybris.showcase.cecs.ymarketingintegration.jobs;

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.cecs.ymarketingintegration.services.InitiativesService;
import com.hybris.showcase.services.CustomerService;
import com.hybris.showcase.services.OfflineBTGEvaluationService;


public class RetrieveInitiativesJob extends AbstractJobPerformable<CronJobModel>
{
	private InitiativesService initiativesService;
	private CMSSiteService cmsSiteService;
	private OfflineBTGEvaluationService offlineBTGEvaluationService;
	private CustomerService customerService;

	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		final Collection<CustomerModel> customers = getCustomerService().getCustomers();
		for (final CustomerModel customer : customers)
		{
			initiativesService.retrieveInitiativesForCustomer(customer);
		}

		// CECS-374 ymarketing segments offline evaluation
		final Collection<CMSSiteModel> sites = cmsSiteService.getSites();
		offlineBTGEvaluationService.evaluateSegments(customers, sites, false);

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	public InitiativesService getInitiativesService()
	{
		return initiativesService;
	}

	@Required
	public void setInitiativesService(final InitiativesService initiativesService)
	{
		this.initiativesService = initiativesService;
	}

	public CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}

	@Required
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	public OfflineBTGEvaluationService getOfflineBTGEvaluationService()
	{
		return offlineBTGEvaluationService;
	}

	@Required
	public void setOfflineBTGEvaluationService(final OfflineBTGEvaluationService offlineBTGEvaluationService)
	{
		this.offlineBTGEvaluationService = offlineBTGEvaluationService;
	}

	/**
	 * @return the customerService
	 */
	public CustomerService getCustomerService()
	{
		return customerService;
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
