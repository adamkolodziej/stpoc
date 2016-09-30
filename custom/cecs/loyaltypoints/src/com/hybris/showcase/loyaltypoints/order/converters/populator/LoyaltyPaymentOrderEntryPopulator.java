/**
 * 
 */
package com.hybris.showcase.loyaltypoints.order.converters.populator;

import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/**
 * @author npavlovic
 * 
 *         CECS-271: Billings component is hardcoded
 */
public class LoyaltyPaymentOrderEntryPopulator implements Populator<AbstractOrderEntryModel, OrderEntryData>
{
	@Override
	public void populate(final AbstractOrderEntryModel source, final OrderEntryData target) throws ConversionException
	{
		target.setLoyaltyPayment(source.isLoyaltyPayment());
	}
}
