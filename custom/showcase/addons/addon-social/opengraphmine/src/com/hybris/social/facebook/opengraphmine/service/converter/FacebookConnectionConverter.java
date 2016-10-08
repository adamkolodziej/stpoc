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
package com.hybris.social.facebook.opengraphmine.service.converter;

import de.hybris.platform.converters.impl.AbstractPopulatingConverter;

import org.springframework.social.connect.ConnectionData;

import com.hybris.social.facebook.opengraphmine.model.FacebookConnectionModel;


/**
 * @author fbieg
 * 
 */
public class FacebookConnectionConverter extends AbstractPopulatingConverter<ConnectionData, FacebookConnectionModel>
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commerceservices.converter.impl.AbstractConverter#createTarget()
	 */
	@Override
	protected FacebookConnectionModel createTarget()
	{
		return new FacebookConnectionModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.impl.AbstractPopulatingConverter#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final ConnectionData source, final FacebookConnectionModel target)
	{
		super.populate(source, target);
		target.setAccessToken(source.getAccessToken());
		target.setDisplayName(source.getDisplayName());
		target.setExpireTime(source.getExpireTime());
		target.setImageUrl(source.getImageUrl());
		target.setProfileUrl(source.getProfileUrl());
		target.setProviderId(source.getProviderId());
		target.setProviderUserId(source.getProviderUserId());
		target.setRefreshToken(source.getRefreshToken());
		target.setSecret(source.getSecret());
	}


}
