
package com.highdeal.ws.charging.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargingResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargingResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chargingProcessInfo" type="{http://schema.charging.ws.highdeal.com/}ChargingProcessInfo" minOccurs="0"/>
 *         &lt;element name="chargingContractInfo" type="{http://schema.charging.ws.highdeal.com/}ChargingContractInfo" minOccurs="0"/>
 *         &lt;element name="chargeableItemInfo" type="{http://schema.charging.ws.highdeal.com/}ChargeableItemInfo" minOccurs="0"/>
 *         &lt;element name="accountOperation" type="{http://schema.charging.ws.highdeal.com/}AccountOperation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="externalAccountInfo" type="{http://schema.charging.ws.highdeal.com/}ExternalAccountInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="prepaidAccountInfo" type="{http://schema.charging.ws.highdeal.com/}PrepaidAccountInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="chargedItem" type="{http://schema.charging.ws.highdeal.com/}ChargedItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="notification" type="{http://schema.charging.ws.highdeal.com/}Notification" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargingResult", propOrder = {
    "chargingProcessInfo",
    "chargingContractInfo",
    "chargeableItemInfo",
    "accountOperation",
    "externalAccountInfo",
    "prepaidAccountInfo",
    "chargedItem",
    "notification"
})
public class ChargingResult {

    protected ChargingProcessInfo chargingProcessInfo;
    protected ChargingContractInfo chargingContractInfo;
    protected ChargeableItemInfo chargeableItemInfo;
    protected List<AccountOperation> accountOperation;
    protected List<ExternalAccountInfo> externalAccountInfo;
    protected List<PrepaidAccountInfo> prepaidAccountInfo;
    protected List<ChargedItem> chargedItem;
    protected List<Notification> notification;

    /**
     * Gets the value of the chargingProcessInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ChargingProcessInfo }
     *     
     */
    public ChargingProcessInfo getChargingProcessInfo() {
        return chargingProcessInfo;
    }

    /**
     * Sets the value of the chargingProcessInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingProcessInfo }
     *     
     */
    public void setChargingProcessInfo(ChargingProcessInfo value) {
        this.chargingProcessInfo = value;
    }

    /**
     * Gets the value of the chargingContractInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ChargingContractInfo }
     *     
     */
    public ChargingContractInfo getChargingContractInfo() {
        return chargingContractInfo;
    }

    /**
     * Sets the value of the chargingContractInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingContractInfo }
     *     
     */
    public void setChargingContractInfo(ChargingContractInfo value) {
        this.chargingContractInfo = value;
    }

    /**
     * Gets the value of the chargeableItemInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeableItemInfo }
     *     
     */
    public ChargeableItemInfo getChargeableItemInfo() {
        return chargeableItemInfo;
    }

    /**
     * Sets the value of the chargeableItemInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeableItemInfo }
     *     
     */
    public void setChargeableItemInfo(ChargeableItemInfo value) {
        this.chargeableItemInfo = value;
    }

    /**
     * Gets the value of the accountOperation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accountOperation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccountOperation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountOperation }
     * 
     * 
     */
    public List<AccountOperation> getAccountOperation() {
        if (accountOperation == null) {
            accountOperation = new ArrayList<AccountOperation>();
        }
        return this.accountOperation;
    }

    /**
     * Gets the value of the externalAccountInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the externalAccountInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExternalAccountInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExternalAccountInfo }
     * 
     * 
     */
    public List<ExternalAccountInfo> getExternalAccountInfo() {
        if (externalAccountInfo == null) {
            externalAccountInfo = new ArrayList<ExternalAccountInfo>();
        }
        return this.externalAccountInfo;
    }

    /**
     * Gets the value of the prepaidAccountInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prepaidAccountInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrepaidAccountInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrepaidAccountInfo }
     * 
     * 
     */
    public List<PrepaidAccountInfo> getPrepaidAccountInfo() {
        if (prepaidAccountInfo == null) {
            prepaidAccountInfo = new ArrayList<PrepaidAccountInfo>();
        }
        return this.prepaidAccountInfo;
    }

    /**
     * Gets the value of the chargedItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chargedItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChargedItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChargedItem }
     * 
     * 
     */
    public List<ChargedItem> getChargedItem() {
        if (chargedItem == null) {
            chargedItem = new ArrayList<ChargedItem>();
        }
        return this.chargedItem;
    }

    /**
     * Gets the value of the notification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Notification }
     * 
     * 
     */
    public List<Notification> getNotification() {
        if (notification == null) {
            notification = new ArrayList<Notification>();
        }
        return this.notification;
    }

}
