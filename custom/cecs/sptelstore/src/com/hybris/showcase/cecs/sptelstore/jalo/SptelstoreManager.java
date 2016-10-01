package com.hybris.showcase.cecs.sptelstore.jalo;

import com.hybris.showcase.cecs.sptelstore.constants.SptelstoreConstants;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class SptelstoreManager extends GeneratedSptelstoreManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( SptelstoreManager.class.getName() );
	
	public static final SptelstoreManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (SptelstoreManager) em.getExtension(SptelstoreConstants.EXTENSIONNAME);
	}
	
}
