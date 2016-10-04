/**
 *
 */
package com.hybris.campaigns.cms.timezone.impl;

import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.campaigns.cms.timezone.CmsTimeZoneService;
import com.hybris.campaigns.enums.Timezone;



/**
 * @author rmcotton
 * @author radek
 */
public class DefaultCmsTimeZoneService implements CmsTimeZoneService
{

	private SessionService sessionService;

	@Override
	public int getTimeZoneOffset(final Timezone timezone)
	{
		if (timezone == null)
		{
			return 0;
		}

		switch (timezone)
		{
			case SYSTEM:
				return new Date().getTimezoneOffset();
			case UTC_MIN_12_00:
				return -720;
			case UTC_MIN_11_00:
				return -660;
			case UTC_MIN_10_00:
				return -600;
			case UTC_MIN_09_30:
				return -570;
			case UTC_MIN_09_00:
				return -540;
			case UTC_MIN_08_00:
				return -480;
			case UTC_MIN_07_00:
				return -420;
			case UTC_MIN_06_00:
				return -360;
			case UTC_MIN_05_00:
				return -300;
			case UTC_MIN_04_30:
				return -270;
			case UTC_MIN_04_00:
				return -240;
			case UTC_MIN_03_30:
				return -210;
			case UTC_MIN_03_00:
				return -180;
			case UTC_MIN_02_00:
				return -120;
			case UTC_MIN_01_00:
				return -60;
			case UTC_00_00:
				return 0;
			case UTC_PLUS_01_00:
				return 60;
			case UTC_PLUS_02_00:
				return 120;
			case UTC_PLUS_03_00:
				return 180;
			case UTC_PLUS_03_30:
				return 210;
			case UTC_PLUS_04_00:
				return 240;
			case UTC_PLUS_04_30:
				return 270;
			case UTC_PLUS_05_00:
				return 300;
			case UTC_PLUS_05_30:
				return 330;
			case UTC_PLUS_05_45:
				return 345;
			case UTC_PLUS_06_00:
				return 360;
			case UTC_PLUS_06_30:
				return 390;
			case UTC_PLUS_07_00:
				return 420;
			case UTC_PLUS_08_00:
				return 480;
			case UTC_PLUS_09_00:
				return 540;
			case UTC_PLUS_09_30:
				return 570;
			case UTC_PLUS_10_00:
				return 600;
			case UTC_PLUS_10_30:
				return 630;
			case UTC_PLUS_11_00:
				return 660;
			case UTC_PLUS_11_30:
				return 690;
			case UTC_PLUS_12_00:
				return 720;
			case UTC_PLUS_12_45:
				return 765;
			case UTC_PLUS_13_00:
				return 780;
			case UTC_PLUS_14_00:
				return 840;
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.showcase.cms.timezone.CmsTimeZoneService#setSessionTimeZoneOffset(de.hybris.showcase.cms.enums.Timezone)
	 */
	@Override
	public void setSessionTimeZoneOffset(final Timezone timezone)
	{
		getSessionService().setAttribute("TimeZoneOffset", timezone != null ? Integer.valueOf(getTimeZoneOffset(timezone)) : null);
	}

	public Integer getTimeZoneOffset()
	{
		return (Integer) sessionService.getAttribute("TimeZoneOffset");
	}

	@Override
	public Date getTimeForTimeZone(final Timezone timezone, final Date time)
	{
		return getCurrentCalendarForTimeZone(timezone, time).getTime();
	}

	protected Calendar getCurrentCalendarForTimeZone(final Timezone timezone, final Date now)
	{
		final Integer timeZoneOffset = getTimeZoneOffset();
		final Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		if (timeZoneOffset != null)
		{
			// converts cal to GMT
			cal.add(Calendar.MINUTE, -1 * timeZoneOffset.intValue());
		}
		else
		{
			// converts cal to GMT
			cal.add(Calendar.MINUTE, -1 * now.getTimezoneOffset());
		}
		cal.add(Calendar.MINUTE, getTimeZoneOffset(timezone));
		return cal;
	}

	@Override
	public boolean evaluate(final Date now, Integer fromHr24, Integer untilHr24, final Timezone timezone,
			final Collection<WeekDay> days)
	{
		if (fromHr24 != null && untilHr24 != null && timezone != null && days != null)
		{
			final Calendar cal = getCurrentCalendarForTimeZone(timezone, now);

			final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			// check day of week
			if (validatedDayOfWeek(dayOfWeek, days))
			{
				// check time of a day
				final int hour = cal.get(Calendar.HOUR_OF_DAY);
				final int minute = cal.get(Calendar.MINUTE);

				final int timeOfDay = hour * 100 + minute;

				// just in case it is only hh and not hhmm
				if (fromHr24.intValue() < 100)
				{
					fromHr24 = Integer.valueOf(100 * fromHr24.intValue());
				}

				// just in case it is only hh and not hhmm
				if (untilHr24.intValue() < 100)
				{
					untilHr24 = Integer.valueOf(100 * untilHr24.intValue());
				}

				if (timeOfDay >= fromHr24.intValue() && timeOfDay <= untilHr24.intValue())
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * @param dayOfWeek
	 * @param days
	 * @return
	 */
	private boolean validatedDayOfWeek(final int dayOfWeek, final Collection<WeekDay> days)
	{
		switch (dayOfWeek)
		{
			case Calendar.SUNDAY:
				if (days.contains(WeekDay.SUNDAY))
				{
					return true;
				}
				break;
			case Calendar.MONDAY:
				if (days.contains(WeekDay.MONDAY))
				{
					return true;
				}
				break;
			case Calendar.TUESDAY:
				if (days.contains(WeekDay.TUESDAY))
				{
					return true;
				}
				break;
			case Calendar.WEDNESDAY:
				if (days.contains(WeekDay.WEDNESDAY))
				{
					return true;
				}
				break;
			case Calendar.THURSDAY:
				if (days.contains(WeekDay.THURSDAY))
				{
					return true;
				}
				break;
			case Calendar.FRIDAY:
				if (days.contains(WeekDay.FRIDAY))
				{
					return true;
				}
				break;
			case Calendar.SATURDAY:
				if (days.contains(WeekDay.SATURDAY))
				{
					return true;
				}
				break;
			default:
				break;
		}
		return false;
	}

	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}
}
