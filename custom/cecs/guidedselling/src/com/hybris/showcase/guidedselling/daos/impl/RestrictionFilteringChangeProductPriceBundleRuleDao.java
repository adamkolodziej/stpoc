/**
 * 
 */
package com.hybris.showcase.guidedselling.daos.impl;

import de.hybris.platform.configurablebundleservices.daos.impl.DefaultChangeProductPriceBundleRuleDao;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.configurablebundleservices.model.ChangeProductPriceBundleRuleModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.guidedselling.bundlerules.BundleRulesRestrictionsService;


/**
 * @author npavlovic CECS-129: Guidedselling - change price - TCO promotion(3 months free)
 */
public class RestrictionFilteringChangeProductPriceBundleRuleDao extends DefaultChangeProductPriceBundleRuleDao
{
	private BundleRulesRestrictionsService bundleRulesRestrictionsService;

	@Override
	public List<ChangeProductPriceBundleRuleModel> findBundleRulesByTargetProductAndCurrency(final ProductModel targetProduct,
			final CurrencyModel currency)
	{
		final List<ChangeProductPriceBundleRuleModel> rules = super.findBundleRulesByTargetProductAndCurrency(targetProduct,
				currency);
		return getBundleRulesRestrictionsService().filter(rules);
	}

	@Override
	public List<ChangeProductPriceBundleRuleModel> findBundleRulesByTargetProductAndTemplateAndCurrency(
			final ProductModel targetProduct, final BundleTemplateModel bundleTemplate, final CurrencyModel currency)
	{
		final List<ChangeProductPriceBundleRuleModel> rules = super.findBundleRulesByTargetProductAndTemplateAndCurrency(
				targetProduct, bundleTemplate, currency);
		return getBundleRulesRestrictionsService().filter(rules);
	}

	@Override
	public List<ChangeProductPriceBundleRuleModel> findBundleRulesByTargetProductAndTemplate(final ProductModel targetProduct,
			final BundleTemplateModel bundleTemplate)
	{
		return getBundleRulesRestrictionsService().filter(
				super.findBundleRulesByTargetProductAndTemplate(targetProduct, bundleTemplate));
	}

	protected BundleRulesRestrictionsService getBundleRulesRestrictionsService()
	{
		return bundleRulesRestrictionsService;
	}

	@Required
	public void setBundleRulesRestrictionsService(final BundleRulesRestrictionsService bundleRulesRestrictionsService)
	{
		this.bundleRulesRestrictionsService = bundleRulesRestrictionsService;
	}
}
