
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargeableItemChargeRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargeableItemChargeRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="context" type="{http://schema.charging.ws.highdeal.com/}ChargeableItemChargeContext" minOccurs="0"/>
 *         &lt;element name="chargeableItem" type="{http://schema.charging.ws.highdeal.com/}ChargeableItem"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargeableItemChargeRequest", propOrder = {
    "context",
    "chargeableItem"
})
public class ChargeableItemChargeRequest {

    protected ChargeableItemChargeContext context;
    @XmlElement(required = true)
    protected ChargeableItem chargeableItem;

    /**
     * Gets the value of the context property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeableItemChargeContext }
     *     
     */
    public ChargeableItemChargeContext getContext() {
        return context;
    }

    /**
     * Sets the value of the context property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeableItemChargeContext }
     *     
     */
    public void setContext(ChargeableItemChargeContext value) {
        this.context = value;
    }

    /**
     * Gets the value of the chargeableItem property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeableItem }
     *     
     */
    public ChargeableItem getChargeableItem() {
        return chargeableItem;
    }

    /**
     * Sets the value of the chargeableItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeableItem }
     *     
     */
    public void setChargeableItem(ChargeableItem value) {
        this.chargeableItem = value;
    }

}
