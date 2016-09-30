package com.hybris.showcase.reports;

import org.apache.commons.lang.time.DateUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Factory bean used in Jasper Studio to build reports
 *
 * NOTE: This class is not used in hybris but is helpful when previewing reports as it helps to see some data
 *
 * Created by i323354 on 03/11/15.
 */
public class MockInvoiceExportDataFactory {

    public static InvoiceExportData[] load() {
        return new  InvoiceExportData[]{sampleInvoice()};
    }

    public static InvoiceExportData sampleInvoice(){
        InvoiceExportData invoice=  new InvoiceExportData();

        //customer info
        invoice.setCustomerName("John Doe");
        invoice.setCustomerCode("#007");
        invoice.setCustomerCountry("USA");
        invoice.setCustomerCompanyName("hybris");
        invoice.setCustomerBillingAddressLine1("Str Tipografilor nr 15");
        invoice.setCustomerBillingAddressLine2("S-Park building, 2nd floor");

        //amount
        invoice.setAmountDue(BigDecimal.valueOf(100));
        invoice.setAmountPaid(BigDecimal.valueOf(50));
        invoice.setAmountRemaining(BigDecimal.valueOf(25));
        invoice.setCurrencyIso("EUR");

        //invoice dates
        Date today = new Date();
        Date billingStartDate = DateUtils.truncate(today, Calendar.DAY_OF_MONTH);
        invoice.setBillingStartDate(billingStartDate);
        invoice.setBillingEndDate(DateUtils.addDays(billingStartDate, 30));
        invoice.setInvoiceDate(today);
        invoice.setDueDate(DateUtils.addDays(today, 15));

        invoice.setInvoiceNumber("test");
        invoice.setPaymentStatus("Awaiting payment");

        return invoice;
    }

}
