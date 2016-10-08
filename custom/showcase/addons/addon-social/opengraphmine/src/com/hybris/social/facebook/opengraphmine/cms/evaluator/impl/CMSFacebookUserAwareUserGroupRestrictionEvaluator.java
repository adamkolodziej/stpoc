/**
 * 
 */
package com.hybris.social.facebook.opengraphmine.cms.evaluator.impl;

/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2011 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */

import de.hybris.platform.cms2.model.restrictions.CMSUserGroupRestrictionModel;
import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.service.FacebookAuthenticationService;


/**
 * Replaces the Default UeerGroupRestrictionEvaluator with one that also checks any current facebook user groups also.
 */
public class CMSFacebookUserAwareUserGroupRestrictionEvaluator implements CMSRestrictionEvaluator<CMSUserGroupRestrictionModel>
{
	private final static Logger LOG = Logger.getLogger(CMSFacebookUserAwareUserGroupRestrictionEvaluator.class);

	private UserService userService;
	private FacebookAuthenticationService facebookAuthenticationService;

	@Override
	public boolean evaluate(final CMSUserGroupRestrictionModel cmsUserGroupRestriction, final RestrictionData context)
	{
		final Collection<UserGroupModel> groups = cmsUserGroupRestriction.getUserGroups();
		final UserModel currentUserModel = getUserService().getCurrentUser();
		final Set<PrincipalGroupModel> userGroups = new HashSet<PrincipalGroupModel>(currentUserModel.getGroups());

		// ADD Facebook User Groups Also
		final FacebookUserModel currentFacebookUser = getFacebookAuthenticationService().getCurrentFacebookUser();
		if (currentFacebookUser != null)
		{
			userGroups.addAll(currentFacebookUser.getGroups());
		}

		if (cmsUserGroupRestriction.isIncludeSubgroups())
		{
			userGroups.addAll(getSubgroups(userGroups));
		}

		final List<String> restrGroupNames = new ArrayList<String>();
		for (final UserGroupModel group : groups)
		{
			restrGroupNames.add(group.getUid());
		}

		final List<String> currentGroupNames = new ArrayList<String>();
		for (final PrincipalGroupModel group : userGroups)
		{
			currentGroupNames.add(group.getUid());
		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("Current UserGroups: " + StringUtils.join(currentGroupNames, "; "));
			LOG.debug("Restricted UserGroups: " + StringUtils.join(restrGroupNames, "; "));
		}

		for (final String group : restrGroupNames)
		{
			if (currentGroupNames.contains(group))
			{
				return true;
			}
		}
		return false;
	}

	protected List<PrincipalGroupModel> getSubgroups(final Collection<PrincipalGroupModel> groups)
	{
		final List<PrincipalGroupModel> ret = new ArrayList<PrincipalGroupModel>(groups);

		for (final PrincipalGroupModel principalGroup : groups)
		{
			ret.addAll(getSubgroups(principalGroup.getGroups()));
		}
		return ret;
	}

	public UserService getUserService()
	{
		return this.userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @param facebookAuthenticationService
	 *           the facebookAuthenticationService to set
	 */
	@Required
	public void setFacebookAuthenticationService(final FacebookAuthenticationService facebookAuthenticationService)
	{
		this.facebookAuthenticationService = facebookAuthenticationService;
	}

	/**
	 * @return the facebookAuthenticationService
	 */
	public FacebookAuthenticationService getFacebookAuthenticationService()
	{
		return facebookAuthenticationService;
	}

}
