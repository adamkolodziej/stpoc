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
package com.hybris.social.facebook.opengraphmine.service.dao;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.Map;
import java.util.Set;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;


/**
 * @author rmcotton
 * 
 */
public interface FacebookSuggestionDao
{

	/**
	 * This method receives a list of facebook users and returns a map with products and the set of those facebook passed
	 * by parameter related to them
	 * 
	 * @param facebookUsers
	 *           The users from which the products need to be retrieved
	 * @return A map of productmodel and the set of users linked to them
	 */
	Map<ProductModel, Set<FacebookUserModel>> getLikedProducts(Set<FacebookUserModel> facebookUsers);

	Set<FacebookUserModel> getThoseThatLikeProducts(Set<FacebookUserModel> facebookUsers, Set<ProductModel> products);
}
