
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ServiceRequestMaintainRequestBundleSalesAndServiceBusinessArea complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestMaintainRequestBundleSalesAndServiceBusinessArea">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SalesOrganisationID" type="{http://sap.com/xi/AP/Common/GDT}OrganisationalCentreID" minOccurs="0"/>
 *         &lt;element name="DistributionChannelCode" type="{http://sap.com/xi/AP/Common/GDT}DistributionChannelCode" minOccurs="0"/>
 *         &lt;element name="DivisionCode" type="{http://sap.com/xi/AP/Common/GDT}DivisionCode" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestMaintainRequestBundleSalesAndServiceBusinessArea", propOrder = {
    "salesOrganisationID",
    "distributionChannelCode",
    "divisionCode"
})
public class ServiceRequestMaintainRequestBundleSalesAndServiceBusinessArea {

    @XmlElement(name = "SalesOrganisationID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String salesOrganisationID;
    @XmlElement(name = "DistributionChannelCode")
    protected DistributionChannelCode distributionChannelCode;
    @XmlElement(name = "DivisionCode")
    protected DivisionCode divisionCode;

    /**
     * Gets the value of the salesOrganisationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesOrganisationID() {
        return salesOrganisationID;
    }

    /**
     * Sets the value of the salesOrganisationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesOrganisationID(String value) {
        this.salesOrganisationID = value;
    }

    /**
     * Gets the value of the distributionChannelCode property.
     * 
     * @return
     *     possible object is
     *     {@link DistributionChannelCode }
     *     
     */
    public DistributionChannelCode getDistributionChannelCode() {
        return distributionChannelCode;
    }

    /**
     * Sets the value of the distributionChannelCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link DistributionChannelCode }
     *     
     */
    public void setDistributionChannelCode(DistributionChannelCode value) {
        this.distributionChannelCode = value;
    }

    /**
     * Gets the value of the divisionCode property.
     * 
     * @return
     *     possible object is
     *     {@link DivisionCode }
     *     
     */
    public DivisionCode getDivisionCode() {
        return divisionCode;
    }

    /**
     * Sets the value of the divisionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link DivisionCode }
     *     
     */
    public void setDivisionCode(DivisionCode value) {
        this.divisionCode = value;
    }

}
