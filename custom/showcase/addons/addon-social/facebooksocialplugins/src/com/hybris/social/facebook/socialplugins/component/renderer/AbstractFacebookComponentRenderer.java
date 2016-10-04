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

import de.hybris.platform.core.HybrisEnumValue;

import java.util.Collection;
import java.util.Iterator;

import com.hybris.addon.common.renderer.GenericAddOnCMSComponentRenderer;
import com.hybris.social.facebook.socialplugins.model.AbstractFacebookComponentModel;


/**
 * @author rmcotton
 * 
 */
public abstract class AbstractFacebookComponentRenderer<C extends AbstractFacebookComponentModel> extends
		GenericAddOnCMSComponentRenderer<C>
{
	protected String getEnumCodeString(final Collection<? extends HybrisEnumValue> enums)
	{
		final StringBuilder output = new StringBuilder();
		for (final Iterator<? extends HybrisEnumValue> i = enums.iterator(); i.hasNext();)
		{
			output.append(i.next());
			if (i.hasNext())
			{
				output.append(",");
			}
		}
		return output.toString();
	}

	public static String toCommaSeperatedString(final Collection inputCollection)
	{
		final StringBuilder output = new StringBuilder();
		for (final Iterator<Object> i = inputCollection.iterator(); i.hasNext();)
		{
			output.append(String.valueOf(i.next()));
			if (i.hasNext())
			{
				output.append(",");
			}
		}
		return output.toString();
	}
}
