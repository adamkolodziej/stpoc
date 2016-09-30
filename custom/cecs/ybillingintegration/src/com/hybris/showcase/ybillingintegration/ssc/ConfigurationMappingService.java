package com.hybris.showcase.ybillingintegration.ssc;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;

import com.hybris.showcase.ybillingintegration.model.ProductConfigurationTemplateModel;


/**
 * CECS-381 Testing implementation for SSC
 *
 * Created by miroslaw.szot@sap.com on 2015-10-15.
 */
public interface ConfigurationMappingService
{
	ProductConfigurationTemplateModel getProductConfigurationTemplate(BundleTemplateModel bundleTemplate);

	BundleTemplateModel getBundleTemplateModel(ProductConfigurationTemplateModel template, CMSSiteModel siteModel, CatalogVersionModel catalogVersionModel);
	
	boolean isIntegrationWithYbilling();
}
