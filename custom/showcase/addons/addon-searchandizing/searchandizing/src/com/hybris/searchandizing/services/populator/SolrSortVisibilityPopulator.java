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
package com.hybris.searchandizing.services.populator;

import de.hybris.platform.commerceservices.search.solrfacetsearch.config.IndexedTypeSort;
import de.hybris.platform.commerceservices.threadcontext.ThreadContext;
import de.hybris.platform.commerceservices.threadcontext.ThreadContextService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexedTypeModel;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.searchandizing.constants.SearchandizingConstants;



/**
 * @author rmcotton
 * 
 */
public class SolrSortVisibilityPopulator implements Populator<SolrIndexedTypeModel, IndexedType>
{

	private ThreadContextService threadContextService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final SolrIndexedTypeModel source, final IndexedType target) throws ConversionException
	{
		if (isObserveVisibility())
		{
			final List<IndexedTypeSort> sortsToRetain = new LinkedList<IndexedTypeSort>();
			for (final IndexedTypeSort sort : target.getSorts())
			{
				if (Boolean.TRUE.equals(sort.getSort().getVisible()))
				{
					sortsToRetain.add(sort);
				}

			}
			target.setSorts(sortsToRetain);
		}
	}

	protected boolean isObserveVisibility()
	{
		final ThreadContext threadContext = getThreadContextService().getCurrentContext();
		if (threadContext == null)
		{
			return true;
		}
		final Boolean observeVisibility = threadContext.getAttribute(SearchandizingConstants.SESSION_OBSERVE_SORT_VISIBILTY_FLAG);
		if (observeVisibility == null)
		{
			return true;
		}
		return Boolean.TRUE.equals(observeVisibility);
	}

	@Required
	public void setThreadContextService(final ThreadContextService threadContextService)
	{
		this.threadContextService = threadContextService;
	}

	public ThreadContextService getThreadContextService()
	{
		return this.threadContextService;
	}

}
