
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceRequestByElementsQueryMessage_sync complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestByElementsQueryMessage_sync">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceRequestSelectionByElements" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsQuerySelectionByElements" minOccurs="0"/>
 *         &lt;element name="ProcessingConditions" type="{http://sap.com/xi/AP/Common/GDT}QueryProcessingConditions" minOccurs="0"/>
 *         &lt;element name="RequestedElements" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsQueryRequestedElements" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestByElementsQueryMessage_sync", propOrder = {
    "serviceRequestSelectionByElements",
    "processingConditions",
    "requestedElements"
})
public class ServiceRequestByElementsQueryMessageSync {

    @XmlElement(name = "ServiceRequestSelectionByElements")
    protected ServiceRequestByElementsQuerySelectionByElements serviceRequestSelectionByElements;
    @XmlElement(name = "ProcessingConditions")
    protected QueryProcessingConditions processingConditions;
    @XmlElement(name = "RequestedElements")
    protected ServiceRequestByElementsQueryRequestedElements requestedElements;

    /**
     * Gets the value of the serviceRequestSelectionByElements property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsQuerySelectionByElements }
     *     
     */
    public ServiceRequestByElementsQuerySelectionByElements getServiceRequestSelectionByElements() {
        return serviceRequestSelectionByElements;
    }

    /**
     * Sets the value of the serviceRequestSelectionByElements property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsQuerySelectionByElements }
     *     
     */
    public void setServiceRequestSelectionByElements(ServiceRequestByElementsQuerySelectionByElements value) {
        this.serviceRequestSelectionByElements = value;
    }

    /**
     * Gets the value of the processingConditions property.
     * 
     * @return
     *     possible object is
     *     {@link QueryProcessingConditions }
     *     
     */
    public QueryProcessingConditions getProcessingConditions() {
        return processingConditions;
    }

    /**
     * Sets the value of the processingConditions property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryProcessingConditions }
     *     
     */
    public void setProcessingConditions(QueryProcessingConditions value) {
        this.processingConditions = value;
    }

    /**
     * Gets the value of the requestedElements property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsQueryRequestedElements }
     *     
     */
    public ServiceRequestByElementsQueryRequestedElements getRequestedElements() {
        return requestedElements;
    }

    /**
     * Sets the value of the requestedElements property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsQueryRequestedElements }
     *     
     */
    public void setRequestedElements(ServiceRequestByElementsQueryRequestedElements value) {
        this.requestedElements = value;
    }

}
