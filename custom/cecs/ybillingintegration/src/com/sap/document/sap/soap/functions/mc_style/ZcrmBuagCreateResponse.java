
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CsDatagnrlExp" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006130GnrlDataExp" minOccurs="0"/>
 *         &lt;element name="CsDataspecExp" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006130SpecDataExp" minOccurs="0"/>
 *         &lt;element name="EvBuagDoubletteGuid" type="{urn:sap-com:document:sap:rfc:functions}char32"/>
 *         &lt;element name="EvBuagDoubletteId" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="Return" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapiret2T"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "csDatagnrlExp",
    "csDataspecExp",
    "evBuagDoubletteGuid",
    "evBuagDoubletteId",
    "_return"
})
@XmlRootElement(name = "ZcrmBuagCreateResponse")
public class ZcrmBuagCreateResponse {

    @XmlElement(name = "CsDatagnrlExp")
    protected Bapibus1006130GnrlDataExp csDatagnrlExp;
    @XmlElement(name = "CsDataspecExp")
    protected Bapibus1006130SpecDataExp csDataspecExp;
    @XmlElement(name = "EvBuagDoubletteGuid", required = true)
    protected String evBuagDoubletteGuid;
    @XmlElement(name = "EvBuagDoubletteId", required = true)
    protected String evBuagDoubletteId;
    @XmlElement(name = "Return", required = true)
    protected Bapiret2T _return;

    /**
     * Gets the value of the csDatagnrlExp property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006130GnrlDataExp }
     *     
     */
    public Bapibus1006130GnrlDataExp getCsDatagnrlExp() {
        return csDatagnrlExp;
    }

    /**
     * Sets the value of the csDatagnrlExp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006130GnrlDataExp }
     *     
     */
    public void setCsDatagnrlExp(Bapibus1006130GnrlDataExp value) {
        this.csDatagnrlExp = value;
    }

    /**
     * Gets the value of the csDataspecExp property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006130SpecDataExp }
     *     
     */
    public Bapibus1006130SpecDataExp getCsDataspecExp() {
        return csDataspecExp;
    }

    /**
     * Sets the value of the csDataspecExp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006130SpecDataExp }
     *     
     */
    public void setCsDataspecExp(Bapibus1006130SpecDataExp value) {
        this.csDataspecExp = value;
    }

    /**
     * Gets the value of the evBuagDoubletteGuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvBuagDoubletteGuid() {
        return evBuagDoubletteGuid;
    }

    /**
     * Sets the value of the evBuagDoubletteGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvBuagDoubletteGuid(String value) {
        this.evBuagDoubletteGuid = value;
    }

    /**
     * Gets the value of the evBuagDoubletteId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvBuagDoubletteId() {
        return evBuagDoubletteId;
    }

    /**
     * Sets the value of the evBuagDoubletteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvBuagDoubletteId(String value) {
        this.evBuagDoubletteId = value;
    }

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link Bapiret2T }
     *     
     */
    public Bapiret2T getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapiret2T }
     *     
     */
    public void setReturn(Bapiret2T value) {
        this._return = value;
    }

}
