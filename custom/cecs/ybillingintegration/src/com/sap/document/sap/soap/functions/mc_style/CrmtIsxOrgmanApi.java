
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrmtIsxOrgmanApi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrmtIsxOrgmanApi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SalesOrg" type="{urn:sap-com:document:sap:rfc:functions}char14"/>
 *         &lt;element name="SalesOffice" type="{urn:sap-com:document:sap:rfc:functions}char14"/>
 *         &lt;element name="SalesGroup" type="{urn:sap-com:document:sap:rfc:functions}char14"/>
 *         &lt;element name="DisChannel" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
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
@XmlType(name = "CrmtIsxOrgmanApi", propOrder = {
    "salesOrg",
    "salesOffice",
    "salesGroup",
    "disChannel",
    "division"
})
public class CrmtIsxOrgmanApi {

    @XmlElement(name = "SalesOrg", required = true)
    protected String salesOrg;
    @XmlElement(name = "SalesOffice", required = true)
    protected String salesOffice;
    @XmlElement(name = "SalesGroup", required = true)
    protected String salesGroup;
    @XmlElement(name = "DisChannel", required = true)
    protected String disChannel;
    @XmlElement(name = "Division", required = true)
    protected String division;

    /**
     * Gets the value of the salesOrg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesOrg() {
        return salesOrg;
    }

    /**
     * Sets the value of the salesOrg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesOrg(String value) {
        this.salesOrg = value;
    }

    /**
     * Gets the value of the salesOffice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesOffice() {
        return salesOffice;
    }

    /**
     * Sets the value of the salesOffice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesOffice(String value) {
        this.salesOffice = value;
    }

    /**
     * Gets the value of the salesGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesGroup() {
        return salesGroup;
    }

    /**
     * Sets the value of the salesGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesGroup(String value) {
        this.salesGroup = value;
    }

    /**
     * Gets the value of the disChannel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisChannel() {
        return disChannel;
    }

    /**
     * Sets the value of the disChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisChannel(String value) {
        this.disChannel = value;
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
