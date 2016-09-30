
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrmtIsxProviderCreateApi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrmtIsxProviderCreateApi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GenData" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxGenDataDcApi"/>
 *         &lt;element name="Items" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxProvItemDcApiT"/>
 *         &lt;element name="AltPartners" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxAltPartnerDcApiT"/>
 *         &lt;element name="AcAssign" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxAcAssignDcApiT"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrmtIsxProviderCreateApi", propOrder = {
    "genData",
    "items",
    "altPartners",
    "acAssign"
})
public class CrmtIsxProviderCreateApi {

    @XmlElement(name = "GenData", required = true)
    protected CrmtIsxGenDataDcApi genData;
    @XmlElement(name = "Items", required = true)
    protected CrmtIsxProvItemDcApiT items;
    @XmlElement(name = "AltPartners", required = true)
    protected CrmtIsxAltPartnerDcApiT altPartners;
    @XmlElement(name = "AcAssign", required = true)
    protected CrmtIsxAcAssignDcApiT acAssign;

    /**
     * Gets the value of the genData property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxGenDataDcApi }
     *     
     */
    public CrmtIsxGenDataDcApi getGenData() {
        return genData;
    }

    /**
     * Sets the value of the genData property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxGenDataDcApi }
     *     
     */
    public void setGenData(CrmtIsxGenDataDcApi value) {
        this.genData = value;
    }

    /**
     * Gets the value of the items property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxProvItemDcApiT }
     *     
     */
    public CrmtIsxProvItemDcApiT getItems() {
        return items;
    }

    /**
     * Sets the value of the items property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxProvItemDcApiT }
     *     
     */
    public void setItems(CrmtIsxProvItemDcApiT value) {
        this.items = value;
    }

    /**
     * Gets the value of the altPartners property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxAltPartnerDcApiT }
     *     
     */
    public CrmtIsxAltPartnerDcApiT getAltPartners() {
        return altPartners;
    }

    /**
     * Sets the value of the altPartners property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxAltPartnerDcApiT }
     *     
     */
    public void setAltPartners(CrmtIsxAltPartnerDcApiT value) {
        this.altPartners = value;
    }

    /**
     * Gets the value of the acAssign property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxAcAssignDcApiT }
     *     
     */
    public CrmtIsxAcAssignDcApiT getAcAssign() {
        return acAssign;
    }

    /**
     * Sets the value of the acAssign property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxAcAssignDcApiT }
     *     
     */
    public void setAcAssign(CrmtIsxAcAssignDcApiT value) {
        this.acAssign = value;
    }

}
