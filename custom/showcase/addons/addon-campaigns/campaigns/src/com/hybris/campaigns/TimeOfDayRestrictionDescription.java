/*
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
 *
 */
package com.hybris.campaigns;

import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.util.localization.Localization;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import com.hybris.campaigns.model.CMSTimeOfDayRestrictionModel;


public class TimeOfDayRestrictionDescription implements DynamicAttributeHandler<String, CMSTimeOfDayRestrictionModel>
{

	@Override
	public String get(final CMSTimeOfDayRestrictionModel model)
	{
		final StringBuilder result = new StringBuilder();
		final Integer from = model.getActiveFrom();
		final Integer until = model.getActiveUntil();

		final Collection<WeekDay> days = model.getDays();
		final ArrayList<String> dayNamesList = new ArrayList<String>();
		if (days != null)
		{
			for (final WeekDay day : days)
			{
				dayNamesList.add(day.getCode());
			}
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

	@Override
	public void set(final CMSTimeOfDayRestrictionModel model, final String value)
	{
		throw new UnsupportedOperationException();
	}

}
