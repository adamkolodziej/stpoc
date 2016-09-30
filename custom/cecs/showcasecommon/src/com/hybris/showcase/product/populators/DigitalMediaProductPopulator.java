package com.hybris.showcase.product.populators;

import com.hybris.social.common.data.VideoMediaData;
import de.hybris.platform.commercefacades.product.converters.populator.AbstractProductPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.log4j.Logger;

import com.hybris.showcase.cecs.servicesshowcase.model.VideoProductModel;


/**
 * Created by miroslaw.szot@sap.com on 2016-04-18.
 */
public class DigitalMediaProductPopulator<SOURCE extends ProductModel, TARGET extends ProductData>
		extends AbstractProductPopulator<SOURCE, TARGET>
{
	private static final Logger LOG = Logger.getLogger(TVChannelSchedulePopulator.class);

    private Converter<MediaModel, ImageData> imageConverter;

	@Override
	public void populate(final SOURCE source, final TARGET target) throws ConversionException
	{
		if (source instanceof VideoProductModel)
		{
            final MediaModel fullVersionMedia = ((VideoProductModel) source).getFullVersionMedia();
            if( fullVersionMedia != null ) {
                final VideoMediaData fullVersionMediaData = new VideoMediaData();
                target.setFullVersionMedia(fullVersionMediaData);
                populate(fullVersionMedia, fullVersionMediaData);
            }

            final MediaModel freePreviewMedia = ((VideoProductModel) source).getFreePreviewMedia();
            if( freePreviewMedia != null ) {
                final VideoMediaData freePreviewMediaData = new VideoMediaData();
                target.setFreePreviewMedia(freePreviewMediaData);
                populate(fullVersionMedia, freePreviewMediaData);
            }

            MediaModel videoPoster = ((VideoProductModel) source).getVideoPoster();
            if (videoPoster != null) {
                final ImageData imageData = imageConverter.convert(videoPoster);
                imageData.setUrl(videoPoster.getURL());
                imageData.setAltText(videoPoster.getAltText());
                target.setVideoPoster(imageData);
            }
        }
	}

    private void populate(MediaModel source, VideoMediaData target) {
        target.setMediaType(source.getMime());
        target.setUrl(source.getURL());
    }

    public void setImageConverter(Converter<MediaModel, ImageData> imageConverter) {
        this.imageConverter = imageConverter;
    }

}