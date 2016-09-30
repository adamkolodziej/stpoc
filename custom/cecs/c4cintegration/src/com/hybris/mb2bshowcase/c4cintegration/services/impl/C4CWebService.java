package com.hybris.mb2bshowcase.c4cintegration.services.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.xml.ws.BindingProvider;


/**
 * Created by Dominik Strzyzewski on 2014-12-03.
 */
// SEMBB-182 Implement integration
public abstract class C4CWebService
{

	private static final Logger LOG = Logger.getLogger(C4CWebService.class);

	private String endPointAddress;

	protected void setUpPort(final Object port)
	{
		setUpPort(port, getEndPointAddress());
	}

	protected void setUpPort(final Object port, final String endPointAddress)
	{
		if (port instanceof BindingProvider)
		{
			final BindingProvider bindingProvider = (BindingProvider) port;
			if (StringUtils.isNotEmpty(endPointAddress))
			{
				bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPointAddress);
			}
		}
		else
		{
			LOG.warn(String.format("Cannot set up port. provided object is not BindingProvider. Service class: %s, Port class: %s",
					getClass().getName(), port.getClass().getName()));
		}
	}

	protected String getEndPointAddress()
	{
		return endPointAddress;
	}

	public void setEndPointAddress(final String endPointAddress)
	{
		this.endPointAddress = endPointAddress;
	}
}
