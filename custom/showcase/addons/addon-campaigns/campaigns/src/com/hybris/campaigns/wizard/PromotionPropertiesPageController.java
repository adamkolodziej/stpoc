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
import de.hybris.platform.cockpit.wizards.generic.DefaultGenericItemMandatoryPageController;


/**
 * SHOW-1549 New Campaign Wizard
 * 
 * @author miroslaw.szot
 */
public class PromotionPropertiesPageController extends DefaultGenericItemMandatoryPageController
{
	@Override
	public void beforeNext(final Wizard wizard, final WizardPage page)
	{
		super.beforeNext(wizard, page);
	}
}
