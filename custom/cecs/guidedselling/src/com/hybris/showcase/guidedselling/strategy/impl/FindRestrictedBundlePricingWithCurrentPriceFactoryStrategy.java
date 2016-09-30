/**
 * 
 */
package com.hybris.showcase.guidedselling.strategy.impl;

import de.hybris.platform.configurablebundleservices.bundle.impl.FindBundlePricingWithCurrentPriceFactoryStrategy;
import de.hybris.platform.configurablebundleservices.model.ChangeProductPriceBundleRuleModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.subscriptionservices.model.SubscriptionPricePlanModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import de.hybris.platform.util.DiscountValue;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.collect.Iterables;
import com.hybris.showcase.guidedselling.bundlerules.BundleRuleRestrictionEvaluator;
import com.hybris.showcase.guidedselling.bundlerules.BundleRulesRestrictionsService;
import com.hybris.showcase.guidedselling.model.AbstractBundleRuleRestrictionModel;


/**
 * @author n.pavlovic
 */
public class FindRestrictedBundlePricingWithCurrentPriceFactoryStrategy extends FindBundlePricingWithCurrentPriceFactoryStrategy
{
	private static final Logger LOG = Logger.getLogger(FindRestrictedBundlePricingWithCurrentPriceFactoryStrategy.class);
	private BundleRulesRestrictionsService bundleRulesRestrictionsService;

	@Nonnull
	@Override
	public List<DiscountValue> findDiscountValues(AbstractOrderEntryModel entry) throws CalculationException
	{
		final List<DiscountValue> discountValues = super.findDiscountValues(entry);

		// UGLY there was no other way to enhance implementation of discount values search for non subscription products
		// SEE: FindBundlePricingWithCurrentPriceFactoryStrategy
		if (!discountValues.isEmpty() && !(entry.getProduct() instanceof SubscriptionProductModel))
		{
			AbstractOrderEntryModel masterEntry = entry.getMasterEntry() == null ? entry : entry.getMasterEntry();
			Integer bundleNo = masterEntry.getBundleNo();
			if (bundleNo != null && bundleNo.intValue() >= 1)
			{
				if (entry.getBasePrice() != null && entry.getBasePrice().doubleValue() > 0.0D)
				{
					ChangeProductPriceBundleRuleModel priceRule = getBundleRuleService()
							.getChangePriceBundleRuleForOrderEntry(masterEntry);
					if (priceRule != null)
					{
						LOG.info("Entry " + entry.getEntryNumber() + " for product " + entry.getProduct().getCode()
								+ " affected by rule: " + priceRule.getId());
						entry.setPriceRule(priceRule);
					}
				}
			}
		}

		return discountValues;
	}

	@Override
	protected void reduceOneTimePrice(final SubscriptionPricePlanModel pricePlan,
			final ChangeProductPriceBundleRuleModel priceRule, final List<DiscountValue> discountValues,
			final CurrencyModel currency, final AbstractOrderEntryModel entry)
	{
		final int originalDiscountsSize = getDiscountSize(discountValues);
		super.reduceOneTimePrice(pricePlan, priceRule, discountValues, currency, entry);
		processDiscount(discountValues, originalDiscountsSize, priceRule, entry);
	}

	@Override
	protected void reduceRecurringPrice(final SubscriptionProductModel subscriptionProduct,
			final ChangeProductPriceBundleRuleModel priceRule, final List<DiscountValue> discountValues,
			final AbstractOrderEntryModel entry, final SubscriptionPricePlanModel pricePlan)
	{
		final int originalDiscountsSize = getDiscountSize(discountValues);
		super.reduceRecurringPrice(subscriptionProduct, priceRule, discountValues, entry, pricePlan);
		processDiscount(discountValues, originalDiscountsSize, priceRule, entry);
	}

	private int getDiscountSize(final Collection<DiscountValue> discountValues)
	{
		return CollectionUtils.isEmpty(discountValues) ? 0 : discountValues.size();
	}

	private void processDiscount(final Collection<DiscountValue> discountValues, final int originalDiscountsSize,
			final ChangeProductPriceBundleRuleModel priceRule, final AbstractOrderEntryModel entry)
	{
		// CECS-717 yBilling enhanced bundle rules
		if (priceRule != null)
		{
			LOG.info("Entry " + entry.getEntryNumber() + " for product " + entry.getProduct().getCode() + " affected by rule: "
					+ priceRule.getId());
			entry.setPriceRule(priceRule);
		}

		if (priceRule == null || CollectionUtils.isEmpty(priceRule.getBundleRuleRestrictions()))
		{
			return;
		}

		if (getDiscountSize(discountValues) == originalDiscountsSize)
		{
			return;
		}

		final DiscountValue discountAdded = Iterables.getLast(discountValues);
		for (final AbstractBundleRuleRestrictionModel restriction : priceRule.getBundleRuleRestrictions())
		{
			final BundleRuleRestrictionEvaluator evaluator = getBundleRulesRestrictionsService()
					.findEvaluatorForRestriction(restriction);
			evaluator.applyDiscount(restriction, entry, priceRule, discountAdded);
		}
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
