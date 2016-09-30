
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapiadx400 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapiadx400">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StdNo" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Country" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Countryiso" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="AdmDom" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="PrivDom" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="Organizatn" type="{urn:sap-com:document:sap:rfc:functions}char64"/>
 *         &lt;element name="OrgUnit1" type="{urn:sap-com:document:sap:rfc:functions}char32"/>
 *         &lt;element name="OrgUnit2" type="{urn:sap-com:document:sap:rfc:functions}char32"/>
 *         &lt;element name="OrgUnit3" type="{urn:sap-com:document:sap:rfc:functions}char32"/>
 *         &lt;element name="OrgUnit4" type="{urn:sap-com:document:sap:rfc:functions}char32"/>
 *         &lt;element name="Lastname" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Firstname" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="Initials" type="{urn:sap-com:document:sap:rfc:functions}char5"/>
 *         &lt;element name="Generation" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="X121Addr" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="TermId" type="{urn:sap-com:document:sap:rfc:functions}char24"/>
 *         &lt;element name="TermType" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Uanid" type="{urn:sap-com:document:sap:rfc:functions}char32"/>
 *         &lt;element name="DdaType1" type="{urn:sap-com:document:sap:rfc:functions}char8"/>
 *         &lt;element name="DdaValue1" type="{urn:sap-com:document:sap:rfc:functions}char128"/>
 *         &lt;element name="DdaType2" type="{urn:sap-com:document:sap:rfc:functions}char8"/>
 *         &lt;element name="DdaValue2" type="{urn:sap-com:document:sap:rfc:functions}char128"/>
 *         &lt;element name="DdaType3" type="{urn:sap-com:document:sap:rfc:functions}char8"/>
 *         &lt;element name="DdaValue3" type="{urn:sap-com:document:sap:rfc:functions}char128"/>
 *         &lt;element name="DdaType4" type="{urn:sap-com:document:sap:rfc:functions}char8"/>
 *         &lt;element name="DdaValue4" type="{urn:sap-com:document:sap:rfc:functions}char128"/>
 *         &lt;element name="StdRecip" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="R3User" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="TxtEncode" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Tnef" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="HomeFlag" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Consnumber" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
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
@XmlType(name = "Bapiadx400", propOrder = {
    "stdNo",
    "country",
    "countryiso",
    "admDom",
    "privDom",
    "organizatn",
    "orgUnit1",
    "orgUnit2",
    "orgUnit3",
    "orgUnit4",
    "lastname",
    "firstname",
    "initials",
    "generation",
    "x121Addr",
    "termId",
    "termType",
    "uanid",
    "ddaType1",
    "ddaValue1",
    "ddaType2",
    "ddaValue2",
    "ddaType3",
    "ddaValue3",
    "ddaType4",
    "ddaValue4",
    "stdRecip",
    "r3User",
    "txtEncode",
    "tnef",
    "homeFlag",
    "consnumber",
    "errorflag",
    "flgNouse",
    "validFrom",
    "validTo"
})
public class Bapiadx400 {

    @XmlElement(name = "StdNo", required = true)
    protected String stdNo;
    @XmlElement(name = "Country", required = true)
    protected String country;
    @XmlElement(name = "Countryiso", required = true)
    protected String countryiso;
    @XmlElement(name = "AdmDom", required = true)
    protected String admDom;
    @XmlElement(name = "PrivDom", required = true)
    protected String privDom;
    @XmlElement(name = "Organizatn", required = true)
    protected String organizatn;
    @XmlElement(name = "OrgUnit1", required = true)
    protected String orgUnit1;
    @XmlElement(name = "OrgUnit2", required = true)
    protected String orgUnit2;
    @XmlElement(name = "OrgUnit3", required = true)
    protected String orgUnit3;
    @XmlElement(name = "OrgUnit4", required = true)
    protected String orgUnit4;
    @XmlElement(name = "Lastname", required = true)
    protected String lastname;
    @XmlElement(name = "Firstname", required = true)
    protected String firstname;
    @XmlElement(name = "Initials", required = true)
    protected String initials;
    @XmlElement(name = "Generation", required = true)
    protected String generation;
    @XmlElement(name = "X121Addr", required = true)
    protected String x121Addr;
    @XmlElement(name = "TermId", required = true)
    protected String termId;
    @XmlElement(name = "TermType", required = true)
    protected String termType;
    @XmlElement(name = "Uanid", required = true)
    protected String uanid;
    @XmlElement(name = "DdaType1", required = true)
    protected String ddaType1;
    @XmlElement(name = "DdaValue1", required = true)
    protected String ddaValue1;
    @XmlElement(name = "DdaType2", required = true)
    protected String ddaType2;
    @XmlElement(name = "DdaValue2", required = true)
    protected String ddaValue2;
    @XmlElement(name = "DdaType3", required = true)
    protected String ddaType3;
    @XmlElement(name = "DdaValue3", required = true)
    protected String ddaValue3;
    @XmlElement(name = "DdaType4", required = true)
    protected String ddaType4;
    @XmlElement(name = "DdaValue4", required = true)
    protected String ddaValue4;
    @XmlElement(name = "StdRecip", required = true)
    protected String stdRecip;
    @XmlElement(name = "R3User", required = true)
    protected String r3User;
    @XmlElement(name = "TxtEncode", required = true)
    protected String txtEncode;
    @XmlElement(name = "Tnef", required = true)
    protected String tnef;
    @XmlElement(name = "HomeFlag", required = true)
    protected String homeFlag;
    @XmlElement(name = "Consnumber", required = true)
    protected String consnumber;
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
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the countryiso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryiso() {
        return countryiso;
    }

    /**
     * Sets the value of the countryiso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryiso(String value) {
        this.countryiso = value;
    }

    /**
     * Gets the value of the admDom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdmDom() {
        return admDom;
    }

    /**
     * Sets the value of the admDom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdmDom(String value) {
        this.admDom = value;
    }

    /**
     * Gets the value of the privDom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivDom() {
        return privDom;
    }

    /**
     * Sets the value of the privDom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivDom(String value) {
        this.privDom = value;
    }

    /**
     * Gets the value of the organizatn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizatn() {
        return organizatn;
    }

    /**
     * Sets the value of the organizatn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizatn(String value) {
        this.organizatn = value;
    }

    /**
     * Gets the value of the orgUnit1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgUnit1() {
        return orgUnit1;
    }

    /**
     * Sets the value of the orgUnit1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgUnit1(String value) {
        this.orgUnit1 = value;
    }

    /**
     * Gets the value of the orgUnit2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgUnit2() {
        return orgUnit2;
    }

    /**
     * Sets the value of the orgUnit2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgUnit2(String value) {
        this.orgUnit2 = value;
    }

    /**
     * Gets the value of the orgUnit3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgUnit3() {
        return orgUnit3;
    }

    /**
     * Sets the value of the orgUnit3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgUnit3(String value) {
        this.orgUnit3 = value;
    }

    /**
     * Gets the value of the orgUnit4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgUnit4() {
        return orgUnit4;
    }

    /**
     * Sets the value of the orgUnit4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgUnit4(String value) {
        this.orgUnit4 = value;
    }

    /**
     * Gets the value of the lastname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the value of the lastname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastname(String value) {
        this.lastname = value;
    }

    /**
     * Gets the value of the firstname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the value of the firstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstname(String value) {
        this.firstname = value;
    }

    /**
     * Gets the value of the initials property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitials() {
        return initials;
    }

    /**
     * Sets the value of the initials property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitials(String value) {
        this.initials = value;
    }

    /**
     * Gets the value of the generation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneration() {
        return generation;
    }

    /**
     * Sets the value of the generation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneration(String value) {
        this.generation = value;
    }

    /**
     * Gets the value of the x121Addr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX121Addr() {
        return x121Addr;
    }

    /**
     * Sets the value of the x121Addr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX121Addr(String value) {
        this.x121Addr = value;
    }

    /**
     * Gets the value of the termId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermId() {
        return termId;
    }

    /**
     * Sets the value of the termId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermId(String value) {
        this.termId = value;
    }

    /**
     * Gets the value of the termType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermType() {
        return termType;
    }

    /**
     * Sets the value of the termType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermType(String value) {
        this.termType = value;
    }

    /**
     * Gets the value of the uanid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUanid() {
        return uanid;
    }

    /**
     * Sets the value of the uanid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUanid(String value) {
        this.uanid = value;
    }

    /**
     * Gets the value of the ddaType1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDdaType1() {
        return ddaType1;
    }

    /**
     * Sets the value of the ddaType1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDdaType1(String value) {
        this.ddaType1 = value;
    }

    /**
     * Gets the value of the ddaValue1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDdaValue1() {
        return ddaValue1;
    }

    /**
     * Sets the value of the ddaValue1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDdaValue1(String value) {
        this.ddaValue1 = value;
    }

    /**
     * Gets the value of the ddaType2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDdaType2() {
        return ddaType2;
    }

    /**
     * Sets the value of the ddaType2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDdaType2(String value) {
        this.ddaType2 = value;
    }

    /**
     * Gets the value of the ddaValue2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDdaValue2() {
        return ddaValue2;
    }

    /**
     * Sets the value of the ddaValue2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDdaValue2(String value) {
        this.ddaValue2 = value;
    }

    /**
     * Gets the value of the ddaType3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDdaType3() {
        return ddaType3;
    }

    /**
     * Sets the value of the ddaType3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDdaType3(String value) {
        this.ddaType3 = value;
    }

    /**
     * Gets the value of the ddaValue3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDdaValue3() {
        return ddaValue3;
    }

    /**
     * Sets the value of the ddaValue3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDdaValue3(String value) {
        this.ddaValue3 = value;
    }

    /**
     * Gets the value of the ddaType4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDdaType4() {
        return ddaType4;
    }

    /**
     * Sets the value of the ddaType4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDdaType4(String value) {
        this.ddaType4 = value;
    }

    /**
     * Gets the value of the ddaValue4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDdaValue4() {
        return ddaValue4;
    }

    /**
     * Sets the value of the ddaValue4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDdaValue4(String value) {
        this.ddaValue4 = value;
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
     * Gets the value of the r3User property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getR3User() {
        return r3User;
    }

    /**
     * Sets the value of the r3User property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setR3User(String value) {
        this.r3User = value;
    }

    /**
     * Gets the value of the txtEncode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxtEncode() {
        return txtEncode;
    }

    /**
     * Sets the value of the txtEncode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxtEncode(String value) {
        this.txtEncode = value;
    }

    /**
     * Gets the value of the tnef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTnef() {
        return tnef;
    }

    /**
     * Sets the value of the tnef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTnef(String value) {
        this.tnef = value;
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
