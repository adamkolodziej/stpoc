/**
 * 
 */
package com.hybris.campaigns;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.promotions.model.ProductPromotionModel;

import java.util.Collection;
import java.util.List;
import java.util.Set;


/**
 * @author rmcotton
 * 
 */
public interface PromotionMerchandizingService
{
	Set<ProductModel> getApplicableProductsFromPromotion(final ProductPromotionModel promotion);

	<T extends AbstractPromotionModel> List<T> filterPromotionsByRestrictions(final List<T> allPromotions,
			final List<ProductModel> products);

	<T extends AbstractPromotionModel> List<T> filterPromotionsByRestrictions(final List<T> allPromotions);

	boolean isPastPromotion(AbstractPromotionModel promotion);

	boolean isFuturePromotion(AbstractPromotionModel promotion);

	boolean isAppliedPromotion(AbstractPromotionModel promotion);

	/**
	 * Supports Variant Rollup and Pluggable promotion Groups.
	 */
	Collection<ProductPromotionModel> getProductPromotions(ProductModel product, boolean evaluateRestrictions);
}
