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
package com.hybris.social.facebook.common.service.dao;

import java.util.List;

import com.hybris.social.facebook.common.model.FacebookApplicationDomainModel;
import com.hybris.social.facebook.common.model.FacebookApplicationModel;


/**
 * @author rmcotton
 * 
 */
public interface FacebookApplicationDao
{
	FacebookApplicationDomainModel findDomain(final String domain);

	FacebookApplicationModel findApplication(final Long appId);

	FacebookApplicationModel findDefaultApplicationForDomain(final String domain);

	/**
	 * Returns the first application that matches the sub domain list. For example if we had the following 3 subdomains
	 * 
	 * apparel.co.uk, co.uk, uk
	 * 
	 * and we didnt have an entry for apparel.co.uk but one for co.uk, and one for uk, then the application for co.uk
	 * will be returned.
	 * 
	 * @param subDomains
	 * @return
	 */
	FacebookApplicationModel findDefaultApplicationForSubDomains(final List<String> subDomains);

	FacebookApplicationModel createDefaultFacebookApplication(final Long appId, final String secret, final String domain);
}
