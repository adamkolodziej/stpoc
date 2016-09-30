
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrmtIsxControlApi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrmtIsxControlApi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExplodeSubitems" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="SubmitOrder" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="SaveOrder" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="CommitAfterSave" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="SaveWithErrors" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="NoBdocSend" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="DeactivateInterfaceChecks" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="NoTerminationWhenError" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="CheckConfig" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="NoInit" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="DetermineRatingArea" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="UseTemplate" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrmtIsxControlApi", propOrder = {
    "explodeSubitems",
    "submitOrder",
    "saveOrder",
    "commitAfterSave",
    "saveWithErrors",
    "noBdocSend",
    "deactivateInterfaceChecks",
    "noTerminationWhenError",
    "checkConfig",
    "noInit",
    "determineRatingArea",
    "useTemplate"
})
public class CrmtIsxControlApi {

    @XmlElement(name = "ExplodeSubitems", required = true)
    protected String explodeSubitems;
    @XmlElement(name = "SubmitOrder", required = true)
    protected String submitOrder;
    @XmlElement(name = "SaveOrder", required = true)
    protected String saveOrder;
    @XmlElement(name = "CommitAfterSave", required = true)
    protected String commitAfterSave;
    @XmlElement(name = "SaveWithErrors", required = true)
    protected String saveWithErrors;
    @XmlElement(name = "NoBdocSend", required = true)
    protected String noBdocSend;
    @XmlElement(name = "DeactivateInterfaceChecks", required = true)
    protected String deactivateInterfaceChecks;
    @XmlElement(name = "NoTerminationWhenError", required = true)
    protected String noTerminationWhenError;
    @XmlElement(name = "CheckConfig", required = true)
    protected String checkConfig;
    @XmlElement(name = "NoInit", required = true)
    protected String noInit;
    @XmlElement(name = "DetermineRatingArea", required = true)
    protected String determineRatingArea;
    @XmlElement(name = "UseTemplate", required = true)
    protected String useTemplate;

    /**
     * Gets the value of the explodeSubitems property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExplodeSubitems() {
        return explodeSubitems;
    }

    /**
     * Sets the value of the explodeSubitems property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExplodeSubitems(String value) {
        this.explodeSubitems = value;
    }

    /**
     * Gets the value of the submitOrder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubmitOrder() {
        return submitOrder;
    }

    /**
     * Sets the value of the submitOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubmitOrder(String value) {
        this.submitOrder = value;
    }

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
     * Gets the value of the saveWithErrors property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaveWithErrors() {
        return saveWithErrors;
    }

    /**
     * Sets the value of the saveWithErrors property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaveWithErrors(String value) {
        this.saveWithErrors = value;
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

    /**
     * Gets the value of the deactivateInterfaceChecks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeactivateInterfaceChecks() {
        return deactivateInterfaceChecks;
    }

    /**
     * Sets the value of the deactivateInterfaceChecks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeactivateInterfaceChecks(String value) {
        this.deactivateInterfaceChecks = value;
    }

    /**
     * Gets the value of the noTerminationWhenError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoTerminationWhenError() {
        return noTerminationWhenError;
    }

    /**
     * Sets the value of the noTerminationWhenError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoTerminationWhenError(String value) {
        this.noTerminationWhenError = value;
    }

    /**
     * Gets the value of the checkConfig property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckConfig() {
        return checkConfig;
    }

    /**
     * Sets the value of the checkConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckConfig(String value) {
        this.checkConfig = value;
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
     * Gets the value of the determineRatingArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetermineRatingArea() {
        return determineRatingArea;
    }

    /**
     * Sets the value of the determineRatingArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetermineRatingArea(String value) {
        this.determineRatingArea = value;
    }

    /**
     * Gets the value of the useTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseTemplate() {
        return useTemplate;
    }

    /**
     * Sets the value of the useTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseTemplate(String value) {
        this.useTemplate = value;
    }

}
