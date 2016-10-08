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
package com.hybris.social.googleplus.component.renderer;

import de.hybris.platform.acceleratorcms.component.renderer.CMSComponentRenderer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.tag.common.core.UrlSupport;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.common.url.SharedPageUrlStrategy;
import com.hybris.social.googleplus.enums.PlusOneButtonSize;
import com.hybris.social.googleplus.enums.PlusOneCountLayoutStyle;
import com.hybris.social.googleplus.model.PlusOneComponentModel;


/**
 * @author rmcotton
 * 
 */
public class PlusOneComponentRenderer implements CMSComponentRenderer<PlusOneComponentModel>
{
	private SharedPageUrlStrategy urlStrategy;


	@Override
	public void renderComponent(final PageContext pageContext, final PlusOneComponentModel component) throws ServletException,
			IOException
	{
		pageContext.getOut().write(buildButtonHTML(pageContext, component));

	}


	protected String buildButtonHTML(final PageContext pageContext, final PlusOneComponentModel component)
	{
		final StringBuilder html = new StringBuilder();
		html.append("<g:plusone ");

		final PlusOneButtonSize size = getSize(component);
		if (size != null)
		{
			html.append("size=\"").append(size.getCode()).append("\" ");
		}
		html.append("annotation=\"").append(getAnnotation(component).getCode()).append("\" ");

		final Integer width = getWidth(component);
		if (width != null)
		{
			html.append("width=\"").append(width).append("\" ");
		}

		final String href = getUrlToPlusOne(pageContext, component);
		if (StringUtils.isNotBlank(href))
		{
			html.append("href=\"").append(href).append("\" ");
		}

		html.append("></g:plusone>");
		return html.toString();
	}


	protected Integer getWidth(final PlusOneComponentModel component)
	{
		return component.getWidth();
	}

	protected PlusOneButtonSize getSize(final PlusOneComponentModel component)
	{
		final PlusOneButtonSize size = component.getButtonSize();
		if (PlusOneButtonSize.STANDARD.equals(size))
		{
			return null;
		}
		else
		{
			return size;
		}
	}

	protected PlusOneCountLayoutStyle getAnnotation(final PlusOneComponentModel component)
	{
		return component.getAnnotation();
	}


	protected String resolveUrl(final String url, final PageContext pageContext)
	{
		try
		{
			return UrlSupport.resolveUrl(url, null, pageContext);
		}
		catch (final JspException jspe)
		{
			throw new IllegalStateException(jspe);
		}
	}

	protected String getUrlToPlusOne(final PageContext pageContext, final PlusOneComponentModel component)
	{
		final String url;
		if (StringUtils.isNotBlank(component.getUrlToPlusOne()))
		{
			url = resolveUrl(component.getUrlToPlusOne(), pageContext);
		}
		else
		{
			url = getSharedPageUrlStrategy().getUrl(pageContext);
		}
		return url;
	}

	@Required
	public void setSharedPageUrlStrategy(final SharedPageUrlStrategy sharedPageUrlStrategy)
	{
		this.urlStrategy = sharedPageUrlStrategy;
	}

	public SharedPageUrlStrategy getSharedPageUrlStrategy()
	{
		return this.urlStrategy;
	}


}
