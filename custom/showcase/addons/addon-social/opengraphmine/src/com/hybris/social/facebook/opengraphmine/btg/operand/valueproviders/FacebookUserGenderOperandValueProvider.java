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
package com.hybris.social.facebook.opengraphmine.btg.operand.valueproviders;

import de.hybris.platform.btg.condition.operand.OperandValueProvider;
import de.hybris.platform.btg.enums.BTGConditionEvaluationScope;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.user.UserModel;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.facebook.opengraphmine.model.BTGFacebookGenderOperandModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.service.FacebookAuthenticationService;


/**
 *
 */
public class FacebookUserGenderOperandValueProvider implements OperandValueProvider<BTGFacebookGenderOperandModel>
{
	private FacebookAuthenticationService facebookAuthenticationService;


	@Override
	public Object getValue(final BTGFacebookGenderOperandModel operand, final UserModel user,
			final BTGConditionEvaluationScope evaluationScope)
	{
		final FacebookUserModel currentUser = getFacebookAuthenticationService().getCurrentFacebookUser();
		if (currentUser != null)
		{
			return currentUser.getGender();
		}
		return null;
	}

	@Override
	public Class getValueType(final BTGFacebookGenderOperandModel operand)
	{
		return Gender.class;
	}


	@Required
	public void setFacebookAuthenticationService(final FacebookAuthenticationService facebookAuthenticationService)
	{
		this.facebookAuthenticationService = facebookAuthenticationService;
	}

	public FacebookAuthenticationService getFacebookAuthenticationService()
	{
		return this.facebookAuthenticationService;
	}
}
