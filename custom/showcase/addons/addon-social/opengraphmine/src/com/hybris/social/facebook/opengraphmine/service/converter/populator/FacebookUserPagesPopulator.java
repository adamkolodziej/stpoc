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
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.facebook.opengraphmine.model.FacebookPageModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;


/**
 * Refreshes all pages linked to this user
 * 
 * @author rmcotton
 * 
 */
public class FacebookUserPagesPopulator implements Populator<FacebookClient, FacebookUserModel>
{
	private ModelService modelService;
	private SessionService sessionService;
	private UserService userService;
	private Populator<FacebookClient, FacebookPageModel> facebookPagePopulator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commerceservices.converter.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final FacebookClient source, final FacebookUserModel target) throws ConversionException
	{
		for (final FacebookPageModel like : target.getLikes())
		{
			getFacebookPagePopulator().populate(source, like);

			getSessionService().executeInLocalView(new SessionExecutionBody()
			{
				/**
				 * you can override this method if you do not need a result
				 */
				@Override
				public void executeWithoutResult()
				{
					getUserService().setCurrentUser(getUserService().getAdminUser());
					getModelService().save(like);
				}
			});
		}
	}

	/**
	 * @param facebookPagePopulator
	 *           the facebookPagePopulator to set
	 */
	@Required
	public void setFacebookPagePopulator(final Populator<FacebookClient, FacebookPageModel> facebookPagePopulator)
	{
		this.facebookPagePopulator = facebookPagePopulator;
	}

	/**
	 * @return the facebookPagePopulator
	 */
	public Populator<FacebookClient, FacebookPageModel> getFacebookPagePopulator()
	{
		return facebookPagePopulator;
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

	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

}
