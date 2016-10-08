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
import com.restfb.types.Page;
import com.restfb.types.User;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.social.InsufficientPermissionException;

import com.hybris.social.facebook.opengraphmine.model.FacebookPageModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.service.dao.FacebookReferenceDao;


/**
 * This populator merges all of the users likes and creates reference for any links we are not yet aware of.
 * 
 * @author rmcotton
 */
public class FacebookLikesPopulator implements Populator<FacebookClient, FacebookUserModel>
{
	private static final Logger LOG = Logger.getLogger(FacebookLikesPopulator.class);

	private FacebookReferenceDao referenceDao;
	private ModelService modelService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commerceservices.converter.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final FacebookClient source, final FacebookUserModel target) throws ConversionException
	{
		final Map<String, FacebookPageModel> currentLikes = getLikeMap(target);
		final Set<FacebookPageModel> newLikes = new HashSet<FacebookPageModel>();

		try
		{
            final com.restfb.Connection<Page> likes =
                    source.fetchConnection("me/likes",
                            Page.class);

            if (likes.getData().isEmpty())
			{
				target.setLikes(Collections.EMPTY_SET);
			}
			else
			{
				for (final Page like : likes.getData())
				{
					final FacebookPageModel page = getOrCreatePage(currentLikes, like);
					populateFacebookPageStub(like, page);
					getModelService().save(page);
					newLikes.add(page);
				}

			}

			// update user living city and home city

			final Set<FacebookPageModel> locationLikes = updateUserLivingLocations(source, target, currentLikes);


			if (locationLikes != null)
			{
				newLikes.addAll(locationLikes);
			}
			target.setLikes(newLikes);
		}
		catch (final InsufficientPermissionException ispe)
		{
			if (!currentLikes.isEmpty())
			{
				target.setLikes(Collections.EMPTY_SET);
			}
		}
	}

	private Set<FacebookPageModel> updateUserLivingLocations(final FacebookClient faceBookTemplate,
			final FacebookUserModel userModel, final Map<String, FacebookPageModel> currentLikes)
	{
		final User fbProfile = faceBookTemplate.fetchObject("me", User.class);
		final Set<FacebookPageModel> locationLikes = new HashSet<FacebookPageModel>();
		// update hometown
		if (fbProfile.getHometown() != null)
		{

			userModel.setHomeTown(fbProfile.getHometownName());
		}
		else
		{
			userModel.setHomeTown(null);
		}

		// update Locaiton
		if (fbProfile.getLocation() != null)
		{
			userModel.setCurrentCity(fbProfile.getLocation().getName());
		}
		else
		{
			userModel.setCurrentCity(null);
		}
		return locationLikes;
	}

	protected FacebookPageModel getOrCreatePage(final Map<String, FacebookPageModel> currentLikesCache, final Page page)
	{
		if (currentLikesCache.containsKey(page.getId()))
		{
			return currentLikesCache.get(page.getId());
		}
		try
		{
			return getReferenceDao().getPageById(page.getId());
		}
		catch (final ModelNotFoundException e)
		{
			final FacebookPageModel facebookPage = getModelService().create(FacebookPageModel.class);
			facebookPage.setId(page.getId());
			return facebookPage;
		}
	}

	protected void populateFacebookPageStub(final Page source, final FacebookPageModel target)
	{
		target.setName(source.getName());
		if (StringUtils.isNotBlank(source.getCategory()))
		{
			target.setCategory(source.getCategory());
		}
	}

	protected Map<String, FacebookPageModel> getLikeMap(final FacebookUserModel user)
	{
		final Map<String, FacebookPageModel> map = new HashMap<String, FacebookPageModel>();
		for (final FacebookPageModel like : user.getLikes())
		{
			map.put(like.getId(), like);
		}
		return map;
	}

	/**
	 * @param referenceDao
	 *           the referenceDao to set
	 */
	@Required
	public void setReferenceDao(final FacebookReferenceDao referenceDao)
	{
		this.referenceDao = referenceDao;
	}

	/**
	 * @return the referenceDao
	 */
	public FacebookReferenceDao getReferenceDao()
	{
		return referenceDao;
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
