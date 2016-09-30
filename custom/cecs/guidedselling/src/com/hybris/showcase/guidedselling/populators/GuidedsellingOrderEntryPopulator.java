package com.hybris.showcase.guidedselling.populators;

import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/**
 * Created by miroslaw.szot@sap.com on 2015-03-27.
 */
public class GuidedsellingOrderEntryPopulator implements Populator<AbstractOrderEntryModel, OrderEntryData>
{

	@Override
	public void populate(final AbstractOrderEntryModel source, final OrderEntryData target) throws ConversionException
	{
		target.setAddedToExistingContract(source.isAddedToExistingContract());
	}

}

