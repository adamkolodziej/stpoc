/**
 * 
 */
package com.hybris.campaigns.search.converters.populators;

import de.hybris.platform.commercefacades.product.ImageFormatMapping;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.PromotionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.media.MediaContainerService;
import de.hybris.platform.servicelayer.media.MediaService;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


/**
 * Populates banners field for Product promotions and additional images connected with promotion type
 */
public class PromotionBannersPopulator implements Populator<AbstractPromotionModel, PromotionData>
{
	private static final Logger LOG = Logger.getLogger(PromotionBannersPopulator.class);

	private Converter<MediaModel, ImageData> imageConverter;
	private List<String> imageFormats;
	private ImageFormatMapping imageFormatMapping;
	private MediaService mediaService;
	private MediaContainerService mediaContainerService;

	@Override
	public void populate(final AbstractPromotionModel promotionModel, final PromotionData promotionData)
			throws ConversionException
	{

		final List<ImageData> promotionBanners = new ArrayList<ImageData>();
		final MediaContainerModel bannerImageContainer = promotionModel.getPromotionBanners();
		if (bannerImageContainer != null)
		{

			for (final String imageFormat : getImageFormats())
			{
				try
				{
					final String mediaFormatCode = getImageFormatMapping().getMediaFormatQualifierForImageFormat(imageFormat);
					if (mediaFormatCode != null)
					{
						final MediaFormatModel mediaFormat = getMediaService().getFormat(mediaFormatCode);
						if (mediaFormat != null)
						{
							try
							{
								final MediaModel media = getMediaContainerService().getMediaForFormat(bannerImageContainer, mediaFormat);
								if (media != null)
								{
									final ImageData imageData = getImageConverter().convert(media);
									imageData.setFormat(imageFormat);
									promotionBanners.add(imageData);
								}
							}
							catch (final ModelNotFoundException mnfe)
							{
								LOG.info("Fetching promotion banner image media failed. Reason: '" + mnfe.getMessage() + "'.");
								continue;
							}
						}
					}
				}
				catch (final ModelNotFoundException mnfe)
				{
					LOG.info("Fetching promotion banner image media failed. Reason: '" + mnfe.getMessage() + "'.");
					continue;
				}
			}
		}
		promotionData.setPromotionTypeCode(promotionModel.getItemtype());
		promotionData.setPromotionBanners(promotionBanners);
	}


	protected List<String> getImageFormats()
	{
		return imageFormats;
	}

	@Required
	public void setImageFormats(final List<String> imageFormats)
	{
		this.imageFormats = imageFormats;
	}

	protected Converter<MediaModel, ImageData> getImageConverter()
	{
		return imageConverter;
	}

	@Required
	public void setImageConverter(final Converter<MediaModel, ImageData> imageConverter)
	{
		this.imageConverter = imageConverter;
	}

	protected ImageFormatMapping getImageFormatMapping()
	{
		return imageFormatMapping;
	}

	@Required
	public void setImageFormatMapping(final ImageFormatMapping imageFormatMapping)
	{
		this.imageFormatMapping = imageFormatMapping;
	}

	protected MediaService getMediaService()
	{
		return mediaService;
	}

	@Required
	public void setMediaService(final MediaService mediaService)
	{
		this.mediaService = mediaService;
	}

	protected MediaContainerService getMediaContainerService()
	{
		return mediaContainerService;
	}

	@Required
	public void setMediaContainerService(final MediaContainerService mediaContainerService)
	{
		this.mediaContainerService = mediaContainerService;
	}
}
