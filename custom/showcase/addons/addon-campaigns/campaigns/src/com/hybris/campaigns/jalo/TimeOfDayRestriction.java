/*
 *  
 * [y] hybris Platform
 *  
 * Copyright (c) 2000-2011 hybris AG
 * All rights reserved.
 *  
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *  
 */
package com.hybris.campaigns.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.localization.Localization;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


public class TimeOfDayRestriction extends GeneratedTimeOfDayRestriction
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(TimeOfDayRestriction.class.getName());

	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes)
			throws JaloBusinessException
	{
		// business code placed here will be executed before the item is created
		// then create the item
		final Item item = super.createItem(ctx, type, allAttributes);
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}

	@Deprecated
	@Override
	public String getDescription(final SessionContext ctx)
	{
		final StringBuilder result = new StringBuilder();
		final Integer from = getActiveFrom();
		final Integer until = getActiveUntil();

		final Collection<EnumerationValue> days = getDays();
		final ArrayList<String> dayNamesList = new ArrayList<String>();
		for (final EnumerationValue day : days)
		{
			dayNamesList.add(day.getName());
		}

		final Object[] args =
		{ from != null ? from.toString() : "", until != null ? until.toString() : "", dayNamesList };
		final String localizedString = Localization.getLocalizedString("type.CMSTimeOfDayRestriction.description.text", args);

		if (localizedString == null && from != null && until != null)
		{
			result.append("Page only applies");
			result.append(" from ").append(StringUtils.leftPad(from.toString(), 4, '0'));
			result.append(" until ").append(StringUtils.leftPad(until.toString(), 4, '0'));
			result.append(" on days of week: ");
			for (final String day : dayNamesList)
			{
				result.append(day).append(", ");
			}
			result.replace(result.length() - 2, result.length(), "");
		}
		else
		{
			return localizedString;
		}
		return result.toString();
	}

}
