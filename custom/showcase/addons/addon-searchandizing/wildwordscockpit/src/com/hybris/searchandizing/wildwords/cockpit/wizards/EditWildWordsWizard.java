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
package com.hybris.searchandizing.wildwords.cockpit.wizards;

import de.hybris.platform.cmscockpit.components.liveedit.LiveEditView;
import de.hybris.platform.cmscockpit.components.liveedit.impl.DefaultLiveEditViewModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.util.StopWatch;

import java.util.Collections;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Window;



/**
 * @author piotr.kalinowski
 * 
 */
public class EditWildWordsWizard
{
	private final String componentURI = "/cmscockpit/editWildWords.zul";
	public static final String WIZARD_ARG = "wizardModel";

	private final EditWildWordsViewModel editWildWordsViewModel;

	public EditWildWordsWizard(final LanguageModel language, final DefaultLiveEditViewModel liveEditViewModel)
	{
		editWildWordsViewModel = new DefaultEditWildWordsViewModel();
		final StopWatch timer = new StopWatch("Initializing Edit Wild Words Wizard");
		try
		{
			editWildWordsViewModel.initModel(liveEditViewModel, language);
		}
		finally
		{
			timer.stop();
		}
	}

	public void show(final LiveEditView liveEditView)
	{

		final Window wizardWindow = createFrameComponent(liveEditView);
		editWildWordsViewModel.fireInitEvents();

		wizardWindow.setPosition("center");
		wizardWindow.doHighlighted();

	}

	protected Window createFrameComponent(final LiveEditView liveEditView)
	{
		final Window ret = (Window) Executions.createComponents(getComponentURI(), null,
				Collections.singletonMap(WIZARD_ARG, editWildWordsViewModel));

		ret.applyProperties();
		new AnnotateDataBinder(ret).loadAll();
		ret.setParent(liveEditView.getViewComponent());

		return ret;
	}

	protected String getComponentURI()
	{
		return this.componentURI;
	}
}
