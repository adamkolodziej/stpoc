/**
 *
 */
package com.hybris.showcase.cecs.tricaststore.setup.impl;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.setup.PostInitHook;


/**
 *
 * moved from TricastSampleDataImportService
 *
 * @author Rafal Zymla
 *
 */
public class EntitlementsPostInitHook implements PostInitHook
{

	// CECS-28 integrate mediaconversion
	private static final Logger LOG = Logger.getLogger(EntitlementsPostInitHook.class);

	// CECS-28 integrate mediaconversion
	private CronJobService cronJobService;

	@Override
	public void performPostInitHooks(final AbstractSystemSetup systemSetup, final SystemSetupContext context,
			final List<ImportData> importDataList)
	{
		for (final ImportData importData : importDataList)
		{
			for (final String store : importData.getStoreNames())
			{
				try
				{
					// CECS-244 export entitlements to simulation account for entitlement proposals [during init]
					systemSetup.logInfo(context, String.format("Export Entitlements Proposals for %s", new Object[]
					{ store }));
					final CronJobModel job = getCronJobService().getCronJob(store + "-entitlementsSimulationExportCronJob");
					getCronJobService().performCronJob(job, true);
				}
				catch (final UnknownIdentifierException e)
				{
					LOG.warn("Problem running entitlements export cronjob for site: " + store, e);
				}
			}
		}

	}

	/**
	 * @return the cronJobService
	 */
	public CronJobService getCronJobService()
	{
		return cronJobService;
	}

	@Required
	public void setCronJobService(final CronJobService cronJobService)
	{
		this.cronJobService = cronJobService;
	}

}
