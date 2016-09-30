
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceRequestByElementsResponseTimePointTerms complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestByElementsResponseTimePointTerms">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestInitialReceiptTimePoint" type="{http://sap.com/xi/AP/Common/GDT}LOCALNORMALISED_DateTime" minOccurs="0"/>
 *         &lt;element name="RequestInProcessAtTimePoint" type="{http://sap.com/xi/AP/Common/GDT}LOCALNORMALISED_DateTime" minOccurs="0"/>
 *         &lt;element name="RequestClosedAtTimePoint" type="{http://sap.com/xi/AP/Common/GDT}LOCALNORMALISED_DateTime" minOccurs="0"/>
 *         &lt;element name="RequestFinishedAtTimePoint" type="{http://sap.com/xi/AP/Common/GDT}LOCALNORMALISED_DateTime" minOccurs="0"/>
 *         &lt;element name="FirstReactionDueTimePoint" type="{http://sap.com/xi/AP/Common/GDT}LOCALNORMALISED_DateTime" minOccurs="0"/>
 *         &lt;element name="CompletionDueTimePoint" type="{http://sap.com/xi/AP/Common/GDT}LOCALNORMALISED_DateTime" minOccurs="0"/>
 *         &lt;element name="WarrantyStartReferenceTimePoint" type="{http://sap.com/xi/AP/Common/GDT}LOCALNORMALISED_DateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestByElementsResponseTimePointTerms", propOrder = {
    "requestInitialReceiptTimePoint",
    "requestInProcessAtTimePoint",
    "requestClosedAtTimePoint",
    "requestFinishedAtTimePoint",
    "firstReactionDueTimePoint",
    "completionDueTimePoint",
    "warrantyStartReferenceTimePoint"
})
public class ServiceRequestByElementsResponseTimePointTerms {

    @XmlElement(name = "RequestInitialReceiptTimePoint")
    protected LOCALNORMALISEDDateTime requestInitialReceiptTimePoint;
    @XmlElement(name = "RequestInProcessAtTimePoint")
    protected LOCALNORMALISEDDateTime requestInProcessAtTimePoint;
    @XmlElement(name = "RequestClosedAtTimePoint")
    protected LOCALNORMALISEDDateTime requestClosedAtTimePoint;
    @XmlElement(name = "RequestFinishedAtTimePoint")
    protected LOCALNORMALISEDDateTime requestFinishedAtTimePoint;
    @XmlElement(name = "FirstReactionDueTimePoint")
    protected LOCALNORMALISEDDateTime firstReactionDueTimePoint;
    @XmlElement(name = "CompletionDueTimePoint")
    protected LOCALNORMALISEDDateTime completionDueTimePoint;
    @XmlElement(name = "WarrantyStartReferenceTimePoint")
    protected LOCALNORMALISEDDateTime warrantyStartReferenceTimePoint;

    /**
     * Gets the value of the requestInitialReceiptTimePoint property.
     * 
     * @return
     *     possible object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public LOCALNORMALISEDDateTime getRequestInitialReceiptTimePoint() {
        return requestInitialReceiptTimePoint;
    }

    /**
     * Sets the value of the requestInitialReceiptTimePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public void setRequestInitialReceiptTimePoint(LOCALNORMALISEDDateTime value) {
        this.requestInitialReceiptTimePoint = value;
    }

    /**
     * Gets the value of the requestInProcessAtTimePoint property.
     * 
     * @return
     *     possible object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public LOCALNORMALISEDDateTime getRequestInProcessAtTimePoint() {
        return requestInProcessAtTimePoint;
    }

    /**
     * Sets the value of the requestInProcessAtTimePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public void setRequestInProcessAtTimePoint(LOCALNORMALISEDDateTime value) {
        this.requestInProcessAtTimePoint = value;
    }

    /**
     * Gets the value of the requestClosedAtTimePoint property.
     * 
     * @return
     *     possible object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public LOCALNORMALISEDDateTime getRequestClosedAtTimePoint() {
        return requestClosedAtTimePoint;
    }

    /**
     * Sets the value of the requestClosedAtTimePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public void setRequestClosedAtTimePoint(LOCALNORMALISEDDateTime value) {
        this.requestClosedAtTimePoint = value;
    }

    /**
     * Gets the value of the requestFinishedAtTimePoint property.
     * 
     * @return
     *     possible object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public LOCALNORMALISEDDateTime getRequestFinishedAtTimePoint() {
        return requestFinishedAtTimePoint;
    }

    /**
     * Sets the value of the requestFinishedAtTimePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public void setRequestFinishedAtTimePoint(LOCALNORMALISEDDateTime value) {
        this.requestFinishedAtTimePoint = value;
    }

    /**
     * Gets the value of the firstReactionDueTimePoint property.
     * 
     * @return
     *     possible object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public LOCALNORMALISEDDateTime getFirstReactionDueTimePoint() {
        return firstReactionDueTimePoint;
    }

    /**
     * Sets the value of the firstReactionDueTimePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public void setFirstReactionDueTimePoint(LOCALNORMALISEDDateTime value) {
        this.firstReactionDueTimePoint = value;
    }

    /**
     * Gets the value of the completionDueTimePoint property.
     * 
     * @return
     *     possible object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public LOCALNORMALISEDDateTime getCompletionDueTimePoint() {
        return completionDueTimePoint;
    }

    /**
     * Sets the value of the completionDueTimePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public void setCompletionDueTimePoint(LOCALNORMALISEDDateTime value) {
        this.completionDueTimePoint = value;
    }

    /**
     * Gets the value of the warrantyStartReferenceTimePoint property.
     * 
     * @return
     *     possible object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public LOCALNORMALISEDDateTime getWarrantyStartReferenceTimePoint() {
        return warrantyStartReferenceTimePoint;
    }

    /**
     * Sets the value of the warrantyStartReferenceTimePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public void setWarrantyStartReferenceTimePoint(LOCALNORMALISEDDateTime value) {
        this.warrantyStartReferenceTimePoint = value;
    }

}
