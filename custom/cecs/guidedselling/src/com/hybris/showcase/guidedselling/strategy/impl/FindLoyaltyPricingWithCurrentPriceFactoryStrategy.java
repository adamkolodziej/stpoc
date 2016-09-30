/**
 * 
 */
package com.hybris.showcase.guidedselling.strategy.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.util.PriceValue;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.loyaltypoints.constants.LoyaltypointsConstants;
import com.hybris.showcase.loyaltypoints.services.LoyaltyPointsService;


/**
 * @author n.pavlovic
 * 
 */
public class FindLoyaltyPricingWithCurrentPriceFactoryStrategy extends FindRestrictedBundlePricingWithCurrentPriceFactoryStrategy
{
	private LoyaltyPointsService loyaltyPointsService;

	@Override
	public PriceValue findBasePrice(final AbstractOrderEntryModel entry) throws CalculationException
	{
		if (entry.isLoyaltyPayment())
		{
			// setting loyalty points price for entry
			final AbstractOrderModel order = entry.getOrder();
			final PriceValue loyaltyPointsPrice = findLoyaltyPrice(entry);
			if (loyaltyPointsPrice != null)
			{
				entry.setLoyaltyPoints(Double.valueOf(loyaltyPointsPrice.getValue()));
			}

			// modifying the actual price to 0.0
			return new PriceValue(order.getCurrency().getIsocode(), 0.0D, order.getNet().booleanValue());
		}
		else
		{
			entry.setLoyaltyPoints(Double.valueOf(0.0D));
			return super.findBasePrice(entry);
		}
	}

	protected PriceValue findLoyaltyPrice(final AbstractOrderEntryModel entry)
	{
		final Double loyaltyPointsPrice = loyaltyPointsService.getLoyaltyPointsPriceForProduct(entry.getProduct(), entry
				.getOrder().getBillingTime().getCode());
		return new PriceValue(LoyaltypointsConstants.CRD_CURRENCY_ISO, loyaltyPointsPrice.doubleValue(), entry.getOrder().getNet()
				.booleanValue());
	}

	public LoyaltyPointsService getLoyaltyPointsService()
	{
		return loyaltyPointsService;
	}

	@Required
	public void setLoyaltyPointsService(final LoyaltyPointsService loyaltyPointsService)
	{
		this.loyaltyPointsService = loyaltyPointsService;
	}
}
