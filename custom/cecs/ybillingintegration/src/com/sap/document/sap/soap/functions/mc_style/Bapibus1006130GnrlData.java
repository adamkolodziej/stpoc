
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapibus1006130GnrlData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapibus1006130GnrlData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BabrText" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="MethodInc" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="MethodOutg" type="{urn:sap-com:document:sap:rfc:functions}char5"/>
 *         &lt;element name="SendcontrolDr" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Tofpaym" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="SendcontrolBr" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="CorrespVariant" type="{urn:sap-com:document:sap:rfc:functions}char5"/>
 *         &lt;element name="SendcontrolBp" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapibus1006130GnrlData", propOrder = {
    "babrText",
    "methodInc",
    "methodOutg",
    "sendcontrolDr",
    "tofpaym",
    "sendcontrolBr",
    "correspVariant",
    "sendcontrolBp"
})
public class Bapibus1006130GnrlData {

    @XmlElement(name = "BabrText", required = true)
    protected String babrText;
    @XmlElement(name = "MethodInc", required = true)
    protected String methodInc;
    @XmlElement(name = "MethodOutg", required = true)
    protected String methodOutg;
    @XmlElement(name = "SendcontrolDr", required = true)
    protected String sendcontrolDr;
    @XmlElement(name = "Tofpaym", required = true)
    protected String tofpaym;
    @XmlElement(name = "SendcontrolBr", required = true)
    protected String sendcontrolBr;
    @XmlElement(name = "CorrespVariant", required = true)
    protected String correspVariant;
    @XmlElement(name = "SendcontrolBp", required = true)
    protected String sendcontrolBp;

    /**
     * Gets the value of the babrText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBabrText() {
        return babrText;
    }

    /**
     * Sets the value of the babrText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBabrText(String value) {
        this.babrText = value;
    }

    /**
     * Gets the value of the methodInc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMethodInc() {
        return methodInc;
    }

    /**
     * Sets the value of the methodInc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMethodInc(String value) {
        this.methodInc = value;
    }

    /**
     * Gets the value of the methodOutg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMethodOutg() {
        return methodOutg;
    }

    /**
     * Sets the value of the methodOutg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMethodOutg(String value) {
        this.methodOutg = value;
    }

    /**
     * Gets the value of the sendcontrolDr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendcontrolDr() {
        return sendcontrolDr;
    }

    /**
     * Sets the value of the sendcontrolDr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendcontrolDr(String value) {
        this.sendcontrolDr = value;
    }

    /**
     * Gets the value of the tofpaym property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTofpaym() {
        return tofpaym;
    }

    /**
     * Sets the value of the tofpaym property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTofpaym(String value) {
        this.tofpaym = value;
    }

    /**
     * Gets the value of the sendcontrolBr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendcontrolBr() {
        return sendcontrolBr;
    }

    /**
     * Sets the value of the sendcontrolBr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendcontrolBr(String value) {
        this.sendcontrolBr = value;
    }

    /**
     * Gets the value of the correspVariant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrespVariant() {
        return correspVariant;
    }

    /**
     * Sets the value of the correspVariant property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrespVariant(String value) {
        this.correspVariant = value;
    }

    /**
     * Gets the value of the sendcontrolBp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendcontrolBp() {
        return sendcontrolBp;
    }

    /**
     * Sets the value of the sendcontrolBp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendcontrolBp(String value) {
        this.sendcontrolBp = value;
    }

}
