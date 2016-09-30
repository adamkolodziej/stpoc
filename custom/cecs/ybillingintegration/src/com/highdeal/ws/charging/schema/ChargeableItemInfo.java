
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargeableItemInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargeableItemInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uid" type="{http://schema.charging.ws.highdeal.com/}StringUniqueIdentifier"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargeableItemInfo", propOrder = {
    "uid"
})
public class ChargeableItemInfo {

    @XmlElement(required = true)
    protected StringUniqueIdentifier uid;

    /**
     * Gets the value of the uid property.
     * 
     * @return
     *     possible object is
     *     {@link StringUniqueIdentifier }
     *     
     */
    public StringUniqueIdentifier getUid() {
        return uid;
    }

    /**
     * Sets the value of the uid property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringUniqueIdentifier }
     *     
     */
    public void setUid(StringUniqueIdentifier value) {
        this.uid = value;
    }

}
