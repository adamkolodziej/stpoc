/**
 * 
 */
package com.hybris.productlists.populators;

import de.hybris.platform.commercefacades.converter.ConfigurablePopulator;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.hybris.addon.common.converters.ConfigurableConverter;
import com.hybris.addon.common.converters.ConfigurableConverters;
import com.hybris.productlists.data.ProductListData;
import com.hybris.productlists.data.ProductListEntryData;
import com.hybris.productlists.enums.ProductListType;
import com.hybris.productlists.model.ProductListEntryModel;
import com.hybris.productlists.model.ProductListModel;


/**
 * @author craig.wayman
 * 
 */
public class ProductListsPopulator implements ConfigurablePopulator<ProductListModel, ProductListData, ProductOption>,
		Populator<ProductListModel, ProductListData>
{
	private ConfigurableConverter<ProductListEntryModel, ProductListEntryData, ProductOption> productListEntryConverter;
	private ProductService productService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final ProductListModel source, final ProductListData target) throws ConversionException
	{
		populateNonConfigurable(source, target);
		target.setEntries(ConfigurableConverters.convertAll(filterEntries(source), productListEntryConverter,
				Collections.singleton(ProductOption.BASIC)));
	}

	protected void populateNonConfigurable(final ProductListModel source, final ProductListData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setGuid(source.getGuid());
		target.setName(source.getName());
		target.setSiteUid(source.getSite().getUid());
		target.setStoreUid(source.getStore().getUid());
		target.setType(source.getType());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final ProductListModel source, final ProductListData target, final Collection<ProductOption> options)
			throws ConversionException
	{
		populateNonConfigurable(source, target);
		target.setEntries(ConfigurableConverters.convertAll(filterEntries(source), productListEntryConverter, options));
	}

	protected List<ProductListEntryModel> filterEntries(final ProductListModel source)
	{
		if (source.getType().equals(ProductListType.WISHLIST))
		{
			final List<ProductListEntryModel> filtered = new ArrayList<ProductListEntryModel>(Collections2.filter(
					source.getProductListEntries(), new Predicate<ProductListEntryModel>()
					{
						@Override
						public boolean apply(final ProductListEntryModel e)
						{
							return (e.getDesired().intValue() - e.getReceived().intValue()) > 0;
						}
					}));
			return filterInvisible(filtered);
		}
		else
		{
			return filterInvisible(source.getProductListEntries());
		}

	}

	protected List<ProductListEntryModel> filterInvisible(final List<ProductListEntryModel> in)
	{
		final List<ProductListEntryModel> out = new LinkedList<ProductListEntryModel>();
		for (final ProductListEntryModel entry : in)
		{
			if (entry.getProduct() != null)
			{
				try
				{
					getProductService().getProductForCode(entry.getProduct().getCode());
					out.add(entry);

				}
				catch (final UnknownIdentifierException e)
				{
					// add if we can find the product
				}

			}
		}
		return out;
	}

	@Required
	public void setProductListEntryConverter(
			final ConfigurableConverter<ProductListEntryModel, ProductListEntryData, ProductOption> productListEntryConverter)
	{
		this.productListEntryConverter = productListEntryConverter;
	}

	/**
	 * @return the productService
	 */
	public ProductService getProductService()
	{
		return productService;
	}

	/**
	 * @param productService
	 *           the productService to set
	 */
	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

}
