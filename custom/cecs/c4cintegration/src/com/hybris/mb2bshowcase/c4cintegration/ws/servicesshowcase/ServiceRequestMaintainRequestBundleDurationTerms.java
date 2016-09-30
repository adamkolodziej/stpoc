
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;


/**
 * <p>Java class for ServiceRequestMaintainRequestBundleDurationTerms complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestMaintainRequestBundleDurationTerms">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestTotalProcessingDuration" type="{http://sap.com/xi/AP/Common/GDT}Duration" minOccurs="0"/>
 *         &lt;element name="RequestTotalRequestorDuration" type="{http://sap.com/xi/AP/Common/GDT}Duration" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestMaintainRequestBundleDurationTerms", propOrder = {
    "requestTotalProcessingDuration",
    "requestTotalRequestorDuration"
})
public class ServiceRequestMaintainRequestBundleDurationTerms {

    @XmlElement(name = "RequestTotalProcessingDuration")
    protected Duration requestTotalProcessingDuration;
    @XmlElement(name = "RequestTotalRequestorDuration")
    protected Duration requestTotalRequestorDuration;

    /**
     * Gets the value of the requestTotalProcessingDuration property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getRequestTotalProcessingDuration() {
        return requestTotalProcessingDuration;
    }

    /**
     * Sets the value of the requestTotalProcessingDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setRequestTotalProcessingDuration(Duration value) {
        this.requestTotalProcessingDuration = value;
    }

    /**
     * Gets the value of the requestTotalRequestorDuration property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getRequestTotalRequestorDuration() {
        return requestTotalRequestorDuration;
    }

    /**
     * Sets the value of the requestTotalRequestorDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setRequestTotalRequestorDuration(Duration value) {
        this.requestTotalRequestorDuration = value;
    }

}
