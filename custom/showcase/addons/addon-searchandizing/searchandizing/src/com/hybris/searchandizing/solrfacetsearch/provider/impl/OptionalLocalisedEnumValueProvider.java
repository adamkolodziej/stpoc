/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.hybris.searchandizing.solrfacetsearch.provider.impl;

import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.OptionalModelPropertyFieldValueProvider;
import de.hybris.platform.core.HybrisEnumValue;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.exceptions.AttributeNotSupportedException;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.variants.model.VariantAttributeDescriptorModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;


public class OptionalLocalisedEnumValueProvider extends OptionalModelPropertyFieldValueProvider
{
	private EnumerationService enumerationService;

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{
		if (model == null)
		{
			throw new IllegalArgumentException("No model given");
		}

		final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
		List<String> rangeNameList = new ArrayList<String>();

		if (indexedProperty.isLocalized())
		{
			final Collection<LanguageModel> languages = indexConfig.getLanguages();
			for (final LanguageModel language : languages)
			{
				Object value = null;
				final Locale locale = i18nService.getCurrentLocale();
				try
				{
					i18nService.setCurrentLocale(getCommonI18NService().getLocaleForLanguage(language));
					value = getPropertyValue(model, indexedProperty);
					rangeNameList = getRangeNameList(indexedProperty, value);
				}
				finally
				{
					i18nService.setCurrentLocale(locale);
				}

				if (value != null || !rangeNameList.isEmpty())
				{
					final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, language.getIsocode());
					addFieldValues(fieldValues, rangeNameList, value, fieldNames);
				}
			}
		}
		else
		{
			final Object value = getPropertyValue(model, indexedProperty);
			rangeNameList = getRangeNameList(indexedProperty, value);

			if (value != null || !rangeNameList.isEmpty())
			{
				final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, null);
				addFieldValues(fieldValues, rangeNameList, value, fieldNames);
			}
		}
		return fieldValues;
	}

	private void addFieldValues(final List<FieldValue> fieldValues, final List<String> rangeNameList, final Object value,
			final Collection<String> fieldNames)
	{
		for (final String fieldName : fieldNames)
		{
			if (value instanceof Collection<?>)
			{
				for (final Object o : (Collection) value)
				{
					createFieldValues(fieldValues, rangeNameList, fieldName, o);

				}
			}
			else
			{
				createFieldValues(fieldValues, rangeNameList, fieldName, value);
			}
		}
	}

	private void createFieldValues(final List<FieldValue> fieldValues, final List<String> rangeNameList, final String fieldName,
			final Object o)
	{
		if (rangeNameList.isEmpty())
		{
			fieldValues.add(new FieldValue(fieldName, o));
		}
		else
		{
			for (final String rangeName : rangeNameList)
			{
				fieldValues.add(new FieldValue(fieldName, rangeName == null ? o : rangeName));
			}
		}
	}

	@Override
	protected Object getPropertyValue(final Object model, final IndexedProperty indexedProperty)
	{
		// Use value provider parameter to obtain attribute qualifier, so you can name your indexed property at will.
		final String valueProviderParameter = indexedProperty.getValueProviderParameter();
		final String qualifier = StringUtils.isEmpty(valueProviderParameter) ? indexedProperty.getName() : valueProviderParameter;
		Object result = null;
		try
		{
			// The superclass implementation doesn't consider empty collections as a NULL value.
			result = modelService.getAttributeValue(model, qualifier);
			if ((result == null || isEmptyCollection(result)) && (model instanceof VariantProductModel))
			{
				final ProductModel baseProduct = ((VariantProductModel) model).getBaseProduct();
				result = modelService.getAttributeValue(baseProduct, qualifier);
			}
		}
		catch (final AttributeNotSupportedException ae)
		{
			if (model instanceof VariantProductModel)
			{
				final ProductModel baseProduct = ((VariantProductModel) model).getBaseProduct();
				for (final VariantAttributeDescriptorModel att : baseProduct.getVariantType().getVariantAttributes())
				{
					if (qualifier.equals(att.getQualifier()))
					{
						result = this.getVariantsService().getVariantAttributeValue((VariantProductModel) model, qualifier);
						break;
					}
				}
			}
		}

		if (result instanceof Collection<?>)
		{
			final List<Object> resultingList = new ArrayList<>();
			for (final Object object : (Collection<?>) result)
			{
				if (object instanceof HybrisEnumValue)
				{
					final String colorName = getEnumerationValue(object);
					resultingList.add(colorName);
				}
				else
				{
					resultingList.add(object);
				}
			}
			result = Collections.singletonList(resultingList);
		}
		else if (result instanceof HybrisEnumValue)
		{
			result = getEnumerationValue(result);
		}

		return result;
	}

	private boolean isEmptyCollection(final Object result)
	{
		return result instanceof Collection && ((Collection<?>) result).isEmpty();
	}

	private String getEnumerationValue(final Object object)
	{
		final String colorName = enumerationService.getEnumerationName((HybrisEnumValue) object, i18nService.getCurrentLocale());
		return colorName;
	}

	@Required
	public void setEnumerationService(final EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}

}
