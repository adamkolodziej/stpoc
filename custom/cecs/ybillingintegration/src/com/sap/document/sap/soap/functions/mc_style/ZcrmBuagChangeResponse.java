
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
 *         &lt;element name="Return" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiret2"/>
 *         &lt;element name="Tppaccdata" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapibus1006130PpaccData" minOccurs="0"/>
 *         &lt;element name="Tppaccdatax" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapibus1006130PpaccDataX" minOccurs="0"/>
 *         &lt;element name="Treldata" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapibus1006130RelData" minOccurs="0"/>
 *         &lt;element name="Treldatax" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapibus1006130RelDataX" minOccurs="0"/>
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
    "_return",
    "tppaccdata",
    "tppaccdatax",
    "treldata",
    "treldatax"
})
@XmlRootElement(name = "ZcrmBuagChangeResponse")
public class ZcrmBuagChangeResponse {

    @XmlElement(name = "Return", required = true)
    protected TableOfBapiret2 _return;
    @XmlElement(name = "Tppaccdata")
    protected TableOfBapibus1006130PpaccData tppaccdata;
    @XmlElement(name = "Tppaccdatax")
    protected TableOfBapibus1006130PpaccDataX tppaccdatax;
    @XmlElement(name = "Treldata")
    protected TableOfBapibus1006130RelData treldata;
    @XmlElement(name = "Treldatax")
    protected TableOfBapibus1006130RelDataX treldatax;

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
     * Gets the value of the tppaccdata property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapibus1006130PpaccData }
     *     
     */
    public TableOfBapibus1006130PpaccData getTppaccdata() {
        return tppaccdata;
    }

    /**
     * Sets the value of the tppaccdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapibus1006130PpaccData }
     *     
     */
    public void setTppaccdata(TableOfBapibus1006130PpaccData value) {
        this.tppaccdata = value;
    }

    /**
     * Gets the value of the tppaccdatax property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapibus1006130PpaccDataX }
     *     
     */
    public TableOfBapibus1006130PpaccDataX getTppaccdatax() {
        return tppaccdatax;
    }

    /**
     * Sets the value of the tppaccdatax property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapibus1006130PpaccDataX }
     *     
     */
    public void setTppaccdatax(TableOfBapibus1006130PpaccDataX value) {
        this.tppaccdatax = value;
    }

    /**
     * Gets the value of the treldata property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapibus1006130RelData }
     *     
     */
    public TableOfBapibus1006130RelData getTreldata() {
        return treldata;
    }

    /**
     * Sets the value of the treldata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapibus1006130RelData }
     *     
     */
    public void setTreldata(TableOfBapibus1006130RelData value) {
        this.treldata = value;
    }

    /**
     * Gets the value of the treldatax property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapibus1006130RelDataX }
     *     
     */
    public TableOfBapibus1006130RelDataX getTreldatax() {
        return treldatax;
    }

    /**
     * Sets the value of the treldatax property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapibus1006130RelDataX }
     *     
     */
    public void setTreldatax(TableOfBapibus1006130RelDataX value) {
        this.treldatax = value;
    }

}
