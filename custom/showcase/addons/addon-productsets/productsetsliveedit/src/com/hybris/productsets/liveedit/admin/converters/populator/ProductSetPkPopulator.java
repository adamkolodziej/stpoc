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
package com.hybris.productsets.liveedit.admin.converters.populator;

import de.hybris.liveeditaddon.admin.ComponentActionMenuRequestData;
import de.hybris.liveeditaddon.admin.ComponentAdminMenuActionData;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.servicelayer.services.CMSComponentService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.productsets.model.components.ProductSetCarouselComponentModel;


/**
 * @author rmcotton
 * 
 */
public class ProductSetPkPopulator implements Populator<ComponentActionMenuRequestData, ComponentAdminMenuActionData>
{
	private CMSComponentService cmsComponentService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final ComponentActionMenuRequestData source, final ComponentAdminMenuActionData target)
			throws ConversionException
	{
		if (target.getEnabled().booleanValue())
		{
			try
			{
				final ProductSetCarouselComponentModel component = (ProductSetCarouselComponentModel) getCmsComponentService()
						.getSimpleCMSComponent(source.getComponentUid());
				target.setEditItemPk(component.getProductSet().getPk().getLong());
			}
			catch (final CMSItemNotFoundException infe)
			{
				// epic fail for some reason.
				target.setEnabled(Boolean.FALSE);
			}
		}

	}

	public CMSComponentService getCmsComponentService()
	{
		return cmsComponentService;
	}

	@Required
	public void setCmsComponentService(final CMSComponentService cmsComponentService)
	{
		this.cmsComponentService = cmsComponentService;
	}

}
