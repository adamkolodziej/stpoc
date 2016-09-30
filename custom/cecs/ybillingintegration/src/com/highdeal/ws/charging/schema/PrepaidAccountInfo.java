
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.highdeal.ws.schema.Amount;


/**
 * <p>Java class for PrepaidAccountInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrepaidAccountInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://schema.ws.highdeal.com/}String256"/>
 *         &lt;element name="balance" type="{http://schema.ws.highdeal.com/}Amount"/>
 *         &lt;element name="stateSchedule" type="{http://schema.charging.ws.highdeal.com/}StateSchedule"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrepaidAccountInfo", propOrder = {
    "id",
    "balance",
    "stateSchedule"
})
public class PrepaidAccountInfo {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected Amount balance;
    @XmlElement(required = true)
    protected StateSchedule stateSchedule;

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

    /**
     * Gets the value of the balance property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getBalance() {
        return balance;
    }

    /**
     * Sets the value of the balance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setBalance(Amount value) {
        this.balance = value;
    }

    /**
     * Gets the value of the stateSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link StateSchedule }
     *     
     */
    public StateSchedule getStateSchedule() {
        return stateSchedule;
    }

    /**
     * Sets the value of the stateSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateSchedule }
     *     
     */
    public void setStateSchedule(StateSchedule value) {
        this.stateSchedule = value;
    }

}
