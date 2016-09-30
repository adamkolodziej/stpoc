/**
 *
 */
package com.hybris.showcase.product.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/**
 *
 */
public class ProductReleaseDatePopulator implements Populator<ProductModel, ProductData>
{
	@Override
	public void populate(final ProductModel productModel, final ProductData productData) throws ConversionException
	{
		productData.setReleaseDate(productModel.getReleaseDate());
	}
}
