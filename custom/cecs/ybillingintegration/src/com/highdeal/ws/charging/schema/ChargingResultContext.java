
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargingResultContext complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargingResultContext">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chargingProcessInfoReturned" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="chargingContractInfoReturned" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="chargeableItemInfoReturned" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="accountOperationReturned" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="accountInfoReturned" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="chargedItemReturned" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="notificationReturned" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargingResultContext", propOrder = {
    "chargingProcessInfoReturned",
    "chargingContractInfoReturned",
    "chargeableItemInfoReturned",
    "accountOperationReturned",
    "accountInfoReturned",
    "chargedItemReturned",
    "notificationReturned"
})
public class ChargingResultContext {

    @XmlElement(defaultValue = "false")
    protected Boolean chargingProcessInfoReturned;
    @XmlElement(defaultValue = "false")
    protected Boolean chargingContractInfoReturned;
    @XmlElement(defaultValue = "false")
    protected Boolean chargeableItemInfoReturned;
    @XmlElement(defaultValue = "false")
    protected Boolean accountOperationReturned;
    @XmlElement(defaultValue = "false")
    protected Boolean accountInfoReturned;
    @XmlElement(defaultValue = "false")
    protected Boolean chargedItemReturned;
    @XmlElement(defaultValue = "false")
    protected Boolean notificationReturned;

    /**
     * Gets the value of the chargingProcessInfoReturned property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChargingProcessInfoReturned() {
        return chargingProcessInfoReturned;
    }

    /**
     * Sets the value of the chargingProcessInfoReturned property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChargingProcessInfoReturned(Boolean value) {
        this.chargingProcessInfoReturned = value;
    }

    /**
     * Gets the value of the chargingContractInfoReturned property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChargingContractInfoReturned() {
        return chargingContractInfoReturned;
    }

    /**
     * Sets the value of the chargingContractInfoReturned property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChargingContractInfoReturned(Boolean value) {
        this.chargingContractInfoReturned = value;
    }

    /**
     * Gets the value of the chargeableItemInfoReturned property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChargeableItemInfoReturned() {
        return chargeableItemInfoReturned;
    }

    /**
     * Sets the value of the chargeableItemInfoReturned property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChargeableItemInfoReturned(Boolean value) {
        this.chargeableItemInfoReturned = value;
    }

    /**
     * Gets the value of the accountOperationReturned property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAccountOperationReturned() {
        return accountOperationReturned;
    }

    /**
     * Sets the value of the accountOperationReturned property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAccountOperationReturned(Boolean value) {
        this.accountOperationReturned = value;
    }

    /**
     * Gets the value of the accountInfoReturned property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAccountInfoReturned() {
        return accountInfoReturned;
    }

    /**
     * Sets the value of the accountInfoReturned property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAccountInfoReturned(Boolean value) {
        this.accountInfoReturned = value;
    }

    /**
     * Gets the value of the chargedItemReturned property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChargedItemReturned() {
        return chargedItemReturned;
    }

    /**
     * Sets the value of the chargedItemReturned property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChargedItemReturned(Boolean value) {
        this.chargedItemReturned = value;
    }

    /**
     * Gets the value of the notificationReturned property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNotificationReturned() {
        return notificationReturned;
    }

    /**
     * Sets the value of the notificationReturned property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNotificationReturned(Boolean value) {
        this.notificationReturned = value;
    }

}
