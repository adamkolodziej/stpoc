/**
 *
 */
package com.hybris.showcase.product.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;


/**
 *
 */
public class ProductViewUrlPopulator implements Populator<ProductModel, ProductData>
{
	private UrlResolver<ProductModel> productModelUrlResolver;

	protected UrlResolver<ProductModel> getProductModelUrlResolver()
	{
		return productModelUrlResolver;
	}

	@Required
	public void setProductModelUrlResolver(final UrlResolver<ProductModel> productModelUrlResolver)
	{
		this.productModelUrlResolver = productModelUrlResolver;
	}

	@Override
	public void populate(final ProductModel source, final ProductData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setCode(source.getCode());
		target.setName(source.getName());
		target.setViewUrl(getProductModelUrlResolver().resolve(source));
	}
}
