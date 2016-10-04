/**
 * 
 */
package com.hybris.campaigns;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.promotions.jalo.AbstractPromotionRestriction.RestrictionResult;
import de.hybris.platform.promotions.model.AbstractPromotionRestrictionModel;

import java.util.Collection;
import java.util.Date;


/**
 * @author rmcotton
 * 
 */
public interface PromotionRestrictionStrategy<R extends AbstractPromotionRestrictionModel>
{
	PromotionRestrictionStrategyResult evaluate(final R restriction, final Collection<ProductModel> products, final Date date,
			final AbstractOrderModel order);

	interface PromotionRestrictionStrategyResult
	{
		RestrictionResult getRestrictionResult();

		Collection<ProductModel> getProductsToRemove();

		Collection<ProductModel> getProductsToAdd();


	}
}
