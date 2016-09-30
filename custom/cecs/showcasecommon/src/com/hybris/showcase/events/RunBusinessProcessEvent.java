package com.hybris.showcase.events;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.servicelayer.event.ClusterAwareEvent;


public class RunBusinessProcessEvent extends AbstractCommerceUserEvent<BaseSiteModel>
{
	private String businessProcessName;

	public String getBusinessProcessName()
	{
		return businessProcessName;
	}

	public void setBusinessProcessName(final String businessProcessName)
	{
		this.businessProcessName = businessProcessName;
	}
}
