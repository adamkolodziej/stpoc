
package com.highdeal.ws.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for DateBoundary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DateBoundary">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://schema.ws.highdeal.com/>Date">
 *       &lt;attribute name="included" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DateBoundary", propOrder = {
    "value"
})
public class DateBoundary {

    @XmlValue
    protected XMLGregorianCalendar value;
    @XmlAttribute(name = "included")
    protected Boolean included;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValue(XMLGregorianCalendar value) {
        this.value = value;
    }

    /**
     * Gets the value of the included property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIncluded() {
        if (included == null) {
            return true;
        } else {
            return included;
        }
    }

    /**
     * Sets the value of the included property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncluded(Boolean value) {
        this.included = value;
    }

}
