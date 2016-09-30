
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
 *         &lt;element name="EtConfig" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZcrmtItemConfigWrkT"/>
 *         &lt;element name="EtContractHistory" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZcrmtContractHistoryT"/>
 *         &lt;element name="EtReturn" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapirettab"/>
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
    "etConfig",
    "etContractHistory",
    "etReturn"
})
@XmlRootElement(name = "ZcrmBupaContractHistoryResponse")
public class ZcrmBupaContractHistoryResponse {

    @XmlElement(name = "EtConfig", required = true)
    protected ZcrmtItemConfigWrkT etConfig;
    @XmlElement(name = "EtContractHistory", required = true)
    protected ZcrmtContractHistoryT etContractHistory;
    @XmlElement(name = "EtReturn", required = true)
    protected Bapirettab etReturn;

    /**
     * Gets the value of the etConfig property.
     * 
     * @return
     *     possible object is
     *     {@link ZcrmtItemConfigWrkT }
     *     
     */
    public ZcrmtItemConfigWrkT getEtConfig() {
        return etConfig;
    }

    /**
     * Sets the value of the etConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZcrmtItemConfigWrkT }
     *     
     */
    public void setEtConfig(ZcrmtItemConfigWrkT value) {
        this.etConfig = value;
    }

    /**
     * Gets the value of the etContractHistory property.
     * 
     * @return
     *     possible object is
     *     {@link ZcrmtContractHistoryT }
     *     
     */
    public ZcrmtContractHistoryT getEtContractHistory() {
        return etContractHistory;
    }

    /**
     * Sets the value of the etContractHistory property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZcrmtContractHistoryT }
     *     
     */
    public void setEtContractHistory(ZcrmtContractHistoryT value) {
        this.etContractHistory = value;
    }

    /**
     * Gets the value of the etReturn property.
     * 
     * @return
     *     possible object is
     *     {@link Bapirettab }
     *     
     */
    public Bapirettab getEtReturn() {
        return etReturn;
    }

    /**
     * Sets the value of the etReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapirettab }
     *     
     */
    public void setEtReturn(Bapirettab value) {
        this.etReturn = value;
    }

}
