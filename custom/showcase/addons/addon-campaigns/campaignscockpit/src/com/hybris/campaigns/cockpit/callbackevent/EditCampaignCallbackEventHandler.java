/**
 * 
 */
package com.hybris.campaigns.cockpit.callbackevent;

import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cmscockpit.components.liveedit.LiveEditView;
import de.hybris.liveeditaddon.cockpit.callbackevent.AbstractLiveEditCallbackEventHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
public class EditCampaignCallbackEventHandler extends AbstractLiveEditCallbackEventHandler
{
	private static final String COMPONENT_ID="cmp_id";
	
	@Override
	public String getEventId()
	{
		return "editCampaign";
	}

	@Override
	public void onCallbackEvent(final LiveEditView view, Map passedAttributes) throws Exception
	{
		final String componentId = passedAttributes.get(COMPONENT_ID).toString();
		try
		{
			final AbstractCMSComponentModel component = getComponentForUid(componentId, view);

			if (!component.getPublicationPeriods().isEmpty())
			{
				final CampaignWizard campaignWizard = (CampaignWizard) SpringUtil.getBean("campaignEditWizard");

				final Map<String, Object> predefinedValues = new HashMap<String, Object>();

				final List<AbstractCMSComponentModel> items = Arrays.asList(component);
				predefinedValues.put(PublicationPeriodModel._TYPECODE + "." + PublicationPeriodModel.CMSITEMS.toLowerCase(), items);

				campaignWizard.initialize(predefinedValues);

				campaignWizard.show();
			}
			else
			{
				throw new IllegalArgumentException("Current component doesn't not have campaing defined!");
			}
		}
		catch (final IllegalArgumentException e)
		{
			Messagebox.show(Labels.getLabel("dialog.campains.notfound.error.message"),
					Labels.getLabel("dialog.campains.notfound.error.title"), Messagebox.CANCEL, Messagebox.ERROR);
		}
		finally
		{
			view.update();
		}
	}

}
