package com.hybris.showcase.guidedselling.populators;

import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.configurablebundleservices.bundle.BundleRuleService;
import de.hybris.platform.configurablebundleservices.model.AutoPickBundleSelectionCriteriaModel;
import de.hybris.platform.configurablebundleservices.model.BundleSelectionCriteriaModel;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.configurablebundleservices.model.ChangeProductPriceBundleRuleModel;
import de.hybris.platform.configurablebundleservices.model.PickExactlyNBundleSelectionCriteriaModel;
import de.hybris.platform.configurablebundleservices.model.PickNToMBundleSelectionCriteriaModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.subscriptionfacades.data.SubscriptionPricePlanData;
import de.hybris.platform.subscriptionfacades.data.UsageChargeData;
import de.hybris.platform.subscriptionfacades.data.UsageChargeEntryData;
import de.hybris.platform.subscriptionservices.model.RecurringChargeEntryModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionPricePlanModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import de.hybris.platform.subscriptionservices.price.SubscriptionCommercePriceService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.guidedselling.bundlerules.PercentPriceBundleRuleService;
import com.hybris.showcase.guidedselling.data.BundleComponentData;
import com.hybris.showcase.guidedselling.data.BundleComponentOptionData;
import com.hybris.showcase.guidedselling.data.BundleOfferData;
import com.hybris.showcase.guidedselling.data.BundleOfferPopulatorParameters;
import com.hybris.showcase.guidedselling.data.ComponentData;
import com.hybris.showcase.guidedselling.data.ConfigMessage;
import com.hybris.showcase.guidedselling.data.SelectionMode;
import com.hybris.showcase.guidedselling.enums.BundleComponentType;
import com.hybris.showcase.guidedselling.model.ChangeProductPercentPriceBundleRuleModel;


/**
 * Created by miroslaw.szot@sap.com on 2015-03-13.
 */
public class BundleComponentPopulator implements Populator<BundleOfferPopulatorParameters, BundleOfferData>
{
	protected class PriceDataAndRule
	{
		private PriceData priceData;
		private ChangeProductPriceBundleRuleModel changeProductPriceBundleRuleModel;
		private ChangeProductPercentPriceBundleRuleModel changeProductPercentPriceBundleRuleModel;

		public PriceData getPriceData()
		{
			return priceData;
		}

		public void setPriceData(final PriceData priceData)
		{
			this.priceData = priceData;
		}

		public ChangeProductPriceBundleRuleModel getChangeProductPriceBundleRuleModel()
		{
			return changeProductPriceBundleRuleModel;
		}

		public void setChangeProductPriceBundleRuleModel(final ChangeProductPriceBundleRuleModel changeProductPriceBundleRuleModel)
		{
			this.changeProductPriceBundleRuleModel = changeProductPriceBundleRuleModel;
		}

		public ChangeProductPercentPriceBundleRuleModel getChangeProductPercentPriceBundleRuleModel()
		{
			return changeProductPercentPriceBundleRuleModel;
		}

		public void setChangeProductPercentPriceBundleRuleModel(
				final ChangeProductPercentPriceBundleRuleModel changeProductPercentPriceBundleRuleModel)
		{
			this.changeProductPercentPriceBundleRuleModel = changeProductPercentPriceBundleRuleModel;
		}

		public String getLabel()
		{
			if (changeProductPercentPriceBundleRuleModel != null)
			{
				return changeProductPercentPriceBundleRuleModel.getLabel();
			}
			if (changeProductPriceBundleRuleModel != null)
			{
				return changeProductPriceBundleRuleModel.getLabel();
			}
			return null;
		}
	}

	private static final List<ProductOption> PRODUCT_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE,
			ProductOption.STOCK);

	private ProductFacade productFacade;
	private BundleRuleService bundleRuleService;
	private SubscriptionCommercePriceService commercePriceService;
	private PercentPriceBundleRuleService percentPriceBundleRuleService;
	private PriceDataFactory priceDataFactory;

	@Override
	public void populate(final BundleOfferPopulatorParameters parameters, final BundleOfferData offerData)
			throws ConversionException
	{
		final List<BundleComponentData> components = new ArrayList<>();
		offerData.setComponents(components);
		offerData.setMessages(new ArrayList<ConfigMessage>());

		populateComponentData(parameters, offerData);
	}

	protected void populateComponentData(final BundleOfferPopulatorParameters parameters, final BundleOfferData offerData)
	{
		final BundleTemplateModel bundleTemplate = parameters.getBundleTemplate();
		final List<BundleComponentData> components = offerData.getComponents();
		final AbstractOrderModel order = parameters.getOrder();

		for (final BundleTemplateModel childTemplate : bundleTemplate.getChildTemplates())
		{
			final BundleComponentData componentToEdit = new BundleComponentData();

			componentToEdit.setId(childTemplate.getId());
			componentToEdit.setName(childTemplate.getName());
			final SelectionMode selectionMode = getSelectionMode(childTemplate);
			componentToEdit.setSelectionMode(selectionMode);

			final List<BundleComponentOptionData> options = new ArrayList<>();
			populateOptions(offerData, order, childTemplate, options);
			componentToEdit.setOptions(options);

			final Collection<BundleTemplateModel> requiredBundleTemplates = childTemplate.getRequiredBundleTemplates();
			final List<ComponentData> requiredComponents = new ArrayList<>();
			populateRequiredComponents(requiredBundleTemplates, requiredComponents);
			componentToEdit.setRequiredComponents(requiredComponents);

			components.add(componentToEdit);
		}
	}

	private void populateRequiredComponents(final Collection<BundleTemplateModel> requiredBundleTemplates,
			final List<ComponentData> requiredComponents)
	{
		for (final BundleTemplateModel requiredBundleTemplate : requiredBundleTemplates)
		{
			final ComponentData requiredComponentData = new ComponentData();
			requiredComponentData.setId(requiredBundleTemplate.getId());
			requiredComponentData.setName(requiredBundleTemplate.getName());
			requiredComponents.add(requiredComponentData);
		}
	}

	private void populateOptions(final BundleOfferData offerData, final AbstractOrderModel order,
			final BundleTemplateModel childTemplate, final List<BundleComponentOptionData> options)
	{
		for (final ProductModel productModel : childTemplate.getProducts())
		{
			final BundleComponentOptionData optionData = new BundleComponentOptionData();
			populateComponentOptionData(offerData, order, childTemplate, productModel, optionData);
			options.add(optionData);
		}
	}

	private void populateComponentOptionData(final BundleOfferData offerData, final AbstractOrderModel order,
			final BundleTemplateModel childTemplate, final ProductModel productModel, final BundleComponentOptionData optionData)
	{
		final ProductData productData = productFacade.getProductForOptions(productModel, PRODUCT_OPTIONS);
		productData.setDescription(productModel.getDescription());
		optionData.setProduct(productData);

		// CECS-82: Guidedselling: price change rules - START
		final PriceDataAndRule changedPrice = changeProductPriceByBundleRule(order, childTemplate, productModel,
				offerData.getBundleNo());
		if (changedPrice != null && changedPrice.getPriceData() != null)
		{
			optionData.setBundleRuleChangedPrice(changedPrice.getPriceData());
			optionData.setLabel(changedPrice.getLabel());
		}
		// CECS-82: Guidedselling: price change rules - END

		populateUsageCharges(optionData, productData);
	}

	private void populateUsageCharges(final BundleComponentOptionData optionData, final ProductData productData)
	{
		if (productData.getPrice() instanceof SubscriptionPricePlanData)
		{
			final Collection<UsageChargeEntryData> usageCharges = new ArrayList<>();
			final SubscriptionPricePlanData pricePlan = (SubscriptionPricePlanData) productData.getPrice();
			for (final UsageChargeData usageCharge : pricePlan.getUsageCharges())
			{
				for (final UsageChargeEntryData entry : usageCharge.getUsageChargeEntries())
				{
					entry.setUsageUnit(usageCharge.getUsageUnit());
					usageCharges.add(entry);
				}
			}
			optionData.setUsageCharges(usageCharges);
		}
	}

	protected SelectionMode getSelectionMode(final BundleTemplateModel childTemplate)
	{
		final BundleComponentType type = childTemplate.getBundleComponentType();
		final BundleSelectionCriteriaModel selectionCriteria = childTemplate.getBundleSelectionCriteria();

		if (type != BundleComponentType.ADDON)
		{
			return SelectionMode.ONE;
		}
		else if (selectionCriteria instanceof AutoPickBundleSelectionCriteriaModel)
		{
			return SelectionMode.AUTOPICK;
		}
		else if (selectionCriteria instanceof PickNToMBundleSelectionCriteriaModel)
		{
			final PickNToMBundleSelectionCriteriaModel crit = (PickNToMBundleSelectionCriteriaModel) selectionCriteria;
			final int n = crit.getN().intValue();
			final int m = crit.getM().intValue();

			return (n == 0 && m == 1) ? SelectionMode.ONEOPTIONAL : SelectionMode.MULTIPLE;
		}
		else if (selectionCriteria instanceof PickExactlyNBundleSelectionCriteriaModel)
		{
			final PickExactlyNBundleSelectionCriteriaModel crit = (PickExactlyNBundleSelectionCriteriaModel) selectionCriteria;

			return crit.getN().intValue() > 1 ? SelectionMode.MULTIPLE : SelectionMode.ONE;
		}
		else
		{
			return SelectionMode.MULTIPLE;
		}
	}

	// CECS-82: Guidedselling: price change rules
	private PriceDataAndRule changeProductPriceByBundleRule(final AbstractOrderModel order, final BundleTemplateModel bundleModel,
			final ProductModel product, final int bundleNo)
	{
		// bundle prices
		final ChangeProductPriceBundleRuleModel priceRule = getBundleRuleService().getChangePriceBundleRule(order, bundleModel,
				product, bundleNo);
		final ChangeProductPercentPriceBundleRuleModel percentPriceRule = getPercentPriceBundleRuleService()
				.getChangePriceBundleRule(order, bundleModel, product, bundleNo);

		final PriceData bestPriceData = getBestPriceForRule(priceRule, order, product);
		final PriceData bestPercentPriceData = getBestPriceForRule(percentPriceRule, order, product);

		final PriceDataAndRule result = new PriceDataAndRule();

		if (bestPriceData == null)
		{
			result.setPriceData(bestPercentPriceData);
			result.setChangeProductPercentPriceBundleRuleModel(percentPriceRule);
		}
		else if (bestPercentPriceData == null)
		{
			result.setPriceData(bestPriceData);
			result.setChangeProductPriceBundleRuleModel(priceRule);
		}
		else if (bestPriceData.getValue().compareTo(bestPercentPriceData.getValue()) <= 0)
		{
			result.setPriceData(bestPriceData);
			result.setChangeProductPriceBundleRuleModel(priceRule);
		}
		else
		{
			result.setPriceData(bestPercentPriceData);
			result.setChangeProductPercentPriceBundleRuleModel(percentPriceRule);
		}
		return result;
	}

	protected PriceData getBestPriceForRule(final ChangeProductPriceBundleRuleModel priceRule, final AbstractOrderModel order,
			final ProductModel product)
	{
		if (priceRule == null || priceRule.getBillingEvent() != null)
		{
			return null;
		}

		Double productPrice = null;
		Double bestPrice;
		if (product instanceof SubscriptionProductModel)
		{
			final SubscriptionPricePlanModel pricePlan = getCommercePriceService()
					.getSubscriptionPricePlanForProduct((SubscriptionProductModel) product);
			bestPrice = getMinPriceOfRuleAndPlan(pricePlan, priceRule);
			if (pricePlan != null)
			{
				productPrice = getCommercePriceService().getFirstRecurringPriceFromPlan(pricePlan).getPrice();
			}
		}
		else
		{
			final PriceInformation priceInfo = getCommercePriceService().getWebPriceForProduct(product);
			productPrice = priceInfo.getPriceValue().getValue();
			bestPrice = Math.min(productPrice, priceRule.getPrice().doubleValue());
		}

		if (bestPrice != null && !bestPrice.equals(productPrice))
		{
			return getPriceDataFactory().create(PriceDataType.BUY, BigDecimal.valueOf(bestPrice.doubleValue()),
					order.getCurrency().getIsocode());
		}
		return null;
	}

	protected PriceData getBestPriceForRule(final ChangeProductPercentPriceBundleRuleModel priceRule,
			final AbstractOrderModel order, final ProductModel product)
	{
		if (priceRule == null || priceRule.getBillingEvent() != null)
		{
			return null;
		}

		final Double productPrice = null;
		Double bestPrice = null;
		if (product instanceof SubscriptionProductModel)
		{
			final SubscriptionPricePlanModel pricePlan = getCommercePriceService()
					.getSubscriptionPricePlanForProduct((SubscriptionProductModel) product);
			if (pricePlan != null)
			{
				bestPrice = new Double(getCommercePriceService().getFirstRecurringPriceFromPlan(pricePlan).getPrice().doubleValue()
						* (100.0 - priceRule.getPercentageDiscount().doubleValue()) / 100.0);
			}

		}
		else
		{
			final PriceInformation priceInfo = getCommercePriceService().getWebPriceForProduct(product);
			bestPrice = new Double(
					priceInfo.getPriceValue().getValue() * (100.0 - priceRule.getPercentageDiscount().doubleValue()) / 100.0);
		}

		if (bestPrice != null && !bestPrice.equals(productPrice))
		{
			return getPriceDataFactory().create(PriceDataType.BUY, BigDecimal.valueOf(bestPrice.doubleValue()),
					order.getCurrency().getIsocode());
		}

		return null;
	}

	// this method duplicates DefaultGuidedSellingFacade.getMinPriceOfRuleAndPlan()
	protected Double getMinPriceOfRuleAndPlan(final SubscriptionPricePlanModel pricePlan,
			final ChangeProductPriceBundleRuleModel priceRule)
	{
		Double rulePrice = null;
		Double planPrice = null;

		if (pricePlan != null)
		{
			final RecurringChargeEntryModel chargeEntry = getCommercePriceService().getFirstRecurringPriceFromPlan(pricePlan);
			planPrice = chargeEntry.getPrice();
		}

		if (priceRule != null)
		{
			rulePrice = Double.valueOf(priceRule.getPrice().doubleValue());
		}

		if (rulePrice == null && planPrice != null)
		{
			return planPrice;
		}
		else if (rulePrice != null && planPrice == null)
		{
			return rulePrice;
		}
		else if (rulePrice != null && planPrice != null)
		{
			return (Double.compare(rulePrice.doubleValue(), planPrice.doubleValue()) == -1) ? rulePrice : planPrice;
		}

		return null;
	}

	// this method duplicates DefaultGuidedSellingFacade.getMinPriceOfRuleAndPlan()
	protected Double getMinPriceOfRuleAndPlan(final SubscriptionPricePlanModel pricePlan,
			final ChangeProductPercentPriceBundleRuleModel priceRule)
	{
		if (pricePlan == null || pricePlan.getPrice() == null)
		{
			return null;
		}

		if (priceRule != null && priceRule.getPercentageDiscount() != null)
		{
			return pricePlan.getPrice();
		}

		return new Double(pricePlan.getPrice().doubleValue() * (100.0 - priceRule.getPercentageDiscount().doubleValue()) / 100.0);
	}

	public ProductFacade getProductFacade()
	{
		return productFacade;
	}

	@Required
	public void setProductFacade(final ProductFacade productFacade)
	{
		this.productFacade = productFacade;
	}

	public BundleRuleService getBundleRuleService()
	{
		return bundleRuleService;
	}

	@Required
	public void setBundleRuleService(final BundleRuleService bundleRuleService)
	{
		this.bundleRuleService = bundleRuleService;
	}

	public SubscriptionCommercePriceService getCommercePriceService()
	{
		return commercePriceService;
	}

	@Required
	public void setCommercePriceService(final SubscriptionCommercePriceService commercePriceService)
	{
		this.commercePriceService = commercePriceService;
	}

	public PercentPriceBundleRuleService getPercentPriceBundleRuleService()
	{
		return percentPriceBundleRuleService;
	}

	@Required
	public void setPercentPriceBundleRuleService(final PercentPriceBundleRuleService percentPriceBundleRuleService)
	{
		this.percentPriceBundleRuleService = percentPriceBundleRuleService;
	}

	public PriceDataFactory getPriceDataFactory()
	{
		return priceDataFactory;
	}

	@Required
	public void setPriceDataFactory(final PriceDataFactory priceDataFactory)
	{
		this.priceDataFactory = priceDataFactory;
	}
}
