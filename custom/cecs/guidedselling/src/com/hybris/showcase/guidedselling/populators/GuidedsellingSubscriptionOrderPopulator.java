/**
 *
 */
package com.hybris.showcase.guidedselling.populators;

import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.subscriptionfacades.order.converters.populator.SubscriptionOrderPopulator;

import java.math.BigDecimal;

import javax.annotation.Resource;

import com.hybris.showcase.services.ShowcasecommonCalculationService;


/**
 * @author Adrian Sbarcea (SAP - i309441)
 *
 */
public class GuidedsellingSubscriptionOrderPopulator extends SubscriptionOrderPopulator
{

	@Resource(name = "showcasecommonCalculationService")
	ShowcasecommonCalculationService showcasecommonCalculationService;

	@Override
	protected PriceData createPrice(final AbstractOrderModel source, final Double val)
	{
		if (source == null)
		{
			throw new IllegalArgumentException("source order must not be null");
		}

		final CurrencyModel currency = source.getCurrency();
		if (currency == null)
		{
			throw new IllegalArgumentException("source order currency must not be null");
		}

		// Get double value, handle null as zero
		final double priceValue = val != null ? val.doubleValue() : 0d;

		return getPriceDataFactory().create(PriceDataType.BUY,
				new BigDecimal(
						getShowcasecommonCalculationService().convertAmountToDefaultCurrency(priceValue, currency.getIsocode())),
				getCommonI18NService().getCurrentCurrency().getIsocode());
	}

	/**
	 * @return the showcasecommonCalculationService
	 */
	public ShowcasecommonCalculationService getShowcasecommonCalculationService()
	{
		return showcasecommonCalculationService;
	}

}
