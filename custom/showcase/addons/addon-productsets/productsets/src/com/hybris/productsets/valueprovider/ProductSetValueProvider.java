/**
 * 
 */
package com.hybris.productsets.valueprovider;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hybris.productsets.model.ProductSetModel;
import com.hybris.productsets.services.ProductSetService;


/**
 * @author pauldavidgilligan
 * 
 */
@Component("productSetValueProvider")
public class ProductSetValueProvider implements FieldValueProvider, Serializable
{
	final static Logger LOG = Logger.getLogger(ProductSetValueProvider.class);

	@Autowired
	private ProductSetService productSetService;

	private FieldNameProvider fieldNameProvider;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.solrfacetsearch.provider.FieldValueProvider#getFieldValues(de.hybris.platform.solrfacetsearch
	 * .config.IndexConfig, de.hybris.platform.solrfacetsearch.config.IndexedProperty, java.lang.Object)
	 */
	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{
		if (!(model instanceof ProductModel))
		{
			throw new FieldValueProviderException("Cannot evaluate set of non-product item");
		}

		final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
		final ProductModel productModel = (ProductModel) model;
		final List<ProductSetModel> models = productSetService.getProductSetsWithProduct(productModel);

		if (models != null && !models.isEmpty())
		{
			final String attributeName = indexedProperty.getValueProviderParameter();
			if (attributeName == null)
			{
				LOG.debug("No set name attribute specified in resolver parameter for indexed property (" + indexedProperty.getName()
						+ "). Skipping.");
				return Collections.emptyList();
			}
			for (final ProductSetModel sm : models)
			{
				fieldValues.add(new FieldValue(attributeName, sm.getTitle()));
			}
			return fieldValues;
		}
		else
		{
			return Collections.emptyList();
		}
	}

	/**
	 * @return the fieldNameProvider
	 */
	public FieldNameProvider getFieldNameProvider()
	{
		return fieldNameProvider;
	}


	/**
	 * @param fieldNameProvider
	 *           the fieldNameProvider to set
	 */
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}



}
