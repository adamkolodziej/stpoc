
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CardNumber" type="{urn:sap-com:document:sap:rfc:functions}char25"/>
 *         &lt;element name="CardType" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Data" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1186MasterData"/>
 *         &lt;element name="Return" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiret2"/>
 *         &lt;element name="SaveDirect" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cardNumber",
    "cardType",
    "data",
    "_return",
    "saveDirect"
})
@XmlRootElement(name = "ZbapiPcaMasterCreate")
public class ZbapiPcaMasterCreate {

    @XmlElement(name = "CardNumber", required = true)
    protected String cardNumber;
    @XmlElement(name = "CardType", required = true)
    protected String cardType;
    @XmlElement(name = "Data", required = true)
    protected Bapibus1186MasterData data;
    @XmlElement(name = "Return", required = true)
    protected TableOfBapiret2 _return;
    @XmlElement(name = "SaveDirect")
    protected String saveDirect;

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
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1186MasterData }
     *     
     */
    public Bapibus1186MasterData getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1186MasterData }
     *     
     */
    public void setData(Bapibus1186MasterData value) {
        this.data = value;
    }

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiret2 }
     *     
     */
    public TableOfBapiret2 getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiret2 }
     *     
     */
    public void setReturn(TableOfBapiret2 value) {
        this._return = value;
    }

    /**
     * Gets the value of the saveDirect property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaveDirect() {
        return saveDirect;
    }

    /**
     * Sets the value of the saveDirect property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaveDirect(String value) {
        this.saveDirect = value;
    }

}
