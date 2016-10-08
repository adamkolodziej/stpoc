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
package com.hybris.productlists.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.productlists.data.ProductListData;
import com.hybris.productlists.model.ProductListModel;
import com.hybris.productlists.service.strategy.ProductListModifiableStrategy;


/**
 * @author rmcotton
 * 
 */
public class ProductListCanModifyPopulator implements Populator<ProductListModel, ProductListData>
{
	private ProductListModifiableStrategy productListModifiableStrategy;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final ProductListModel source, final ProductListData target) throws ConversionException
	{
		target.setCanModify(getProductListModifiableStrategy().canModify(source));
	}

	public ProductListModifiableStrategy getProductListModifiableStrategy()
	{
		return productListModifiableStrategy;
	}

	@Required
	public void setProductListModifiableStrategy(final ProductListModifiableStrategy productListModifiableStrategy)
	{
		this.productListModifiableStrategy = productListModifiableStrategy;
	}

}
