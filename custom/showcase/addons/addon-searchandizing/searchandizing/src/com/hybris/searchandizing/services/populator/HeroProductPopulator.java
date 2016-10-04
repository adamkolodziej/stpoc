/**
 * 
 */
package com.hybris.searchandizing.services.populator;

import de.hybris.platform.commercefacades.product.converters.populator.AbstractProductPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercesearch.model.SolrHeroProductDefinitionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import com.hybris.searchandizing.data.SolrHeroProductDefinitionData;


/**
 * @author dilic
 * 
 */
public class HeroProductPopulator<SOURCE extends ProductModel, TARGET extends ProductData> extends
		AbstractProductPopulator<SOURCE, TARGET>
{

	@Override
	public void populate(final ProductModel source, final ProductData target) throws ConversionException
	{
		if (source != null && source.getSolrHeroProductDefinitions() != null && !source.getSolrHeroProductDefinitions().isEmpty())
		{
			final List<SolrHeroProductDefinitionData> list = new ArrayList<SolrHeroProductDefinitionData>();
			for (final SolrHeroProductDefinitionModel m : source.getSolrHeroProductDefinitions())
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
}
