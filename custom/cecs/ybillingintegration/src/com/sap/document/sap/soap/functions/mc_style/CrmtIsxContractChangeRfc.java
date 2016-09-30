
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrmtIsxContractChangeRfc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrmtIsxContractChangeRfc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItemGuid" type="{urn:sap-com:document:sap:rfc:functions}byte16"/>
 *         &lt;element name="Process" type="{urn:sap-com:document:sap:rfc:functions}char32"/>
 *         &lt;element name="ActivationDate" type="{urn:sap-com:document:sap:rfc:functions}decimal15.0"/>
 *         &lt;element name="Timezone" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
 *         &lt;element name="ProcessAttributes" type="{urn:sap-com:document:sap:soap:functions:mc-style}CrmtIsxProcessAttribRfcT"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrmtIsxContractChangeRfc", propOrder = {
    "itemGuid",
    "process",
    "activationDate",
    "timezone",
    "processAttributes"
})
public class CrmtIsxContractChangeRfc {

    @XmlElement(name = "ItemGuid", required = true)
    protected byte[] itemGuid;
    @XmlElement(name = "Process", required = true)
    protected String process;
    @XmlElement(name = "ActivationDate", required = true)
    protected BigDecimal activationDate;
    @XmlElement(name = "Timezone", required = true)
    protected String timezone;
    @XmlElement(name = "ProcessAttributes", required = true)
    protected CrmtIsxProcessAttribRfcT processAttributes;

    /**
     * Gets the value of the itemGuid property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getItemGuid() {
        return itemGuid;
    }

    /**
     * Sets the value of the itemGuid property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setItemGuid(byte[] value) {
        this.itemGuid = value;
    }

    /**
     * Gets the value of the process property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcess() {
        return process;
    }

    /**
     * Sets the value of the process property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcess(String value) {
        this.process = value;
    }

    /**
     * Gets the value of the activationDate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getActivationDate() {
        return activationDate;
    }

    /**
     * Sets the value of the activationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setActivationDate(BigDecimal value) {
        this.activationDate = value;
    }

    /**
     * Gets the value of the timezone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Sets the value of the timezone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimezone(String value) {
        this.timezone = value;
    }

    /**
     * Gets the value of the processAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link CrmtIsxProcessAttribRfcT }
     *     
     */
    public CrmtIsxProcessAttribRfcT getProcessAttributes() {
        return processAttributes;
    }

    /**
     * Sets the value of the processAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmtIsxProcessAttribRfcT }
     *     
     */
    public void setProcessAttributes(CrmtIsxProcessAttribRfcT value) {
        this.processAttributes = value;
    }

}
