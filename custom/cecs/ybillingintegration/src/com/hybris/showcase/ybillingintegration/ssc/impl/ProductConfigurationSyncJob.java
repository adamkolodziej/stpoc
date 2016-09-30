package com.hybris.showcase.ybillingintegration.ssc.impl;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.commerceservices.setup.SetupImpexService;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.jalo.flexiblesearch.FlexibleSearch;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.ybillingintegration.model.ProductConfigurationSyncCronJobModel;
import com.hybris.showcase.ybillingintegration.model.ProductConfigurationTemplateModel;
import com.hybris.showcase.ybillingintegration.ssc.ConfigurationMappingService;


/**
 * CECS-381 Testing implementation for SSC Created by miroslaw.szot@sap.com on 2015-10-15.
 */
public class ProductConfigurationSyncJob extends AbstractJobPerformable<ProductConfigurationSyncCronJobModel>
{
	private static final Logger LOG = Logger.getLogger(ProductConfigurationSyncJob.class);
	public static final String TV_ADDONS_NAME = "TriCast-TV-Addons";
	public static final String CONF_TRICAST_BUNDLE_TEMPLATE_ID = "ybillingintegration.bundletemplate.tricast.id";


	private ConfigurationMappingService configurationMappingService;
	private CatalogVersionService catalogVersionService;
	private SearchRestrictionService searchRestrictionService;
	private UserService userService;
	private SetupImpexService setupImpexAddonService;
	private ConfigurationService configurationService;

	@Override
	public PerformResult perform(final ProductConfigurationSyncCronJobModel cronJob)
	{


		final CMSSiteModel site = cronJob.getSite();
		final ProductConfigurationTemplateModel template = cronJob.getTemplate();
		final CatalogVersionModel catalogVersion = template == null ? cronJob.getCatalogVersion() : template.getCatalogVersion();
		final CustomerModel anonymous = userService.getAnonymousUser();

		LOG.info("SSC Product Sync - cv: " + catalogVersion.getMnemonic());
		LOG.info("SSC Product Sync - site: " + site.getUid());
		if (template != null)
		{
			LOG.info("SSC Product Sync - template: " + template.getCode());
		}

		final List<BundleTemplateModel> mappedBundleTemplates = sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public Object execute()
			{
				catalogVersionService.setSessionCatalogVersions(Arrays.asList(catalogVersion));
				searchRestrictionService.enableSearchRestrictions();
				sessionService.setAttribute(FlexibleSearch.DISABLE_RESTRICTION_GROUP_INHERITANCE, Boolean.FALSE);

				final ProductConfigurationTemplateModel example = new ProductConfigurationTemplateModel();
				example.setConfigurable(Boolean.TRUE);
				example.setCatalogVersion(catalogVersion);
				final List<ProductConfigurationTemplateModel> templates = flexibleSearchService.getModelsByExample(example);
				final List<BundleTemplateModel> mappedBundleTemplates = new ArrayList<BundleTemplateModel>();
				for (final ProductConfigurationTemplateModel template : templates)
				{
					LOG.info("SSC Product Sync - Evaluating template: " + template.getCode() + " -> " + template.getYBillingId());
					final BundleTemplateModel bundleTemplate = configurationMappingService.getBundleTemplateModel(template, site,
							catalogVersion);

					if (bundleTemplate == null)
					{
						LOG.error("SSC Product Sync - Failed on template: " + template.getCode() + " -> " + template.getYBillingId());
					}
					else
					{
						mappedBundleTemplates.add(bundleTemplate);
						LOG.info("SSC Product Sync - Completed bundle: " + bundleTemplate.getId() + " -> "
								+ bundleTemplate.getYBillingId());
					}
				}

				LOG.info("SSC Product Sync - Finished/Success");
				return mappedBundleTemplates;
			}


		}, anonymous);



		executeImpexForTricastPack(mappedBundleTemplates);



		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	private void executeImpexForTricastPack(final List<BundleTemplateModel> bundleTemplates)
	{
		for (final BundleTemplateModel bundleTemplate : bundleTemplates)
		{
			if (getTricastBundleTemplateId().equals(bundleTemplate.getId()))
			{
				LOG.info("Executing impex after SSC mapping: afterSSCMapping.impex for " + TV_ADDONS_NAME + ", "
						+ bundleTemplate.getVersion());

				final Map<String, Object> macroParameters = new HashMap<>();
				macroParameters.put("bundleTemplateId", TV_ADDONS_NAME);
				macroParameters.put("bundleTemplateVersion", bundleTemplate.getVersion());
				setupImpexAddonService.importImpexFile(
						"/ybillingintegration/import/productCatalogs/tricastProductCatalog/afterSSCMapping.impex", macroParameters,
						true);
			}
		}
	}

	public String getTricastBundleTemplateId()
	{
		return configurationService.getConfiguration().getString(CONF_TRICAST_BUNDLE_TEMPLATE_ID, "TRICAST_PACK");
	}

	public ConfigurationMappingService getConfigurationMappingService()
	{
		return configurationMappingService;
	}

	@Required
	public void setConfigurationMappingService(final ConfigurationMappingService configurationMappingService)
	{
		this.configurationMappingService = configurationMappingService;
	}

	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	@Required
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	public SearchRestrictionService getSearchRestrictionService()
	{
		return searchRestrictionService;
	}

	@Required
	public void setSearchRestrictionService(final SearchRestrictionService searchRestrictionService)
	{
		this.searchRestrictionService = searchRestrictionService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public SetupImpexService getSetupImpexAddonService()
	{
		return setupImpexAddonService;
	}

	@Required
	public void setSetupImpexAddonService(final SetupImpexService setupImpexAddonService)
	{
		this.setupImpexAddonService = setupImpexAddonService;
	}

	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	@Required
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}




}
