/**
 * 
 */
package com.hybris.showcase.guidedselling.bundlerules.impl;

import de.hybris.platform.configurablebundleservices.model.ChangeProductPriceBundleRuleModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.DiscountValue;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.guidedselling.bundlerules.BundleRuleRestrictionEvaluator;
import com.hybris.showcase.guidedselling.model.AbstractBundleRuleRestrictionModel;
import com.hybris.showcase.guidedselling.model.RecurringChargeRestrictionModel;
import com.hybris.showcase.guidedselling.model.RestrictedDiscountModel;


public class RecurringChargeRestrictionEvaluator implements BundleRuleRestrictionEvaluator
{
	private ModelService modelService;

	@Override
	public boolean accept(final AbstractBundleRuleRestrictionModel restriction)
	{
		return true;
	}

	@Override
	public Class<? extends AbstractBundleRuleRestrictionModel> getEvaluatedRestrictionClass()
	{
		return RecurringChargeRestrictionModel.class;
	}

	@Override
	public void applyDiscount(final AbstractBundleRuleRestrictionModel restriction, final AbstractOrderEntryModel entry,
			final ChangeProductPriceBundleRuleModel priceRule, final DiscountValue discount)
	{
		final RestrictedDiscountModel restrictedDiscount = getModelService().create(RestrictedDiscountModel.class);
		restrictedDiscount.setDiscount(discount);
		restrictedDiscount.setRestriction(restriction);

		final Collection<RestrictedDiscountModel> restrictedDiscounts = new ArrayList<>(entry.getRestrictedDiscounts());
		restrictedDiscounts.add(restrictedDiscount);
		entry.setRestrictedDiscounts(restrictedDiscounts);
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}
}
