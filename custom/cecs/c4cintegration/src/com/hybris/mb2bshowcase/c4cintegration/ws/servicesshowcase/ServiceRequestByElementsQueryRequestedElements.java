
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ServiceRequestByElementsQueryRequestedElements complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestByElementsQueryRequestedElements">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceRequest" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsQueryRequestedElementsServiceRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="serviceRequestTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestByElementsQueryRequestedElements", propOrder = {
    "serviceRequest"
})
public class ServiceRequestByElementsQueryRequestedElements {

    @XmlElement(name = "ServiceRequest")
    protected ServiceRequestByElementsQueryRequestedElementsServiceRequest serviceRequest;
    @XmlAttribute(name = "serviceRequestTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String serviceRequestTransmissionRequestCode;

    /**
     * Gets the value of the serviceRequest property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsQueryRequestedElementsServiceRequest }
     *     
     */
    public ServiceRequestByElementsQueryRequestedElementsServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    /**
     * Sets the value of the serviceRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsQueryRequestedElementsServiceRequest }
     *     
     */
    public void setServiceRequest(ServiceRequestByElementsQueryRequestedElementsServiceRequest value) {
        this.serviceRequest = value;
    }

    /**
     * Gets the value of the serviceRequestTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceRequestTransmissionRequestCode() {
        return serviceRequestTransmissionRequestCode;
    }

    /**
     * Sets the value of the serviceRequestTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceRequestTransmissionRequestCode(String value) {
        this.serviceRequestTransmissionRequestCode = value;
    }

}
