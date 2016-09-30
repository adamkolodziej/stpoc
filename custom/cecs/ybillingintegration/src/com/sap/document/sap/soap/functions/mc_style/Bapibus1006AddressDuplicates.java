
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapibus1006AddressDuplicates complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapibus1006AddressDuplicates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Addrnumber" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Persnumber" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Percentage" type="{urn:sap-com:document:sap:rfc:functions}decimal3.1"/>
 *         &lt;element name="Businesspartner" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Addressguid" type="{urn:sap-com:document:sap:rfc:functions}char32"/>
 *         &lt;element name="PercentCompl" type="{urn:sap-com:document:sap:rfc:functions}decimal4.1"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapibus1006AddressDuplicates", propOrder = {
    "addrnumber",
    "persnumber",
    "percentage",
    "businesspartner",
    "addressguid",
    "percentCompl"
})
public class Bapibus1006AddressDuplicates {

    @XmlElement(name = "Addrnumber", required = true)
    protected String addrnumber;
    @XmlElement(name = "Persnumber", required = true)
    protected String persnumber;
    @XmlElement(name = "Percentage", required = true)
    protected BigDecimal percentage;
    @XmlElement(name = "Businesspartner", required = true)
    protected String businesspartner;
    @XmlElement(name = "Addressguid", required = true)
    protected String addressguid;
    @XmlElement(name = "PercentCompl", required = true)
    protected BigDecimal percentCompl;

    /**
     * Gets the value of the addrnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrnumber() {
        return addrnumber;
    }

    /**
     * Sets the value of the addrnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrnumber(String value) {
        this.addrnumber = value;
    }

    /**
     * Gets the value of the persnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersnumber() {
        return persnumber;
    }

    /**
     * Sets the value of the persnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersnumber(String value) {
        this.persnumber = value;
    }

    /**
     * Gets the value of the percentage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPercentage() {
        return percentage;
    }

    /**
     * Sets the value of the percentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPercentage(BigDecimal value) {
        this.percentage = value;
    }

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
     * Gets the value of the addressguid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressguid() {
        return addressguid;
    }

    /**
     * Sets the value of the addressguid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressguid(String value) {
        this.addressguid = value;
    }

    /**
     * Gets the value of the percentCompl property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPercentCompl() {
        return percentCompl;
    }

    /**
     * Sets the value of the percentCompl property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPercentCompl(BigDecimal value) {
        this.percentCompl = value;
    }

}
