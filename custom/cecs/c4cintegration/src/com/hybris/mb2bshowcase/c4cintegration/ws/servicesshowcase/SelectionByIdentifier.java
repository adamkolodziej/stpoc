
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for SelectionByIdentifier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SelectionByIdentifier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InclusionExclusionCode" type="{http://sap.com/xi/AP/Common/GDT}InclusionExclusionCode" minOccurs="0"/>
 *         &lt;element name="IntervalBoundaryTypeCode" type="{http://sap.com/xi/AP/Common/GDT}IntervalBoundaryTypeCode"/>
 *         &lt;element name="LowerBoundaryIdentifier" type="{http://sap.com/xi/AP/Common/GDT}SearchText" minOccurs="0"/>
 *         &lt;element name="UpperBoundaryIdentifier" type="{http://sap.com/xi/AP/Common/GDT}SearchText" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SelectionByIdentifier", namespace = "http://sap.com/xi/AP/Common/Global", propOrder = {
    "inclusionExclusionCode",
    "intervalBoundaryTypeCode",
    "lowerBoundaryIdentifier",
    "upperBoundaryIdentifier"
})
public class SelectionByIdentifier {

    @XmlElement(name = "InclusionExclusionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String inclusionExclusionCode;
    @XmlElement(name = "IntervalBoundaryTypeCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String intervalBoundaryTypeCode;
    @XmlElement(name = "LowerBoundaryIdentifier")
    protected String lowerBoundaryIdentifier;
    @XmlElement(name = "UpperBoundaryIdentifier")
    protected String upperBoundaryIdentifier;

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
     * Gets the value of the lowerBoundaryIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLowerBoundaryIdentifier() {
        return lowerBoundaryIdentifier;
    }

    /**
     * Sets the value of the lowerBoundaryIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLowerBoundaryIdentifier(String value) {
        this.lowerBoundaryIdentifier = value;
    }

    /**
     * Gets the value of the upperBoundaryIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpperBoundaryIdentifier() {
        return upperBoundaryIdentifier;
    }

    /**
     * Sets the value of the upperBoundaryIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpperBoundaryIdentifier(String value) {
        this.upperBoundaryIdentifier = value;
    }

}
