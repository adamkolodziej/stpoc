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

import com.hybris.social.facebook.socialplugins.model.FacebookRecommendationsBoxComponentModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookRecommendationsBoxComponentRenderer<C extends FacebookRecommendationsBoxComponentModel> extends
		AbstractFacebookComponentRenderer<C>
{

	private boolean defaultComponentIdAsRef = false;

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
	{
		final Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(FacebookRecommendationsBoxComponentModel.DOMAINS,
				StringUtils.collectionToCommaDelimitedString(component.getDomains()));

		if (component.getFont() != null)
		{
			variables.put(FacebookRecommendationsBoxComponentModel.FONT,
					StringUtils.replace((component.getFont().getCode().toLowerCase()), "_", " "));
		}
		variables.put(FacebookRecommendationsBoxComponentModel.WIDTH, component.getWidth());
		variables.put(FacebookRecommendationsBoxComponentModel.HEIGHT, component.getHeight());
		variables.put(FacebookRecommendationsBoxComponentModel.SHOWHEADER, component.getShowHeader());
		variables.put(FacebookRecommendationsBoxComponentModel.BORDERCOLOR, component.getBorderColor());

		if (component.getColorScheme() != null)
		{
			variables.put(FacebookRecommendationsBoxComponentModel.COLORSCHEME, component.getColorScheme().getCode());
		}
		variables.put(FacebookRecommendationsBoxComponentModel.MAXAGE, component.getMaxAge());

		if (component.getLinkTarget() != null)
		{
			variables.put(FacebookRecommendationsBoxComponentModel.LINKTARGET, component.getLinkTarget().getCode());
		}

		addRefVariable(variables, component);


		return variables;
	}

	protected void addRefVariable(final Map<String, Object> variables, final C component)
	{
		if (org.apache.commons.lang.StringUtils.isBlank(component.getRef()) && isDefaultComponentUidAsRef())
		{
			variables.put(FacebookRecommendationsBoxComponentModel.REF, component.getUid());
		}
		else
		{
			variables.put(FacebookRecommendationsBoxComponentModel.REF, component.getRef());
		}
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
