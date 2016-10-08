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
package com.hybris.social.addthis.jalo;

import com.hybris.social.addthis.constants.AddthisConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class AddthisManager extends GeneratedAddthisManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( AddthisManager.class.getName() );
	
	public static final AddthisManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (AddthisManager) em.getExtension(AddthisConstants.EXTENSIONNAME);
	}
	
}
