
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ServiceRequestByElementsResponseServiceTerms complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestByElementsResponseServiceTerms">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServicePriorityCode" type="{http://sap.com/xi/AP/Common/GDT}PriorityCode" minOccurs="0"/>
 *         &lt;element name="ServiceIssueCategoryID" type="{http://sap.com/xi/AP/Common/GDT}ServiceIssueCategoryID" minOccurs="0"/>
 *         &lt;element name="WarrantyID" type="{http://sap.com/xi/AP/Common/GDT}ProductID" minOccurs="0"/>
 *         &lt;element name="WarrantyValidityPeriod" type="{http://sap.com/xi/AP/Common/GDT}CLOSED_DatePeriod" minOccurs="0"/>
 *         &lt;element name="ChangedByCustomerIndicator" type="{http://sap.com/xi/Common/DataTypes}Indicator" minOccurs="0"/>
 *         &lt;element name="ServiceExecutionRelevanceIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/>
 *         &lt;element name="ClassificationCode" type="{http://sap.com/xi/Common/DataTypes}ServiceRequestClassificationCode" minOccurs="0"/>
 *         &lt;element name="UserLifeCycleStatusCode" type="{http://sap.com/xi/Common/DataTypes}ServiceRequestUserLifeCycleStatusCode" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestByElementsResponseServiceTerms", propOrder = {
    "servicePriorityCode",
    "serviceIssueCategoryID",
    "warrantyID",
    "warrantyValidityPeriod",
    "changedByCustomerIndicator",
    "serviceExecutionRelevanceIndicator",
    "classificationCode",
    "userLifeCycleStatusCode"
})
public class ServiceRequestByElementsResponseServiceTerms {

    @XmlElement(name = "ServicePriorityCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String servicePriorityCode;
    @XmlElement(name = "ServiceIssueCategoryID")
    protected ServiceIssueCategoryID serviceIssueCategoryID;
    @XmlElement(name = "WarrantyID")
    protected ProductID warrantyID;
    @XmlElement(name = "WarrantyValidityPeriod")
    protected CLOSEDDatePeriod warrantyValidityPeriod;
    @XmlElement(name = "ChangedByCustomerIndicator")
    protected Boolean changedByCustomerIndicator;
    @XmlElement(name = "ServiceExecutionRelevanceIndicator")
    protected Boolean serviceExecutionRelevanceIndicator;
    @XmlElement(name = "ClassificationCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String classificationCode;
    @XmlElement(name = "UserLifeCycleStatusCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String userLifeCycleStatusCode;

    /**
     * Gets the value of the servicePriorityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicePriorityCode() {
        return servicePriorityCode;
    }

    /**
     * Sets the value of the servicePriorityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicePriorityCode(String value) {
        this.servicePriorityCode = value;
    }

    /**
     * Gets the value of the serviceIssueCategoryID property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceIssueCategoryID }
     *     
     */
    public ServiceIssueCategoryID getServiceIssueCategoryID() {
        return serviceIssueCategoryID;
    }

    /**
     * Sets the value of the serviceIssueCategoryID property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceIssueCategoryID }
     *     
     */
    public void setServiceIssueCategoryID(ServiceIssueCategoryID value) {
        this.serviceIssueCategoryID = value;
    }

    /**
     * Gets the value of the warrantyID property.
     * 
     * @return
     *     possible object is
     *     {@link ProductID }
     *     
     */
    public ProductID getWarrantyID() {
        return warrantyID;
    }

    /**
     * Sets the value of the warrantyID property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductID }
     *     
     */
    public void setWarrantyID(ProductID value) {
        this.warrantyID = value;
    }

    /**
     * Gets the value of the warrantyValidityPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link CLOSEDDatePeriod }
     *     
     */
    public CLOSEDDatePeriod getWarrantyValidityPeriod() {
        return warrantyValidityPeriod;
    }

    /**
     * Sets the value of the warrantyValidityPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link CLOSEDDatePeriod }
     *     
     */
    public void setWarrantyValidityPeriod(CLOSEDDatePeriod value) {
        this.warrantyValidityPeriod = value;
    }

    /**
     * Gets the value of the changedByCustomerIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChangedByCustomerIndicator() {
        return changedByCustomerIndicator;
    }

    /**
     * Sets the value of the changedByCustomerIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChangedByCustomerIndicator(Boolean value) {
        this.changedByCustomerIndicator = value;
    }

    /**
     * Gets the value of the serviceExecutionRelevanceIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isServiceExecutionRelevanceIndicator() {
        return serviceExecutionRelevanceIndicator;
    }

    /**
     * Sets the value of the serviceExecutionRelevanceIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setServiceExecutionRelevanceIndicator(Boolean value) {
        this.serviceExecutionRelevanceIndicator = value;
    }

    /**
     * Gets the value of the classificationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassificationCode() {
        return classificationCode;
    }

    /**
     * Sets the value of the classificationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassificationCode(String value) {
        this.classificationCode = value;
    }

    /**
     * Gets the value of the userLifeCycleStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserLifeCycleStatusCode() {
        return userLifeCycleStatusCode;
    }

    /**
     * Sets the value of the userLifeCycleStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserLifeCycleStatusCode(String value) {
        this.userLifeCycleStatusCode = value;
    }

}
