
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapibus1006130SpecData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapibus1006130SpecData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BuagText" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="BuagDefault" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="BuagClass" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="AddrNo" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="RefNumber" type="{urn:sap-com:document:sap:rfc:functions}char25"/>
 *         &lt;element name="BankInc" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="BankOutg" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="CreditCardInc" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
 *         &lt;element name="CreditCardOutg" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
 *         &lt;element name="PayerBuagId" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="AddrDr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="AddrPy" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="AddrPr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="AddrIr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="AddrTax" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="TaxCategory" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="TaxCode" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="DirectDebitAmount" type="{urn:sap-com:document:sap:rfc:functions}decimal23.4"/>
 *         &lt;element name="DirectDebitCurrency" type="{urn:sap-com:document:sap:rfc:functions}cuky5"/>
 *         &lt;element name="DirectDebitCurrencyIso" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="DirectDebitMonths" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/>
 *         &lt;element name="DirectDebitRolling" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="BuagDeletionFlag" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="BuagText35" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="BuagIsCollBill" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="CollBillBuagId" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="PpaccRelation" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="SepaMndid" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="BuagIdLegacy" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapibus1006130SpecData", propOrder = {
    "buagText",
    "buagDefault",
    "buagClass",
    "addrNo",
    "refNumber",
    "bankInc",
    "bankOutg",
    "creditCardInc",
    "creditCardOutg",
    "payerBuagId",
    "addrDr",
    "addrPy",
    "addrPr",
    "addrIr",
    "addrTax",
    "taxCategory",
    "taxCode",
    "directDebitAmount",
    "directDebitCurrency",
    "directDebitCurrencyIso",
    "directDebitMonths",
    "directDebitRolling",
    "buagDeletionFlag",
    "buagText35",
    "buagIsCollBill",
    "collBillBuagId",
    "ppaccRelation",
    "sepaMndid",
    "buagIdLegacy"
})
public class Bapibus1006130SpecData {

    @XmlElement(name = "BuagText", required = true)
    protected String buagText;
    @XmlElement(name = "BuagDefault", required = true)
    protected String buagDefault;
    @XmlElement(name = "BuagClass", required = true)
    protected String buagClass;
    @XmlElement(name = "AddrNo", required = true)
    protected String addrNo;
    @XmlElement(name = "RefNumber", required = true)
    protected String refNumber;
    @XmlElement(name = "BankInc", required = true)
    protected String bankInc;
    @XmlElement(name = "BankOutg", required = true)
    protected String bankOutg;
    @XmlElement(name = "CreditCardInc", required = true)
    protected String creditCardInc;
    @XmlElement(name = "CreditCardOutg", required = true)
    protected String creditCardOutg;
    @XmlElement(name = "PayerBuagId", required = true)
    protected String payerBuagId;
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
    @XmlElement(name = "DirectDebitAmount", required = true)
    protected BigDecimal directDebitAmount;
    @XmlElement(name = "DirectDebitCurrency", required = true)
    protected String directDebitCurrency;
    @XmlElement(name = "DirectDebitCurrencyIso", required = true)
    protected String directDebitCurrencyIso;
    @XmlElement(name = "DirectDebitMonths", required = true)
    protected String directDebitMonths;
    @XmlElement(name = "DirectDebitRolling", required = true)
    protected String directDebitRolling;
    @XmlElement(name = "BuagDeletionFlag", required = true)
    protected String buagDeletionFlag;
    @XmlElement(name = "BuagText35", required = true)
    protected String buagText35;
    @XmlElement(name = "BuagIsCollBill", required = true)
    protected String buagIsCollBill;
    @XmlElement(name = "CollBillBuagId", required = true)
    protected String collBillBuagId;
    @XmlElement(name = "PpaccRelation", required = true)
    protected String ppaccRelation;
    @XmlElement(name = "SepaMndid", required = true)
    protected String sepaMndid;
    @XmlElement(name = "BuagIdLegacy", required = true)
    protected String buagIdLegacy;

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
     * Gets the value of the buagClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuagClass() {
        return buagClass;
    }

    /**
     * Sets the value of the buagClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuagClass(String value) {
        this.buagClass = value;
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
     * Gets the value of the refNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefNumber() {
        return refNumber;
    }

    /**
     * Sets the value of the refNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefNumber(String value) {
        this.refNumber = value;
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
     * Gets the value of the directDebitAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDirectDebitAmount() {
        return directDebitAmount;
    }

    /**
     * Sets the value of the directDebitAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDirectDebitAmount(BigDecimal value) {
        this.directDebitAmount = value;
    }

    /**
     * Gets the value of the directDebitCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectDebitCurrency() {
        return directDebitCurrency;
    }

    /**
     * Sets the value of the directDebitCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectDebitCurrency(String value) {
        this.directDebitCurrency = value;
    }

    /**
     * Gets the value of the directDebitCurrencyIso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectDebitCurrencyIso() {
        return directDebitCurrencyIso;
    }

    /**
     * Sets the value of the directDebitCurrencyIso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectDebitCurrencyIso(String value) {
        this.directDebitCurrencyIso = value;
    }

    /**
     * Gets the value of the directDebitMonths property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectDebitMonths() {
        return directDebitMonths;
    }

    /**
     * Sets the value of the directDebitMonths property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectDebitMonths(String value) {
        this.directDebitMonths = value;
    }

    /**
     * Gets the value of the directDebitRolling property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectDebitRolling() {
        return directDebitRolling;
    }

    /**
     * Sets the value of the directDebitRolling property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectDebitRolling(String value) {
        this.directDebitRolling = value;
    }

    /**
     * Gets the value of the buagDeletionFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuagDeletionFlag() {
        return buagDeletionFlag;
    }

    /**
     * Sets the value of the buagDeletionFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuagDeletionFlag(String value) {
        this.buagDeletionFlag = value;
    }

    /**
     * Gets the value of the buagText35 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuagText35() {
        return buagText35;
    }

    /**
     * Sets the value of the buagText35 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuagText35(String value) {
        this.buagText35 = value;
    }

    /**
     * Gets the value of the buagIsCollBill property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuagIsCollBill() {
        return buagIsCollBill;
    }

    /**
     * Sets the value of the buagIsCollBill property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuagIsCollBill(String value) {
        this.buagIsCollBill = value;
    }

    /**
     * Gets the value of the collBillBuagId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollBillBuagId() {
        return collBillBuagId;
    }

    /**
     * Sets the value of the collBillBuagId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollBillBuagId(String value) {
        this.collBillBuagId = value;
    }

    /**
     * Gets the value of the ppaccRelation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPpaccRelation() {
        return ppaccRelation;
    }

    /**
     * Sets the value of the ppaccRelation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPpaccRelation(String value) {
        this.ppaccRelation = value;
    }

    /**
     * Gets the value of the sepaMndid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSepaMndid() {
        return sepaMndid;
    }

    /**
     * Sets the value of the sepaMndid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSepaMndid(String value) {
        this.sepaMndid = value;
    }

    /**
     * Gets the value of the buagIdLegacy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuagIdLegacy() {
        return buagIdLegacy;
    }

    /**
     * Sets the value of the buagIdLegacy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuagIdLegacy(String value) {
        this.buagIdLegacy = value;
    }

}
