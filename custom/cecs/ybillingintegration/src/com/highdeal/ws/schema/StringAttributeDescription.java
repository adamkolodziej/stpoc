
package com.highdeal.ws.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StringAttributeDescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StringAttributeDescription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="enumeration" type="{http://schema.ws.highdeal.com/}StringEnumeration"/>
 *           &lt;element name="pattern" type="{http://schema.ws.highdeal.com/}String256"/>
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
@XmlType(name = "StringAttributeDescription", propOrder = {
    "enumeration",
    "pattern"
})
public class StringAttributeDescription {

    protected StringEnumeration enumeration;
    protected String pattern;

    /**
     * Gets the value of the enumeration property.
     * 
     * @return
     *     possible object is
     *     {@link StringEnumeration }
     *     
     */
    public StringEnumeration getEnumeration() {
        return enumeration;
    }

    /**
     * Sets the value of the enumeration property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringEnumeration }
     *     
     */
    public void setEnumeration(StringEnumeration value) {
        this.enumeration = value;
    }

    /**
     * Gets the value of the pattern property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Sets the value of the pattern property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPattern(String value) {
        this.pattern = value;
    }

}
