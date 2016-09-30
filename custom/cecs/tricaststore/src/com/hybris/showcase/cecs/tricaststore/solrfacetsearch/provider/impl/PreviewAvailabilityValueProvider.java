/**
 * 
 */
package com.hybris.showcase.cecs.tricaststore.solrfacetsearch.provider.impl;

import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.cecs.servicesshowcase.model.VideoProductModel;


/**
 * @author npavlovic
 * 
 *         CECS-65 Add solr config for new product types
 */
public class PreviewAvailabilityValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider,
		Serializable
{
	public static final String FULL_PREVIEW_FLAG = "full";
	public static final String FREE_PREVIEW_FLAG = "free";

	private FieldNameProvider fieldNameProvider;

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{
		final ArrayList<FieldValue> fieldValues = new ArrayList<>();

		if (model instanceof VideoProductModel)
		{
			final String valueProviderParameter = indexedProperty.getValueProviderParameter();
			if (StringUtils.isNotBlank(valueProviderParameter))
			{
				final VideoProductModel videoProductModel = (VideoProductModel) model;

				final boolean exists = checkIfExists(valueProviderParameter, videoProductModel);

				addToFieldValues(indexedProperty, fieldValues, Boolean.valueOf(exists));
			}
		}
		return fieldValues;
	}

	protected boolean checkIfExists(final String valueProviderParameter, final VideoProductModel videoProductModel)
	{
		boolean exists = false;

		if (valueProviderParameter.equals(FULL_PREVIEW_FLAG) && videoProductModel.getFullVersionMedia() != null)
		{
			exists = true;
		}
		else if (valueProviderParameter.equals(FREE_PREVIEW_FLAG) && videoProductModel.getFreePreviewMedia() != null)
		{
			exists = true;
		}
		return exists;
	}

	protected void addToFieldValues(final IndexedProperty indexedProperty, final ArrayList<FieldValue> fieldValues,
			final Object value)
	{
		final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, null);
		for (final String fieldName : fieldNames)
		{
			fieldValues.add(new FieldValue(fieldName, value));
		}
	}

	public FieldNameProvider getFieldNameProvider()
	{
		return fieldNameProvider;
	}

	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}
}
