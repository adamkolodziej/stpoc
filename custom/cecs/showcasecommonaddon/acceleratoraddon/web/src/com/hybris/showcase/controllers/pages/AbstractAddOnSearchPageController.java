/**
 *
 */
package com.hybris.showcase.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController.ShowMode;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import org.springframework.ui.Model;


/**
 * @author Rafal Zymla
 *
 */
public class AbstractAddOnSearchPageController extends AbstractAddOnPageController
{

	public static final int MAX_PAGE_LIMIT = 100;
	private static final String PAGINATION_NUMBER_OF_RESULTS_COUNT = "pagination.number.results.count";


	protected PageableData createPageableData(final int pageNumber, final int pageSize, final String sortCode,
			final ShowMode showMode)
	{
		final PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(pageNumber);
		pageableData.setSort(sortCode);

		if (ShowMode.All == showMode)
		{
			pageableData.setPageSize(MAX_PAGE_LIMIT);
		}
		else
		{
			pageableData.setPageSize(pageSize);
		}
		return pageableData;
	}

	/**
	 * Special case, when total number of results > {@link #MAX_PAGE_LIMIT}
	 */
	protected boolean isShowAllAllowed(final SearchPageData<?> searchPageData)
	{
		return searchPageData.getPagination().getNumberOfPages() > 1
				&& searchPageData.getPagination().getTotalNumberOfResults() < MAX_PAGE_LIMIT;
	}

	protected void populateModel(final Model model, final SearchPageData<?> searchPageData, final ShowMode showMode)
	{
		final int numberPagesShown = getSiteConfigService().getInt(PAGINATION_NUMBER_OF_RESULTS_COUNT, 5);

		model.addAttribute("numberPagesShown", Integer.valueOf(numberPagesShown));
		model.addAttribute("searchPageData", searchPageData);
		model.addAttribute("isShowAllAllowed", calculateShowAll(searchPageData, showMode));
		model.addAttribute("isShowPageAllowed", calculateShowPaged(searchPageData, showMode));
	}


	protected Boolean calculateShowAll(final SearchPageData<?> searchPageData, final ShowMode showMode)
	{
		return Boolean.valueOf((showMode != ShowMode.All && //
				searchPageData.getPagination() != null
				&& searchPageData.getPagination().getTotalNumberOfResults() > searchPageData.getPagination().getPageSize())
				&& isShowAllAllowed(searchPageData));
	}

	protected Boolean calculateShowPaged(final SearchPageData<?> searchPageData, final ShowMode showMode)
	{
		return Boolean.valueOf(showMode == ShowMode.All && (searchPageData.getPagination().getNumberOfPages() > 1
				|| searchPageData.getPagination().getPageSize() == getMaxSearchPageSize()));
	}

	protected int getMaxSearchPageSize()
	{
		return MAX_PAGE_LIMIT;
	}

}
