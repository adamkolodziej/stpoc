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

import com.restfb.FacebookClient;
import com.restfb.types.Location;
import com.restfb.types.Page;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.facebook.opengraphmine.model.FacebookLocationModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookPageModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookPagePopulator implements Populator<FacebookClient, FacebookPageModel>
{
	private Converter<Location, FacebookLocationModel> locationConverter;
	private List<Populator<Page, FacebookPageModel>> pagePopulators;


	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commerceservices.converter.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final FacebookClient source, final FacebookPageModel target) throws ConversionException
	{
		assert target.getId() != null : "target page must have an id";

		final Page page = source.fetchObject(target.getId(), Page.class);
        populateGeneral(source, page, target);
		populateLinks(source, page, target);
		populateMetrics(source, page, target);
		if (getPagePopulators() != null)
		{
			for (final Populator<Page, FacebookPageModel> populator : getPagePopulators())
			{
				populator.populate(page, target);
			}
		}

	}

	protected void populateGeneral(final FacebookClient connection, final Page page, final FacebookPageModel target)
	{
		target.setCategory(page.getCategory());
		target.setName(page.getName());
		target.setDescription(page.getDescription());
	}

	protected void populateLinks(final FacebookClient connection, final Page page, final FacebookPageModel target)
	{
		if (StringUtils.isNotBlank(page.getLink()))
		{
			target.setLink(page.getLink());
		}
		target.setWebsiteLink(page.getWebsite());
	}

	protected void populateMetrics(final FacebookClient connection, final Page page, final FacebookPageModel target)
	{
		target.setCheckins(Integer.valueOf(page.getCheckins()));
		target.setLikes(page.getLikes().intValue());
		target.setTalkingAboutCount(Integer.valueOf(page.getTalkingAboutCount().intValue()));
	}


	/**
	 * @param pagePopulators
	 *           the pagePopulators to set
	 */
	@Required
	public void setPagePopulators(final List<Populator<Page, FacebookPageModel>> pagePopulators)
	{
		this.pagePopulators = pagePopulators;
	}

	/**
	 * @return the pagePopulators
	 */
	public List<Populator<Page, FacebookPageModel>> getPagePopulators()
	{
		return pagePopulators;
	}


}
