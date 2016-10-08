/**
 * 
 */
package com.hybris.productsets.converters.populator;

import de.hybris.platform.commercefacades.converter.ConfigurablePopulator;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.util.Assert;

import com.hybris.productsets.facades.data.ProductSetData;
import com.hybris.productsets.model.ProductSetModel;


/**
 * @author dariusz.malachowski
 * 
 */
public class DefaultProductSetPropertiesPopulator implements
		ConfigurablePopulator<ProductSetModel, ProductSetData, ProductOption>
{

	private Converter<MediaModel, ImageData> imageConverter;

	@Override
	public void populate(final ProductSetModel source, final ProductSetData target, final Collection<ProductOption> options)
			throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setCode(source.getCode());
		target.setName(source.getName());
		target.setTitle(source.getTitle());
		target.setApprovalStatus(source.getApprovalStatus());
		target.setProductSetType(source.getProductSetType());
		final MediaContainerModel medias = source.getMedias();
		final List<ImageData> images = new ArrayList<>();
		if (medias != null)
		{
			for (final MediaModel mediaModel : medias.getMedias())
			{
				images.add(imageConverter.convert(mediaModel));
			}
		}
		target.setMediaContainer(images);
	}


	/**
	 * @param imageConverter
	 *           the imageConverter to set
	 */
	public void setImageConverter(final Converter<MediaModel, ImageData> imageConverter)
	{
		this.imageConverter = imageConverter;
	}

}
