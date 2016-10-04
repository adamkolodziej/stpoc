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
package com.hybris.cms.turbocmspages.cockpit.liveedit.wizards;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cmscockpit.wizard.CmsWizard;
import de.hybris.platform.cockpit.session.BrowserModel;
import de.hybris.platform.cockpit.session.BrowserSectionModel;


/**
 * @author rmcotton
 * 
 */
public class CmsCategoryPageWizard extends CmsWizard
{
	private final CategoryModel category;

	public CmsCategoryPageWizard(final BrowserModel browserModel, final BrowserSectionModel browserSectionModel,
			final CategoryModel category)
	{
		super(browserModel, browserSectionModel);
		this.category = category;
	}

	public CategoryModel getCategory()
	{
		return this.category;
	}
}
