
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ServiceRequestByElementsResponseBusinessTransactionDocumentReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestByElementsResponseBusinessTransactionDocumentReference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BusinessTransactionDocumentReferenceID" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentID" minOccurs="0"/>
 *         &lt;element name="BusinessTransactionDocumentReferenceTypeCode" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentTypeCode" minOccurs="0"/>
 *         &lt;element name="BusinessTransactionDocumentReferenceItemUUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/>
 *         &lt;element name="BusinessTransactionDocumentReferenceItemTypeCode" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentItemTypeCode" minOccurs="0"/>
 *         &lt;element name="BusinessTransactionDocumentRelationshipRoleCode" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentRelationshipRoleCode" minOccurs="0"/>
 *         &lt;element name="BusinessSystemID" type="{http://sap.com/xi/Common/DataTypes}CommunicationSystemParticipatingBusinessSystemID" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestByElementsResponseBusinessTransactionDocumentReference", propOrder = {
    "businessTransactionDocumentReferenceID",
    "businessTransactionDocumentReferenceTypeCode",
    "businessTransactionDocumentReferenceItemUUID",
    "businessTransactionDocumentReferenceItemTypeCode",
    "businessTransactionDocumentRelationshipRoleCode",
    "businessSystemID"
})
public class ServiceRequestByElementsResponseBusinessTransactionDocumentReference {

    @XmlElement(name = "BusinessTransactionDocumentReferenceID")
    protected BusinessTransactionDocumentID businessTransactionDocumentReferenceID;
    @XmlElement(name = "BusinessTransactionDocumentReferenceTypeCode")
    protected BusinessTransactionDocumentTypeCode businessTransactionDocumentReferenceTypeCode;
    @XmlElement(name = "BusinessTransactionDocumentReferenceItemUUID")
    protected UUID businessTransactionDocumentReferenceItemUUID;
    @XmlElement(name = "BusinessTransactionDocumentReferenceItemTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String businessTransactionDocumentReferenceItemTypeCode;
    @XmlElement(name = "BusinessTransactionDocumentRelationshipRoleCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String businessTransactionDocumentRelationshipRoleCode;
    @XmlElement(name = "BusinessSystemID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String businessSystemID;

    /**
     * Gets the value of the businessTransactionDocumentReferenceID property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessTransactionDocumentID }
     *     
     */
    public BusinessTransactionDocumentID getBusinessTransactionDocumentReferenceID() {
        return businessTransactionDocumentReferenceID;
    }

    /**
     * Sets the value of the businessTransactionDocumentReferenceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessTransactionDocumentID }
     *     
     */
    public void setBusinessTransactionDocumentReferenceID(BusinessTransactionDocumentID value) {
        this.businessTransactionDocumentReferenceID = value;
    }

    /**
     * Gets the value of the businessTransactionDocumentReferenceTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessTransactionDocumentTypeCode }
     *     
     */
    public BusinessTransactionDocumentTypeCode getBusinessTransactionDocumentReferenceTypeCode() {
        return businessTransactionDocumentReferenceTypeCode;
    }

    /**
     * Sets the value of the businessTransactionDocumentReferenceTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessTransactionDocumentTypeCode }
     *     
     */
    public void setBusinessTransactionDocumentReferenceTypeCode(BusinessTransactionDocumentTypeCode value) {
        this.businessTransactionDocumentReferenceTypeCode = value;
    }

    /**
     * Gets the value of the businessTransactionDocumentReferenceItemUUID property.
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getBusinessTransactionDocumentReferenceItemUUID() {
        return businessTransactionDocumentReferenceItemUUID;
    }

    /**
     * Sets the value of the businessTransactionDocumentReferenceItemUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setBusinessTransactionDocumentReferenceItemUUID(UUID value) {
        this.businessTransactionDocumentReferenceItemUUID = value;
    }

    /**
     * Gets the value of the businessTransactionDocumentReferenceItemTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessTransactionDocumentReferenceItemTypeCode() {
        return businessTransactionDocumentReferenceItemTypeCode;
    }

    /**
     * Sets the value of the businessTransactionDocumentReferenceItemTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessTransactionDocumentReferenceItemTypeCode(String value) {
        this.businessTransactionDocumentReferenceItemTypeCode = value;
    }

    /**
     * Gets the value of the businessTransactionDocumentRelationshipRoleCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessTransactionDocumentRelationshipRoleCode() {
        return businessTransactionDocumentRelationshipRoleCode;
    }

    /**
     * Sets the value of the businessTransactionDocumentRelationshipRoleCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessTransactionDocumentRelationshipRoleCode(String value) {
        this.businessTransactionDocumentRelationshipRoleCode = value;
    }

    /**
     * Gets the value of the businessSystemID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessSystemID() {
        return businessSystemID;
    }

    /**
     * Sets the value of the businessSystemID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessSystemID(String value) {
        this.businessSystemID = value;
    }

}
