package com.hybris.solrrollup.solr.providers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.solrrollup.services.SolrRollupService;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

public class RootProductCodeProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable
{
	private FieldNameProvider fieldNameProvider;
	private SolrRollupService solrRollupService;


	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{
		if (model == null)
		{
			throw new IllegalArgumentException("No model given");
		}

		final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
		if (model instanceof ProductModel)
		{
			final ProductModel rootProduct = getSolrRollupService().getRollupProduct(((ProductModel)model),getSolrRollupService().getRootProduct(((ProductModel)model)));
			fieldValues.addAll(createFieldValue(rootProduct, indexedProperty));
		}
		return fieldValues;
	}

	private List<FieldValue> createFieldValue(final ProductModel product, final IndexedProperty indexedProperty)
	{
		final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
		final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
		for (final String fieldName : fieldNames)
		{
			fieldValues.add(new FieldValue(fieldName,  product.getCode()));	
		}
		return fieldValues;
	}
	



	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}

	public SolrRollupService getSolrRollupService() {
		return solrRollupService;
	}

	@Required
	public void setSolrRollupService(SolrRollupService solrRollupService) {
		this.solrRollupService = solrRollupService;
	}

}