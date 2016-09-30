
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ChargeableItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargeableItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://schema.ws.highdeal.com/}NotEmptyString256"/>
 *         &lt;element name="uid" type="{http://schema.charging.ws.highdeal.com/}StringUniqueIdentifier" minOccurs="0"/>
 *         &lt;element name="userTechnicalId" type="{http://schema.ws.highdeal.com/}NotEmptyString256"/>
 *         &lt;element name="serviceId" type="{http://schema.ws.highdeal.com/}NotEmptyString256"/>
 *         &lt;element name="consumptionDate" type="{http://schema.ws.highdeal.com/}Date"/>
 *         &lt;element name="userProperties" type="{http://schema.charging.ws.highdeal.com/}ChargeableItemUserProperties"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargeableItem", propOrder = {
    "name",
    "uid",
    "userTechnicalId",
    "serviceId",
    "consumptionDate",
    "userProperties"
})
public class ChargeableItem {

    @XmlElement(required = true)
    protected String name;
    protected StringUniqueIdentifier uid;
    @XmlElement(required = true)
    protected String userTechnicalId;
    @XmlElement(required = true)
    protected String serviceId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar consumptionDate;
    @XmlElement(required = true)
    protected ChargeableItemUserProperties userProperties;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the uid property.
     * 
     * @return
     *     possible object is
     *     {@link StringUniqueIdentifier }
     *     
     */
    public StringUniqueIdentifier getUid() {
        return uid;
    }

    /**
     * Sets the value of the uid property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringUniqueIdentifier }
     *     
     */
    public void setUid(StringUniqueIdentifier value) {
        this.uid = value;
    }

    /**
     * Gets the value of the userTechnicalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserTechnicalId() {
        return userTechnicalId;
    }

    /**
     * Sets the value of the userTechnicalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserTechnicalId(String value) {
        this.userTechnicalId = value;
    }

    /**
     * Gets the value of the serviceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * Sets the value of the serviceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceId(String value) {
        this.serviceId = value;
    }

    /**
     * Gets the value of the consumptionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConsumptionDate() {
        return consumptionDate;
    }

    /**
     * Sets the value of the consumptionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConsumptionDate(XMLGregorianCalendar value) {
        this.consumptionDate = value;
    }

    /**
     * Gets the value of the userProperties property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeableItemUserProperties }
     *     
     */
    public ChargeableItemUserProperties getUserProperties() {
        return userProperties;
    }

    /**
     * Sets the value of the userProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeableItemUserProperties }
     *     
     */
    public void setUserProperties(ChargeableItemUserProperties value) {
        this.userProperties = value;
    }

}
