
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargingContractInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargingContractInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subscriberAccountId" type="{http://schema.ws.highdeal.com/}NotEmptyString256"/>
 *         &lt;element name="chargingContractId" type="{http://schema.ws.highdeal.com/}NotEmptyString256"/>
 *         &lt;element name="chargingContractItemId" type="{http://schema.ws.highdeal.com/}String256"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargingContractInfo", propOrder = {
    "subscriberAccountId",
    "chargingContractId",
    "chargingContractItemId"
})
public class ChargingContractInfo {

    @XmlElement(required = true)
    protected String subscriberAccountId;
    @XmlElement(required = true)
    protected String chargingContractId;
    @XmlElement(required = true)
    protected String chargingContractItemId;

    /**
     * Gets the value of the subscriberAccountId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberAccountId() {
        return subscriberAccountId;
    }

    /**
     * Sets the value of the subscriberAccountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberAccountId(String value) {
        this.subscriberAccountId = value;
    }

    /**
     * Gets the value of the chargingContractId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargingContractId() {
        return chargingContractId;
    }

    /**
     * Sets the value of the chargingContractId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargingContractId(String value) {
        this.chargingContractId = value;
    }

    /**
     * Gets the value of the chargingContractItemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargingContractItemId() {
        return chargingContractItemId;
    }

    /**
     * Sets the value of the chargingContractItemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargingContractItemId(String value) {
        this.chargingContractItemId = value;
    }

}
