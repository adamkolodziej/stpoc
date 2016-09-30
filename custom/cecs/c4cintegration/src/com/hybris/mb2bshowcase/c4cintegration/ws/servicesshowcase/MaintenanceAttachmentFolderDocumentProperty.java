
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for MaintenanceAttachmentFolderDocumentProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MaintenanceAttachmentFolderDocumentProperty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodeTechnicalID" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_Name" minOccurs="0"/>
 *         &lt;element name="DataTypeFormatCode" type="{http://sap.com/xi/AP/Common/GDT}PropertyDataTypeFormatCode" minOccurs="0"/>
 *         &lt;element name="VisibleIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/>
 *         &lt;element name="ChangeAllowedIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/>
 *         &lt;element name="MultipleValueIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/>
 *         &lt;element name="NamespaceURI" type="{http://sap.com/xi/AP/Common/GDT}NamespaceURI" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://sap.com/xi/AP/Common/GDT}Description" minOccurs="0"/>
 *         &lt;element name="PropertyValue" type="{http://sap.com/xi/DocumentServices/Global}MaintenanceAttachmentFolderDocumentPropertyPropertyValue" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="PropertyValueListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" />
 *       &lt;attribute name="ActionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MaintenanceAttachmentFolderDocumentProperty", namespace = "http://sap.com/xi/DocumentServices/Global", propOrder = {
    "technicalID",
    "name",
    "dataTypeFormatCode",
    "visibleIndicator",
    "changeAllowedIndicator",
    "multipleValueIndicator",
    "namespaceURI",
    "description",
    "propertyValue"
})
public class MaintenanceAttachmentFolderDocumentProperty {

    @XmlElement(name = "TechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String technicalID;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "DataTypeFormatCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String dataTypeFormatCode;
    @XmlElement(name = "VisibleIndicator")
    protected Boolean visibleIndicator;
    @XmlElement(name = "ChangeAllowedIndicator")
    protected Boolean changeAllowedIndicator;
    @XmlElement(name = "MultipleValueIndicator")
    protected Boolean multipleValueIndicator;
    @XmlElement(name = "NamespaceURI")
    protected NamespaceURI namespaceURI;
    @XmlElement(name = "Description")
    protected Description description;
    @XmlElement(name = "PropertyValue")
    protected List<MaintenanceAttachmentFolderDocumentPropertyPropertyValue> propertyValue;
    @XmlAttribute(name = "PropertyValueListCompleteTransmissionIndicator")
    protected Boolean propertyValueListCompleteTransmissionIndicator;
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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the dataTypeFormatCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataTypeFormatCode() {
        return dataTypeFormatCode;
    }

    /**
     * Sets the value of the dataTypeFormatCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataTypeFormatCode(String value) {
        this.dataTypeFormatCode = value;
    }

    /**
     * Gets the value of the visibleIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVisibleIndicator() {
        return visibleIndicator;
    }

    /**
     * Sets the value of the visibleIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVisibleIndicator(Boolean value) {
        this.visibleIndicator = value;
    }

    /**
     * Gets the value of the changeAllowedIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChangeAllowedIndicator() {
        return changeAllowedIndicator;
    }

    /**
     * Sets the value of the changeAllowedIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChangeAllowedIndicator(Boolean value) {
        this.changeAllowedIndicator = value;
    }

    /**
     * Gets the value of the multipleValueIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMultipleValueIndicator() {
        return multipleValueIndicator;
    }

    /**
     * Sets the value of the multipleValueIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMultipleValueIndicator(Boolean value) {
        this.multipleValueIndicator = value;
    }

    /**
     * Gets the value of the namespaceURI property.
     * 
     * @return
     *     possible object is
     *     {@link NamespaceURI }
     *     
     */
    public NamespaceURI getNamespaceURI() {
        return namespaceURI;
    }

    /**
     * Sets the value of the namespaceURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link NamespaceURI }
     *     
     */
    public void setNamespaceURI(NamespaceURI value) {
        this.namespaceURI = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * Gets the value of the propertyValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the propertyValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPropertyValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MaintenanceAttachmentFolderDocumentPropertyPropertyValue }
     * 
     * 
     */
    public List<MaintenanceAttachmentFolderDocumentPropertyPropertyValue> getPropertyValue() {
        if (propertyValue == null) {
            propertyValue = new ArrayList<MaintenanceAttachmentFolderDocumentPropertyPropertyValue>();
        }
        return this.propertyValue;
    }

    /**
     * Gets the value of the propertyValueListCompleteTransmissionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPropertyValueListCompleteTransmissionIndicator() {
        return propertyValueListCompleteTransmissionIndicator;
    }

    /**
     * Sets the value of the propertyValueListCompleteTransmissionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPropertyValueListCompleteTransmissionIndicator(Boolean value) {
        this.propertyValueListCompleteTransmissionIndicator = value;
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
