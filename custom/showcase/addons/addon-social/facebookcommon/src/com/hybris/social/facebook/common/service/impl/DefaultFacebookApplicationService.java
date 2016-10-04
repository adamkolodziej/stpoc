/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 * 
 */
package com.hybris.social.facebook.common.service.impl;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.facebook.common.model.FacebookApplicationModel;
import com.hybris.social.facebook.common.service.FacebookApplicationService;
import com.hybris.social.facebook.common.service.dao.FacebookApplicationDao;


/**
 * @author rmcotton
 * 
 */
public class DefaultFacebookApplicationService extends AbstractBusinessService implements FacebookApplicationService
{
	protected static final String FB_APPLICATION_SERVER_NAME_KEY = "fbApplicationServerName";
	protected static final String FB_APPLICATION_SESSION_KEY = "fbApplication";

	protected String ipAddressRegex = "\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
	protected Pattern ipAddressPattern;


	private FacebookApplicationDao facebookApplicationDao;
	private ConfigurationService configurationService;

	protected FacebookApplicationModel tryGetFacebookApplicationFromConfiguration(final String serverName)
	{
		// not in db so check configuration (and save to the db)
		final String fbconnectAppId = getConfigurationService().getConfiguration().getString("fbconnect.appid." + serverName, null);
		if (StringUtils.isNotBlank(fbconnectAppId))
		{
			final String secret = getConfigurationService().getConfiguration().getString("fbconnect.secret." + serverName, null);
			return getFacebookApplicationDao().createDefaultFacebookApplication(Long.valueOf(fbconnectAppId), secret, serverName);
		}
		return null;
	}

	protected FacebookApplicationModel tryGetDefaultFacebookApplicationForDomain(final String serverName)
	{
		final FacebookApplicationModel application = getFacebookApplicationDao().findDefaultApplicationForDomain(serverName);
		if (application == null)
		{
			return tryGetFacebookApplicationFromConfiguration(serverName);
		}
		return application;
	}

	protected FacebookApplicationModel tryGetDefaultFacebookApplicationForSubDomains(final List<String> subDomains)
	{
		final FacebookApplicationModel application = getFacebookApplicationDao().findDefaultApplicationForSubDomains(subDomains);
		if (application == null)
		{
			for (final String domain : subDomains)
			{
				final FacebookApplicationModel appFromConfig = tryGetFacebookApplicationFromConfiguration(domain);
				if (appFromConfig != null)
				{
					return appFromConfig;
				}
			}
		}
		return application;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.social.facebook.service.FacebookConfigurationService#getDefaultFacebookApplicationForServerName
	 * (java.lang.String)
	 */
	@Override
	public FacebookApplicationModel initApplication(final String serverName)
	{
		if (!serverName.equals(getSessionService().getAttribute(FB_APPLICATION_SERVER_NAME_KEY)))
		{
			setCurrentApplication(getDefaultFacebookApplicationForServerName(serverName));
			getSessionService().setAttribute(FB_APPLICATION_SERVER_NAME_KEY, serverName);
		}
		return getCurrentApplication();
	}



	protected FacebookApplicationModel getDefaultFacebookApplicationForServerName(final String serverName)
	{
		FacebookApplicationModel application = tryGetDefaultFacebookApplicationForDomain(serverName);
		if (application == null)
		{
			if (!isIpAddress(serverName))
			{
				final List<String> subDomains = getSubDomains(serverName);
				if (CollectionUtils.isNotEmpty(subDomains))
				{
					application = tryGetDefaultFacebookApplicationForSubDomains(subDomains);
				}
			}
		}
		return application;
	}

	protected List<String> getSubDomains(final String serverName)
	{
		final String[] domainParts = StringUtils.split(serverName, '.');
		if (domainParts.length > 1)
		{
			final List<String> domainsToTry = new LinkedList<String>();
			for (int i = 1; i < domainParts.length; i++)
			{
				final StringBuilder domain = new StringBuilder();

				for (int j = i; j < domainParts.length; j++)
				{
					domain.append(domainParts[j]);
					if (j < domainParts.length - 1)
					{
						domain.append(".");
					}
				}
				domainsToTry.add(domain.toString());
			}
			return domainsToTry;
		}
		return null;
	}

	protected boolean isIpAddress(final String serverName)
	{
		return this.ipAddressPattern.matcher(serverName).matches();
	}

	/**
	 * @param facebookApplicationDao
	 *           the facebookApplicationDao to set
	 */
	@Required
	public void setFacebookApplicationDao(final FacebookApplicationDao facebookApplicationDao)
	{
		this.facebookApplicationDao = facebookApplicationDao;
	}

	/**
	 * @return the facebookApplicationDao
	 */
	public FacebookApplicationDao getFacebookApplicationDao()
	{
		return facebookApplicationDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception
	{
		super.afterPropertiesSet();
		this.ipAddressPattern = Pattern.compile(ipAddressRegex);
	}

	public void setIpAddressRegex(final String ipAddressRegex)
	{
		this.ipAddressRegex = ipAddressRegex;
	}

	/**
	 * @param configurationService
	 *           the configurationService to set
	 */
	@Required
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}

	/**
	 * @return the configurationService
	 */
	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.social.facebook.common.service.FacebookApplicationService#getCurrentApplication()
	 */
	@Override
	public FacebookApplicationModel getCurrentApplication()
	{
		return getSessionService().getAttribute(FB_APPLICATION_SESSION_KEY);
	}


	protected void setCurrentApplication(final FacebookApplicationModel facebookApplication)
	{
		if (facebookApplication == null)
		{
			getSessionService().removeAttribute(FB_APPLICATION_SESSION_KEY);
		}
		else
		{
			getSessionService().setAttribute(FB_APPLICATION_SESSION_KEY, facebookApplication);
		}
	}

}
