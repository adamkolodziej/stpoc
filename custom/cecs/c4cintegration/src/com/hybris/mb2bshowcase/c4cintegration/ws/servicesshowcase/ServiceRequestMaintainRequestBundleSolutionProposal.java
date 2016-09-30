
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ServiceRequestMaintainRequestBundleSolutionProposal complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestMaintainRequestBundleSolutionProposal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID" minOccurs="0"/>
 *         &lt;element name="ID" type="{http://sap.com/xi/AP/Common/GDT}CustomerTransactionDocumentSolutionProposalID" minOccurs="0"/>
 *         &lt;element name="ExternalKnowledgeBaseArticleID" type="{http://sap.com/xi/AP/Common/GDT}KnowledgeBaseArticleID" minOccurs="0"/>
 *         &lt;element name="ExternalKnowledgeBaseArticleDescription" type="{http://sap.com/xi/AP/Common/GDT}MEDIUM_Description" minOccurs="0"/>
 *         &lt;element name="ExternalKnowledgeBaseArticleURI" type="{http://sap.com/xi/AP/Common/GDT}URI" minOccurs="0"/>
 *         &lt;element name="Note" type="{http://sap.com/xi/AP/Common/GDT}Note" minOccurs="0"/>
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
@XmlType(name = "ServiceRequestMaintainRequestBundleSolutionProposal", propOrder = {
    "objectNodeSenderTechnicalID",
    "id",
    "externalKnowledgeBaseArticleID",
    "externalKnowledgeBaseArticleDescription",
    "externalKnowledgeBaseArticleURI",
    "note"
})
public class ServiceRequestMaintainRequestBundleSolutionProposal {

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String objectNodeSenderTechnicalID;
    @XmlElement(name = "ID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String id;
    @XmlElement(name = "ExternalKnowledgeBaseArticleID")
    protected KnowledgeBaseArticleID externalKnowledgeBaseArticleID;
    @XmlElement(name = "ExternalKnowledgeBaseArticleDescription")
    protected MEDIUMDescription externalKnowledgeBaseArticleDescription;
    @XmlElement(name = "ExternalKnowledgeBaseArticleURI")
    protected URI externalKnowledgeBaseArticleURI;
    @XmlElement(name = "Note")
    protected Note note;
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
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the externalKnowledgeBaseArticleID property.
     * 
     * @return
     *     possible object is
     *     {@link KnowledgeBaseArticleID }
     *     
     */
    public KnowledgeBaseArticleID getExternalKnowledgeBaseArticleID() {
        return externalKnowledgeBaseArticleID;
    }

    /**
     * Sets the value of the externalKnowledgeBaseArticleID property.
     * 
     * @param value
     *     allowed object is
     *     {@link KnowledgeBaseArticleID }
     *     
     */
    public void setExternalKnowledgeBaseArticleID(KnowledgeBaseArticleID value) {
        this.externalKnowledgeBaseArticleID = value;
    }

    /**
     * Gets the value of the externalKnowledgeBaseArticleDescription property.
     * 
     * @return
     *     possible object is
     *     {@link MEDIUMDescription }
     *     
     */
    public MEDIUMDescription getExternalKnowledgeBaseArticleDescription() {
        return externalKnowledgeBaseArticleDescription;
    }

    /**
     * Sets the value of the externalKnowledgeBaseArticleDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MEDIUMDescription }
     *     
     */
    public void setExternalKnowledgeBaseArticleDescription(MEDIUMDescription value) {
        this.externalKnowledgeBaseArticleDescription = value;
    }

    /**
     * Gets the value of the externalKnowledgeBaseArticleURI property.
     * 
     * @return
     *     possible object is
     *     {@link URI }
     *     
     */
    public URI getExternalKnowledgeBaseArticleURI() {
        return externalKnowledgeBaseArticleURI;
    }

    /**
     * Sets the value of the externalKnowledgeBaseArticleURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link URI }
     *     
     */
    public void setExternalKnowledgeBaseArticleURI(URI value) {
        this.externalKnowledgeBaseArticleURI = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link Note }
     *     
     */
    public Note getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link Note }
     *     
     */
    public void setNote(Note value) {
        this.note = value;
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
