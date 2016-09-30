/**
 * 
 */
package com.hybris.showcase.emsextras.facades.impl;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.SortData;
import de.hybris.platform.entitlementfacades.data.EntitlementData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hybris.showcase.comparators.EndDateEntitlementDataComparator;
import com.hybris.showcase.comparators.MeteredEntitlementDataComparator;
import com.hybris.showcase.comparators.NameEntitlementDataComparator;
import com.hybris.showcase.comparators.StartDateEntitlementDataComparator;
import com.hybris.showcase.comparators.StatusEntitlementDataComparator;
import com.hybris.showcase.emsextras.facades.EntitlementsPaginationFacade;


/**
 * @author I307113
 * 
 */
public class DefaultEntitlementsPaginationFacade implements EntitlementsPaginationFacade
{
	final private static String SORTCODE_NAME = "name";
	final private static String SORTCODE_START_DATE = "startDate";
	final private static String SORTCODE_END_DATE = "endDate";
	final private static String SORTCODE_STATUS = "status";
	final private static String SORTCODE_METERED = "metered";

	@Override
	public SearchPageData<EntitlementData> getEntitlementSearchPageData(final List<EntitlementData> entitlements,
			final PageableData pageableData, final String sortCode)
	{
		final SearchPageData<EntitlementData> entitlementSearchPageData = new SearchPageData<>();

		if (sortCode != null)
		{
			sortEntitlements(entitlements, sortCode);
		}

		final int pageSize = pageableData.getPageSize();
		final PaginationData pD = new PaginationData();
		if (!entitlements.isEmpty())
		{
			final int from = pageableData.getCurrentPage() * pageSize;
			int to = ((pageableData.getCurrentPage() + 1) * pageSize);
			if (to > entitlements.size())
			{
				to = entitlements.size();
			}
			entitlementSearchPageData.setResults(entitlements.subList(from, to));
			final int numberOfPages = (int) Math.ceil((double) entitlements.size() / (double) pageSize);
			pD.setNumberOfPages(numberOfPages);
			pD.setTotalNumberOfResults(entitlements.size());
		}
		else
		{
			entitlementSearchPageData.setResults(entitlements);
			pD.setNumberOfPages(0);
			pD.setTotalNumberOfResults(0);
		}
		entitlementSearchPageData.setSorts(createSorts(pageableData.getSort()));
		pD.setCurrentPage(pageableData.getCurrentPage());
		pD.setPageSize(pageSize);
		pD.setSort(sortCode);
		entitlementSearchPageData.setPagination(pD);

		return entitlementSearchPageData;
	}


	protected void sortEntitlements(final List<EntitlementData> entitlements, final String sortCode)
	{
		switch (sortCode)
		{
			case SORTCODE_NAME:
				Collections.sort(entitlements, new NameEntitlementDataComparator());
				break;
			case SORTCODE_START_DATE:
				Collections.sort(entitlements, new StartDateEntitlementDataComparator());
				break;
			case SORTCODE_END_DATE:
				Collections.sort(entitlements, new EndDateEntitlementDataComparator());
				break;
			case SORTCODE_STATUS:
				Collections.sort(entitlements, new StatusEntitlementDataComparator());
				break;
			case SORTCODE_METERED:
				Collections.sort(entitlements, new MeteredEntitlementDataComparator());
				break;
		}

		return;
	}

	protected List<SortData> createSorts(final String selectedSortCode)
	{
		final List result = new ArrayList(5);

		result.add(createSort(SORTCODE_NAME, null, selectedSortCode));
		result.add(createSort(SORTCODE_START_DATE, null, selectedSortCode));
		result.add(createSort(SORTCODE_END_DATE, null, selectedSortCode));
		result.add(createSort(SORTCODE_STATUS, null, selectedSortCode));
		result.add(createSort(SORTCODE_METERED, null, selectedSortCode));

		return result;
	}

	protected SortData createSort(final String code, final String name, final String selectedSortCode)
	{
		final SortData sortData = new SortData();
		sortData.setCode(code);
		sortData.setName(name);
		sortData.setSelected((selectedSortCode != null) && (selectedSortCode.equals(code)));
		return sortData;
	}

}
