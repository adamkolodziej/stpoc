package com.hybris.showcase.guidedselling.populators;

import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.subscriptionfacades.data.OneTimeChargeEntryData;
import de.hybris.platform.subscriptionfacades.data.RecurringChargeEntryData;
import de.hybris.platform.subscriptionfacades.data.SubscriptionPricePlanData;
import de.hybris.platform.subscriptionfacades.data.UsageChargeData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.hybris.showcase.guidedselling.data.BundlePackageData;
import com.hybris.showcase.guidedselling.enums.BundleComponentType;
import com.hybris.showcase.guidedselling.model.BundlePackageModel;


/**
 * CECS-148 Packages Page Created by I303845 on 2015-03-04.
 */
public class BundlePackagePopulator<SOURCE extends BundlePackageModel, TARGET extends BundlePackageData>
		implements Populator<SOURCE, TARGET>
{

	private static final List<ProductOption> NORMAL_PRODUCT_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE);
	private static final List<ProductOption> SUBSCRIPTION_PRODUCT_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE,
			ProductOption.SERVICE_PLAN_BUNDLE_TABS);

	private ProductFacade productFacade;
	private Converter<MediaModel, ImageData> imageConverter;
	private PriceDataFactory priceDataFactory;

	@Override
	public void populate(final SOURCE source, final TARGET target)
	{
		target.setCode(source.getCode());
		target.setName(source.getName());
		target.setDescription(source.getDescription());

		final BundleTemplateModel bundleTemplate = source.getBundleTemplate();
		target.setBundleTemplateId(bundleTemplate.getId());
		final String bundleTemplateName = bundleTemplate.getName();
		target.setBundleTemplateName(bundleTemplateName);

		final List<ProductModel> selectedProductModels = source.getSelectedProducts();
		final List<ProductData> selectedProducts = new ArrayList<>();
		final List<RecurringChargeEntryData> recurringChargeEntriesAll = new ArrayList<>();
		final List<OneTimeChargeEntryData> oneTimeChargeEntriesAll = new ArrayList<>();
		final Collection<UsageChargeData> usageCharges = new ArrayList<>();

		PriceData onInstallationTotalPrice;
		PriceData recurringChargeEntryTotalPrice;
		String currency = "";
		BigDecimal onInstallationTotalPriceValue = BigDecimal.ZERO;
		BigDecimal recurringChargeEntryTotalPriceValue = BigDecimal.ZERO;


		for (final ProductModel productModel : selectedProductModels)
		{
			final ProductData productData = productFacade.getProductForOptions(productModel, NORMAL_PRODUCT_OPTIONS);
			selectedProducts.add(productData);

			addUsageCharges(usageCharges, productData.getPrice());

			if (isConditionalProduct(productModel, source))
			{
				final ProductData subscriptionData = productFacade.getProductForOptions(productModel, SUBSCRIPTION_PRODUCT_OPTIONS);
				target.setSubscription(subscriptionData);
				final SubscriptionPricePlanData subscriptionPricePlanData = (SubscriptionPricePlanData) subscriptionData.getPrice();
				if (!subscriptionPricePlanData.getRecurringChargeEntries().isEmpty())
				{
					for (final RecurringChargeEntryData recurringChargeEntry : subscriptionPricePlanData.getRecurringChargeEntries())
					{
						if (recurringChargeEntry.getCycleStart() == 1)
						{
							recurringChargeEntryTotalPriceValue = recurringChargeEntryTotalPriceValue
									.add(recurringChargeEntry.getPrice().getValue());

							if (recurringChargeEntry.getCycleEnd() != -1)
							{
								recurringChargeEntry.setDiscounted(true);
							}

							break;
						}

					}
				}

				if (!subscriptionPricePlanData.getOneTimeChargeEntries().isEmpty())
				{
					onInstallationTotalPriceValue = onInstallationTotalPriceValue
							.add(subscriptionPricePlanData.getOneTimeChargeEntries().get(0).getPrice().getValue());
				}
			}
			else
			{
				//CECS-194 Packages Page - complete implementation START
				if (productData.getPrice() instanceof SubscriptionPricePlanData)
				{
					final List<RecurringChargeEntryData> recurringChargeEntries = ((SubscriptionPricePlanData) productData.getPrice())
							.getRecurringChargeEntries();

					final List<OneTimeChargeEntryData> oneTimeChargeEntries = ((SubscriptionPricePlanData) productData.getPrice())
							.getOneTimeChargeEntries();

					if (!oneTimeChargeEntries.isEmpty())
					{
						currency = oneTimeChargeEntries.get(0).getPrice().getCurrencyIso();
					}

					if (!recurringChargeEntries.isEmpty())
					{
						currency = recurringChargeEntries.get(0).getPrice().getCurrencyIso();
					}

					final List<BundleTemplateModel> childBundleTemplates = bundleTemplate.getChildTemplates();
					for (final BundleTemplateModel childBundleTemplate : childBundleTemplates)
					{
						if (childBundleTemplate.getProducts().contains(productModel))
						{
							for (final RecurringChargeEntryData recurringChargeEntry : recurringChargeEntries)
							{
								recurringChargeEntry.setPackageName(childBundleTemplate.getName());
								if (recurringChargeEntry.getCycleStart() == 1)
								{
									recurringChargeEntryTotalPriceValue = recurringChargeEntryTotalPriceValue
											.add(recurringChargeEntry.getPrice().getValue());

									if (recurringChargeEntry.getCycleEnd() != -1)
									{
										recurringChargeEntry.setDiscounted(true);
									}
								}
							}
							for (final OneTimeChargeEntryData oneTimeChargeEntry : oneTimeChargeEntries)
							{
								oneTimeChargeEntry.setPackageName(childBundleTemplate.getName());
								onInstallationTotalPriceValue = onInstallationTotalPriceValue
										.add(oneTimeChargeEntry.getPrice().getValue());
							}
						}
					}

					if (!onInstallationTotalPriceValue.equals(BigDecimal.ZERO))
					{
						onInstallationTotalPrice = priceDataFactory.create(PriceDataType.BUY, onInstallationTotalPriceValue, currency);
						target.setOnInstallationTotalPrice(onInstallationTotalPrice);
					}

					if (!recurringChargeEntryTotalPriceValue.equals(BigDecimal.ZERO))
					{
						recurringChargeEntryTotalPrice = priceDataFactory.create(PriceDataType.BUY, recurringChargeEntryTotalPriceValue,
								currency);
						target.setRecurringChargeEntryTotalPrice(recurringChargeEntryTotalPrice);
					}

					recurringChargeEntriesAll.addAll(recurringChargeEntries);
					oneTimeChargeEntriesAll.addAll(oneTimeChargeEntries);
				}

			}
		}

		target.setRecurringChargeEntryFormattedList(recurringChargeEntriesAll);
		target.setOneTimeChargeEntryFormattedList(oneTimeChargeEntriesAll);
		//CECS-194 Packages Page - complete implementation END
		target.setSelectedProducts(selectedProducts);

		if (source.getImage() != null)
		{
			target.setImage(getImageConverter().convert(source.getImage()));
		}

		if (source.getThumbnail() != null)
		{
			target.setThumbnail(getImageConverter().convert(source.getThumbnail()));
		}

		target.setUsageCharges(usageCharges);
	}

	private void addUsageCharges(final Collection<UsageChargeData> usageCharges, final PriceData price)
	{
		if (price instanceof SubscriptionPricePlanData)
		{
			usageCharges.addAll(((SubscriptionPricePlanData) price).getUsageCharges());
		}
	}

	// TODO could be moved to a better place like a FlexibleGuidedSellingFacade
	private boolean isConditionalProduct(final ProductModel product, final SOURCE source)
	{
		final BundleTemplateModel conditionalBundleTemplate = Iterables.find(source.getBundleTemplate().getChildTemplates(),
				new Predicate<BundleTemplateModel>()
				{
					@Override
					public boolean apply(final BundleTemplateModel bundleTemplateModel)
					{
						return bundleTemplateModel.getBundleComponentType() == BundleComponentType.CONDITIONAL;
					}
				});
		return conditionalBundleTemplate.getProducts().contains(product);
	}

	@Required
	public void setProductFacade(final ProductFacade productFacade)
	{
		this.productFacade = productFacade;
	}

	protected ProductFacade getProductFacade()
	{
		return productFacade;
	}

	protected Converter<MediaModel, ImageData> getImageConverter()
	{
		return imageConverter;
	}

	@Required
	public void setImageConverter(final Converter<MediaModel, ImageData> imageConverter)
	{
		this.imageConverter = imageConverter;
	}

	/**
	 * @return the priceDataFactory
	 */
	public PriceDataFactory getPriceDataFactory()
	{
		return priceDataFactory;
	}

	/**
	 * @param priceDataFactory
	 *           the priceDataFactory to set
	 */
	public void setPriceDataFactory(final PriceDataFactory priceDataFactory)
	{
		this.priceDataFactory = priceDataFactory;
	}
}
