/**
 * 
 */
package com.hybris.cms.turbocmspages.cockpit.liveedit.dao;

import java.util.Set;


/**
 * @author rmcotton
 * 
 */
public interface TurboReferencePageDao
{
	Set<String> findObjectTemplateCodesForCode(String code);
}
