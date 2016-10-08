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
package com.hybris.social.facebook.opengraphmine.service.converter.populator;

import com.restfb.types.Page;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.facebook.opengraphmine.model.FacebookPageModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookPageLinkedSitePopulator implements Populator<Page, FacebookPageModel>
{

	private CMSSiteService cmsSiteService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commerceservices.converter.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final Page source, final FacebookPageModel target) throws ConversionException
	{
		if (target.getLinkedSite() == null && StringUtils.isNotBlank(target.getLink()))
		{
			try
			{
				final CMSSiteModel site = getCmsSiteService().getSiteForURL(new URL(target.getLink()));
				target.setLinkedSite(site);
			}
			catch (final CMSItemNotFoundException e)
			{
				// flag page accordingly
			}
			catch (final MalformedURLException e)
			{
				// bad url
			}
		}

	}

	/**
	 * @param cmsSiteService
	 *           the cmsSiteService to set
	 */
	@Required
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	/**
	 * @return the cmsSiteService
	 */
	public CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}



}
