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
package com.hybris.social.facebook.opengraphmine.process.event.dao;

import de.hybris.platform.processengine.enums.ProcessState;

import java.util.List;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.model.process.FacebookSynchronizationProcessModel;


/**
 * @author rmcotton
 * 
 */
public interface FacebookSynchronizationProcessDao
{
	List<FacebookSynchronizationProcessModel> getFacebookSynchronizationProcessesForUser(FacebookUserModel user,
			ProcessState processstate);
}
