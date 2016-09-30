/**
 * 
 */
package com.hybris.showcase.guidedselling.bundlerules;

import de.hybris.platform.configurablebundleservices.model.ChangeProductPriceBundleRuleModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.util.DiscountValue;

import com.hybris.showcase.guidedselling.model.AbstractBundleRuleRestrictionModel;


public interface BundleRuleRestrictionEvaluator
{
	boolean accept(AbstractBundleRuleRestrictionModel restriction);

	Class<? extends AbstractBundleRuleRestrictionModel> getEvaluatedRestrictionClass();

	void applyDiscount(AbstractBundleRuleRestrictionModel restriction, AbstractOrderEntryModel entry,
			ChangeProductPriceBundleRuleModel priceRule, DiscountValue discount);
}
