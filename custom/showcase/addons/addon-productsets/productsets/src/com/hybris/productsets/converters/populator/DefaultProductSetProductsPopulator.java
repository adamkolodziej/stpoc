/**
 * 
 */
package com.hybris.productsets.converters.populator;

import de.hybris.platform.commercefacades.converter.ConfigurablePopulator;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.threadcontext.ThreadContextService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.hybris.productsets.constants.ProductsetsConstants;
import com.hybris.productsets.facades.data.ProductSetData;
import com.hybris.productsets.model.ProductSetModel;
import com.hybris.productsets.services.ProductSetService;


/**
 * @author dariusz.malachowski
 * 
 */
public class DefaultProductSetProductsPopulator implements ConfigurablePopulator<ProductSetModel, ProductSetData, ProductOption>
{
	private ThreadContextService threadContextService;
	private Converter<ProductModel, ProductData> productConverter;
	private ConfigurablePopulator<ProductModel, ProductData, ProductOption> productConfiguredPopulator;
	private ProductSetService productSetService;

	@Override
	public void populate(final ProductSetModel source, final ProductSetData target, final Collection<ProductOption> options)
			throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		final ProductModel currentProduct = getThreadContextService().getAttribute(ProductsetsConstants.SESSION_CURRENT_PRODUCT);
		final Set<ProductData> products = convertProducts(
				currentProduct != null ? getProductSetService().getDisplayedProducts(source, currentProduct) : source.getProducts(),
				options);
		target.setProducts(products);
	}

	private Set<ProductData> convertProducts(final Collection<ProductModel> products, final Collection<ProductOption> options)
	{
		final Set<ProductData> data = new HashSet<>();
		for (final ProductModel productModel : products)
		{
			final ProductData productData = getProductConverter().convert(productModel);
			if (options != null)
			{
				getProductConfiguredPopulator().populate(productModel, productData, options);
			}
			data.add(productData);
		}
		return data;
	}

	public Converter<ProductModel, ProductData> getProductConverter()
	{
		return productConverter;
	}

	@Required
	public void setProductConverter(final Converter<ProductModel, ProductData> productConverter)
	{
		this.productConverter = productConverter;
	}

	public ConfigurablePopulator<ProductModel, ProductData, ProductOption> getProductConfiguredPopulator()
	{
		return productConfiguredPopulator;
	}

	@Required
	public void setProductConfiguredPopulator(
			final ConfigurablePopulator<ProductModel, ProductData, ProductOption> productConfiguredPopulator)
	{
		this.productConfiguredPopulator = productConfiguredPopulator;
	}

	/**
	 * @return the threadContextService
	 */
	public ThreadContextService getThreadContextService()
	{
		return threadContextService;
	}

	/**
	 * @param threadContextService
	 *           the threadContextService to set
	 */
	@Required
	public void setThreadContextService(final ThreadContextService threadContextService)
	{
		this.threadContextService = threadContextService;
	}

	public ProductSetService getProductSetService()
	{
		return this.productSetService;
	}

	@Required
	public void setProductSetService(final ProductSetService productSetService)
	{
		this.productSetService = productSetService;
	}
}
