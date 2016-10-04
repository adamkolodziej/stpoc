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
package com.hybris.social.facebook.opengraphmine.service.dao.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.service.dao.FacebookUserDao;


/**
 * @author rmcotton
 * 
 */
public class DefaultFacebookUserDao extends AbstractItemDao implements FacebookUserDao
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.facebook.service.dao.FacebookUserDao#findFacebookUserById(java.lang.String)
	 */
	@Override
	public FacebookUserModel findFacebookUserById(final String id) throws ModelNotFoundException
	{
		final FacebookUserModel example = new FacebookUserModel();
		example.setUid(id);
		return getFlexibleSearchService().getModelByExample(example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.social.facebook.service.dao.FacebookUserDao#findFacebookUsersLinkedToCustomer(de.hybris.platform
	 * .core.model.user.CustomerModel)
	 */
	@Override
	public Set<FacebookUserModel> findFacebookUsersLinkedToCustomer(final CustomerModel customer)
	{
		final FacebookUserModel example = new FacebookUserModel();
		example.setLinkedCustomer(customer);
		try
		{
			return new LinkedHashSet<FacebookUserModel>(getFlexibleSearchService().getModelsByExample(example));
		}
		catch (final ModelNotFoundException mnfe)
		{
			return Collections.emptySet();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.social.facebook.service.dao.FacebookUserDao#getFriendIds(de.hybris.platform.social.model.facebook
	 * .FacebookUserModel)
	 */
	@Override
	public List<String> getFriendIds(final FacebookUserModel facebookUser)
	{
		final String query = "select {user.uid} from {FacebookFriends as friend}, {FacebookUser as user} where {user.PK} = {friend.target} and {friend.source} = ?me ";
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(query);
		fsq.addQueryParameter("me", facebookUser);
		fsq.setResultClassList(Collections.singletonList(String.class));
		final SearchResult<String> result = getFlexibleSearchService().search(fsq);
		return result.getResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.social.facebook.service.dao.FacebookUserDao#getFriendFacebookUsers(de.hybris.platform.social
	 * .model.facebook.FacebookUserModel)
	 */
	@Override
	public List<FacebookUserModel> getFriendFacebookUsers(final FacebookUserModel facebookUser)
	{
		final String query = "select {user.PK} from {FacebookFriends as friend}, {FacebookUser as user} where {user.PK} = {friend.target} and {friend.source} = ?me ";
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(query);
		fsq.addQueryParameter("me", facebookUser);
		final SearchResult<FacebookUserModel> result = getFlexibleSearchService().search(fsq);
		return result.getResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.facebook.service.dao.FacebookUserDao#findFacebookUsersById(java.util.List)
	 */
	@Override
	public List<FacebookUserModel> findFacebookUsersById(final List<String> ids)
	{
		final String query = "select {PK} from {FacebookUser as user} where {user.uid} in (?users) ";
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(query);
		fsq.addQueryParameter("users", ids);
		final SearchResult<FacebookUserModel> result = getFlexibleSearchService().search(fsq);
		return result.getResult();
	}


}
