
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
 * <p>Java class for MaintenanceAttachmentFolder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MaintenanceAttachmentFolder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/>
 *         &lt;element name="Document" type="{http://sap.com/xi/DocumentServices/Global}MaintenanceAttachmentFolderDocument" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="DocumentListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" />
 *       &lt;attribute name="ActionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MaintenanceAttachmentFolder", namespace = "http://sap.com/xi/DocumentServices/Global", propOrder = {
    "uuid",
    "document"
})
public class MaintenanceAttachmentFolder {

    @XmlElement(name = "UUID")
    protected UUID uuid;
    @XmlElement(name = "Document")
    protected List<MaintenanceAttachmentFolderDocument> document;
    @XmlAttribute(name = "DocumentListCompleteTransmissionIndicator")
    protected Boolean documentListCompleteTransmissionIndicator;
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
     * Gets the value of the document property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the document property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MaintenanceAttachmentFolderDocument }
     * 
     * 
     */
    public List<MaintenanceAttachmentFolderDocument> getDocument() {
        if (document == null) {
            document = new ArrayList<MaintenanceAttachmentFolderDocument>();
        }
        return this.document;
    }

    /**
     * Gets the value of the documentListCompleteTransmissionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDocumentListCompleteTransmissionIndicator() {
        return documentListCompleteTransmissionIndicator;
    }

    /**
     * Sets the value of the documentListCompleteTransmissionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDocumentListCompleteTransmissionIndicator(Boolean value) {
        this.documentListCompleteTransmissionIndicator = value;
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
