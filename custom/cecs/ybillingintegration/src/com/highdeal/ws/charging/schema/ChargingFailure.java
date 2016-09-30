
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargingFailure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargingFailure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schema.charging.ws.highdeal.com/}Failure">
 *       &lt;sequence>
 *         &lt;element name="reason" type="{http://schema.charging.ws.highdeal.com/}ChargingFailureReason" minOccurs="0"/>
 *         &lt;element name="chargeableItem" type="{http://schema.charging.ws.highdeal.com/}ChargeableItem"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargingFailure", propOrder = {
    "reason",
    "chargeableItem"
})
public class ChargingFailure
    extends Failure
{

    @XmlSchemaType(name = "string")
    protected ChargingFailureReason reason;
    @XmlElement(required = true)
    protected ChargeableItem chargeableItem;

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link ChargingFailureReason }
     *     
     */
    public ChargingFailureReason getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingFailureReason }
     *     
     */
    public void setReason(ChargingFailureReason value) {
        this.reason = value;
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
