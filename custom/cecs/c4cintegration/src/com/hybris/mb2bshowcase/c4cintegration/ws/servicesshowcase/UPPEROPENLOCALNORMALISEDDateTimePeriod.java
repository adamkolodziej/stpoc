
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UPPEROPEN_LOCALNORMALISED_DateTimePeriod complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UPPEROPEN_LOCALNORMALISED_DateTimePeriod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StartDateTime" type="{http://sap.com/xi/BASIS/Global}LOCALNORMALISED_DateTime" minOccurs="0"/>
 *         &lt;element name="EndDateTime" type="{http://sap.com/xi/BASIS/Global}LOCALNORMALISED_DateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UPPEROPEN_LOCALNORMALISED_DateTimePeriod", namespace = "http://sap.com/xi/AP/Common/GDT", propOrder = {
    "startDateTime",
    "endDateTime"
})
public class UPPEROPENLOCALNORMALISEDDateTimePeriod {

    @XmlElement(name = "StartDateTime")
    protected LOCALNORMALISEDDateTime startDateTime;
    @XmlElement(name = "EndDateTime")
    protected LOCALNORMALISEDDateTime endDateTime;

    /**
     * Gets the value of the startDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public LOCALNORMALISEDDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Sets the value of the startDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public void setStartDateTime(LOCALNORMALISEDDateTime value) {
        this.startDateTime = value;
    }

    /**
     * Gets the value of the endDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public LOCALNORMALISEDDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Sets the value of the endDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public void setEndDateTime(LOCALNORMALISEDDateTime value) {
        this.endDateTime = value;
    }

}
