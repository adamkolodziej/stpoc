
package com.highdeal.ws.charging.schema;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Field complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Field">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://schema.ws.highdeal.com/}String256"/>
 *         &lt;choice>
 *           &lt;element name="stringValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="numberValue" type="{http://schema.ws.highdeal.com/}Decimal28_6"/>
 *           &lt;element name="dateValue" type="{http://schema.ws.highdeal.com/}Date"/>
 *           &lt;element name="booleanValue" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "Field", propOrder = {
    "name",
    "stringValue",
    "numberValue",
    "dateValue",
    "booleanValue"
})
public class Field {

    @XmlElement(required = true)
    protected String name;
    protected String stringValue;
    protected BigDecimal numberValue;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateValue;
    protected Boolean booleanValue;

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
     * Gets the value of the stringValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringValue() {
        return stringValue;
    }

    /**
     * Sets the value of the stringValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringValue(String value) {
        this.stringValue = value;
    }

    /**
     * Gets the value of the numberValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumberValue() {
        return numberValue;
    }

    /**
     * Sets the value of the numberValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumberValue(BigDecimal value) {
        this.numberValue = value;
    }

    /**
     * Gets the value of the dateValue property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateValue() {
        return dateValue;
    }

    /**
     * Sets the value of the dateValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateValue(XMLGregorianCalendar value) {
        this.dateValue = value;
    }

    /**
     * Gets the value of the booleanValue property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBooleanValue() {
        return booleanValue;
    }

    /**
     * Sets the value of the booleanValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBooleanValue(Boolean value) {
        this.booleanValue = value;
    }

}
