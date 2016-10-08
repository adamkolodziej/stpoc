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
package com.hybris.social.twitter.component.renderer;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.jsp.PageContext;

import com.hybris.social.twitter.model.TwitterFollowButtonComponentModel;


/**
 * @author rmcotton
 * 
 */
public class TwitterFollowComponentRenderer extends AbstractTwitterButtonComponentRenderer<TwitterFollowButtonComponentModel>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.twitter.web.AbstractTwitterButtonTag#getBaseHref()
	 */
	@Override
	protected String getBaseHref(final TwitterFollowButtonComponentModel component)
	{
		return "https://twitter.com/";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.twitter.web.AbstractTwitterButtonTag#getHrefParameterString()
	 */
	@Override
	protected String getHrefParameterString(final PageContext context, final TwitterFollowButtonComponentModel component)
	{
		return component.getAccount().getScreenName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.twitter.web.AbstractTwitterButtonTag#getCssClass()
	 */
	@Override
	protected String getCssClass(final TwitterFollowButtonComponentModel component)
	{
		return "twitter-follow-button";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.twitter.web.AbstractTwitterButtonTag#getDataParameterString()
	 */
	@Override
	protected List<KeyValue> getDataParameters(final PageContext context, final TwitterFollowButtonComponentModel component)
	{
		final List<KeyValue> values = new LinkedList<KeyValue>();
		addIfNotBlank(values, "data-lang", getLang());
		addIfNotBlank(values, "data-show-screen-name", getShowUsername(component));
		addIfNotBlank(values, "data-show-count", getShowCount(component));
		addIfNotBlank(values, "data-size", getButtonSize(component));
		return values;
	}

	protected String getShowCount(final TwitterFollowButtonComponentModel component)
	{
		return Boolean.TRUE.equals(component.getShowCount()) ? "true" : "false";
	}

	protected String getShowUsername(final TwitterFollowButtonComponentModel component)
	{
		return Boolean.TRUE.equals(component.getShowUsername()) ? "true" : "false";
	}

	protected String getButtonSize(final TwitterFollowButtonComponentModel component)
	{
		return component.getButtonSize().getCode();
	}

}
