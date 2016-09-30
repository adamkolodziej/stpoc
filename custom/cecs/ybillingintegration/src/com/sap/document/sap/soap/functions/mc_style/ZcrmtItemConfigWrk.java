
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZcrmtItemConfigWrk complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZcrmtItemConfigWrk">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ContractNo" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="ContractVersion" type="{urn:sap-com:document:sap:rfc:functions}numeric10"/>
 *         &lt;element name="Charc" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Value" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="CharcTxt" type="{urn:sap-com:document:sap:rfc:functions}char70"/>
 *         &lt;element name="ValueTxt" type="{urn:sap-com:document:sap:rfc:functions}char70"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZcrmtItemConfigWrk", propOrder = {
    "contractNo",
    "contractVersion",
    "charc",
    "value",
    "charcTxt",
    "valueTxt"
})
public class ZcrmtItemConfigWrk {

    @XmlElement(name = "ContractNo", required = true)
    protected String contractNo;
    @XmlElement(name = "ContractVersion", required = true)
    protected String contractVersion;
    @XmlElement(name = "Charc", required = true)
    protected String charc;
    @XmlElement(name = "Value", required = true)
    protected String value;
    @XmlElement(name = "CharcTxt", required = true)
    protected String charcTxt;
    @XmlElement(name = "ValueTxt", required = true)
    protected String valueTxt;

    /**
     * Gets the value of the contractNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * Sets the value of the contractNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractNo(String value) {
        this.contractNo = value;
    }

    /**
     * Gets the value of the contractVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractVersion() {
        return contractVersion;
    }

    /**
     * Sets the value of the contractVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractVersion(String value) {
        this.contractVersion = value;
    }

    /**
     * Gets the value of the charc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharc() {
        return charc;
    }

    /**
     * Sets the value of the charc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharc(String value) {
        this.charc = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the charcTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharcTxt() {
        return charcTxt;
    }

    /**
     * Sets the value of the charcTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharcTxt(String value) {
        this.charcTxt = value;
    }

    /**
     * Gets the value of the valueTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueTxt() {
        return valueTxt;
    }

    /**
     * Sets the value of the valueTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueTxt(String value) {
        this.valueTxt = value;
    }

}
