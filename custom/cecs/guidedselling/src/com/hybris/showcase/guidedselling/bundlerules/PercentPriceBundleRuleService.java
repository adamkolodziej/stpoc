/**
 *
 */
package com.hybris.showcase.guidedselling.bundlerules;

import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;

import com.hybris.showcase.guidedselling.model.ChangeProductPercentPriceBundleRuleModel;


/**
 *
 */
public interface PercentPriceBundleRuleService
{
	public ChangeProductPercentPriceBundleRuleModel getChangePriceBundleRuleForOrderEntry(
			AbstractOrderEntryModel paramAbstractOrderEntryModel);

	public ChangeProductPercentPriceBundleRuleModel getChangePriceBundleRule(AbstractOrderModel paramAbstractOrderModel,
			BundleTemplateModel paramBundleTemplateModel, ProductModel paramProductModel, int paramInt);

}
