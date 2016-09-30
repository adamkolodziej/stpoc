/**
 * 
 */
package com.hybris.showcase.showcasecockpits.services.label.impl;

import de.hybris.platform.cockpit.helpers.LocaleHelper;
import de.hybris.platform.cockpit.helpers.impl.DefaultLocaleHelper;
import de.hybris.platform.cockpit.services.label.AbstractModelLabelProvider;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.subscriptionservices.model.SubscriptionPricePlanModel;
import de.hybris.platform.subscriptionservices.model.UsageChargeModel;

import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;


/**
 * @author n.pavlovic
 * 
 */
public class SubscriptionPricePlanLabelProvider extends AbstractModelLabelProvider<SubscriptionPricePlanModel>
{
	private static final String RECURRING_LABEL = "RCE";
	private static final String ONETIME_LABEL = "OTCE";
	private static final String USAGE_LABEL = "UCE";

	@Override
	protected String getItemLabel(final SubscriptionPricePlanModel pricePlan)
	{
		final String name = pricePlan.getName();
		Double recurringPrice = null;
		Double oneTimePrice = null;
		Double usagePrice = null;
		if (pricePlan.getRecurringChargeEntries().iterator().hasNext())
		{
			recurringPrice = pricePlan.getRecurringChargeEntries().iterator().next().getPrice();
		}
		if (pricePlan.getOneTimeChargeEntries().iterator().hasNext())
		{
			oneTimePrice = pricePlan.getOneTimeChargeEntries().iterator().next().getPrice();
		}
		if (pricePlan.getUsageCharges().iterator().hasNext())
		{
			final UsageChargeModel usageCharge = pricePlan.getUsageCharges().iterator().next();
			if (usageCharge.getUsageChargeEntries().iterator().hasNext())
			{
				usagePrice = usageCharge.getUsageChargeEntries().iterator().next().getPrice();
			}
		}
		final String currency = pricePlan.getCurrency().getIsocode();
		final StringBuffer buf = new StringBuffer();

		if (StringUtils.isBlank(name))
		{
			if (recurringPrice != null)
			{
				buf.append(getPriceString(recurringPrice, RECURRING_LABEL));
			}
			if (oneTimePrice != null)
			{
				buf.append(getPriceString(oneTimePrice, ONETIME_LABEL));
			}
			if (usagePrice != null)
			{
				buf.append(getPriceString(usagePrice, USAGE_LABEL));
			}
			if (recurringPrice == null && oneTimePrice == null && usagePrice == null)
			{
				buf.append("? ");
			}
		}
		else
		{
			buf.append(name);
			buf.append(' ');
		}
		if (currency != null)
		{
			buf.append("- ");
			buf.append(currency);
		}

		return buf.toString();
	}

	private String getPriceString(final Double price, final String labelPrefix)
	{
		final StringBuffer buf = new StringBuffer();
		buf.append(labelPrefix);
		buf.append(':');
		buf.append(getFormattedPrice(price));
		buf.append(' ');
		return buf.toString();
	}

	@Override
	protected String getItemLabel(final SubscriptionPricePlanModel pricePlan, final String languageIso)
	{
		return getItemLabel(pricePlan);
	}

	@Override
	protected String getIconPath(final SubscriptionPricePlanModel item)
	{
		return null;
	}

	@Override
	protected String getIconPath(final SubscriptionPricePlanModel item, final String languageIso)
	{
		return null;
	}

	@Override
	protected String getItemDescription(final SubscriptionPricePlanModel item)
	{
		return "";
	}

	@Override
	protected String getItemDescription(final SubscriptionPricePlanModel item, final String languageIso)
	{
		return "";
	}

	private String getCurrentLangCode()
	{
		return UISessionUtils.getCurrentSession().getSystemService().getCurrentLanguage().getIsocode();
	}

	private String getFormattedPrice(final Double val)
	{
		final LocaleHelper localeHelper = new DefaultLocaleHelper();
		return NumberFormat.getInstance(localeHelper.getLocale(getCurrentLangCode())).format(val);
	}
}
