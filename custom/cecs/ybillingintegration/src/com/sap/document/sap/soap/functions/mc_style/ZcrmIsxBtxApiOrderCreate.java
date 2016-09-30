
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
 *         &lt;element name="IsControl" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxControlApi" minOccurs="0"/>
 *         &lt;element name="IsOrderData" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxProviderCreateApi" minOccurs="0"/>
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
    "isControl",
    "isOrderData"
})
@XmlRootElement(name = "ZcrmIsxBtxApiOrderCreate")
public class ZcrmIsxBtxApiOrderCreate {

    @XmlElement(name = "IsControl")
    protected CrmtIsxControlApi isControl;
    @XmlElement(name = "IsOrderData")
    protected CrmtIsxProviderCreateApi isOrderData;

    /**
     * Gets the value of the isControl property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxControlApi }
     *     
     */
    public CrmtIsxControlApi getIsControl() {
        return isControl;
    }

    /**
     * Sets the value of the isControl property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxControlApi }
     *     
     */
    public void setIsControl(CrmtIsxControlApi value) {
        this.isControl = value;
    }

    /**
     * Gets the value of the isOrderData property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxProviderCreateApi }
     *     
     */
    public CrmtIsxProviderCreateApi getIsOrderData() {
        return isOrderData;
    }

    /**
     * Sets the value of the isOrderData property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxProviderCreateApi }
     *     
     */
    public void setIsOrderData(CrmtIsxProviderCreateApi value) {
        this.isOrderData = value;
    }

}
