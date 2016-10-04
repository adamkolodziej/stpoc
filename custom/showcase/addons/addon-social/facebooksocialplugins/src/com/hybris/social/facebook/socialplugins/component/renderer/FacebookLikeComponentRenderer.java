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
import com.hybris.social.facebook.socialplugins.model.FacebookLikeComponentModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookLikeComponentRenderer<C extends FacebookLikeComponentModel> extends AbstractFacebookComponentRenderer<C>
{
	private SharedPageUrlStrategy sharedPageUrlStrategy;

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
	{
		final Map<String, Object> variables = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(component.getUrlToLike()))
		{
			variables.put(FacebookLikeComponentModel.URLTOLIKE, component.getUrlToLike());
		}
		else
		{
			variables.put(FacebookLikeComponentModel.URLTOLIKE, getSharedPageUrlStrategy().getUrl(pageContext));
		}
		variables.put(FacebookLikeComponentModel.SENDBUTTON, component.getSendButton());
		variables.put(FacebookLikeComponentModel.SHOWFACES, component.getShowFaces());
		variables.put(FacebookLikeComponentModel.WIDTH, component.getWidth());

		if (component.getLayoutStyle() != null)
		{
			variables.put(FacebookLikeComponentModel.LAYOUTSTYLE, component.getLayoutStyle().getCode());
		}
		if (component.getVerbToDisplay() != null)
		{
			variables.put(FacebookLikeComponentModel.VERBTODISPLAY, component.getVerbToDisplay().getCode());
		}
		if (component.getFont() != null)
		{
			variables.put(FacebookLikeComponentModel.FONT,
					StringUtils.replace(StringUtils.lowerCase(component.getFont().getCode()), "_", " "));
		}
		if (component.getColorScheme() != null)
		{
			variables.put(FacebookLikeComponentModel.COLORSCHEME, component.getColorScheme().getCode());
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