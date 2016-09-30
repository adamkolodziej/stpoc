package com.hybris.showcase.cecs.tricaststore.setup.impl;

import de.hybris.platform.commerceservices.dataimport.impl.SampleDataImportService;
import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.TypeService;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.cecs.tricaststore.setup.TricastImportService;
import com.hybris.showcase.setup.PostInitHook;


/**
 * Created by Dominik Strzyzewski on 2015-01-16.
 */
/**
 * Tricast specific (migrated from telcostore) implementation of the
 * {@link de.hybris.platform.commerceservices.dataimport.impl.SampleDataImportService}:
 * <p>
 * <ul>
 * <li>imports additional sample data for subscriptions and bundles
 * <li>adds the types {@link de.hybris.platform.configurablebundleservices.model.BundleTemplateModel} and
 * {@link de.hybris.platform.configurablebundleservices.model.BundleTemplateStatusModel} as new root types to the
 * catalog sync job
 * <ul>
 * <p>
 */
public class TricastSampleDataImportService extends SampleDataImportService implements TricastImportService
{
	// CECS-28 integrate mediaconversion
	private static final Logger LOG = Logger.getLogger(TricastSampleDataImportService.class);

	private static final String PRODUCT_CATALOG_PATH = "/%s/import/sampledata/productCatalogs/%sProductCatalog";

	private TypeService typeService;
	private ModelService modelService;

	// CECS-28 integrate mediaconversion
	private CronJobService cronJobService;

	private List<PostInitHook> postInitHooks;

	@Override
	protected void importProductCatalog(final String extensionName, final String productCatalogName)
	{
		// Load Billing Plans
		getSetupImpexService().importImpexFile(
				String.format(PRODUCT_CATALOG_PATH + "/billingPlans.impex", extensionName, productCatalogName), false);

		// Load Subscription Terms
		getSetupImpexService().importImpexFile(
				String.format(PRODUCT_CATALOG_PATH + "/subscriptionterms.impex", extensionName, productCatalogName), false);

		// Load Usage Units
		getSetupImpexService()
				.importImpexFile(String.format(PRODUCT_CATALOG_PATH + "/usageunits.impex", extensionName, productCatalogName), false);

		super.importProductCatalog(extensionName, productCatalogName);

		// Load Entitlements
		getSetupImpexService().importImpexFile(
				String.format(PRODUCT_CATALOG_PATH + "/entitlements.impex", extensionName, productCatalogName), false);
		getSetupImpexService().importImpexFile(
				String.format(PRODUCT_CATALOG_PATH + "/products-entitlements.impex", extensionName, productCatalogName), false);

		// Load per Usage Pricing
		getSetupImpexService().importImpexFile(
				String.format(PRODUCT_CATALOG_PATH + "/usage-charges.impex", extensionName, productCatalogName), false);

		// Load Bundle Template data
		getSetupImpexService().importImpexFile(
				String.format(PRODUCT_CATALOG_PATH + "/bundletemplates.impex", extensionName, productCatalogName), false);
		getSetupImpexService().importImpexFile(
				String.format(PRODUCT_CATALOG_PATH + "/bundletemplates-products.impex", extensionName, productCatalogName), false);
		getSetupImpexService().importImpexFile(
				String.format(PRODUCT_CATALOG_PATH + "/bundletemplates-selectioncriteria.impex", extensionName, productCatalogName),
				false);
		getSetupImpexService().importImpexFile(
				String.format(PRODUCT_CATALOG_PATH + "/bundletemplates-pricerules.impex", extensionName, productCatalogName), false);
		getSetupImpexService().importImpexFile(
				String.format(PRODUCT_CATALOG_PATH + "/bundletemplates-disablerules.impex", extensionName, productCatalogName),
				false);

		// CECS-28 integrate mediaconversion
		runMediaConversionForProducts(extensionName, productCatalogName);
	}

	// CECS-28 integrate mediaconversion
	protected void runMediaConversionForProducts(final String extensionName, final String productCatalogName)
	{
		getSetupImpexService().importImpexFile(
				String.format(PRODUCT_CATALOG_PATH + "/mediaconversion_cronjobs.impex", extensionName, productCatalogName), false);

		try
		{
			final CronJobModel mediaConversionCronJob = getCronJobService()
					.getCronJob(productCatalogName + "MediaConversionCronjob");
			getCronJobService().performCronJob(mediaConversionCronJob, true);
		}
		catch (final UnknownIdentifierException e)
		{
			LOG.warn("Problem running media conversion cronjob for catalog: " + productCatalogName, e);
		}

		getSetupImpexService().importImpexFile(
				String.format(PRODUCT_CATALOG_PATH + "/products-media-converted.impex", extensionName, productCatalogName), false);
	}

	// CECS-38 create sample customer data
	@Override
	protected void importCommonData(final String extensionName)
	{
		super.importCommonData(extensionName);

		getSetupImpexService().importImpexFile(String.format("/%s/import/sampledata/common/user-groups.impex", extensionName),
				false);
	}

	@Override
	public void performPostInitHooks(final AbstractSystemSetup systemSetup, final SystemSetupContext context,
			final List<ImportData> importDataList)
	{

		for (final PostInitHook hook : postInitHooks)
		{
			hook.performPostInitHooks(systemSetup, context, importDataList);
		}
	}

	/**
	 * @return the typeService
	 */
	protected TypeService getTypeService()
	{
		return typeService;
	}

	@Required
	public void setTypeService(final TypeService typeService)
	{
		this.typeService = typeService;
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	// CECS-28 integrate mediaconversion
	public CronJobService getCronJobService()
	{
		return cronJobService;
	}

	// CECS-28 integrate mediaconversion
	public void setCronJobService(final CronJobService cronJobService)
	{
		this.cronJobService = cronJobService;
	}

	/**
	 * @return the postInitHooks
	 */
	public List<PostInitHook> getPostInitHooks()
	{
		return postInitHooks;
	}


	public void setPostInitHooks(final List<PostInitHook> postInitHooks)
	{
		this.postInitHooks = postInitHooks;
	}




}
