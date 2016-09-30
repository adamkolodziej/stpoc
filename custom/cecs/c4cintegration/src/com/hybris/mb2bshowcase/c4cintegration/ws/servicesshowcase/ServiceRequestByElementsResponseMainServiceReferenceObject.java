
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ServiceRequestByElementsResponseMainServiceReferenceObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestByElementsResponseMainServiceReferenceObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MaterialID" type="{http://sap.com/xi/AP/Common/GDT}ProductID" minOccurs="0"/>
 *         &lt;element name="IndividualProductSerialID" type="{http://sap.com/xi/AP/Common/GDT}SerialID" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestByElementsResponseMainServiceReferenceObject", propOrder = {
    "materialID",
    "individualProductSerialID"
})
public class ServiceRequestByElementsResponseMainServiceReferenceObject {

    @XmlElement(name = "MaterialID")
    protected ProductID materialID;
    @XmlElement(name = "IndividualProductSerialID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String individualProductSerialID;

    /**
     * Gets the value of the materialID property.
     * 
     * @return
     *     possible object is
     *     {@link ProductID }
     *     
     */
    public ProductID getMaterialID() {
        return materialID;
    }

    /**
     * Sets the value of the materialID property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductID }
     *     
     */
    public void setMaterialID(ProductID value) {
        this.materialID = value;
    }

    /**
     * Gets the value of the individualProductSerialID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndividualProductSerialID() {
        return individualProductSerialID;
    }

    /**
     * Sets the value of the individualProductSerialID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndividualProductSerialID(String value) {
        this.individualProductSerialID = value;
    }

}
