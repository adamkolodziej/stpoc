/**
 * 
 */
package com.hybris.addon.common.config.cms.dataattributeproviders;

import java.util.Map;

import de.hybris.platform.acceleratorcms.data.CmsPageRequestContextData;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;


/**
 * @author mgolubovic
 * 
 */
public interface TagDataAttributesProvider
{
	Map<String, String> getAttributes(AbstractCMSComponentModel comContainer, AbstractCMSComponentModel currentComponent, 
			CmsPageRequestContextData currentCmsPageRequestContextData);
}
