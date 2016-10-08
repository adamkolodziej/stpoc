/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.hybris.campaigns.cockpit.components.toolbar;

import de.hybris.platform.cockpit.components.contentbrowser.AbstractMultiViewToolbarBrowserComponent;
import de.hybris.platform.cockpit.session.SearchBrowserModel;
import de.hybris.platform.cockpit.util.UITools;

import org.zkoss.spring.SpringUtil;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Toolbarbutton;

import com.hybris.addon.cockpits.components.toolbar.ToolbarRightAddition;
import com.hybris.campaigns.wizard.CampaignWizard;


/**
 * 
 * @author miroslaw.szot
 */
public class NewCampaingToolbarRightAddition implements ToolbarRightAddition
{

	@Override
	public Component getContent(final SearchBrowserModel model, final AbstractMultiViewToolbarBrowserComponent toolbar)
	{
		final Toolbarbutton createButton = new Toolbarbutton("", "/promotioncockpit/images/campaigns-wizard.png");
		createButton.setTooltiptext(Labels.getLabel("campaings.new_campaign", "*New Campaign"));
		UITools.addBusyListener(createButton, Events.ON_CLICK, new EventListener()
		{
			@Override
			public void onEvent(final Event event) throws Exception //NOPMD: ZK specific
			{
				final CampaignWizard campaignWizard = (CampaignWizard) SpringUtil.getBean("campaignWizard");
				campaignWizard.show();
			}
		}, null, "general.updating.busy");
		return createButton;
	}

}
