
package com.highdeal.ws.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdditionalAttributeDescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdditionalAttributeDescription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://schema.ws.highdeal.com/}String256"/>
 *         &lt;choice>
 *           &lt;element name="pattern" type="{http://schema.ws.highdeal.com/}String256"/>
 *           &lt;element name="stringEnumeration" type="{http://schema.ws.highdeal.com/}StringEnumeration"/>
 *           &lt;element name="numberEnumeration" type="{http://schema.ws.highdeal.com/}NumberEnumeration"/>
 *           &lt;element name="dateEnumeration" type="{http://schema.ws.highdeal.com/}DateEnumeration"/>
 *           &lt;element name="numberRange" type="{http://schema.ws.highdeal.com/}NumberRange"/>
 *           &lt;element name="dateRange" type="{http://schema.ws.highdeal.com/}DateRange"/>
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
@XmlType(name = "AdditionalAttributeDescription", propOrder = {
    "name",
    "pattern",
    "stringEnumeration",
    "numberEnumeration",
    "dateEnumeration",
    "numberRange",
    "dateRange"
})
public class AdditionalAttributeDescription {

    @XmlElement(required = true)
    protected String name;
    protected String pattern;
    protected StringEnumeration stringEnumeration;
    protected NumberEnumeration numberEnumeration;
    protected DateEnumeration dateEnumeration;
    protected NumberRange numberRange;
    protected DateRange dateRange;

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

    /**
     * Gets the value of the stringEnumeration property.
     * 
     * @return
     *     possible object is
     *     {@link StringEnumeration }
     *     
     */
    public StringEnumeration getStringEnumeration() {
        return stringEnumeration;
    }

    /**
     * Sets the value of the stringEnumeration property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringEnumeration }
     *     
     */
    public void setStringEnumeration(StringEnumeration value) {
        this.stringEnumeration = value;
    }

    /**
     * Gets the value of the numberEnumeration property.
     * 
     * @return
     *     possible object is
     *     {@link NumberEnumeration }
     *     
     */
    public NumberEnumeration getNumberEnumeration() {
        return numberEnumeration;
    }

    /**
     * Sets the value of the numberEnumeration property.
     * 
     * @param value
     *     allowed object is
     *     {@link NumberEnumeration }
     *     
     */
    public void setNumberEnumeration(NumberEnumeration value) {
        this.numberEnumeration = value;
    }

    /**
     * Gets the value of the dateEnumeration property.
     * 
     * @return
     *     possible object is
     *     {@link DateEnumeration }
     *     
     */
    public DateEnumeration getDateEnumeration() {
        return dateEnumeration;
    }

    /**
     * Sets the value of the dateEnumeration property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateEnumeration }
     *     
     */
    public void setDateEnumeration(DateEnumeration value) {
        this.dateEnumeration = value;
    }

    /**
     * Gets the value of the numberRange property.
     * 
     * @return
     *     possible object is
     *     {@link NumberRange }
     *     
     */
    public NumberRange getNumberRange() {
        return numberRange;
    }

    /**
     * Sets the value of the numberRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link NumberRange }
     *     
     */
    public void setNumberRange(NumberRange value) {
        this.numberRange = value;
    }

    /**
     * Gets the value of the dateRange property.
     * 
     * @return
     *     possible object is
     *     {@link DateRange }
     *     
     */
    public DateRange getDateRange() {
        return dateRange;
    }

    /**
     * Sets the value of the dateRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateRange }
     *     
     */
    public void setDateRange(DateRange value) {
        this.dateRange = value;
    }

}
