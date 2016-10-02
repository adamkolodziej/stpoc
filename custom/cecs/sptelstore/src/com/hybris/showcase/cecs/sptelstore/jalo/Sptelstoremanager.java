package com.hybris.showcase.cecs.sptelstore.jalo;

import com.hybris.showcase.cecs.sptelstore.constants.SptelstoreConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class Sptelstoremanager extends GeneratedSptelstoremanager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( Sptelstoremanager.class.getName() );
	
	public static final Sptelstoremanager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (Sptelstoremanager) em.getExtension(SptelstoreConstants.EXTENSIONNAME);
	}
	
}
