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
package com.hybris.searchandizing.services.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.category.CommerceCategoryService;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.searchandizing.services.TypedCategoryService;


/**
 * @author rmcotton
 * 
 */
public class DefaultTypedCategoryService implements TypedCategoryService
{

	private CommerceCategoryService commerceCategoryService;

	private String rootCategoryCode;



	@Override
	public boolean isCategoryType(final String categoryCode)
	{
		return isCategoryType(getCommerceCategoryService().getCategoryForCode(categoryCode));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.showcase.yacceleratorcore.category.ShowcaseCategoryService#isBrandCategory(de.hybris.platform.category
	 * .model.CategoryModel)
	 */
	@Override
	public boolean isCategoryType(final CategoryModel category)
	{
		for (final CategoryModel cm : getRootCategories(category))
		{
			if (getRootCategoryCode().equals(cm.getCode()))
			{
				return true;
			}
		}
		return false;
	}

	protected Set<CategoryModel> getRootCategories(final CategoryModel category)
	{
		final Set<CategoryModel> rootCategories = new LinkedHashSet<CategoryModel>();
		for (final List<CategoryModel> path : getCommerceCategoryService().getPathsForCategory(category))
		{
			rootCategories.add(path.get(0));
		}
		return rootCategories;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.searchandizing.service.TypedCategoryService#getSuperCategoriesOfType(de.hybris.platform.core
	 * .model.product.ProductModel)
	 */
	@Override
	public List<CategoryModel> getSuperCategoriesOfType(final ProductModel productModel)
	{
		final List<CategoryModel> superCategories = new LinkedList<CategoryModel>();
		for (final CategoryModel superCategory : productModel.getSupercategories())
		{
			if (isCategoryType(superCategory))
			{
				superCategories.add(superCategory);
			}
		}
		return superCategories;
	}

	/**
	 * @param commerceCategoryService
	 *           the commerceCategoryService to set
	 */
	@Required
	public void setCommerceCategoryService(final CommerceCategoryService commerceCategoryService)
	{
		this.commerceCategoryService = commerceCategoryService;
	}

	/**
	 * @return the commerceCategoryService
	 */
	public CommerceCategoryService getCommerceCategoryService()
	{
		return commerceCategoryService;
	}

	@Required
	public void setRootCategoryCode(final String rootCategoryCode)
	{
		this.rootCategoryCode = rootCategoryCode;
	}


	public String getRootCategoryCode()
	{
		return rootCategoryCode;
	}


}
