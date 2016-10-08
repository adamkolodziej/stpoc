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

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.internal.dao.Dao;

import java.util.List;
import java.util.Set;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;


/**
 * @author rmcotton
 * 
 */
public interface FacebookUserDao extends Dao
{
	FacebookUserModel findFacebookUserById(String id) throws ModelNotFoundException;

	Set<FacebookUserModel> findFacebookUsersLinkedToCustomer(CustomerModel customer);

	List<String> getFriendIds(FacebookUserModel facebookUser);

	List<FacebookUserModel> getFriendFacebookUsers(FacebookUserModel facebookUser);

	List<FacebookUserModel> findFacebookUsersById(List<String> ids);
}
