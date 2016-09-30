/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *  
 */
package com.hybris.showcase.instantcheckout.constants;

import com.hybris.showcase.instantcheckout.model.InstantCheckoutComponentModel;


/**
 * Global class for all Instantcheckout constants. You can add global constants for your extension into this class.
 */
public final class InstantcheckoutConstants extends GeneratedInstantcheckoutConstants
{
	public static final String EXTENSIONNAME = "instantcheckout";

	private InstantcheckoutConstants()
	{
		//empty to avoid instantiating this constant class
	}

	// implement here constants used by this extension
	public static final String _Prefix = "/view/";
	public static final String _Suffix = "Controller";
	public static final String InstantCheckoutComponentController = _Prefix + InstantCheckoutComponentModel._TYPECODE + _Suffix;
}
