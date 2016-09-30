
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
 * <p>Java class for ServiceRequestMaintainRequestBundleBuyerParty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestMaintainRequestBundleBuyerParty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BusinessPartnerInternalID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerInternalID" minOccurs="0"/>
 *         &lt;element name="MainContactParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleBuyerPartyMainContactParty" minOccurs="0"/>
 *         &lt;element name="ContactParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleBuyerPartyContactParty" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="contactPartyListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestMaintainRequestBundleBuyerParty", propOrder = {
    "businessPartnerInternalID",
    "mainContactParty",
    "contactParty"
})
public class ServiceRequestMaintainRequestBundleBuyerParty {

    @XmlElement(name = "BusinessPartnerInternalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String businessPartnerInternalID;
    @XmlElement(name = "MainContactParty")
    protected ServiceRequestMaintainRequestBundleBuyerPartyMainContactParty mainContactParty;
    @XmlElement(name = "ContactParty")
    protected List<ServiceRequestMaintainRequestBundleBuyerPartyContactParty> contactParty;
    @XmlAttribute(name = "contactPartyListCompleteTransmissionIndicator")
    protected Boolean contactPartyListCompleteTransmissionIndicator;

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

    /**
     * Gets the value of the mainContactParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleBuyerPartyMainContactParty }
     *     
     */
    public ServiceRequestMaintainRequestBundleBuyerPartyMainContactParty getMainContactParty() {
        return mainContactParty;
    }

    /**
     * Sets the value of the mainContactParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleBuyerPartyMainContactParty }
     *     
     */
    public void setMainContactParty(ServiceRequestMaintainRequestBundleBuyerPartyMainContactParty value) {
        this.mainContactParty = value;
    }

    /**
     * Gets the value of the contactParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contactParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContactParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceRequestMaintainRequestBundleBuyerPartyContactParty }
     * 
     * 
     */
    public List<ServiceRequestMaintainRequestBundleBuyerPartyContactParty> getContactParty() {
        if (contactParty == null) {
            contactParty = new ArrayList<ServiceRequestMaintainRequestBundleBuyerPartyContactParty>();
        }
        return this.contactParty;
    }

    /**
     * Gets the value of the contactPartyListCompleteTransmissionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isContactPartyListCompleteTransmissionIndicator() {
        return contactPartyListCompleteTransmissionIndicator;
    }

    /**
     * Sets the value of the contactPartyListCompleteTransmissionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setContactPartyListCompleteTransmissionIndicator(Boolean value) {
        this.contactPartyListCompleteTransmissionIndicator = value;
    }

}
