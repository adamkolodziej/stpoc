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
import com.hybris.social.facebook.socialplugins.model.FacebookCommentsComponentModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookCommentsComponentRenderer<C extends FacebookCommentsComponentModel> extends
		AbstractFacebookComponentRenderer<C>
{

	private SharedPageUrlStrategy sharedPageUrlStrategy;


	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
	{
		final Map<String, Object> variables = new HashMap<String, Object>();

		if (StringUtils.isNotBlank(component.getUrlToCommentOn()))
		{
			variables.put(FacebookCommentsComponentModel.URLTOCOMMENTON, component.getUrlToCommentOn());
		}
		else
		{
			variables.put(FacebookCommentsComponentModel.URLTOCOMMENTON, getSharedPageUrlStrategy().getUrl(pageContext));
		}
		variables.put(FacebookCommentsComponentModel.NUMBEROFPOSTS, component.getNumberOfPosts());
		variables.put(FacebookCommentsComponentModel.WIDTH, component.getWidth());
		if (component.getColorScheme() != null)
		{
			variables.put(FacebookCommentsComponentModel.COLORSCHEME, component.getColorScheme().getCode());
		}
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
