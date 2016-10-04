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
package com.hybris.social.facebook.opengraphmine.service.dao;

import de.hybris.platform.servicelayer.internal.dao.Dao;

import com.hybris.social.facebook.opengraphmine.model.FacebookPageModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookReferenceModel;


/**
 * @author rmcotton
 * 
 */
public interface FacebookReferenceDao extends Dao
{
	FacebookReferenceModel getReferenceById(final String id);

	FacebookPageModel getPageById(final String id);
}
