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

import com.hybris.social.facebook.socialplugins.model.FacebookLikeBoxComponentModel;
import com.hybris.social.facebook.socialplugins.model.FacebookLikeComponentModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookLikeBoxComponentRenderer<C extends FacebookLikeBoxComponentModel> extends
		AbstractFacebookComponentRenderer<C>
{
	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
	{
		final Map<String, Object> variables = new HashMap<String, Object>();

		variables.put(FacebookLikeBoxComponentModel.FACEBOOKPAGEURL, component.getFacebookPageUrl());
		variables.put(FacebookLikeBoxComponentModel.SHOWFACES, component.getShowFaces());
		variables.put(FacebookLikeBoxComponentModel.SHOWHEADER, component.getShowHeader());
		variables.put(FacebookLikeBoxComponentModel.SHOWSTREAM, component.getShowStream());
		variables.put(FacebookLikeBoxComponentModel.FORCEWALL, component.getForceWall());
		variables.put(FacebookLikeBoxComponentModel.WIDTH, component.getWidth());
		if (component.getColorScheme() != null)
		{
			variables.put(FacebookLikeComponentModel.COLORSCHEME, component.getColorScheme().getCode());
		}
		variables.put(FacebookLikeBoxComponentModel.BORDERCOLOR, component.getBorderColor());

		if (component.getHeight() != null)
		{
			variables.put(FacebookLikeBoxComponentModel.HEIGHT, component.getHeight());
		}
		return variables;

	}
}
