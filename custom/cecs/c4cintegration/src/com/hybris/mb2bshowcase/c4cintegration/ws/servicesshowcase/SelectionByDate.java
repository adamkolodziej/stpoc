
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SelectionByDate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SelectionByDate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InclusionExclusionCode" type="{http://sap.com/xi/AP/Common/GDT}InclusionExclusionCode" minOccurs="0"/>
 *         &lt;element name="IntervalBoundaryTypeCode" type="{http://sap.com/xi/AP/Common/GDT}IntervalBoundaryTypeCode"/>
 *         &lt;element name="LowerBoundaryDate" type="{http://sap.com/xi/BASIS/Global}Date" minOccurs="0"/>
 *         &lt;element name="UpperBoundaryDate" type="{http://sap.com/xi/BASIS/Global}Date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SelectionByDate", namespace = "http://sap.com/xi/AP/Common/Global", propOrder = {
    "inclusionExclusionCode",
    "intervalBoundaryTypeCode",
    "lowerBoundaryDate",
    "upperBoundaryDate"
})
public class SelectionByDate {

    @XmlElement(name = "InclusionExclusionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String inclusionExclusionCode;
    @XmlElement(name = "IntervalBoundaryTypeCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String intervalBoundaryTypeCode;
    @XmlElement(name = "LowerBoundaryDate")
    protected XMLGregorianCalendar lowerBoundaryDate;
    @XmlElement(name = "UpperBoundaryDate")
    protected XMLGregorianCalendar upperBoundaryDate;

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
     * Gets the value of the lowerBoundaryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLowerBoundaryDate() {
        return lowerBoundaryDate;
    }

    /**
     * Sets the value of the lowerBoundaryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLowerBoundaryDate(XMLGregorianCalendar value) {
        this.lowerBoundaryDate = value;
    }

    /**
     * Gets the value of the upperBoundaryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpperBoundaryDate() {
        return upperBoundaryDate;
    }

    /**
     * Sets the value of the upperBoundaryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpperBoundaryDate(XMLGregorianCalendar value) {
        this.upperBoundaryDate = value;
    }

}
