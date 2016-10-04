package com.hybris.productsets.renderers;

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


import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import com.hybris.addon.common.converters.ConfigurableConverter;
import com.hybris.productsets.facades.data.ProductSetData;
import com.hybris.productsets.model.ProductSetModel;
import com.hybris.productsets.model.components.ProductSetCollectionCarouselComponentModel;


/**
 * Controller for CMS ProductSetCollectionCarouselComponent
 * 
 * @author mkostic
 */
public class ProductSetCollectionCarouselComponentRenderer<T extends ProductSetCollectionCarouselComponentModel> extends
		DefaultAddOnCMSComponentRenderer<T>
{
	private ConfigurableConverter<ProductSetModel, ProductSetData, ProductOption> productSetConverter;

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final T component)
	{
		final Map<String, Object> map = new HashMap<>();
		final List<ProductSetModel> productSetModels = component.getProductSets();
		// Filter out product sets that do not correspond to given media format.
		final List<ProductSetData> filteredProductSets = filterProductSetsByMediaFormat(productSetModels,
				component.getMediaFormat());
		final List<List<ProductSetData>> productSetMatrix = convertToMatrix(filteredProductSets, component.getPerRow().intValue());
		map.put("productSets", productSetMatrix);
		map.put("uid", component.getUid());
		map.put("perRow", component.getPerRow());

		return map;
	}

	/**
	 * Converts a list of product sets to a matrix, with number of rows depending on given product set list size and
	 * desired number of columns. The method will create as many rows with the maximum number of columns as possible. For
	 * example, if a given number of columns is 4 and there are 6 product sets in the list, it will create one row with 4
	 * columns and another row with 2 columns, instead of creating 2 rows with 3 columns each.
	 * 
	 * @param filteredProductSets
	 * @return a matrix of product sets.
	 */
	private List<List<ProductSetData>> convertToMatrix(final List<ProductSetData> filteredProductSets, final int numberOfColumns)
	{
		final List<List<ProductSetData>> productSetMatrix = new ArrayList<>();
		final int divided = filteredProductSets.size() / numberOfColumns;
		final int remain = filteredProductSets.size() % numberOfColumns;
		final int numberOfRows = remain > 0 ? divided + 1 : divided;
		int startIndex = 0;
		int endIndex = numberOfColumns;
		for (int i = 0; i < numberOfRows; i++)
		{
			final List<ProductSetData> column = filteredProductSets.subList(startIndex,
					endIndex > filteredProductSets.size() ? filteredProductSets.size() : endIndex);
			productSetMatrix.add(column);
			startIndex = endIndex;
			endIndex += numberOfColumns;
		}
		return productSetMatrix;
	}

	/**
	 * Filters given product sets by the given media format, converting them to DTO objects at the same time, to avoid
	 * multiple iterations.
	 * 
	 * @param productSetModels
	 * @param mediaFormatModel
	 * @return product sets that have media in given format, or an empty list either if no media format is given or there
	 *         are no sets with such media.
	 */
	private List<ProductSetData> filterProductSetsByMediaFormat(final List<ProductSetModel> productSetModels,
			final MediaFormatModel mediaFormatModel)
	{
		final List<ProductSetData> filteredProductSets = new ArrayList<>();
		// Skip filtering if there is no media format available.
		if (mediaFormatModel != null)
		{
			for (final ProductSetModel productSetModel : productSetModels)
			{
				final MediaContainerModel mediaContainer = productSetModel.getMedias();
				final Collection<MediaModel> mediaModels = mediaContainer.getMedias();
				for (final MediaModel mediaModel : mediaModels)
				{
					final MediaFormatModel currentMediaFormatModel = mediaModel.getMediaFormat();
					if (currentMediaFormatModel != null && currentMediaFormatModel.equals(mediaFormatModel))
					{
						final ProductSetData productSetData = productSetConverter.convert(productSetModel);
						filteredProductSets.add(productSetData);
					}
				}
			}
		}
		return filteredProductSets;
	}

	/**
	 * @param productSetConverter
	 *           the productSetConverter to set
	 */
	public void setProductSetConverter(
			final ConfigurableConverter<ProductSetModel, ProductSetData, ProductOption> productSetConverter)
	{
		this.productSetConverter = productSetConverter;
	}

}
