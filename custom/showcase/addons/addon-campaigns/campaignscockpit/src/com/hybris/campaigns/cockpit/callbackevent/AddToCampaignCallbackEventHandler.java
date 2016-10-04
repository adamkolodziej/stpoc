/**
 * 
 */
package com.hybris.campaigns.cockpit.callbackevent;

import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cmscockpit.components.liveedit.LiveEditView;
import de.hybris.liveeditaddon.cockpit.callbackevent.AbstractLiveEditCallbackEventHandler;

import java.util.Arrays;
import java.util.Map;

import org.zkoss.spring.SpringUtil;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.Messagebox;

import com.hybris.campaigns.model.PublicationPeriodModel;
import com.hybris.campaigns.wizard.CampaignWizard;


/**
 * 
 * @author miroslaw.szot
 */
public class AddToCampaignCallbackEventHandler extends AbstractLiveEditCallbackEventHandler
{

	private static final String COMPONENT_ID="cmp_id";
	@Override
	public String getEventId()
	{
		return "addToCampaign";
	}

	@Override
	public void onCallbackEvent(final LiveEditView view, Map passedAttributes) throws Exception
	{
		final String componentId = passedAttributes.get(COMPONENT_ID).toString();
		try
		{
			final AbstractCMSComponentModel component = getComponentForUid(componentId, view);

			final CampaignWizard campaignWizard = (CampaignWizard) SpringUtil.getBean("campaignEditWizard");

			campaignWizard.getAdditionalValues().put(PublicationPeriodModel._TYPECODE + "." + PublicationPeriodModel.CMSITEMS,
					Arrays.asList(component));

			campaignWizard.show();
		}
		finally
		{
			view.update();
		}
	}

}
