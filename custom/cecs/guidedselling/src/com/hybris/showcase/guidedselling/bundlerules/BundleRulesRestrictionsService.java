/**
 * 
 */
package com.hybris.showcase.guidedselling.bundlerules;

import de.hybris.platform.configurablebundleservices.model.AbstractBundleRuleModel;

import java.util.List;

import com.hybris.showcase.guidedselling.model.AbstractBundleRuleRestrictionModel;


public interface BundleRulesRestrictionsService
{
	<T extends AbstractBundleRuleModel> List<T> filter(List<T> rules);

	BundleRuleRestrictionEvaluator findEvaluatorForRestriction(AbstractBundleRuleRestrictionModel restriction);
}
