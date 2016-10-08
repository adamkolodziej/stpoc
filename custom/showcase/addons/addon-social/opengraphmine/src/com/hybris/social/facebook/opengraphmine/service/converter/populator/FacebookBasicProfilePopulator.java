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

import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hybris.social.facebook.opengraphmine.constants.OpengraphmineConstants;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookBasicProfilePopulator implements Populator<FacebookClient, FacebookUserModel>
{
	private static final Logger LOG = Logger.getLogger(FacebookBasicProfilePopulator.class);

	/**
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.servicelayer.dto.converter.Converter#convert(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final FacebookClient source, final FacebookUserModel prototype) throws ConversionException
	{
		final User facebookUser = source.fetchObject("me", User.class, Parameter.with("fields",
				"email, birthday, link, first_name, last_name, picture, gender, locale, name"));
		prototype.setUid(facebookUser.getId());
		prototype.setFacebookUpdatedTime(facebookUser.getUpdatedTime());
		prototype.setProfileLink(facebookUser.getLink());

		updateEmail(facebookUser, prototype);
		updateLocale(facebookUser, prototype);
		updateName(facebookUser, prototype);
		updateGender(facebookUser, prototype);
		updateBirthday(facebookUser, prototype);
		updatePictureInfo(facebookUser, prototype);
	}

	protected void updatePictureInfo(final User facebookUser, final FacebookUserModel prototype)
	{
		if(facebookUser.getPicture()!=null)
		{
			prototype.setProfilePictureUrl(facebookUser.getPicture().getUrl());
		}
	}

	protected void updateName(final User facebookUser, final FacebookUserModel prototype)
	{
		prototype.setName(facebookUser.getName());
		prototype.setFirstname(facebookUser.getFirstName());
		prototype.setLastname(facebookUser.getLastName());
	}

	protected void updateGender(final User facebookUser, final FacebookUserModel prototype)
	{
		if (StringUtils.isBlank(facebookUser.getGender()))
		{
			prototype.setGender(null);
		}
		else
		{
			if (StringUtils.equalsIgnoreCase("MALE", facebookUser.getGender()))
			{
				prototype.setGender(Gender.MALE);
			}
			else if (StringUtils.equalsIgnoreCase("FEMALE", facebookUser.getGender()))
			{
				prototype.setGender(Gender.FEMALE);
			}
			else
			{
				LOG.warn("unable to map gender value [" + facebookUser.getGender() + "] to a Gender");
				prototype.setGender(null);
			}

		}
	}

	protected void updateBirthday(final User facebookUser, final FacebookUserModel userModel)
	{
		final String birthdayStr = facebookUser.getBirthday();
		Date birthdate = null;

		if (StringUtils.isNotBlank(birthdayStr))
		{
			final SimpleDateFormat dateformatter = new SimpleDateFormat(OpengraphmineConstants.FB_BORTHDAY_DATE_FORMAT);

			try
			{
				birthdate = dateformatter.parse(birthdayStr);
			}
			catch (final ParseException e)
			{
				LOG.warn("unable to parse birthday received from facebook  [" + birthdayStr + "] to a java.util.Date", e);
			}
		}

		userModel.setBirthday(birthdate);
	}

	protected void updateEmail(final User facebookUser, final FacebookUserModel prototype)
	{
		prototype.setEmail(facebookUser.getEmail());
	}

	protected void updateLocale(final User facebookUser, final FacebookUserModel prototype)
	{
		if (facebookUser.getLocale() != null)
		{
			prototype.setLocale(facebookUser.getLocale().toString());
		}
		else
		{
			prototype.setLocale(null);
		}
	}


}
