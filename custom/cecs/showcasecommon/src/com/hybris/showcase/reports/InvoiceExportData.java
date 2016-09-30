package com.hybris.showcase.reports;

import de.hybris.platform.commercefacades.user.data.CountryData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by i323354 on 03/11/15.
 */
public class InvoiceExportData {

    String invoiceNumber;

    String customerName;
    String customerCode;
    String customerCompanyName;
    String customerTown;
    String customerCountry;
    String customerBillingAddressLine1;

    String customerBillingAddressLine2;
    Date invoiceDate;
    Date dueDate;
    Date billingStartDate;

    Date billingEndDate;
    BigDecimal amountDue;
    BigDecimal amountRemaining;
    BigDecimal amountPaid;

    String currencyIso;
    String paymentStatus;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customeName) {
        this.customerName = customeName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerBillingAddressLine1() {
        return customerBillingAddressLine1;
    }

    public void setCustomerBillingAddressLine1(String customerBillingAddressLine1) {
        this.customerBillingAddressLine1 = customerBillingAddressLine1;
    }

    public String getCustomerBillingAddressLine2() {
        return customerBillingAddressLine2;
    }

    public void setCustomerBillingAddressLine2(String customerBillingAddressLine2) {
        this.customerBillingAddressLine2 = customerBillingAddressLine2;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getBillingStartDate() {
        return billingStartDate;
    }

    public void setBillingStartDate(Date billingStartDate) {
        this.billingStartDate = billingStartDate;
    }

    public Date getBillingEndDate() {
        return billingEndDate;
    }

    public void setBillingEndDate(Date billingEndDate) {
        this.billingEndDate = billingEndDate;
    }

    public String getCurrencyIso() {
        return currencyIso;
    }

    public void setCurrencyIso(String currencyIso) {
        this.currencyIso = currencyIso;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(BigDecimal amountDue) {
        this.amountDue = amountDue;
    }

    public BigDecimal getAmountRemaining() {
        return amountRemaining;
    }

    public void setAmountRemaining(BigDecimal amountRemaining) {
        this.amountRemaining = amountRemaining;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setCustomerCompanyName(String customerCompanyName) {
        this.customerCompanyName = customerCompanyName;
    }

    public void setCustomerTown(String customerTown) {
        this.customerTown = customerTown;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerCompanyName() {
        return customerCompanyName;
    }

    public String getCustomerTown() {
        return customerTown;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }
}
