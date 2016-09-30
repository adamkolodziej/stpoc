
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:RepresentationTerm xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:3.0" xmlns="http://sap.com/xi/AP/Common/GDT" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:n1="http://sap.com/xi/AP/CustomerExtension/BYD/A0014" xmlns:n2="http://sap.com/xi/SAPGlobal20/Global" xmlns:n3="http://sap.com/xi/AP/Common/Global" xmlns:n4="http://sap.com/xi/AP/Common/GDT" xmlns:n5="http://sap.com/xi/BASIS/Global" xmlns:n6="http://sap.com/xi/DocumentServices/Global" xmlns:n7="http://sap.com/xi/Common/DataTypes" xmlns:rfc="urn:sap-com:sap:rfc:functions" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://sap.com/xi/A1S/Global" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:wsoap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:wsp="http://schemas.xmlsoap.org/ws/2004/09/policy" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" xmlns:xi1="http://sap.com/xi/AP/CustomerExtension/BYD/A0014" xmlns:xi2="http://sap.com/xi/SAPGlobal20/Global" xmlns:xi3="http://sap.com/xi/AP/Common/Global" xmlns:xi4="http://sap.com/xi/A1S/Global" xmlns:xi5="http://sap.com/xi/AP/Common/GDT" xmlns:xi6="http://sap.com/xi/BASIS/Global" xmlns:xi7="http://sap.com/xi/DocumentServices/Global" xmlns:xi8="http://sap.com/xi/Common/DataTypes" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Description&lt;/ccts:RepresentationTerm&gt;
 * </pre>
 * 
 * 
 * <p>Java class for SHORT_Description complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SHORT_Description">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://sap.com/xi/AP/Common/GDT>SHORT_Description.Content">
 *       &lt;attribute name="languageCode" type="{http://sap.com/xi/BASIS/Global}LanguageCode" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SHORT_Description", namespace = "http://sap.com/xi/AP/Common/GDT", propOrder = {
    "value"
})
public class SHORTDescription {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "languageCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String languageCode;

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
     * Gets the value of the languageCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * Sets the value of the languageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageCode(String value) {
        this.languageCode = value;
    }

}
