
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:RepresentationTerm xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:3.0" xmlns="http://sap.com/xi/BASIS/Global" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:n1="http://sap.com/xi/AP/CustomerExtension/BYD/A0014" xmlns:n2="http://sap.com/xi/SAPGlobal20/Global" xmlns:n3="http://sap.com/xi/AP/Common/Global" xmlns:n4="http://sap.com/xi/DocumentServices/Global" xmlns:n5="http://sap.com/xi/BASIS/Global" xmlns:n6="http://sap.com/xi/AP/Common/GDT" xmlns:n7="http://sap.com/xi/Common/DataTypes" xmlns:n8="http://sap.com/xi/Extensibility/Global" xmlns:rfc="urn:sap-com:sap:rfc:functions" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://sap.com/xi/A1S/Global" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:wsoap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:wsp="http://schemas.xmlsoap.org/ws/2004/09/policy" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" xmlns:xi0="http://sap.com/xi/AP/CustomerExtension/BYD/A0014" xmlns:xi1="http://sap.com/xi/SAPGlobal20/Global" xmlns:xi2="http://sap.com/xi/AP/Common/Global" xmlns:xi3="http://sap.com/xi/A1S/Global" xmlns:xi4="http://sap.com/xi/DocumentServices/Global" xmlns:xi5="http://sap.com/xi/BASIS/Global" xmlns:xi6="http://sap.com/xi/AP/Common/GDT" xmlns:xi7="http://sap.com/xi/Common/DataTypes" xmlns:xi8="http://sap.com/xi/Extensibility/Global" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Code&lt;/ccts:RepresentationTerm&gt;
 * </pre>
 * 
 * 
 * <p>Java class for Code complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Code">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
 *       &lt;attribute name="listID">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;maxLength value="60"/>
 *             &lt;minLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="listVersionID">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;maxLength value="15"/>
 *             &lt;minLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="listAgencyID">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;maxLength value="60"/>
 *             &lt;minLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="listAgencySchemeID">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;maxLength value="60"/>
 *             &lt;minLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="listAgencySchemeAgencyID" type="{http://sap.com/xi/BASIS/Global}AgencyIdentificationCode" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Code", namespace = "http://sap.com/xi/BASIS/Global", propOrder = {
    "value"
})
public class Code {

    @XmlValue
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String value;
    @XmlAttribute(name = "listID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String listID;
    @XmlAttribute(name = "listVersionID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String listVersionID;
    @XmlAttribute(name = "listAgencyID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String listAgencyID;
    @XmlAttribute(name = "listAgencySchemeID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String listAgencySchemeID;
    @XmlAttribute(name = "listAgencySchemeAgencyID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String listAgencySchemeAgencyID;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the listID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListID() {
        return listID;
    }

    /**
     * Sets the value of the listID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListID(String value) {
        this.listID = value;
    }

    /**
     * Gets the value of the listVersionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListVersionID() {
        return listVersionID;
    }

    /**
     * Sets the value of the listVersionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListVersionID(String value) {
        this.listVersionID = value;
    }

    /**
     * Gets the value of the listAgencyID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListAgencyID() {
        return listAgencyID;
    }

    /**
     * Sets the value of the listAgencyID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListAgencyID(String value) {
        this.listAgencyID = value;
    }

    /**
     * Gets the value of the listAgencySchemeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListAgencySchemeID() {
        return listAgencySchemeID;
    }

    /**
     * Sets the value of the listAgencySchemeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListAgencySchemeID(String value) {
        this.listAgencySchemeID = value;
    }

    /**
     * Gets the value of the listAgencySchemeAgencyID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListAgencySchemeAgencyID() {
        return listAgencySchemeAgencyID;
    }

    /**
     * Sets the value of the listAgencySchemeAgencyID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListAgencySchemeAgencyID(String value) {
        this.listAgencySchemeAgencyID = value;
    }

}
