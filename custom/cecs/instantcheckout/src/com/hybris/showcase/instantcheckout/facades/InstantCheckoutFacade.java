/**
 * 
 */
package com.hybris.showcase.instantcheckout.facades;

import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;


/**
 * @author mgolubovic
 * 
 */
public interface InstantCheckoutFacade
{
	public CartModificationData purchase(final String productCode, final Boolean loyaltyPayment) throws CommerceCartModificationException;
}
