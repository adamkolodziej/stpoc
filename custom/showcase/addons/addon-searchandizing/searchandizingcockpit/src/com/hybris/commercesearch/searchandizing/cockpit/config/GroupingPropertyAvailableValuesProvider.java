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
package com.hybris.commercesearch.searchandizing.cockpit.config;

import de.hybris.platform.cockpit.model.meta.PropertyDescriptor;
import de.hybris.platform.cockpit.services.config.AvailableValuesProvider;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexedPropertyModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexedTypeModel;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.commercesearch.searchandizing.cockpit.service.SolrPerspectiveAdminService;


/**
 * @author rmcotton
 * 
 */
public class GroupingPropertyAvailableValuesProvider implements AvailableValuesProvider
{
	private SolrPerspectiveAdminService solrPerspectiveAdminService;
	private FlexibleSearchService flexibleSearchService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.cockpit.services.config.AvailableValuesProvider#getAvailableValues(de.hybris.platform.cockpit
	 * .model.meta.PropertyDescriptor)
	 */
	@Override
	public List<? extends Object> getAvailableValues(final PropertyDescriptor propertyDescriptor)
	{
		if (getSolrPerspectiveAdminService().getActiveIndexedType() != null)
		{
			return getSolrPerspectiveAdminService().getActiveIndexedType().getSolrIndexedProperties();
		}


		if (getSolrPerspectiveAdminService().getActiveFacetSearchConfig() != null)
		{
			final List<SolrIndexedPropertyModel> r = new LinkedList<SolrIndexedPropertyModel>();
			for (final SolrIndexedTypeModel indexedType : getSolrPerspectiveAdminService().getActiveFacetSearchConfig()
					.getSolrIndexedTypes())
			{
				r.addAll(indexedType.getSolrIndexedProperties());

			}
			return r;
		}
		else
		{
			final SearchResult<SolrIndexedPropertyModel> sr = getFlexibleSearchService().search(
					"select {PK} from {SolrIndexedProperty}");
			return sr.getResult();
		}
	}

	@Required
	public void setSolrPerspectiveAdminService(final SolrPerspectiveAdminService solrPerspectiveAdminService)
	{
		this.solrPerspectiveAdminService = solrPerspectiveAdminService;
	}

	public SolrPerspectiveAdminService getSolrPerspectiveAdminService()
	{
		return this.solrPerspectiveAdminService;
	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
