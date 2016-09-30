
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IvBusinesspartner" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ivBusinesspartner"
})
@XmlRootElement(name = "ZcrmBupaContractHistory")
public class ZcrmBupaContractHistory {

    @XmlElement(name = "IvBusinesspartner", required = true)
    protected String ivBusinesspartner;

    /**
     * Gets the value of the ivBusinesspartner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIvBusinesspartner() {
        return ivBusinesspartner;
    }

    /**
     * Sets the value of the ivBusinesspartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIvBusinesspartner(String value) {
        this.ivBusinesspartner = value;
    }

}
