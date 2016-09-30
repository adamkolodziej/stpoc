
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ServiceRequestMaintainConfirmationBundle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestMaintainConfirmationBundle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReferenceObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID"/>
 *         &lt;element name="ChangeStateID" type="{http://sap.com/xi/AP/Common/GDT}ChangeStateID"/>
 *         &lt;element name="UUID" type="{http://sap.com/xi/AP/Common/GDT}UUID"/>
 *         &lt;element name="ID" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentID"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestMaintainConfirmationBundle", propOrder = {
    "referenceObjectNodeSenderTechnicalID",
    "changeStateID",
    "uuid",
    "id"
})
public class ServiceRequestMaintainConfirmationBundle {

    @XmlElement(name = "ReferenceObjectNodeSenderTechnicalID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String referenceObjectNodeSenderTechnicalID;
    @XmlElement(name = "ChangeStateID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String changeStateID;
    @XmlElement(name = "UUID", required = true)
    protected UUID uuid;
    @XmlElement(name = "ID", required = true)
    protected BusinessTransactionDocumentID id;

    /**
     * Gets the value of the referenceObjectNodeSenderTechnicalID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceObjectNodeSenderTechnicalID() {
        return referenceObjectNodeSenderTechnicalID;
    }

    /**
     * Sets the value of the referenceObjectNodeSenderTechnicalID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceObjectNodeSenderTechnicalID(String value) {
        this.referenceObjectNodeSenderTechnicalID = value;
    }

    /**
     * Gets the value of the changeStateID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangeStateID() {
        return changeStateID;
    }

    /**
     * Sets the value of the changeStateID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangeStateID(String value) {
        this.changeStateID = value;
    }

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
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessTransactionDocumentID }
     *     
     */
    public BusinessTransactionDocumentID getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessTransactionDocumentID }
     *     
     */
    public void setID(BusinessTransactionDocumentID value) {
        this.id = value;
    }

}
