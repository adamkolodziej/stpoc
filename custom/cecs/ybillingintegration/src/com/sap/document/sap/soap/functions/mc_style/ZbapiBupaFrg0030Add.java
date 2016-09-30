
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
 *         &lt;element name="Data" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006030Pricing" minOccurs="0"/>
 *         &lt;element name="Return" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiret2"/>
 *         &lt;element name="SalesArea" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006SalesArea" minOccurs="0"/>
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
    "data",
    "_return",
    "salesArea"
})
@XmlRootElement(name = "ZbapiBupaFrg0030Add")
public class ZbapiBupaFrg0030Add {

    @XmlElement(name = "Businesspartner", required = true)
    protected String businesspartner;
    @XmlElement(name = "Data")
    protected Bapibus1006030Pricing data;
    @XmlElement(name = "Return", required = true)
    protected TableOfBapiret2 _return;
    @XmlElement(name = "SalesArea")
    protected Bapibus1006SalesArea salesArea;

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
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006030Pricing }
     *     
     */
    public Bapibus1006030Pricing getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006030Pricing }
     *     
     */
    public void setData(Bapibus1006030Pricing value) {
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
     * Gets the value of the salesArea property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006SalesArea }
     *     
     */
    public Bapibus1006SalesArea getSalesArea() {
        return salesArea;
    }

    /**
     * Sets the value of the salesArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006SalesArea }
     *     
     */
    public void setSalesArea(Bapibus1006SalesArea value) {
        this.salesArea = value;
    }

}
