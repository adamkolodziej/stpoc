
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseProcessingConditions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseProcessingConditions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReturnedQueryHitsNumberValue" type="{http://sap.com/xi/AP/Common/GDT}NumberValue"/>
 *         &lt;element name="MoreHitsAvailableIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator"/>
 *         &lt;element name="LastReturnedObjectID" type="{http://sap.com/xi/AP/Common/GDT}ObjectID" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseProcessingConditions", namespace = "http://sap.com/xi/AP/Common/GDT", propOrder = {
    "returnedQueryHitsNumberValue",
    "moreHitsAvailableIndicator",
    "lastReturnedObjectID"
})
public class ResponseProcessingConditions {

    @XmlElement(name = "ReturnedQueryHitsNumberValue")
    protected int returnedQueryHitsNumberValue;
    @XmlElement(name = "MoreHitsAvailableIndicator")
    protected boolean moreHitsAvailableIndicator;
    @XmlElement(name = "LastReturnedObjectID")
    protected ObjectID lastReturnedObjectID;

    /**
     * Gets the value of the returnedQueryHitsNumberValue property.
     * 
     */
    public int getReturnedQueryHitsNumberValue() {
        return returnedQueryHitsNumberValue;
    }

    /**
     * Sets the value of the returnedQueryHitsNumberValue property.
     * 
     */
    public void setReturnedQueryHitsNumberValue(int value) {
        this.returnedQueryHitsNumberValue = value;
    }

    /**
     * Gets the value of the moreHitsAvailableIndicator property.
     * 
     */
    public boolean isMoreHitsAvailableIndicator() {
        return moreHitsAvailableIndicator;
    }

    /**
     * Sets the value of the moreHitsAvailableIndicator property.
     * 
     */
    public void setMoreHitsAvailableIndicator(boolean value) {
        this.moreHitsAvailableIndicator = value;
    }

    /**
     * Gets the value of the lastReturnedObjectID property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectID }
     *     
     */
    public ObjectID getLastReturnedObjectID() {
        return lastReturnedObjectID;
    }

    /**
     * Sets the value of the lastReturnedObjectID property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectID }
     *     
     */
    public void setLastReturnedObjectID(ObjectID value) {
        this.lastReturnedObjectID = value;
    }

}
