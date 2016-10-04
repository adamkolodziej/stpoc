/**
 * 
 */
package com.hybris.campaigns.liveedit.admin.strategies.impl;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cms2.servicelayer.services.CMSComponentService;
import de.hybris.liveeditaddon.admin.ComponentActionMenuRequestData;
import de.hybris.liveeditaddon.admin.strategies.ComponentAdminActionEnabledStrategy;

import org.springframework.beans.factory.annotation.Required;


/**
 * @author lukasz.bochenek
 * 
 */
public class EditCampaignActionComponentEnabledStrategy implements ComponentAdminActionEnabledStrategy
{
	private CMSComponentService cmsComponentService;

	@Override
	public boolean isEnabled(final ComponentActionMenuRequestData request)
	{
		try
		{
			final String componentUid = request.getComponentUid();

			if (componentUid == null || componentUid.isEmpty())
			{
				return false;
			}

			final AbstractCMSComponentModel component = getCmsComponentService().getAbstractCMSComponent(componentUid);

			return (component.getPublicationPeriods() != null && !component.getPublicationPeriods().isEmpty());
		}
		catch (final CMSItemNotFoundException e)
		{
			throw new IllegalStateException("Component is missing for uid [" + request.getComponentUid() + "]");
		}
	}

	@Override
	public boolean isVisible(final ComponentActionMenuRequestData request, final boolean enabled)
	{
		return enabled;
	}

	public CMSComponentService getCmsComponentService()
	{
		return cmsComponentService;
	}

	@Required
	public void setCmsComponentService(final CMSComponentService cmsComponentService)
	{
		this.cmsComponentService = cmsComponentService;
	}
}
