
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapibus1006030Pricing complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapibus1006030Pricing">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CustPricProc" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="ExchangeType" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Currency" type="{urn:sap-com:document:sap:rfc:functions}cuky5"/>
 *         &lt;element name="CurrencyIso" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="PaymentTerms" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="PriceGroup" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="PriceListType" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="CustomerGroup" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="BillplanProc" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="EtaxHandType" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="EtaxSource" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="AccountAsgngrp" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="RebateRelevant" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="DirectInvoice" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="SplitByCond" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapibus1006030Pricing", propOrder = {
    "custPricProc",
    "exchangeType",
    "currency",
    "currencyIso",
    "paymentTerms",
    "priceGroup",
    "priceListType",
    "customerGroup",
    "billplanProc",
    "etaxHandType",
    "etaxSource",
    "accountAsgngrp",
    "rebateRelevant",
    "directInvoice",
    "splitByCond"
})
public class Bapibus1006030Pricing {

    @XmlElement(name = "CustPricProc", required = true)
    protected String custPricProc;
    @XmlElement(name = "ExchangeType", required = true)
    protected String exchangeType;
    @XmlElement(name = "Currency", required = true)
    protected String currency;
    @XmlElement(name = "CurrencyIso", required = true)
    protected String currencyIso;
    @XmlElement(name = "PaymentTerms", required = true)
    protected String paymentTerms;
    @XmlElement(name = "PriceGroup", required = true)
    protected String priceGroup;
    @XmlElement(name = "PriceListType", required = true)
    protected String priceListType;
    @XmlElement(name = "CustomerGroup", required = true)
    protected String customerGroup;
    @XmlElement(name = "BillplanProc", required = true)
    protected String billplanProc;
    @XmlElement(name = "EtaxHandType", required = true)
    protected String etaxHandType;
    @XmlElement(name = "EtaxSource", required = true)
    protected String etaxSource;
    @XmlElement(name = "AccountAsgngrp", required = true)
    protected String accountAsgngrp;
    @XmlElement(name = "RebateRelevant", required = true)
    protected String rebateRelevant;
    @XmlElement(name = "DirectInvoice", required = true)
    protected String directInvoice;
    @XmlElement(name = "SplitByCond", required = true)
    protected String splitByCond;

    /**
     * Gets the value of the custPricProc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustPricProc() {
        return custPricProc;
    }

    /**
     * Sets the value of the custPricProc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustPricProc(String value) {
        this.custPricProc = value;
    }

    /**
     * Gets the value of the exchangeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExchangeType() {
        return exchangeType;
    }

    /**
     * Sets the value of the exchangeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExchangeType(String value) {
        this.exchangeType = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the currencyIso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyIso() {
        return currencyIso;
    }

    /**
     * Sets the value of the currencyIso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyIso(String value) {
        this.currencyIso = value;
    }

    /**
     * Gets the value of the paymentTerms property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentTerms() {
        return paymentTerms;
    }

    /**
     * Sets the value of the paymentTerms property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentTerms(String value) {
        this.paymentTerms = value;
    }

    /**
     * Gets the value of the priceGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceGroup() {
        return priceGroup;
    }

    /**
     * Sets the value of the priceGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceGroup(String value) {
        this.priceGroup = value;
    }

    /**
     * Gets the value of the priceListType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceListType() {
        return priceListType;
    }

    /**
     * Sets the value of the priceListType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceListType(String value) {
        this.priceListType = value;
    }

    /**
     * Gets the value of the customerGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerGroup() {
        return customerGroup;
    }

    /**
     * Sets the value of the customerGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerGroup(String value) {
        this.customerGroup = value;
    }

    /**
     * Gets the value of the billplanProc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillplanProc() {
        return billplanProc;
    }

    /**
     * Sets the value of the billplanProc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillplanProc(String value) {
        this.billplanProc = value;
    }

    /**
     * Gets the value of the etaxHandType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEtaxHandType() {
        return etaxHandType;
    }

    /**
     * Sets the value of the etaxHandType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEtaxHandType(String value) {
        this.etaxHandType = value;
    }

    /**
     * Gets the value of the etaxSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEtaxSource() {
        return etaxSource;
    }

    /**
     * Sets the value of the etaxSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEtaxSource(String value) {
        this.etaxSource = value;
    }

    /**
     * Gets the value of the accountAsgngrp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountAsgngrp() {
        return accountAsgngrp;
    }

    /**
     * Sets the value of the accountAsgngrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountAsgngrp(String value) {
        this.accountAsgngrp = value;
    }

    /**
     * Gets the value of the rebateRelevant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRebateRelevant() {
        return rebateRelevant;
    }

    /**
     * Sets the value of the rebateRelevant property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRebateRelevant(String value) {
        this.rebateRelevant = value;
    }

    /**
     * Gets the value of the directInvoice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectInvoice() {
        return directInvoice;
    }

    /**
     * Sets the value of the directInvoice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectInvoice(String value) {
        this.directInvoice = value;
    }

    /**
     * Gets the value of the splitByCond property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplitByCond() {
        return splitByCond;
    }

    /**
     * Sets the value of the splitByCond property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplitByCond(String value) {
        this.splitByCond = value;
    }

}
