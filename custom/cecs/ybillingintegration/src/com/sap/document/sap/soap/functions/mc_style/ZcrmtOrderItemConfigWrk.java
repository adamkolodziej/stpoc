
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZcrmtOrderItemConfigWrk complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZcrmtOrderItemConfigWrk">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderId" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="OrderItem" type="{urn:sap-com:document:sap:rfc:functions}numeric10"/>
 *         &lt;element name="Charc" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Value" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="CharcTxt" type="{urn:sap-com:document:sap:rfc:functions}char70"/>
 *         &lt;element name="ValueTxt" type="{urn:sap-com:document:sap:rfc:functions}char70"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZcrmtOrderItemConfigWrk", propOrder = {
    "orderId",
    "orderItem",
    "charc",
    "value",
    "charcTxt",
    "valueTxt"
})
public class ZcrmtOrderItemConfigWrk {

    @XmlElement(name = "OrderId", required = true)
    protected String orderId;
    @XmlElement(name = "OrderItem", required = true)
    protected String orderItem;
    @XmlElement(name = "Charc", required = true)
    protected String charc;
    @XmlElement(name = "Value", required = true)
    protected String value;
    @XmlElement(name = "CharcTxt", required = true)
    protected String charcTxt;
    @XmlElement(name = "ValueTxt", required = true)
    protected String valueTxt;

    /**
     * Gets the value of the orderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderId(String value) {
        this.orderId = value;
    }

    /**
     * Gets the value of the orderItem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItem() {
        return orderItem;
    }

    /**
     * Sets the value of the orderItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItem(String value) {
        this.orderItem = value;
    }

    /**
     * Gets the value of the charc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharc() {
        return charc;
    }

    /**
     * Sets the value of the charc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharc(String value) {
        this.charc = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the charcTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharcTxt() {
        return charcTxt;
    }

    /**
     * Sets the value of the charcTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharcTxt(String value) {
        this.charcTxt = value;
    }

    /**
     * Gets the value of the valueTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueTxt() {
        return valueTxt;
    }

    /**
     * Sets the value of the valueTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueTxt(String value) {
        this.valueTxt = value;
    }

}
