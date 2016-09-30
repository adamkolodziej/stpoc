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
package com.hybris.showcase.media.storage.impl;

import com.google.common.base.Preconditions;
import de.hybris.platform.media.exceptions.MediaNotFoundException;
import de.hybris.platform.media.exceptions.MediaStoreException;
import de.hybris.platform.media.services.MediaLocationHashService;
import de.hybris.platform.media.services.MimeService;
import de.hybris.platform.media.storage.LocalStoringStrategy;
import de.hybris.platform.media.storage.MediaMetaData;
import de.hybris.platform.media.storage.MediaStorageConfigService;
import de.hybris.platform.media.storage.MediaStorageConfigService.MediaFolderConfig;
import de.hybris.platform.media.storage.MediaStorageStrategy;
import de.hybris.platform.media.storage.impl.StoredMediaData;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.util.MediaUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public class CustomLocalDirectoryMediaStorageStrategy implements MediaStorageStrategy, LocalStoringStrategy
{
	private static final Logger LOG = Logger.getLogger(CustomLocalDirectoryMediaStorageStrategy.class);
	public static final String CUSTOM_MEDIA_DIR_PROPERTY = "customLocalDirectoryMediaStorageStrategy.baseDir";

	private MediaLocationHashService locationHashService;
	private MimeService mimeService;
	protected MediaStorageConfigService storageConfigService;

	private ConfigurationService configurationService;

	@Override
	public StoredMediaData store(final MediaFolderConfig config, final String mediaId, final Map<String, Object> metaData,
			final InputStream dataStream)
	{
		Preconditions.checkArgument(config != null, "config is required!");
		Preconditions.checkArgument(mediaId != null, "mediaId is required!");
		Preconditions.checkArgument(metaData != null, "metaData is required!");
		Preconditions.checkArgument(dataStream != null, "dataStream is required!");

		final String originalFileName = (String) metaData.get(MediaMetaData.FILE_NAME);
		final String fileName = getFileName(originalFileName, (String) metaData.get(MediaMetaData.MIME));

		final String relativeMediaDirPath = (String) metaData.get(MediaMetaData.FOLDER_PATH);
		//		final HierarchicalMediaPathBuilder pathBuilder = HierarchicalMediaPathBuilder.forDepth(0);
		//		final String relativeMediaDirPath = pathBuilder.buildPath((String) metaData.get(MediaMetaData.FOLDER_PATH), fileName);

		File absoluteMediaDirPath = null;
		File media = null;
		final List<OutputStream> fos = new ArrayList<OutputStream>();

		try
		{
			absoluteMediaDirPath = getFile(relativeMediaDirPath);
			media = new File(absoluteMediaDirPath, fileName);
			final boolean fileCreated = createNewMediaFile(absoluteMediaDirPath, media);

			if (fileCreated)
			{
				fos.add(new FileOutputStream(media));
			}

			long mediaSize = MediaUtil.copy(dataStream, fos, true);

			if (mediaSize == 0 && media.exists())
			{
				mediaSize = Files.size(media.toPath());
			}

			final String location = relativeMediaDirPath + File.separator + fileName;

			final String mime = (String) metaData.get(MediaMetaData.MIME);
			return new StoredMediaData(location, locationHashService.createHashForLocation(config.getFolderQualifier(), location),
					mediaSize, mime);
		}
		catch (final IOException e)
		{
			throw new MediaStoreException("Error writing media file (mediaId: " + mediaId + ", file:" + media + ", dir:"
					+ absoluteMediaDirPath + ")", e);
		}
		finally
		{
			closeInputStream(dataStream);
			closeOutputStreams(fos);
		}
	}

	private String getBaseDir()
	{
		return getConfigurationService().getConfiguration().getString(CUSTOM_MEDIA_DIR_PROPERTY);
	}

	private String getFileName(final String mediaId, final String mime)
	{
		return mediaId;
	}

	private void closeInputStream(final InputStream dataStream)
	{
		try
		{
			dataStream.close();
		}
		catch (final IOException e)
		{
			LOG.error("cannot close stream", e);
		}
	}

	private void closeOutputStreams(final Collection<OutputStream> fos)
	{
		for (final OutputStream outputStream : fos)
		{
			try
			{
				outputStream.close();
			}
			catch (final IOException e)
			{
				LOG.error("cannot close stream", e);
			}
		}
	}

	@Override
	public void delete(final MediaFolderConfig config, final String location)
	{
		// intentionally blank - we do not want to delete files handled by this strategy
	}

	@Override
	public InputStream getAsStream(final MediaFolderConfig config, String location)
	{
		location = fixLocationBasedOnOS(location);

		Preconditions.checkArgument(config != null, "config is required!");
		Preconditions.checkArgument(location != null, "location is required!");

		final File media = getFile(location);

		try
		{
			return new FileInputStream(media);
		}
		catch (final FileNotFoundException e)
		{
			throw new MediaNotFoundException("Media not found (requested media location: " + location + ")", e);
		}
	}

	// TODO it should work OOTB but does not... ;(
	private String fixLocationBasedOnOS(final String location)
	{
		//        if (File.separator.equals("\\"))
		//        {
		//            //location = location.replaceAll("/", "\\");
		//            location = location.replace('/', '\\');
		//        }
		//        if (File.separator.equals("/"))
		//        {
		//            location = location.replaceAll("\\", "/");
		//        }
		return location;
	}

	@Override
	public File getAsFile(final MediaFolderConfig config, String location)
	{
		location = fixLocationBasedOnOS(location);

		Preconditions.checkArgument(config != null, "folderQualifier is required!");
		Preconditions.checkArgument(location != null, "location is required!");
		return getFile(location);
	}

	private File getFile(final String location) {
		return MediaUtil.composeOrGetParent(new File(getBaseDir()), location);
	}

	private boolean createNewMediaFile(final File absoluteMediaDirPath, final File media) throws IOException
	{
		if (!absoluteMediaDirPath.exists())
		{
			absoluteMediaDirPath.mkdirs();
		}

		if (media.exists() && Files.size(media.toPath()) > 0)
		{
			return false;
		}

		if (media.exists())
		{
			return true;
		}

		return media.createNewFile();
	}

	@Override
	public long getSize(MediaFolderConfig mediaFolderConfig, String location)
	{
		final File file = getFile(location);

		if (file.exists())
		{
			return file.length();
		}

		throw new MediaNotFoundException("Media not found (requested media location: " + location + ")");
	}

	@Required
	public void setStorageConfigService(final MediaStorageConfigService storageConfigService)
	{
		this.storageConfigService = storageConfigService;
	}

	@Required
	public void setLocationHashService(final MediaLocationHashService locationHashService)
	{
		this.locationHashService = locationHashService;
	}

	@Required
	public void setMimeService(final MimeService mimeService)
	{
		this.mimeService = mimeService;
	}

	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	@Required
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}
}
