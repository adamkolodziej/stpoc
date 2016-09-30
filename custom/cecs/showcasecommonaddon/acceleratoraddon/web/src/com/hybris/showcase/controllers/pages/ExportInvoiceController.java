package com.hybris.showcase.controllers.pages;

import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CustomerData;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hybris.showcase.data.CurrentExpensesData;
import com.hybris.showcase.data.InvoiceData;
import com.hybris.showcase.facades.ExpenseFacade;
import com.hybris.showcase.facades.InvoiceFacade;
import com.hybris.showcase.reports.ExpenseExportData;
import com.hybris.showcase.reports.ExpenseExportDataConverter;
import com.hybris.showcase.reports.InvoiceExportData;
import com.hybris.showcase.reports.InvoiceExportDataConverter;
import com.hybris.showcase.reports.ReportsManager;

import net.sf.jasperreports.engine.JRException;


/**
 * Created by i323354 on 05/11/15.
 */
@Controller
@RequestMapping(ExportInvoiceController.EXPORT_INVOICE_REQUESTPATH)
public class ExportInvoiceController
{


	private static final Logger LOG = Logger.getLogger(ExportInvoiceController.class);

	public static final String EXPORT_INVOICE_REQUESTPATH = "/my-account/exportInvoice";
	public static final String RESOURE_EXPORT_INVOICE_REPORT_JRXML = "reports/ExportInvoiceReport.jrxml";

	@Resource
	private InvoiceFacade invoiceFacade;

	@Resource
	private ExpenseFacade expenseFacade;

	@Resource
	private CustomerFacade customerFacade;

	@Resource
	private InvoiceExportDataConverter invoiceExportDataConverter;

	@Resource
	ExpenseExportDataConverter expenseExportDataConverter;

	@Resource
	private ConfigurationService configurationService;


	@RequestMapping(produces = "application/pdf", method =
	{ RequestMethod.POST, RequestMethod.GET })
	public ResponseEntity<byte[]> exportExpenses(@ModelAttribute final ExportInvoiceForm form, final BindingResult bindingResult,
			final Model model, final RedirectAttributes redirectAttributes) throws JRException, IOException
	{


		//invoice
		final InvoiceExportData invoice = loadInvoice(form.getInvoiceNumber());
		final String logoPath = configurationService.getConfiguration().getString("reports.logo.path");
		final BufferedImage image = ImageIO.read(getClass().getResource(logoPath));

		final Map parameters = new HashMap();
		parameters.put("INVOICE", invoice);
		parameters.put("LOGO", image);

		//expenses
		final List<ExpenseExportData> invoiceExpenses = loadInvoiceExpenses(form.getInvoiceNumber());

		LOG.debug("loaded invoice and expenses list");

		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		ReportsManager.executeToPdfStream(parameters, RESOURE_EXPORT_INVOICE_REPORT_JRXML, invoiceExpenses, out);
		final byte[] pdfContents = out.toByteArray();

		Date invDate = invoice.getInvoiceDate();
		final String filename = String.format("invoice_%s_%td_%tm_%ty.pdf", form.getInvoiceNumber(), invDate, invDate, invDate);

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.add("content-disposition", "inline;filename=" + filename);

		LOG.info("exported");

		final ResponseEntity<byte[]> response = new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
		return response;
	}

	public InvoiceExportData loadInvoice(final String invoiceNumber)
	{
		final InvoiceData invoiceData = invoiceFacade.getInvoiceDetails(invoiceNumber);
		if (invoiceData != null)
		{

			final InvoiceExportData invoice = invoiceExportDataConverter.convert(invoiceData);
			loadCustomerData(invoice);
			return invoice;
		}

		return null;
	}


	public List<ExpenseExportData> loadInvoiceExpenses(final String invoiceNumber)
	{
		final List<CurrentExpensesData> expenseDataList = expenseFacade.getExpensesForInvoice(invoiceNumber);
		final List<ExpenseExportData> expenseExportDataList = new ArrayList<>();

		if (expenseDataList != null && expenseDataList.size() > 0)
		{
			for (final CurrentExpensesData expense : expenseDataList)
			{
				final ExpenseExportData exportData = expenseExportDataConverter.convert(expense);
				expenseExportDataList.add(exportData);
			}
		}

		return expenseExportDataList;

	}


	public void loadCustomerData(final InvoiceExportData invoice)
	{
		final CustomerData customerData = customerFacade.getCurrentCustomer();

		invoice.setCustomerCode(customerData.getDisplayUid());
		invoice.setCustomerName(customerData.getFirstName() + " " + customerData.getLastName());

		if (customerData.getDefaultBillingAddress() != null)
		{
			final AddressData billing = customerData.getDefaultBillingAddress();
			invoice.setCustomerBillingAddressLine1(billing.getLine1());
			invoice.setCustomerBillingAddressLine2(billing.getLine2());
			invoice.setCustomerCompanyName(billing.getCompanyName());
			invoice.setCustomerTown(billing.getTown());
			if (billing.getCountry() != null)
			{
				invoice.setCustomerCountry(billing.getCountry().getName());
			}
		}
	}

	/**
	 * Form bean used for expenses
	 */
	public static class ExportInvoiceForm
	{
		String invoiceNumber;

		public String getInvoiceNumber()
		{
			return invoiceNumber;
		}

		public void setInvoiceNumber(final String invoiceNumber)
		{
			this.invoiceNumber = invoiceNumber;
		}
	}



}


