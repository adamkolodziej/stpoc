package com.hybris.showcase.controllers.cms;

import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.commercefacades.product.data.PriceData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.showcase.controllers.ShowcasecommonaddonControllerConstants;
import com.hybris.showcase.data.InvoiceData;
import com.hybris.showcase.facades.ExpenseFacade;
import com.hybris.showcase.facades.InvoiceFacade;
import com.hybris.showcase.model.DetailAccountBodyBillingSubComponentModel;


@Controller("DetailAccountBodyBillingSubComponentController")
@RequestMapping(value = ShowcasecommonaddonControllerConstants.DetailAccountBodyBillingSubComponentController)
public class DetailAccountBodyBillingSubComponentController
		extends AbstractCMSAddOnComponentController<DetailAccountBodyBillingSubComponentModel>
{
	protected static final Logger LOG = Logger.getLogger(DetailAccountBodyBillingSubComponentController.class);

	@Resource
	private ExpenseFacade expenseFacade;

	@Resource
	private InvoiceFacade invoiceFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final DetailAccountBodyBillingSubComponentModel component)
	{
		final InvoiceData totalBilling = expenseFacade.getCurrentPeriodExpenseBalance();
		final PriceData invoiceBalance = invoiceFacade.getInvoiceBalanceForCurrentUser();

		model.addAttribute("totalBilling", totalBilling);
		model.addAttribute("invoiceBalance", invoiceBalance);
	}


}
