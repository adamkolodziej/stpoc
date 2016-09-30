
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SystemAdministrativeData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SystemAdministrativeData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CreationDateTime" type="{http://sap.com/xi/BASIS/Global}GLOBAL_DateTime"/>
 *         &lt;element name="CreationIdentityUUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/>
 *         &lt;element name="LastChangeDateTime" type="{http://sap.com/xi/BASIS/Global}GLOBAL_DateTime" minOccurs="0"/>
 *         &lt;element name="LastChangeIdentityUUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SystemAdministrativeData", namespace = "http://sap.com/xi/AP/Common/GDT", propOrder = {
    "creationDateTime",
    "creationIdentityUUID",
    "lastChangeDateTime",
    "lastChangeIdentityUUID"
})
public class SystemAdministrativeData {

    @XmlElement(name = "CreationDateTime", required = true)
    protected XMLGregorianCalendar creationDateTime;
    @XmlElement(name = "CreationIdentityUUID")
    protected UUID creationIdentityUUID;
    @XmlElement(name = "LastChangeDateTime")
    protected XMLGregorianCalendar lastChangeDateTime;
    @XmlElement(name = "LastChangeIdentityUUID")
    protected UUID lastChangeIdentityUUID;

    /**
     * Gets the value of the creationDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDateTime() {
        return creationDateTime;
    }

    /**
     * Sets the value of the creationDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDateTime(XMLGregorianCalendar value) {
        this.creationDateTime = value;
    }

    /**
     * Gets the value of the creationIdentityUUID property.
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getCreationIdentityUUID() {
        return creationIdentityUUID;
    }

    /**
     * Sets the value of the creationIdentityUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setCreationIdentityUUID(UUID value) {
        this.creationIdentityUUID = value;
    }

    /**
     * Gets the value of the lastChangeDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastChangeDateTime() {
        return lastChangeDateTime;
    }

    /**
     * Sets the value of the lastChangeDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastChangeDateTime(XMLGregorianCalendar value) {
        this.lastChangeDateTime = value;
    }

    /**
     * Gets the value of the lastChangeIdentityUUID property.
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getLastChangeIdentityUUID() {
        return lastChangeIdentityUUID;
    }

    /**
     * Sets the value of the lastChangeIdentityUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setLastChangeIdentityUUID(UUID value) {
        this.lastChangeIdentityUUID = value;
    }

}
