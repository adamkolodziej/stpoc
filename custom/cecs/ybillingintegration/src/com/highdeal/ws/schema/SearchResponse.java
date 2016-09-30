
package com.highdeal.ws.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schema.ws.highdeal.com/}Response">
 *       &lt;sequence>
 *         &lt;element name="totalCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="lastUniqueId" type="{http://schema.ws.highdeal.com/}String256" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchResponse", propOrder = {
    "totalCount",
    "lastUniqueId"
})
public class SearchResponse
    extends Response
{

    protected Integer totalCount;
    protected String lastUniqueId;

    /**
     * Gets the value of the totalCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * Sets the value of the totalCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalCount(Integer value) {
        this.totalCount = value;
    }

    /**
     * Gets the value of the lastUniqueId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUniqueId() {
        return lastUniqueId;
    }

    /**
     * Sets the value of the lastUniqueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUniqueId(String value) {
        this.lastUniqueId = value;
    }

}
