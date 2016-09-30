
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountReference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{http://schema.charging.ws.highdeal.com/}AccountReferenceType"/>
 *         &lt;element name="id" type="{http://schema.ws.highdeal.com/}String256"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountReference", propOrder = {
    "type",
    "id"
})
public class AccountReference {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected AccountReferenceType type;
    @XmlElement(required = true)
    protected String id;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link AccountReferenceType }
     *     
     */
    public AccountReferenceType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReferenceType }
     *     
     */
    public void setType(AccountReferenceType value) {
        this.type = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
