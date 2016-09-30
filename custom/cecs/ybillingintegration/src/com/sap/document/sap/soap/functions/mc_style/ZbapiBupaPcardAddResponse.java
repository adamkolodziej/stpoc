
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
 *         &lt;element name="CardIdOut" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
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
    "cardIdOut",
    "_return"
})
@XmlRootElement(name = "ZbapiBupaPcardAddResponse")
public class ZbapiBupaPcardAddResponse {

    @XmlElement(name = "CardIdOut", required = true)
    protected String cardIdOut;
    @XmlElement(name = "Return")
    protected TableOfBapiret2 _return;

    /**
     * Gets the value of the cardIdOut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardIdOut() {
        return cardIdOut;
    }

    /**
     * Sets the value of the cardIdOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardIdOut(String value) {
        this.cardIdOut = value;
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
