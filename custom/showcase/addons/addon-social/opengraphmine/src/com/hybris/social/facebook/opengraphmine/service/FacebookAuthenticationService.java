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
package com.hybris.social.facebook.opengraphmine.service;

import com.restfb.FacebookClient;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.Set;


import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.service.exception.FacebookServiceException;

import static com.restfb.FacebookClient.*;


/**
 * @author richard cotton
 * 
 */
public interface FacebookAuthenticationService
{

	void logout();


	/**
	 * Returns the current facebook user associated with the session. The facebook user is totally independent of the
	 * session user, therefore you can be logged into facebook but not logged into the site (hence still anonymous).
	 */
	FacebookUserModel getCurrentFacebookUser();

	FacebookUserModel getOrCreateFacebookUserForConnection(final FacebookClient client);




	/**
	 * Returns a customer that has been auto-logged in based on a login from the facebook user account.
	 * 
	 **/
	UserModel getAutoLoginUser();

	void setAutoLoginUser(UserModel user);

	/**
	 * A users account can have multiple facebook users, nothing prevents this. This will return all facebook users
	 * associated with the current logged in user including the current facebook user. If the current user is anonymous
	 * this will return only the current facebook user.
	 */
	Set<FacebookUserModel> getAllAssociatedFacebookUsers();

	/**
	 * This may appear to be odd, but there is in fact nothing preventing the circumstance of a single customers profile
	 * to be linked to multiple facebook accounts.
	 */
	Set<FacebookUserModel> getLinkedFacebookUsersForCustomer(CustomerModel customer);

	/**
	 * Links the current facebook user with the current user. If the current user is anonymous this will do nothing, if
	 * the current facebook user is null this will do nothing. This returns true if it actually links the 2 users. This
	 * will also trigger an asynchronous resync of the facebook account and transfer any segment or user group details to
	 * the current user.
	 */
	boolean linkCurrentFacebookUserWithCurrentUser();


	/**
	 * @param connection
	 */
	void setCurrentConnection(FacebookClient  connection);

	/**
	 * @return
	 */
    FacebookClient getCurrentConnection();

	/**
	 * @param connection
	 * @throws FacebookServiceException
	 */
	void authenticateHybrisUser(FacebookClient  connection,AccessToken token) throws FacebookServiceException;

}
