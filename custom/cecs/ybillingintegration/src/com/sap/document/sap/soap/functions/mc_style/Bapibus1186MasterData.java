
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapibus1186MasterData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapibus1186MasterData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ValidTo" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ValidFrom" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="CardType" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="MaskNumber" type="{urn:sap-com:document:sap:rfc:functions}char25"/>
 *         &lt;element name="StampName" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Issuer" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="IssuingDate" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapibus1186MasterData", propOrder = {
    "validTo",
    "validFrom",
    "cardType",
    "maskNumber",
    "stampName",
    "issuer",
    "issuingDate"
})
public class Bapibus1186MasterData {

    @XmlElement(name = "ValidTo", required = true)
    protected String validTo;
    @XmlElement(name = "ValidFrom", required = true)
    protected String validFrom;
    @XmlElement(name = "CardType", required = true)
    protected String cardType;
    @XmlElement(name = "MaskNumber", required = true)
    protected String maskNumber;
    @XmlElement(name = "StampName", required = true)
    protected String stampName;
    @XmlElement(name = "Issuer", required = true)
    protected String issuer;
    @XmlElement(name = "IssuingDate", required = true)
    protected String issuingDate;

    /**
     * Gets the value of the validTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidTo() {
        return validTo;
    }

    /**
     * Sets the value of the validTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidTo(String value) {
        this.validTo = value;
    }

    /**
     * Gets the value of the validFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidFrom() {
        return validFrom;
    }

    /**
     * Sets the value of the validFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidFrom(String value) {
        this.validFrom = value;
    }

    /**
     * Gets the value of the cardType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Sets the value of the cardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardType(String value) {
        this.cardType = value;
    }

    /**
     * Gets the value of the maskNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaskNumber() {
        return maskNumber;
    }

    /**
     * Sets the value of the maskNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaskNumber(String value) {
        this.maskNumber = value;
    }

    /**
     * Gets the value of the stampName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStampName() {
        return stampName;
    }

    /**
     * Sets the value of the stampName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStampName(String value) {
        this.stampName = value;
    }

    /**
     * Gets the value of the issuer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * Sets the value of the issuer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuer(String value) {
        this.issuer = value;
    }

    /**
     * Gets the value of the issuingDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuingDate() {
        return issuingDate;
    }

    /**
     * Sets the value of the issuingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuingDate(String value) {
        this.issuingDate = value;
    }

}
