package com.hybris.showcase.guidedselling.jalo;

import com.hybris.showcase.guidedselling.constants.GuidedsellingConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class GuidedsellingManager extends GeneratedGuidedsellingManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( GuidedsellingManager.class.getName() );
	
	public static final GuidedsellingManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (GuidedsellingManager) em.getExtension(GuidedsellingConstants.EXTENSIONNAME);
	}
	
}
