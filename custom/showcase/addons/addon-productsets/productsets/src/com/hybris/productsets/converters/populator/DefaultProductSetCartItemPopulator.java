/**
 * 
 */
package com.hybris.productsets.converters.populator;

import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.springframework.util.Assert;

import com.hybris.productsets.facades.data.ProductSetCartItemData;


/**
 * @author dariusz.malachowski
 * 
 */
public class DefaultProductSetCartItemPopulator implements Populator<CartModificationData, ProductSetCartItemData>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final CartModificationData source, final ProductSetCartItemData target) throws ConversionException
	{

		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setProduct(source.getEntry().getProduct());
		target.setQuantity(Long.valueOf(source.getQuantity()));

	}

}
