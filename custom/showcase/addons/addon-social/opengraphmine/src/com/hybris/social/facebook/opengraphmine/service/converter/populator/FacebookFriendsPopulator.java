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
package com.hybris.social.facebook.opengraphmine.service.converter.populator;

import com.restfb.FacebookClient;
import com.restfb.types.User;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.social.NotAuthorizedException;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.service.dao.FacebookUserDao;


/**
 * @author rmcotton
 * 
 */
public class FacebookFriendsPopulator implements Populator<FacebookClient, FacebookUserModel>
{
	private static final Logger LOG = Logger.getLogger(FacebookFriendsPopulator.class);
	private FacebookUserDao facebookUserDao;
	private ModelService modelService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commerceservices.converter.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final FacebookClient source, final FacebookUserModel target) throws ConversionException
	{
		try
		{   com.restfb.Connection<User> myFriends = source.fetchConnection("me/friends", User.class);
			final List<String> unlinkedFriendIds = filterExistingFriendIds(myFriends.getData(), target);
			if (CollectionUtils.isNotEmpty(unlinkedFriendIds))
			{
				final List<FacebookUserModel> unlinkedKnownFriends = getFacebookUserDao().findFacebookUsersById(unlinkedFriendIds);
				if (CollectionUtils.isNotEmpty(unlinkedKnownFriends))
				{
					final Set<FacebookUserModel> friends = new LinkedHashSet<FacebookUserModel>(target.getFriends());
					friends.addAll(unlinkedKnownFriends);
					target.setFriends(friends);

					for (final FacebookUserModel newFriend : unlinkedKnownFriends)
					{
						final Set<FacebookUserModel> newFriendsFriends = new LinkedHashSet<FacebookUserModel>(newFriend.getFriends());
						newFriendsFriends.add(target);
						newFriend.setFriends(newFriendsFriends);
					}
				}

				final List<FacebookUserModel> usersToSave = new ArrayList<FacebookUserModel>(unlinkedKnownFriends);
				usersToSave.add(target);
				getModelService().saveAll(usersToSave);

			}
		}
		catch (final NotAuthorizedException e)
		{
			LOG.warn("not authorised to sync friends for [ " + target.getUid() + "|" + target.getName() + "] message ["
					+ e.getMessage() + "]");
		}
	}

	protected List<String> filterExistingFriendIds(final List<User> friends, final FacebookUserModel target)
	{
        List<String> friendIds = new ArrayList<String>();
        for(User friend : friends){
            friendIds.add(friend.getId());
        }
		if (CollectionUtils.isEmpty(friendIds))
		{
			return friendIds;
		}
		final List<String> currentFriendIds = new LinkedList<String>(getFacebookUserDao().getFriendIds(target));
		final List<String> filteredFriendIds = new ArrayList<String>(friendIds);
		filteredFriendIds.removeAll(currentFriendIds);
		return filteredFriendIds;
	}

	/**
	 * @param facebookUserDao
	 *           the facebookUserDao to set
	 */
	@Required
	public void setFacebookUserDao(final FacebookUserDao facebookUserDao)
	{
		this.facebookUserDao = facebookUserDao;
	}

	/**
	 * @return the facebookUserDao
	 */
	public FacebookUserDao getFacebookUserDao()
	{
		return facebookUserDao;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}
}
