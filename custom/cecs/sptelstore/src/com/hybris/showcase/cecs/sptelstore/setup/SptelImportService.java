package com.hybris.showcase.cecs.sptelstore.setup;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.core.initialization.SystemSetupContext;

import java.util.List;

/**
 * CECS-244 export entitlements to simulation account for entitlement proposals [during init]
 * Created by miroslaw.szot@sap.com on 2015-05-27.
 */
public interface SptelImportService {
    void performPostInitHooks(AbstractSystemSetup systemSetup, SystemSetupContext context, List<ImportData> importData);
}
