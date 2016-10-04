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

import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.core.Registry;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationManager;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.servicelayer.time.TimeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hybris.campaigns.cms.timezone.CmsTimeZoneService;
import com.hybris.campaigns.enums.Timezone;


public class TimezonePromotionRestriction extends GeneratedTimezonePromotionRestriction
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(TimezonePromotionRestriction.class.getName());

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.promotions.jalo.AbstractPromotionRestriction#evaluate(de.hybris.platform.jalo.SessionContext,
	 * java.util.Collection, java.util.Date, de.hybris.platform.jalo.order.AbstractOrder)
	 */
	@Override
	public RestrictionResult evaluate(final SessionContext ctx, final Collection<Product> products, final Date date,
			final AbstractOrder order)
	{
		// 'now' it's a time on the Server 
		final Date now = Registry.getApplicationContext().getBean("timeService", TimeService.class).getCurrentTime();

		final Integer from = getActiveFrom(ctx) == null ? Integer.valueOf(0) : getActiveFrom(ctx);
		final Integer until = getActiveUntil(ctx) == null ? Integer.valueOf(2359) : getActiveUntil(ctx);
		final EnumerationValue timezone = getTimezone(ctx);
		final Collection<EnumerationValue> days = getDays(ctx);

		if (timezone != null)
		{
			final CmsTimeZoneService cmsTimeZoneService = Registry.getApplicationContext().getBean("cmsTimeZoneService",
					CmsTimeZoneService.class);

			final Timezone tz = Timezone.valueOf(Timezone.class, StringUtils.upperCase(timezone.getCode()));

			if (getActivationDate(ctx) != null)
			{
				if (cmsTimeZoneService.getTimeForTimeZone(tz, now).before(
						cmsTimeZoneService.getTimeForTimeZone(tz, getActivationDate(ctx))))
				{
					return RestrictionResult.ALLOW;
				}
			}

			final Collection<WeekDay> weekdays;
			if (CollectionUtils.isEmpty(days))
			{
				weekdays = new ArrayList<WeekDay>(7);
				weekdays.addAll(Arrays.asList(WeekDay.values()));
			}
			else
			{
				weekdays = new ArrayList<WeekDay>(days.size());
				for (final EnumerationValue day : days)
				{
					weekdays.add(WeekDay.valueOf(WeekDay.class, StringUtils.upperCase(day.getCode())));
				}
			}

			final boolean allow = cmsTimeZoneService.evaluate(now, from, until, tz, weekdays);
			if (allow)
			{
				return RestrictionResult.ALLOW;
			}
		}

		return RestrictionResult.DENY;
	}

	@Override
	protected Object[] getDescriptionPatternArguments(final SessionContext ctx)
	{
		final Integer from = getActiveFrom(ctx);
		final Integer until = getActiveUntil(ctx);

		final Collection<EnumerationValue> days = getDays(ctx).isEmpty() ? EnumerationManager.getInstance()
				.getEnumerationType(WeekDay._TYPECODE).getValues() : getDays(ctx);
		final ArrayList<String> dayNamesList = new ArrayList<String>();
		for (final EnumerationValue day : days)
		{
			dayNamesList.add(day.getName());
		}

		final EnumerationValue timezoneValue = getTimezone(ctx);

		return new Object[]
		{ from != null ? StringUtils.leftPad(from.toString(), 4, '0') : "0000",
				until != null ? StringUtils.leftPad(until.toString(), 4, '0') : "2359", dayNamesList,
				timezoneValue != null ? Timezone.valueOf(timezoneValue.getCode().toUpperCase()) : "" };
	}
}
