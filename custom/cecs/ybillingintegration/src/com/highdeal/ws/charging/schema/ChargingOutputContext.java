
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargingOutputContext complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargingOutputContext">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chargeableItemExported" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargingOutputContext", propOrder = {
    "chargeableItemExported"
})
public class ChargingOutputContext {

    @XmlElement(defaultValue = "true")
    protected Boolean chargeableItemExported;

    /**
     * Gets the value of the chargeableItemExported property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChargeableItemExported() {
        return chargeableItemExported;
    }

    /**
     * Sets the value of the chargeableItemExported property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChargeableItemExported(Boolean value) {
        this.chargeableItemExported = value;
    }

}
