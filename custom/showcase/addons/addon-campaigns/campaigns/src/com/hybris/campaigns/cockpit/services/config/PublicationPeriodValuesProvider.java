package com.hybris.campaigns.cockpit.services.config;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.servicelayer.services.admin.CMSAdminSiteService;
import de.hybris.platform.cockpit.model.meta.PropertyDescriptor;
import de.hybris.platform.cockpit.services.config.AvailableValuesProvider;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.campaigns.services.PublicationPeriodsService;


/**
 * author: dariusz.malachowski
 */
public class PublicationPeriodValuesProvider implements AvailableValuesProvider
{

	private CMSAdminSiteService cmsAdminSiteService;

	private SessionService sessionService;

	private PublicationPeriodsService publicationPeriodsService;

	@Override
	public List<? extends Object> getAvailableValues(final PropertyDescriptor propertyDescriptor)
	{
		final CatalogVersionModel catalogVersion = cmsAdminSiteService.getActiveCatalogVersion();
		if (catalogVersion != null)
		{
			return publicationPeriodsService.getAllActivePublicationPeriods(catalogVersion);
		}
		else
		{
			return Collections.emptyList();
		}
	}

	public SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * @return the publicationPeriodsService
	 */
	public PublicationPeriodsService getPublicationPeriodsService()
	{
		return publicationPeriodsService;
	}

	/**
	 * @param publicationPeriodsService
	 *           the publicationPeriodsService to set
	 */
	public void setPublicationPeriodsService(final PublicationPeriodsService publicationPeriodsService)
	{
		this.publicationPeriodsService = publicationPeriodsService;
	}

	/**
	 * @return the cmsAdminSiteService
	 */
	public CMSAdminSiteService getCmsAdminSiteService()
	{
		return cmsAdminSiteService;
	}

	/**
	 * @param cmsAdminSiteService
	 *           the cmsAdminSiteService to set
	 */
	public void setCmsAdminSiteService(final CMSAdminSiteService cmsAdminSiteService)
	{
		this.cmsAdminSiteService = cmsAdminSiteService;
	}

}
