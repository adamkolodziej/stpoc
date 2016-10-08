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
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

import org.springframework.social.connect.ConnectionData;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;


/**
 * @author rmcotton
 * 
 */
public class AbstractFacebookSyncEvent extends AbstractEvent
{
	private final FacebookClient connection;
	private final Long appId;
	private final String appSecret;
	private final boolean force;
	private final FacebookUserModel facebookUser;


	public AbstractFacebookSyncEvent(final FacebookClient connection, final FacebookUserModel facebookUser, final Long appId,
			final String appSecret)
	{
		this(connection, facebookUser, appId, appSecret, false);
	}

	public AbstractFacebookSyncEvent(final FacebookClient connection, final FacebookUserModel facebookUser, final Long appId,
			final String appSecret, final boolean force)
	{
		super();
		this.connection = connection;
		this.appId = appId;
		this.appSecret = appSecret;
		this.force = force;
		this.facebookUser = facebookUser;
	}



	/**
	 * Forces the sync regardless of whether one is already running.
	 */
	public boolean isForce()
	{
		return this.force;
	}



	public FacebookClient getConnection()
	{
		return connection;
	}

	public Long getAppId()
	{
		return appId;
	}

	public String getAppSecret()
	{
		return appSecret;
	}

	public FacebookUserModel getFacebookUser()
	{
		return facebookUser;
	}
}
