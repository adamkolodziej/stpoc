package com.hybris.showcase.ybillingintegration.ssc;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.sap.productconfig.runtime.interf.model.ConfigModel;

/**
 * CECS-381 Testing implementation for SSC
 *
 * Created by miroslaw.szot@sap.com on 2015-10-14.
 */
public interface ProductConfigurationLoader {
    ConfigModel getConfigurationModel(String code, CMSSiteModel siteModel, CatalogVersionModel catalogVersionModel);

    ConfigModel getConfigurationModel(String code);
}
