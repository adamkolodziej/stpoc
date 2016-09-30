/**
 *
 */
package com.hybris.showcase.guidedselling.daos;

import de.hybris.platform.configurablebundleservices.daos.BundleRuleDao;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

import com.hybris.showcase.guidedselling.model.ChangeProductPercentPriceBundleRuleModel;


/**
 *
 */
public interface ChangeProductPercentPriceBundleRuleDao extends BundleRuleDao<ChangeProductPercentPriceBundleRuleModel>
{
	public List<ChangeProductPercentPriceBundleRuleModel> findBundleRulesByTargetProduct(ProductModel paramProductModel,
			BundleTemplateModel paramBundleTemplateModel);
}