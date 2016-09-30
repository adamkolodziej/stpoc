/**
 * 
 */
package com.hybris.showcase.guidedselling.bundlerules.impl;

import de.hybris.platform.configurablebundleservices.model.AbstractBundleRuleModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.hybris.showcase.guidedselling.bundlerules.BundleRuleRestrictionEvaluator;
import com.hybris.showcase.guidedselling.bundlerules.BundleRulesRestrictionsService;
import com.hybris.showcase.guidedselling.model.AbstractBundleRuleRestrictionModel;


public class DefaultBundleRulesRestrictionsService implements BundleRulesRestrictionsService
{
	private Collection<BundleRuleRestrictionEvaluator> restrictionEvaluators;

	@Override
	public <T extends AbstractBundleRuleModel> List<T> filter(final List<T> rules)
	{
		final List<T> result = new ArrayList<>();
		for (final T rule : rules)
		{
			if (isRuleAccepted(rule))
			{
				result.add(rule);
			}
		}
		return result;
	}

	private boolean isRuleAccepted(final AbstractBundleRuleModel rule)
	{
		if (CollectionUtils.isEmpty(rule.getBundleRuleRestrictions()))
		{
			return true;
		}
		for (final AbstractBundleRuleRestrictionModel restriction : rule.getBundleRuleRestrictions())
		{
			final BundleRuleRestrictionEvaluator evaluator = findEvaluatorForRestriction(restriction);
			if (!evaluator.accept(restriction))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public BundleRuleRestrictionEvaluator findEvaluatorForRestriction(final AbstractBundleRuleRestrictionModel restriction)
	{
		try
		{
			return Iterables.find(getRestrictionEvaluators(), new Predicate<BundleRuleRestrictionEvaluator>()
			{
				@Override
				public boolean apply(final BundleRuleRestrictionEvaluator evaluator)
				{
					return evaluator.getEvaluatedRestrictionClass().equals(restriction.getClass());
				}
			});
		}
		catch (final NoSuchElementException e)
		{
			throw new IllegalStateException(String.format("No evaluator configured for restriction: %s", restriction.getClass()
					.getName()), e);
		}
	}

	public Collection<BundleRuleRestrictionEvaluator> getRestrictionEvaluators()
	{
		return restrictionEvaluators;
	}

	@Required
	public void setRestrictionEvaluators(final Collection<BundleRuleRestrictionEvaluator> restrictionEvaluators)
	{
		this.restrictionEvaluators = restrictionEvaluators;
	}
}
