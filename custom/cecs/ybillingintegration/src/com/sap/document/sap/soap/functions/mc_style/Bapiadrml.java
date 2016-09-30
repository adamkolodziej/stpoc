
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapiadrml complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapiadrml">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StdNo" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="SymbDest" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="RecType" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="RMailClt" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="RMail" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="StdRecip" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="R3User" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="HomeFlag" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Consnumber" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
 *         &lt;element name="Errorflag" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="FlgNouse" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="ValidFrom" type="{urn:sap-com:document:sap:rfc:functions}char14"/>
 *         &lt;element name="ValidTo" type="{urn:sap-com:document:sap:rfc:functions}char14"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapiadrml", propOrder = {
    "stdNo",
    "symbDest",
    "recType",
    "rMailClt",
    "rMail",
    "stdRecip",
    "r3User",
    "homeFlag",
    "consnumber",
    "errorflag",
    "flgNouse",
    "validFrom",
    "validTo"
})
public class Bapiadrml {

    @XmlElement(name = "StdNo", required = true)
    protected String stdNo;
    @XmlElement(name = "SymbDest", required = true)
    protected String symbDest;
    @XmlElement(name = "RecType", required = true)
    protected String recType;
    @XmlElement(name = "RMailClt", required = true)
    protected String rMailClt;
    @XmlElement(name = "RMail", required = true)
    protected String rMail;
    @XmlElement(name = "StdRecip", required = true)
    protected String stdRecip;
    @XmlElement(name = "R3User", required = true)
    protected String r3User;
    @XmlElement(name = "HomeFlag", required = true)
    protected String homeFlag;
    @XmlElement(name = "Consnumber", required = true)
    protected String consnumber;
    @XmlElement(name = "Errorflag", required = true)
    protected String errorflag;
    @XmlElement(name = "FlgNouse", required = true)
    protected String flgNouse;
    @XmlElement(name = "ValidFrom", required = true)
    protected String validFrom;
    @XmlElement(name = "ValidTo", required = true)
    protected String validTo;

    /**
     * Gets the value of the stdNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStdNo() {
        return stdNo;
    }

    /**
     * Sets the value of the stdNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStdNo(String value) {
        this.stdNo = value;
    }

    /**
     * Gets the value of the symbDest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSymbDest() {
        return symbDest;
    }

    /**
     * Sets the value of the symbDest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSymbDest(String value) {
        this.symbDest = value;
    }

    /**
     * Gets the value of the recType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecType() {
        return recType;
    }

    /**
     * Sets the value of the recType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecType(String value) {
        this.recType = value;
    }

    /**
     * Gets the value of the rMailClt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRMailClt() {
        return rMailClt;
    }

    /**
     * Sets the value of the rMailClt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRMailClt(String value) {
        this.rMailClt = value;
    }

    /**
     * Gets the value of the rMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRMail() {
        return rMail;
    }

    /**
     * Sets the value of the rMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRMail(String value) {
        this.rMail = value;
    }

    /**
     * Gets the value of the stdRecip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStdRecip() {
        return stdRecip;
    }

    /**
     * Sets the value of the stdRecip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStdRecip(String value) {
        this.stdRecip = value;
    }

    /**
     * Gets the value of the r3User property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getR3User() {
        return r3User;
    }

    /**
     * Sets the value of the r3User property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setR3User(String value) {
        this.r3User = value;
    }

    /**
     * Gets the value of the homeFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeFlag() {
        return homeFlag;
    }

    /**
     * Sets the value of the homeFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeFlag(String value) {
        this.homeFlag = value;
    }

    /**
     * Gets the value of the consnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsnumber() {
        return consnumber;
    }

    /**
     * Sets the value of the consnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsnumber(String value) {
        this.consnumber = value;
    }

    /**
     * Gets the value of the errorflag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorflag() {
        return errorflag;
    }

    /**
     * Sets the value of the errorflag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorflag(String value) {
        this.errorflag = value;
    }

    /**
     * Gets the value of the flgNouse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlgNouse() {
        return flgNouse;
    }

    /**
     * Sets the value of the flgNouse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlgNouse(String value) {
        this.flgNouse = value;
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

}
