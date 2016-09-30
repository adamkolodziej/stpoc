
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrmtIsxControlChangeApi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrmtIsxControlChangeApi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SaveOrder" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="ReleaseOrder" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="CommitAfterSave" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="NoInit" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="NoBdocSend" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrmtIsxControlChangeApi", propOrder = {
    "saveOrder",
    "releaseOrder",
    "commitAfterSave",
    "noInit",
    "noBdocSend"
})
public class CrmtIsxControlChangeApi {

    @XmlElement(name = "SaveOrder", required = true)
    protected String saveOrder;
    @XmlElement(name = "ReleaseOrder", required = true)
    protected String releaseOrder;
    @XmlElement(name = "CommitAfterSave", required = true)
    protected String commitAfterSave;
    @XmlElement(name = "NoInit", required = true)
    protected String noInit;
    @XmlElement(name = "NoBdocSend", required = true)
    protected String noBdocSend;

    /**
     * Gets the value of the saveOrder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaveOrder() {
        return saveOrder;
    }

    /**
     * Sets the value of the saveOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaveOrder(String value) {
        this.saveOrder = value;
    }

    /**
     * Gets the value of the releaseOrder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReleaseOrder() {
        return releaseOrder;
    }

    /**
     * Sets the value of the releaseOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReleaseOrder(String value) {
        this.releaseOrder = value;
    }

    /**
     * Gets the value of the commitAfterSave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommitAfterSave() {
        return commitAfterSave;
    }

    /**
     * Sets the value of the commitAfterSave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommitAfterSave(String value) {
        this.commitAfterSave = value;
    }

    /**
     * Gets the value of the noInit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoInit() {
        return noInit;
    }

    /**
     * Sets the value of the noInit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoInit(String value) {
        this.noInit = value;
    }

    /**
     * Gets the value of the noBdocSend property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoBdocSend() {
        return noBdocSend;
    }

    /**
     * Sets the value of the noBdocSend property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoBdocSend(String value) {
        this.noBdocSend = value;
    }

}
