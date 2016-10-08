///*
// * [y] hybris Platform
// *
// * Copyright (c) 2000-2013 hybris AG
// * All rights reserved.
// *
// * This software is the confidential and proprietary information of hybris
// * ("Confidential Information"). You shall not disclose such Confidential
// * Information and shall use it only in accordance with the terms of the
// * license agreement you entered into with hybris.
// *
// *
// */
//package com.hybris.addon.cockpits.cms.strategies.impl;
//
//import de.hybris.platform.catalog.model.CatalogModel;
//import de.hybris.platform.catalog.model.CatalogVersionModel;
//import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
//import de.hybris.platform.cms2.model.site.CMSSiteModel;
//import de.hybris.platform.cms2.servicelayer.services.admin.CMSAdminSiteService;
//import de.hybris.platform.cmscockpit.cms.strategies.CounterpartProductCatalogVersionsStrategy;
//import de.hybris.platform.site.BaseSiteService;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.springframework.beans.factory.annotation.Required;
//
//
//
///**
// * @author rmcotton
// *
// */
//public class DefaultCounterpartProductCatalogVersionsStrategy implements CounterpartProductCatalogVersionsStrategy
//{
//
//	private CMSAdminSiteService cmsAdminSiteService;
//	private BaseSiteService baseSiteService;
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see de.hybris.showcase.strategies.CounterpartProductCatalogVersionsStrategy#getCounterpartProductCatalogVersion
//	 * (de.hybris.platform.catalog.model.CatalogVersionModel)
//	 */
//	@Override
//	public Collection<CatalogVersionModel> getCounterpartProductCatalogVersions()
//	{
//		final CMSSiteModel cmsSite = getCmsAdminSiteService().getActiveSite();
//		final CatalogVersionModel currentCatalogVersion = getCmsAdminSiteService().getActiveCatalogVersion();
//
//		if (currentCatalogVersion == null)
//		{
//			return Collections.emptyList();
//		}
//
//		if (currentCatalogVersion.getCatalog() instanceof ContentCatalogModel)
//		{
//			final String versionStringToMatch = currentCatalogVersion.getVersion();
//			final List<CatalogModel> productCatalogs = getBaseSiteService().getProductCatalogs(cmsSite);
//			final Set<CatalogVersionModel> applicableCatalogVersions = new HashSet<CatalogVersionModel>();
//			for (final CatalogModel catalog : productCatalogs)
//			{
//				for (final CatalogVersionModel catalogVersion : catalog.getCatalogVersions())
//				{
//					if (catalogVersion.getVersion().equals(versionStringToMatch))
//					{
//						applicableCatalogVersions.add(catalogVersion);
//					}
//				}
//			}
//
//			if (CollectionUtils.isEmpty(applicableCatalogVersions))
//			{
//				for (final CatalogModel catalog : productCatalogs)
//				{
//					applicableCatalogVersions.add(catalog.getActiveCatalogVersion());
//				}
//			}
//
//			return applicableCatalogVersions;
//		}
//
//		return Collections.singleton(currentCatalogVersion);
//	}
//
//	/**
//	 * @return the cmsAdminSiteService
//	 */
//	public CMSAdminSiteService getCmsAdminSiteService()
//	{
//		return cmsAdminSiteService;
//	}
//
//	/**
//	 * @param cmsAdminSiteService
//	 *           the cmsAdminSiteService to set
//	 */
//	@Required
//	public void setCmsAdminSiteService(final CMSAdminSiteService cmsAdminSiteService)
//	{
//		this.cmsAdminSiteService = cmsAdminSiteService;
//	}
//
//	/**
//	 * @return the baseSiteService
//	 */
//	public BaseSiteService getBaseSiteService()
//	{
//		return baseSiteService;
//	}
//
//	/**
//	 * @param baseSiteService
//	 *           the baseSiteService to set
//	 */
//	@Required
//	public void setBaseSiteService(final BaseSiteService baseSiteService)
//	{
//		this.baseSiteService = baseSiteService;
//	}
//
//
//}
