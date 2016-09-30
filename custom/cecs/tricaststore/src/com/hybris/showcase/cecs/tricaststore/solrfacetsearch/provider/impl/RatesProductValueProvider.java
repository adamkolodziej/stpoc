/**
 *
 */
package com.hybris.showcase.cecs.tricaststore.solrfacetsearch.provider.impl;

import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.ProductReviewAverageRatingValueProvider;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;

import java.util.Collection;
import java.util.List;


/**
 * @author lmachnik
 *
 */
public class RatesProductValueProvider extends ProductReviewAverageRatingValueProvider
{
	@Override
	protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty,
			final LanguageModel language, final Object value)
	{
		try
		{
			final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty,
					language == null ? null : language.getIsocode());
			final List<String> rangeNames = getRangeNameList(indexedProperty, value);
			if (!rangeNames.isEmpty())
			{
				addFieldValuesAsRangeName(fieldValues, fieldNames, rangeNames);
			}
			else
			{
				addFieldValuesAsRate(fieldValues, fieldNames, value);
			}
		}
		catch (final FieldValueProviderException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			i18nService.setCurrentLocale(i18nService.getCurrentLocale());
		}
	}

	private void addFieldValuesAsRangeName(final List<FieldValue> fieldValues, final Collection<String> fieldNames,
			final List<String> rangeNames)
	{
		for (final String fieldName : fieldNames)
		{
			for (final String rangeName : rangeNames)
			{
				fieldValues.add(new FieldValue(fieldName, rangeName));
			}
		}
	}

	private void addFieldValuesAsRate(final List<FieldValue> fieldValues, final Collection<String> fieldNames, final Object value)
	{
		for (final String fieldName : fieldNames)
		{
			fieldValues.add(new FieldValue(fieldName, value));
		}
	}
}
