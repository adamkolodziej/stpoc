/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.hybris.showcase.guidedselling.services.impl;

import de.hybris.platform.configurablebundleservices.bundle.impl.DefaultBundleRuleService;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.configurablebundleservices.model.ChangeProductPriceBundleRuleModel;
import de.hybris.platform.configurablebundleservices.model.DisableProductBundleRuleModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.guidedselling.bundlerules.BundleRulesRestrictionsService;


/**
 * 
 * @author miroslaw.szot
 */
public class MultiCurrencyBundleRuleService extends DefaultBundleRuleService
{
	private CommonI18NService commonI18NService;
	private BundleRulesRestrictionsService bundleRulesRestrictionsService;

	@Override
	protected ChangeProductPriceBundleRuleModel getLowestPriceForTargetProductAndTemplate(
			final BundleTemplateModel bundleTemplate, final ProductModel targetProduct, final CurrencyModel currency,
			final Set<ProductModel> otherProductsInSameBundle)
	{
		ChangeProductPriceBundleRuleModel lowestPriceRule = super.getLowestPriceForTargetProductAndTemplate(bundleTemplate,
				targetProduct, currency, otherProductsInSameBundle);

		if (lowestPriceRule == null)
		{
			lowestPriceRule = getLowestPriceWithConversion(bundleTemplate, targetProduct, currency, otherProductsInSameBundle,
					lowestPriceRule);
		}

		return lowestPriceRule;
	}

	@Override
	public ChangeProductPriceBundleRuleModel getChangePriceBundleRuleWithLowestPrice(final ProductModel targetProduct,
			final CurrencyModel currency)
	{
		ChangeProductPriceBundleRuleModel lowestPriceRule = super.getChangePriceBundleRuleWithLowestPrice(targetProduct, currency);

		if (lowestPriceRule == null)
		{
			lowestPriceRule = getLowestPriceWithConversion(targetProduct, currency, lowestPriceRule);
		}
		return lowestPriceRule;
	}

	@Override
	public DisableProductBundleRuleModel getDisableRuleForBundleProduct(final AbstractOrderModel masterAbstractOrder,
			final ProductModel product, final BundleTemplateModel bundleTemplate, final int bundleNo,
			final boolean ignoreCurrentProducts)
	{
		final DisableProductBundleRuleModel disableRule = super.getDisableRuleForBundleProduct(masterAbstractOrder, product,
				bundleTemplate, bundleNo, ignoreCurrentProducts);
		if (disableRule != null)
		{
			final List<DisableProductBundleRuleModel> disableRuleList = new ArrayList<>();
			disableRuleList.add(disableRule);
			if (CollectionUtils.isNotEmpty(getBundleRulesRestrictionsService().filter(disableRuleList)))
			{
				return disableRule;
			}
		}
		return null;
	}

	@Override
	public DisableProductBundleRuleModel getDisableRuleForBundleProduct(final BundleTemplateModel bundleTemplate,
			final ProductModel product1, final ProductModel product2)
	{
		final DisableProductBundleRuleModel disableRule = super.getDisableRuleForBundleProduct(bundleTemplate, product1, product2);
		if (disableRule != null)
		{
			final List<DisableProductBundleRuleModel> disableRuleList = new ArrayList<>();
			disableRuleList.add(disableRule);
			if (CollectionUtils.isNotEmpty(getBundleRulesRestrictionsService().filter(disableRuleList)))
			{
				return disableRule;
			}
		}
		return null;
	}

	protected ChangeProductPriceBundleRuleModel getLowestPriceWithConversion(final ProductModel targetProduct,
			final CurrencyModel currency, ChangeProductPriceBundleRuleModel lowestPriceRule)
	{
		final List<ChangeProductPriceBundleRuleModel> priceRules = getChangeProductPriceBundleRuleDao()
				.findBundleRulesByTargetProduct(targetProduct);

		for (final ChangeProductPriceBundleRuleModel priceRule : priceRules)
		{
			if (priceRule.getBundleTemplate() != null
					&& (lowestPriceRule == null || priceRule.getPrice().compareTo(lowestPriceRule.getPrice()) < 0))
			{
				if (!currency.equals(priceRule.getCurrency()))
				{
					final double convertedValue = convertPrice(currency, priceRule);

					if (lowestPriceRule == null || convertedValue < lowestPriceRule.getPrice().doubleValue())
					{
						lowestPriceRule = priceRule;
					}
				}

				lowestPriceRule = priceRule;
			}
		}
		return lowestPriceRule;
	}

	protected ChangeProductPriceBundleRuleModel getLowestPriceWithConversion(final BundleTemplateModel bundleTemplate,
			final ProductModel targetProduct, final CurrencyModel currency, final Set<ProductModel> otherProductsInSameBundle,
			ChangeProductPriceBundleRuleModel lowestPriceRule)
	{
		final List<ChangeProductPriceBundleRuleModel> priceRules = getChangeProductPriceBundleRuleDao()
				.findBundleRulesByTargetProductAndTemplate(targetProduct, bundleTemplate);
		for (final ChangeProductPriceBundleRuleModel priceRule : priceRules)
		{
			final boolean isRuleApplicable = checkBundleRuleForTargetProduct(priceRule, otherProductsInSameBundle);

			if (isRuleApplicable && (lowestPriceRule == null || priceRule.getPrice().compareTo(lowestPriceRule.getPrice()) < 0))
			{
				if (!currency.equals(priceRule.getCurrency()))
				{
					final double convertedValue = convertPrice(currency, priceRule);

					if (lowestPriceRule == null || convertedValue < lowestPriceRule.getPrice().doubleValue())
					{
						lowestPriceRule = priceRule;
					}
				}

				lowestPriceRule = priceRule;
			}
		}
		return lowestPriceRule;
	}

	protected double convertPrice(final CurrencyModel targetCurrency, final ChangeProductPriceBundleRuleModel rule)
	{
		final CurrencyModel sourceCurrency = rule.getCurrency();
		final double price = rule.getPrice().doubleValue();
		return convertPrice(sourceCurrency, targetCurrency, price);
	}

	protected double convertPrice(final CurrencyModel sourceCurrency, final CurrencyModel targetCurrency, final double price)
	{
		final double sourceFactor = sourceCurrency.getConversion().doubleValue();
		final double targetFactor = targetCurrency.getConversion().doubleValue();

		return getCommonI18NService().convertCurrency(sourceFactor, targetFactor, price);
	}

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
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
