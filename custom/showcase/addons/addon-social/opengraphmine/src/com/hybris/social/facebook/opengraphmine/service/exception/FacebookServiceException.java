/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 * 
 */
package com.hybris.social.facebook.opengraphmine.service.exception;

/**
 * @author sarju.ladwa
 * 
 */
public class FacebookServiceException extends Exception
{
	public FacebookServiceException(final String message)
	{
		super(message);
	}

	public FacebookServiceException(final Throwable cause)
	{
		super(cause);
	}

	public FacebookServiceException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
