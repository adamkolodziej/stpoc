/**
 * 
 */
package com.hybris.cms.turbocmspages.setup.populators.dao;

import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.commerceservices.enums.UiExperienceLevel;


/**
 * @author rmcotton
 * 
 */
public interface UiExperienceSupportDao
{
	boolean hasUiExperienceRestrictions(ContentCatalogModel contentCatalogModel, final UiExperienceLevel uiExperience);
}
