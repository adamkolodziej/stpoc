
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
 *         &lt;element name="Buagguid" type="{urn:sap-com:document:sap:rfc:functions}char32" minOccurs="0"/>
 *         &lt;element name="Businessagreement" type="{urn:sap-com:document:sap:rfc:functions}char12" minOccurs="0"/>
 *         &lt;element name="Businesspartner" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/>
 *         &lt;element name="Datagnrl" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006130GnrlData" minOccurs="0"/>
 *         &lt;element name="Datagnrlx" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006130GnrlDataX" minOccurs="0"/>
 *         &lt;element name="Dataspec" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006130SpecData" minOccurs="0"/>
 *         &lt;element name="Dataspecx" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006130SpecDataX" minOccurs="0"/>
 *         &lt;element name="Return" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiret2"/>
 *         &lt;element name="Testrun" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/>
 *         &lt;element name="Tppaccdata" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapibus1006130PpaccData" minOccurs="0"/>
 *         &lt;element name="Tppaccdatax" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapibus1006130PpaccDataX" minOccurs="0"/>
 *         &lt;element name="Treldata" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapibus1006130RelData" minOccurs="0"/>
 *         &lt;element name="Treldatax" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapibus1006130RelDataX" minOccurs="0"/>
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
    "buagguid",
    "businessagreement",
    "businesspartner",
    "datagnrl",
    "datagnrlx",
    "dataspec",
    "dataspecx",
    "_return",
    "testrun",
    "tppaccdata",
    "tppaccdatax",
    "treldata",
    "treldatax"
})
@XmlRootElement(name = "ZcrmBuagChange")
public class ZcrmBuagChange {

    @XmlElement(name = "Buagguid")
    protected String buagguid;
    @XmlElement(name = "Businessagreement")
    protected String businessagreement;
    @XmlElement(name = "Businesspartner")
    protected String businesspartner;
    @XmlElement(name = "Datagnrl")
    protected Bapibus1006130GnrlData datagnrl;
    @XmlElement(name = "Datagnrlx")
    protected Bapibus1006130GnrlDataX datagnrlx;
    @XmlElement(name = "Dataspec")
    protected Bapibus1006130SpecData dataspec;
    @XmlElement(name = "Dataspecx")
    protected Bapibus1006130SpecDataX dataspecx;
    @XmlElement(name = "Return", required = true)
    protected TableOfBapiret2 _return;
    @XmlElement(name = "Testrun")
    protected String testrun;
    @XmlElement(name = "Tppaccdata")
    protected TableOfBapibus1006130PpaccData tppaccdata;
    @XmlElement(name = "Tppaccdatax")
    protected TableOfBapibus1006130PpaccDataX tppaccdatax;
    @XmlElement(name = "Treldata")
    protected TableOfBapibus1006130RelData treldata;
    @XmlElement(name = "Treldatax")
    protected TableOfBapibus1006130RelDataX treldatax;

    /**
     * Gets the value of the buagguid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuagguid() {
        return buagguid;
    }

    /**
     * Sets the value of the buagguid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuagguid(String value) {
        this.buagguid = value;
    }

    /**
     * Gets the value of the businessagreement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessagreement() {
        return businessagreement;
    }

    /**
     * Sets the value of the businessagreement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessagreement(String value) {
        this.businessagreement = value;
    }

    /**
     * Gets the value of the businesspartner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinesspartner() {
        return businesspartner;
    }

    /**
     * Sets the value of the businesspartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinesspartner(String value) {
        this.businesspartner = value;
    }

    /**
     * Gets the value of the datagnrl property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006130GnrlData }
     *     
     */
    public Bapibus1006130GnrlData getDatagnrl() {
        return datagnrl;
    }

    /**
     * Sets the value of the datagnrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006130GnrlData }
     *     
     */
    public void setDatagnrl(Bapibus1006130GnrlData value) {
        this.datagnrl = value;
    }

    /**
     * Gets the value of the datagnrlx property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006130GnrlDataX }
     *     
     */
    public Bapibus1006130GnrlDataX getDatagnrlx() {
        return datagnrlx;
    }

    /**
     * Sets the value of the datagnrlx property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006130GnrlDataX }
     *     
     */
    public void setDatagnrlx(Bapibus1006130GnrlDataX value) {
        this.datagnrlx = value;
    }

    /**
     * Gets the value of the dataspec property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006130SpecData }
     *     
     */
    public Bapibus1006130SpecData getDataspec() {
        return dataspec;
    }

    /**
     * Sets the value of the dataspec property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006130SpecData }
     *     
     */
    public void setDataspec(Bapibus1006130SpecData value) {
        this.dataspec = value;
    }

    /**
     * Gets the value of the dataspecx property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006130SpecDataX }
     *     
     */
    public Bapibus1006130SpecDataX getDataspecx() {
        return dataspecx;
    }

    /**
     * Sets the value of the dataspecx property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006130SpecDataX }
     *     
     */
    public void setDataspecx(Bapibus1006130SpecDataX value) {
        this.dataspecx = value;
    }

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiret2 }
     *     
     */
    public TableOfBapiret2 getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiret2 }
     *     
     */
    public void setReturn(TableOfBapiret2 value) {
        this._return = value;
    }

    /**
     * Gets the value of the testrun property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestrun() {
        return testrun;
    }

    /**
     * Sets the value of the testrun property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestrun(String value) {
        this.testrun = value;
    }

    /**
     * Gets the value of the tppaccdata property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapibus1006130PpaccData }
     *     
     */
    public TableOfBapibus1006130PpaccData getTppaccdata() {
        return tppaccdata;
    }

    /**
     * Sets the value of the tppaccdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapibus1006130PpaccData }
     *     
     */
    public void setTppaccdata(TableOfBapibus1006130PpaccData value) {
        this.tppaccdata = value;
    }

    /**
     * Gets the value of the tppaccdatax property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapibus1006130PpaccDataX }
     *     
     */
    public TableOfBapibus1006130PpaccDataX getTppaccdatax() {
        return tppaccdatax;
    }

    /**
     * Sets the value of the tppaccdatax property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapibus1006130PpaccDataX }
     *     
     */
    public void setTppaccdatax(TableOfBapibus1006130PpaccDataX value) {
        this.tppaccdatax = value;
    }

    /**
     * Gets the value of the treldata property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapibus1006130RelData }
     *     
     */
    public TableOfBapibus1006130RelData getTreldata() {
        return treldata;
    }

    /**
     * Sets the value of the treldata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapibus1006130RelData }
     *     
     */
    public void setTreldata(TableOfBapibus1006130RelData value) {
        this.treldata = value;
    }

    /**
     * Gets the value of the treldatax property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapibus1006130RelDataX }
     *     
     */
    public TableOfBapibus1006130RelDataX getTreldatax() {
        return treldatax;
    }

    /**
     * Sets the value of the treldatax property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapibus1006130RelDataX }
     *     
     */
    public void setTreldatax(TableOfBapibus1006130RelDataX value) {
        this.treldatax = value;
    }

}
