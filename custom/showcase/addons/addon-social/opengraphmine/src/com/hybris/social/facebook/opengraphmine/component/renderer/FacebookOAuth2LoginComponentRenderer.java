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
package com.hybris.social.facebook.opengraphmine.component.renderer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;


import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.facebook.opengraphmine.model.FacebookOAuth2LoginComponentModel;
import com.hybris.social.facebook.opengraphmine.service.FacebookAuthenticationService;
import com.hybris.social.facebook.socialplugins.component.renderer.AbstractFacebookComponentRenderer;



/**
 * 
 * 
 * @author rmcotton
 * 
 */
public class FacebookOAuth2LoginComponentRenderer<C extends FacebookOAuth2LoginComponentModel> extends
		AbstractFacebookComponentRenderer<C>
{
	private FacebookAuthenticationService facebookAuthenticationService;

	public String getRequestedPermissionsString(final C loginComponent)
	{
		final String userPermissionString = getEnumCodeString(loginComponent.getUserPermissions());
		final String friendsPermissionsString = getEnumCodeString(loginComponent.getFriendsPermissions());
		final String extendedPermissionsString = getEnumCodeString(loginComponent.getExtendedPermissions());
		final String pagePermissionString = getEnumCodeString(loginComponent.getPagePermissions());

		final StringBuilder sb = new StringBuilder();
		boolean appendComma = false;
		if (StringUtils.isNotBlank(userPermissionString))
		{
			sb.append(userPermissionString);
			appendComma = true;
		}
		if (StringUtils.isNotBlank(friendsPermissionsString))
		{
			if (appendComma)
			{
				sb.append(",");
			}
			sb.append(friendsPermissionsString);
			appendComma = true;
		}
		if (StringUtils.isNotBlank(extendedPermissionsString))
		{
			if (appendComma)
			{
				sb.append(",");
			}
			sb.append(extendedPermissionsString);
			appendComma = true;
		}
		if (StringUtils.isNotBlank(pagePermissionString))
		{
			if (appendComma)
			{
				sb.append(",");
			}
			sb.append(pagePermissionString);
			appendComma = true;
		}
		return sb.toString();
	}



	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
	{
		final Map<String, Object> variables = new HashMap<String, Object>();

		final FacebookUserModel user= facebookAuthenticationService.getCurrentFacebookUser();

		if (user != null)
		{
			variables.put("facebookUser", user.getFirstname());
			variables.put("facebookUserImage",user.getProfilePictureUrl());
		}
		variables.put(FacebookOAuth2LoginComponentModel.LABELLOGIN, component.getLabelLogin());
		variables.put(FacebookOAuth2LoginComponentModel.LABELLOGOUT, component.getLabelLogout());
		variables.put("scope", getRequestedPermissionsString(component));
		variables.put(FacebookOAuth2LoginComponentModel.SIZE, component.getSize().getCode());
		return variables;
	}



	public FacebookAuthenticationService getFacebookAuthenticationService()
	{
		return facebookAuthenticationService;
	}


	@Required
	public void setFacebookAuthenticationService(final FacebookAuthenticationService facebookAuthenticationService)
	{
		this.facebookAuthenticationService = facebookAuthenticationService;
	}

}