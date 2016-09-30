
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
 *         &lt;element name="CsDatagnrlExp" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006130GnrlDataExp" minOccurs="0"/>
 *         &lt;element name="CsDataspecExp" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006130SpecDataExp" minOccurs="0"/>
 *         &lt;element name="IsTemplate" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtBuagTemplStruc" minOccurs="0"/>
 *         &lt;element name="ItTreldata" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006130RelDataT" minOccurs="0"/>
 *         &lt;element name="IvBuagChannel" type="{urn:sap-com:document:sap:rfc:functions}char3" minOccurs="0"/>
 *         &lt;element name="IvBuagId" type="{urn:sap-com:document:sap:rfc:functions}char12" minOccurs="0"/>
 *         &lt;element name="IvBuagIsCollBill" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/>
 *         &lt;element name="IvBuagPaymtype" type="{urn:sap-com:document:sap:rfc:functions}numeric2" minOccurs="0"/>
 *         &lt;element name="IvBuagUsage" type="{urn:sap-com:document:sap:rfc:functions}numeric2" minOccurs="0"/>
 *         &lt;element name="IvBupaId" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="IvDoubleCheck" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/>
 *         &lt;element name="IvRiskClass" type="{urn:sap-com:document:sap:rfc:functions}char3" minOccurs="0"/>
 *         &lt;element name="IvSave" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/>
 *         &lt;element name="IvTestrun" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/>
 *         &lt;element name="Return" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapiret2T"/>
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
    "csDatagnrlExp",
    "csDataspecExp",
    "isTemplate",
    "itTreldata",
    "ivBuagChannel",
    "ivBuagId",
    "ivBuagIsCollBill",
    "ivBuagPaymtype",
    "ivBuagUsage",
    "ivBupaId",
    "ivDoubleCheck",
    "ivRiskClass",
    "ivSave",
    "ivTestrun",
    "_return"
})
@XmlRootElement(name = "ZcrmBuagCreate")
public class ZcrmBuagCreate {

    @XmlElement(name = "CsDatagnrlExp")
    protected Bapibus1006130GnrlDataExp csDatagnrlExp;
    @XmlElement(name = "CsDataspecExp")
    protected Bapibus1006130SpecDataExp csDataspecExp;
    @XmlElement(name = "IsTemplate")
    protected CrmtBuagTemplStruc isTemplate;
    @XmlElement(name = "ItTreldata")
    protected Bapibus1006130RelDataT itTreldata;
    @XmlElement(name = "IvBuagChannel")
    protected String ivBuagChannel;
    @XmlElement(name = "IvBuagId")
    protected String ivBuagId;
    @XmlElement(name = "IvBuagIsCollBill")
    protected String ivBuagIsCollBill;
    @XmlElement(name = "IvBuagPaymtype")
    protected String ivBuagPaymtype;
    @XmlElement(name = "IvBuagUsage")
    protected String ivBuagUsage;
    @XmlElement(name = "IvBupaId", required = true)
    protected String ivBupaId;
    @XmlElement(name = "IvDoubleCheck")
    protected String ivDoubleCheck;
    @XmlElement(name = "IvRiskClass")
    protected String ivRiskClass;
    @XmlElement(name = "IvSave")
    protected String ivSave;
    @XmlElement(name = "IvTestrun")
    protected String ivTestrun;
    @XmlElement(name = "Return", required = true)
    protected Bapiret2T _return;

    /**
     * Gets the value of the csDatagnrlExp property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006130GnrlDataExp }
     *     
     */
    public Bapibus1006130GnrlDataExp getCsDatagnrlExp() {
        return csDatagnrlExp;
    }

    /**
     * Sets the value of the csDatagnrlExp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006130GnrlDataExp }
     *     
     */
    public void setCsDatagnrlExp(Bapibus1006130GnrlDataExp value) {
        this.csDatagnrlExp = value;
    }

    /**
     * Gets the value of the csDataspecExp property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006130SpecDataExp }
     *     
     */
    public Bapibus1006130SpecDataExp getCsDataspecExp() {
        return csDataspecExp;
    }

    /**
     * Sets the value of the csDataspecExp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006130SpecDataExp }
     *     
     */
    public void setCsDataspecExp(Bapibus1006130SpecDataExp value) {
        this.csDataspecExp = value;
    }

    /**
     * Gets the value of the isTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtBuagTemplStruc }
     *     
     */
    public CrmtBuagTemplStruc getIsTemplate() {
        return isTemplate;
    }

    /**
     * Sets the value of the isTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtBuagTemplStruc }
     *     
     */
    public void setIsTemplate(CrmtBuagTemplStruc value) {
        this.isTemplate = value;
    }

    /**
     * Gets the value of the itTreldata property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006130RelDataT }
     *     
     */
    public Bapibus1006130RelDataT getItTreldata() {
        return itTreldata;
    }

    /**
     * Sets the value of the itTreldata property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006130RelDataT }
     *     
     */
    public void setItTreldata(Bapibus1006130RelDataT value) {
        this.itTreldata = value;
    }

    /**
     * Gets the value of the ivBuagChannel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIvBuagChannel() {
        return ivBuagChannel;
    }

    /**
     * Sets the value of the ivBuagChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIvBuagChannel(String value) {
        this.ivBuagChannel = value;
    }

    /**
     * Gets the value of the ivBuagId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIvBuagId() {
        return ivBuagId;
    }

    /**
     * Sets the value of the ivBuagId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIvBuagId(String value) {
        this.ivBuagId = value;
    }

    /**
     * Gets the value of the ivBuagIsCollBill property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIvBuagIsCollBill() {
        return ivBuagIsCollBill;
    }

    /**
     * Sets the value of the ivBuagIsCollBill property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIvBuagIsCollBill(String value) {
        this.ivBuagIsCollBill = value;
    }

    /**
     * Gets the value of the ivBuagPaymtype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIvBuagPaymtype() {
        return ivBuagPaymtype;
    }

    /**
     * Sets the value of the ivBuagPaymtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIvBuagPaymtype(String value) {
        this.ivBuagPaymtype = value;
    }

    /**
     * Gets the value of the ivBuagUsage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIvBuagUsage() {
        return ivBuagUsage;
    }

    /**
     * Sets the value of the ivBuagUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIvBuagUsage(String value) {
        this.ivBuagUsage = value;
    }

    /**
     * Gets the value of the ivBupaId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIvBupaId() {
        return ivBupaId;
    }

    /**
     * Sets the value of the ivBupaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIvBupaId(String value) {
        this.ivBupaId = value;
    }

    /**
     * Gets the value of the ivDoubleCheck property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIvDoubleCheck() {
        return ivDoubleCheck;
    }

    /**
     * Sets the value of the ivDoubleCheck property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIvDoubleCheck(String value) {
        this.ivDoubleCheck = value;
    }

    /**
     * Gets the value of the ivRiskClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIvRiskClass() {
        return ivRiskClass;
    }

    /**
     * Sets the value of the ivRiskClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIvRiskClass(String value) {
        this.ivRiskClass = value;
    }

    /**
     * Gets the value of the ivSave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIvSave() {
        return ivSave;
    }

    /**
     * Sets the value of the ivSave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIvSave(String value) {
        this.ivSave = value;
    }

    /**
     * Gets the value of the ivTestrun property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIvTestrun() {
        return ivTestrun;
    }

    /**
     * Sets the value of the ivTestrun property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIvTestrun(String value) {
        this.ivTestrun = value;
    }

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link Bapiret2T }
     *     
     */
    public Bapiret2T getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapiret2T }
     *     
     */
    public void setReturn(Bapiret2T value) {
        this._return = value;
    }

}
