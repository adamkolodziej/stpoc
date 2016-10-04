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
package com.hybris.social.facebook.opengraphmine.service.converter.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.hybris.social.facebook.opengraphmine.facade.data.FacebookUserData;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;


public class FacebookUserDataPopulator<SOURCE extends FacebookUserModel, TARGET extends FacebookUserData> implements
		Populator<SOURCE, TARGET>
{
	@Override
	public void populate(final SOURCE source, final TARGET target) throws ConversionException
	{
		target.setFirstname(source.getFirstname());
		target.setLastname(source.getLastname());
		target.setProfileLink(source.getProfileLink());
		target.setSmallProfilePictureURL(source.getSmallProfilePicture() == null ? null : source.getSmallProfilePicture().getURL());
	}
}
