package com.hybris.showcase.guidedselling.populators;

import de.hybris.platform.converters.Populator;

import org.apache.commons.collections.CollectionUtils;

import com.hybris.showcase.guidedselling.data.BundlePackageData;
import com.hybris.showcase.guidedselling.model.BundlePackageModel;


/**
 * CECS-148 Packages Page Created by I303845 on 2015-03-04.
 */
public class BundlePackageCMSPopulator<SOURCE extends BundlePackageModel, TARGET extends BundlePackageData>
		implements Populator<SOURCE, TARGET>
{
	@Override
	public void populate(final SOURCE source, final TARGET target)
	{
		if (CollectionUtils.isNotEmpty(source.getDescriptionSlotsContainer()))
		{
			target.setDescriptionSlotsContainer(source.getDescriptionSlotsContainer().iterator().next());
		}

		if (CollectionUtils.isNotEmpty(source.getRelatedComponentsContainers()))
		{
			target.setRelatedComponentsContainer(source.getRelatedComponentsContainers().iterator().next());
		}
	}

}
