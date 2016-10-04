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

import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.values.ObjectValueContainer;
import de.hybris.platform.cockpit.wizards.Wizard;
import de.hybris.platform.cockpit.wizards.WizardPage;
import de.hybris.platform.cockpit.wizards.generic.AdvancedSearchPage;
import de.hybris.platform.core.model.ItemModel;

import java.util.List;

import com.hybris.campaigns.model.PublicationPeriodModel;


/**
 * SHOW-1549 New Campaign Wizard
 * 
 * @author miroslaw.szot
 */
public class PublicationPeriodAdvancedSearchPageController extends CampaignAdvancedSearchPageController
{
	@Override
	public void beforeNext(final Wizard wizard, final WizardPage page)
	{
		super.beforeNext(wizard, page);

		final AdvancedSearchPage searchPage = (AdvancedSearchPage) page;

		final List<ItemModel> currentlySelected = extractSelectedChildren(searchPage.getTableModel());
		if (!currentlySelected.isEmpty())
		{
			final TypedObject item = getTypeService().wrapItem(currentlySelected.get(0));

			final CampaignWizard itemWizard = (CampaignWizard) wizard;
			itemWizard.setPublicationPeriodItem(item);

			final ObjectValueContainer valueContainer = itemWizard.getPublicationPeriodObjectValueContainer();
			itemWizard.handleAdditionalValues(itemWizard.getAdditionalValues(), PublicationPeriodModel._TYPECODE, valueContainer);
		}

		if (wizard.getDefaultController() != null)
		{
			wizard.getDefaultController().beforeNext(wizard, page);
		}
	}
}
