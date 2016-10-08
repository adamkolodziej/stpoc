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
package com.hybris.social.facebook.socialplugins.component.renderer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.springframework.util.StringUtils;

import com.hybris.social.facebook.socialplugins.model.FacebookRecommendationsBarComponentModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookRecommendationsBarComponentRenderer<C extends FacebookRecommendationsBarComponentModel> extends
		AbstractFacebookComponentRenderer<C>
{
	private boolean defaultComponentIdAsRef = false;


	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
	{
		final Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(FacebookRecommendationsBarComponentModel.URL, component.getUrl());
		variables.put(FacebookRecommendationsBarComponentModel.DOMAINS,
				StringUtils.collectionToCommaDelimitedString(component.getDomains()));
		variables.put(FacebookRecommendationsBarComponentModel.MAXAGE, component.getMaxAge());
		variables.put(FacebookRecommendationsBarComponentModel.NUMRECOMMENDATIONS, component.getNumRecommendations());
		variables.put("trigger", createTrigger(component));
		variables.put(FacebookRecommendationsBarComponentModel.READTIME, component.getReadTime());
		variables.put(FacebookRecommendationsBarComponentModel.SIDE, component.getSide().getCode());
		addRefVariable(variables, component);

		return variables;
	}

	protected void addRefVariable(final Map<String, Object> variables, final C component)
	{
		if (org.apache.commons.lang.StringUtils.isBlank(component.getRef()) && isDefaultComponentUidAsRef())
		{
			variables.put(FacebookRecommendationsBarComponentModel.REF, component.getUid());
		}
		else
		{
			variables.put(FacebookRecommendationsBarComponentModel.REF, component.getRef());
		}
	}

	protected String createTrigger(final C component)
	{
		if (component.getPageScrollPercentage() == null || component.getPageScrollPercentage().intValue() <= 0)
		{
			return "onvisible";
		}
		return component.getPageScrollPercentage().intValue() > 100 ? "100" : component.getPageScrollPercentage().toString();
	}

	public boolean isDefaultComponentUidAsRef()
	{
		return defaultComponentIdAsRef;
	}

	/**
	 * If the ref attribute is null, use the component uid
	 */
	public void setDefaultComponentUidAsRef(final boolean defaultComponentIdAsRef)
	{
		this.defaultComponentIdAsRef = defaultComponentIdAsRef;
	}
}
