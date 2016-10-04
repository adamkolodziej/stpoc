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
package com.hybris.social.urlshortening.service.provider;

/**
 * Thrown when a provider is mis-configured.
 * 
 * @author rmcotton
 * 
 */
public class UrlShorteningProviderConfigurationException extends UrlShorteningProviderException
{

	/**
	 * @param message
	 */
	public UrlShorteningProviderConfigurationException(final String message)
	{
		super(message);
	}

}
