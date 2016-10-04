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
package com.hybris.productlists.service.strategy;

import de.hybris.platform.core.model.security.PrincipalModel;

import com.hybris.productlists.model.ProductListModel;


/**
 * @author rmcotton
 * 
 */
public interface ProductListModifiableStrategy
{
	boolean canModify(ProductListModel productList, PrincipalModel principal);

	boolean canModify(ProductListModel productList);

}
