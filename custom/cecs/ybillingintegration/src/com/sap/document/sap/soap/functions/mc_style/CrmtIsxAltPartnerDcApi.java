
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrmtIsxAltPartnerDcApi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrmtIsxAltPartnerDcApi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RefToItemHandle" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxHandleApiT"/>
 *         &lt;element name="PartnerFct" type="{urn:sap-com:document:sap:rfc:functions}char8"/>
 *         &lt;element name="PartnerNo" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="AddrNr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrmtIsxAltPartnerDcApi", propOrder = {
    "refToItemHandle",
    "partnerFct",
    "partnerNo",
    "addrNr"
})
public class CrmtIsxAltPartnerDcApi {

    @XmlElement(name = "RefToItemHandle", required = true)
    protected CrmtIsxHandleApiT refToItemHandle;
    @XmlElement(name = "PartnerFct", required = true)
    protected String partnerFct;
    @XmlElement(name = "PartnerNo", required = true)
    protected String partnerNo;
    @XmlElement(name = "AddrNr", required = true)
    protected String addrNr;

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
     * Gets the value of the partnerFct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerFct() {
        return partnerFct;
    }

    /**
     * Sets the value of the partnerFct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerFct(String value) {
        this.partnerFct = value;
    }

    /**
     * Gets the value of the partnerNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerNo() {
        return partnerNo;
    }

    /**
     * Sets the value of the partnerNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerNo(String value) {
        this.partnerNo = value;
    }

    /**
     * Gets the value of the addrNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrNr() {
        return addrNr;
    }

    /**
     * Sets the value of the addrNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrNr(String value) {
        this.addrNr = value;
    }

}
