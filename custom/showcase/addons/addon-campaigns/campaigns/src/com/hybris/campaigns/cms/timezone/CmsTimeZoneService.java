/**
 *
 */
package com.hybris.campaigns.cms.timezone;

import de.hybris.platform.basecommerce.enums.WeekDay;

import java.util.Collection;
import java.util.Date;

import com.hybris.campaigns.enums.Timezone;



/**
 * @author rmcotton
 * 
 */
public interface CmsTimeZoneService
{
	int getTimeZoneOffset(final Timezone timezone);

	void setSessionTimeZoneOffset(Timezone timezone);

	Date getTimeForTimeZone(final Timezone timezone, final Date now);


	boolean evaluate(final Date now, final Integer fromHr24, final Integer untilHr24, final Timezone timezone,
			final Collection<WeekDay> days);
}
