/**
 *
 */
package com.hybris.showcase.populators;

import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.PromotionData;
import de.hybris.platform.commerceservices.price.CommercePriceService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.promotions.PromotionsService;
import de.hybris.platform.promotions.model.ProductPercentageDiscountPromotionModel;
import de.hybris.platform.promotions.model.ProductPromotionModel;
import de.hybris.platform.promotions.model.PromotionGroupModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.site.BaseSiteService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 */
public class ServicesProductsPromotionsPopulator implements Populator<ProductModel, ProductData>
{
	private static final Logger LOG = Logger.getLogger(ServicesProductsPromotionsPopulator.class);


	@Autowired
	private CommonI18NService commonI18NService;

	@Autowired
	private PriceDataFactory priceDataFactory;

	@Autowired
	private CommercePriceService commercePriceService;

	@Autowired
	private Converter<ProductPromotionModel, PromotionData> promotionsConverter;


	@Autowired
	private PromotionsService promotionsService;

	@Autowired
	private BaseSiteService baseSiteService;

	@Override
	public void populate(final ProductModel productModel, final ProductData productData) throws ConversionException
	{
		String currencyIsocode = commonI18NService.getCurrentCurrency().getIsocode();
		PriceInformation priceInformation = commercePriceService.getWebPriceForProduct(productModel);

		if (priceInformation != null)
		{
			Double currentPrice = priceInformation.getPriceValue().getValue();

			final PromotionGroupModel defaultPromotionGroup = baseSiteService.getCurrentBaseSite().getDefaultPromotionGroup();
			if (defaultPromotionGroup != null)
			{

				final List<ProductPromotionModel> productPromotions = promotionsService
						.getProductPromotions(Arrays.asList(defaultPromotionGroup), productModel);

				final Map<String, ProductPromotionModel> promoModelByCode = productPromotions.stream() //
						.filter(pm -> pm instanceof ProductPercentageDiscountPromotionModel) //
						.collect(Collectors.toMap(ProductPromotionModel::getCode, Function.identity()));

				for (PromotionData promotionData : productData.getPotentialPromotions())
				{
					final ProductPromotionModel promotionModel = promoModelByCode.get(promotionData.getCode());

					if (promotionModel != null)
					{
						final Double percentageDiscount = ((ProductPercentageDiscountPromotionModel) promotionModel)
								.getPercentageDiscount();
						double newPrice = (100 - percentageDiscount) / 100 * currentPrice;

						if (newPrice >= 0)
						{
							PriceData priceData = priceDataFactory.create(PriceDataType.BUY, new BigDecimal(newPrice), currencyIsocode);
							promotionData.setNewPrice(priceData);
						}
					}
				}
			}
		}
	}

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	public void setCommonI18NService(CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	public PriceDataFactory getPriceDataFactory()
	{
		return priceDataFactory;
	}

	public void setPriceDataFactory(PriceDataFactory priceDataFactory)
	{
		this.priceDataFactory = priceDataFactory;
	}

	public CommercePriceService getCommercePriceService()
	{
		return commercePriceService;
	}

	public void setCommercePriceService(CommercePriceService commercePriceService)
	{
		this.commercePriceService = commercePriceService;
	}

	public Converter<ProductPromotionModel, PromotionData> getPromotionsConverter()
	{
		return promotionsConverter;
	}

	public void setPromotionsConverter(Converter<ProductPromotionModel, PromotionData> promotionsConverter)
	{
		this.promotionsConverter = promotionsConverter;
	}
}
