
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrmtIsxGenDataDcApi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrmtIsxGenDataDcApi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SoldToParty" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxPartnerApi"/>
 *         &lt;element name="Employee" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Orgman" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxOrgmanApi"/>
 *         &lt;element name="ProcessType" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrmtIsxGenDataDcApi", propOrder = {
    "soldToParty",
    "employee",
    "orgman",
    "processType"
})
public class CrmtIsxGenDataDcApi {

    @XmlElement(name = "SoldToParty", required = true)
    protected CrmtIsxPartnerApi soldToParty;
    @XmlElement(name = "Employee", required = true)
    protected String employee;
    @XmlElement(name = "Orgman", required = true)
    protected CrmtIsxOrgmanApi orgman;
    @XmlElement(name = "ProcessType", required = true)
    protected String processType;

    /**
     * Gets the value of the soldToParty property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxPartnerApi }
     *     
     */
    public CrmtIsxPartnerApi getSoldToParty() {
        return soldToParty;
    }

    /**
     * Sets the value of the soldToParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxPartnerApi }
     *     
     */
    public void setSoldToParty(CrmtIsxPartnerApi value) {
        this.soldToParty = value;
    }

    /**
     * Gets the value of the employee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployee() {
        return employee;
    }

    /**
     * Sets the value of the employee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployee(String value) {
        this.employee = value;
    }

    /**
     * Gets the value of the orgman property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxOrgmanApi }
     *     
     */
    public CrmtIsxOrgmanApi getOrgman() {
        return orgman;
    }

    /**
     * Sets the value of the orgman property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxOrgmanApi }
     *     
     */
    public void setOrgman(CrmtIsxOrgmanApi value) {
        this.orgman = value;
    }

    /**
     * Gets the value of the processType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessType() {
        return processType;
    }

    /**
     * Sets the value of the processType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessType(String value) {
        this.processType = value;
    }

}
