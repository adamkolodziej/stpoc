package com.hybris.campaigns.cockpit.wizards.mediamanagement.service.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.liveeditaddon.cockpit.wizards.mediacontainermanagement.service.impl.AbstractManageMediaService;
import de.hybris.liveeditaddon.cockpit.wizards.productimagemanagement.events.ProductImageModelListener;

import java.util.ArrayList;
import java.util.Map;

import com.hybris.campaigns.model.cms.PromotionBannerComponentModel;


/**
 * author:
 */
public class DefaultManagePromotionBannerMediaService extends AbstractManageMediaService
{


	public DefaultManagePromotionBannerMediaService(final String serverPath, final String siteUid,
			final CatalogVersionModel catalogVersion, final MediaContainerModel mediaContainerModel,
			final PromotionBannerComponentModel component, final Map<String, String> mediaFormatMap)
	{
		this.serverPath = serverPath;
		this.siteUid = siteUid;
		this.catalogVersion = catalogVersion;
		this.mediaContainerModel = mediaContainerModel;
		this.component = component;
		this.mediaFormatMap = mediaFormatMap;

		if (mediaContainerModel != null)
		{
			convertMediasToMap(mediaContainerModel.getMedias(), mediaModelMap);
		}
		listeners = new ArrayList<ProductImageModelListener>();
	}

	@Override
	public void save()
	{
		if (mediaContainerModel == null)
		{
			mediaContainerModel = getModelService().create(MediaContainerModel._TYPECODE);
			mediaContainerModel.setQualifier("base_" + System.nanoTime() + "_MediaContainer");
			mediaContainerModel.setCatalogVersion(catalogVersion);
			((PromotionBannerComponentModel) component).getPromotion().setPromotionBanners(mediaContainerModel);
		}
		mediaContainerModel.setMedias(mediaModelMap.values());

		getModelService().save(this.mediaContainerModel);
		getModelService().save(((PromotionBannerComponentModel) component).getPromotion());
		getModelService().save(component);
		getModelService().saveAll(mediaModelMap.values());
	}
}
