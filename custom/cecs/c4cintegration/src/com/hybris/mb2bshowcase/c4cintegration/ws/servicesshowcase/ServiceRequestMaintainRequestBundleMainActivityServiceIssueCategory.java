
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceRequestMaintainRequestBundleMainActivityServiceIssueCategory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestMaintainRequestBundleMainActivityServiceIssueCategory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceIssueCategoryID" type="{http://sap.com/xi/AP/Common/GDT}ServiceIssueCategoryID" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestMaintainRequestBundleMainActivityServiceIssueCategory", propOrder = {
    "serviceIssueCategoryID"
})
public class ServiceRequestMaintainRequestBundleMainActivityServiceIssueCategory {

    @XmlElement(name = "ServiceIssueCategoryID")
    protected ServiceIssueCategoryID serviceIssueCategoryID;

    /**
     * Gets the value of the serviceIssueCategoryID property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceIssueCategoryID }
     *     
     */
    public ServiceIssueCategoryID getServiceIssueCategoryID() {
        return serviceIssueCategoryID;
    }

    /**
     * Sets the value of the serviceIssueCategoryID property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceIssueCategoryID }
     *     
     */
    public void setServiceIssueCategoryID(ServiceIssueCategoryID value) {
        this.serviceIssueCategoryID = value;
    }

}
