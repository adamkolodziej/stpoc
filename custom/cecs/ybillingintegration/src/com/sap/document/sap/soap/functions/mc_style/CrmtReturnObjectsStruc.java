
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrmtReturnObjectsStruc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrmtReturnObjectsStruc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Guid" type="{urn:sap-com:document:sap:rfc:functions}byte16"/>
 *         &lt;element name="ObjectId" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ProviderContract" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrmtReturnObjectsStruc", propOrder = {
    "guid",
    "objectId",
    "providerContract"
})
public class CrmtReturnObjectsStruc {

    @XmlElement(name = "Guid", required = true)
    protected byte[] guid;
    @XmlElement(name = "ObjectId", required = true)
    protected String objectId;
    @XmlElement(name = "ProviderContract", required = true)
    protected String providerContract;

    /**
     * Gets the value of the guid property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getGuid() {
        return guid;
    }

    /**
     * Sets the value of the guid property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setGuid(byte[] value) {
        this.guid = value;
    }

    /**
     * Gets the value of the objectId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * Sets the value of the objectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectId(String value) {
        this.objectId = value;
    }

    /**
     * Gets the value of the providerContract property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderContract() {
        return providerContract;
    }

    /**
     * Sets the value of the providerContract property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderContract(String value) {
        this.providerContract = value;
    }

}
