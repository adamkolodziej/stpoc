/**
 * 
 */
package com.hybris.showcase.emsextras.populators.entitlements.grants;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.entitlementservices.data.EmsGrantData;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/**
 * @author npavlovic
 * 
 */
public class ServicesProductEntitlementEmsGrantPopulator<SOURCE extends ProductEntitlementModel, TARGET extends EmsGrantData>
		implements Populator<SOURCE, TARGET>
{
	@Override
	public void populate(final SOURCE source, final TARGET target) throws ConversionException
	{
		target.setProductEntitlementId(source.getId());
	}
}
