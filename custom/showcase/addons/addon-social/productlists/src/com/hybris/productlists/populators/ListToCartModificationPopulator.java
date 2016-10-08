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

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.hybris.productlists.data.ListToCartModification;


/**
 * @author rmcotton
 * 
 */
public class ListToCartModificationPopulator implements Populator<CommerceCartModification, ListToCartModification>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final CommerceCartModification source, final ListToCartModification target) throws ConversionException
	{
		target.setCartModification(source);
	}

}
