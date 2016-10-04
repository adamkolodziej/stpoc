/**
 * 
 */
package com.hybris.searchandizing.services.populator;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultProductPopulator;
import de.hybris.platform.commercesearch.model.SolrHeroProductDefinitionModel;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.ArrayList;
import java.util.List;

import com.hybris.searchandizing.data.SolrHeroProductDefinitionData;
import de.hybris.platform.product.ProductService;
import org.springframework.beans.factory.annotation.Required;


/**
 * @author dilic
 * 
 */
public class SearchandizingSearchResultProductPopulator implements Populator<SearchResultValueData, ProductData>
{

	private ProductService productService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.commercefacades.search.converters.populator.SearchResultProductPopulator#populate(de.hybris
	 * .platform.commerceservices.search.resultdata.SearchResultValueData,
	 * de.hybris.platform.commercefacades.product.data.ProductData)
	 */
	@Override
	public void populate(final SearchResultValueData source, final ProductData target)
	{
		final String code = (this.<String> getValue(source, "code"));
		final ProductModel pm = getProductService().getProductForCode(code);

		if (pm != null && pm.getSolrHeroProductDefinitions() != null && !pm.getSolrHeroProductDefinitions().isEmpty())
		{
			final List<SolrHeroProductDefinitionData> list = new ArrayList<SolrHeroProductDefinitionData>();
			for (final SolrHeroProductDefinitionModel m : pm.getSolrHeroProductDefinitions())
			{
				final SolrHeroProductDefinitionData data = new SolrHeroProductDefinitionData();
				data.setCode(m.getCode());
				data.setCategoryCode(m.getCategory().getCode());
				final List<String> productCodes = new ArrayList<String>();
				for (final ProductModel hero : m.getProducts())
				{
					final String productCode = hero.getCode();
					productCodes.add(productCode);
				}
				data.setProductCodes(productCodes);
				list.add(data);
			}

			target.setSolrHeroProductDefinitions(list);
		}

	}

	protected <T> T getValue(final SearchResultValueData source, final String propertyName)
	{
		if (source.getValues() == null)
		{
			return null;
		}
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
