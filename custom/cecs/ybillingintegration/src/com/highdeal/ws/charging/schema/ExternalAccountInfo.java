
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExternalAccountInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExternalAccountInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://schema.ws.highdeal.com/}String256"/>
 *         &lt;element name="externalSystemCode" type="{http://schema.ws.highdeal.com/}String256" minOccurs="0"/>
 *         &lt;element name="additionalId" type="{http://schema.ws.highdeal.com/}String256" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExternalAccountInfo", propOrder = {
    "id",
    "externalSystemCode",
    "additionalId"
})
public class ExternalAccountInfo {

    @XmlElement(required = true)
    protected String id;
    protected String externalSystemCode;
    protected String additionalId;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the externalSystemCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalSystemCode() {
        return externalSystemCode;
    }

    /**
     * Sets the value of the externalSystemCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalSystemCode(String value) {
        this.externalSystemCode = value;
    }

    /**
     * Gets the value of the additionalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalId() {
        return additionalId;
    }

    /**
     * Sets the value of the additionalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalId(String value) {
        this.additionalId = value;
    }

}
