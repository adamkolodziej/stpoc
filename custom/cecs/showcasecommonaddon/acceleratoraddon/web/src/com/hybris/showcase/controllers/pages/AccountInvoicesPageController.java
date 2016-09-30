package com.hybris.showcase.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController.ShowMode;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hybris.showcase.data.InvoiceData;
import com.hybris.showcase.facades.ExpenseFacade;
import com.hybris.showcase.facades.InvoiceFacade;


/**
 * @author I307113
 *
 */
@Controller
@RequestMapping("/my-account")
public class AccountInvoicesPageController extends AbstractAddOnSearchPageController
{
	private static final String INVOICES_CMS_PAGE = "invoices";
	private static final String INVOICE_DETAILS_CMS_PAGE = "invoice-details";

	@Autowired
	private InvoiceFacade invoiceFacade;

	@Autowired
	ExpenseFacade expenseFacade;

	@Resource(name = "accountBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;

	@Resource(name = "themeSource")
	private MessageSource themeSource;

	private PriceDataFactory priceDataFactory;

	@RequestMapping(value = "/invoices", method = RequestMethod.GET)
	@RequireHardLogIn
	public String getInvoices(@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "sort", required = false) final String sortCode,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode, final Model model)
					throws CMSItemNotFoundException
	{
		final List<InvoiceData> invoices = new ArrayList<>();
		final PageableData pageableData = createPageableData(page, getSearchPageSize(), sortCode, showMode);
		final SearchPageData<InvoiceData> searchPageData = invoiceFacade.getInvoicesForCurrentUser(null, pageableData);

		if (searchPageData != null)
		{
			invoices.addAll(searchPageData.getResults());
		}
		populateModel(model, searchPageData, showMode);

		storeCmsPageInModel(model, getContentPageForLabelOrId(INVOICES_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(INVOICES_CMS_PAGE));
		model.addAttribute("metaRobots", "noindex,nofollow");
		// here need to be changed mocked invoice to invoice from yBilling
		model.addAttribute("ybillingInvoices", invoices);
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.invoices"));

		return getViewForPage(model);
	}

	@RequestMapping(value = "/invoices/{invoiceNo}", method = RequestMethod.GET)
	@RequireHardLogIn
	public String getInvoiceDetails(@PathVariable(value = "invoiceNo") final String invoiceNumber, final Model model)
			throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(INVOICE_DETAILS_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(INVOICE_DETAILS_CMS_PAGE));
		model.addAttribute("metaRobots", "noindex,nofollow");
		// here need to be changed mocked invoice to invoice from yBilling
		model.addAttribute("invoice", invoiceFacade.getInvoiceDetails(invoiceNumber));
		model.addAttribute("expenses", expenseFacade.getExpensesForInvoice(invoiceNumber));

		final List<Breadcrumb> breadcrumbs = accountBreadcrumbBuilder.getBreadcrumbs(null);
		breadcrumbs.add(new Breadcrumb("/my-account/invoices",
				themeSource.getMessage("text.account.invoices", null, getI18nService().getCurrentLocale()), null));
		breadcrumbs.add(new Breadcrumb("#", themeSource.getMessage("text.account.invoice.details", new Object[]
		{ invoiceNumber }, getI18nService().getCurrentLocale()), null));
		model.addAttribute("breadcrumbs", breadcrumbs);

		return getViewForPage(model);
	}

	protected int getSearchPageSize()
	{
		return getSiteConfigService().getInt("storefront.search.pageSize", 0);
	}


	public PriceDataFactory getPriceDataFactory()
	{
		return priceDataFactory;
	}

	@Autowired
	public void setPriceDataFactory(final PriceDataFactory priceDataFactory)
	{
		this.priceDataFactory = priceDataFactory;
	}

}
