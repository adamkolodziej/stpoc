
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZcrmtOrderHistDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZcrmtOrderHistDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderId" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="OrderItem" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ProcessType" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="OrderType" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="ProviderContractId" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ContractStartTerm" type="{urn:sap-com:document:sap:rfc:functions}decimal15.0"/>
 *         &lt;element name="ContractStartTermTimeZone" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
 *         &lt;element name="ContractEndTerm" type="{urn:sap-com:document:sap:rfc:functions}decimal15.0"/>
 *         &lt;element name="ContractEndTermTimeZone" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
 *         &lt;element name="Product" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="ProductDescription" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="OrderDate" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="OrderStatus" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="OneTimeNetValue" type="{urn:sap-com:document:sap:rfc:functions}curr15.2"/>
 *         &lt;element name="OneTimeTaxAmount" type="{urn:sap-com:document:sap:rfc:functions}curr15.2"/>
 *         &lt;element name="OneTimeGrossValue" type="{urn:sap-com:document:sap:rfc:functions}curr15.2"/>
 *         &lt;element name="RecurringNetValue" type="{urn:sap-com:document:sap:rfc:functions}curr15.2"/>
 *         &lt;element name="RecurringTaxAmount" type="{urn:sap-com:document:sap:rfc:functions}curr15.2"/>
 *         &lt;element name="RecurringGrossValue" type="{urn:sap-com:document:sap:rfc:functions}curr15.2"/>
 *         &lt;element name="RecurringDuration" type="{urn:sap-com:document:sap:rfc:functions}decimal13.0"/>
 *         &lt;element name="RecurringTimeUnit" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="SoldTo" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="SoldToAddress" type="{urn:sap-com:document:sap:rfc:functions}char80"/>
 *         &lt;element name="ShipTo" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ShipToAddress" type="{urn:sap-com:document:sap:rfc:functions}char80"/>
 *         &lt;element name="BillTo" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="BillToAddress" type="{urn:sap-com:document:sap:rfc:functions}char80"/>
 *         &lt;element name="Payer" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="PayerAddress" type="{urn:sap-com:document:sap:rfc:functions}char80"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZcrmtOrderHistDetails", propOrder = {
    "orderId",
    "orderItem",
    "processType",
    "orderType",
    "providerContractId",
    "contractStartTerm",
    "contractStartTermTimeZone",
    "contractEndTerm",
    "contractEndTermTimeZone",
    "product",
    "productDescription",
    "orderDate",
    "orderStatus",
    "oneTimeNetValue",
    "oneTimeTaxAmount",
    "oneTimeGrossValue",
    "recurringNetValue",
    "recurringTaxAmount",
    "recurringGrossValue",
    "recurringDuration",
    "recurringTimeUnit",
    "soldTo",
    "soldToAddress",
    "shipTo",
    "shipToAddress",
    "billTo",
    "billToAddress",
    "payer",
    "payerAddress"
})
public class ZcrmtOrderHistDetails {

    @XmlElement(name = "OrderId", required = true)
    protected String orderId;
    @XmlElement(name = "OrderItem", required = true)
    protected String orderItem;
    @XmlElement(name = "ProcessType", required = true)
    protected String processType;
    @XmlElement(name = "OrderType", required = true)
    protected String orderType;
    @XmlElement(name = "ProviderContractId", required = true)
    protected String providerContractId;
    @XmlElement(name = "ContractStartTerm", required = true)
    protected BigDecimal contractStartTerm;
    @XmlElement(name = "ContractStartTermTimeZone", required = true)
    protected String contractStartTermTimeZone;
    @XmlElement(name = "ContractEndTerm", required = true)
    protected BigDecimal contractEndTerm;
    @XmlElement(name = "ContractEndTermTimeZone", required = true)
    protected String contractEndTermTimeZone;
    @XmlElement(name = "Product", required = true)
    protected String product;
    @XmlElement(name = "ProductDescription", required = true)
    protected String productDescription;
    @XmlElement(name = "OrderDate", required = true)
    protected String orderDate;
    @XmlElement(name = "OrderStatus", required = true)
    protected String orderStatus;
    @XmlElement(name = "OneTimeNetValue", required = true)
    protected BigDecimal oneTimeNetValue;
    @XmlElement(name = "OneTimeTaxAmount", required = true)
    protected BigDecimal oneTimeTaxAmount;
    @XmlElement(name = "OneTimeGrossValue", required = true)
    protected BigDecimal oneTimeGrossValue;
    @XmlElement(name = "RecurringNetValue", required = true)
    protected BigDecimal recurringNetValue;
    @XmlElement(name = "RecurringTaxAmount", required = true)
    protected BigDecimal recurringTaxAmount;
    @XmlElement(name = "RecurringGrossValue", required = true)
    protected BigDecimal recurringGrossValue;
    @XmlElement(name = "RecurringDuration", required = true)
    protected BigDecimal recurringDuration;
    @XmlElement(name = "RecurringTimeUnit", required = true)
    protected String recurringTimeUnit;
    @XmlElement(name = "SoldTo", required = true)
    protected String soldTo;
    @XmlElement(name = "SoldToAddress", required = true)
    protected String soldToAddress;
    @XmlElement(name = "ShipTo", required = true)
    protected String shipTo;
    @XmlElement(name = "ShipToAddress", required = true)
    protected String shipToAddress;
    @XmlElement(name = "BillTo", required = true)
    protected String billTo;
    @XmlElement(name = "BillToAddress", required = true)
    protected String billToAddress;
    @XmlElement(name = "Payer", required = true)
    protected String payer;
    @XmlElement(name = "PayerAddress", required = true)
    protected String payerAddress;

    /**
     * Gets the value of the orderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderId(String value) {
        this.orderId = value;
    }

    /**
     * Gets the value of the orderItem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItem() {
        return orderItem;
    }

    /**
     * Sets the value of the orderItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItem(String value) {
        this.orderItem = value;
    }

    /**
     * Gets the value of the processType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessType() {
        return processType;
    }

    /**
     * Sets the value of the processType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessType(String value) {
        this.processType = value;
    }

    /**
     * Gets the value of the orderType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * Sets the value of the orderType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderType(String value) {
        this.orderType = value;
    }

    /**
     * Gets the value of the providerContractId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderContractId() {
        return providerContractId;
    }

    /**
     * Sets the value of the providerContractId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderContractId(String value) {
        this.providerContractId = value;
    }

    /**
     * Gets the value of the contractStartTerm property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getContractStartTerm() {
        return contractStartTerm;
    }

    /**
     * Sets the value of the contractStartTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setContractStartTerm(BigDecimal value) {
        this.contractStartTerm = value;
    }

    /**
     * Gets the value of the contractStartTermTimeZone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractStartTermTimeZone() {
        return contractStartTermTimeZone;
    }

    /**
     * Sets the value of the contractStartTermTimeZone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractStartTermTimeZone(String value) {
        this.contractStartTermTimeZone = value;
    }

    /**
     * Gets the value of the contractEndTerm property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getContractEndTerm() {
        return contractEndTerm;
    }

    /**
     * Sets the value of the contractEndTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setContractEndTerm(BigDecimal value) {
        this.contractEndTerm = value;
    }

    /**
     * Gets the value of the contractEndTermTimeZone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractEndTermTimeZone() {
        return contractEndTermTimeZone;
    }

    /**
     * Sets the value of the contractEndTermTimeZone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractEndTermTimeZone(String value) {
        this.contractEndTermTimeZone = value;
    }

    /**
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProduct(String value) {
        this.product = value;
    }

    /**
     * Gets the value of the productDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Sets the value of the productDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductDescription(String value) {
        this.productDescription = value;
    }

    /**
     * Gets the value of the orderDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the value of the orderDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderDate(String value) {
        this.orderDate = value;
    }

    /**
     * Gets the value of the orderStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets the value of the orderStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderStatus(String value) {
        this.orderStatus = value;
    }

    /**
     * Gets the value of the oneTimeNetValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOneTimeNetValue() {
        return oneTimeNetValue;
    }

    /**
     * Sets the value of the oneTimeNetValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOneTimeNetValue(BigDecimal value) {
        this.oneTimeNetValue = value;
    }

    /**
     * Gets the value of the oneTimeTaxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOneTimeTaxAmount() {
        return oneTimeTaxAmount;
    }

    /**
     * Sets the value of the oneTimeTaxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOneTimeTaxAmount(BigDecimal value) {
        this.oneTimeTaxAmount = value;
    }

    /**
     * Gets the value of the oneTimeGrossValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOneTimeGrossValue() {
        return oneTimeGrossValue;
    }

    /**
     * Sets the value of the oneTimeGrossValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOneTimeGrossValue(BigDecimal value) {
        this.oneTimeGrossValue = value;
    }

    /**
     * Gets the value of the recurringNetValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRecurringNetValue() {
        return recurringNetValue;
    }

    /**
     * Sets the value of the recurringNetValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRecurringNetValue(BigDecimal value) {
        this.recurringNetValue = value;
    }

    /**
     * Gets the value of the recurringTaxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRecurringTaxAmount() {
        return recurringTaxAmount;
    }

    /**
     * Sets the value of the recurringTaxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRecurringTaxAmount(BigDecimal value) {
        this.recurringTaxAmount = value;
    }

    /**
     * Gets the value of the recurringGrossValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRecurringGrossValue() {
        return recurringGrossValue;
    }

    /**
     * Sets the value of the recurringGrossValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRecurringGrossValue(BigDecimal value) {
        this.recurringGrossValue = value;
    }

    /**
     * Gets the value of the recurringDuration property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRecurringDuration() {
        return recurringDuration;
    }

    /**
     * Sets the value of the recurringDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRecurringDuration(BigDecimal value) {
        this.recurringDuration = value;
    }

    /**
     * Gets the value of the recurringTimeUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecurringTimeUnit() {
        return recurringTimeUnit;
    }

    /**
     * Sets the value of the recurringTimeUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecurringTimeUnit(String value) {
        this.recurringTimeUnit = value;
    }

    /**
     * Gets the value of the soldTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoldTo() {
        return soldTo;
    }

    /**
     * Sets the value of the soldTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoldTo(String value) {
        this.soldTo = value;
    }

    /**
     * Gets the value of the soldToAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoldToAddress() {
        return soldToAddress;
    }

    /**
     * Sets the value of the soldToAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoldToAddress(String value) {
        this.soldToAddress = value;
    }

    /**
     * Gets the value of the shipTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipTo() {
        return shipTo;
    }

    /**
     * Sets the value of the shipTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipTo(String value) {
        this.shipTo = value;
    }

    /**
     * Gets the value of the shipToAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipToAddress() {
        return shipToAddress;
    }

    /**
     * Sets the value of the shipToAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipToAddress(String value) {
        this.shipToAddress = value;
    }

    /**
     * Gets the value of the billTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillTo() {
        return billTo;
    }

    /**
     * Sets the value of the billTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillTo(String value) {
        this.billTo = value;
    }

    /**
     * Gets the value of the billToAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillToAddress() {
        return billToAddress;
    }

    /**
     * Sets the value of the billToAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillToAddress(String value) {
        this.billToAddress = value;
    }

    /**
     * Gets the value of the payer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayer() {
        return payer;
    }

    /**
     * Sets the value of the payer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayer(String value) {
        this.payer = value;
    }

    /**
     * Gets the value of the payerAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayerAddress() {
        return payerAddress;
    }

    /**
     * Sets the value of the payerAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayerAddress(String value) {
        this.payerAddress = value;
    }

}
