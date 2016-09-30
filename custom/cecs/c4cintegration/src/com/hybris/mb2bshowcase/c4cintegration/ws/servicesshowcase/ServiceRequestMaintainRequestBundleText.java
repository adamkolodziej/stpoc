
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
 * <p>Java class for ServiceRequestMaintainRequestBundleText complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestMaintainRequestBundleText">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID" minOccurs="0"/>
 *         &lt;element name="ID" type="{http://sap.com/xi/AP/Common/GDT}TextCollectionTextID" minOccurs="0"/>
 *         &lt;element name="TypeCode" type="{http://sap.com/xi/AP/Common/GDT}TextCollectionTextTypeCode" minOccurs="0"/>
 *         &lt;element name="Author" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleTextAuthor" minOccurs="0"/>
 *         &lt;element name="CreationDateTime" type="{http://sap.com/xi/BASIS/Global}GLOBAL_DateTime" minOccurs="0"/>
 *         &lt;element name="Content" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_Text" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="actionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestMaintainRequestBundleText", propOrder = {
    "objectNodeSenderTechnicalID",
    "id",
    "typeCode",
    "author",
    "creationDateTime",
    "content"
})
public class ServiceRequestMaintainRequestBundleText {

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String objectNodeSenderTechnicalID;
    @XmlElement(name = "ID")
    protected TextCollectionTextID id;
    @XmlElement(name = "TypeCode")
    protected TextCollectionTextTypeCode typeCode;
    @XmlElement(name = "Author")
    protected ServiceRequestMaintainRequestBundleTextAuthor author;
    @XmlElement(name = "CreationDateTime")
    protected XMLGregorianCalendar creationDateTime;
    @XmlElement(name = "Content")
    protected String content;
    @XmlAttribute(name = "actionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;

    /**
     * Gets the value of the objectNodeSenderTechnicalID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectNodeSenderTechnicalID() {
        return objectNodeSenderTechnicalID;
    }

    /**
     * Sets the value of the objectNodeSenderTechnicalID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectNodeSenderTechnicalID(String value) {
        this.objectNodeSenderTechnicalID = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link TextCollectionTextID }
     *     
     */
    public TextCollectionTextID getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextCollectionTextID }
     *     
     */
    public void setID(TextCollectionTextID value) {
        this.id = value;
    }

    /**
     * Gets the value of the typeCode property.
     * 
     * @return
     *     possible object is
     *     {@link TextCollectionTextTypeCode }
     *     
     */
    public TextCollectionTextTypeCode getTypeCode() {
        return typeCode;
    }

    /**
     * Sets the value of the typeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextCollectionTextTypeCode }
     *     
     */
    public void setTypeCode(TextCollectionTextTypeCode value) {
        this.typeCode = value;
    }

    /**
     * Gets the value of the author property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleTextAuthor }
     *     
     */
    public ServiceRequestMaintainRequestBundleTextAuthor getAuthor() {
        return author;
    }

    /**
     * Sets the value of the author property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleTextAuthor }
     *     
     */
    public void setAuthor(ServiceRequestMaintainRequestBundleTextAuthor value) {
        this.author = value;
    }

    /**
     * Gets the value of the creationDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDateTime() {
        return creationDateTime;
    }

    /**
     * Sets the value of the creationDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDateTime(XMLGregorianCalendar value) {
        this.creationDateTime = value;
    }

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
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
