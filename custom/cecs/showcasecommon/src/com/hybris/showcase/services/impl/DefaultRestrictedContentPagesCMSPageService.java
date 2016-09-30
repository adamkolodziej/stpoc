package com.hybris.showcase.services.impl;

import com.hybris.showcase.services.RestrictedContentPagesCMSPageService;
import de.hybris.platform.acceleratorservices.data.RequestContextData;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.impl.DefaultCMSPageService;

import java.util.Collection;


public class DefaultRestrictedContentPagesCMSPageService extends DefaultCMSPageService implements
        RestrictedContentPagesCMSPageService
{

	@Override
	public ContentPageModel getPageForLabel(final String label, final RequestContextData requestContextData)
			throws CMSItemNotFoundException
	{
		final ContentPageModel page = getSingleContentPage(label);
		if (page != null)
		{
			LOG.debug("Only one ContentPage with label [" + label + "] found. Considering this as default.");
			return page;
		}
		final Collection<AbstractPageModel> pages = cmsPageDao.findAllPagesByLabel(label,
				catalogService.getSessionCatalogVersions());
		final RestrictionData restrictionData = cmsDataFactory.createRestrictionData(requestContextData.getProduct());
		final Collection<AbstractPageModel> result = cmsRestrictionService.evaluatePages(pages, restrictionData);
		if (result.isEmpty())
		{
			throw new CMSItemNotFoundException("No page with label [" + label + "] found.");
		}
		else if (result.size() > 1)
		{
			LOG.warn("More than one page found for label [" + label + "]. Returning default.");
		}
		return (ContentPageModel) result.iterator().next();
	}
}
