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
package com.hybris.cms.turbocmspages.cockpit.liveedit.service;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.search.data.SearchStateData;


/**
 * @author rmcotton
 * 
 */
public interface TurboCmsPagesLiveEditWorkflowService
{
	boolean canShowMakeLandingPageButton(String pagePk, SearchStateData searchState);

	boolean canActivateLandingPageEdit(String pagePk, SearchStateData searchState);

	public CategoryModel getCategoryForPage(final SearchStateData searchState);

}
