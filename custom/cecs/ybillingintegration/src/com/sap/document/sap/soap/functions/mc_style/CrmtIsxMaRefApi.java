
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrmtIsxMaRefApi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrmtIsxMaRefApi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MaHeadRef" type="{urn:sap-com:document:sap:rfc:functions}byte16"/>
 *         &lt;element name="MaItemRef" type="{urn:sap-com:document:sap:rfc:functions}byte16"/>
 *         &lt;element name="MaRootRef" type="{urn:sap-com:document:sap:rfc:functions}byte16"/>
 *         &lt;element name="MaShareRef" type="{urn:sap-com:document:sap:rfc:functions}byte16"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrmtIsxMaRefApi", propOrder = {
    "maHeadRef",
    "maItemRef",
    "maRootRef",
    "maShareRef"
})
public class CrmtIsxMaRefApi {

    @XmlElement(name = "MaHeadRef", required = true)
    protected byte[] maHeadRef;
    @XmlElement(name = "MaItemRef", required = true)
    protected byte[] maItemRef;
    @XmlElement(name = "MaRootRef", required = true)
    protected byte[] maRootRef;
    @XmlElement(name = "MaShareRef", required = true)
    protected byte[] maShareRef;

    /**
     * Gets the value of the maHeadRef property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getMaHeadRef() {
        return maHeadRef;
    }

    /**
     * Sets the value of the maHeadRef property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setMaHeadRef(byte[] value) {
        this.maHeadRef = value;
    }

    /**
     * Gets the value of the maItemRef property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getMaItemRef() {
        return maItemRef;
    }

    /**
     * Sets the value of the maItemRef property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setMaItemRef(byte[] value) {
        this.maItemRef = value;
    }

    /**
     * Gets the value of the maRootRef property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getMaRootRef() {
        return maRootRef;
    }

    /**
     * Sets the value of the maRootRef property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setMaRootRef(byte[] value) {
        this.maRootRef = value;
    }

    /**
     * Gets the value of the maShareRef property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getMaShareRef() {
        return maShareRef;
    }

    /**
     * Sets the value of the maShareRef property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setMaShareRef(byte[] value) {
        this.maShareRef = value;
    }

}
