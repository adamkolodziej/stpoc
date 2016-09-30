package com.hybris.showcase.reports;

import de.hybris.platform.converters.impl.AbstractConverter;
import de.hybris.platform.enumeration.EnumerationService;

import javax.annotation.Resource;

import com.hybris.showcase.data.InvoiceData;


/**
 * Created by i323354 on 07/11/15.
 */
public class InvoiceExportDataConverter extends AbstractConverter<InvoiceData, InvoiceExportData>
{

	@Resource(name = "enumerationService")
	private EnumerationService enumerationService;


	public InvoiceExportDataConverter()
	{
		super.setTargetClass(InvoiceExportData.class);
	}

	@Override
	public void populate(final InvoiceData source, final InvoiceExportData target)
	{
		target.setInvoiceNumber(source.getInvoiceNumber());

		if (source.getPaymentStatus() != null)
		{
			target.setPaymentStatus(enumerationService.getEnumerationName(source.getPaymentStatus()));
		}

		//dates
		target.setInvoiceDate(source.getInvoiceDate());
		target.setDueDate(source.getDueDate());
		target.setBillingStartDate(source.getBillingPeriodStart());
		target.setBillingEndDate(source.getBillingPeriodEnd());

		//amount
		if (source.getAmountDue() != null)
		{
			target.setAmountDue(source.getAmountDue().getValue());
			target.setCurrencyIso(source.getAmountDue().getCurrencyIso() + " ");
		}

		if (source.getAmountPaid() != null)
		{
			target.setAmountPaid(source.getAmountPaid().getValue());
		}

		if (source.getAmountRemaining() != null)
		{
			target.setAmountRemaining(source.getAmountRemaining().getValue());
		}
	}
}
