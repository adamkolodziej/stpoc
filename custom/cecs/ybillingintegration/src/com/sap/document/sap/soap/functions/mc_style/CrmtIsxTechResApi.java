
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrmtIsxTechResApi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrmtIsxTechResApi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TrType" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="TrSlot" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
 *         &lt;element name="TrObjId" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="TrObjKey" type="{urn:sap-com:document:sap:rfc:functions}char32"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrmtIsxTechResApi", propOrder = {
    "trType",
    "trSlot",
    "trObjId",
    "trObjKey"
})
public class CrmtIsxTechResApi {

    @XmlElement(name = "TrType", required = true)
    protected String trType;
    @XmlElement(name = "TrSlot", required = true)
    protected String trSlot;
    @XmlElement(name = "TrObjId", required = true)
    protected String trObjId;
    @XmlElement(name = "TrObjKey", required = true)
    protected String trObjKey;

    /**
     * Gets the value of the trType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrType() {
        return trType;
    }

    /**
     * Sets the value of the trType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrType(String value) {
        this.trType = value;
    }

    /**
     * Gets the value of the trSlot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrSlot() {
        return trSlot;
    }

    /**
     * Sets the value of the trSlot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrSlot(String value) {
        this.trSlot = value;
    }

    /**
     * Gets the value of the trObjId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrObjId() {
        return trObjId;
    }

    /**
     * Sets the value of the trObjId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrObjId(String value) {
        this.trObjId = value;
    }

    /**
     * Gets the value of the trObjKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrObjKey() {
        return trObjKey;
    }

    /**
     * Sets the value of the trObjKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrObjKey(String value) {
        this.trObjKey = value;
    }

}
