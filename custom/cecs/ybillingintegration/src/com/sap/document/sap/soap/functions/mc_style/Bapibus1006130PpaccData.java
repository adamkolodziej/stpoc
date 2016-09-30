
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapibus1006130PpaccData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapibus1006130PpaccData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PpaccGuid" type="{urn:sap-com:document:sap:rfc:functions}char32"/>
 *         &lt;element name="PpaccId" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="PrepaidSchema" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Currency" type="{urn:sap-com:document:sap:rfc:functions}cuky5"/>
 *         &lt;element name="CurrencyIso" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="MinBalance" type="{urn:sap-com:document:sap:rfc:functions}decimal23.4"/>
 *         &lt;element name="PayerAlternat" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="PaymentBy" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="BdidIn" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="CcardIn" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
 *         &lt;element name="NotifLimit1" type="{urn:sap-com:document:sap:rfc:functions}decimal23.4"/>
 *         &lt;element name="LimitActive1" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="NotifLimit2" type="{urn:sap-com:document:sap:rfc:functions}decimal23.4"/>
 *         &lt;element name="LimitActive2" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="NotifLimit3" type="{urn:sap-com:document:sap:rfc:functions}decimal23.4"/>
 *         &lt;element name="LimitActive3" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="ProvCloseDate" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="ClosedDate" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="SepaMndid" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapibus1006130PpaccData", propOrder = {
    "ppaccGuid",
    "ppaccId",
    "prepaidSchema",
    "currency",
    "currencyIso",
    "minBalance",
    "payerAlternat",
    "paymentBy",
    "bdidIn",
    "ccardIn",
    "notifLimit1",
    "limitActive1",
    "notifLimit2",
    "limitActive2",
    "notifLimit3",
    "limitActive3",
    "provCloseDate",
    "closedDate",
    "sepaMndid"
})
public class Bapibus1006130PpaccData {

    @XmlElement(name = "PpaccGuid", required = true)
    protected String ppaccGuid;
    @XmlElement(name = "PpaccId", required = true)
    protected String ppaccId;
    @XmlElement(name = "PrepaidSchema", required = true)
    protected String prepaidSchema;
    @XmlElement(name = "Currency", required = true)
    protected String currency;
    @XmlElement(name = "CurrencyIso", required = true)
    protected String currencyIso;
    @XmlElement(name = "MinBalance", required = true)
    protected BigDecimal minBalance;
    @XmlElement(name = "PayerAlternat", required = true)
    protected String payerAlternat;
    @XmlElement(name = "PaymentBy", required = true)
    protected String paymentBy;
    @XmlElement(name = "BdidIn", required = true)
    protected String bdidIn;
    @XmlElement(name = "CcardIn", required = true)
    protected String ccardIn;
    @XmlElement(name = "NotifLimit1", required = true)
    protected BigDecimal notifLimit1;
    @XmlElement(name = "LimitActive1", required = true)
    protected String limitActive1;
    @XmlElement(name = "NotifLimit2", required = true)
    protected BigDecimal notifLimit2;
    @XmlElement(name = "LimitActive2", required = true)
    protected String limitActive2;
    @XmlElement(name = "NotifLimit3", required = true)
    protected BigDecimal notifLimit3;
    @XmlElement(name = "LimitActive3", required = true)
    protected String limitActive3;
    @XmlElement(name = "ProvCloseDate", required = true)
    protected String provCloseDate;
    @XmlElement(name = "ClosedDate", required = true)
    protected String closedDate;
    @XmlElement(name = "SepaMndid", required = true)
    protected String sepaMndid;

    /**
     * Gets the value of the ppaccGuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPpaccGuid() {
        return ppaccGuid;
    }

    /**
     * Sets the value of the ppaccGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPpaccGuid(String value) {
        this.ppaccGuid = value;
    }

    /**
     * Gets the value of the ppaccId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPpaccId() {
        return ppaccId;
    }

    /**
     * Sets the value of the ppaccId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPpaccId(String value) {
        this.ppaccId = value;
    }

    /**
     * Gets the value of the prepaidSchema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrepaidSchema() {
        return prepaidSchema;
    }

    /**
     * Sets the value of the prepaidSchema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrepaidSchema(String value) {
        this.prepaidSchema = value;
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
     * Gets the value of the minBalance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMinBalance() {
        return minBalance;
    }

    /**
     * Sets the value of the minBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMinBalance(BigDecimal value) {
        this.minBalance = value;
    }

    /**
     * Gets the value of the payerAlternat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayerAlternat() {
        return payerAlternat;
    }

    /**
     * Sets the value of the payerAlternat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayerAlternat(String value) {
        this.payerAlternat = value;
    }

    /**
     * Gets the value of the paymentBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentBy() {
        return paymentBy;
    }

    /**
     * Sets the value of the paymentBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentBy(String value) {
        this.paymentBy = value;
    }

    /**
     * Gets the value of the bdidIn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBdidIn() {
        return bdidIn;
    }

    /**
     * Sets the value of the bdidIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBdidIn(String value) {
        this.bdidIn = value;
    }

    /**
     * Gets the value of the ccardIn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcardIn() {
        return ccardIn;
    }

    /**
     * Sets the value of the ccardIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcardIn(String value) {
        this.ccardIn = value;
    }

    /**
     * Gets the value of the notifLimit1 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNotifLimit1() {
        return notifLimit1;
    }

    /**
     * Sets the value of the notifLimit1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNotifLimit1(BigDecimal value) {
        this.notifLimit1 = value;
    }

    /**
     * Gets the value of the limitActive1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLimitActive1() {
        return limitActive1;
    }

    /**
     * Sets the value of the limitActive1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLimitActive1(String value) {
        this.limitActive1 = value;
    }

    /**
     * Gets the value of the notifLimit2 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNotifLimit2() {
        return notifLimit2;
    }

    /**
     * Sets the value of the notifLimit2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNotifLimit2(BigDecimal value) {
        this.notifLimit2 = value;
    }

    /**
     * Gets the value of the limitActive2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLimitActive2() {
        return limitActive2;
    }

    /**
     * Sets the value of the limitActive2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLimitActive2(String value) {
        this.limitActive2 = value;
    }

    /**
     * Gets the value of the notifLimit3 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNotifLimit3() {
        return notifLimit3;
    }

    /**
     * Sets the value of the notifLimit3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNotifLimit3(BigDecimal value) {
        this.notifLimit3 = value;
    }

    /**
     * Gets the value of the limitActive3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLimitActive3() {
        return limitActive3;
    }

    /**
     * Sets the value of the limitActive3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLimitActive3(String value) {
        this.limitActive3 = value;
    }

    /**
     * Gets the value of the provCloseDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvCloseDate() {
        return provCloseDate;
    }

    /**
     * Sets the value of the provCloseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvCloseDate(String value) {
        this.provCloseDate = value;
    }

    /**
     * Gets the value of the closedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClosedDate() {
        return closedDate;
    }

    /**
     * Sets the value of the closedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClosedDate(String value) {
        this.closedDate = value;
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

}
