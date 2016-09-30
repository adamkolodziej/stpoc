
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapibus1006CentralOrgan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapibus1006CentralOrgan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name1" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Name2" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Name3" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Name4" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Legalform" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Industrysector" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Foundationdate" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="Liquidationdate" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="LocNo1" type="{urn:sap-com:document:sap:rfc:functions}numeric7"/>
 *         &lt;element name="LocNo2" type="{urn:sap-com:document:sap:rfc:functions}numeric5"/>
 *         &lt;element name="ChkDigit" type="{urn:sap-com:document:sap:rfc:functions}numeric1"/>
 *         &lt;element name="Legalorg" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapibus1006CentralOrgan", propOrder = {
    "name1",
    "name2",
    "name3",
    "name4",
    "legalform",
    "industrysector",
    "foundationdate",
    "liquidationdate",
    "locNo1",
    "locNo2",
    "chkDigit",
    "legalorg"
})
public class Bapibus1006CentralOrgan {

    @XmlElement(name = "Name1", required = true)
    protected String name1;
    @XmlElement(name = "Name2", required = true)
    protected String name2;
    @XmlElement(name = "Name3", required = true)
    protected String name3;
    @XmlElement(name = "Name4", required = true)
    protected String name4;
    @XmlElement(name = "Legalform", required = true)
    protected String legalform;
    @XmlElement(name = "Industrysector", required = true)
    protected String industrysector;
    @XmlElement(name = "Foundationdate", required = true)
    protected String foundationdate;
    @XmlElement(name = "Liquidationdate", required = true)
    protected String liquidationdate;
    @XmlElement(name = "LocNo1", required = true)
    protected String locNo1;
    @XmlElement(name = "LocNo2", required = true)
    protected String locNo2;
    @XmlElement(name = "ChkDigit", required = true)
    protected String chkDigit;
    @XmlElement(name = "Legalorg", required = true)
    protected String legalorg;

    /**
     * Gets the value of the name1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName1() {
        return name1;
    }

    /**
     * Sets the value of the name1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName1(String value) {
        this.name1 = value;
    }

    /**
     * Gets the value of the name2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName2() {
        return name2;
    }

    /**
     * Sets the value of the name2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName2(String value) {
        this.name2 = value;
    }

    /**
     * Gets the value of the name3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName3() {
        return name3;
    }

    /**
     * Sets the value of the name3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName3(String value) {
        this.name3 = value;
    }

    /**
     * Gets the value of the name4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName4() {
        return name4;
    }

    /**
     * Sets the value of the name4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName4(String value) {
        this.name4 = value;
    }

    /**
     * Gets the value of the legalform property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalform() {
        return legalform;
    }

    /**
     * Sets the value of the legalform property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalform(String value) {
        this.legalform = value;
    }

    /**
     * Gets the value of the industrysector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndustrysector() {
        return industrysector;
    }

    /**
     * Sets the value of the industrysector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndustrysector(String value) {
        this.industrysector = value;
    }

    /**
     * Gets the value of the foundationdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFoundationdate() {
        return foundationdate;
    }

    /**
     * Sets the value of the foundationdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFoundationdate(String value) {
        this.foundationdate = value;
    }

    /**
     * Gets the value of the liquidationdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLiquidationdate() {
        return liquidationdate;
    }

    /**
     * Sets the value of the liquidationdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLiquidationdate(String value) {
        this.liquidationdate = value;
    }

    /**
     * Gets the value of the locNo1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocNo1() {
        return locNo1;
    }

    /**
     * Sets the value of the locNo1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocNo1(String value) {
        this.locNo1 = value;
    }

    /**
     * Gets the value of the locNo2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocNo2() {
        return locNo2;
    }

    /**
     * Sets the value of the locNo2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocNo2(String value) {
        this.locNo2 = value;
    }

    /**
     * Gets the value of the chkDigit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChkDigit() {
        return chkDigit;
    }

    /**
     * Sets the value of the chkDigit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChkDigit(String value) {
        this.chkDigit = value;
    }

    /**
     * Gets the value of the legalorg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalorg() {
        return legalorg;
    }

    /**
     * Sets the value of the legalorg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalorg(String value) {
        this.legalorg = value;
    }

}
