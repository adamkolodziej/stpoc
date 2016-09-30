
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapibus1006CentralGroup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapibus1006CentralGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Namegroup1" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Namegroup2" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Grouptype" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapibus1006CentralGroup", propOrder = {
    "namegroup1",
    "namegroup2",
    "grouptype"
})
public class Bapibus1006CentralGroup {

    @XmlElement(name = "Namegroup1", required = true)
    protected String namegroup1;
    @XmlElement(name = "Namegroup2", required = true)
    protected String namegroup2;
    @XmlElement(name = "Grouptype", required = true)
    protected String grouptype;

    /**
     * Gets the value of the namegroup1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamegroup1() {
        return namegroup1;
    }

    /**
     * Sets the value of the namegroup1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamegroup1(String value) {
        this.namegroup1 = value;
    }

    /**
     * Gets the value of the namegroup2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamegroup2() {
        return namegroup2;
    }

    /**
     * Sets the value of the namegroup2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamegroup2(String value) {
        this.namegroup2 = value;
    }

    /**
     * Gets the value of the grouptype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrouptype() {
        return grouptype;
    }

    /**
     * Sets the value of the grouptype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrouptype(String value) {
        this.grouptype = value;
    }

}
