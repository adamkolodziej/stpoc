package com.hybris.showcase.guidedselling.order.exceptions;

public class PlaceOrderException extends RuntimeException
{

	private static final long serialVersionUID = -4744009291197996314L;

	public PlaceOrderException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
