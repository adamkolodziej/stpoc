
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrmtIsxDiscountApi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrmtIsxDiscountApi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DiscKey" type="{urn:sap-com:document:sap:rfc:functions}char8"/>
 *         &lt;element name="ValidFrom" type="{urn:sap-com:document:sap:rfc:functions}decimal15.0"/>
 *         &lt;element name="ValidTo" type="{urn:sap-com:document:sap:rfc:functions}decimal15.0"/>
 *         &lt;element name="ContRel" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrmtIsxDiscountApi", propOrder = {
    "discKey",
    "validFrom",
    "validTo",
    "contRel"
})
public class CrmtIsxDiscountApi {

    @XmlElement(name = "DiscKey", required = true)
    protected String discKey;
    @XmlElement(name = "ValidFrom", required = true)
    protected BigDecimal validFrom;
    @XmlElement(name = "ValidTo", required = true)
    protected BigDecimal validTo;
    @XmlElement(name = "ContRel", required = true)
    protected String contRel;

    /**
     * Gets the value of the discKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscKey() {
        return discKey;
    }

    /**
     * Sets the value of the discKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscKey(String value) {
        this.discKey = value;
    }

    /**
     * Gets the value of the validFrom property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValidFrom() {
        return validFrom;
    }

    /**
     * Sets the value of the validFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValidFrom(BigDecimal value) {
        this.validFrom = value;
    }

    /**
     * Gets the value of the validTo property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValidTo() {
        return validTo;
    }

    /**
     * Sets the value of the validTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValidTo(BigDecimal value) {
        this.validTo = value;
    }

    /**
     * Gets the value of the contRel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContRel() {
        return contRel;
    }

    /**
     * Sets the value of the contRel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContRel(String value) {
        this.contRel = value;
    }

}
