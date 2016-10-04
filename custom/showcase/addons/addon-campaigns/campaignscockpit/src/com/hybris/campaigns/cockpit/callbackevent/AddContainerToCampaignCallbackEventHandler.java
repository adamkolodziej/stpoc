/**
 * 
 */
package com.hybris.campaigns.cockpit.callbackevent;

import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cms2.model.contents.components.SimpleCMSComponentModel;
import de.hybris.platform.cmscockpit.components.liveedit.LiveEditView;
import de.hybris.liveeditaddon.cockpit.callbackevent.AbstractLiveEditCallbackEventHandler;

import java.util.Map;

import org.zkoss.spring.SpringUtil;

import com.hybris.campaigns.model.PublicationPeriodModel;
import com.hybris.campaigns.wizard.CampaignWizard;


/**
 * 
 * @author lukasz.bochenek
 */
public class AddContainerToCampaignCallbackEventHandler extends AbstractLiveEditCallbackEventHandler
{

	private static final String COMPONENT_ID="cmp_id";
	@Override
	public String getEventId()
	{
		return "addContainerToCampaign";
	}

	@Override
	public void onCallbackEvent(final LiveEditView view, final Map passedAttributes) throws Exception
	{
		final String componentId = passedAttributes.get(COMPONENT_ID).toString();
		try
		{
			final AbstractCMSComponentModel component = getComponentForUid(componentId, view);

			if (component instanceof SimpleCMSComponentModel)
			{
				final SimpleCMSComponentModel sc = (SimpleCMSComponentModel) component;
				final CampaignWizard campaignWizard = (CampaignWizard) SpringUtil.getBean("campaignEditWizard");
				campaignWizard.getAdditionalValues().put(PublicationPeriodModel._TYPECODE + "." + PublicationPeriodModel.CMSITEMS,
						sc.getContainers());

				campaignWizard.show();
			}
			else
			{
				throw new IllegalArgumentException("Unsupported component type [" + component.getClass() + "]");
			}
		}
		finally
		{
			view.update();
		}
	}

}
