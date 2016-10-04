/**
 * 
 */
package com.hybris.productsets.services;

import de.hybris.platform.promotions.model.ProductSetPromotionModel;
import de.hybris.platform.promotions.model.PromotionGroupModel;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.hybris.productsets.model.ProductSetModel;


/**
 * @author rmcotton
 * 
 */
public interface ProductSetPromotionService
{
	List<ProductSetPromotionModel> getProductSetPromotions(Collection<PromotionGroupModel> promotionGroups, ProductSetModel set);

	List<ProductSetPromotionModel> getProductSetPromotions(final Collection<PromotionGroupModel> promotionGroups,
			final ProductSetModel set, final boolean evaluateRestrictions, Date date);
}
