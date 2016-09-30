/**
 * 
 */
package com.hybris.showcase.emsextras.facades;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.entitlementfacades.data.EntitlementData;

import java.util.List;


/**
 * @author I307113
 * 
 */
public interface EntitlementsPaginationFacade
{
	public SearchPageData<EntitlementData> getEntitlementSearchPageData(final List<EntitlementData> entitlements,
			final PageableData pageableData, final String sortCode);
}
