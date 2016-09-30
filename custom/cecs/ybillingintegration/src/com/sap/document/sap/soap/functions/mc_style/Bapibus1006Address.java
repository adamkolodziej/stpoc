
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapibus1006Address complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapibus1006Address">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Standardaddress" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="COName" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="City" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="District" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Regiogroup" type="{urn:sap-com:document:sap:rfc:functions}char8"/>
 *         &lt;element name="PostlCod1" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="PostlCod2" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="PostlCod3" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Pcode1Ext" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Pcode2Ext" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Pcode3Ext" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="PoBox" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="PoWONo" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="PoBoxCit" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="PoBoxReg" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="PoboxCtry" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="PoCtryiso" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Street" type="{urn:sap-com:document:sap:rfc:functions}char60"/>
 *         &lt;element name="StrAbbr" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="HouseNo" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="HouseNo2" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="HouseNo3" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="StrSuppl1" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="StrSuppl2" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="StrSuppl3" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Location" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Building" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="Floor" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="RoomNo" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Country" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Countryiso" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Region" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="TimeZone" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
 *         &lt;element name="Taxjurcode" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="HomeCity" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Transpzone" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Langu" type="{urn:sap-com:document:sap:rfc:functions}lang"/>
 *         &lt;element name="Languiso" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="CommType" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Extaddressnumber" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="DontUseP" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="DontUseS" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="MoveDate" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="MoveAddress" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Validfromdate" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="Validtodate" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="MoveAddrGuid" type="{urn:sap-com:document:sap:rfc:functions}char32"/>
 *         &lt;element name="CityNo" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="DistrctNo" type="{urn:sap-com:document:sap:rfc:functions}char8"/>
 *         &lt;element name="Chckstatus" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="PboxcitNo" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="StreetNo" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="Homecityno" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="PoBoxLobby" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="DeliServType" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="DeliServNumber" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="County" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="CountyNo" type="{urn:sap-com:document:sap:rfc:functions}char8"/>
 *         &lt;element name="Township" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="TownshipNo" type="{urn:sap-com:document:sap:rfc:functions}char8"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapibus1006Address", propOrder = {
    "standardaddress",
    "coName",
    "city",
    "district",
    "regiogroup",
    "postlCod1",
    "postlCod2",
    "postlCod3",
    "pcode1Ext",
    "pcode2Ext",
    "pcode3Ext",
    "poBox",
    "poWONo",
    "poBoxCit",
    "poBoxReg",
    "poboxCtry",
    "poCtryiso",
    "street",
    "strAbbr",
    "houseNo",
    "houseNo2",
    "houseNo3",
    "strSuppl1",
    "strSuppl2",
    "strSuppl3",
    "location",
    "building",
    "floor",
    "roomNo",
    "country",
    "countryiso",
    "region",
    "timeZone",
    "taxjurcode",
    "homeCity",
    "transpzone",
    "langu",
    "languiso",
    "commType",
    "extaddressnumber",
    "dontUseP",
    "dontUseS",
    "moveDate",
    "moveAddress",
    "validfromdate",
    "validtodate",
    "moveAddrGuid",
    "cityNo",
    "distrctNo",
    "chckstatus",
    "pboxcitNo",
    "streetNo",
    "homecityno",
    "poBoxLobby",
    "deliServType",
    "deliServNumber",
    "county",
    "countyNo",
    "township",
    "townshipNo"
})
public class Bapibus1006Address {

    @XmlElement(name = "Standardaddress", required = true)
    protected String standardaddress;
    @XmlElement(name = "COName", required = true)
    protected String coName;
    @XmlElement(name = "City", required = true)
    protected String city;
    @XmlElement(name = "District", required = true)
    protected String district;
    @XmlElement(name = "Regiogroup", required = true)
    protected String regiogroup;
    @XmlElement(name = "PostlCod1", required = true)
    protected String postlCod1;
    @XmlElement(name = "PostlCod2", required = true)
    protected String postlCod2;
    @XmlElement(name = "PostlCod3", required = true)
    protected String postlCod3;
    @XmlElement(name = "Pcode1Ext", required = true)
    protected String pcode1Ext;
    @XmlElement(name = "Pcode2Ext", required = true)
    protected String pcode2Ext;
    @XmlElement(name = "Pcode3Ext", required = true)
    protected String pcode3Ext;
    @XmlElement(name = "PoBox", required = true)
    protected String poBox;
    @XmlElement(name = "PoWONo", required = true)
    protected String poWONo;
    @XmlElement(name = "PoBoxCit", required = true)
    protected String poBoxCit;
    @XmlElement(name = "PoBoxReg", required = true)
    protected String poBoxReg;
    @XmlElement(name = "PoboxCtry", required = true)
    protected String poboxCtry;
    @XmlElement(name = "PoCtryiso", required = true)
    protected String poCtryiso;
    @XmlElement(name = "Street", required = true)
    protected String street;
    @XmlElement(name = "StrAbbr", required = true)
    protected String strAbbr;
    @XmlElement(name = "HouseNo", required = true)
    protected String houseNo;
    @XmlElement(name = "HouseNo2", required = true)
    protected String houseNo2;
    @XmlElement(name = "HouseNo3", required = true)
    protected String houseNo3;
    @XmlElement(name = "StrSuppl1", required = true)
    protected String strSuppl1;
    @XmlElement(name = "StrSuppl2", required = true)
    protected String strSuppl2;
    @XmlElement(name = "StrSuppl3", required = true)
    protected String strSuppl3;
    @XmlElement(name = "Location", required = true)
    protected String location;
    @XmlElement(name = "Building", required = true)
    protected String building;
    @XmlElement(name = "Floor", required = true)
    protected String floor;
    @XmlElement(name = "RoomNo", required = true)
    protected String roomNo;
    @XmlElement(name = "Country", required = true)
    protected String country;
    @XmlElement(name = "Countryiso", required = true)
    protected String countryiso;
    @XmlElement(name = "Region", required = true)
    protected String region;
    @XmlElement(name = "TimeZone", required = true)
    protected String timeZone;
    @XmlElement(name = "Taxjurcode", required = true)
    protected String taxjurcode;
    @XmlElement(name = "HomeCity", required = true)
    protected String homeCity;
    @XmlElement(name = "Transpzone", required = true)
    protected String transpzone;
    @XmlElement(name = "Langu", required = true)
    protected String langu;
    @XmlElement(name = "Languiso", required = true)
    protected String languiso;
    @XmlElement(name = "CommType", required = true)
    protected String commType;
    @XmlElement(name = "Extaddressnumber", required = true)
    protected String extaddressnumber;
    @XmlElement(name = "DontUseP", required = true)
    protected String dontUseP;
    @XmlElement(name = "DontUseS", required = true)
    protected String dontUseS;
    @XmlElement(name = "MoveDate", required = true)
    protected String moveDate;
    @XmlElement(name = "MoveAddress", required = true)
    protected String moveAddress;
    @XmlElement(name = "Validfromdate", required = true)
    protected String validfromdate;
    @XmlElement(name = "Validtodate", required = true)
    protected String validtodate;
    @XmlElement(name = "MoveAddrGuid", required = true)
    protected String moveAddrGuid;
    @XmlElement(name = "CityNo", required = true)
    protected String cityNo;
    @XmlElement(name = "DistrctNo", required = true)
    protected String distrctNo;
    @XmlElement(name = "Chckstatus", required = true)
    protected String chckstatus;
    @XmlElement(name = "PboxcitNo", required = true)
    protected String pboxcitNo;
    @XmlElement(name = "StreetNo", required = true)
    protected String streetNo;
    @XmlElement(name = "Homecityno", required = true)
    protected String homecityno;
    @XmlElement(name = "PoBoxLobby", required = true)
    protected String poBoxLobby;
    @XmlElement(name = "DeliServType", required = true)
    protected String deliServType;
    @XmlElement(name = "DeliServNumber", required = true)
    protected String deliServNumber;
    @XmlElement(name = "County", required = true)
    protected String county;
    @XmlElement(name = "CountyNo", required = true)
    protected String countyNo;
    @XmlElement(name = "Township", required = true)
    protected String township;
    @XmlElement(name = "TownshipNo", required = true)
    protected String townshipNo;

    /**
     * Gets the value of the standardaddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStandardaddress() {
        return standardaddress;
    }

    /**
     * Sets the value of the standardaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStandardaddress(String value) {
        this.standardaddress = value;
    }

    /**
     * Gets the value of the coName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOName() {
        return coName;
    }

    /**
     * Sets the value of the coName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOName(String value) {
        this.coName = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the district property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets the value of the district property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrict(String value) {
        this.district = value;
    }

    /**
     * Gets the value of the regiogroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegiogroup() {
        return regiogroup;
    }

    /**
     * Sets the value of the regiogroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegiogroup(String value) {
        this.regiogroup = value;
    }

    /**
     * Gets the value of the postlCod1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostlCod1() {
        return postlCod1;
    }

    /**
     * Sets the value of the postlCod1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostlCod1(String value) {
        this.postlCod1 = value;
    }

    /**
     * Gets the value of the postlCod2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostlCod2() {
        return postlCod2;
    }

    /**
     * Sets the value of the postlCod2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostlCod2(String value) {
        this.postlCod2 = value;
    }

    /**
     * Gets the value of the postlCod3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostlCod3() {
        return postlCod3;
    }

    /**
     * Sets the value of the postlCod3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostlCod3(String value) {
        this.postlCod3 = value;
    }

    /**
     * Gets the value of the pcode1Ext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcode1Ext() {
        return pcode1Ext;
    }

    /**
     * Sets the value of the pcode1Ext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcode1Ext(String value) {
        this.pcode1Ext = value;
    }

    /**
     * Gets the value of the pcode2Ext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcode2Ext() {
        return pcode2Ext;
    }

    /**
     * Sets the value of the pcode2Ext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcode2Ext(String value) {
        this.pcode2Ext = value;
    }

    /**
     * Gets the value of the pcode3Ext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcode3Ext() {
        return pcode3Ext;
    }

    /**
     * Sets the value of the pcode3Ext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcode3Ext(String value) {
        this.pcode3Ext = value;
    }

    /**
     * Gets the value of the poBox property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoBox() {
        return poBox;
    }

    /**
     * Sets the value of the poBox property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoBox(String value) {
        this.poBox = value;
    }

    /**
     * Gets the value of the poWONo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoWONo() {
        return poWONo;
    }

    /**
     * Sets the value of the poWONo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoWONo(String value) {
        this.poWONo = value;
    }

    /**
     * Gets the value of the poBoxCit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoBoxCit() {
        return poBoxCit;
    }

    /**
     * Sets the value of the poBoxCit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoBoxCit(String value) {
        this.poBoxCit = value;
    }

    /**
     * Gets the value of the poBoxReg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoBoxReg() {
        return poBoxReg;
    }

    /**
     * Sets the value of the poBoxReg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoBoxReg(String value) {
        this.poBoxReg = value;
    }

    /**
     * Gets the value of the poboxCtry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoboxCtry() {
        return poboxCtry;
    }

    /**
     * Sets the value of the poboxCtry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoboxCtry(String value) {
        this.poboxCtry = value;
    }

    /**
     * Gets the value of the poCtryiso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoCtryiso() {
        return poCtryiso;
    }

    /**
     * Sets the value of the poCtryiso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoCtryiso(String value) {
        this.poCtryiso = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the strAbbr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrAbbr() {
        return strAbbr;
    }

    /**
     * Sets the value of the strAbbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrAbbr(String value) {
        this.strAbbr = value;
    }

    /**
     * Gets the value of the houseNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseNo() {
        return houseNo;
    }

    /**
     * Sets the value of the houseNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseNo(String value) {
        this.houseNo = value;
    }

    /**
     * Gets the value of the houseNo2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseNo2() {
        return houseNo2;
    }

    /**
     * Sets the value of the houseNo2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseNo2(String value) {
        this.houseNo2 = value;
    }

    /**
     * Gets the value of the houseNo3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseNo3() {
        return houseNo3;
    }

    /**
     * Sets the value of the houseNo3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseNo3(String value) {
        this.houseNo3 = value;
    }

    /**
     * Gets the value of the strSuppl1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrSuppl1() {
        return strSuppl1;
    }

    /**
     * Sets the value of the strSuppl1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrSuppl1(String value) {
        this.strSuppl1 = value;
    }

    /**
     * Gets the value of the strSuppl2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrSuppl2() {
        return strSuppl2;
    }

    /**
     * Sets the value of the strSuppl2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrSuppl2(String value) {
        this.strSuppl2 = value;
    }

    /**
     * Gets the value of the strSuppl3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrSuppl3() {
        return strSuppl3;
    }

    /**
     * Sets the value of the strSuppl3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrSuppl3(String value) {
        this.strSuppl3 = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the building property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Sets the value of the building property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuilding(String value) {
        this.building = value;
    }

    /**
     * Gets the value of the floor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFloor() {
        return floor;
    }

    /**
     * Sets the value of the floor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFloor(String value) {
        this.floor = value;
    }

    /**
     * Gets the value of the roomNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * Sets the value of the roomNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomNo(String value) {
        this.roomNo = value;
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
     * Gets the value of the region property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the value of the region property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegion(String value) {
        this.region = value;
    }

    /**
     * Gets the value of the timeZone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Sets the value of the timeZone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeZone(String value) {
        this.timeZone = value;
    }

    /**
     * Gets the value of the taxjurcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxjurcode() {
        return taxjurcode;
    }

    /**
     * Sets the value of the taxjurcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxjurcode(String value) {
        this.taxjurcode = value;
    }

    /**
     * Gets the value of the homeCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeCity() {
        return homeCity;
    }

    /**
     * Sets the value of the homeCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeCity(String value) {
        this.homeCity = value;
    }

    /**
     * Gets the value of the transpzone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranspzone() {
        return transpzone;
    }

    /**
     * Sets the value of the transpzone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranspzone(String value) {
        this.transpzone = value;
    }

    /**
     * Gets the value of the langu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLangu() {
        return langu;
    }

    /**
     * Sets the value of the langu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLangu(String value) {
        this.langu = value;
    }

    /**
     * Gets the value of the languiso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguiso() {
        return languiso;
    }

    /**
     * Sets the value of the languiso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguiso(String value) {
        this.languiso = value;
    }

    /**
     * Gets the value of the commType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommType() {
        return commType;
    }

    /**
     * Sets the value of the commType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommType(String value) {
        this.commType = value;
    }

    /**
     * Gets the value of the extaddressnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtaddressnumber() {
        return extaddressnumber;
    }

    /**
     * Sets the value of the extaddressnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtaddressnumber(String value) {
        this.extaddressnumber = value;
    }

    /**
     * Gets the value of the dontUseP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDontUseP() {
        return dontUseP;
    }

    /**
     * Sets the value of the dontUseP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDontUseP(String value) {
        this.dontUseP = value;
    }

    /**
     * Gets the value of the dontUseS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDontUseS() {
        return dontUseS;
    }

    /**
     * Sets the value of the dontUseS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDontUseS(String value) {
        this.dontUseS = value;
    }

    /**
     * Gets the value of the moveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoveDate() {
        return moveDate;
    }

    /**
     * Sets the value of the moveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoveDate(String value) {
        this.moveDate = value;
    }

    /**
     * Gets the value of the moveAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoveAddress() {
        return moveAddress;
    }

    /**
     * Sets the value of the moveAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoveAddress(String value) {
        this.moveAddress = value;
    }

    /**
     * Gets the value of the validfromdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidfromdate() {
        return validfromdate;
    }

    /**
     * Sets the value of the validfromdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidfromdate(String value) {
        this.validfromdate = value;
    }

    /**
     * Gets the value of the validtodate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidtodate() {
        return validtodate;
    }

    /**
     * Sets the value of the validtodate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidtodate(String value) {
        this.validtodate = value;
    }

    /**
     * Gets the value of the moveAddrGuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoveAddrGuid() {
        return moveAddrGuid;
    }

    /**
     * Sets the value of the moveAddrGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoveAddrGuid(String value) {
        this.moveAddrGuid = value;
    }

    /**
     * Gets the value of the cityNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityNo() {
        return cityNo;
    }

    /**
     * Sets the value of the cityNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityNo(String value) {
        this.cityNo = value;
    }

    /**
     * Gets the value of the distrctNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrctNo() {
        return distrctNo;
    }

    /**
     * Sets the value of the distrctNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrctNo(String value) {
        this.distrctNo = value;
    }

    /**
     * Gets the value of the chckstatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChckstatus() {
        return chckstatus;
    }

    /**
     * Sets the value of the chckstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChckstatus(String value) {
        this.chckstatus = value;
    }

    /**
     * Gets the value of the pboxcitNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPboxcitNo() {
        return pboxcitNo;
    }

    /**
     * Sets the value of the pboxcitNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPboxcitNo(String value) {
        this.pboxcitNo = value;
    }

    /**
     * Gets the value of the streetNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetNo() {
        return streetNo;
    }

    /**
     * Sets the value of the streetNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetNo(String value) {
        this.streetNo = value;
    }

    /**
     * Gets the value of the homecityno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomecityno() {
        return homecityno;
    }

    /**
     * Sets the value of the homecityno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomecityno(String value) {
        this.homecityno = value;
    }

    /**
     * Gets the value of the poBoxLobby property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoBoxLobby() {
        return poBoxLobby;
    }

    /**
     * Sets the value of the poBoxLobby property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoBoxLobby(String value) {
        this.poBoxLobby = value;
    }

    /**
     * Gets the value of the deliServType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliServType() {
        return deliServType;
    }

    /**
     * Sets the value of the deliServType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliServType(String value) {
        this.deliServType = value;
    }

    /**
     * Gets the value of the deliServNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliServNumber() {
        return deliServNumber;
    }

    /**
     * Sets the value of the deliServNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliServNumber(String value) {
        this.deliServNumber = value;
    }

    /**
     * Gets the value of the county property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCounty() {
        return county;
    }

    /**
     * Sets the value of the county property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCounty(String value) {
        this.county = value;
    }

    /**
     * Gets the value of the countyNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountyNo() {
        return countyNo;
    }

    /**
     * Sets the value of the countyNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountyNo(String value) {
        this.countyNo = value;
    }

    /**
     * Gets the value of the township property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTownship() {
        return township;
    }

    /**
     * Sets the value of the township property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTownship(String value) {
        this.township = value;
    }

    /**
     * Gets the value of the townshipNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTownshipNo() {
        return townshipNo;
    }

    /**
     * Sets the value of the townshipNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTownshipNo(String value) {
        this.townshipNo = value;
    }

}
