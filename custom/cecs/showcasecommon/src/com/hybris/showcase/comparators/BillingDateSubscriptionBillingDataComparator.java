/**
 * 
 */
package com.hybris.showcase.comparators;

import de.hybris.platform.subscriptionfacades.data.SubscriptionBillingData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;


/**
 * @author npavlovic
 * 
 *         CECS-271: Billings component is hardcoded
 */
public class BillingDateSubscriptionBillingDataComparator implements Comparator<SubscriptionBillingData>
{
	protected static final Logger LOG = Logger.getLogger(BillingDateSubscriptionBillingDataComparator.class);

	private static final String DATE_FORMAT = "dd MMMMM yyyy";

	@Override
	public int compare(final SubscriptionBillingData firstBilling, final SubscriptionBillingData secondBilling)
	{
		int compared = firstBilling.getBillingDate().compareTo(secondBilling.getBillingDate());

		try
		{
			final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
			final Date b1Date = dateFormat.parse(firstBilling.getBillingDate());
			final Date b2Date = dateFormat.parse(secondBilling.getBillingDate());

			compared = b1Date.compareTo(b2Date);
		}
		catch (final ParseException e)
		{
			LOG.warn("Error during parse billing dates", e);
		}

		return compared;
	}
}
