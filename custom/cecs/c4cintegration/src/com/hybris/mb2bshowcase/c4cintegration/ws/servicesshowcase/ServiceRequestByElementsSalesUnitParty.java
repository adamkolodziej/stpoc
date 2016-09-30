
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ServiceRequestByElementsSalesUnitParty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestByElementsSalesUnitParty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrganisationalCentreID" type="{http://sap.com/xi/AP/Common/GDT}OrganisationalCentreID" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestByElementsSalesUnitParty", propOrder = {
    "organisationalCentreID"
})
public class ServiceRequestByElementsSalesUnitParty {

    @XmlElement(name = "OrganisationalCentreID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String organisationalCentreID;

    /**
     * Gets the value of the organisationalCentreID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganisationalCentreID() {
        return organisationalCentreID;
    }

    /**
     * Sets the value of the organisationalCentreID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganisationalCentreID(String value) {
        this.organisationalCentreID = value;
    }

}
