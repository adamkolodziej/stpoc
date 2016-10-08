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
package com.hybris.social.facebook.opengraphmine.process.event;

import com.restfb.FacebookClient;
import org.springframework.social.connect.ConnectionData;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;



/**
 * Call this to trigger a background facebook profile sync.
 * 
 * @author rmcotton
 * @author fbieg
 * 
 */
public class FacebookProfileSyncEvent extends AbstractFacebookSyncEvent
{

	public FacebookProfileSyncEvent(final FacebookClient connection, final FacebookUserModel facebookUser, final Long appId,
			final String appSecret)
	{
		super(connection, facebookUser, appId, appSecret);
	}


	public FacebookProfileSyncEvent(final FacebookClient connection, final FacebookUserModel facebookUser, final Long appId,
			final String appSecret, final boolean force)
	{
		super(connection, facebookUser, appId, appSecret, force);
	}



}