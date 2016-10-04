/**
 * 
 */
package com.hybris.productsets.converters.populator;

import de.hybris.platform.commercefacades.product.data.BaseOptionData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.VariantOptionData;
import de.hybris.platform.commercefacades.product.data.VariantOptionQualifierData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.util.Assert;

import com.hybris.productsets.facades.data.VariantSelectItemData;
import com.hybris.productsets.facades.data.VariantSelectOptionData;


/**
 * @author dariusz.malachowski
 * 
 */
public class DefaultVariantSelectOptionPopulator implements Populator<ProductData, Set<VariantSelectOptionData>>
{

	@Override
	public void populate(final ProductData source, final Set<VariantSelectOptionData> target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");


		final Map<String, List<VariantSelectItemData>> baseOptionsMap = new HashMap<String, List<VariantSelectItemData>>();
		final Map<String, List<VariantSelectItemData>> variantOptionsMap = buildVariantsMap(source.getVariantOptions());
		final Map<String, String> currentProductVariant = new HashMap<String, String>();

		// Sets base option variants
		for (final BaseOptionData baseOptionData : source.getBaseOptions())
		{
			final Map<String, List<VariantSelectItemData>> currentOptionsMap = buildVariantsMap(baseOptionData.getOptions());
			baseOptionsMap.putAll(currentOptionsMap);

			final VariantOptionData selectedVariant = baseOptionData.getSelected();

			// Store the current product variant options
			if (selectedVariant != null && selectedVariant.getUrl().equals(source.getUrl()))
			{
				for (final VariantOptionQualifierData selectedQualifierData : selectedVariant.getVariantOptionQualifiers())
				{
					currentProductVariant.put(selectedQualifierData.getName().toLowerCase(), selectedQualifierData.getValue());
				}
			}
		}

		final Iterator baseOptionIterator = baseOptionsMap.entrySet().iterator();
		while (baseOptionIterator.hasNext())
		{
			final Map.Entry<String, List<VariantSelectItemData>> entry = (Entry<String, List<VariantSelectItemData>>) baseOptionIterator
					.next();

			final VariantSelectOptionData variantSelectOptionData = new VariantSelectOptionData();
			variantSelectOptionData.setType(entry.getKey());
			variantSelectOptionData.setOptions(entry.getValue());

			// If no variants then take the last element of base option and make it as leaf
			if (variantOptionsMap.isEmpty() && !baseOptionIterator.hasNext())
			{
				variantSelectOptionData.setLeaf(Boolean.TRUE);
			}

			// Set the selected value for current variant
			if (currentProductVariant.containsKey(entry.getKey()))
			{
				variantSelectOptionData.setSelectedOption(currentProductVariant.get(entry.getKey()));
			}
			target.add(variantSelectOptionData);
		}

		// If variants not empty then add that one which are not in base options and set them as leaf
		if (!variantOptionsMap.isEmpty())
		{
			for (final Map.Entry<String, List<VariantSelectItemData>> entrySet : variantOptionsMap.entrySet())
			{
				if (!baseOptionsMap.containsKey(entrySet.getKey()))
				{
					final VariantSelectOptionData variantSelectOptionData = new VariantSelectOptionData();
					variantSelectOptionData.setType(entrySet.getKey());
					variantSelectOptionData.setOptions(entrySet.getValue());
					variantSelectOptionData.setLeaf(Boolean.TRUE);
					target.add(variantSelectOptionData);
				}
			}
		}
	}

	private Map<String, List<VariantSelectItemData>> buildVariantsMap(final List<VariantOptionData> sourceList)
	{
		final Map<String, List<VariantSelectItemData>> optionQualifiersMap = new HashMap<String, List<VariantSelectItemData>>();

		if (sourceList != null && !sourceList.isEmpty())
		{
			for (final VariantOptionData variant : sourceList)
			{
				for (final VariantOptionQualifierData qualifierData : variant.getVariantOptionQualifiers())
				{
					final String type = qualifierData.getQualifier();
					final VariantSelectItemData itemData = buildOptionQualifier(qualifierData, variant);
					List<VariantSelectItemData> itemDataList = optionQualifiersMap.get(type);

					if (itemDataList == null)
					{
						itemDataList = new ArrayList<VariantSelectItemData>();
						itemDataList.add(itemData);
						optionQualifiersMap.put(type, itemDataList);
					}
					else
					{
						itemDataList.add(itemData);
					}
				}
			}
		}
		return optionQualifiersMap;
	}

	private VariantSelectItemData buildOptionQualifier(final VariantOptionQualifierData qualifierData,
			final VariantOptionData variant)
	{
		final VariantSelectItemData itemData = new VariantSelectItemData();
		itemData.setCode(variant.getCode());
		itemData.setUrl(variant.getUrl());
		itemData.setValue(qualifierData.getValue());
		return itemData;
	}
}
