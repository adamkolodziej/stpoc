/**
 *
 */
package com.hybris.showcase.facades.populators;

import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.PromotionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.promotions.model.ProductPercentageDiscountPromotionModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/**
 * @author Sebastian Weiner
 *
 */
public class PromotionDetailsPopulator implements Populator<AbstractPromotionModel, PromotionData>
{

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final AbstractPromotionModel source, final PromotionData target) throws ConversionException
	{
		target.setName(source.getName());

		if (source instanceof ProductPercentageDiscountPromotionModel)
		{
			final ProductPercentageDiscountPromotionModel promotionModel = (ProductPercentageDiscountPromotionModel) source;
			target.setProductBanner(getProductBanner(promotionModel));
			target.setPercentageDiscount(promotionModel.getPercentageDiscount());
		}

	}

	private ImageData getProductBanner(final ProductPercentageDiscountPromotionModel promotionModel)
	{
		final ImageData banner = new ImageData();
		if (promotionModel.getProductBanner() != null)
		{
			banner.setUrl(promotionModel.getProductBanner().getURL());
		}
		return banner;
	}

}
