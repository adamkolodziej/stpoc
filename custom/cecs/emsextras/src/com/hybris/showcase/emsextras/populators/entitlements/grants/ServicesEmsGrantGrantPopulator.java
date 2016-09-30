/**
 *
 */
package com.hybris.showcase.emsextras.populators.entitlements.grants;

import de.hybris.platform.catalog.FlexibleSearchServicePaginationTest;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.entitlementservices.data.EmsGrantData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.hybris.services.entitlements.api.GrantData;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.apache.commons.lang.StringUtils;


/**
 * @author npavlovic
 *
 */
public class ServicesEmsGrantGrantPopulator<SOURCE extends EmsGrantData, TARGET extends GrantData> implements
		Populator<SOURCE, TARGET>
{
	@Override
	public void populate(final SOURCE source, final TARGET target) throws ConversionException
	{
		if( source.getProductEntitlementId() != null ) {
			target.setGrantSource(source.getProductEntitlementId());
		}
		if( StringUtils.isBlank(target.getGrantSourceId()) ) {
			target.setGrantSourceId(source.getEntitlementType());
		}
	}
}
