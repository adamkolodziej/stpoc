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
package com.hybris.productlists.constants;



/**
 * Global class for all Productlists constants. You can add global constants for your extension into this class.
 */
public final class ProductlistsConstants extends GeneratedProductlistsConstants
{
	public static final String EXTENSIONNAME = "productlists";

	public interface Views
	{

		interface Pages
		{

			interface ProductLists
			{

				//These values match the impex for content pages
				public static final String create = "createProductListPage";
				public static final String view = "viewProductListPage";
				public static final String list = "listProductListPage";
				public static final String edit = "editProductListPage";
				public static final String find = "findProductListPage";

			}

		}
	}

	private ProductlistsConstants()
	{
		//empty to avoid instantiating this constant class
	}

	// implement here constants used by this extension
}
