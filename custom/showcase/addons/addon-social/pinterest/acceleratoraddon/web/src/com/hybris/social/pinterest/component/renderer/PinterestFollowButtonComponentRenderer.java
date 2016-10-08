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
package com.hybris.social.pinterest.component.renderer;

import de.hybris.platform.acceleratorcms.component.renderer.CMSComponentRenderer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;

import com.hybris.social.pinterest.model.PinterestFollowButtonComponentModel;


/**
 * @author rmcotton
 * 
 */
public class PinterestFollowButtonComponentRenderer implements CMSComponentRenderer<PinterestFollowButtonComponentModel>
{

	@Override
	public void renderComponent(final PageContext pageContext, final PinterestFollowButtonComponentModel component)
			throws ServletException, IOException
	{
		final JspWriter out = pageContext.getOut();
		out.write(buildButtonHTML(pageContext, component));
	}

	protected String buildButtonHTML(final PageContext pageContext, final PinterestFollowButtonComponentModel component)
	{
		final StringBuilder html = new StringBuilder();
		html.append("<a data-pin-do=\"buttonFollow\" href=\"").append(getBaseHref()).append(getUsername(component)).append("/\" >");

		if (StringUtils.isNotEmpty(component.getButtonTitle()))
		{
			html.append(component.getButtonTitle());
		}
		else
		{
			return "Pinterest";
		}

		html.append("</a>");
		return html.toString();
	}


	protected String getBaseHref()
	{
		return "http://pinterest.com/";
	}


	protected String getUsername(final PinterestFollowButtonComponentModel component)
	{
		return component.getUsername();
	}


}
