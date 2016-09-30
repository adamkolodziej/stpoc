/**
 *
 */
package com.hybris.showcase.cecs.tricaststore.solrfacetsearch.populator;

import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Required;


/**
 *
 */
public class SearchResultProductPercentageDiscountPopulator implements Populator<SearchResultValueData, ProductData>
{
	private PriceDataFactory priceDataFactory;
	private CommonI18NService commonI18NService;

	protected PriceDataFactory getPriceDataFactory()
	{
		return priceDataFactory;
	}

	@Required
	public void setPriceDataFactory(final PriceDataFactory priceDataFactory)
	{
		this.priceDataFactory = priceDataFactory;
	}

	protected CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	@Override
	public void populate(final SearchResultValueData source, final ProductData target) throws ConversionException
	{
		final Object promotionPercentage = source.getValues().get("primaryPromotionPercentageDiscount");
		if (promotionPercentage instanceof Double && target.getPrice() != null)
		{
			final Double promotionPercentageValue = (Double) promotionPercentage;
			target.getPotentialPromotions().forEach((promotion) -> {
				final BigDecimal priceValue = target.getPrice().getValue()
						.multiply(BigDecimal.valueOf(100.0 - promotionPercentageValue.doubleValue())).divide(new BigDecimal(100.0));
				final PriceData priceData = getPriceDataFactory().create(PriceDataType.BUY, priceValue,
						getCommonI18NService().getCurrentCurrency());

				promotion.setNewPrice(priceData);
				promotion.setPercentageDiscount(promotionPercentageValue);
			});
		}

	}

}
