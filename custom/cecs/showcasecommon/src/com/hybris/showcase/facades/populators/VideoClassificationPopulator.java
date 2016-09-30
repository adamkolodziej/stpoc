package com.hybris.showcase.facades.populators;

import de.hybris.platform.commercefacades.product.converters.populator.AbstractProductPopulator;
import de.hybris.platform.commercefacades.product.data.ClassificationData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * Created by miroslaw.szot@sap.com on 2016-04-01.
 */
public class VideoClassificationPopulator<SOURCE extends ProductModel, TARGET extends ProductData>
		extends AbstractProductPopulator<SOURCE, TARGET>
{

	private static final Logger LOG = Logger.getLogger(VideoClassificationPopulator.class);

	@Override
	public void populate(final SOURCE source, final TARGET target) throws ConversionException
	{
		if (CollectionUtils.isEmpty(target.getClassifications()))
		{
			LOG.warn("Features have't been populated yet!");
		}

		target.setRunning(getClassificationProperty(target, "running"));
		target.setStarring(getClassificationProperties(target, "starring"));
		target.setProductionCompanies(getClassificationProperties(target, "Production companies"));
		target.setDirector(getClassificationProperties(target, "director"));
		target.setGenre(getClassificationProperties(target, "genre"));
		try
		{
			target.setReleaseYear(Integer.valueOf(getClassificationProperty(target, "Release year")));
		}
		catch (final NumberFormatException ex)
		{
			LOG.warn("releaseYear has wrong number format for: " + target.getCode());
		}
	}

	public String getClassificationProperty(final TARGET target, final String propertyName)
	{
		if (target.getClassifications() == null)
		{
			LOG.warn("No classification found for: " + target.getCode());
			return null;
		}
		for (final Iterator<ClassificationData> classIterator = target.getClassifications().iterator(); classIterator.hasNext();)
		{
			String propertyValue = null;

			try
			{
				propertyValue = classIterator.next().getFeatures().stream()
						.filter(item -> item.getName().equalsIgnoreCase(propertyName)).findFirst().get().getFeatureValues().stream()
						.findFirst().get().getValue();
			}
			catch (final NullPointerException | NoSuchElementException ex)
			{
				LOG.info(propertyName + " at this attempt was not found for: " + target.getCode());
			}

			if (StringUtils.isNotBlank(propertyValue))
			{
				return propertyValue;
			}
		}

		LOG.warn(propertyName + " was not found at all for: " + target.getCode());
		return null;
	}

	public List<String> getClassificationProperties(final TARGET target, final String propertyName)
	{
		final List<String> propertyValues = new ArrayList<>();
		if (target.getClassifications() == null)
		{
			LOG.warn("No classification found for: " + target.getCode());
			return propertyValues;
		}
		for (final Iterator<ClassificationData> classIterator = target.getClassifications().iterator(); classIterator.hasNext();)
		{
			try
			{
				classIterator.next().getFeatures().stream().filter(item -> item.getName().equalsIgnoreCase(propertyName))
						.forEach(featur -> featur.getFeatureValues().forEach(val -> propertyValues.add(val.getValue())));
			}
			catch (final NullPointerException | NoSuchElementException ex)
			{
				LOG.info(propertyName + " at this attempt was not found for: " + target.getCode());
			}

			if (CollectionUtils.isNotEmpty(propertyValues))
			{
				return propertyValues;
			}
		}

		LOG.warn(propertyName + " was not found at all for: " + target.getCode());
		return Collections.EMPTY_LIST;
	}
}
