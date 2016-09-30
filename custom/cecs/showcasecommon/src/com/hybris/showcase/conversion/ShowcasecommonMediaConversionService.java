package com.hybris.showcase.conversion;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;
//import de.hybris.platform.mediaconversion.constants.GeneratedMediaConversionConstants.Enumerations.ConversionStatus;
import de.hybris.platform.mediaconversion.conversion.DefaultMediaConversionService;
import de.hybris.platform.mediaconversion.enums.ConversionStatus;
import de.hybris.platform.mediaconversion.model.ConversionGroupModel;


/**
 * @author miroslaw.szot
 */
public class ShowcasecommonMediaConversionService extends DefaultMediaConversionService
{
	@Override
	public MediaModel getOrConvert(final MediaContainerModel container, final MediaFormatModel format)
	{
		final ConversionStatus status = container.getConversionStatus();
		final ConversionGroupModel conversionGroup = container.getConversionGroup();

		// alternative way to identify non-mediaconversion containers
		if ((status == null || status == ConversionStatus.EMPTY) && conversionGroup == null)
		{
			return getMediaService().getMediaByFormat(container, format);
		}

		return super.getOrConvert(container, format);
	}
}
