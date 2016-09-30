
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
 * <p>Java class for ServiceRequestMaintainRequestBundleItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestMaintainRequestBundleItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID" minOccurs="0"/>
 *         &lt;element name="ID" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentItemID" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://sap.com/xi/AP/Common/GDT}SHORT_Description" minOccurs="0"/>
 *         &lt;element name="ProductID" type="{http://sap.com/xi/AP/Common/GDT}ProductID" minOccurs="0"/>
 *         &lt;element name="RequestedQuantity" type="{http://sap.com/xi/AP/Common/GDT}Quantity" minOccurs="0"/>
 *         &lt;element name="FulfilledQuantity" type="{http://sap.com/xi/AP/Common/GDT}Quantity" minOccurs="0"/>
 *         &lt;element name="QuantityMeasureUnitCode" type="{http://sap.com/xi/AP/Common/GDT}MeasureUnitCode" minOccurs="0"/>
 *         &lt;element name="RequestedFulfilmentPeriod" type="{http://sap.com/xi/AP/Common/GDT}UPPEROPEN_LOCALNORMALISED_DateTimePeriod" minOccurs="0"/>
 *         &lt;element name="ActualFulfilmentPeriod" type="{http://sap.com/xi/AP/Common/GDT}UPPEROPEN_LOCALNORMALISED_DateTimePeriod" minOccurs="0"/>
 *         &lt;element name="Text" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleText" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="UserServiceTransactionProcessingTypeCode" type="{http://sap.com/xi/Common/DataTypes}UserServiceTransactionProcessingTypeCode" minOccurs="0"/>
 *         &lt;element name="ExecutionReleaseStatusCode" type="{http://sap.com/xi/AP/Common/GDT}ReleaseStatusCode" minOccurs="0"/>
 *         &lt;element name="FulfilmentProcessingStatusCode" type="{http://sap.com/xi/AP/Common/GDT}ProcessingStatusCode" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="actionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" />
 *       &lt;attribute name="textListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestMaintainRequestBundleItem", propOrder = {
    "objectNodeSenderTechnicalID",
    "id",
    "description",
    "productID",
    "requestedQuantity",
    "fulfilledQuantity",
    "quantityMeasureUnitCode",
    "requestedFulfilmentPeriod",
    "actualFulfilmentPeriod",
    "text",
    "userServiceTransactionProcessingTypeCode",
    "executionReleaseStatusCode",
    "fulfilmentProcessingStatusCode"
})
public class ServiceRequestMaintainRequestBundleItem {

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String objectNodeSenderTechnicalID;
    @XmlElement(name = "ID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String id;
    @XmlElement(name = "Description")
    protected SHORTDescription description;
    @XmlElement(name = "ProductID")
    protected ProductID productID;
    @XmlElement(name = "RequestedQuantity")
    protected Quantity requestedQuantity;
    @XmlElement(name = "FulfilledQuantity")
    protected Quantity fulfilledQuantity;
    @XmlElement(name = "QuantityMeasureUnitCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String quantityMeasureUnitCode;
    @XmlElement(name = "RequestedFulfilmentPeriod")
    protected UPPEROPENLOCALNORMALISEDDateTimePeriod requestedFulfilmentPeriod;
    @XmlElement(name = "ActualFulfilmentPeriod")
    protected UPPEROPENLOCALNORMALISEDDateTimePeriod actualFulfilmentPeriod;
    @XmlElement(name = "Text")
    protected List<ServiceRequestMaintainRequestBundleText> text;
    @XmlElement(name = "UserServiceTransactionProcessingTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String userServiceTransactionProcessingTypeCode;
    @XmlElement(name = "ExecutionReleaseStatusCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String executionReleaseStatusCode;
    @XmlElement(name = "FulfilmentProcessingStatusCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String fulfilmentProcessingStatusCode;
    @XmlAttribute(name = "actionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;
    @XmlAttribute(name = "textListCompleteTransmissionIndicator")
    protected Boolean textListCompleteTransmissionIndicator;

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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link SHORTDescription }
     *     
     */
    public SHORTDescription getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link SHORTDescription }
     *     
     */
    public void setDescription(SHORTDescription value) {
        this.description = value;
    }

    /**
     * Gets the value of the productID property.
     * 
     * @return
     *     possible object is
     *     {@link ProductID }
     *     
     */
    public ProductID getProductID() {
        return productID;
    }

    /**
     * Sets the value of the productID property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductID }
     *     
     */
    public void setProductID(ProductID value) {
        this.productID = value;
    }

    /**
     * Gets the value of the requestedQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Quantity }
     *     
     */
    public Quantity getRequestedQuantity() {
        return requestedQuantity;
    }

    /**
     * Sets the value of the requestedQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Quantity }
     *     
     */
    public void setRequestedQuantity(Quantity value) {
        this.requestedQuantity = value;
    }

    /**
     * Gets the value of the fulfilledQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Quantity }
     *     
     */
    public Quantity getFulfilledQuantity() {
        return fulfilledQuantity;
    }

    /**
     * Sets the value of the fulfilledQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Quantity }
     *     
     */
    public void setFulfilledQuantity(Quantity value) {
        this.fulfilledQuantity = value;
    }

    /**
     * Gets the value of the quantityMeasureUnitCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantityMeasureUnitCode() {
        return quantityMeasureUnitCode;
    }

    /**
     * Sets the value of the quantityMeasureUnitCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantityMeasureUnitCode(String value) {
        this.quantityMeasureUnitCode = value;
    }

    /**
     * Gets the value of the requestedFulfilmentPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link UPPEROPENLOCALNORMALISEDDateTimePeriod }
     *     
     */
    public UPPEROPENLOCALNORMALISEDDateTimePeriod getRequestedFulfilmentPeriod() {
        return requestedFulfilmentPeriod;
    }

    /**
     * Sets the value of the requestedFulfilmentPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link UPPEROPENLOCALNORMALISEDDateTimePeriod }
     *     
     */
    public void setRequestedFulfilmentPeriod(UPPEROPENLOCALNORMALISEDDateTimePeriod value) {
        this.requestedFulfilmentPeriod = value;
    }

    /**
     * Gets the value of the actualFulfilmentPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link UPPEROPENLOCALNORMALISEDDateTimePeriod }
     *     
     */
    public UPPEROPENLOCALNORMALISEDDateTimePeriod getActualFulfilmentPeriod() {
        return actualFulfilmentPeriod;
    }

    /**
     * Sets the value of the actualFulfilmentPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link UPPEROPENLOCALNORMALISEDDateTimePeriod }
     *     
     */
    public void setActualFulfilmentPeriod(UPPEROPENLOCALNORMALISEDDateTimePeriod value) {
        this.actualFulfilmentPeriod = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the text property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getText().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceRequestMaintainRequestBundleText }
     * 
     * 
     */
    public List<ServiceRequestMaintainRequestBundleText> getText() {
        if (text == null) {
            text = new ArrayList<ServiceRequestMaintainRequestBundleText>();
        }
        return this.text;
    }

    /**
     * Gets the value of the userServiceTransactionProcessingTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserServiceTransactionProcessingTypeCode() {
        return userServiceTransactionProcessingTypeCode;
    }

    /**
     * Sets the value of the userServiceTransactionProcessingTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserServiceTransactionProcessingTypeCode(String value) {
        this.userServiceTransactionProcessingTypeCode = value;
    }

    /**
     * Gets the value of the executionReleaseStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExecutionReleaseStatusCode() {
        return executionReleaseStatusCode;
    }

    /**
     * Sets the value of the executionReleaseStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExecutionReleaseStatusCode(String value) {
        this.executionReleaseStatusCode = value;
    }

    /**
     * Gets the value of the fulfilmentProcessingStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFulfilmentProcessingStatusCode() {
        return fulfilmentProcessingStatusCode;
    }

    /**
     * Sets the value of the fulfilmentProcessingStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFulfilmentProcessingStatusCode(String value) {
        this.fulfilmentProcessingStatusCode = value;
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

    /**
     * Gets the value of the textListCompleteTransmissionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTextListCompleteTransmissionIndicator() {
        return textListCompleteTransmissionIndicator;
    }

    /**
     * Sets the value of the textListCompleteTransmissionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTextListCompleteTransmissionIndicator(Boolean value) {
        this.textListCompleteTransmissionIndicator = value;
    }

}
