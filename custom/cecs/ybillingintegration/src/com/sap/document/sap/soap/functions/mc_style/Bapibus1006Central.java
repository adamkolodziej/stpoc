
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapibus1006Central complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapibus1006Central">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Searchterm1" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="Searchterm2" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="Partnertype" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Authorizationgroup" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Partnerlanguage" type="{urn:sap-com:document:sap:rfc:functions}lang"/>
 *         &lt;element name="Partnerlanguageiso" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Dataorigintype" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Centralarchivingflag" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Centralblock" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="TitleKey" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Contactallowance" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Partnerexternal" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="Titleletter" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="Notreleased" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="CommType" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="PrintMode" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapibus1006Central", propOrder = {
    "searchterm1",
    "searchterm2",
    "partnertype",
    "authorizationgroup",
    "partnerlanguage",
    "partnerlanguageiso",
    "dataorigintype",
    "centralarchivingflag",
    "centralblock",
    "titleKey",
    "contactallowance",
    "partnerexternal",
    "titleletter",
    "notreleased",
    "commType",
    "printMode"
})
public class Bapibus1006Central {

    @XmlElement(name = "Searchterm1", required = true)
    protected String searchterm1;
    @XmlElement(name = "Searchterm2", required = true)
    protected String searchterm2;
    @XmlElement(name = "Partnertype", required = true)
    protected String partnertype;
    @XmlElement(name = "Authorizationgroup", required = true)
    protected String authorizationgroup;
    @XmlElement(name = "Partnerlanguage", required = true)
    protected String partnerlanguage;
    @XmlElement(name = "Partnerlanguageiso", required = true)
    protected String partnerlanguageiso;
    @XmlElement(name = "Dataorigintype", required = true)
    protected String dataorigintype;
    @XmlElement(name = "Centralarchivingflag", required = true)
    protected String centralarchivingflag;
    @XmlElement(name = "Centralblock", required = true)
    protected String centralblock;
    @XmlElement(name = "TitleKey", required = true)
    protected String titleKey;
    @XmlElement(name = "Contactallowance", required = true)
    protected String contactallowance;
    @XmlElement(name = "Partnerexternal", required = true)
    protected String partnerexternal;
    @XmlElement(name = "Titleletter", required = true)
    protected String titleletter;
    @XmlElement(name = "Notreleased", required = true)
    protected String notreleased;
    @XmlElement(name = "CommType", required = true)
    protected String commType;
    @XmlElement(name = "PrintMode", required = true)
    protected String printMode;

    /**
     * Gets the value of the searchterm1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchterm1() {
        return searchterm1;
    }

    /**
     * Sets the value of the searchterm1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchterm1(String value) {
        this.searchterm1 = value;
    }

    /**
     * Gets the value of the searchterm2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchterm2() {
        return searchterm2;
    }

    /**
     * Sets the value of the searchterm2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchterm2(String value) {
        this.searchterm2 = value;
    }

    /**
     * Gets the value of the partnertype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnertype() {
        return partnertype;
    }

    /**
     * Sets the value of the partnertype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnertype(String value) {
        this.partnertype = value;
    }

    /**
     * Gets the value of the authorizationgroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorizationgroup() {
        return authorizationgroup;
    }

    /**
     * Sets the value of the authorizationgroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorizationgroup(String value) {
        this.authorizationgroup = value;
    }

    /**
     * Gets the value of the partnerlanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerlanguage() {
        return partnerlanguage;
    }

    /**
     * Sets the value of the partnerlanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerlanguage(String value) {
        this.partnerlanguage = value;
    }

    /**
     * Gets the value of the partnerlanguageiso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerlanguageiso() {
        return partnerlanguageiso;
    }

    /**
     * Sets the value of the partnerlanguageiso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerlanguageiso(String value) {
        this.partnerlanguageiso = value;
    }

    /**
     * Gets the value of the dataorigintype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataorigintype() {
        return dataorigintype;
    }

    /**
     * Sets the value of the dataorigintype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataorigintype(String value) {
        this.dataorigintype = value;
    }

    /**
     * Gets the value of the centralarchivingflag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentralarchivingflag() {
        return centralarchivingflag;
    }

    /**
     * Sets the value of the centralarchivingflag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentralarchivingflag(String value) {
        this.centralarchivingflag = value;
    }

    /**
     * Gets the value of the centralblock property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentralblock() {
        return centralblock;
    }

    /**
     * Sets the value of the centralblock property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentralblock(String value) {
        this.centralblock = value;
    }

    /**
     * Gets the value of the titleKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleKey() {
        return titleKey;
    }

    /**
     * Sets the value of the titleKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleKey(String value) {
        this.titleKey = value;
    }

    /**
     * Gets the value of the contactallowance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactallowance() {
        return contactallowance;
    }

    /**
     * Sets the value of the contactallowance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactallowance(String value) {
        this.contactallowance = value;
    }

    /**
     * Gets the value of the partnerexternal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerexternal() {
        return partnerexternal;
    }

    /**
     * Sets the value of the partnerexternal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerexternal(String value) {
        this.partnerexternal = value;
    }

    /**
     * Gets the value of the titleletter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleletter() {
        return titleletter;
    }

    /**
     * Sets the value of the titleletter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleletter(String value) {
        this.titleletter = value;
    }

    /**
     * Gets the value of the notreleased property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotreleased() {
        return notreleased;
    }

    /**
     * Sets the value of the notreleased property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotreleased(String value) {
        this.notreleased = value;
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
     * Gets the value of the printMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrintMode() {
        return printMode;
    }

    /**
     * Sets the value of the printMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrintMode(String value) {
        this.printMode = value;
    }

}
