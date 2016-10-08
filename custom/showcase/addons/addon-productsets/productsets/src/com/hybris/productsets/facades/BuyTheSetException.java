/**
 * 
 */
package com.hybris.productsets.facades;

import de.hybris.platform.commercefacades.order.data.CartModificationData;


/**
 * @author dominik.strzyzewski
 * 
 */
public class BuyTheSetException extends RuntimeException
{

	private CartModificationData cartModificationData;

	public BuyTheSetException(final CartModificationData cartModificationData)
	{
		super();
		this.cartModificationData = cartModificationData;
	}

	public BuyTheSetException(final Throwable cause)
	{
		super(cause);
	}

	public BuyTheSetException(final String message)
	{
		super(message);
	}

	public BuyTheSetException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	public CartModificationData getCartModificationData()
	{
		return cartModificationData;
	}

}
