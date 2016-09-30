
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapiadprt complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapiadprt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StdNo" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="PrintDest" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="StdRecip" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
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
@XmlType(name = "Bapiadprt", propOrder = {
    "stdNo",
    "printDest",
    "stdRecip",
    "homeFlag",
    "consnumber",
    "errorflag",
    "flgNouse",
    "validFrom",
    "validTo"
})
public class Bapiadprt {

    @XmlElement(name = "StdNo", required = true)
    protected String stdNo;
    @XmlElement(name = "PrintDest", required = true)
    protected String printDest;
    @XmlElement(name = "StdRecip", required = true)
    protected String stdRecip;
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
     * Gets the value of the printDest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrintDest() {
        return printDest;
    }

    /**
     * Sets the value of the printDest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrintDest(String value) {
        this.printDest = value;
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
