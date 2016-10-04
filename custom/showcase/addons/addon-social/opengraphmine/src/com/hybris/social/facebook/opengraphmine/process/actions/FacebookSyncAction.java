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
package com.hybris.social.facebook.opengraphmine.process.actions;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

import com.hybris.social.facebook.opengraphmine.model.process.FacebookSynchronizationProcessModel;

public class FacebookSyncAction extends AbstractPluggableFacebookSyncAction<FacebookClient>
{

	@Override
	protected FacebookClient getSource(final FacebookSynchronizationProcessModel processModel)
	{
		return new DefaultFacebookClient(processModel.getUser().getProlongedAccessToken(),processModel.getApplicationSecret(), Version.VERSION_2_0);
	}
}
