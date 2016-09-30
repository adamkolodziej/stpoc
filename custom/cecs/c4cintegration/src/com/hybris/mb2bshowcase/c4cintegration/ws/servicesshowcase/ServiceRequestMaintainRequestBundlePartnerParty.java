
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ServiceRequestMaintainRequestBundlePartnerParty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestMaintainRequestBundlePartnerParty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BusinessPartnerInternalID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerInternalID" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestMaintainRequestBundlePartnerParty", propOrder = {
    "businessPartnerInternalID"
})
public class ServiceRequestMaintainRequestBundlePartnerParty {

    @XmlElement(name = "BusinessPartnerInternalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String businessPartnerInternalID;

    /**
     * Gets the value of the businessPartnerInternalID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessPartnerInternalID() {
        return businessPartnerInternalID;
    }

    /**
     * Sets the value of the businessPartnerInternalID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessPartnerInternalID(String value) {
        this.businessPartnerInternalID = value;
    }

}
