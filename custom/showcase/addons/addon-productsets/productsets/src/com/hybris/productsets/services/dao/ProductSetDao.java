/**
 * 
 */
package com.hybris.productsets.services.dao;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.promotions.model.ProductSetPromotionModel;
import de.hybris.platform.promotions.model.PromotionGroupModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.hybris.productsets.enums.ProductSetType;
import com.hybris.productsets.model.ProductSetModel;


/**
 * @author dominik.strzyzewski
 * 
 */
public interface ProductSetDao extends Dao
{
	List<ProductSetModel> findSetsHavingAnyProduct(List<ProductModel> products);

	List<ProductSetModel> findSetsHavingAnyProductAndType(List<ProductModel> products, ProductSetType productSetType);

	ProductSetModel findSetByCode(String code);

	List<ProductSetPromotionModel> findPromotionsForSet(final ProductSetModel set,
			final Collection<PromotionGroupModel> promotionGroups, final Date date);
}
