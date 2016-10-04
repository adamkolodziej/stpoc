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
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import com.hybris.social.facebook.opengraphmine.model.FacebookPageModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookReferenceModel;
import com.hybris.social.facebook.opengraphmine.service.dao.FacebookReferenceDao;


/**
 * @author rmcotton
 * 
 */
public class DefaultFacebookReferenceDao extends AbstractItemDao implements FacebookReferenceDao
{

	/**
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.facebook.service.dao.FacebookReferenceDao#getReferenceById(java.lang.String)
	 */
	@Override
	public FacebookReferenceModel getReferenceById(final String id)
	{
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery("select {PK} from {FacebookReference} where {id} = ?id");
		fsq.addQueryParameter("id", id);
		return searchUnique(fsq);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.facebook.service.dao.FacebookReferenceDao#getPageById(java.lang.String)
	 */
	@Override
	public FacebookPageModel getPageById(final String id)
	{
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery("select {PK} from {FacebookPage} where {id} = ?id");
		fsq.addQueryParameter("id", id);
		return searchUnique(fsq);
	}

}
