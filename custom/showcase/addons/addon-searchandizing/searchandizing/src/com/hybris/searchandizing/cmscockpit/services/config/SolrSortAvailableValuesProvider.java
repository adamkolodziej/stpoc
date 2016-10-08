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
package com.hybris.searchandizing.cmscockpit.services.config;

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.admin.CMSAdminSiteService;
import de.hybris.platform.cockpit.model.meta.PropertyDescriptor;
import de.hybris.platform.cockpit.services.config.AvailableValuesProvider;
import de.hybris.platform.cockpit.services.meta.TypeService;
import de.hybris.platform.commerceservices.model.solrsearch.config.SolrSortModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexedTypeModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


/**
 * The list of available values will be filtered according to the selected site.
 */
public class SolrSortAvailableValuesProvider implements AvailableValuesProvider
{
	private static final Logger LOG = Logger.getLogger(SolrSortAvailableValuesProvider.class);


	private CMSAdminSiteService cmsAdminSiteService;
	private TypeService typeService;
	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<? extends Object> getAvailableValues(final PropertyDescriptor propertyDescriptor)
	{
		final CMSSiteModel activeSite = getCmsAdminSiteService().getActiveSite();
		if (activeSite == null)
		{
			LOG.warn("no active site, so returning all solr sorts");
			return typeService.getAvailableValues(propertyDescriptor);
		}

		final Set<SolrSortModel> sorts = new LinkedHashSet<SolrSortModel>();
		final Collection<CatalogModel> catalogs = getCmsAdminSiteService().getAllCatalogs(activeSite);
		for (final CatalogModel catalog : catalogs)
		{
			for (final CatalogVersionModel cv : catalog.getCatalogVersions())
			{
				final List<SolrFacetSearchConfigModel> facetSearchConfigs = cv.getFacetSearchConfigs();
				for (final SolrFacetSearchConfigModel facetSearchConfig : facetSearchConfigs)
				{
					final List<SolrIndexedTypeModel> indexedTypes = facetSearchConfig.getSolrIndexedTypes();
					if (CollectionUtils.isNotEmpty(indexedTypes))
					{
						for (final SolrIndexedTypeModel indexedType : indexedTypes)
						{
							final SolrSortModel example = new SolrSortModel();
							example.setIndexedType(indexedType);
							sorts.addAll(getFlexibleSearchService().getModelsByExample(example));
						}
					}
				}
			}
		}

		return new ArrayList<SolrSortModel>(sorts);
	}


	@Required
	public void setCmsAdminSiteService(final CMSAdminSiteService cmsAdminSiteService)
	{
		this.cmsAdminSiteService = cmsAdminSiteService;
	}

	public CMSAdminSiteService getCmsAdminSiteService()
	{
		return this.cmsAdminSiteService;
	}

	@Required
	public void setTypeService(final TypeService typeService)
	{
		this.typeService = typeService;
	}

	public TypeService getTypeService()
	{
		return this.typeService;
	}

	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}
}
