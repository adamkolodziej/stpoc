
package com.highdeal.ws.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DateRange complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DateRange">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="min" type="{http://schema.ws.highdeal.com/}DateBoundary"/>
 *         &lt;element name="max" type="{http://schema.ws.highdeal.com/}DateBoundary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DateRange", propOrder = {
    "min",
    "max"
})
public class DateRange {

    @XmlElement(required = true)
    protected DateBoundary min;
    @XmlElement(required = true)
    protected DateBoundary max;

    /**
     * Gets the value of the min property.
     * 
     * @return
     *     possible object is
     *     {@link DateBoundary }
     *     
     */
    public DateBoundary getMin() {
        return min;
    }

    /**
     * Sets the value of the min property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateBoundary }
     *     
     */
    public void setMin(DateBoundary value) {
        this.min = value;
    }

    /**
     * Gets the value of the max property.
     * 
     * @return
     *     possible object is
     *     {@link DateBoundary }
     *     
     */
    public DateBoundary getMax() {
        return max;
    }

    /**
     * Sets the value of the max property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateBoundary }
     *     
     */
    public void setMax(DateBoundary value) {
        this.max = value;
    }

}
