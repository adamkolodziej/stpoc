
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrmtIsxItemConfigApi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrmtIsxItemConfigApi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Config" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxConfigApiT"/>
 *         &lt;element name="TechRes" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxTechResApiT"/>
 *         &lt;element name="Discount" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxDiscountApiT"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrmtIsxItemConfigApi", propOrder = {
    "config",
    "techRes",
    "discount"
})
public class CrmtIsxItemConfigApi {

    @XmlElement(name = "Config", required = true)
    protected CrmtIsxConfigApiT config;
    @XmlElement(name = "TechRes", required = true)
    protected CrmtIsxTechResApiT techRes;
    @XmlElement(name = "Discount", required = true)
    protected CrmtIsxDiscountApiT discount;

    /**
     * Gets the value of the config property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxConfigApiT }
     *     
     */
    public CrmtIsxConfigApiT getConfig() {
        return config;
    }

    /**
     * Sets the value of the config property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxConfigApiT }
     *     
     */
    public void setConfig(CrmtIsxConfigApiT value) {
        this.config = value;
    }

    /**
     * Gets the value of the techRes property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxTechResApiT }
     *     
     */
    public CrmtIsxTechResApiT getTechRes() {
        return techRes;
    }

    /**
     * Sets the value of the techRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxTechResApiT }
     *     
     */
    public void setTechRes(CrmtIsxTechResApiT value) {
        this.techRes = value;
    }

    /**
     * Gets the value of the discount property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxDiscountApiT }
     *     
     */
    public CrmtIsxDiscountApiT getDiscount() {
        return discount;
    }

    /**
     * Sets the value of the discount property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxDiscountApiT }
     *     
     */
    public void setDiscount(CrmtIsxDiscountApiT value) {
        this.discount = value;
    }

}
