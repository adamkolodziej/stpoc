package com.hybris.showcase.product.populators;

import de.hybris.platform.commercefacades.product.converters.populator.AbstractProductPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.hybris.showcase.cecs.servicesshowcase.model.ChannelProductModel;


/**
 * Created by miroslaw.szot@sap.com on 2016-04-18.
 */
public class TVChannelSchedulePopulator<SOURCE extends ProductModel, TARGET extends ProductData>
		extends AbstractProductPopulator<SOURCE, TARGET>
{
	@Override
	public void populate(final SOURCE source, final TARGET target) throws ConversionException
	{
		if (source instanceof ChannelProductModel)
		{
			final String schedule = ((ChannelProductModel) source).getSchedule();
			target.setChannelSchedule(schedule);
		}
	}
}