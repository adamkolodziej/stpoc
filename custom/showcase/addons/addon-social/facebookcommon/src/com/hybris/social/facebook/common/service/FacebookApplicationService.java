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
package com.hybris.social.facebook.common.service;

import com.hybris.social.facebook.common.model.FacebookApplicationModel;



/**
 * @author rmcotton
 * 
 */
public interface FacebookApplicationService
{

	/**
	 * Init the facetbook application in the session from the given server name
	 */
	FacebookApplicationModel initApplication(String serverName);

	/**
	 * Get the current Facebook Application configured on the session
	 */
	FacebookApplicationModel getCurrentApplication();
}
