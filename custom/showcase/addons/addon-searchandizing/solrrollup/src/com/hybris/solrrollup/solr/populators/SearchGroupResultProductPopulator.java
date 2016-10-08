/**
 * 
 */
package com.hybris.solrrollup.solr.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexedPropertyModel;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;


/**
 * @author wojciech.piotrowiak
 * 
 */
public class SearchGroupResultProductPopulator implements Populator<SearchResultValueData, ProductData>
{
	private ProductService productService;

	@Override
	public void populate(final SearchResultValueData source, final ProductData target) throws ConversionException
	{
		//SHOW-1345
		target.setName(this.<String> getValue(source, "name"));
		final ProductModel productModel = getProductService().getProductForCode(target.getCode());
		if (productModel != null)
		{
			final SolrIndexedPropertyModel groupingProperty = productModel.getCatalogVersion().getFacetSearchConfigs().get(0)
					.getSolrSearchConfig().getGroupingProperty();

			if (groupingProperty != null && StringUtils.isNotBlank(this.<String> getValue(source, "baseProductName")))
			{
				target.setName(this.<String> getValue(source, "baseProductName"));
			}
		}
	}

	protected <T> T getValue(final SearchResultValueData source, final String propertyName)
	{
		if (source.getValues() == null)
		{
			return null;
		}

		// DO NOT REMOVE the cast (T) below, while it should be unnecessary it is required by the javac compiler
		return (T) source.getValues().get(propertyName);
	}

	protected ProductService getProductService()
	{
		return productService;
	}

	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

}
