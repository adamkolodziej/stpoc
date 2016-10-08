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
package com.hybris.commercesearch.searchandizing.cockpit.service;

import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexedTypeModel;

import java.util.Collection;


/**
 * @author rmcotton
 * 
 */
public interface SolrPerspectiveAdminService
{
	void setActiveFacetSearchConfig(SolrFacetSearchConfigModel activeConfig);

	SolrFacetSearchConfigModel getActiveFacetSearchConfig();

	void setActiveIndexedType(SolrIndexedTypeModel activeIndexedType);

	SolrIndexedTypeModel getActiveIndexedType();

	Collection<SolrFacetSearchConfigModel> getAllFacetSearchConfigs();

}
