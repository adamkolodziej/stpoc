
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for MaintenanceAttachmentFolderDocumentPropertyPropertyValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MaintenanceAttachmentFolderDocumentPropertyPropertyValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodeTechnicalID" minOccurs="0"/>
 *         &lt;element name="Text" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_Text" minOccurs="0"/>
 *         &lt;element name="Indicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/>
 *         &lt;element name="DateTime" type="{http://sap.com/xi/BASIS/Global}GLOBAL_DateTime" minOccurs="0"/>
 *         &lt;element name="IntegerValue" type="{http://sap.com/xi/AP/Common/GDT}IntegerValue" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ActionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MaintenanceAttachmentFolderDocumentPropertyPropertyValue", namespace = "http://sap.com/xi/DocumentServices/Global", propOrder = {
    "technicalID",
    "text",
    "indicator",
    "dateTime",
    "integerValue"
})
public class MaintenanceAttachmentFolderDocumentPropertyPropertyValue {

    @XmlElement(name = "TechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String technicalID;
    @XmlElement(name = "Text")
    protected String text;
    @XmlElement(name = "Indicator")
    protected Boolean indicator;
    @XmlElement(name = "DateTime")
    protected XMLGregorianCalendar dateTime;
    @XmlElement(name = "IntegerValue")
    protected Integer integerValue;
    @XmlAttribute(name = "ActionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;

    /**
     * Gets the value of the technicalID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechnicalID() {
        return technicalID;
    }

    /**
     * Sets the value of the technicalID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechnicalID(String value) {
        this.technicalID = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the indicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIndicator() {
        return indicator;
    }

    /**
     * Sets the value of the indicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIndicator(Boolean value) {
        this.indicator = value;
    }

    /**
     * Gets the value of the dateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateTime() {
        return dateTime;
    }

    /**
     * Sets the value of the dateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateTime(XMLGregorianCalendar value) {
        this.dateTime = value;
    }

    /**
     * Gets the value of the integerValue property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntegerValue() {
        return integerValue;
    }

    /**
     * Sets the value of the integerValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntegerValue(Integer value) {
        this.integerValue = value;
    }

    /**
     * Gets the value of the actionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionCode() {
        return actionCode;
    }

    /**
     * Sets the value of the actionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionCode(String value) {
        this.actionCode = value;
    }

}
