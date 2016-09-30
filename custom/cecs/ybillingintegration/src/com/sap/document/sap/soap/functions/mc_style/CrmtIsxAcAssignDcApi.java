
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrmtIsxAcAssignDcApi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrmtIsxAcAssignDcApi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RefToItemHandle" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxHandleApiT"/>
 *         &lt;element name="AcObjectType" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="AcAssignment" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrmtIsxAcAssignDcApi", propOrder = {
    "refToItemHandle",
    "acObjectType",
    "acAssignment"
})
public class CrmtIsxAcAssignDcApi {

    @XmlElement(name = "RefToItemHandle", required = true)
    protected CrmtIsxHandleApiT refToItemHandle;
    @XmlElement(name = "AcObjectType", required = true)
    protected String acObjectType;
    @XmlElement(name = "AcAssignment", required = true)
    protected String acAssignment;

    /**
     * Gets the value of the refToItemHandle property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxHandleApiT }
     *     
     */
    public CrmtIsxHandleApiT getRefToItemHandle() {
        return refToItemHandle;
    }

    /**
     * Sets the value of the refToItemHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxHandleApiT }
     *     
     */
    public void setRefToItemHandle(CrmtIsxHandleApiT value) {
        this.refToItemHandle = value;
    }

    /**
     * Gets the value of the acObjectType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcObjectType() {
        return acObjectType;
    }

    /**
     * Sets the value of the acObjectType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcObjectType(String value) {
        this.acObjectType = value;
    }

    /**
     * Gets the value of the acAssignment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcAssignment() {
        return acAssignment;
    }

    /**
     * Sets the value of the acAssignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcAssignment(String value) {
        this.acAssignment = value;
    }

}
