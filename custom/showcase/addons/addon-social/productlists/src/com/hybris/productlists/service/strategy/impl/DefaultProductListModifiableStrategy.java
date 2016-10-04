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
package com.hybris.productlists.service.strategy.impl;

import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.servicelayer.user.UserService;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.productlists.model.PrincipalListOwnerModel;
import com.hybris.productlists.model.ProductListModel;
import com.hybris.productlists.model.ProductListOwnerModel;
import com.hybris.productlists.service.strategy.ProductListModifiableStrategy;


/**
 * @author rmcotton
 * 
 */
public class DefaultProductListModifiableStrategy implements ProductListModifiableStrategy
{

	private UserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.service.strategy.ProductListModifiableStrategy#canModify(com.hybris.productlists.model
	 * .ProductListModel, de.hybris.platform.core.model.security.PrincipalModel)
	 */
	@Override
	public boolean canModify(final ProductListModel productList, final PrincipalModel principal)
	{
		if (productList.getListOwners().isEmpty())
		{
			return handleAnonymousListDecision(productList, principal);
		}
		else
		{
			for (final ProductListOwnerModel listOwner : productList.getListOwners())
			{
				if (listOwner instanceof PrincipalListOwnerModel)
				{
					if (principal.equals(((PrincipalListOwnerModel) listOwner).getPrincipal()))
					{
						return true;
					}
				}
			}
		}

		return false;
	}


	/**
	 * @param productList
	 * @param principal
	 */
	protected boolean handleAnonymousListDecision(final ProductListModel productList, final PrincipalModel principal)
	{
		// totally anonymous list so it can be modified
		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.service.strategy.ProductListModifiableStrategy#canModify(com.hybris.productlists.model
	 * .ProductListModel)
	 */
	@Override
	public boolean canModify(final ProductListModel productList)
	{
		return canModify(productList, getUserService().getCurrentUser());
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

}
