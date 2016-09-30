package com.hybris.showcase.cecs.mobiletricast.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class MobileEntitlementAuthorizationException extends RuntimeException
{

	private static final long serialVersionUID = 5981985978875748057L;

	public MobileEntitlementAuthorizationException(final String message)
	{
		super(message);
	}
}
