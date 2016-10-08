package com.hybris.campaigns.impl;

import de.hybris.platform.acceleratorcms.preview.strategies.PreviewContextInformationLoaderStrategy;
import de.hybris.platform.cms2.model.preview.PreviewDataModel;

import com.hybris.campaigns.cms.timezone.CmsTimeZoneService;


public class LoadTimezoneStrategy implements PreviewContextInformationLoaderStrategy
{

	private CmsTimeZoneService timeZoneService;


	@Override
	public void initContextFromPreview(final PreviewDataModel preview)
	{

		if (preview.getTimezone() != null)
		{
			getTimeZoneService().setSessionTimeZoneOffset(preview.getTimezone());
			//JaloSession.getCurrentSession().setAttribute(PreviewDataModel.TIMEZONE, preview.getTimezone());
		}

	}


	public CmsTimeZoneService getTimeZoneService()
	{
		return timeZoneService;
	}


	public void setTimeZoneService(final CmsTimeZoneService timeZoneService)
	{
		this.timeZoneService = timeZoneService;
	}


}
