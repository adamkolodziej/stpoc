/**
 * 
 */
package com.hybris.productlists.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.springframework.util.Assert;

import com.hybris.productlists.data.ProductListData;
import com.hybris.productlists.model.ProductListModel;


/**
 * @author craig.wayman
 * 
 */
public class ProductListsModelPopulator implements Populator<ProductListData, ProductListModel>
{

	@Override
	public void populate(final ProductListData source, final ProductListModel target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setName(source.getName());

	}

}
