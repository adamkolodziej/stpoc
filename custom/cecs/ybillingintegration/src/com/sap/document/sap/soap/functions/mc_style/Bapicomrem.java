
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapicomrem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapicomrem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CommType" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Langu" type="{urn:sap-com:document:sap:rfc:functions}lang"/>
 *         &lt;element name="LanguIso" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="CommNotes" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="Consnumber" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
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
@XmlType(name = "Bapicomrem", propOrder = {
    "commType",
    "langu",
    "languIso",
    "commNotes",
    "consnumber",
    "errorflag"
})
public class Bapicomrem {

    @XmlElement(name = "CommType", required = true)
    protected String commType;
    @XmlElement(name = "Langu", required = true)
    protected String langu;
    @XmlElement(name = "LanguIso", required = true)
    protected String languIso;
    @XmlElement(name = "CommNotes", required = true)
    protected String commNotes;
    @XmlElement(name = "Consnumber", required = true)
    protected String consnumber;
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
     * Gets the value of the commNotes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommNotes() {
        return commNotes;
    }

    /**
     * Sets the value of the commNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommNotes(String value) {
        this.commNotes = value;
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

}
