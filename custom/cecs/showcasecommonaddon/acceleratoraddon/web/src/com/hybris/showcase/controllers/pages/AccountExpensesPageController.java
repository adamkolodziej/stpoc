package com.hybris.showcase.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController.ShowMode;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hybris.showcase.data.CurrentExpensesData;
import com.hybris.showcase.facades.ExpenseFacade;


/**
 * @author Sebastian Weiner
 *
 */
@Controller
@RequestMapping("/my-account")
public class AccountExpensesPageController extends AbstractAddOnSearchPageController
{

	private static final String ACCOUNT_CURRENT_ExpenseS_PAGE_ID = "accountExpenses";

	@Resource
	private ExpenseFacade expenseFacade;

	@Resource
	private PriceDataFactory priceDataFactory;

	@Resource
	private CommonI18NService commonI18NService;

	@Resource(name = "accountBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;

	public static final int MAX_PAGE_LIMIT = 100; // should be configured

	@RequestMapping(value = "/expenses", method = RequestMethod.GET)
	@RequireHardLogIn
	public String showExpenses(@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "sort", required = false) final String sortCode,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode, final Model model,
			final HttpServletRequest request) throws CMSItemNotFoundException
	{
		final List<CurrentExpensesData> results = new ArrayList<>();

		final PageableData pageableData = createPageableData(page, getSearchPageSize(), sortCode, showMode);
		final SearchPageData<CurrentExpensesData> searchPageData = expenseFacade.getExpensesForCurrentMonth(null, pageableData);

		if (searchPageData != null)
		{
			results.addAll(searchPageData.getResults());
		}
		final PriceData total = calculateTotalAmount(results, searchPageData);

		populateModel(model, searchPageData, showMode);
		storeCmsPageInModel(model, getContentPageForLabelOrId(ACCOUNT_CURRENT_ExpenseS_PAGE_ID));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ACCOUNT_CURRENT_ExpenseS_PAGE_ID));

		model.addAttribute("results", results);
		model.addAttribute("total", total);
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.expenses"));

		return getViewForPage(model);
	}

	private PriceData calculateTotalAmount(final List<CurrentExpensesData> results,
			final SearchPageData<CurrentExpensesData> dataList)
	{
		BigDecimal totalPrice = BigDecimal.valueOf(0l);
		if (dataList != null)
		{
			for (final CurrentExpensesData result : results)
			{
				totalPrice = totalPrice.add(result.getAmount().getValue());
			}
		}

		final PriceData total = getPriceDataFactory().create(PriceDataType.BUY, totalPrice,
				getCommonI18NService().getCurrentCurrency());
		return total;
	}

	protected int getSearchPageSize()
	{
		return getSiteConfigService().getInt("storefront.search.pageSize", 0);
	}


	public PriceDataFactory getPriceDataFactory()
	{
		return priceDataFactory;
	}

	public void setPriceDataFactory(final PriceDataFactory priceDataFactory)
	{
		this.priceDataFactory = priceDataFactory;
	}

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}
}
