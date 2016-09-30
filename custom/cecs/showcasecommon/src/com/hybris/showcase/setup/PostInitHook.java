/**
 *
 */
package com.hybris.showcase.setup;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.core.initialization.SystemSetupContext;

import java.util.List;


/**
 * @author Rafal Zymla
 *
 */
public interface PostInitHook
{
	void performPostInitHooks(AbstractSystemSetup systemSetup, SystemSetupContext context, List<ImportData> importDataList);
}
