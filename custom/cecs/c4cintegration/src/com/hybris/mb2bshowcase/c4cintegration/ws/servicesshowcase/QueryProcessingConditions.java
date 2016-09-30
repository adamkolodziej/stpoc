
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueryProcessingConditions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryProcessingConditions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QueryHitsMaximumNumberValue" type="{http://sap.com/xi/AP/Common/GDT}NumberValue" minOccurs="0"/>
 *         &lt;element name="QueryHitsUnlimitedIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator"/>
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
@XmlType(name = "QueryProcessingConditions", namespace = "http://sap.com/xi/AP/Common/GDT", propOrder = {
    "queryHitsMaximumNumberValue",
    "queryHitsUnlimitedIndicator",
    "lastReturnedObjectID"
})
public class QueryProcessingConditions {

    @XmlElement(name = "QueryHitsMaximumNumberValue")
    protected Integer queryHitsMaximumNumberValue;
    @XmlElement(name = "QueryHitsUnlimitedIndicator")
    protected boolean queryHitsUnlimitedIndicator;
    @XmlElement(name = "LastReturnedObjectID")
    protected ObjectID lastReturnedObjectID;

    /**
     * Gets the value of the queryHitsMaximumNumberValue property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQueryHitsMaximumNumberValue() {
        return queryHitsMaximumNumberValue;
    }

    /**
     * Sets the value of the queryHitsMaximumNumberValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQueryHitsMaximumNumberValue(Integer value) {
        this.queryHitsMaximumNumberValue = value;
    }

    /**
     * Gets the value of the queryHitsUnlimitedIndicator property.
     * 
     */
    public boolean isQueryHitsUnlimitedIndicator() {
        return queryHitsUnlimitedIndicator;
    }

    /**
     * Sets the value of the queryHitsUnlimitedIndicator property.
     * 
     */
    public void setQueryHitsUnlimitedIndicator(boolean value) {
        this.queryHitsUnlimitedIndicator = value;
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
