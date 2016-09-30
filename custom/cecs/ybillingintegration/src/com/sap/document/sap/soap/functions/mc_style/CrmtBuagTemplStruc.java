
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrmtBuagTemplStruc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrmtBuagTemplStruc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BuagText" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="BuagDefault" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="BankInc" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="BankOutg" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="CreditCardInc" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
 *         &lt;element name="CreditCardOutg" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
 *         &lt;element name="AddrNo" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="AddrDr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="AddrPy" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="AddrPr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="AddrIr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="AddrTax" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="TaxCategory" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="TaxCode" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="PayerBuagId" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrmtBuagTemplStruc", propOrder = {
    "buagText",
    "buagDefault",
    "bankInc",
    "bankOutg",
    "creditCardInc",
    "creditCardOutg",
    "addrNo",
    "addrDr",
    "addrPy",
    "addrPr",
    "addrIr",
    "addrTax",
    "taxCategory",
    "taxCode",
    "payerBuagId"
})
public class CrmtBuagTemplStruc {

    @XmlElement(name = "BuagText", required = true)
    protected String buagText;
    @XmlElement(name = "BuagDefault", required = true)
    protected String buagDefault;
    @XmlElement(name = "BankInc", required = true)
    protected String bankInc;
    @XmlElement(name = "BankOutg", required = true)
    protected String bankOutg;
    @XmlElement(name = "CreditCardInc", required = true)
    protected String creditCardInc;
    @XmlElement(name = "CreditCardOutg", required = true)
    protected String creditCardOutg;
    @XmlElement(name = "AddrNo", required = true)
    protected String addrNo;
    @XmlElement(name = "AddrDr", required = true)
    protected String addrDr;
    @XmlElement(name = "AddrPy", required = true)
    protected String addrPy;
    @XmlElement(name = "AddrPr", required = true)
    protected String addrPr;
    @XmlElement(name = "AddrIr", required = true)
    protected String addrIr;
    @XmlElement(name = "AddrTax", required = true)
    protected String addrTax;
    @XmlElement(name = "TaxCategory", required = true)
    protected String taxCategory;
    @XmlElement(name = "TaxCode", required = true)
    protected String taxCode;
    @XmlElement(name = "PayerBuagId", required = true)
    protected String payerBuagId;

    /**
     * Gets the value of the buagText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuagText() {
        return buagText;
    }

    /**
     * Sets the value of the buagText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuagText(String value) {
        this.buagText = value;
    }

    /**
     * Gets the value of the buagDefault property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuagDefault() {
        return buagDefault;
    }

    /**
     * Sets the value of the buagDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuagDefault(String value) {
        this.buagDefault = value;
    }

    /**
     * Gets the value of the bankInc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankInc() {
        return bankInc;
    }

    /**
     * Sets the value of the bankInc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankInc(String value) {
        this.bankInc = value;
    }

    /**
     * Gets the value of the bankOutg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankOutg() {
        return bankOutg;
    }

    /**
     * Sets the value of the bankOutg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankOutg(String value) {
        this.bankOutg = value;
    }

    /**
     * Gets the value of the creditCardInc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardInc() {
        return creditCardInc;
    }

    /**
     * Sets the value of the creditCardInc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardInc(String value) {
        this.creditCardInc = value;
    }

    /**
     * Gets the value of the creditCardOutg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardOutg() {
        return creditCardOutg;
    }

    /**
     * Sets the value of the creditCardOutg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardOutg(String value) {
        this.creditCardOutg = value;
    }

    /**
     * Gets the value of the addrNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrNo() {
        return addrNo;
    }

    /**
     * Sets the value of the addrNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrNo(String value) {
        this.addrNo = value;
    }

    /**
     * Gets the value of the addrDr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrDr() {
        return addrDr;
    }

    /**
     * Sets the value of the addrDr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrDr(String value) {
        this.addrDr = value;
    }

    /**
     * Gets the value of the addrPy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrPy() {
        return addrPy;
    }

    /**
     * Sets the value of the addrPy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrPy(String value) {
        this.addrPy = value;
    }

    /**
     * Gets the value of the addrPr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrPr() {
        return addrPr;
    }

    /**
     * Sets the value of the addrPr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrPr(String value) {
        this.addrPr = value;
    }

    /**
     * Gets the value of the addrIr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrIr() {
        return addrIr;
    }

    /**
     * Sets the value of the addrIr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrIr(String value) {
        this.addrIr = value;
    }

    /**
     * Gets the value of the addrTax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrTax() {
        return addrTax;
    }

    /**
     * Sets the value of the addrTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrTax(String value) {
        this.addrTax = value;
    }

    /**
     * Gets the value of the taxCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxCategory() {
        return taxCategory;
    }

    /**
     * Sets the value of the taxCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxCategory(String value) {
        this.taxCategory = value;
    }

    /**
     * Gets the value of the taxCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxCode() {
        return taxCode;
    }

    /**
     * Sets the value of the taxCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxCode(String value) {
        this.taxCode = value;
    }

    /**
     * Gets the value of the payerBuagId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayerBuagId() {
        return payerBuagId;
    }

    /**
     * Sets the value of the payerBuagId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayerBuagId(String value) {
        this.payerBuagId = value;
    }

}
