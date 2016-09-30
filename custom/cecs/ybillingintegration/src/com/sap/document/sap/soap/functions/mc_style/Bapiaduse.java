
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapiaduse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapiaduse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CommType" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Consnumber" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
 *         &lt;element name="CommUsage" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ValidTo" type="{urn:sap-com:document:sap:rfc:functions}char14"/>
 *         &lt;element name="ValidFrom" type="{urn:sap-com:document:sap:rfc:functions}char14"/>
 *         &lt;element name="DefUsage" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Errorflag" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapiaduse", propOrder = {
    "commType",
    "consnumber",
    "commUsage",
    "validTo",
    "validFrom",
    "defUsage",
    "errorflag"
})
public class Bapiaduse {

    @XmlElement(name = "CommType", required = true)
    protected String commType;
    @XmlElement(name = "Consnumber", required = true)
    protected String consnumber;
    @XmlElement(name = "CommUsage", required = true)
    protected String commUsage;
    @XmlElement(name = "ValidTo", required = true)
    protected String validTo;
    @XmlElement(name = "ValidFrom", required = true)
    protected String validFrom;
    @XmlElement(name = "DefUsage", required = true)
    protected String defUsage;
    @XmlElement(name = "Errorflag", required = true)
    protected String errorflag;

    /**
     * Gets the value of the commType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommType() {
        return commType;
    }

    /**
     * Sets the value of the commType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommType(String value) {
        this.commType = value;
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
     * Gets the value of the commUsage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommUsage() {
        return commUsage;
    }

    /**
     * Sets the value of the commUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommUsage(String value) {
        this.commUsage = value;
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
     * Gets the value of the defUsage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefUsage() {
        return defUsage;
    }

    /**
     * Sets the value of the defUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefUsage(String value) {
        this.defUsage = value;
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

}
