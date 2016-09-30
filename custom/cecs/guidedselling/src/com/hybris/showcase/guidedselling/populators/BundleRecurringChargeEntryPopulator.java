/**
 * 
 */
package com.hybris.showcase.guidedselling.populators;

import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.subscriptionfacades.data.RecurringChargeEntryData;
import de.hybris.platform.subscriptionfacades.product.converters.populator.AbstractChargeEntryPopulator;
import de.hybris.platform.subscriptionservices.model.RecurringChargeEntryModel;


/**
 * @author I307113
 * 
 */
public class BundleRecurringChargeEntryPopulator<SOURCE extends RecurringChargeEntryModel, TARGET extends RecurringChargeEntryData>
		extends AbstractChargeEntryPopulator<SOURCE, TARGET>
{
	@Override
	public void populate(final SOURCE source, final TARGET target) throws ConversionException
	{
		target.setLabel(source.getLabel());
	}
}
