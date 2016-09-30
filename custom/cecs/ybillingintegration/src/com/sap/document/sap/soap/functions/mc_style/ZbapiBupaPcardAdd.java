
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
 *         &lt;element name="Businesspartner" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="CardId" type="{urn:sap-com:document:sap:rfc:functions}char6" minOccurs="0"/>
 *         &lt;element name="Data" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006PcardData"/>
 *         &lt;element name="Return" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiret2" minOccurs="0"/>
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
    "businesspartner",
    "cardId",
    "data",
    "_return"
})
@XmlRootElement(name = "ZbapiBupaPcardAdd")
public class ZbapiBupaPcardAdd {

    @XmlElement(name = "Businesspartner", required = true)
    protected String businesspartner;
    @XmlElement(name = "CardId")
    protected String cardId;
    @XmlElement(name = "Data", required = true)
    protected Bapibus1006PcardData data;
    @XmlElement(name = "Return")
    protected TableOfBapiret2 _return;

    /**
     * Gets the value of the businesspartner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinesspartner() {
        return businesspartner;
    }

    /**
     * Sets the value of the businesspartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinesspartner(String value) {
        this.businesspartner = value;
    }

    /**
     * Gets the value of the cardId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * Sets the value of the cardId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardId(String value) {
        this.cardId = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006PcardData }
     *     
     */
    public Bapibus1006PcardData getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006PcardData }
     *     
     */
    public void setData(Bapibus1006PcardData value) {
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

}
