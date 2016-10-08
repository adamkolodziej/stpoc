/**
 * 
 */
package com.hybris.campaigns.services;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cockpit.model.meta.TypedObject;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author miroslaw.szot
 */
public interface ItemSyncStrategy
{
	Collection<TypedObject> synchronize(TypedObject item);

	/**
	 * @see de.hybris.platform.cockpit.services.sync.SynchronizationService.SYNCHRONIZATION_OK
	 * @param item
	 * @return
	 */
	int getSyncStatus(TypedObject item);

	Map<CatalogVersionModel, List<TypedObject>> resolveRelatedItems(TypedObject item);

}
