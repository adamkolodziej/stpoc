
package com.highdeal.ws.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NumberRange complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NumberRange">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="min" type="{http://schema.ws.highdeal.com/}NumberBoundary"/>
 *         &lt;element name="max" type="{http://schema.ws.highdeal.com/}NumberBoundary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NumberRange", propOrder = {
    "min",
    "max"
})
public class NumberRange {

    @XmlElement(required = true)
    protected NumberBoundary min;
    @XmlElement(required = true)
    protected NumberBoundary max;

    /**
     * Gets the value of the min property.
     * 
     * @return
     *     possible object is
     *     {@link NumberBoundary }
     *     
     */
    public NumberBoundary getMin() {
        return min;
    }

    /**
     * Sets the value of the min property.
     * 
     * @param value
     *     allowed object is
     *     {@link NumberBoundary }
     *     
     */
    public void setMin(NumberBoundary value) {
        this.min = value;
    }

    /**
     * Gets the value of the max property.
     * 
     * @return
     *     possible object is
     *     {@link NumberBoundary }
     *     
     */
    public NumberBoundary getMax() {
        return max;
    }

    /**
     * Sets the value of the max property.
     * 
     * @param value
     *     allowed object is
     *     {@link NumberBoundary }
     *     
     */
    public void setMax(NumberBoundary value) {
        this.max = value;
    }

}
