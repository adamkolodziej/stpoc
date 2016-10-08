/**
 * 
 */
package com.hybris.productsets.converters.populator;

import de.hybris.platform.commercefacades.converter.ConfigurablePopulator;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.PromotionData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.promotions.model.ProductSetPromotionModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.time.TimeService;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.hybris.campaigns.order.GetPromotionGroupsStrategy;
import com.hybris.productsets.facades.data.ProductSetData;
import com.hybris.productsets.model.ProductSetModel;
import com.hybris.productsets.services.ProductSetPromotionService;


/**
 * @author dilic
 * 
 */
public class DefaultProductSetPromotionsPopulator implements
		ConfigurablePopulator<ProductSetModel, ProductSetData, ProductOption>
{
	private ProductSetPromotionService productSetPromotionService;
	private GetPromotionGroupsStrategy promotionGroupsStrategy;
	private TimeService timeService;
	private Converter<ProductSetPromotionModel, PromotionData> promotionsConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commercefacades.converter.ConfigurablePopulator#populate(java.lang.Object,
	 * java.lang.Object, java.util.Collection)
	 */
	@Override
	public void populate(final ProductSetModel source, final ProductSetData target, final Collection<ProductOption> options)
			throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		if (options != null && !options.isEmpty() && options.contains(ProductOption.PROMOTIONS))
		{

			final List<ProductSetPromotionModel> promotions = getProductSetPromotionService().getProductSetPromotions(
					getPromotionGroupsStrategy().getPromotionGroups(), source, true,
					DateUtils.round(getTimeService().getCurrentTime(), Calendar.MINUTE));
			if (promotions != null && !promotions.isEmpty())
			{


				target.setPromotions(Converters.convertAll(promotions, getPromotionsConverter()));
			}

		}

	}

	/**
	 * @return the promotionsConverter
	 */
	public Converter<ProductSetPromotionModel, PromotionData> getPromotionsConverter()
	{
		return promotionsConverter;
	}

	/**
	 * @param promotionsConverter
	 *           the promotionsConverter to set
	 */
	public void setPromotionsConverter(final Converter<ProductSetPromotionModel, PromotionData> promotionsConverter)
	{
		this.promotionsConverter = promotionsConverter;
	}

	/**
	 * @return the productSetPromotionService
	 */
	public ProductSetPromotionService getProductSetPromotionService()
	{
		return productSetPromotionService;
	}

	/**
	 * @param productSetPromotionService
	 *           the productSetPromotionService to set
	 */
	@Required
	public void setProductSetPromotionService(final ProductSetPromotionService productSetPromotionService)
	{
		this.productSetPromotionService = productSetPromotionService;
	}

	/**
	 * @return the promotionGroupStrategy
	 */
	public GetPromotionGroupsStrategy getPromotionGroupsStrategy()
	{
		return promotionGroupsStrategy;
	}

	/**
	 * @param promotionGroupStrategy
	 *           the promotionGroupStrategy to set
	 */
	@Required
	public void setPromotionGroupsStrategy(final GetPromotionGroupsStrategy promotionGroupsStrategy)
	{
		this.promotionGroupsStrategy = promotionGroupsStrategy;
	}

	/**
	 * @return the timeService
	 */
	public TimeService getTimeService()
	{
		return timeService;
	}

	/**
	 * @param timeService
	 *           the timeService to set
	 */
	@Required
	public void setTimeService(final TimeService timeService)
	{
		this.timeService = timeService;
	}

}
