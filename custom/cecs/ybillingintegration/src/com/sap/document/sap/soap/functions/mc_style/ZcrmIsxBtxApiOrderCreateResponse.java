
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
 *         &lt;element name="EsCreatedOrder" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtReturnObjectsStruc"/>
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
    "esCreatedOrder",
    "etReturn"
})
@XmlRootElement(name = "ZcrmIsxBtxApiOrderCreateResponse")
public class ZcrmIsxBtxApiOrderCreateResponse {

    @XmlElement(name = "EsCreatedOrder", required = true)
    protected CrmtReturnObjectsStruc esCreatedOrder;
    @XmlElement(name = "EtReturn", required = true)
    protected Bapirettab etReturn;

    /**
     * Gets the value of the esCreatedOrder property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtReturnObjectsStruc }
     *     
     */
    public CrmtReturnObjectsStruc getEsCreatedOrder() {
        return esCreatedOrder;
    }

    /**
     * Sets the value of the esCreatedOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtReturnObjectsStruc }
     *     
     */
    public void setEsCreatedOrder(CrmtReturnObjectsStruc value) {
        this.esCreatedOrder = value;
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
