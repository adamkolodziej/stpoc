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

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.net.URL;


import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookMediaPopulator implements Populator<FacebookClient, FacebookUserModel>
{

	private MediaService mediaService;
	private ModelService modelService;
	private CatalogVersionService catalogVersionService;

	private final String mediaCatalogId = "Default";
	private final String mediaCatalogVersion = "Staged";

	private boolean updateOnlyWhenNull = false;

	private static final Logger LOG = Logger.getLogger(FacebookMediaPopulator.class);

	@Override
	public void populate(final FacebookClient source, final FacebookUserModel target) throws ConversionException
	{
		final User user = source.fetchObject("me", User.class, Parameter.with("fields",
				"picture"));
		processNormalImage(user, target);
		processSmallImage(user, target);
	}
    //done elsewhere
	protected void processNormalImage(final User user, final FacebookUserModel target)
	{
		MediaModel profilePicture = target.getProfilePicture();

		if (isUpdateOnlyWhenNull() && profilePicture != null)
		{
			return;
		}

		final byte[] rawimage;

		if(user.getPicture() != null)
		{
			rawimage = downloadFacebookPicture(user.getPicture().getUrl());
		} else
		{
			target.setProfilePicture(null);
			return;
		}

		if (profilePicture == null)
		{
			profilePicture = getModelService().create(MediaModel.class);
			profilePicture.setCode(target.getUid() + "-facebook-profilepic-" + System.currentTimeMillis());
			profilePicture.setCatalogVersion(getCatalogVersionService().getCatalogVersion(getMediaCatalogId(),
					getMediaCatalogVersion()));
			target.setProfilePicture(profilePicture);
			getModelService().save(profilePicture);
		}
		else
		{
			final byte[] currentMedia = getMediaService().getDataFromMedia(profilePicture);
			if (currentMedia != null && Arrays.equals(rawimage, currentMedia))
			{
				// same image
				return;
			}
		}

		profilePicture.setAltText(target.getName());
		getMediaService().setDataForMedia(profilePicture, rawimage);
		getModelService().save(profilePicture);
	}

	protected void processSmallImage(final User user, final FacebookUserModel target)
	{
		MediaModel profilePicture = target.getSmallProfilePicture();

		if (isUpdateOnlyWhenNull() && profilePicture != null)
		{
			return;
		}

		final byte[] rawimage;

		if(user.getPicture() != null)
		{
			rawimage = downloadFacebookPicture(user.getPicture().getUrl());
		} else
		{
			target.setSmallProfilePicture(null);
			return;
		}

		if (profilePicture == null)
		{
			profilePicture = getModelService().create(MediaModel.class);
			profilePicture.setCode(target.getUid() + "-facebook-smallprofilepic-" + System.currentTimeMillis());
			profilePicture.setCatalogVersion(getCatalogVersionService().getCatalogVersion(getMediaCatalogId(),
					getMediaCatalogVersion()));
			target.setSmallProfilePicture(profilePicture);
			getModelService().save(profilePicture);
		}
		else
		{
			final byte[] currentMedia = getMediaService().getDataFromMedia(profilePicture);
			if (currentMedia != null && Arrays.equals(rawimage, currentMedia))
			{
				// same image
				return;
			}
		}

		profilePicture.setAltText(target.getName());
		getMediaService().setDataForMedia(profilePicture, rawimage);
		getModelService().save(profilePicture);
	}

	private byte[] downloadFacebookPicture(final String url) {
		final URL u;
		InputStream stream = null;
		byte[] imageBytes = new byte[0];

		try
		{
			u = new URL(url);
			stream = u.openStream();
			imageBytes = IOUtils.toByteArray(stream);
		}
		catch (IOException e)
		{
			LOG.debug("There were some issues while trying to download facebook profile picture from url: " + url);
			LOG.error(e);
		}
		return imageBytes;
	}

	@Required
	public void setMediaService(final MediaService mediaService)
	{
		this.mediaService = mediaService;
	}


	public MediaService getMediaService()
	{
		return this.mediaService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param catalogVersionService
	 *           the catalogVersionService to set
	 */
	@Required
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	/**
	 * @return the catalogVersionService
	 */
	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	/**
	 * @return the mediaCatalogId
	 */
	public String getMediaCatalogId()
	{
		return mediaCatalogId;
	}

	/**
	 * @return the mediaCatalogVersion
	 */
	public String getMediaCatalogVersion()
	{
		return mediaCatalogVersion;
	}

	/**
	 * @param updateOnlyIfNull
	 *           the updateOnlyIfNull to set
	 */
	public void setUpdateOnlyWhenNull(final boolean updateOnlyIfNull)
	{
		this.updateOnlyWhenNull = updateOnlyIfNull;
	}

	/**
	 * @return the updateOnlyIfNull
	 */
	public boolean isUpdateOnlyWhenNull()
	{
		return updateOnlyWhenNull;
	}

}
