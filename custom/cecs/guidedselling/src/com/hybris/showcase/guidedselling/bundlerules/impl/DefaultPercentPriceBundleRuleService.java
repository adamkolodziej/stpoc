/**
 *
 */
package com.hybris.showcase.guidedselling.bundlerules.impl;

import de.hybris.platform.configurablebundleservices.daos.OrderEntryDao;
import de.hybris.platform.configurablebundleservices.enums.BundleRuleTypeEnum;
import de.hybris.platform.configurablebundleservices.model.AbstractBundleRuleModel;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.configurablebundleservices.model.ChangeProductPriceBundleRuleModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.guidedselling.bundlerules.PercentPriceBundleRuleService;
import com.hybris.showcase.guidedselling.daos.ChangeProductPercentPriceBundleRuleDao;
import com.hybris.showcase.guidedselling.model.ChangeProductPercentPriceBundleRuleModel;


/**
 *
 */
public class DefaultPercentPriceBundleRuleService implements PercentPriceBundleRuleService
{

	private OrderEntryDao cartEntryDao;
	private ChangeProductPercentPriceBundleRuleDao changeProductPercentPriceBundleRuleDao;

	public OrderEntryDao getCartEntryDao()
	{
		return cartEntryDao;
	}

	@Required
	public void setCartEntryDao(final OrderEntryDao cartEntryDao)
	{
		this.cartEntryDao = cartEntryDao;
	}

	public ChangeProductPercentPriceBundleRuleDao getChangeProductPercentPriceBundleRuleDao()
	{
		return changeProductPercentPriceBundleRuleDao;
	}

	@Required
	public void setChangeProductPercentPriceBundleRuleDao(
			final ChangeProductPercentPriceBundleRuleDao changeProductPercentPriceBundleRuleDao)
	{
		this.changeProductPercentPriceBundleRuleDao = changeProductPercentPriceBundleRuleDao;
	}

	@Override
	public ChangeProductPercentPriceBundleRuleModel getChangePriceBundleRuleForOrderEntry(final AbstractOrderEntryModel entry)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("entry", entry);
		ServicesUtil.validateParameterNotNull(entry.getBundleTemplate(), "Cart entry model does not have a bundle template");

		final Integer bundleNo = entry.getBundleNo();

		if (bundleNo == null || bundleNo.intValue() == 0)
		{
			return null;
		}

		final ProductModel targetProduct = entry.getProduct();
		final AbstractOrderModel masterAbstractOrder = (entry.getOrder().getParent() == null) ? entry.getOrder()
				: entry.getOrder().getParent();
		final Set otherProductsInSameBundle = getCartProductsInSameBundle(masterAbstractOrder, targetProduct, bundleNo.intValue());

		return getLowestPriceForTargetProductAndTemplate(entry.getBundleTemplate(), targetProduct, otherProductsInSameBundle);
	}

	@Override
	public ChangeProductPercentPriceBundleRuleModel getChangePriceBundleRule(final AbstractOrderModel masterAbstractOrder,
			final BundleTemplateModel bundleTemplate, final ProductModel targetProduct, final int bundleNo)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("masterAbstractOrder", masterAbstractOrder);
		ServicesUtil.validateParameterNotNullStandardMessage("bundleTemplate", bundleTemplate);
		ServicesUtil.validateParameterNotNullStandardMessage("targetProduct", targetProduct);

		final Set otherProductsInSameBundle = getCartProductsInSameBundle(masterAbstractOrder, null, bundleNo);

		return getLowestPriceForTargetProductAndTemplate(bundleTemplate, targetProduct, otherProductsInSameBundle);
	}

	protected Set<ProductModel> getCartProductsInSameBundle(final AbstractOrderModel masterAbstractOrder,
			final ProductModel product, final int bundleNo)
	{
		final Set productInSameBundle = new HashSet();

		if (bundleNo == 0)
		{
			return productInSameBundle;
		}

		final List<AbstractOrderEntryModel> abstractOrderEntries = getCartEntryDao()
				.findEntriesByMasterCartAndBundleNo(masterAbstractOrder, bundleNo);

		for (final AbstractOrderEntryModel entry : abstractOrderEntries)
		{
			if (!entry.getProduct().equals(product))
			{
				productInSameBundle.add(entry.getProduct());
			}
		}

		return productInSameBundle;
	}

	protected ChangeProductPercentPriceBundleRuleModel getLowestPriceForTargetProductAndTemplate(
			final BundleTemplateModel bundleTemplate, final ProductModel targetProduct,
			final Set<ProductModel> otherProductsInSameBundle)
	{
		ChangeProductPercentPriceBundleRuleModel lowestPriceRule = null;

		final List<ChangeProductPercentPriceBundleRuleModel> priceRules = getChangeProductPercentPriceBundleRuleDao()
				.findBundleRulesByTargetProductAndTemplate(targetProduct, bundleTemplate);

		for (final ChangeProductPercentPriceBundleRuleModel priceRule : priceRules)
		{
			final boolean isRuleApplicable = checkBundleRuleForTargetProduct(priceRule, otherProductsInSameBundle);

			if (isRuleApplicable && (lowestPriceRule == null
					|| priceRule.getPercentageDiscount().compareTo(lowestPriceRule.getPercentageDiscount()) <= 0))
			{
				lowestPriceRule = priceRule;
			}
		}

		return lowestPriceRule;
	}

	protected boolean checkBundleRuleForTargetProduct(final AbstractBundleRuleModel rule,
			final Set<ProductModel> otherProductsInSameBundle)
	{
		boolean isRuleApplicable = false;

		if (rule instanceof ChangeProductPriceBundleRuleModel && rule.getConditionalProducts().isEmpty())
		{
			isRuleApplicable = true;
		}
		else if (rule.getRuleType() == null || BundleRuleTypeEnum.ANY.equals(rule.getRuleType()))
		{
			final Set intersection = new HashSet(otherProductsInSameBundle);
			intersection.retainAll(rule.getConditionalProducts());
			if (!intersection.isEmpty())
			{
				isRuleApplicable = true;
			}
		}
		else if (BundleRuleTypeEnum.ALL.equals(rule.getRuleType())
				&& otherProductsInSameBundle.containsAll(rule.getConditionalProducts()))
		{
			isRuleApplicable = true;
		}

		return isRuleApplicable;
	}

}
