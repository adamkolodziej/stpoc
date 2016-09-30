
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapiadssf complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapiadssf">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StdNo" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Dummy" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="StdRecip" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="HomeFlag" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Consnumber" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
 *         &lt;element name="Rfcdest" type="{urn:sap-com:document:sap:rfc:functions}char32"/>
 *         &lt;element name="Ssfidshort" type="{urn:sap-com:document:sap:rfc:functions}char132"/>
 *         &lt;element name="SsfNs" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="SsfProf" type="{urn:sap-com:document:sap:rfc:functions}char132"/>
 *         &lt;element name="Ssfidpart1" type="{urn:sap-com:document:sap:rfc:functions}char250"/>
 *         &lt;element name="Ssfidpart2" type="{urn:sap-com:document:sap:rfc:functions}char250"/>
 *         &lt;element name="Ssfidpart3" type="{urn:sap-com:document:sap:rfc:functions}char250"/>
 *         &lt;element name="Ssfidpart4" type="{urn:sap-com:document:sap:rfc:functions}char250"/>
 *         &lt;element name="Ssfidpart5" type="{urn:sap-com:document:sap:rfc:functions}char250"/>
 *         &lt;element name="Ssfidpart6" type="{urn:sap-com:document:sap:rfc:functions}char250"/>
 *         &lt;element name="Ssfidpart7" type="{urn:sap-com:document:sap:rfc:functions}char250"/>
 *         &lt;element name="Ssfidpart8" type="{urn:sap-com:document:sap:rfc:functions}char250"/>
 *         &lt;element name="Ssfidpart9" type="{urn:sap-com:document:sap:rfc:functions}char48"/>
 *         &lt;element name="Errorflag" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="FlgNouse" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="ValidFrom" type="{urn:sap-com:document:sap:rfc:functions}char14"/>
 *         &lt;element name="ValidTo" type="{urn:sap-com:document:sap:rfc:functions}char14"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapiadssf", propOrder = {
    "stdNo",
    "dummy",
    "stdRecip",
    "homeFlag",
    "consnumber",
    "rfcdest",
    "ssfidshort",
    "ssfNs",
    "ssfProf",
    "ssfidpart1",
    "ssfidpart2",
    "ssfidpart3",
    "ssfidpart4",
    "ssfidpart5",
    "ssfidpart6",
    "ssfidpart7",
    "ssfidpart8",
    "ssfidpart9",
    "errorflag",
    "flgNouse",
    "validFrom",
    "validTo"
})
public class Bapiadssf {

    @XmlElement(name = "StdNo", required = true)
    protected String stdNo;
    @XmlElement(name = "Dummy", required = true)
    protected String dummy;
    @XmlElement(name = "StdRecip", required = true)
    protected String stdRecip;
    @XmlElement(name = "HomeFlag", required = true)
    protected String homeFlag;
    @XmlElement(name = "Consnumber", required = true)
    protected String consnumber;
    @XmlElement(name = "Rfcdest", required = true)
    protected String rfcdest;
    @XmlElement(name = "Ssfidshort", required = true)
    protected String ssfidshort;
    @XmlElement(name = "SsfNs", required = true)
    protected String ssfNs;
    @XmlElement(name = "SsfProf", required = true)
    protected String ssfProf;
    @XmlElement(name = "Ssfidpart1", required = true)
    protected String ssfidpart1;
    @XmlElement(name = "Ssfidpart2", required = true)
    protected String ssfidpart2;
    @XmlElement(name = "Ssfidpart3", required = true)
    protected String ssfidpart3;
    @XmlElement(name = "Ssfidpart4", required = true)
    protected String ssfidpart4;
    @XmlElement(name = "Ssfidpart5", required = true)
    protected String ssfidpart5;
    @XmlElement(name = "Ssfidpart6", required = true)
    protected String ssfidpart6;
    @XmlElement(name = "Ssfidpart7", required = true)
    protected String ssfidpart7;
    @XmlElement(name = "Ssfidpart8", required = true)
    protected String ssfidpart8;
    @XmlElement(name = "Ssfidpart9", required = true)
    protected String ssfidpart9;
    @XmlElement(name = "Errorflag", required = true)
    protected String errorflag;
    @XmlElement(name = "FlgNouse", required = true)
    protected String flgNouse;
    @XmlElement(name = "ValidFrom", required = true)
    protected String validFrom;
    @XmlElement(name = "ValidTo", required = true)
    protected String validTo;

    /**
     * Gets the value of the stdNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStdNo() {
        return stdNo;
    }

    /**
     * Sets the value of the stdNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStdNo(String value) {
        this.stdNo = value;
    }

    /**
     * Gets the value of the dummy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDummy() {
        return dummy;
    }

    /**
     * Sets the value of the dummy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDummy(String value) {
        this.dummy = value;
    }

    /**
     * Gets the value of the stdRecip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStdRecip() {
        return stdRecip;
    }

    /**
     * Sets the value of the stdRecip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStdRecip(String value) {
        this.stdRecip = value;
    }

    /**
     * Gets the value of the homeFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeFlag() {
        return homeFlag;
    }

    /**
     * Sets the value of the homeFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeFlag(String value) {
        this.homeFlag = value;
    }

    /**
     * Gets the value of the consnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsnumber() {
        return consnumber;
    }

    /**
     * Sets the value of the consnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsnumber(String value) {
        this.consnumber = value;
    }

    /**
     * Gets the value of the rfcdest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRfcdest() {
        return rfcdest;
    }

    /**
     * Sets the value of the rfcdest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRfcdest(String value) {
        this.rfcdest = value;
    }

    /**
     * Gets the value of the ssfidshort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsfidshort() {
        return ssfidshort;
    }

    /**
     * Sets the value of the ssfidshort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsfidshort(String value) {
        this.ssfidshort = value;
    }

    /**
     * Gets the value of the ssfNs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsfNs() {
        return ssfNs;
    }

    /**
     * Sets the value of the ssfNs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsfNs(String value) {
        this.ssfNs = value;
    }

    /**
     * Gets the value of the ssfProf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsfProf() {
        return ssfProf;
    }

    /**
     * Sets the value of the ssfProf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsfProf(String value) {
        this.ssfProf = value;
    }

    /**
     * Gets the value of the ssfidpart1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsfidpart1() {
        return ssfidpart1;
    }

    /**
     * Sets the value of the ssfidpart1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsfidpart1(String value) {
        this.ssfidpart1 = value;
    }

    /**
     * Gets the value of the ssfidpart2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsfidpart2() {
        return ssfidpart2;
    }

    /**
     * Sets the value of the ssfidpart2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsfidpart2(String value) {
        this.ssfidpart2 = value;
    }

    /**
     * Gets the value of the ssfidpart3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsfidpart3() {
        return ssfidpart3;
    }

    /**
     * Sets the value of the ssfidpart3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsfidpart3(String value) {
        this.ssfidpart3 = value;
    }

    /**
     * Gets the value of the ssfidpart4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsfidpart4() {
        return ssfidpart4;
    }

    /**
     * Sets the value of the ssfidpart4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsfidpart4(String value) {
        this.ssfidpart4 = value;
    }

    /**
     * Gets the value of the ssfidpart5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsfidpart5() {
        return ssfidpart5;
    }

    /**
     * Sets the value of the ssfidpart5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsfidpart5(String value) {
        this.ssfidpart5 = value;
    }

    /**
     * Gets the value of the ssfidpart6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsfidpart6() {
        return ssfidpart6;
    }

    /**
     * Sets the value of the ssfidpart6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsfidpart6(String value) {
        this.ssfidpart6 = value;
    }

    /**
     * Gets the value of the ssfidpart7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsfidpart7() {
        return ssfidpart7;
    }

    /**
     * Sets the value of the ssfidpart7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsfidpart7(String value) {
        this.ssfidpart7 = value;
    }

    /**
     * Gets the value of the ssfidpart8 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsfidpart8() {
        return ssfidpart8;
    }

    /**
     * Sets the value of the ssfidpart8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsfidpart8(String value) {
        this.ssfidpart8 = value;
    }

    /**
     * Gets the value of the ssfidpart9 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsfidpart9() {
        return ssfidpart9;
    }

    /**
     * Sets the value of the ssfidpart9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsfidpart9(String value) {
        this.ssfidpart9 = value;
    }

    /**
     * Gets the value of the errorflag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorflag() {
        return errorflag;
    }

    /**
     * Sets the value of the errorflag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorflag(String value) {
        this.errorflag = value;
    }

    /**
     * Gets the value of the flgNouse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlgNouse() {
        return flgNouse;
    }

    /**
     * Sets the value of the flgNouse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlgNouse(String value) {
        this.flgNouse = value;
    }

    /**
     * Gets the value of the validFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidFrom() {
        return validFrom;
    }

    /**
     * Sets the value of the validFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidFrom(String value) {
        this.validFrom = value;
    }

    /**
     * Gets the value of the validTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidTo() {
        return validTo;
    }

    /**
     * Sets the value of the validTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidTo(String value) {
        this.validTo = value;
    }

}
