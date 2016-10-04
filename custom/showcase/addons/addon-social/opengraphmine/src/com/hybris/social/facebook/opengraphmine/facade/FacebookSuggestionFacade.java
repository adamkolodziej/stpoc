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
package com.hybris.social.facebook.opengraphmine.facade;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.Collection;
import java.util.List;

import com.hybris.social.facebook.opengraphmine.enums.CategoriesOperator;
import com.hybris.social.facebook.opengraphmine.enums.SocialContentPopulationRule;
import com.hybris.social.facebook.opengraphmine.facade.data.FacebookSuggestedProductData;


/**
 * Returns suggested products based on data extracted from the users linked facebook profile.
 * 
 * @author rmcotton
 */
public interface FacebookSuggestionFacade
{

	/**
	 * Returns products that are directly linked to liked pages.
	 */
	List<FacebookSuggestedProductData> getSuggestions(SocialContentPopulationRule populationRule);

	/**
	 * @param populationRule
	 * @param categories
	 * @param categoriesOperator
	 * @param fullCategoryPath
	 * @return Filterred list of suggested products
	 */
	List<FacebookSuggestedProductData> getSuggestions(SocialContentPopulationRule populationRule,
			Collection<CategoryModel> categories, CategoriesOperator categoriesOperator, boolean fullCategoryPath);

	FacebookSuggestedProductData getProductSocialData(ProductData product, SocialContentPopulationRule populationRule,
			Collection<? extends ProductOption> options);
}
