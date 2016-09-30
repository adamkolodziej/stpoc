
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
 * <p>Java class for MaintenanceAttachmentFolderDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MaintenanceAttachmentFolderDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/>
 *         &lt;element name="LinkInternalIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/>
 *         &lt;element name="VisibleIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/>
 *         &lt;element name="CategoryCode" type="{http://sap.com/xi/AP/Common/GDT}DocumentCategoryCode" minOccurs="0"/>
 *         &lt;element name="TypeCode" type="{http://sap.com/xi/AP/Common/GDT}DocumentTypeCode" minOccurs="0"/>
 *         &lt;element name="MIMECode" type="{http://sap.com/xi/AP/Common/GDT}MIMECode" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_Name" minOccurs="0"/>
 *         &lt;element name="AlternativeName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_Name" minOccurs="0"/>
 *         &lt;element name="InternalLinkUUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://sap.com/xi/AP/Common/GDT}Description" minOccurs="0"/>
 *         &lt;element name="ExternalLinkWebURI" type="{http://sap.com/xi/AP/Common/GDT}WebURI" minOccurs="0"/>
 *         &lt;element name="Property" type="{http://sap.com/xi/DocumentServices/Global}MaintenanceAttachmentFolderDocumentProperty" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="FileContent" type="{http://sap.com/xi/DocumentServices/Global}MaintenanceAttachmentFolderDocumentFileContent" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="PropertyListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" />
 *       &lt;attribute name="ActionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MaintenanceAttachmentFolderDocument", namespace = "http://sap.com/xi/DocumentServices/Global", propOrder = {
    "uuid",
    "linkInternalIndicator",
    "visibleIndicator",
    "categoryCode",
    "typeCode",
    "mimeCode",
    "name",
    "alternativeName",
    "internalLinkUUID",
    "description",
    "externalLinkWebURI",
    "property",
    "fileContent"
})
public class MaintenanceAttachmentFolderDocument {

    @XmlElement(name = "UUID")
    protected UUID uuid;
    @XmlElement(name = "LinkInternalIndicator")
    protected Boolean linkInternalIndicator;
    @XmlElement(name = "VisibleIndicator")
    protected Boolean visibleIndicator;
    @XmlElement(name = "CategoryCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String categoryCode;
    @XmlElement(name = "TypeCode")
    protected DocumentTypeCode typeCode;
    @XmlElement(name = "MIMECode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mimeCode;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "AlternativeName")
    protected String alternativeName;
    @XmlElement(name = "InternalLinkUUID")
    protected UUID internalLinkUUID;
    @XmlElement(name = "Description")
    protected Description description;
    @XmlElement(name = "ExternalLinkWebURI")
    protected String externalLinkWebURI;
    @XmlElement(name = "Property")
    protected List<MaintenanceAttachmentFolderDocumentProperty> property;
    @XmlElement(name = "FileContent")
    protected MaintenanceAttachmentFolderDocumentFileContent fileContent;
    @XmlAttribute(name = "PropertyListCompleteTransmissionIndicator")
    protected Boolean propertyListCompleteTransmissionIndicator;
    @XmlAttribute(name = "ActionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setUUID(UUID value) {
        this.uuid = value;
    }

    /**
     * Gets the value of the linkInternalIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLinkInternalIndicator() {
        return linkInternalIndicator;
    }

    /**
     * Sets the value of the linkInternalIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLinkInternalIndicator(Boolean value) {
        this.linkInternalIndicator = value;
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
     * Gets the value of the categoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * Sets the value of the categoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryCode(String value) {
        this.categoryCode = value;
    }

    /**
     * Gets the value of the typeCode property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentTypeCode }
     *     
     */
    public DocumentTypeCode getTypeCode() {
        return typeCode;
    }

    /**
     * Sets the value of the typeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentTypeCode }
     *     
     */
    public void setTypeCode(DocumentTypeCode value) {
        this.typeCode = value;
    }

    /**
     * Gets the value of the mimeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMIMECode() {
        return mimeCode;
    }

    /**
     * Sets the value of the mimeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMIMECode(String value) {
        this.mimeCode = value;
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
     * Gets the value of the alternativeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlternativeName() {
        return alternativeName;
    }

    /**
     * Sets the value of the alternativeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlternativeName(String value) {
        this.alternativeName = value;
    }

    /**
     * Gets the value of the internalLinkUUID property.
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getInternalLinkUUID() {
        return internalLinkUUID;
    }

    /**
     * Sets the value of the internalLinkUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setInternalLinkUUID(UUID value) {
        this.internalLinkUUID = value;
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
     * Gets the value of the externalLinkWebURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalLinkWebURI() {
        return externalLinkWebURI;
    }

    /**
     * Sets the value of the externalLinkWebURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalLinkWebURI(String value) {
        this.externalLinkWebURI = value;
    }

    /**
     * Gets the value of the property property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the property property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MaintenanceAttachmentFolderDocumentProperty }
     * 
     * 
     */
    public List<MaintenanceAttachmentFolderDocumentProperty> getProperty() {
        if (property == null) {
            property = new ArrayList<MaintenanceAttachmentFolderDocumentProperty>();
        }
        return this.property;
    }

    /**
     * Gets the value of the fileContent property.
     * 
     * @return
     *     possible object is
     *     {@link MaintenanceAttachmentFolderDocumentFileContent }
     *     
     */
    public MaintenanceAttachmentFolderDocumentFileContent getFileContent() {
        return fileContent;
    }

    /**
     * Sets the value of the fileContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link MaintenanceAttachmentFolderDocumentFileContent }
     *     
     */
    public void setFileContent(MaintenanceAttachmentFolderDocumentFileContent value) {
        this.fileContent = value;
    }

    /**
     * Gets the value of the propertyListCompleteTransmissionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPropertyListCompleteTransmissionIndicator() {
        return propertyListCompleteTransmissionIndicator;
    }

    /**
     * Sets the value of the propertyListCompleteTransmissionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPropertyListCompleteTransmissionIndicator(Boolean value) {
        this.propertyListCompleteTransmissionIndicator = value;
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
