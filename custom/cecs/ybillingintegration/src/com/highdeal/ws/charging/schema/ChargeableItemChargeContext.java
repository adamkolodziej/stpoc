
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargeableItemChargeContext complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargeableItemChargeContext">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="senderInfo" type="{http://schema.charging.ws.highdeal.com/}SenderContext" minOccurs="0"/>
 *         &lt;element name="chargingOutputContext" type="{http://schema.charging.ws.highdeal.com/}ChargingOutputContext" minOccurs="0"/>
 *         &lt;element name="chargingResultContext" type="{http://schema.charging.ws.highdeal.com/}ChargingResultContext" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargeableItemChargeContext", propOrder = {
    "senderInfo",
    "chargingOutputContext",
    "chargingResultContext"
})
public class ChargeableItemChargeContext {

    protected SenderContext senderInfo;
    protected ChargingOutputContext chargingOutputContext;
    protected ChargingResultContext chargingResultContext;

    /**
     * Gets the value of the senderInfo property.
     * 
     * @return
     *     possible object is
     *     {@link SenderContext }
     *     
     */
    public SenderContext getSenderInfo() {
        return senderInfo;
    }

    /**
     * Sets the value of the senderInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SenderContext }
     *     
     */
    public void setSenderInfo(SenderContext value) {
        this.senderInfo = value;
    }

    /**
     * Gets the value of the chargingOutputContext property.
     * 
     * @return
     *     possible object is
     *     {@link ChargingOutputContext }
     *     
     */
    public ChargingOutputContext getChargingOutputContext() {
        return chargingOutputContext;
    }

    /**
     * Sets the value of the chargingOutputContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingOutputContext }
     *     
     */
    public void setChargingOutputContext(ChargingOutputContext value) {
        this.chargingOutputContext = value;
    }

    /**
     * Gets the value of the chargingResultContext property.
     * 
     * @return
     *     possible object is
     *     {@link ChargingResultContext }
     *     
     */
    public ChargingResultContext getChargingResultContext() {
        return chargingResultContext;
    }

    /**
     * Sets the value of the chargingResultContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingResultContext }
     *     
     */
    public void setChargingResultContext(ChargingResultContext value) {
        this.chargingResultContext = value;
    }

}
