package com.hybris.showcase.guidedselling.daos.impl;

import de.hybris.platform.configurablebundleservices.daos.impl.DefaultDisableProductBundleRuleDao;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.configurablebundleservices.model.DisableProductBundleRuleModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.guidedselling.bundlerules.BundleRulesRestrictionsService;


/**
 * Created by mgolubovic on 16.4.2015..
 */
public class RestrictionFilteringDisableProductBundleRuleDao extends DefaultDisableProductBundleRuleDao
{
	private BundleRulesRestrictionsService bundleRulesRestrictionsService;

	@Override
	public List<DisableProductBundleRuleModel> findBundleRulesByTargetProduct(final ProductModel var1)
	{
		return bundleRulesRestrictionsService.filter(super.findBundleRulesByTargetProduct(var1));
	}

	@Override
	public List<DisableProductBundleRuleModel> findBundleRulesByTargetProductAndTemplate(final ProductModel var1,
			final BundleTemplateModel var2)
	{
		return bundleRulesRestrictionsService.filter(super.findBundleRulesByTargetProductAndTemplate(var1, var2));
	}

	@Override
	public List<DisableProductBundleRuleModel> findBundleRulesByProductAndRootTemplate(final ProductModel var1,
			final BundleTemplateModel var2)
	{
		return bundleRulesRestrictionsService.filter(super.findBundleRulesByProductAndRootTemplate(var1, var2));
	}

	public BundleRulesRestrictionsService getBundleRulesRestrictionsService()
	{
		return bundleRulesRestrictionsService;
	}

	@Required
	public void setBundleRulesRestrictionsService(final BundleRulesRestrictionsService bundleRulesRestrictionsService)
	{
		this.bundleRulesRestrictionsService = bundleRulesRestrictionsService;
	}
}
