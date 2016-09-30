
package com.highdeal.ws.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="searchWindow" type="{http://schema.ws.highdeal.com/}SearchFilterWindow" minOccurs="0"/>
 *         &lt;element name="totalCountRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchFilter", propOrder = {
    "searchWindow",
    "totalCountRequired"
})
public class SearchFilter {

    protected SearchFilterWindow searchWindow;
    @XmlElement(defaultValue = "false")
    protected Boolean totalCountRequired;

    /**
     * Gets the value of the searchWindow property.
     * 
     * @return
     *     possible object is
     *     {@link SearchFilterWindow }
     *     
     */
    public SearchFilterWindow getSearchWindow() {
        return searchWindow;
    }

    /**
     * Sets the value of the searchWindow property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchFilterWindow }
     *     
     */
    public void setSearchWindow(SearchFilterWindow value) {
        this.searchWindow = value;
    }

    /**
     * Gets the value of the totalCountRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTotalCountRequired() {
        return totalCountRequired;
    }

    /**
     * Sets the value of the totalCountRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTotalCountRequired(Boolean value) {
        this.totalCountRequired = value;
    }

}
