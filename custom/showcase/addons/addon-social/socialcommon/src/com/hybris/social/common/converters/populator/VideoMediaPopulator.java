/**
 * 
 */
package com.hybris.social.common.converters.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.springframework.util.Assert;

import com.hybris.social.common.data.VideoMediaData;


/**
 * @author mgolubovic
 * 
 */
public class VideoMediaPopulator implements Populator<MediaModel, VideoMediaData>
{
	@Override
	public void populate(final MediaModel source, final VideoMediaData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setMediaType(source.getMime());
		target.setUrl(source.getDownloadURL());
	}
}
