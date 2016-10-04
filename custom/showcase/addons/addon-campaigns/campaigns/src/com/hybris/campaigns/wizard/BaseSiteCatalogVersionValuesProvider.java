/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.hybris.campaigns.wizard;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cockpit.model.meta.ObjectType;
import de.hybris.platform.cockpit.model.meta.PropertyDescriptor;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.config.AvailableValuesProvider;
import de.hybris.platform.cockpit.services.meta.TypeService;
import de.hybris.platform.cockpit.services.values.ObjectValueContainer.ObjectValueHolder;
import de.hybris.platform.cockpit.session.UISession;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.store.BaseStoreModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.hybris.campaigns.model.PublicationPeriodModel;


/**
 * 
 * @author miroslaw.szot
 */
class BaseSiteCatalogVersionValuesProvider implements AvailableValuesProvider
{
	private final CampaignWizard campaignWizard;

	BaseSiteCatalogVersionValuesProvider(final CampaignWizard campaignWizard)
	{
		this.campaignWizard = campaignWizard;
	}

	@Override
	public List<? extends Object> getAvailableValues(final PropertyDescriptor propertyDescriptor)
	{
		final List<? extends Object> catalogVersionsForSite = getContentCatalogVersionsForSite();
		if (CollectionUtils.isEmpty(catalogVersionsForSite))
		{
			return getDefaultContentCatalogVersions(propertyDescriptor);
		}
		return catalogVersionsForSite;
	}

	protected List<Object> getDefaultContentCatalogVersions(final PropertyDescriptor propertyDescriptor)
	{
		final UISession uiSession = UISessionUtils.getCurrentSession();
		final List<Object> availableValues = uiSession.getTypeService().getAvailableValues(propertyDescriptor);

		CollectionUtils.filter(availableValues, new Predicate()
		{

			@Override
			public boolean evaluate(final Object catalogVersion)
			{
				if (catalogVersion instanceof TypedObject)
				{
					return evaluateModel(((TypedObject) catalogVersion).getObject());
				}
				else
				{
					return evaluateModel(catalogVersion);
				}
			}

			private boolean evaluateModel(final Object catalogVersion)
			{
				if (catalogVersion instanceof CatalogVersionModel)
				{
					return ((CatalogVersionModel) catalogVersion).getCatalog() instanceof ContentCatalogModel;
				}

				return false;
			}
		});

		return availableValues;
	}

	protected List<? extends Object> getContentCatalogVersionsForSite()
	{
		final BaseSiteModel site = getSelectedBaseSite();
		if (site != null)
		{
			final List<CatalogModel> catalogs = new ArrayList<CatalogModel>();
			if (site instanceof CMSSiteModel)
			{
				catalogs.addAll(((CMSSiteModel) site).getContentCatalogs());
			}
			final List<CatalogVersionModel> catalogVersions = new ArrayList<CatalogVersionModel>();

			for (final CatalogModel catalog : catalogs)
			{
				catalogVersions.addAll(catalog.getCatalogVersions());
			}

			if (!catalogVersions.isEmpty())
			{
				return catalogVersions;
			}
		}
		return null;
	}

	private List<? extends Object> getCatalogVersionsForSite()
	{
		final BaseSiteModel site = getSelectedBaseSite();
		if (site != null)
		{
			final List<CatalogModel> catalogs = new ArrayList<CatalogModel>();
			for (final BaseStoreModel baseStore : site.getStores())
			{
				catalogs.addAll(baseStore.getCatalogs());
			}
			if (site instanceof CMSSiteModel)
			{
				catalogs.addAll(((CMSSiteModel) site).getContentCatalogs());
			}
			final List<CatalogVersionModel> catalogVersions = new ArrayList<CatalogVersionModel>();

			for (final CatalogModel catalog : catalogs)
			{
				catalogVersions.addAll(catalog.getCatalogVersions());
			}

			if (!catalogVersions.isEmpty())
			{
				return catalogVersions;
			}
		}
		return null;
	}

	protected BaseSiteModel getSelectedBaseSite()
	{
		final UISession uiSession = UISessionUtils.getCurrentSession();
		final TypeService typeService = uiSession.getTypeService();

		final ObjectType type = typeService.getObjectType(PublicationPeriodModel._TYPECODE);
		final String qualifier = type.getCode() + "." + PublicationPeriodModel.BASESITE;
		final PropertyDescriptor baseSitePD = typeService.getPropertyDescriptor(type, qualifier);

		final ObjectValueHolder valueHolder = this.campaignWizard.getPublicationPeriodObjectValueContainer().getValue(baseSitePD,
				null);

		final TypedObject siteItem = (TypedObject) valueHolder.getCurrentValue();

		if (siteItem != null)
		{
			return (BaseSiteModel) siteItem.getObject();
		}
		return null;
	}
}