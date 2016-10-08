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
package com.hybris.campaigns.evaluator.impl;

import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator;
import de.hybris.platform.servicelayer.time.TimeService;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.campaigns.cms.timezone.CmsTimeZoneService;
import com.hybris.campaigns.enums.Timezone;
import com.hybris.campaigns.model.CMSTimeOfDayRestrictionModel;


/**
 * Evaluates a time of day restriction accordingly to current time, day of week and timezone.
 * <p/>
 * 
 * @author radoslaw.golabek
 */
public class CMSTimeOfDayRestrictionEvaluator implements CMSRestrictionEvaluator<CMSTimeOfDayRestrictionModel>
{
	private CmsTimeZoneService cmsTimeZoneService;
	private TimeService timeService;

	@Override
	public boolean evaluate(final CMSTimeOfDayRestrictionModel timeRestriction, final RestrictionData context)
	{
		// 'now' it's a time on the Server
		final Date now = getTimeService().getCurrentTime();
		final Integer from = timeRestriction.getActiveFrom();
		final Integer until = timeRestriction.getActiveUntil();
		final Timezone timezone = timeRestriction.getTimezone();
		final Collection<WeekDay> days = timeRestriction.getDays();

		return this.cmsTimeZoneService.evaluate(now, from, until, timezone, days);
	}

	@Required
	public void setCmsTimeZoneService(final CmsTimeZoneService cmsTimeZoneService)
	{
		this.cmsTimeZoneService = cmsTimeZoneService;
	}

	public CmsTimeZoneService getCmsTimeZoneService()
	{
		return this.cmsTimeZoneService;
	}

	/**
	 * @return the timeService
	 */
	public TimeService getTimeService()
	{
		return timeService;
	}



	/**
	 * @param timeService
	 *           the timeService to set
	 */
	@Required
	public void setTimeService(final TimeService timeService)
	{
		this.timeService = timeService;
	}


}
