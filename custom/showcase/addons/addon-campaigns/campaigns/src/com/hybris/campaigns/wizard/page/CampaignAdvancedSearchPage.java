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
package com.hybris.campaigns.wizard.page;

import de.hybris.platform.cockpit.model.listview.TableModelListener;
import de.hybris.platform.cockpit.model.listview.UIListView;
import de.hybris.platform.cockpit.model.misc.ComponentController;
import de.hybris.platform.cockpit.wizards.WizardPage;
import de.hybris.platform.cockpit.wizards.generic.AdvancedSearchPage;

import java.util.List;

import org.zkoss.zk.ui.Component;


/**
 * 
 * @author miroslaw.szot
 */
public class CampaignAdvancedSearchPage extends AdvancedSearchPage
{
	@Override
	public Component createRepresentationItself()
	{
		initAdvanceModeModels();
		return super.createRepresentationItself();
	}

	@Override
	protected ComponentController createListViewSelectorController(final UIListView listView)
	{
		return new DefaultListViewSelectorController(listView)
		{
			@Override
			protected TableModelListener createTableModelListener(final UIListView listView)
			{
				return new DefaultSelectorTableModelListener(listView)
				{
					@Override
					public void selectionChanged(final List<Integer> colIndexes, final List<Integer> rowIndexes)
					{
						this.view.updateSelection();
						final boolean selected = !rowIndexes.isEmpty();

						final WizardPage wizardPage = wizard.getDefaultController().next(wizard, wizard.getCurrentPage());
						if (wizardPage != null)
						{
							wizard.setShowNext(selected);
						}
						else
						{
							wizard.setShowDone(selected);
						}
						getWizard().refreshButtons();
					}
				};
			}
		};
	}
}
