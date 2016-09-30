
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapibus1006130RelData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapibus1006130RelData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PartnerPft" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Partner1" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Partner2" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="PartnerFct" type="{urn:sap-com:document:sap:rfc:functions}char8"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapibus1006130RelData", propOrder = {
    "partnerPft",
    "partner1",
    "partner2",
    "partnerFct"
})
public class Bapibus1006130RelData {

    @XmlElement(name = "PartnerPft", required = true)
    protected String partnerPft;
    @XmlElement(name = "Partner1", required = true)
    protected String partner1;
    @XmlElement(name = "Partner2", required = true)
    protected String partner2;
    @XmlElement(name = "PartnerFct", required = true)
    protected String partnerFct;

    /**
     * Gets the value of the partnerPft property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerPft() {
        return partnerPft;
    }

    /**
     * Sets the value of the partnerPft property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerPft(String value) {
        this.partnerPft = value;
    }

    /**
     * Gets the value of the partner1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartner1() {
        return partner1;
    }

    /**
     * Sets the value of the partner1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartner1(String value) {
        this.partner1 = value;
    }

    /**
     * Gets the value of the partner2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartner2() {
        return partner2;
    }

    /**
     * Sets the value of the partner2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartner2(String value) {
        this.partner2 = value;
    }

    /**
     * Gets the value of the partnerFct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerFct() {
        return partnerFct;
    }

    /**
     * Sets the value of the partnerFct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerFct(String value) {
        this.partnerFct = value;
    }

}
