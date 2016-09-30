/*
 *  
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
 */
package com.hybris.showcase.cecs.tricaststore.jalo;

import com.hybris.showcase.cecs.tricaststore.constants.TricaststoreConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class TricaststoreManager extends GeneratedTricaststoreManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( TricaststoreManager.class.getName() );
	
	public static final TricaststoreManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (TricaststoreManager) em.getExtension(TricaststoreConstants.EXTENSIONNAME);
	}
	
}
