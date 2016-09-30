
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.highdeal.ws.schema.Amount;


/**
 * <p>Java class for AmountAssignment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AmountAssignment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://schema.ws.highdeal.com/}Amount"/>
 *         &lt;element name="accountReference" type="{http://schema.charging.ws.highdeal.com/}AccountReference"/>
 *         &lt;element name="chargedItemKey" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AmountAssignment", propOrder = {
    "amount",
    "accountReference",
    "chargedItemKey"
})
public class AmountAssignment {

    @XmlElement(required = true)
    protected Amount amount;
    @XmlElement(required = true)
    protected AccountReference accountReference;
    protected Integer chargedItemKey;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAmount(Amount value) {
        this.amount = value;
    }

    /**
     * Gets the value of the accountReference property.
     * 
     * @return
     *     possible object is
     *     {@link AccountReference }
     *     
     */
    public AccountReference getAccountReference() {
        return accountReference;
    }

    /**
     * Sets the value of the accountReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReference }
     *     
     */
    public void setAccountReference(AccountReference value) {
        this.accountReference = value;
    }

    /**
     * Gets the value of the chargedItemKey property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getChargedItemKey() {
        return chargedItemKey;
    }

    /**
     * Sets the value of the chargedItemKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChargedItemKey(Integer value) {
        this.chargedItemKey = value;
    }

}
