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
package com.hybris.commercesearch.searchandizing.cockpit.service.impl;



import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.solrfacetsearch.config.impl.FacetSearchConfigDao;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexedTypeModel;

import java.util.Collection;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.commercesearch.searchandizing.cockpit.service.SolrPerspectiveAdminService;


/**
 * @author rmcotton
 * 
 */
public class DefaultSolrPerspectiveAdminService implements SolrPerspectiveAdminService
{

	private static final String FACETSEARCHCONFIG_KEY = "sSolrFacetSearchConfig";
	private static final String INDEXEDTYPE_KEY = "sSolrIndexedType";

	private SessionService sessionService;
	private FacetSearchConfigDao facetSearchConfigDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.searchandizing.search.solrfacetsearch.admin.SolrSearchandizingAdminService#
	 * setActiveFacetSearchConfig(de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel)
	 */
	@Override
	public void setActiveFacetSearchConfig(final SolrFacetSearchConfigModel activeConfig)
	{
		getSessionService().setAttribute(FACETSEARCHCONFIG_KEY, activeConfig);
		if (activeConfig == null)
		{
			setActiveIndexedType(null);
		}
		else
		{
			final SolrIndexedTypeModel activeIndexedType = getActiveIndexedType();
			if (activeIndexedType == null)
			{
				// just one index type default - no further selection required
				if (activeConfig.getSolrIndexedTypes().size() == 1)
				{
					setActiveIndexedType(activeConfig.getSolrIndexedTypes().get(0));
				}
			}
			else if (!activeConfig.getSolrIndexedTypes().contains(activeIndexedType))
			{
				// just one index type default - no further selection required
				if (activeConfig.getSolrIndexedTypes().size() == 1)
				{
					setActiveIndexedType(activeConfig.getSolrIndexedTypes().get(0));
				}
				else
				{
					setActiveIndexedType(null);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.searchandizing.search.solrfacetsearch.admin.SolrSearchandizingAdminService#
	 * getActiveFacetSearchConfig()
	 */
	@Override
	public SolrFacetSearchConfigModel getActiveFacetSearchConfig()
	{
		return getSessionService().getAttribute(FACETSEARCHCONFIG_KEY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.searchandizing.search.solrfacetsearch.admin.SolrSearchandizingAdminService#setActiveIndexedType
	 * (de.hybris.platform.solrfacetsearch.model.config.SolrIndexedTypeModel)
	 */
	@Override
	public void setActiveIndexedType(final SolrIndexedTypeModel activeIndexedType)
	{
		if (activeIndexedType != null)
		{
			if (!ObjectUtils.equals(getActiveFacetSearchConfig(), activeIndexedType.getSolrFacetSearchConfig()))
			{
				setActiveFacetSearchConfig(activeIndexedType.getSolrFacetSearchConfig());
			}
		}
		getSessionService().setAttribute(INDEXEDTYPE_KEY, activeIndexedType);


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.searchandizing.search.solrfacetsearch.admin.SolrSearchandizingAdminService#getActiveIndexedType
	 * ()
	 */
	@Override
	public SolrIndexedTypeModel getActiveIndexedType()
	{
		return getSessionService().getAttribute(INDEXEDTYPE_KEY);
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.searchandizing.search.solrfacetsearch.admin.SolrSearchandizingAdminService#getAllFacetSearchConfigs
	 * ()
	 */
	@Override
	public Collection<SolrFacetSearchConfigModel> getAllFacetSearchConfigs()
	{
		return getFacetSearchConfigDao().findAllSolrFacetSearchConfigs();
	}

	/**
	 * @return the facetSearchConfigDao
	 */
	public FacetSearchConfigDao getFacetSearchConfigDao()
	{
		return facetSearchConfigDao;
	}

	/**
	 * @param facetSearchConfigDao
	 *           the facetSearchConfigDao to set
	 */
	@Required
	public void setFacetSearchConfigDao(final FacetSearchConfigDao facetSearchConfigDao)
	{
		this.facetSearchConfigDao = facetSearchConfigDao;
	}

}
