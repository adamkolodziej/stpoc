
package com.highdeal.ws.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *             The settings to enable reference-based pagination of search results in response. Several operation requests are necessary to retrieve the complete results.
 *           
 * 
 * <p>Java class for SearchFilterWindow complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchFilterWindow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="windowSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="fromUniqueId" type="{http://schema.ws.highdeal.com/}String256" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchFilterWindow", propOrder = {
    "windowSize",
    "fromUniqueId"
})
public class SearchFilterWindow {

    protected Integer windowSize;
    protected String fromUniqueId;

    /**
     * Gets the value of the windowSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWindowSize() {
        return windowSize;
    }

    /**
     * Sets the value of the windowSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWindowSize(Integer value) {
        this.windowSize = value;
    }

    /**
     * Gets the value of the fromUniqueId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromUniqueId() {
        return fromUniqueId;
    }

    /**
     * Sets the value of the fromUniqueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromUniqueId(String value) {
        this.fromUniqueId = value;
    }

}
