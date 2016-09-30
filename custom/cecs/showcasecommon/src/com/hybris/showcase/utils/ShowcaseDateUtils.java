/**
 *
 */
package com.hybris.showcase.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;


/**
 * @author Rafal Zymla
 *
 */
public class ShowcaseDateUtils
{

	public static Date getLastDayOfMonth(final int year, final int month)
	{

		final Calendar calendar = Calendar.getInstance();

		calendar.set(year, month, 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		final Date date = calendar.getTime();
		return date;
	}

	public static Date getFirstDayOfMonth(final int year, final int month)
	{
		final Calendar calendar = Calendar.getInstance();

		calendar.set(year, month, 1);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		final Date date = calendar.getTime();
		return date;
	}

	public static Date getFirstDayOfMonth(final Date date)
	{

		final ZoneId zone = ZoneId.systemDefault();
		final LocalDateTime localDate = LocalDateTime.ofInstant(date.toInstant(), zone);
		final LocalDateTime firstDay = localDate.withDayOfMonth(1);
		return Date.from(firstDay.atZone(zone).toInstant());
	}

	public static Date getLastDayOfMonth(final Date date)
	{
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));

		return calendar.getTime();
	}

	public static int getMonth(final Date date)
	{
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	public static int getYear(final Date date)
	{
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}


}
