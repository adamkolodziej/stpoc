
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ChargingProcessInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargingProcessInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chargedItemSetUniqueId" type="{http://schema.charging.ws.highdeal.com/}IntegerUniqueIdentifier"/>
 *         &lt;element name="eventProcessingDate" type="{http://schema.ws.highdeal.com/}Date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargingProcessInfo", propOrder = {
    "chargedItemSetUniqueId",
    "eventProcessingDate"
})
public class ChargingProcessInfo {

    @XmlElement(required = true)
    protected IntegerUniqueIdentifier chargedItemSetUniqueId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar eventProcessingDate;

    /**
     * Gets the value of the chargedItemSetUniqueId property.
     * 
     * @return
     *     possible object is
     *     {@link IntegerUniqueIdentifier }
     *     
     */
    public IntegerUniqueIdentifier getChargedItemSetUniqueId() {
        return chargedItemSetUniqueId;
    }

    /**
     * Sets the value of the chargedItemSetUniqueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegerUniqueIdentifier }
     *     
     */
    public void setChargedItemSetUniqueId(IntegerUniqueIdentifier value) {
        this.chargedItemSetUniqueId = value;
    }

    /**
     * Gets the value of the eventProcessingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEventProcessingDate() {
        return eventProcessingDate;
    }

    /**
     * Sets the value of the eventProcessingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEventProcessingDate(XMLGregorianCalendar value) {
        this.eventProcessingDate = value;
    }

}
