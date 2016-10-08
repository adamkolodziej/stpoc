/**
 * 
 */
package com.hybris.campaigns.services.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.SyncItemJobModel;
import de.hybris.platform.cms2.model.contents.CMSItemModel;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.meta.TypeService;
import de.hybris.platform.cockpit.services.sync.SynchronizationService;
import de.hybris.platform.cockpit.services.sync.SynchronizationService.SyncContext;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.campaigns.model.PublicationPeriodModel;
import com.hybris.campaigns.services.ItemSyncStrategy;
import com.hybris.productsets.model.ProductSetModel;


/**
 * 
 * @author miroslaw.szot
 */
public class PublicationPeriodSyncStrategy implements ItemSyncStrategy
{
	private static final Logger LOG = Logger.getLogger(PublicationPeriodSyncStrategy.class);
	private SynchronizationService synchronizationService;
	private TypeService typeService;

	@Override
	public int getSyncStatus(final TypedObject item)
	{
		final Map<CatalogVersionModel, List<TypedObject>> resolveRelatedItems = resolveRelatedItems(item);
		final Collection<List<TypedObject>> cvList = resolveRelatedItems.values();
		for (final List<TypedObject> itemList : cvList)
		{
			for (final TypedObject relatedItem : itemList)
			{
				final int syncStatus = synchronizationService.isObjectSynchronized(relatedItem);
				if (syncStatus != SynchronizationService.SYNCHRONIZATION_OK)
				{
					return syncStatus;
				}
			}
		}

		return SynchronizationService.SYNCHRONIZATION_OK;
	}

	@Override
	public Collection<TypedObject> synchronize(final TypedObject item)
	{
		final Map<CatalogVersionModel, List<TypedObject>> resolveRelatedItems = resolveRelatedItems(item);
		final Set<Entry<CatalogVersionModel, List<TypedObject>>> entrySet = resolveRelatedItems.entrySet();

		int safetyNetLock = 15;
		final Set<TypedObject> affectedItems = new HashSet<TypedObject>();

		int syncStatus = SynchronizationService.SYNCHRONIZATION_NOT_OK;
		while (syncStatus == SynchronizationService.SYNCHRONIZATION_NOT_OK && safetyNetLock > 0)
		{
			for (final Entry<CatalogVersionModel, List<TypedObject>> entry : entrySet)
			{
				final List<TypedObject> items = entry.getValue();
				if (CollectionUtils.isNotEmpty(items))
				{
					affectedItems.addAll(syncItems(items));
				}
			}
			safetyNetLock--;
			syncStatus = getSyncStatus(item);
		}
		if (syncStatus == SynchronizationService.SYNCHRONIZATION_NOT_OK)
		{
			LOG.error("unable to synchronize completely, sync status: " + syncStatus);
		}
		return affectedItems;
	}

	protected Collection<TypedObject> syncItems(final List<TypedObject> items)
	{
		final SyncContext syncCtx = synchronizationService.getSyncContext(items.get(0));

		final List<SyncItemJobModel>[] matrixRules = syncCtx.getSyncJobs();
		final int size = matrixRules[0].size() + matrixRules[1].size();
		if (size > 1)
		{
			LOG.warn("only one source/one target syncs are supported atm");
		}
		else if (matrixRules[0].size() == 1 && matrixRules[1].size() == 0)
		{
			return synchronizationService.performSynchronization(items, null, null, null);
		}
		else
		{
			LOG.info("no synchronization rule available");
		}
		return Collections.emptyList();
	}

	@Override
	public Map<CatalogVersionModel, List<TypedObject>> resolveRelatedItems(final TypedObject item)
	{
		final Map<CatalogVersionModel, List<TypedObject>> cvMap = new HashMap<CatalogVersionModel, List<TypedObject>>();

		if (item.getObject() instanceof PublicationPeriodModel)
		{
			final PublicationPeriodModel publicationPeriodModel = (PublicationPeriodModel) item.getObject();
			final List<TypedObject> list = getItemListForSync(cvMap, publicationPeriodModel.getCatalogVersion());
			list.add(item);

			resolveCMSitems(cvMap, publicationPeriodModel.getCmsitems());
			resolveProducts(cvMap, publicationPeriodModel.getProducts());
			resolveProductSets(cvMap, publicationPeriodModel.getProductSets());
		}
		return cvMap;
	}

	private void resolveCMSitems(final Map<CatalogVersionModel, List<TypedObject>> cvMap, final Set<CMSItemModel> cmsitems)
	{
		if (CollectionUtils.isNotEmpty(cmsitems))
		{
			for (final CMSItemModel cmsitem : cmsitems)
			{
				final List<TypedObject> list = getItemListForSync(cvMap, cmsitem.getCatalogVersion());
				list.add(typeService.wrapItem(cmsitem));
			}
		}
	}

	private void resolveProducts(final Map<CatalogVersionModel, List<TypedObject>> map, final Collection<ProductModel> products)
	{
		if (CollectionUtils.isNotEmpty(products))
		{
			for (final ProductModel product : products)
			{
				final List<TypedObject> list = getItemListForSync(map, product.getCatalogVersion());
				list.add(typeService.wrapItem(product));
			}
		}
	}

	private void resolveProductSets(final Map<CatalogVersionModel, List<TypedObject>> cvMap,
			final Collection<ProductSetModel> productSets)
	{
		if (CollectionUtils.isNotEmpty(productSets))
		{
			for (final ProductSetModel item : productSets)
			{
				final List<TypedObject> list = getItemListForSync(cvMap, item.getCatalogVersion());
				list.add(typeService.wrapItem(item));
			}
		}
	}

	private List<TypedObject> getItemListForSync(final Map<CatalogVersionModel, List<TypedObject>> cvMap,
			final CatalogVersionModel catalogVersion)
	{
		List<TypedObject> list = cvMap.get(catalogVersion);
		if (list == null)
		{
			list = new ArrayList<TypedObject>();
			cvMap.put(catalogVersion, list);
		}
		return list;
	}

	public SynchronizationService getSynchronizationService()
	{
		return synchronizationService;
	}

	@Required
	public void setSynchronizationService(final SynchronizationService synchronizationService)
	{
		this.synchronizationService = synchronizationService;
	}

	public TypeService getTypeService()
	{
		return typeService;
	}

	@Required
	public void setTypeService(final TypeService typeService)
	{
		this.typeService = typeService;
	}

}
