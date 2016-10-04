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
import de.hybris.platform.core.model.user.UserModel;

import java.util.Date;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.facebook.opengraphmine.model.BTGFacebookBirthdayOperandModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.service.FacebookAuthenticationService;


/**
 * @author rmcotton
 * 
 */
public class FacebookUserBirthdayOperandValueProvider implements OperandValueProvider<BTGFacebookBirthdayOperandModel>
{
	private FacebookAuthenticationService facebookAuthenticationService;


	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.btg.condition.operand.OperandValueProvider#getValueType(de.hybris.platform.btg.model.
	 * BTGOperandModel)
	 */
	@Override
	public Class getValueType(final BTGFacebookBirthdayOperandModel operand)
	{
		return Date.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.btg.condition.operand.OperandValueProvider#getValue(de.hybris.platform.btg.model.BTGOperandModel
	 * , de.hybris.platform.core.model.user.UserModel, de.hybris.platform.btg.enums.BTGConditionEvaluationScope)
	 */
	@Override
	public Object getValue(final BTGFacebookBirthdayOperandModel operand, final UserModel user,
			final BTGConditionEvaluationScope evaluationScope)
	{
		final FacebookUserModel currentUser = getFacebookAuthenticationService().getCurrentFacebookUser();
		if (currentUser != null)
		{
			return currentUser.getBirthday();
		}
		return null;
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