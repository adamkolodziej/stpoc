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
package com.hybris.social.facebook.opengraphmine.process.actions;

import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.task.RetryLaterException;

import org.apache.log4j.Logger;

import com.hybris.social.facebook.opengraphmine.model.FacebookConnectionModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.model.process.FacebookSynchronizationProcessModel;


/**
 * This action verifies the connection is still valid and that it links to the facebook customer associated with the
 * assigned business process.
 * 
 * @author rmcotton
 * @author fbieg
 * 
 */
public class ValidateConnectionAction extends AbstractSimpleDecisionAction
{

	private static final Logger LOG = Logger.getLogger(ValidateConnectionAction.class);

	@Override
	public Transition executeAction(final BusinessProcessModel arg0) throws RetryLaterException, Exception
	{
		final FacebookSynchronizationProcessModel model = (FacebookSynchronizationProcessModel) arg0;
		final FacebookUserModel processUser = model.getUser();
		if (processUser == null)
		{
			LOG.error("validation failed due to missing user");
			return Transition.NOK;
		}

		LOG.debug("validation successful for sync request for facebook user [" + processUser.getUid() + "]  name ["
				+ processUser.getName() + "]");
		return Transition.OK;
	}
}
