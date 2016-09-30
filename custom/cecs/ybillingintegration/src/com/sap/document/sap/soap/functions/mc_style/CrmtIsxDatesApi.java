
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrmtIsxDatesApi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrmtIsxDatesApi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ContractStart" type="{urn:sap-com:document:sap:rfc:functions}decimal15.0"/>
 *         &lt;element name="Timezone" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
 *         &lt;element name="ContractDuration" type="{urn:sap-com:document:sap:rfc:functions}decimal13.0"/>
 *         &lt;element name="DurationUnit" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="ContractEnd" type="{urn:sap-com:document:sap:rfc:functions}decimal15.0"/>
 *         &lt;element name="RuntimeEnd" type="{urn:sap-com:document:sap:rfc:functions}decimal15.0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrmtIsxDatesApi", propOrder = {
    "contractStart",
    "timezone",
    "contractDuration",
    "durationUnit",
    "contractEnd",
    "runtimeEnd"
})
public class CrmtIsxDatesApi {

    @XmlElement(name = "ContractStart", required = true)
    protected BigDecimal contractStart;
    @XmlElement(name = "Timezone", required = true)
    protected String timezone;
    @XmlElement(name = "ContractDuration", required = true)
    protected BigDecimal contractDuration;
    @XmlElement(name = "DurationUnit", required = true)
    protected String durationUnit;
    @XmlElement(name = "ContractEnd", required = true)
    protected BigDecimal contractEnd;
    @XmlElement(name = "RuntimeEnd", required = true)
    protected BigDecimal runtimeEnd;

    /**
     * Gets the value of the contractStart property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getContractStart() {
        return contractStart;
    }

    /**
     * Sets the value of the contractStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setContractStart(BigDecimal value) {
        this.contractStart = value;
    }

    /**
     * Gets the value of the timezone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Sets the value of the timezone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimezone(String value) {
        this.timezone = value;
    }

    /**
     * Gets the value of the contractDuration property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getContractDuration() {
        return contractDuration;
    }

    /**
     * Sets the value of the contractDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setContractDuration(BigDecimal value) {
        this.contractDuration = value;
    }

    /**
     * Gets the value of the durationUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDurationUnit() {
        return durationUnit;
    }

    /**
     * Sets the value of the durationUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDurationUnit(String value) {
        this.durationUnit = value;
    }

    /**
     * Gets the value of the contractEnd property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getContractEnd() {
        return contractEnd;
    }

    /**
     * Sets the value of the contractEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setContractEnd(BigDecimal value) {
        this.contractEnd = value;
    }

    /**
     * Gets the value of the runtimeEnd property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRuntimeEnd() {
        return runtimeEnd;
    }

    /**
     * Sets the value of the runtimeEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRuntimeEnd(BigDecimal value) {
        this.runtimeEnd = value;
    }

}
