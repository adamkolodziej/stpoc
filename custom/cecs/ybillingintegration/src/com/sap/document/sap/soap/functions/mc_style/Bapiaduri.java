
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapiaduri complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapiaduri">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StdNo" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="UriType" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Uri" type="{urn:sap-com:document:sap:rfc:functions}char132"/>
 *         &lt;element name="StdRecip" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="HomeFlag" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Consnumber" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
 *         &lt;element name="UriPart1" type="{urn:sap-com:document:sap:rfc:functions}char250"/>
 *         &lt;element name="UriPart2" type="{urn:sap-com:document:sap:rfc:functions}char250"/>
 *         &lt;element name="UriPart3" type="{urn:sap-com:document:sap:rfc:functions}char250"/>
 *         &lt;element name="UriPart4" type="{urn:sap-com:document:sap:rfc:functions}char250"/>
 *         &lt;element name="UriPart5" type="{urn:sap-com:document:sap:rfc:functions}char250"/>
 *         &lt;element name="UriPart6" type="{urn:sap-com:document:sap:rfc:functions}char250"/>
 *         &lt;element name="UriPart7" type="{urn:sap-com:document:sap:rfc:functions}char250"/>
 *         &lt;element name="UriPart8" type="{urn:sap-com:document:sap:rfc:functions}char250"/>
 *         &lt;element name="UriPart9" type="{urn:sap-com:document:sap:rfc:functions}char48"/>
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
@XmlType(name = "Bapiaduri", propOrder = {
    "stdNo",
    "uriType",
    "uri",
    "stdRecip",
    "homeFlag",
    "consnumber",
    "uriPart1",
    "uriPart2",
    "uriPart3",
    "uriPart4",
    "uriPart5",
    "uriPart6",
    "uriPart7",
    "uriPart8",
    "uriPart9",
    "errorflag",
    "flgNouse",
    "validFrom",
    "validTo"
})
public class Bapiaduri {

    @XmlElement(name = "StdNo", required = true)
    protected String stdNo;
    @XmlElement(name = "UriType", required = true)
    protected String uriType;
    @XmlElement(name = "Uri", required = true)
    protected String uri;
    @XmlElement(name = "StdRecip", required = true)
    protected String stdRecip;
    @XmlElement(name = "HomeFlag", required = true)
    protected String homeFlag;
    @XmlElement(name = "Consnumber", required = true)
    protected String consnumber;
    @XmlElement(name = "UriPart1", required = true)
    protected String uriPart1;
    @XmlElement(name = "UriPart2", required = true)
    protected String uriPart2;
    @XmlElement(name = "UriPart3", required = true)
    protected String uriPart3;
    @XmlElement(name = "UriPart4", required = true)
    protected String uriPart4;
    @XmlElement(name = "UriPart5", required = true)
    protected String uriPart5;
    @XmlElement(name = "UriPart6", required = true)
    protected String uriPart6;
    @XmlElement(name = "UriPart7", required = true)
    protected String uriPart7;
    @XmlElement(name = "UriPart8", required = true)
    protected String uriPart8;
    @XmlElement(name = "UriPart9", required = true)
    protected String uriPart9;
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
     * Gets the value of the uriType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUriType() {
        return uriType;
    }

    /**
     * Sets the value of the uriType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUriType(String value) {
        this.uriType = value;
    }

    /**
     * Gets the value of the uri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUri(String value) {
        this.uri = value;
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
     * Gets the value of the uriPart1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUriPart1() {
        return uriPart1;
    }

    /**
     * Sets the value of the uriPart1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUriPart1(String value) {
        this.uriPart1 = value;
    }

    /**
     * Gets the value of the uriPart2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUriPart2() {
        return uriPart2;
    }

    /**
     * Sets the value of the uriPart2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUriPart2(String value) {
        this.uriPart2 = value;
    }

    /**
     * Gets the value of the uriPart3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUriPart3() {
        return uriPart3;
    }

    /**
     * Sets the value of the uriPart3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUriPart3(String value) {
        this.uriPart3 = value;
    }

    /**
     * Gets the value of the uriPart4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUriPart4() {
        return uriPart4;
    }

    /**
     * Sets the value of the uriPart4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUriPart4(String value) {
        this.uriPart4 = value;
    }

    /**
     * Gets the value of the uriPart5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUriPart5() {
        return uriPart5;
    }

    /**
     * Sets the value of the uriPart5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUriPart5(String value) {
        this.uriPart5 = value;
    }

    /**
     * Gets the value of the uriPart6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUriPart6() {
        return uriPart6;
    }

    /**
     * Sets the value of the uriPart6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUriPart6(String value) {
        this.uriPart6 = value;
    }

    /**
     * Gets the value of the uriPart7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUriPart7() {
        return uriPart7;
    }

    /**
     * Sets the value of the uriPart7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUriPart7(String value) {
        this.uriPart7 = value;
    }

    /**
     * Gets the value of the uriPart8 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUriPart8() {
        return uriPart8;
    }

    /**
     * Sets the value of the uriPart8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUriPart8(String value) {
        this.uriPart8 = value;
    }

    /**
     * Gets the value of the uriPart9 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUriPart9() {
        return uriPart9;
    }

    /**
     * Sets the value of the uriPart9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUriPart9(String value) {
        this.uriPart9 = value;
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
