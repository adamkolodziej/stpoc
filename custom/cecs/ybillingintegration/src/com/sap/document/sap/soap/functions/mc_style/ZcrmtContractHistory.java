
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZcrmtContractHistory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZcrmtContractHistory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ContractNumber" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="ContractVersion" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ContractStartDate" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="ContractEndDate" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="ContractVersionStartDate" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="ContractVersionEndDate" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="ActivationStatus" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="OrderId" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ProcessType" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="OrderType" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="ProviderContractId" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ContractStartTerm" type="{urn:sap-com:document:sap:rfc:functions}decimal15.0"/>
 *         &lt;element name="ContractStartTermTimeZone" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
 *         &lt;element name="ContractEndTerm" type="{urn:sap-com:document:sap:rfc:functions}decimal15.0"/>
 *         &lt;element name="ContractEndTermTimeZone" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
 *         &lt;element name="OrderedProduct" type="{urn:sap-com:document:sap:rfc:functions}char54"/>
 *         &lt;element name="OrderedProductDescription" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="OrderDate" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="ContractItemStatus" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZcrmtContractHistory", propOrder = {
    "contractNumber",
    "contractVersion",
    "contractStartDate",
    "contractEndDate",
    "contractVersionStartDate",
    "contractVersionEndDate",
    "activationStatus",
    "orderId",
    "processType",
    "orderType",
    "providerContractId",
    "contractStartTerm",
    "contractStartTermTimeZone",
    "contractEndTerm",
    "contractEndTermTimeZone",
    "orderedProduct",
    "orderedProductDescription",
    "orderDate",
    "contractItemStatus"
})
public class ZcrmtContractHistory {

    @XmlElement(name = "ContractNumber", required = true)
    protected String contractNumber;
    @XmlElement(name = "ContractVersion", required = true)
    protected String contractVersion;
    @XmlElement(name = "ContractStartDate", required = true)
    protected String contractStartDate;
    @XmlElement(name = "ContractEndDate", required = true)
    protected String contractEndDate;
    @XmlElement(name = "ContractVersionStartDate", required = true)
    protected String contractVersionStartDate;
    @XmlElement(name = "ContractVersionEndDate", required = true)
    protected String contractVersionEndDate;
    @XmlElement(name = "ActivationStatus", required = true)
    protected String activationStatus;
    @XmlElement(name = "OrderId", required = true)
    protected String orderId;
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
    @XmlElement(name = "OrderedProduct", required = true)
    protected String orderedProduct;
    @XmlElement(name = "OrderedProductDescription", required = true)
    protected String orderedProductDescription;
    @XmlElement(name = "OrderDate", required = true)
    protected String orderDate;
    @XmlElement(name = "ContractItemStatus", required = true)
    protected String contractItemStatus;

    /**
     * Gets the value of the contractNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractNumber() {
        return contractNumber;
    }

    /**
     * Sets the value of the contractNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractNumber(String value) {
        this.contractNumber = value;
    }

    /**
     * Gets the value of the contractVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractVersion() {
        return contractVersion;
    }

    /**
     * Sets the value of the contractVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractVersion(String value) {
        this.contractVersion = value;
    }

    /**
     * Gets the value of the contractStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractStartDate() {
        return contractStartDate;
    }

    /**
     * Sets the value of the contractStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractStartDate(String value) {
        this.contractStartDate = value;
    }

    /**
     * Gets the value of the contractEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractEndDate() {
        return contractEndDate;
    }

    /**
     * Sets the value of the contractEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractEndDate(String value) {
        this.contractEndDate = value;
    }

    /**
     * Gets the value of the contractVersionStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractVersionStartDate() {
        return contractVersionStartDate;
    }

    /**
     * Sets the value of the contractVersionStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractVersionStartDate(String value) {
        this.contractVersionStartDate = value;
    }

    /**
     * Gets the value of the contractVersionEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractVersionEndDate() {
        return contractVersionEndDate;
    }

    /**
     * Sets the value of the contractVersionEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractVersionEndDate(String value) {
        this.contractVersionEndDate = value;
    }

    /**
     * Gets the value of the activationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivationStatus() {
        return activationStatus;
    }

    /**
     * Sets the value of the activationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivationStatus(String value) {
        this.activationStatus = value;
    }

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
     * Gets the value of the orderedProduct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderedProduct() {
        return orderedProduct;
    }

    /**
     * Sets the value of the orderedProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderedProduct(String value) {
        this.orderedProduct = value;
    }

    /**
     * Gets the value of the orderedProductDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderedProductDescription() {
        return orderedProductDescription;
    }

    /**
     * Sets the value of the orderedProductDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderedProductDescription(String value) {
        this.orderedProductDescription = value;
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
     * Gets the value of the contractItemStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractItemStatus() {
        return contractItemStatus;
    }

    /**
     * Sets the value of the contractItemStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractItemStatus(String value) {
        this.contractItemStatus = value;
    }

}
