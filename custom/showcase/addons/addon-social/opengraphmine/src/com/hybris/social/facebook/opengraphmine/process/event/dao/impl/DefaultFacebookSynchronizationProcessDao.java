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
package com.hybris.social.facebook.opengraphmine.process.event.dao.impl;

import de.hybris.platform.processengine.enums.ProcessState;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;

import java.util.Collections;
import java.util.List;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.model.process.FacebookSynchronizationProcessModel;
import com.hybris.social.facebook.opengraphmine.process.event.dao.FacebookSynchronizationProcessDao;


/**
 * @author rmcotton
 * 
 */
public class DefaultFacebookSynchronizationProcessDao extends AbstractItemDao implements FacebookSynchronizationProcessDao
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.facebook.process.event.dao.FacebookSynchronizationProcessDao#
	 * getFacebookSynchronizationProcessesForUser(de.hybris.platform.social.jalo.facebook.FacebookUser,
	 * de.hybris.platform.processengine.enums.ProcessState)
	 */
	@Override
	public List<FacebookSynchronizationProcessModel> getFacebookSynchronizationProcessesForUser(final FacebookUserModel user,
			final ProcessState processstate)
	{
		final FacebookSynchronizationProcessModel example = new FacebookSynchronizationProcessModel();
		example.setUser(user);
		example.setState(processstate);
		try
		{
			return getFlexibleSearchService().getModelsByExample(example);
		}
		catch (final ModelNotFoundException e)
		{
			return Collections.emptyList();
		}
	}

}
