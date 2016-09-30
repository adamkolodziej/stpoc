
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
 *         &lt;element name="IsChangeData" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxContractChangeRfc"/>
 *         &lt;element name="IsControl" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxControlChangeApi"/>
 *         &lt;element name="IvItemNo" type="{urn:sap-com:document:sap:rfc:functions}numeric10"/>
 *         &lt;element name="IvObjectId" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
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
    "isChangeData",
    "isControl",
    "ivItemNo",
    "ivObjectId"
})
@XmlRootElement(name = "ZcrmIsxBtxApiContChange")
public class ZcrmIsxBtxApiContChange {

    @XmlElement(name = "IsChangeData", required = true)
    protected CrmtIsxContractChangeRfc isChangeData;
    @XmlElement(name = "IsControl", required = true)
    protected CrmtIsxControlChangeApi isControl;
    @XmlElement(name = "IvItemNo", required = true)
    protected String ivItemNo;
    @XmlElement(name = "IvObjectId", required = true)
    protected String ivObjectId;

    /**
     * Gets the value of the isChangeData property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxContractChangeRfc }
     *     
     */
    public CrmtIsxContractChangeRfc getIsChangeData() {
        return isChangeData;
    }

    /**
     * Sets the value of the isChangeData property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxContractChangeRfc }
     *     
     */
    public void setIsChangeData(CrmtIsxContractChangeRfc value) {
        this.isChangeData = value;
    }

    /**
     * Gets the value of the isControl property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxControlChangeApi }
     *     
     */
    public CrmtIsxControlChangeApi getIsControl() {
        return isControl;
    }

    /**
     * Sets the value of the isControl property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxControlChangeApi }
     *     
     */
    public void setIsControl(CrmtIsxControlChangeApi value) {
        this.isControl = value;
    }

    /**
     * Gets the value of the ivItemNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIvItemNo() {
        return ivItemNo;
    }

    /**
     * Sets the value of the ivItemNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIvItemNo(String value) {
        this.ivItemNo = value;
    }

    /**
     * Gets the value of the ivObjectId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIvObjectId() {
        return ivObjectId;
    }

    /**
     * Sets the value of the ivObjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIvObjectId(String value) {
        this.ivObjectId = value;
    }

}
