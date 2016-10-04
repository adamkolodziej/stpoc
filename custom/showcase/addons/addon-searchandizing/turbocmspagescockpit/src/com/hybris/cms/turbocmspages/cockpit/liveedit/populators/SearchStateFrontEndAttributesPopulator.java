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
package com.hybris.cms.turbocmspages.cockpit.liveedit.populators;

import de.hybris.platform.cmscockpit.session.impl.FrontendAttributes;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/**
 * @author rmcotton
 *
 */
public class SearchStateFrontEndAttributesPopulator implements Populator<String[], FrontendAttributes>
{

	protected static final String URL_CHANGE_EVENT = "urlChange";

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final String[] source, final FrontendAttributes target) throws ConversionException
	{
		if (isValidEventId(source[0]))
		{
			if (source.length >= 7 && (source[5] != null && source[6] != null))
			{
				final SearchStateData searchState = new SearchStateData();
				final SearchQueryData searchQuery = new SearchQueryData();
				searchQuery.setValue(source[6]);
				searchState.setQuery(searchQuery);
				searchState.setUrl(source[5]);
				target.setSearchState(searchState);
			}
		}

	}


	protected boolean isValidEventId(final String id)
	{
		return URL_CHANGE_EVENT.equals(id);
	}
}
