
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapibus1006PcardData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapibus1006PcardData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CardType" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="CardNumber" type="{urn:sap-com:document:sap:rfc:functions}char25"/>
 *         &lt;element name="CardDefault" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Creditcardname" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapibus1006PcardData", propOrder = {
    "cardType",
    "cardNumber",
    "cardDefault",
    "creditcardname"
})
public class Bapibus1006PcardData {

    @XmlElement(name = "CardType", required = true)
    protected String cardType;
    @XmlElement(name = "CardNumber", required = true)
    protected String cardNumber;
    @XmlElement(name = "CardDefault", required = true)
    protected String cardDefault;
    @XmlElement(name = "Creditcardname", required = true)
    protected String creditcardname;

    /**
     * Gets the value of the cardType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Sets the value of the cardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardType(String value) {
        this.cardType = value;
    }

    /**
     * Gets the value of the cardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the value of the cardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardNumber(String value) {
        this.cardNumber = value;
    }

    /**
     * Gets the value of the cardDefault property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardDefault() {
        return cardDefault;
    }

    /**
     * Sets the value of the cardDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardDefault(String value) {
        this.cardDefault = value;
    }

    /**
     * Gets the value of the creditcardname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditcardname() {
        return creditcardname;
    }

    /**
     * Sets the value of the creditcardname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditcardname(String value) {
        this.creditcardname = value;
    }

}
