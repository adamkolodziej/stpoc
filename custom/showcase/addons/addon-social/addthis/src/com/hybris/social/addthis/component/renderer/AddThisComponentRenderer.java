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
package com.hybris.social.addthis.component.renderer;

import de.hybris.platform.acceleratorservices.config.SiteConfigService;
import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.addthis.enums.AddThisOrientation;
import com.hybris.social.addthis.enums.AddThisServiceEnum;
import com.hybris.social.addthis.model.AddThisComponentModel;


/**
 * Created with IntelliJ IDEA. User: Zdary Date: 12/03/13 Time: 16:26 To change this template use File | Settings | File
 * Templates.
 */
public class AddThisComponentRenderer<C extends AddThisComponentModel> extends DefaultAddOnCMSComponentRenderer<C>
{
	private CommerceCommonI18NService commerceCommonI18nService;
	private SiteConfigService siteConfigService;
	private C component;

	private final String PREFERRED_SERVICE_NAME = "_preferedservice";
	private final String PREFERRED_SERVICE_CSS_CLASS = "preferred_%N%";
	private final String PROPERTY_ANALYTICS_DEFAULT = "addthis.analytics.default";
	private final String PROPERTY_ANALYTICS_PER_SITE = "addthis.analytics";

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
	{
		this.component = component;
		final Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("size", component.getSize().toString());
		variables.put("orientation", component.getOrientation().toString());
		variables.put("analyticsUser", setupAnalyticsUser());
		variables.put("buttons", setupButtonList());
		variables.put("isCompactButton",
				component.getOrientation().equals(AddThisOrientation.VERTICAL) && component.getShowCounter());
		variables.put("isCounterButton", component.getShowCounter());
		variables.put("config", setupConfig());
		return variables;
	}

	private List<String> setupButtonList()
	{
		final List<String> buttonList = new ArrayList<String>();
		final List<AddThisServiceEnum> servicesList = component.getVisibleServiceButtons();
		int preferedIndex = 0;
		for (final AddThisServiceEnum addThisService : servicesList)
		{
			final String code = addThisService.getCode();
			if (code.equals(PREFERRED_SERVICE_NAME))
			{
				++preferedIndex;
				buttonList.add(PREFERRED_SERVICE_CSS_CLASS.replace("%N%", Integer.toString(preferedIndex)));
			}
			else
			{
				buttonList.add(code.charAt(0) == '_' ? code.substring(1) : code);
			}
		}
		return buttonList;
	}


	private String setupAnalyticsUser()
	{
		String analyticsUser = component.getAltAnalyticsUser();
		if (analyticsUser != null)
		{
			return analyticsUser;
		}
		analyticsUser = getSiteConfigService().getProperty(PROPERTY_ANALYTICS_PER_SITE);
		if (analyticsUser != null)
		{
			return analyticsUser;
		}
		return getSiteConfigService().getProperty(PROPERTY_ANALYTICS_DEFAULT);
	}

	private Map<String, String> setupConfig()
	{
		final Map<String, String> configMap = new HashMap<String, String>();
		configMap.put("ui_language", getCommerceCommonI18NService().getCurrentLocale().toString());
		configMap.put("services_compact", getServiceList(component.getServicesCompact()));
		configMap.put("services_exclude", getServiceList(component.getServicesExclude()));
		configMap.put("services_expanded", getServiceList(component.getServicesExpanded()));
		configMap.put("ui_click", component.getUiClick().toString());
		return configMap;
	}


	private String getServiceList(final List<AddThisServiceEnum> services)
	{
		if (services == null || services.isEmpty())
		{
			return "";
		}
		final StringBuilder result = new StringBuilder();
		for (final AddThisServiceEnum service : services)
		{
			result.append(service.getCode());
			result.append(",");
		}
		return result.substring(0, result.length() - 1);
	}

	/**
	 * @return the commerceCommonI18nService
	 */
	public CommerceCommonI18NService getCommerceCommonI18NService()
	{
		return commerceCommonI18nService;
	}

	/**
	 * @param commerceCommonI18nService
	 *           the commerceCommonI18nService to set
	 */
	@Required
	public void setCommerceCommonI18NService(final CommerceCommonI18NService commerceCommonI18nService)
	{
		this.commerceCommonI18nService = commerceCommonI18nService;
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



}
