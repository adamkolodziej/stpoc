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
import de.hybris.platform.cms2.model.preview.PreviewDataModel;
import de.hybris.platform.cockpit.wizards.Wizard;

import org.zkoss.zk.ui.Component;


/**
 * @author rmcotton
 * 
 */
public interface LiveEditCategoryLandingPageWizardInvoker
{
	Wizard start(final Component parent, final CategoryModel category, final PreviewDataModel previewData);
}
