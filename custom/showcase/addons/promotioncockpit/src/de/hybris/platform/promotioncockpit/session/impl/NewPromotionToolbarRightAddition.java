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
package de.hybris.platform.promotioncockpit.session.impl;

import de.hybris.platform.cockpit.components.contentbrowser.AbstractMultiViewToolbarBrowserComponent;
import de.hybris.platform.cockpit.session.SearchBrowserModel;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.util.UITools;
import de.hybris.platform.cockpit.wizards.generic.NewItemWizard;
import de.hybris.platform.jalo.security.AccessManager;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Toolbarbutton;

import com.hybris.addon.cockpits.components.toolbar.ToolbarRightAddition;


/**
 *
 */
public class NewPromotionToolbarRightAddition implements ToolbarRightAddition
{

	@Override
	public Component getContent(final SearchBrowserModel model, final AbstractMultiViewToolbarBrowserComponent toolbar)
	{
		final Div btnContainer = new Div();
		btnContainer.setSclass("new_btn_container");

		final Toolbarbutton addElementButton = new Toolbarbutton("", "/cockpit/images/add_btn.gif");
		addElementButton.setTooltiptext(Labels.getLabel("promotioncockpit.create_promotion"));

		UITools.addBusyListener(addElementButton, Events.ON_CLICK, new EventListener()
		{
			@Override
			public void onEvent(final Event event) throws Exception //NOPMD: ZK specific
			{
				final NewItemWizard itemWizard = new NewItemWizard(UISessionUtils.getCurrentSession().getTypeService()
						.getObjectTemplate("AbstractPromotion"), toolbar.getRoot(), null);

				final Map<String, Object> contextValues = new HashMap<String, Object>();
				itemWizard.setPredefinedValues(contextValues);
				itemWizard.setAllowCreate(UISessionUtils.getCurrentSession().getSystemService()
						.checkPermissionOn("AbstractPromotion", AccessManager.CREATE));
				itemWizard.setAllowSelect(false);
				itemWizard.start();
			}
		}, null, "general.updating.busy");

		btnContainer.appendChild(addElementButton);
		return btnContainer;
	}

}
