
package com.highdeal.ws.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NumberAttributeDescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NumberAttributeDescription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="enumeration" type="{http://schema.ws.highdeal.com/}NumberEnumeration"/>
 *           &lt;element name="range" type="{http://schema.ws.highdeal.com/}NumberRange"/>
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
@XmlType(name = "NumberAttributeDescription", propOrder = {
    "enumeration",
    "range"
})
public class NumberAttributeDescription {

    protected NumberEnumeration enumeration;
    protected NumberRange range;

    /**
     * Gets the value of the enumeration property.
     * 
     * @return
     *     possible object is
     *     {@link NumberEnumeration }
     *     
     */
    public NumberEnumeration getEnumeration() {
        return enumeration;
    }

    /**
     * Sets the value of the enumeration property.
     * 
     * @param value
     *     allowed object is
     *     {@link NumberEnumeration }
     *     
     */
    public void setEnumeration(NumberEnumeration value) {
        this.enumeration = value;
    }

    /**
     * Gets the value of the range property.
     * 
     * @return
     *     possible object is
     *     {@link NumberRange }
     *     
     */
    public NumberRange getRange() {
        return range;
    }

    /**
     * Sets the value of the range property.
     * 
     * @param value
     *     allowed object is
     *     {@link NumberRange }
     *     
     */
    public void setRange(NumberRange value) {
        this.range = value;
    }

}
