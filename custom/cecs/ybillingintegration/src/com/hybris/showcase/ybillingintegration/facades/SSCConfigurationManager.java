package com.hybris.showcase.ybillingintegration.facades;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.sap.productconfig.runtime.interf.model.ConfigModel;


/**
 * CECS-700 Get rid of yBilling dependency on guideselling<br />
 * CECS-668 evaluate SSC rules during guidedselling <br />
 *
 * Created by miroslaw.szot@sap.com on 2016-01-26.
 */
public interface SSCConfigurationManager
{
	ConfigModel createConfiguration(AbstractOrderEntryModel entry);

	ConfigModel retrieveConfiguration(AbstractOrderEntryModel entry);

	ConfigModel updateConfiguration(ConfigModel configModel);

	void releaseSession(AbstractOrderEntryModel entry);

	boolean isSlcEnabled();
}
