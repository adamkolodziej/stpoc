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
package com.hybris.social.twitter.model.interceptors;

import de.hybris.platform.acceleratorservices.config.SiteConfigService;
import de.hybris.platform.cms2.servicelayer.services.admin.CMSAdminSiteService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.twitter.model.AbstractTwitterWidgetComponentModel;


/**
 * @author rmcotton
 * 
 */
public class TwitterWidgetInitDefaultsInterceptor implements InitDefaultsInterceptor
{
	private SiteConfigService siteConfigService;
	private BaseSiteService baseSiteService;
	private CMSAdminSiteService cmsAdminSiteService;
	private ConfigurationService configurationService;
	private SessionService sessionService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor#onInitDefaults(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onInitDefaults(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (model instanceof AbstractTwitterWidgetComponentModel)
		{
			final AbstractTwitterWidgetComponentModel widgetComponent = (AbstractTwitterWidgetComponentModel) model;
			if (getBaseSiteService().getCurrentBaseSite() != null)
			{
				widgetComponent.setWidgetId(getWidgetIdBySiteConfigService(widgetComponent));
			}
			else if (getCmsAdminSiteService().hasActiveSite())
			{
				widgetComponent.setWidgetId((String) getSessionService().executeInLocalView(new SessionExecutionBody()
				{
					/*
					 * (non-Javadoc)
					 * 
					 * @see de.hybris.platform.servicelayer.session.SessionExecutionBody#execute()
					 */
					@Override
					public Object execute()
					{
						getBaseSiteService().setCurrentBaseSite(getCmsAdminSiteService().getActiveSite(), false);

						return getWidgetIdBySiteConfigService(widgetComponent);
					}
				}));
			}
			else
			{
				widgetComponent.setWidgetId(getWidgetIdByConfigService(widgetComponent));
			}
		}

	}

	protected String getWidgetIdBySiteConfigService(final AbstractTwitterWidgetComponentModel model)
	{

		final String val = getSiteConfigService().getProperty("twitter." + model.getItemtype() + ".widget.id");
		if (val != null)
		{
			return val;
		}
		return getSiteConfigService().getProperty("twitter.widget.id");
	}

	protected String getWidgetIdByConfigService(final AbstractTwitterWidgetComponentModel model)
	{
		getBaseSiteService().setCurrentBaseSite(getBaseSiteService().getCurrentBaseSite(), false);

		final String val = getSiteConfigService().getProperty("twitter." + model.getItemtype() + ".widget.id");
		if (val != null)
		{
			return val;
		}
		return getSiteConfigService().getProperty("twitter.widget.id");
	}

	public SiteConfigService getSiteConfigService()
	{
		return siteConfigService;
	}

	@Required
	public void setSiteConfigService(final SiteConfigService siteConfigService)
	{
		this.siteConfigService = siteConfigService;
	}


	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}


	public CMSAdminSiteService getCmsAdminSiteService()
	{
		return cmsAdminSiteService;
	}

	@Required
	public void setCmsAdminSiteService(final CMSAdminSiteService cmsAdminSiteService)
	{
		this.cmsAdminSiteService = cmsAdminSiteService;
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


	public SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

}
