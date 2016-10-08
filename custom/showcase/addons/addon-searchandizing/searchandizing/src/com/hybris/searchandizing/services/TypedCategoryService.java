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
package com.hybris.searchandizing.services;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;


/**
 * @author rmcotton
 * 
 */
public interface TypedCategoryService
{
	boolean isCategoryType(CategoryModel category);

	boolean isCategoryType(String categoryCode);

	List<CategoryModel> getSuperCategoriesOfType(ProductModel productModel);
}
