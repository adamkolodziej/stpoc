
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BapiadRem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BapiadRem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AddrVers" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Langu" type="{urn:sap-com:document:sap:rfc:functions}lang"/>
 *         &lt;element name="LanguIso" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="AdrNotes" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
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
@XmlType(name = "BapiadRem", propOrder = {
    "addrVers",
    "langu",
    "languIso",
    "adrNotes",
    "errorflag"
})
public class BapiadRem {

    @XmlElement(name = "AddrVers", required = true)
    protected String addrVers;
    @XmlElement(name = "Langu", required = true)
    protected String langu;
    @XmlElement(name = "LanguIso", required = true)
    protected String languIso;
    @XmlElement(name = "AdrNotes", required = true)
    protected String adrNotes;
    @XmlElement(name = "Errorflag", required = true)
    protected String errorflag;

    /**
     * Gets the value of the addrVers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrVers() {
        return addrVers;
    }

    /**
     * Sets the value of the addrVers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrVers(String value) {
        this.addrVers = value;
    }

    /**
     * Gets the value of the langu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLangu() {
        return langu;
    }

    /**
     * Sets the value of the langu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLangu(String value) {
        this.langu = value;
    }

    /**
     * Gets the value of the languIso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguIso() {
        return languIso;
    }

    /**
     * Sets the value of the languIso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguIso(String value) {
        this.languIso = value;
    }

    /**
     * Gets the value of the adrNotes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdrNotes() {
        return adrNotes;
    }

    /**
     * Sets the value of the adrNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdrNotes(String value) {
        this.adrNotes = value;
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
