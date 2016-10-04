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
package com.hybris.campaigns.wizard;

import de.hybris.platform.cockpit.wizards.Wizard;
import de.hybris.platform.cockpit.wizards.WizardPage;
import de.hybris.platform.cockpit.wizards.generic.AdvancedSearchPage;
import de.hybris.platform.cockpit.wizards.generic.DefaultAdvancedSearchPageController;
import de.hybris.platform.core.model.ItemModel;

import java.util.List;


/**
 * SHOW-1549 New Campaign Wizard
 * 
 * @author miroslaw.szot
 */
public class CampaignAdvancedSearchPageController extends DefaultAdvancedSearchPageController
{
	@Override
	public void initPage(final Wizard wizard, final WizardPage page)
	{
		super.initPage(wizard, page);

		final List<ItemModel> currentlySelected = extractSelectedChildren(((AdvancedSearchPage) page).getTableModel());

		final WizardPage wizardPage = next(wizard, page);
		if (wizardPage != null)
		{
			wizard.setShowDone(false);
			wizard.setShowNext(!currentlySelected.isEmpty());
		}
		else
		{
			wizard.setShowDone(!currentlySelected.isEmpty());
			wizard.setShowNext(false);
		}
	}

}
