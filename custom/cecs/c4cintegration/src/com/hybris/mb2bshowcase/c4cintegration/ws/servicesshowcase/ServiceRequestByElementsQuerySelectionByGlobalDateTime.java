
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServiceRequestByElementsQuerySelectionByGlobalDateTime complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestByElementsQuerySelectionByGlobalDateTime">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InclusionExclusionCode" type="{http://sap.com/xi/AP/Common/GDT}InclusionExclusionCode" minOccurs="0"/>
 *         &lt;element name="IntervalBoundaryTypeCode" type="{http://sap.com/xi/AP/Common/GDT}IntervalBoundaryTypeCode"/>
 *         &lt;element name="LowerBoundaryDateTime" type="{http://sap.com/xi/BASIS/Global}GLOBAL_DateTime" minOccurs="0"/>
 *         &lt;element name="UpperBoundaryDateTime" type="{http://sap.com/xi/BASIS/Global}GLOBAL_DateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestByElementsQuerySelectionByGlobalDateTime", propOrder = {
    "inclusionExclusionCode",
    "intervalBoundaryTypeCode",
    "lowerBoundaryDateTime",
    "upperBoundaryDateTime"
})
public class ServiceRequestByElementsQuerySelectionByGlobalDateTime {

    @XmlElement(name = "InclusionExclusionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String inclusionExclusionCode;
    @XmlElement(name = "IntervalBoundaryTypeCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String intervalBoundaryTypeCode;
    @XmlElement(name = "LowerBoundaryDateTime")
    protected XMLGregorianCalendar lowerBoundaryDateTime;
    @XmlElement(name = "UpperBoundaryDateTime")
    protected XMLGregorianCalendar upperBoundaryDateTime;

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
     * Gets the value of the lowerBoundaryDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLowerBoundaryDateTime() {
        return lowerBoundaryDateTime;
    }

    /**
     * Sets the value of the lowerBoundaryDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLowerBoundaryDateTime(XMLGregorianCalendar value) {
        this.lowerBoundaryDateTime = value;
    }

    /**
     * Gets the value of the upperBoundaryDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpperBoundaryDateTime() {
        return upperBoundaryDateTime;
    }

    /**
     * Sets the value of the upperBoundaryDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpperBoundaryDateTime(XMLGregorianCalendar value) {
        this.upperBoundaryDateTime = value;
    }

}
