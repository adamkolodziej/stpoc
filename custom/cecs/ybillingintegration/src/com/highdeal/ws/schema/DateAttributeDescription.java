
package com.highdeal.ws.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DateAttributeDescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DateAttributeDescription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="enumeration" type="{http://schema.ws.highdeal.com/}DateEnumeration"/>
 *           &lt;element name="range" type="{http://schema.ws.highdeal.com/}DateRange"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DateAttributeDescription", propOrder = {
    "enumeration",
    "range"
})
public class DateAttributeDescription {

    protected DateEnumeration enumeration;
    protected DateRange range;

    /**
     * Gets the value of the enumeration property.
     * 
     * @return
     *     possible object is
     *     {@link DateEnumeration }
     *     
     */
    public DateEnumeration getEnumeration() {
        return enumeration;
    }

    /**
     * Sets the value of the enumeration property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateEnumeration }
     *     
     */
    public void setEnumeration(DateEnumeration value) {
        this.enumeration = value;
    }

    /**
     * Gets the value of the range property.
     * 
     * @return
     *     possible object is
     *     {@link DateRange }
     *     
     */
    public DateRange getRange() {
        return range;
    }

    /**
     * Sets the value of the range property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateRange }
     *     
     */
    public void setRange(DateRange value) {
        this.range = value;
    }

}
