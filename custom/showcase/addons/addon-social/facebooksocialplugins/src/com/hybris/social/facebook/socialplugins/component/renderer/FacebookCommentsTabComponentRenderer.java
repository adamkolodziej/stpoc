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

import java.util.Map;

import javax.servlet.jsp.PageContext;

import com.hybris.social.facebook.socialplugins.model.FacebookCommentsTabComponentModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookCommentsTabComponentRenderer extends FacebookCommentsComponentRenderer<FacebookCommentsTabComponentModel>
{
	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext,
			final FacebookCommentsTabComponentModel component)
	{
		final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);
		variables.put(FacebookCommentsTabComponentModel.TITLE, component.getTitle());
		variables.put(FacebookCommentsTabComponentModel.UID, component.getUid());
		return variables;
	}
}
