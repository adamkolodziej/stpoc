
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for AccessAttachmentFolderDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessAttachmentFolderDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/>
 *         &lt;element name="VersionID" type="{http://sap.com/xi/AP/Common/GDT}VersionID" minOccurs="0"/>
 *         &lt;element name="SystemAdministrativeData" type="{http://sap.com/xi/AP/Common/GDT}SystemAdministrativeData" minOccurs="0"/>
 *         &lt;element name="LinkInternalIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/>
 *         &lt;element name="CheckedOutIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/>
 *         &lt;element name="VisibleIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/>
 *         &lt;element name="VersioningEnabledIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/>
 *         &lt;element name="CategoryCode" type="{http://sap.com/xi/AP/Common/GDT}DocumentCategoryCode" minOccurs="0"/>
 *         &lt;element name="CategoryName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/>
 *         &lt;element name="TypeCode" type="{http://sap.com/xi/AP/Common/GDT}DocumentTypeCode" minOccurs="0"/>
 *         &lt;element name="TypeName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/>
 *         &lt;element name="MIMECode" type="{http://sap.com/xi/AP/Common/GDT}MIMECode" minOccurs="0"/>
 *         &lt;element name="MIMEName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/>
 *         &lt;element name="PathName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_Name" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_Name" minOccurs="0"/>
 *         &lt;element name="AlternativeName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_Name" minOccurs="0"/>
 *         &lt;element name="InternalLinkUUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://sap.com/xi/AP/Common/GDT}Description" minOccurs="0"/>
 *         &lt;element name="ExternalLinkWebURI" type="{http://sap.com/xi/AP/Common/GDT}WebURI" minOccurs="0"/>
 *         &lt;element name="FileContentURI" type="{http://sap.com/xi/AP/Common/GDT}URI" minOccurs="0"/>
 *         &lt;element name="FilesizeMeasure" type="{http://sap.com/xi/AP/Common/GDT}Measure" minOccurs="0"/>
 *         &lt;element name="Property" type="{http://sap.com/xi/DocumentServices/Global}AccessAttachmentFolderDocumentProperty" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessAttachmentFolderDocument", namespace = "http://sap.com/xi/DocumentServices/Global", propOrder = {
    "uuid",
    "versionID",
    "systemAdministrativeData",
    "linkInternalIndicator",
    "checkedOutIndicator",
    "visibleIndicator",
    "versioningEnabledIndicator",
    "categoryCode",
    "categoryName",
    "typeCode",
    "typeName",
    "mimeCode",
    "mimeName",
    "pathName",
    "name",
    "alternativeName",
    "internalLinkUUID",
    "description",
    "externalLinkWebURI",
    "fileContentURI",
    "filesizeMeasure",
    "property"
})
public class AccessAttachmentFolderDocument {

    @XmlElement(name = "UUID")
    protected UUID uuid;
    @XmlElement(name = "VersionID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String versionID;
    @XmlElement(name = "SystemAdministrativeData")
    protected SystemAdministrativeData systemAdministrativeData;
    @XmlElement(name = "LinkInternalIndicator")
    protected Boolean linkInternalIndicator;
    @XmlElement(name = "CheckedOutIndicator")
    protected Boolean checkedOutIndicator;
    @XmlElement(name = "VisibleIndicator")
    protected Boolean visibleIndicator;
    @XmlElement(name = "VersioningEnabledIndicator")
    protected Boolean versioningEnabledIndicator;
    @XmlElement(name = "CategoryCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String categoryCode;
    @XmlElement(name = "CategoryName")
    protected String categoryName;
    @XmlElement(name = "TypeCode")
    protected DocumentTypeCode typeCode;
    @XmlElement(name = "TypeName")
    protected String typeName;
    @XmlElement(name = "MIMECode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mimeCode;
    @XmlElement(name = "MIMEName")
    protected String mimeName;
    @XmlElement(name = "PathName")
    protected String pathName;
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
    @XmlElement(name = "FileContentURI")
    protected URI fileContentURI;
    @XmlElement(name = "FilesizeMeasure")
    protected Measure filesizeMeasure;
    @XmlElement(name = "Property")
    protected List<AccessAttachmentFolderDocumentProperty> property;

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
     * Gets the value of the versionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionID() {
        return versionID;
    }

    /**
     * Sets the value of the versionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionID(String value) {
        this.versionID = value;
    }

    /**
     * Gets the value of the systemAdministrativeData property.
     * 
     * @return
     *     possible object is
     *     {@link SystemAdministrativeData }
     *     
     */
    public SystemAdministrativeData getSystemAdministrativeData() {
        return systemAdministrativeData;
    }

    /**
     * Sets the value of the systemAdministrativeData property.
     * 
     * @param value
     *     allowed object is
     *     {@link SystemAdministrativeData }
     *     
     */
    public void setSystemAdministrativeData(SystemAdministrativeData value) {
        this.systemAdministrativeData = value;
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
     * Gets the value of the checkedOutIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCheckedOutIndicator() {
        return checkedOutIndicator;
    }

    /**
     * Sets the value of the checkedOutIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCheckedOutIndicator(Boolean value) {
        this.checkedOutIndicator = value;
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
     * Gets the value of the versioningEnabledIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVersioningEnabledIndicator() {
        return versioningEnabledIndicator;
    }

    /**
     * Sets the value of the versioningEnabledIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVersioningEnabledIndicator(Boolean value) {
        this.versioningEnabledIndicator = value;
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
     * Gets the value of the categoryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the value of the categoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
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
     * Gets the value of the typeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Sets the value of the typeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeName(String value) {
        this.typeName = value;
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
     * Gets the value of the mimeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMIMEName() {
        return mimeName;
    }

    /**
     * Sets the value of the mimeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMIMEName(String value) {
        this.mimeName = value;
    }

    /**
     * Gets the value of the pathName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPathName() {
        return pathName;
    }

    /**
     * Sets the value of the pathName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPathName(String value) {
        this.pathName = value;
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
     * Gets the value of the fileContentURI property.
     * 
     * @return
     *     possible object is
     *     {@link URI }
     *     
     */
    public URI getFileContentURI() {
        return fileContentURI;
    }

    /**
     * Sets the value of the fileContentURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link URI }
     *     
     */
    public void setFileContentURI(URI value) {
        this.fileContentURI = value;
    }

    /**
     * Gets the value of the filesizeMeasure property.
     * 
     * @return
     *     possible object is
     *     {@link Measure }
     *     
     */
    public Measure getFilesizeMeasure() {
        return filesizeMeasure;
    }

    /**
     * Sets the value of the filesizeMeasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link Measure }
     *     
     */
    public void setFilesizeMeasure(Measure value) {
        this.filesizeMeasure = value;
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
     * {@link AccessAttachmentFolderDocumentProperty }
     * 
     * 
     */
    public List<AccessAttachmentFolderDocumentProperty> getProperty() {
        if (property == null) {
            property = new ArrayList<AccessAttachmentFolderDocumentProperty>();
        }
        return this.property;
    }

}
