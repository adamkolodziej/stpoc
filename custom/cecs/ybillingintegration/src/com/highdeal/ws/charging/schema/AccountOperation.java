
package com.highdeal.ws.charging.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import com.highdeal.ws.schema.Amount;


/**
 * <p>Java class for AccountOperation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountOperation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{http://schema.charging.ws.highdeal.com/}AccountOperationType"/>
 *         &lt;element name="amount" type="{http://schema.ws.highdeal.com/}Amount" minOccurs="0"/>
 *         &lt;element name="netAmount" type="{http://schema.ws.highdeal.com/}Amount" minOccurs="0"/>
 *         &lt;element name="taxAmount" type="{http://schema.ws.highdeal.com/}Amount" minOccurs="0"/>
 *         &lt;element name="mainAccountReference" type="{http://schema.charging.ws.highdeal.com/}AccountReference" minOccurs="0"/>
 *         &lt;element name="amountAssignment" type="{http://schema.charging.ws.highdeal.com/}AmountAssignment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dependentAccountOperationKey" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountOperation", propOrder = {
    "type",
    "amount",
    "netAmount",
    "taxAmount",
    "mainAccountReference",
    "amountAssignment",
    "key",
    "dependentAccountOperationKey"
})
public class AccountOperation {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected AccountOperationType type;
    protected Amount amount;
    protected Amount netAmount;
    protected Amount taxAmount;
    protected AccountReference mainAccountReference;
    protected List<AmountAssignment> amountAssignment;
    protected int key;
    protected Integer dependentAccountOperationKey;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link AccountOperationType }
     *     
     */
    public AccountOperationType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountOperationType }
     *     
     */
    public void setType(AccountOperationType value) {
        this.type = value;
    }

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
     * Gets the value of the netAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getNetAmount() {
        return netAmount;
    }

    /**
     * Sets the value of the netAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setNetAmount(Amount value) {
        this.netAmount = value;
    }

    /**
     * Gets the value of the taxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTaxAmount() {
        return taxAmount;
    }

    /**
     * Sets the value of the taxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTaxAmount(Amount value) {
        this.taxAmount = value;
    }

    /**
     * Gets the value of the mainAccountReference property.
     * 
     * @return
     *     possible object is
     *     {@link AccountReference }
     *     
     */
    public AccountReference getMainAccountReference() {
        return mainAccountReference;
    }

    /**
     * Sets the value of the mainAccountReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountReference }
     *     
     */
    public void setMainAccountReference(AccountReference value) {
        this.mainAccountReference = value;
    }

    /**
     * Gets the value of the amountAssignment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amountAssignment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmountAssignment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AmountAssignment }
     * 
     * 
     */
    public List<AmountAssignment> getAmountAssignment() {
        if (amountAssignment == null) {
            amountAssignment = new ArrayList<AmountAssignment>();
        }
        return this.amountAssignment;
    }

    /**
     * Gets the value of the key property.
     * 
     */
    public int getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     */
    public void setKey(int value) {
        this.key = value;
    }

    /**
     * Gets the value of the dependentAccountOperationKey property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDependentAccountOperationKey() {
        return dependentAccountOperationKey;
    }

    /**
     * Sets the value of the dependentAccountOperationKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDependentAccountOperationKey(Integer value) {
        this.dependentAccountOperationKey = value;
    }

}
