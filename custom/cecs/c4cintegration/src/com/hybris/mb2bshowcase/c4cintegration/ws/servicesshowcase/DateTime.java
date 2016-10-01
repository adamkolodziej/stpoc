
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:RepresentationTerm xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:3.0" xmlns="http://sap.com/xi/BASIS/Global" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:n1="http://sap.com/xi/AP/CustomerExtension/BYD/A0014" xmlns:n2="http://sap.com/xi/SAPGlobal20/Global" xmlns:n3="http://sap.com/xi/AP/Common/Global" xmlns:n4="http://sap.com/xi/DocumentServices/Global" xmlns:n5="http://sap.com/xi/BASIS/Global" xmlns:n6="http://sap.com/xi/AP/Common/GDT" xmlns:n7="http://sap.com/xi/Common/DataTypes" xmlns:n8="http://sap.com/xi/Extensibility/Global" xmlns:rfc="urn:sap-com:sap:rfc:functions" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://sap.com/xi/A1S/Global" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:wsoap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:wsp="http://schemas.xmlsoap.org/ws/2004/09/policy" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" xmlns:xi0="http://sap.com/xi/AP/CustomerExtension/BYD/A0014" xmlns:xi1="http://sap.com/xi/SAPGlobal20/Global" xmlns:xi2="http://sap.com/xi/AP/Common/Global" xmlns:xi3="http://sap.com/xi/A1S/Global" xmlns:xi4="http://sap.com/xi/DocumentServices/Global" xmlns:xi5="http://sap.com/xi/BASIS/Global" xmlns:xi6="http://sap.com/xi/AP/Common/GDT" xmlns:xi7="http://sap.com/xi/Common/DataTypes" xmlns:xi8="http://sap.com/xi/Extensibility/Global" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;DateTime&lt;/ccts:RepresentationTerm&gt;
 * </pre>
 * 
 * 
 * <p>Java class for DateTime complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DateTime">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>dateTime">
 *       &lt;attribute name="timeZoneCode" type="{http://sap.com/xi/BASIS/Global}TimeZoneCode" />
 *       &lt;attribute name="daylightSavingTimeIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DateTime", namespace = "http://sap.com/xi/BASIS/Global", propOrder = {
    "value"
})
public class DateTime {

    @XmlValue
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar value;
    @XmlAttribute(name = "timeZoneCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String timeZoneCode;
    @XmlAttribute(name = "daylightSavingTimeIndicator")
    protected Boolean daylightSavingTimeIndicator;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValue(XMLGregorianCalendar value) {
        this.value = value;
    }

    /**
     * Gets the value of the timeZoneCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeZoneCode() {
        return timeZoneCode;
    }

    /**
     * Sets the value of the timeZoneCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeZoneCode(String value) {
        this.timeZoneCode = value;
    }

    /**
     * Gets the value of the daylightSavingTimeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDaylightSavingTimeIndicator() {
        return daylightSavingTimeIndicator;
    }

    /**
     * Sets the value of the daylightSavingTimeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDaylightSavingTimeIndicator(Boolean value) {
        this.daylightSavingTimeIndicator = value;
    }

}