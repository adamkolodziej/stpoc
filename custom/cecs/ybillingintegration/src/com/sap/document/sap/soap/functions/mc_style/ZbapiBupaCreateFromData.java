
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
 *         &lt;element name="AcceptError" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/>
 *         &lt;element name="Addressdata" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006Address" minOccurs="0"/>
 *         &lt;element name="Addressduplicates" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapibus1006AddressDuplicates" minOccurs="0"/>
 *         &lt;element name="Addressnotes" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadRem" minOccurs="0"/>
 *         &lt;element name="Businesspartnerextern" type="{urn:sap-com:document:sap:rfc:functions}char10" minOccurs="0"/>
 *         &lt;element name="Centraldata" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006Central"/>
 *         &lt;element name="Centraldatagroup" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006CentralGroup" minOccurs="0"/>
 *         &lt;element name="Centraldataorganization" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006CentralOrgan" minOccurs="0"/>
 *         &lt;element name="Centraldataperson" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapibus1006CentralPerson" minOccurs="0"/>
 *         &lt;element name="Communicationnotes" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapicomrem" minOccurs="0"/>
 *         &lt;element name="Communicationnotesnonaddress" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapicomrem" minOccurs="0"/>
 *         &lt;element name="Communicationusage" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiaduse" minOccurs="0"/>
 *         &lt;element name="Communicationusagenonaddress" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiaduse" minOccurs="0"/>
 *         &lt;element name="DuplicateMessageType" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/>
 *         &lt;element name="EMaildata" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadsmtp" minOccurs="0"/>
 *         &lt;element name="EMaildatanonaddress" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadsmtp" minOccurs="0"/>
 *         &lt;element name="Faxdata" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadfax" minOccurs="0"/>
 *         &lt;element name="Faxdatanonaddress" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadfax" minOccurs="0"/>
 *         &lt;element name="Pagaddressdata" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadpag" minOccurs="0"/>
 *         &lt;element name="Pagaddressdatanonaddress" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadpag" minOccurs="0"/>
 *         &lt;element name="Partnercategory" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Partnergroup" type="{urn:sap-com:document:sap:rfc:functions}char4" minOccurs="0"/>
 *         &lt;element name="Prtaddressdata" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadprt" minOccurs="0"/>
 *         &lt;element name="Prtaddressdatanonaddress" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadprt" minOccurs="0"/>
 *         &lt;element name="Return" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiret2" minOccurs="0"/>
 *         &lt;element name="Rfcaddressdata" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadrfc" minOccurs="0"/>
 *         &lt;element name="Rfcaddressdatanonaddress" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadrfc" minOccurs="0"/>
 *         &lt;element name="Rmladdressdata" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadrml" minOccurs="0"/>
 *         &lt;element name="Rmladdressdatanonaddress" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadrml" minOccurs="0"/>
 *         &lt;element name="Ssfaddressdata" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadssf" minOccurs="0"/>
 *         &lt;element name="Ssfaddressdatanonaddress" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadssf" minOccurs="0"/>
 *         &lt;element name="Telefondata" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadtel" minOccurs="0"/>
 *         &lt;element name="Telefondatanonaddress" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadtel" minOccurs="0"/>
 *         &lt;element name="Teletexdata" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadttx" minOccurs="0"/>
 *         &lt;element name="Teletexdatanonaddress" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadttx" minOccurs="0"/>
 *         &lt;element name="Telexdata" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadtlx" minOccurs="0"/>
 *         &lt;element name="Telexdatanonaddress" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadtlx" minOccurs="0"/>
 *         &lt;element name="Uriaddressdata" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiaduri" minOccurs="0"/>
 *         &lt;element name="Uriaddressdatanonaddress" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiaduri" minOccurs="0"/>
 *         &lt;element name="X400addressdata" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadx400" minOccurs="0"/>
 *         &lt;element name="X400addressdatanonaddress" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiadx400" minOccurs="0"/>
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
    "acceptError",
    "addressdata",
    "addressduplicates",
    "addressnotes",
    "businesspartnerextern",
    "centraldata",
    "centraldatagroup",
    "centraldataorganization",
    "centraldataperson",
    "communicationnotes",
    "communicationnotesnonaddress",
    "communicationusage",
    "communicationusagenonaddress",
    "duplicateMessageType",
    "eMaildata",
    "eMaildatanonaddress",
    "faxdata",
    "faxdatanonaddress",
    "pagaddressdata",
    "pagaddressdatanonaddress",
    "partnercategory",
    "partnergroup",
    "prtaddressdata",
    "prtaddressdatanonaddress",
    "_return",
    "rfcaddressdata",
    "rfcaddressdatanonaddress",
    "rmladdressdata",
    "rmladdressdatanonaddress",
    "ssfaddressdata",
    "ssfaddressdatanonaddress",
    "telefondata",
    "telefondatanonaddress",
    "teletexdata",
    "teletexdatanonaddress",
    "telexdata",
    "telexdatanonaddress",
    "uriaddressdata",
    "uriaddressdatanonaddress",
    "x400Addressdata",
    "x400Addressdatanonaddress"
})
@XmlRootElement(name = "ZbapiBupaCreateFromData")
public class ZbapiBupaCreateFromData {

    @XmlElement(name = "AcceptError")
    protected String acceptError;
    @XmlElement(name = "Addressdata")
    protected Bapibus1006Address addressdata;
    @XmlElement(name = "Addressduplicates")
    protected TableOfBapibus1006AddressDuplicates addressduplicates;
    @XmlElement(name = "Addressnotes")
    protected TableOfBapiadRem addressnotes;
    @XmlElement(name = "Businesspartnerextern")
    protected String businesspartnerextern;
    @XmlElement(name = "Centraldata", required = true)
    protected Bapibus1006Central centraldata;
    @XmlElement(name = "Centraldatagroup")
    protected Bapibus1006CentralGroup centraldatagroup;
    @XmlElement(name = "Centraldataorganization")
    protected Bapibus1006CentralOrgan centraldataorganization;
    @XmlElement(name = "Centraldataperson")
    protected Bapibus1006CentralPerson centraldataperson;
    @XmlElement(name = "Communicationnotes")
    protected TableOfBapicomrem communicationnotes;
    @XmlElement(name = "Communicationnotesnonaddress")
    protected TableOfBapicomrem communicationnotesnonaddress;
    @XmlElement(name = "Communicationusage")
    protected TableOfBapiaduse communicationusage;
    @XmlElement(name = "Communicationusagenonaddress")
    protected TableOfBapiaduse communicationusagenonaddress;
    @XmlElement(name = "DuplicateMessageType")
    protected String duplicateMessageType;
    @XmlElement(name = "EMaildata")
    protected TableOfBapiadsmtp eMaildata;
    @XmlElement(name = "EMaildatanonaddress")
    protected TableOfBapiadsmtp eMaildatanonaddress;
    @XmlElement(name = "Faxdata")
    protected TableOfBapiadfax faxdata;
    @XmlElement(name = "Faxdatanonaddress")
    protected TableOfBapiadfax faxdatanonaddress;
    @XmlElement(name = "Pagaddressdata")
    protected TableOfBapiadpag pagaddressdata;
    @XmlElement(name = "Pagaddressdatanonaddress")
    protected TableOfBapiadpag pagaddressdatanonaddress;
    @XmlElement(name = "Partnercategory", required = true)
    protected String partnercategory;
    @XmlElement(name = "Partnergroup")
    protected String partnergroup;
    @XmlElement(name = "Prtaddressdata")
    protected TableOfBapiadprt prtaddressdata;
    @XmlElement(name = "Prtaddressdatanonaddress")
    protected TableOfBapiadprt prtaddressdatanonaddress;
    @XmlElement(name = "Return")
    protected TableOfBapiret2 _return;
    @XmlElement(name = "Rfcaddressdata")
    protected TableOfBapiadrfc rfcaddressdata;
    @XmlElement(name = "Rfcaddressdatanonaddress")
    protected TableOfBapiadrfc rfcaddressdatanonaddress;
    @XmlElement(name = "Rmladdressdata")
    protected TableOfBapiadrml rmladdressdata;
    @XmlElement(name = "Rmladdressdatanonaddress")
    protected TableOfBapiadrml rmladdressdatanonaddress;
    @XmlElement(name = "Ssfaddressdata")
    protected TableOfBapiadssf ssfaddressdata;
    @XmlElement(name = "Ssfaddressdatanonaddress")
    protected TableOfBapiadssf ssfaddressdatanonaddress;
    @XmlElement(name = "Telefondata")
    protected TableOfBapiadtel telefondata;
    @XmlElement(name = "Telefondatanonaddress")
    protected TableOfBapiadtel telefondatanonaddress;
    @XmlElement(name = "Teletexdata")
    protected TableOfBapiadttx teletexdata;
    @XmlElement(name = "Teletexdatanonaddress")
    protected TableOfBapiadttx teletexdatanonaddress;
    @XmlElement(name = "Telexdata")
    protected TableOfBapiadtlx telexdata;
    @XmlElement(name = "Telexdatanonaddress")
    protected TableOfBapiadtlx telexdatanonaddress;
    @XmlElement(name = "Uriaddressdata")
    protected TableOfBapiaduri uriaddressdata;
    @XmlElement(name = "Uriaddressdatanonaddress")
    protected TableOfBapiaduri uriaddressdatanonaddress;
    @XmlElement(name = "X400addressdata")
    protected TableOfBapiadx400 x400Addressdata;
    @XmlElement(name = "X400addressdatanonaddress")
    protected TableOfBapiadx400 x400Addressdatanonaddress;

    /**
     * Gets the value of the acceptError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptError() {
        return acceptError;
    }

    /**
     * Sets the value of the acceptError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptError(String value) {
        this.acceptError = value;
    }

    /**
     * Gets the value of the addressdata property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006Address }
     *     
     */
    public Bapibus1006Address getAddressdata() {
        return addressdata;
    }

    /**
     * Sets the value of the addressdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006Address }
     *     
     */
    public void setAddressdata(Bapibus1006Address value) {
        this.addressdata = value;
    }

    /**
     * Gets the value of the addressduplicates property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapibus1006AddressDuplicates }
     *     
     */
    public TableOfBapibus1006AddressDuplicates getAddressduplicates() {
        return addressduplicates;
    }

    /**
     * Sets the value of the addressduplicates property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapibus1006AddressDuplicates }
     *     
     */
    public void setAddressduplicates(TableOfBapibus1006AddressDuplicates value) {
        this.addressduplicates = value;
    }

    /**
     * Gets the value of the addressnotes property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadRem }
     *     
     */
    public TableOfBapiadRem getAddressnotes() {
        return addressnotes;
    }

    /**
     * Sets the value of the addressnotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadRem }
     *     
     */
    public void setAddressnotes(TableOfBapiadRem value) {
        this.addressnotes = value;
    }

    /**
     * Gets the value of the businesspartnerextern property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinesspartnerextern() {
        return businesspartnerextern;
    }

    /**
     * Sets the value of the businesspartnerextern property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinesspartnerextern(String value) {
        this.businesspartnerextern = value;
    }

    /**
     * Gets the value of the centraldata property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006Central }
     *     
     */
    public Bapibus1006Central getCentraldata() {
        return centraldata;
    }

    /**
     * Sets the value of the centraldata property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006Central }
     *     
     */
    public void setCentraldata(Bapibus1006Central value) {
        this.centraldata = value;
    }

    /**
     * Gets the value of the centraldatagroup property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006CentralGroup }
     *     
     */
    public Bapibus1006CentralGroup getCentraldatagroup() {
        return centraldatagroup;
    }

    /**
     * Sets the value of the centraldatagroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006CentralGroup }
     *     
     */
    public void setCentraldatagroup(Bapibus1006CentralGroup value) {
        this.centraldatagroup = value;
    }

    /**
     * Gets the value of the centraldataorganization property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006CentralOrgan }
     *     
     */
    public Bapibus1006CentralOrgan getCentraldataorganization() {
        return centraldataorganization;
    }

    /**
     * Sets the value of the centraldataorganization property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006CentralOrgan }
     *     
     */
    public void setCentraldataorganization(Bapibus1006CentralOrgan value) {
        this.centraldataorganization = value;
    }

    /**
     * Gets the value of the centraldataperson property.
     * 
     * @return
     *     possible object is
     *     {@link Bapibus1006CentralPerson }
     *     
     */
    public Bapibus1006CentralPerson getCentraldataperson() {
        return centraldataperson;
    }

    /**
     * Sets the value of the centraldataperson property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapibus1006CentralPerson }
     *     
     */
    public void setCentraldataperson(Bapibus1006CentralPerson value) {
        this.centraldataperson = value;
    }

    /**
     * Gets the value of the communicationnotes property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapicomrem }
     *     
     */
    public TableOfBapicomrem getCommunicationnotes() {
        return communicationnotes;
    }

    /**
     * Sets the value of the communicationnotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapicomrem }
     *     
     */
    public void setCommunicationnotes(TableOfBapicomrem value) {
        this.communicationnotes = value;
    }

    /**
     * Gets the value of the communicationnotesnonaddress property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapicomrem }
     *     
     */
    public TableOfBapicomrem getCommunicationnotesnonaddress() {
        return communicationnotesnonaddress;
    }

    /**
     * Sets the value of the communicationnotesnonaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapicomrem }
     *     
     */
    public void setCommunicationnotesnonaddress(TableOfBapicomrem value) {
        this.communicationnotesnonaddress = value;
    }

    /**
     * Gets the value of the communicationusage property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiaduse }
     *     
     */
    public TableOfBapiaduse getCommunicationusage() {
        return communicationusage;
    }

    /**
     * Sets the value of the communicationusage property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiaduse }
     *     
     */
    public void setCommunicationusage(TableOfBapiaduse value) {
        this.communicationusage = value;
    }

    /**
     * Gets the value of the communicationusagenonaddress property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiaduse }
     *     
     */
    public TableOfBapiaduse getCommunicationusagenonaddress() {
        return communicationusagenonaddress;
    }

    /**
     * Sets the value of the communicationusagenonaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiaduse }
     *     
     */
    public void setCommunicationusagenonaddress(TableOfBapiaduse value) {
        this.communicationusagenonaddress = value;
    }

    /**
     * Gets the value of the duplicateMessageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuplicateMessageType() {
        return duplicateMessageType;
    }

    /**
     * Sets the value of the duplicateMessageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuplicateMessageType(String value) {
        this.duplicateMessageType = value;
    }

    /**
     * Gets the value of the eMaildata property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadsmtp }
     *     
     */
    public TableOfBapiadsmtp getEMaildata() {
        return eMaildata;
    }

    /**
     * Sets the value of the eMaildata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadsmtp }
     *     
     */
    public void setEMaildata(TableOfBapiadsmtp value) {
        this.eMaildata = value;
    }

    /**
     * Gets the value of the eMaildatanonaddress property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadsmtp }
     *     
     */
    public TableOfBapiadsmtp getEMaildatanonaddress() {
        return eMaildatanonaddress;
    }

    /**
     * Sets the value of the eMaildatanonaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadsmtp }
     *     
     */
    public void setEMaildatanonaddress(TableOfBapiadsmtp value) {
        this.eMaildatanonaddress = value;
    }

    /**
     * Gets the value of the faxdata property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadfax }
     *     
     */
    public TableOfBapiadfax getFaxdata() {
        return faxdata;
    }

    /**
     * Sets the value of the faxdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadfax }
     *     
     */
    public void setFaxdata(TableOfBapiadfax value) {
        this.faxdata = value;
    }

    /**
     * Gets the value of the faxdatanonaddress property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadfax }
     *     
     */
    public TableOfBapiadfax getFaxdatanonaddress() {
        return faxdatanonaddress;
    }

    /**
     * Sets the value of the faxdatanonaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadfax }
     *     
     */
    public void setFaxdatanonaddress(TableOfBapiadfax value) {
        this.faxdatanonaddress = value;
    }

    /**
     * Gets the value of the pagaddressdata property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadpag }
     *     
     */
    public TableOfBapiadpag getPagaddressdata() {
        return pagaddressdata;
    }

    /**
     * Sets the value of the pagaddressdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadpag }
     *     
     */
    public void setPagaddressdata(TableOfBapiadpag value) {
        this.pagaddressdata = value;
    }

    /**
     * Gets the value of the pagaddressdatanonaddress property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadpag }
     *     
     */
    public TableOfBapiadpag getPagaddressdatanonaddress() {
        return pagaddressdatanonaddress;
    }

    /**
     * Sets the value of the pagaddressdatanonaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadpag }
     *     
     */
    public void setPagaddressdatanonaddress(TableOfBapiadpag value) {
        this.pagaddressdatanonaddress = value;
    }

    /**
     * Gets the value of the partnercategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnercategory() {
        return partnercategory;
    }

    /**
     * Sets the value of the partnercategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnercategory(String value) {
        this.partnercategory = value;
    }

    /**
     * Gets the value of the partnergroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnergroup() {
        return partnergroup;
    }

    /**
     * Sets the value of the partnergroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnergroup(String value) {
        this.partnergroup = value;
    }

    /**
     * Gets the value of the prtaddressdata property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadprt }
     *     
     */
    public TableOfBapiadprt getPrtaddressdata() {
        return prtaddressdata;
    }

    /**
     * Sets the value of the prtaddressdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadprt }
     *     
     */
    public void setPrtaddressdata(TableOfBapiadprt value) {
        this.prtaddressdata = value;
    }

    /**
     * Gets the value of the prtaddressdatanonaddress property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadprt }
     *     
     */
    public TableOfBapiadprt getPrtaddressdatanonaddress() {
        return prtaddressdatanonaddress;
    }

    /**
     * Sets the value of the prtaddressdatanonaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadprt }
     *     
     */
    public void setPrtaddressdatanonaddress(TableOfBapiadprt value) {
        this.prtaddressdatanonaddress = value;
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
     * Gets the value of the rfcaddressdata property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadrfc }
     *     
     */
    public TableOfBapiadrfc getRfcaddressdata() {
        return rfcaddressdata;
    }

    /**
     * Sets the value of the rfcaddressdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadrfc }
     *     
     */
    public void setRfcaddressdata(TableOfBapiadrfc value) {
        this.rfcaddressdata = value;
    }

    /**
     * Gets the value of the rfcaddressdatanonaddress property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadrfc }
     *     
     */
    public TableOfBapiadrfc getRfcaddressdatanonaddress() {
        return rfcaddressdatanonaddress;
    }

    /**
     * Sets the value of the rfcaddressdatanonaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadrfc }
     *     
     */
    public void setRfcaddressdatanonaddress(TableOfBapiadrfc value) {
        this.rfcaddressdatanonaddress = value;
    }

    /**
     * Gets the value of the rmladdressdata property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadrml }
     *     
     */
    public TableOfBapiadrml getRmladdressdata() {
        return rmladdressdata;
    }

    /**
     * Sets the value of the rmladdressdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadrml }
     *     
     */
    public void setRmladdressdata(TableOfBapiadrml value) {
        this.rmladdressdata = value;
    }

    /**
     * Gets the value of the rmladdressdatanonaddress property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadrml }
     *     
     */
    public TableOfBapiadrml getRmladdressdatanonaddress() {
        return rmladdressdatanonaddress;
    }

    /**
     * Sets the value of the rmladdressdatanonaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadrml }
     *     
     */
    public void setRmladdressdatanonaddress(TableOfBapiadrml value) {
        this.rmladdressdatanonaddress = value;
    }

    /**
     * Gets the value of the ssfaddressdata property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadssf }
     *     
     */
    public TableOfBapiadssf getSsfaddressdata() {
        return ssfaddressdata;
    }

    /**
     * Sets the value of the ssfaddressdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadssf }
     *     
     */
    public void setSsfaddressdata(TableOfBapiadssf value) {
        this.ssfaddressdata = value;
    }

    /**
     * Gets the value of the ssfaddressdatanonaddress property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadssf }
     *     
     */
    public TableOfBapiadssf getSsfaddressdatanonaddress() {
        return ssfaddressdatanonaddress;
    }

    /**
     * Sets the value of the ssfaddressdatanonaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadssf }
     *     
     */
    public void setSsfaddressdatanonaddress(TableOfBapiadssf value) {
        this.ssfaddressdatanonaddress = value;
    }

    /**
     * Gets the value of the telefondata property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadtel }
     *     
     */
    public TableOfBapiadtel getTelefondata() {
        return telefondata;
    }

    /**
     * Sets the value of the telefondata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadtel }
     *     
     */
    public void setTelefondata(TableOfBapiadtel value) {
        this.telefondata = value;
    }

    /**
     * Gets the value of the telefondatanonaddress property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadtel }
     *     
     */
    public TableOfBapiadtel getTelefondatanonaddress() {
        return telefondatanonaddress;
    }

    /**
     * Sets the value of the telefondatanonaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadtel }
     *     
     */
    public void setTelefondatanonaddress(TableOfBapiadtel value) {
        this.telefondatanonaddress = value;
    }

    /**
     * Gets the value of the teletexdata property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadttx }
     *     
     */
    public TableOfBapiadttx getTeletexdata() {
        return teletexdata;
    }

    /**
     * Sets the value of the teletexdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadttx }
     *     
     */
    public void setTeletexdata(TableOfBapiadttx value) {
        this.teletexdata = value;
    }

    /**
     * Gets the value of the teletexdatanonaddress property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadttx }
     *     
     */
    public TableOfBapiadttx getTeletexdatanonaddress() {
        return teletexdatanonaddress;
    }

    /**
     * Sets the value of the teletexdatanonaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadttx }
     *     
     */
    public void setTeletexdatanonaddress(TableOfBapiadttx value) {
        this.teletexdatanonaddress = value;
    }

    /**
     * Gets the value of the telexdata property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadtlx }
     *     
     */
    public TableOfBapiadtlx getTelexdata() {
        return telexdata;
    }

    /**
     * Sets the value of the telexdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadtlx }
     *     
     */
    public void setTelexdata(TableOfBapiadtlx value) {
        this.telexdata = value;
    }

    /**
     * Gets the value of the telexdatanonaddress property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadtlx }
     *     
     */
    public TableOfBapiadtlx getTelexdatanonaddress() {
        return telexdatanonaddress;
    }

    /**
     * Sets the value of the telexdatanonaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadtlx }
     *     
     */
    public void setTelexdatanonaddress(TableOfBapiadtlx value) {
        this.telexdatanonaddress = value;
    }

    /**
     * Gets the value of the uriaddressdata property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiaduri }
     *     
     */
    public TableOfBapiaduri getUriaddressdata() {
        return uriaddressdata;
    }

    /**
     * Sets the value of the uriaddressdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiaduri }
     *     
     */
    public void setUriaddressdata(TableOfBapiaduri value) {
        this.uriaddressdata = value;
    }

    /**
     * Gets the value of the uriaddressdatanonaddress property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiaduri }
     *     
     */
    public TableOfBapiaduri getUriaddressdatanonaddress() {
        return uriaddressdatanonaddress;
    }

    /**
     * Sets the value of the uriaddressdatanonaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiaduri }
     *     
     */
    public void setUriaddressdatanonaddress(TableOfBapiaduri value) {
        this.uriaddressdatanonaddress = value;
    }

    /**
     * Gets the value of the x400Addressdata property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadx400 }
     *     
     */
    public TableOfBapiadx400 getX400Addressdata() {
        return x400Addressdata;
    }

    /**
     * Sets the value of the x400Addressdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadx400 }
     *     
     */
    public void setX400Addressdata(TableOfBapiadx400 value) {
        this.x400Addressdata = value;
    }

    /**
     * Gets the value of the x400Addressdatanonaddress property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiadx400 }
     *     
     */
    public TableOfBapiadx400 getX400Addressdatanonaddress() {
        return x400Addressdatanonaddress;
    }

    /**
     * Sets the value of the x400Addressdatanonaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiadx400 }
     *     
     */
    public void setX400Addressdatanonaddress(TableOfBapiadx400 value) {
        this.x400Addressdatanonaddress = value;
    }

}
