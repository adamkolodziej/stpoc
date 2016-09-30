
package com.highdeal.ws.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IntegerRange complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IntegerRange">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="min" type="{http://schema.ws.highdeal.com/}IntegerBoundary"/>
 *         &lt;element name="max" type="{http://schema.ws.highdeal.com/}IntegerBoundary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IntegerRange", propOrder = {
    "min",
    "max"
})
public class IntegerRange {

    @XmlElement(required = true)
    protected IntegerBoundary min;
    @XmlElement(required = true)
    protected IntegerBoundary max;

    /**
     * Gets the value of the min property.
     * 
     * @return
     *     possible object is
     *     {@link IntegerBoundary }
     *     
     */
    public IntegerBoundary getMin() {
        return min;
    }

    /**
     * Sets the value of the min property.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegerBoundary }
     *     
     */
    public void setMin(IntegerBoundary value) {
        this.min = value;
    }

    /**
     * Gets the value of the max property.
     * 
     * @return
     *     possible object is
     *     {@link IntegerBoundary }
     *     
     */
    public IntegerBoundary getMax() {
        return max;
    }

    /**
     * Sets the value of the max property.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegerBoundary }
     *     
     */
    public void setMax(IntegerBoundary value) {
        this.max = value;
    }

}
