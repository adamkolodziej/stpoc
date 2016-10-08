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
import com.restfb.types.Page;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.net.URI;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.social.support.URIBuilder;
import org.springframework.web.client.RestOperations;

import com.hybris.social.facebook.opengraphmine.model.FacebookPageModel;


/**
 * @author Muneendra
 * 
 */
public class FacebookPageMediaPopulator implements Populator<FacebookClient, FacebookPageModel>
{
	private static final Logger LOG = Logger.getLogger(FacebookPageMediaPopulator.class);
	private CatalogVersionService catalogVersionService;
	private MediaService mediaService;
	private ModelService modelService;
	private String mediaCatalogId = "Default";
	private String mediaCatalogVersion = "Staged";
	private int imageRefreshIntervalInMinutes = 60 * 24 * 7; // Refresh Image Weekly 


	protected boolean isStale(final MediaModel media)
	{
		if (media == null)
		{
			return true;
		}

		return new Date().after(DateUtils.addMinutes(media.getModifiedtime(), getImageRefreshIntervalInMinutes()));

	}

	protected void populatePagePicture(final FacebookClient connection, final Page page, final FacebookPageModel target)
	{
		final URI pagepicUri = URIBuilder.fromUri(page.getId() + "/picture").build();
//		final RestOperations restOperations = connection.getApi().restOperations();
//
//
//		ResponseEntity<byte[]> response = null;
//		try
//		{
//			response = restOperations.getForEntity(pagepicUri, byte[].class);
//			final byte[] rawimage = response.getBody();
//
//			MediaModel picture = target.getPicture();
//			//rawimage = null;
//			if (picture != null && rawimage != null && rawimage.length > 0)
//			{
//				picture.setAltText(target.getName());
//				getMediaService().setDataForMedia(picture, rawimage);
//				getModelService().save(picture);
//
//			}
//			else if (picture == null && rawimage != null && rawimage.length > 0)
//			{
//				picture = getModelService().create(MediaModel.class);
//				picture.setCode(target.getId() + "-facebook-pagepic-" + System.currentTimeMillis());
//				picture
//						.setCatalogVersion(getCatalogVersionService().getCatalogVersion(getMediaCatalogId(), getMediaCatalogVersion()));
//				target.setPicture(picture);
//				picture.setAltText(target.getName());
//				getModelService().save(picture);
//				getMediaService().setDataForMedia(picture, rawimage);
//
//			}
//			else if (picture != null && (rawimage == null || rawimage.length > 0))
//			{
//				// when rawimage is null, means picture is removed
//				target.setPicture(null);
//				try
//				{
//					getModelService().remove(picture.getPk());
//				}
//				catch (final Exception e)
//				{
//					LOG.error("Error while removing the picture from type system", e);
//				}
//			}
//		}
//		catch (final Exception e)
//		{
//			LOG.error("Error while at facebook picture and facebook link process", e);
//		}
	}


	public void populateCoverPhoto(final FacebookClient connection, final Page page, final FacebookPageModel target)
	{
        final URI pagepicUri = URIBuilder.fromUri(page.getId() + "/picture").build();
//		if (page.getCover() != null && page.getCover().getSource() != null)
//		{
//			if (target.getCoverPhoto() == null || coverFilenameChanged(page, target.getCoverPhoto()))
//			{
//				if (LOG.isInfoEnabled() && target.getCoverPhoto() != null && coverFilenameChanged(page, target.getCoverPhoto()))
//				{
//					LOG.info("Cover for page [" + page.getId() + "] changed. Old [" + target.getPicture().getRealFileName()
//							+ "] new [" + page.getCover().getSource() + "]");
//				}
//
//				// download new image if filepaths dont match
//				final ResponseEntity<byte[]> response = connection.getApi().restOperations()
//						.getForEntity(page.getCover().getSource(), byte[].class);
//				final byte[] rawimage = response.getBody();
//
//				MediaModel picture;
//				if (target.getCoverPhoto() != null)
//				{
//					picture = target.getCoverPhoto();
//				}
//				else
//				{
//					picture = getModelService().create(MediaModel.class);
//					picture.setCode(page.getId() + "-" + "-facebook-coverphoto-" + page.getCover().getId() + "-"
//							+ System.currentTimeMillis());
//					picture.setCatalogVersion(getCatalogVersionService().getCatalogVersion(getMediaCatalogId(),
//							getMediaCatalogVersion()));
//					target.setCoverPhoto(picture);
//					getModelService().save(picture);
//				}
//
//				picture.setAltText(target.getName());
//				picture.setRealFileName(page.getCover().getSource()
//						.substring(StringUtils.lastIndexOf(page.getCover().getSource(), "/") + 1));
//				getMediaService().setDataForMedia(picture, rawimage);
//				getModelService().save(picture);
//			}
//		}
	}



	protected boolean coverFilenameChanged(final Page page, final MediaModel picture)
	{
		return !StringUtils.endsWithIgnoreCase(page.getCover().getSource(), picture.getRealFileName());
	}

	@Override
	public void populate(final FacebookClient connection, final FacebookPageModel target)
	{

//		final boolean pageStale = isStale(target.getPicture());
//		final boolean coverStale = isStale(target.getCoverPhoto());
//
//
//
//		if (pageStale || coverStale)
//		{
//			final PageOperations pageOperations = connection.getApi().pageOperations();
//			final Page page = pageOperations.getPage(target.getId());
//
//			if (page != null)
//			{
//				if (pageStale)
//				{
//					populatePagePicture(connection, page, target);
//				}
//				if (coverStale)
//				{
//					populateCoverPhoto(connection, page, target);
//				}
//			}
//
//		}
	}

	public void setMediaCatalogId(final String catalogId)
	{
		this.mediaCatalogId = catalogId;
	}

	/**
	 * @return the mediaCatalogId
	 */
	public String getMediaCatalogId()
	{
		return mediaCatalogId;
	}

	public void setMediaCatalogVersion(final String catalogVersion)
	{
		this.mediaCatalogVersion = catalogVersion;
	}

	/**
	 * @return the mediaCatalogVersion
	 */
	public String getMediaCatalogVersion()
	{
		return mediaCatalogVersion;
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

	public void setImageRefreshIntervalInMinutes(final int imageRefreshInterval)
	{
		this.imageRefreshIntervalInMinutes = imageRefreshInterval;
	}

	public int getImageRefreshIntervalInMinutes()
	{
		return this.imageRefreshIntervalInMinutes;
	}

}
