
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceRequestMaintainRequestBundleMessage_sync complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestMaintainRequestBundleMessage_sync">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BasicMessageHeader" type="{http://sap.com/xi/AP/Common/GDT}BusinessDocumentBasicMessageHeader"/>
 *         &lt;element name="ServiceRequest" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundle" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestMaintainRequestBundleMessage_sync", propOrder = {
    "basicMessageHeader",
    "serviceRequest"
})
public class ServiceRequestMaintainRequestBundleMessageSync {

    @XmlElement(name = "BasicMessageHeader", required = true)
    protected BusinessDocumentBasicMessageHeader basicMessageHeader;
    @XmlElement(name = "ServiceRequest", required = true)
    protected List<ServiceRequestMaintainRequestBundle> serviceRequest;

    /**
     * Gets the value of the basicMessageHeader property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessDocumentBasicMessageHeader }
     *     
     */
    public BusinessDocumentBasicMessageHeader getBasicMessageHeader() {
        return basicMessageHeader;
    }

    /**
     * Sets the value of the basicMessageHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessDocumentBasicMessageHeader }
     *     
     */
    public void setBasicMessageHeader(BusinessDocumentBasicMessageHeader value) {
        this.basicMessageHeader = value;
    }

    /**
     * Gets the value of the serviceRequest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceRequest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceRequest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceRequestMaintainRequestBundle }
     * 
     * 
     */
    public List<ServiceRequestMaintainRequestBundle> getServiceRequest() {
        if (serviceRequest == null) {
            serviceRequest = new ArrayList<ServiceRequestMaintainRequestBundle>();
        }
        return this.serviceRequest;
    }

}
