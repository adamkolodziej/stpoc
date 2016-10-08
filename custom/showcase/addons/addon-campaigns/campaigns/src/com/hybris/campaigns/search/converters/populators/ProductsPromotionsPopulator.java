/**
 * 
 */
package com.hybris.campaigns.search.converters.populators;

import de.hybris.platform.commercefacades.converter.ConfigurablePopulator;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.PromotionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.promotions.model.ProductPromotionModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.campaigns.PromotionMerchandizingService;


/**
 * Populates banners field for Product promotions and additional images connected with promotion type
 */
public class ProductsPromotionsPopulator implements Populator<AbstractPromotionModel, PromotionData>
{
	private static final Logger LOG = Logger.getLogger(ProductsPromotionsPopulator.class);

	private PromotionMerchandizingService promotionMerchandizingService;
	private Converter<ProductModel, ProductData> productConverter;
	private ConfigurablePopulator<ProductModel, ProductData, ProductOption> productConfiguredPopulator;
	private Converter<AbstractPromotionModel, PromotionData> promotionsConverter;

	@Override
	public void populate(final AbstractPromotionModel promotionModel, final PromotionData promotionData)
			throws ConversionException
	{
		promotionsConverter.convert(promotionModel, promotionData);
		if (promotionModel instanceof ProductPromotionModel)
		{
			final Set<ProductModel> promotionProducts = promotionMerchandizingService
					.getApplicableProductsFromPromotion((ProductPromotionModel) promotionModel);
			final List<ProductData> promotionProductsData = new ArrayList<ProductData>();
			for (final ProductModel productModel : promotionProducts)
			{
				final ProductData productData = productConverter.convert(productModel);
				//				promotionMerchandizingService.getProductPromotions(productModel, true);
				productConfiguredPopulator.populate(productModel, productData, Arrays.asList(ProductOption.PROMOTIONS));
				promotionProductsData.add(productData);
			}
			promotionData.setPromotionProducts(promotionProductsData);
		}
	}

	/**
	 * @return the promotionMerchandizingService
	 */
	public PromotionMerchandizingService getPromotionMerchandizingService()
	{
		return promotionMerchandizingService;
	}

	/**
	 * @param promotionMerchandizingService
	 *           the promotionMerchandizingService to set
	 */
	@Required
	public void setPromotionMerchandizingService(final PromotionMerchandizingService promotionMerchandizingService)
	{
		this.promotionMerchandizingService = promotionMerchandizingService;
	}

	/**
	 * @return the productConverter
	 */
	public Converter<ProductModel, ProductData> getProductConverter()
	{
		return productConverter;
	}

	/**
	 * @param productConverter
	 *           the productConverter to set
	 */
	@Required
	public void setProductConverter(final Converter<ProductModel, ProductData> productConverter)
	{
		this.productConverter = productConverter;
	}

	/**
	 * @return the productConfiguredPopulator
	 */
	public ConfigurablePopulator<ProductModel, ProductData, ProductOption> getProductConfiguredPopulator()
	{
		return productConfiguredPopulator;
	}

	/**
	 * @param productConfiguredPopulator
	 *           the productConfiguredPopulator to set
	 */
	@Required
	public void setProductConfiguredPopulator(
			final ConfigurablePopulator<ProductModel, ProductData, ProductOption> productConfiguredPopulator)
	{
		this.productConfiguredPopulator = productConfiguredPopulator;
	}

	/**
	 * @return the promotionsConverter
	 */
	public Converter<AbstractPromotionModel, PromotionData> getPromotionsConverter()
	{
		return promotionsConverter;
	}

	/**
	 * @param promotionsConverter
	 *           the promotionsConverter to set
	 */
	@Required
	public void setPromotionsConverter(final Converter<AbstractPromotionModel, PromotionData> promotionsConverter)
	{
		this.promotionsConverter = promotionsConverter;
	}
}