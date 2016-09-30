
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
 * <p>Java class for ServiceRequestByElementsResponseItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestByElementsResponseItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentItemID" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://sap.com/xi/AP/Common/GDT}SHORT_Description" minOccurs="0"/>
 *         &lt;element name="ProductID" type="{http://sap.com/xi/AP/Common/GDT}ProductID" minOccurs="0"/>
 *         &lt;element name="RequestedQuantity" type="{http://sap.com/xi/AP/Common/GDT}Quantity" minOccurs="0"/>
 *         &lt;element name="FulfilledQuantity" type="{http://sap.com/xi/AP/Common/GDT}Quantity" minOccurs="0"/>
 *         &lt;element name="QuantityMeasureUnitCode" type="{http://sap.com/xi/AP/Common/GDT}MeasureUnitCode" minOccurs="0"/>
 *         &lt;element name="RequestedFulfilmentPeriod" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseItemRequestedFulfilmentPeriod" minOccurs="0"/>
 *         &lt;element name="ActualFulfilmentPeriod" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseItemActualFulfilmentPeriod" minOccurs="0"/>
 *         &lt;element name="Text" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseItemText" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="UserServiceTransactionProcessingTypeCode" type="{http://sap.com/xi/Common/DataTypes}UserServiceTransactionProcessingTypeCode" minOccurs="0"/>
 *         &lt;element name="ServiceTransactionProcessingTypeCode" type="{http://sap.com/xi/Common/DataTypes}ServiceTransactionProcessingTypeCode" minOccurs="0"/>
 *         &lt;element name="InvoicingMethodCode" type="{http://sap.com/xi/AP/Common/GDT}InvoicingMethodCode" minOccurs="0"/>
 *         &lt;element name="ExecutionReleaseStatusCode" type="{http://sap.com/xi/AP/Common/GDT}ReleaseStatusCode" minOccurs="0"/>
 *         &lt;element name="FulfilmentProcessingStatusCode" type="{http://sap.com/xi/AP/Common/GDT}ProcessingStatusCode" minOccurs="0"/>
 *         &lt;element name="ServiceRequestExecutionLifeCycleStatusCode" type="{http://sap.com/xi/AP/Common/GDT}ServiceRequestExecutionLifeCycleStatusCode" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestByElementsResponseItem", propOrder = {
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
    "serviceTransactionProcessingTypeCode",
    "invoicingMethodCode",
    "executionReleaseStatusCode",
    "fulfilmentProcessingStatusCode",
    "serviceRequestExecutionLifeCycleStatusCode"
})
public class ServiceRequestByElementsResponseItem {

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
    protected ServiceRequestByElementsResponseItemRequestedFulfilmentPeriod requestedFulfilmentPeriod;
    @XmlElement(name = "ActualFulfilmentPeriod")
    protected ServiceRequestByElementsResponseItemActualFulfilmentPeriod actualFulfilmentPeriod;
    @XmlElement(name = "Text")
    protected List<ServiceRequestByElementsResponseItemText> text;
    @XmlElement(name = "UserServiceTransactionProcessingTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String userServiceTransactionProcessingTypeCode;
    @XmlElement(name = "ServiceTransactionProcessingTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String serviceTransactionProcessingTypeCode;
    @XmlElement(name = "InvoicingMethodCode")
    protected InvoicingMethodCode invoicingMethodCode;
    @XmlElement(name = "ExecutionReleaseStatusCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String executionReleaseStatusCode;
    @XmlElement(name = "FulfilmentProcessingStatusCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String fulfilmentProcessingStatusCode;
    @XmlElement(name = "ServiceRequestExecutionLifeCycleStatusCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String serviceRequestExecutionLifeCycleStatusCode;

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
     *     {@link ServiceRequestByElementsResponseItemRequestedFulfilmentPeriod }
     *     
     */
    public ServiceRequestByElementsResponseItemRequestedFulfilmentPeriod getRequestedFulfilmentPeriod() {
        return requestedFulfilmentPeriod;
    }

    /**
     * Sets the value of the requestedFulfilmentPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsResponseItemRequestedFulfilmentPeriod }
     *     
     */
    public void setRequestedFulfilmentPeriod(ServiceRequestByElementsResponseItemRequestedFulfilmentPeriod value) {
        this.requestedFulfilmentPeriod = value;
    }

    /**
     * Gets the value of the actualFulfilmentPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsResponseItemActualFulfilmentPeriod }
     *     
     */
    public ServiceRequestByElementsResponseItemActualFulfilmentPeriod getActualFulfilmentPeriod() {
        return actualFulfilmentPeriod;
    }

    /**
     * Sets the value of the actualFulfilmentPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsResponseItemActualFulfilmentPeriod }
     *     
     */
    public void setActualFulfilmentPeriod(ServiceRequestByElementsResponseItemActualFulfilmentPeriod value) {
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
     * {@link ServiceRequestByElementsResponseItemText }
     * 
     * 
     */
    public List<ServiceRequestByElementsResponseItemText> getText() {
        if (text == null) {
            text = new ArrayList<ServiceRequestByElementsResponseItemText>();
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
     * Gets the value of the serviceTransactionProcessingTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceTransactionProcessingTypeCode() {
        return serviceTransactionProcessingTypeCode;
    }

    /**
     * Sets the value of the serviceTransactionProcessingTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTransactionProcessingTypeCode(String value) {
        this.serviceTransactionProcessingTypeCode = value;
    }

    /**
     * Gets the value of the invoicingMethodCode property.
     * 
     * @return
     *     possible object is
     *     {@link InvoicingMethodCode }
     *     
     */
    public InvoicingMethodCode getInvoicingMethodCode() {
        return invoicingMethodCode;
    }

    /**
     * Sets the value of the invoicingMethodCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoicingMethodCode }
     *     
     */
    public void setInvoicingMethodCode(InvoicingMethodCode value) {
        this.invoicingMethodCode = value;
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
     * Gets the value of the serviceRequestExecutionLifeCycleStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceRequestExecutionLifeCycleStatusCode() {
        return serviceRequestExecutionLifeCycleStatusCode;
    }

    /**
     * Sets the value of the serviceRequestExecutionLifeCycleStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceRequestExecutionLifeCycleStatusCode(String value) {
        this.serviceRequestExecutionLifeCycleStatusCode = value;
    }

}
