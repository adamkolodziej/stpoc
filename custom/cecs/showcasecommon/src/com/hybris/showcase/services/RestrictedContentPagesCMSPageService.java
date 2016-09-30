package com.hybris.showcase.services;

import de.hybris.platform.acceleratorservices.data.RequestContextData;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.servicelayer.services.CMSPageService;


public interface RestrictedContentPagesCMSPageService extends CMSPageService
{

	ContentPageModel getPageForLabel(String label, RequestContextData requestContextData) throws CMSItemNotFoundException;

}
