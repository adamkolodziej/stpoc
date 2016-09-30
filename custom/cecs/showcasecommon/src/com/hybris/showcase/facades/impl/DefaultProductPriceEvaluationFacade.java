/**
 *
 */
package com.hybris.showcase.facades.impl;

import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.PromotionData;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.annotation.Resource;

import com.hybris.showcase.facades.ProductPriceEvaluationFacade;


/**
 * @author Sebastian Weiner
 *
 */
public class DefaultProductPriceEvaluationFacade implements ProductPriceEvaluationFacade
{

	@Resource(name = "priceDataFactory")
	private PriceDataFactory priceDataFactory;


	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hybris.showcase.facades.ProductPriceEvaluationFacade#getPriceDataAfterPromotionEvaluation(de.hybris.platform
	 * .commercefacades.product.data.ProductData)
	 */
	@Override
	public PriceData getPriceDataAfterPromotionEvaluation(final PromotionData promotionData, final PriceData priceDataBefore)
	{
		BigDecimal discount = BigDecimal.ZERO;

		final double percentageDiscount = promotionData.getPercentageDiscount().doubleValue();

		discount = discount.add(priceDataBefore.getValue().multiply(new BigDecimal(percentageDiscount))
				.divide(BigDecimal.valueOf(100)));

		discount = discount.setScale(2, RoundingMode.HALF_UP);

		final BigDecimal value = priceDataBefore.getValue().subtract(discount);

		final PriceData priceDataAfter = getPriceDataFactory().create(PriceDataType.BUY, value, priceDataBefore.getCurrencyIso());

		return priceDataAfter;
	}


	/**
	 * @return the priceDataFactory
	 */
	public PriceDataFactory getPriceDataFactory()
	{
		return priceDataFactory;
	}

	/**
	 * @param priceDataFactory
	 *           the priceDataFactory to set
	 */
	public void setPriceDataFactory(final PriceDataFactory priceDataFactory)
	{
		this.priceDataFactory = priceDataFactory;
	}
}
