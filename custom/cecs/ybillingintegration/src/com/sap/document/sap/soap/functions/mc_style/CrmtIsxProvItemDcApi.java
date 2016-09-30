
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrmtIsxProvItemDcApi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrmtIsxProvItemDcApi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItemHandle" type="{urn:sap-com:document:sap:rfc:functions}numeric10"/>
 *         &lt;element name="ParentItemHandle" type="{urn:sap-com:document:sap:rfc:functions}numeric10"/>
 *         &lt;element name="ContractNr" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="ProductId" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Timeframe" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxDatesApi"/>
 *         &lt;element name="BuagId" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="ItemConfig" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxItemConfigApi"/>
 *         &lt;element name="MaRef" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxMaRefApi"/>
 *         &lt;element name="PoolAgrRef" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="ContItmId" type="{urn:sap-com:document:sap:rfc:functions}byte16"/>
 *         &lt;element name="PrePostpaid" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="AdditionalSubItem" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="RatingArea" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="BillingCycle" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="RevaccRefid" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="RevaccReftype" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrmtIsxProvItemDcApi", propOrder = {
    "itemHandle",
    "parentItemHandle",
    "contractNr",
    "productId",
    "timeframe",
    "buagId",
    "itemConfig",
    "maRef",
    "poolAgrRef",
    "contItmId",
    "prePostpaid",
    "additionalSubItem",
    "ratingArea",
    "billingCycle",
    "revaccRefid",
    "revaccReftype"
})
public class CrmtIsxProvItemDcApi {

    @XmlElement(name = "ItemHandle", required = true)
    protected String itemHandle;
    @XmlElement(name = "ParentItemHandle", required = true)
    protected String parentItemHandle;
    @XmlElement(name = "ContractNr", required = true)
    protected String contractNr;
    @XmlElement(name = "ProductId", required = true)
    protected String productId;
    @XmlElement(name = "Timeframe", required = true)
    protected CrmtIsxDatesApi timeframe;
    @XmlElement(name = "BuagId", required = true)
    protected String buagId;
    @XmlElement(name = "ItemConfig", required = true)
    protected CrmtIsxItemConfigApi itemConfig;
    @XmlElement(name = "MaRef", required = true)
    protected CrmtIsxMaRefApi maRef;
    @XmlElement(name = "PoolAgrRef", required = true)
    protected String poolAgrRef;
    @XmlElement(name = "ContItmId", required = true)
    protected byte[] contItmId;
    @XmlElement(name = "PrePostpaid", required = true)
    protected String prePostpaid;
    @XmlElement(name = "AdditionalSubItem", required = true)
    protected String additionalSubItem;
    @XmlElement(name = "RatingArea", required = true)
    protected String ratingArea;
    @XmlElement(name = "BillingCycle", required = true)
    protected String billingCycle;
    @XmlElement(name = "RevaccRefid", required = true)
    protected String revaccRefid;
    @XmlElement(name = "RevaccReftype", required = true)
    protected String revaccReftype;

    /**
     * Gets the value of the itemHandle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemHandle() {
        return itemHandle;
    }

    /**
     * Sets the value of the itemHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemHandle(String value) {
        this.itemHandle = value;
    }

    /**
     * Gets the value of the parentItemHandle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentItemHandle() {
        return parentItemHandle;
    }

    /**
     * Sets the value of the parentItemHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentItemHandle(String value) {
        this.parentItemHandle = value;
    }

    /**
     * Gets the value of the contractNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractNr() {
        return contractNr;
    }

    /**
     * Sets the value of the contractNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractNr(String value) {
        this.contractNr = value;
    }

    /**
     * Gets the value of the productId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets the value of the productId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductId(String value) {
        this.productId = value;
    }

    /**
     * Gets the value of the timeframe property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxDatesApi }
     *     
     */
    public CrmtIsxDatesApi getTimeframe() {
        return timeframe;
    }

    /**
     * Sets the value of the timeframe property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxDatesApi }
     *     
     */
    public void setTimeframe(CrmtIsxDatesApi value) {
        this.timeframe = value;
    }

    /**
     * Gets the value of the buagId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuagId() {
        return buagId;
    }

    /**
     * Sets the value of the buagId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuagId(String value) {
        this.buagId = value;
    }

    /**
     * Gets the value of the itemConfig property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxItemConfigApi }
     *     
     */
    public CrmtIsxItemConfigApi getItemConfig() {
        return itemConfig;
    }

    /**
     * Sets the value of the itemConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxItemConfigApi }
     *     
     */
    public void setItemConfig(CrmtIsxItemConfigApi value) {
        this.itemConfig = value;
    }

    /**
     * Gets the value of the maRef property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxMaRefApi }
     *     
     */
    public CrmtIsxMaRefApi getMaRef() {
        return maRef;
    }

    /**
     * Sets the value of the maRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxMaRefApi }
     *     
     */
    public void setMaRef(CrmtIsxMaRefApi value) {
        this.maRef = value;
    }

    /**
     * Gets the value of the poolAgrRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoolAgrRef() {
        return poolAgrRef;
    }

    /**
     * Sets the value of the poolAgrRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoolAgrRef(String value) {
        this.poolAgrRef = value;
    }

    /**
     * Gets the value of the contItmId property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getContItmId() {
        return contItmId;
    }

    /**
     * Sets the value of the contItmId property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setContItmId(byte[] value) {
        this.contItmId = value;
    }

    /**
     * Gets the value of the prePostpaid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrePostpaid() {
        return prePostpaid;
    }

    /**
     * Sets the value of the prePostpaid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrePostpaid(String value) {
        this.prePostpaid = value;
    }

    /**
     * Gets the value of the additionalSubItem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalSubItem() {
        return additionalSubItem;
    }

    /**
     * Sets the value of the additionalSubItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalSubItem(String value) {
        this.additionalSubItem = value;
    }

    /**
     * Gets the value of the ratingArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatingArea() {
        return ratingArea;
    }

    /**
     * Sets the value of the ratingArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatingArea(String value) {
        this.ratingArea = value;
    }

    /**
     * Gets the value of the billingCycle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingCycle() {
        return billingCycle;
    }

    /**
     * Sets the value of the billingCycle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingCycle(String value) {
        this.billingCycle = value;
    }

    /**
     * Gets the value of the revaccRefid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevaccRefid() {
        return revaccRefid;
    }

    /**
     * Sets the value of the revaccRefid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevaccRefid(String value) {
        this.revaccRefid = value;
    }

    /**
     * Gets the value of the revaccReftype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevaccReftype() {
        return revaccReftype;
    }

    /**
     * Sets the value of the revaccReftype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevaccReftype(String value) {
        this.revaccReftype = value;
    }

}
