/**
 * 
 */
package com.hybris.productlists.populators;

import de.hybris.platform.commercefacades.converter.ConfigurablePopulator;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.hybris.productlists.data.ProductListEntryData;
import com.hybris.productlists.model.ProductListEntryModel;


/**
 * @author craig.wayman
 * 
 */
public class ProductListsEntryPopulator implements
		ConfigurablePopulator<ProductListEntryModel, ProductListEntryData, ProductOption>,
		Populator<ProductListEntryModel, ProductListEntryData>
{
	private ProductFacade productFacade;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final ProductListEntryModel source, final ProductListEntryData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setDesired(source.getDesired());
		target.setNotes(source.getComment());
		target.setPriority(source.getPriority());
		target.setQuantity(source.getDesired());
		target.setReceived(source.getReceived());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final ProductListEntryModel source, final ProductListEntryData target,
			final Collection<ProductOption> options) throws ConversionException
	{
		populate(source, target);
		if (CollectionUtils.isNotEmpty(options))
		{
			//From the product
			target.setProduct(getProductFacade().getProductForCodeAndOptions(source.getProduct().getCode(), options));
		}
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



}