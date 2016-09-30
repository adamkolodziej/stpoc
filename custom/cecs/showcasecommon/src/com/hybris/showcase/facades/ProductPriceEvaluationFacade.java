/**
 *
 */
package com.hybris.showcase.facades;

import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PromotionData;


/**
 * @author Sebastian Weiner
 *
 */
public interface ProductPriceEvaluationFacade
{
	/**
	 * TODO: Seba
	 *
	 * @param promotionData
	 * @return
	 */
	PriceData getPriceDataAfterPromotionEvaluation(PromotionData promotionData, PriceData priceDataBefore);
}
