
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for SelectionByIndicator complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SelectionByIndicator">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InclusionExclusionCode" type="{http://sap.com/xi/AP/Common/GDT}InclusionExclusionCode" minOccurs="0"/>
 *         &lt;element name="IntervalBoundaryTypeCode" type="{http://sap.com/xi/AP/Common/GDT}IntervalBoundaryTypeCode"/>
 *         &lt;element name="LowerBoundaryIndicator" type="{http://sap.com/xi/BASIS/Global}Indicator" minOccurs="0"/>
 *         &lt;element name="UpperBoundaryIndicator" type="{http://sap.com/xi/BASIS/Global}Indicator" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SelectionByIndicator", namespace = "http://sap.com/xi/AP/Common/Global", propOrder = {
    "inclusionExclusionCode",
    "intervalBoundaryTypeCode",
    "lowerBoundaryIndicator",
    "upperBoundaryIndicator"
})
public class SelectionByIndicator {

    @XmlElement(name = "InclusionExclusionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String inclusionExclusionCode;
    @XmlElement(name = "IntervalBoundaryTypeCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String intervalBoundaryTypeCode;
    @XmlElement(name = "LowerBoundaryIndicator")
    protected Boolean lowerBoundaryIndicator;
    @XmlElement(name = "UpperBoundaryIndicator")
    protected Boolean upperBoundaryIndicator;

    /**
     * Gets the value of the inclusionExclusionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInclusionExclusionCode() {
        return inclusionExclusionCode;
    }

    /**
     * Sets the value of the inclusionExclusionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInclusionExclusionCode(String value) {
        this.inclusionExclusionCode = value;
    }

    /**
     * Gets the value of the intervalBoundaryTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntervalBoundaryTypeCode() {
        return intervalBoundaryTypeCode;
    }

    /**
     * Sets the value of the intervalBoundaryTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntervalBoundaryTypeCode(String value) {
        this.intervalBoundaryTypeCode = value;
    }

    /**
     * Gets the value of the lowerBoundaryIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLowerBoundaryIndicator() {
        return lowerBoundaryIndicator;
    }

    /**
     * Sets the value of the lowerBoundaryIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLowerBoundaryIndicator(Boolean value) {
        this.lowerBoundaryIndicator = value;
    }

    /**
     * Gets the value of the upperBoundaryIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUpperBoundaryIndicator() {
        return upperBoundaryIndicator;
    }

    /**
     * Sets the value of the upperBoundaryIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUpperBoundaryIndicator(Boolean value) {
        this.upperBoundaryIndicator = value;
    }

}
