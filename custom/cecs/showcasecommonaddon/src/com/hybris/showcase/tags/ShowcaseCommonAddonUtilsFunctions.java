/**
 *
 */
package com.hybris.showcase.tags;

import de.hybris.platform.commercefacades.product.data.ClassificationData;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;


/**
 *
 */
public class ShowcaseCommonAddonUtilsFunctions
{
	private static final Logger LOG = Logger.getLogger(ShowcaseCommonAddonUtilsFunctions.class);

	public static String getCustomFieldStringValue(final Object object, final String method)
	{
		String result = null;
		try
		{
			result = object.getClass().getMethod(method).invoke(object).toString();
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e)
		{
			LOG.warn("Can not find method: " + method);
		}

		if (result == null && object instanceof ProductData)
		{
			result = getProductDataCustomFiledStringValue((ProductData) object, method);
			if (result == null && StringUtils.startsWith(method, "get")) {
				String fieldName = StringUtils.substring(method, 3);
				fieldName = WordUtils.uncapitalize(fieldName);
				result = getProductDataCustomFiledStringValue((ProductData) object, fieldName);
			}
		}

		return result;
	}

	public static Collection getCustomFieldCollectionValue(final Object object, final String method)
	{
		Collection result = null;
		try
		{
			result = (Collection) object.getClass().getMethod(method).invoke(object);
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e)
		{
			LOG.warn("Can not find method: " + method);
		}

		if (CollectionUtils.isEmpty(result) && object instanceof ProductData)
		{
			result = getProductDataCustomFiledCollectionValue((ProductData) object, method);
			if (CollectionUtils.isEmpty(result) && StringUtils.startsWith(method, "get"))
			{
				String fieldName = StringUtils.substring(method, 3);
				fieldName = WordUtils.uncapitalize(fieldName);
				result = getProductDataCustomFiledCollectionValue((ProductData) object, fieldName);
			}
		}

		return result;
	}

	public static String getProductDataCustomFiledStringValue(final ProductData productData, final String method)
	{
		if (productData.getClassifications() == null)
		{
			LOG.warn("No classification found for: " + productData.getCode());
			return null;
		}
		for (final Iterator<ClassificationData> classIterator = productData.getClassifications().iterator(); classIterator.hasNext();)
		{
			String propertyValue = null;

			try
			{
				propertyValue = classIterator.next().getFeatures().stream()
						.filter(item -> item.getName().equalsIgnoreCase(method)).findFirst().get().getFeatureValues().stream()
						.findFirst().get().getValue();
			}
			catch (final NullPointerException | NoSuchElementException ex)
			{
				LOG.info(productData + " at this attempt was not found for: " + productData.getCode());
			}

			if (StringUtils.isNotBlank(propertyValue))
			{
				return propertyValue;
			}
		}

		LOG.warn(method + " was not found at all for: " + productData.getCode());
		return null;
	}

	public static Collection getProductDataCustomFiledCollectionValue(final ProductData productData, final String method)
	{
		final List<String> propertyValues = new ArrayList<>();
		if (productData.getClassifications() == null)
		{
			LOG.warn("No classification found for: " + productData.getCode());
			return propertyValues;
		}
		for (final Iterator<ClassificationData> classIterator = productData.getClassifications().iterator(); classIterator
				.hasNext();)
		{
			try
			{
				classIterator.next().getFeatures().stream().filter(item -> item.getName().equalsIgnoreCase(method))
						.forEach(featur -> featur.getFeatureValues().forEach(val -> propertyValues.add(val.getValue())));
			}
			catch (final NullPointerException | NoSuchElementException ex)
			{
				LOG.info(method + " at this attempt was not found for: " + productData.getCode());
			}

			if (CollectionUtils.isNotEmpty(propertyValues))
			{
				return propertyValues;
			}
		}

		LOG.warn(method + " was not found at all for: " + productData.getCode());
		return Collections.EMPTY_LIST;
	}
}
