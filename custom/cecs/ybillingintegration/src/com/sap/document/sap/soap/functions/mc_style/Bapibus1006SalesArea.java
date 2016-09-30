
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapibus1006SalesArea complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapibus1006SalesArea">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SalesOrganization" type="{urn:sap-com:document:sap:rfc:functions}char14"/>
 *         &lt;element name="DistributionChannel" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Division" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapibus1006SalesArea", propOrder = {
    "salesOrganization",
    "distributionChannel",
    "division"
})
public class Bapibus1006SalesArea {

    @XmlElement(name = "SalesOrganization", required = true)
    protected String salesOrganization;
    @XmlElement(name = "DistributionChannel", required = true)
    protected String distributionChannel;
    @XmlElement(name = "Division", required = true)
    protected String division;

    /**
     * Gets the value of the salesOrganization property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesOrganization() {
        return salesOrganization;
    }

    /**
     * Sets the value of the salesOrganization property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesOrganization(String value) {
        this.salesOrganization = value;
    }

    /**
     * Gets the value of the distributionChannel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistributionChannel() {
        return distributionChannel;
    }

    /**
     * Sets the value of the distributionChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistributionChannel(String value) {
        this.distributionChannel = value;
    }

    /**
     * Gets the value of the division property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDivision() {
        return division;
    }

    /**
     * Sets the value of the division property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDivision(String value) {
        this.division = value;
    }

}
