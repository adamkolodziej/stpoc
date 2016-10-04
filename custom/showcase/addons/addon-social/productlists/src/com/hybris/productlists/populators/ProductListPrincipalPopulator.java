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

import de.hybris.platform.commercefacades.user.data.PrincipalData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.productlists.data.ProductListData;
import com.hybris.productlists.model.PrincipalListOwnerModel;
import com.hybris.productlists.model.ProductListModel;
import com.hybris.productlists.model.ProductListOwnerModel;


/**
 * @author rmcotton
 * 
 */
public class ProductListPrincipalPopulator implements Populator<ProductListModel, ProductListData>
{

	private Converter<PrincipalModel, PrincipalData> principalConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final ProductListModel source, final ProductListData target) throws ConversionException
	{
		final List<PrincipalData> principals = new LinkedList<PrincipalData>();
		for (final ProductListOwnerModel listOwner : source.getListOwners())
		{
			if (listOwner instanceof PrincipalListOwnerModel)
			{
				principals.add(getPrincipalConverter().convert(((PrincipalListOwnerModel) listOwner).getPrincipal()));
			}
		}
		target.setRegisteredOwners(principals);
	}

	public Converter<PrincipalModel, PrincipalData> getPrincipalConverter()
	{
		return principalConverter;
	}

	@Required
	public void setPrincipalConverter(final Converter<PrincipalModel, PrincipalData> principalConverter)
	{
		this.principalConverter = principalConverter;
	}

}
