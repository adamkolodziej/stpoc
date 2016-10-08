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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.common.url.SharedPageUrlStrategy;
import com.hybris.social.facebook.socialplugins.model.FacebookFacepileComponentModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookFacepileComponentRenderer<C extends FacebookFacepileComponentModel> extends
		AbstractFacebookComponentRenderer<C>
{
	private SharedPageUrlStrategy sharedPageUrlStrategy;


	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
	{
		final Map<String, Object> variables = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(component.getUrl()))
		{
			variables.put(FacebookFacepileComponentModel.URL, component.getUrl());
		}
		else
		{
			variables.put(FacebookFacepileComponentModel.URL, getSharedPageUrlStrategy().getUrl(pageContext));
		}
		variables.put(FacebookFacepileComponentModel.PICTURESIZE, component.getPictureSize().getCode());

		if (component.getColorScheme() != null)
		{
			variables.put(FacebookFacepileComponentModel.COLORSCHEME, component.getColorScheme().getCode());
		}
		variables.put(FacebookFacepileComponentModel.WIDTH, component.getWidth());
		variables.put(FacebookFacepileComponentModel.MAXROWS, component.getMaxRows());
		return variables;
	}

	/**
	 * @param sharedPageUrlStrategy
	 *           the sharedPageUrlStrategy to set
	 */
	@Required
	public void setSharedPageUrlStrategy(final SharedPageUrlStrategy sharedPageUrlStrategy)
	{
		this.sharedPageUrlStrategy = sharedPageUrlStrategy;
	}

	public SharedPageUrlStrategy getSharedPageUrlStrategy()
	{
		return this.sharedPageUrlStrategy;
	}


}
