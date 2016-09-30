
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
 *         &lt;element name="EtConfig" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZcrmtOrderItemConfigWrkT"/>
 *         &lt;element name="EtOrderHistDetails" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZcrmtOrderHistDetailsT"/>
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
    "etOrderHistDetails",
    "etReturn"
})
@XmlRootElement(name = "ZcrmBupaOrderHistDetailsResponse")
public class ZcrmBupaOrderHistDetailsResponse {

    @XmlElement(name = "EtConfig", required = true)
    protected ZcrmtOrderItemConfigWrkT etConfig;
    @XmlElement(name = "EtOrderHistDetails", required = true)
    protected ZcrmtOrderHistDetailsT etOrderHistDetails;
    @XmlElement(name = "EtReturn", required = true)
    protected Bapirettab etReturn;

    /**
     * Gets the value of the etConfig property.
     * 
     * @return
     *     possible object is
     *     {@link ZcrmtOrderItemConfigWrkT }
     *     
     */
    public ZcrmtOrderItemConfigWrkT getEtConfig() {
        return etConfig;
    }

    /**
     * Sets the value of the etConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZcrmtOrderItemConfigWrkT }
     *     
     */
    public void setEtConfig(ZcrmtOrderItemConfigWrkT value) {
        this.etConfig = value;
    }

    /**
     * Gets the value of the etOrderHistDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ZcrmtOrderHistDetailsT }
     *     
     */
    public ZcrmtOrderHistDetailsT getEtOrderHistDetails() {
        return etOrderHistDetails;
    }

    /**
     * Sets the value of the etOrderHistDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZcrmtOrderHistDetailsT }
     *     
     */
    public void setEtOrderHistDetails(ZcrmtOrderHistDetailsT value) {
        this.etOrderHistDetails = value;
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
