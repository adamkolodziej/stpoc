/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.hybris.cms.turbocmspages.cockpit.liveedit.events;

import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.preview.PreviewDataModel;
import de.hybris.platform.cmscockpit.cms.events.LiveEditBrowserCockpitEventHandler;
import de.hybris.platform.cmscockpit.enums.LiveEditVariant;
import de.hybris.platform.cmscockpit.session.impl.LiveEditBrowserArea;
import de.hybris.platform.cmscockpit.session.impl.LiveEditBrowserModel;
import de.hybris.platform.cockpit.events.CockpitEvent;
import de.hybris.platform.cockpit.events.impl.ItemChangedEvent;
import de.hybris.platform.cockpit.session.UIBrowserArea;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.servicelayer.model.ModelService;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.cms.turbocmspages.cockpit.liveedit.service.TurboCmsPagesLiveEditWorkflowService;


/**
 * When a page is created and the current focussed brow
 *
 * @author rmcotton
 */
public class SwitchToLiveEditOnPageCreateEventHandler implements LiveEditBrowserCockpitEventHandler<UIBrowserArea>
{
	private TurboCmsPagesLiveEditWorkflowService turboCmsPagesLiveEditWorkflowService;
	private ModelService modelService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.addon.cockpits.cms.events.AddOnLiveEditBrowserCockpitEventHandler#handleCockpitEvent(de.hybris.platform
	 * .cockpit.events.CockpitEvent, de.hybris.platform.cmscockpit.session.impl.LiveEditBrowserArea)
	 */
	@Override
	public void handleCockpitEvent(final CockpitEvent event, final UIBrowserArea browserArea)
	{
		final SearchStateData searchState = ((LiveEditBrowserModel) browserArea.getFocusedBrowser()).getFrontendAttributes()
				.getSearchState();

		if (getTurboCmsPagesLiveEditWorkflowService().canActivateLandingPageEdit(
				((AbstractPageModel) ((ItemChangedEvent) event).getItem().getObject()).getPk().getLongValueAsString(), searchState))
		{
			final UIBrowserArea currentBrowserArea = browserArea.getFocusedBrowser().getArea();
			if (currentBrowserArea instanceof LiveEditBrowserArea)
			{
				final LiveEditBrowserArea liveEditBrowserArea = ((LiveEditBrowserArea) currentBrowserArea);


				final PreviewDataModel previewData = ((LiveEditBrowserModel) liveEditBrowserArea.getFocusedBrowser()).getViewModel()
						.getCurrentPreviewData();
				previewData.setLiveEditVariant(LiveEditVariant.QUICKEDIT);
				modelService.save(previewData);

				liveEditBrowserArea.fireModeChange(true);
			}
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.addon.cockpits.cms.events.AddOnLiveEditBrowserCockpitEventHandler#canHandleEvent(de.hybris.platform
	 * .cockpit.events.CockpitEvent, de.hybris.platform.cockpit.session.UIBrowserArea)
	 */
	@Override
	public boolean canHandleEvent(final CockpitEvent event, final UIBrowserArea browserArea)
	{
		return (event instanceof ItemChangedEvent && browserArea.getFocusedBrowser() instanceof LiveEditBrowserModel && (((ItemChangedEvent) event)
				.getItem().getObject() instanceof AbstractPageModel && ((LiveEditBrowserModel) browserArea.getFocusedBrowser())
				.getFrontendAttributes() != null));
	}


	public TurboCmsPagesLiveEditWorkflowService getTurboCmsPagesLiveEditWorkflowService()
	{
		return turboCmsPagesLiveEditWorkflowService;
	}

	@Required
	public void setTurboCmsPagesLiveEditWorkflowService(
			final TurboCmsPagesLiveEditWorkflowService turboCmsPagesLiveEditWorkflowService)
	{
		this.turboCmsPagesLiveEditWorkflowService = turboCmsPagesLiveEditWorkflowService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}



}
