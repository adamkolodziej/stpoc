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
package com.hybris.social.facebook.opengraphmine.service.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.util.FlexibleSearchUtils;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.hybris.social.facebook.opengraphmine.model.FacebookUrlLikeModel;
import com.hybris.social.facebook.opengraphmine.service.dao.FacebookUrlLikeDao;


/**
 * @author rmcotton
 * 
 */
public class DefaultFacebookUrlLikeDao extends AbstractItemDao implements FacebookUrlLikeDao
{


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.social.facebook.opengraphmine.service.dao.FacebookUrlLikeDao#getUrlLikes(java.util.Set)
	 */
	@Override
	public Set<FacebookUrlLikeModel> getUrlLikes(final Set<String> urls)
	{
		final StringBuilder baseQuery = new StringBuilder("select {PK} from {FacebookUrlLike} where ");
		final Map<String, Object> params = new HashMap<>();
		final String where = FlexibleSearchUtils.buildOracleCompatibleCollectionStatement("{url} in (?urls)", "urls", "OR", urls,
				params);
		baseQuery.append(where);
		final SearchResult<FacebookUrlLikeModel> result = getFlexibleSearchService().search(baseQuery.toString(), params);
		return new LinkedHashSet<FacebookUrlLikeModel>(result.getResult());
	}
}
