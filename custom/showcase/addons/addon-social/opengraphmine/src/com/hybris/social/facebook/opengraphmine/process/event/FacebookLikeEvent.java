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
 * @author rmcotton
 * 
 */
public class FacebookLikeEvent extends AbstractFacebookSyncEvent
{
	public static enum TYPE
	{
		LIKE, UNLIKE
	}

	private final TYPE type;


	public FacebookLikeEvent(final FacebookClient connection, final FacebookUserModel facebookUser, final Long appId,
			final String appSecret, final TYPE type)
	{
		this(connection, facebookUser, appId, appSecret, false, type);
	}

	public FacebookLikeEvent(final FacebookClient connection, final FacebookUserModel facebookUser, final Long appId,
			final String appSecret, final boolean force, final TYPE type)
	{
		super(connection, facebookUser, appId, appSecret, force);
		this.type = type;

	}

	public TYPE getType()
	{
		return this.type;
	}

}
