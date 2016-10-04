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
package com.hybris.commercesearch.searchandizing.facet.visibilityrules.populators;

import de.hybris.platform.commercesearch.model.solr.LeafCategorySelectedSolrFacetVisibilityRuleModel;
import de.hybris.platform.commercesearch.searchandizing.facet.visibilityrules.populators.AbstractVisibilityRulePopulator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.solrfacetsearch.config.LeafCategorySelectedFacetVisibilityRuleData;


public class LeafCategorySelectedSolrFacetVisibilityRulePopulator
		extends
		AbstractVisibilityRulePopulator<LeafCategorySelectedSolrFacetVisibilityRuleModel, LeafCategorySelectedFacetVisibilityRuleData>
{
	@Override
	public void populate(final LeafCategorySelectedSolrFacetVisibilityRuleModel source,
			final LeafCategorySelectedFacetVisibilityRuleData target) throws ConversionException
	{
		super.populate(source, target);
	}
}
