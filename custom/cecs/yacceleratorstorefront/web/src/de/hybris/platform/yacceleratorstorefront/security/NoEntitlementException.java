package de.hybris.platform.yacceleratorstorefront.security;

import org.springframework.security.core.AuthenticationException;


/**
 * Created by i303841 on 2/3/15.
 */
//CECS-76 check for MobileAccess entitlement after login
public class NoEntitlementException extends AuthenticationException
{

	public NoEntitlementException(final String message)
	{
		super(message);
	}

}
