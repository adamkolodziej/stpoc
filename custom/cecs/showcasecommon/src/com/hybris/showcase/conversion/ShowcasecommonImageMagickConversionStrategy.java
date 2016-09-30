package com.hybris.showcase.conversion;

import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.mediaconversion.conversion.MediaConversionException;
import de.hybris.platform.mediaconversion.imagemagick.ImageMagickMediaConversionStrategy;
import de.hybris.platform.mediaconversion.model.ConversionMediaFormatModel;

import java.io.IOException;

/**
 * Created by miroslaw.szot@sap.com on 2016-04-20.
 */
public class ShowcasecommonImageMagickConversionStrategy extends ImageMagickMediaConversionStrategy {

    @Override
    protected String targetFileExtension(ConversionMediaFormatModel format, MediaModel input) throws IOException, MediaConversionException {
        String ext = null;

        // Try with input format first
        if (input.getMime() != null)
        {
            ext = this.getMimeMappingStrategy().fileExtensionForMimeType(input.getMime());
        }

        if( ext == null ) {

            ext = super.targetFileExtension(format, input);
        }

        return ext;
    }
}
