
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServiceRequestByElementsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestByElementsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ChangeStateID" type="{http://sap.com/xi/AP/Common/GDT}ChangeStateID" minOccurs="0"/>
 *         &lt;element name="ID" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentID" minOccurs="0"/>
 *         &lt;element name="UUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/>
 *         &lt;element name="BusinessTransactionDocumentReference" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseBusinessTransactionDocumentReference" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SystemAdministrativeData" type="{http://sap.com/xi/AP/Common/GDT}SystemAdministrativeData" minOccurs="0"/>
 *         &lt;element name="MainBusinessProcessVariantTypeCode" type="{http://sap.com/xi/AP/Common/GDT}BusinessProcessVariantTypeCode" minOccurs="0"/>
 *         &lt;element name="ProcessingTypeCode" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentProcessingTypeCode" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_EXTENDED_Name" minOccurs="0"/>
 *         &lt;element name="DataOriginTypeCode" type="{http://sap.com/xi/AP/Common/GDT}CustomerTransactionDocumentDataOriginTypeCode" minOccurs="0"/>
 *         &lt;element name="LifeCycleStatusCode" type="{http://sap.com/xi/AP/Common/GDT}ServiceRequestLifeCycleStatusCode" minOccurs="0"/>
 *         &lt;element name="EscalationStatusCode" type="{http://sap.com/xi/AP/Common/GDT}EscalationStatusCode" minOccurs="0"/>
 *         &lt;element name="ApprovalStatusCode" type="{http://sap.com/xi/AP/Common/GDT}ApprovalStatusCode" minOccurs="0"/>
 *         &lt;element name="ItemListServiceRequestExecutionLifeCycleStatusCode" type="{http://sap.com/xi/AP/Common/GDT}ServiceRequestExecutionLifeCycleStatusCode" minOccurs="0"/>
 *         &lt;element name="ItemListExecutionReleaseStatusCode" type="{http://sap.com/xi/AP/Common/GDT}ReleaseStatusCode" minOccurs="0"/>
 *         &lt;element name="ItemListFulfilmentProcessingStatusCode" type="{http://sap.com/xi/AP/Common/GDT}ProcessingStatusCode" minOccurs="0"/>
 *         &lt;element name="BuyerParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsBuyerParty" minOccurs="0"/>
 *         &lt;element name="ProcessorParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsProcessorParty" minOccurs="0"/>
 *         &lt;element name="ServicePerformerParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsServicePerformerParty" minOccurs="0"/>
 *         &lt;element name="EndBuyerParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsEndBuyerParty" minOccurs="0"/>
 *         &lt;element name="PartnerParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsPartnerParty" minOccurs="0"/>
 *         &lt;element name="PartnerContactParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponsePartnerContactParty" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ReporterParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsReporterParty" minOccurs="0"/>
 *         &lt;element name="ReportedForParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsReportedForParty" minOccurs="0"/>
 *         &lt;element name="OtherParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsOtherParty" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ServiceSupportTeamParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsServiceSupportTeamParty" minOccurs="0"/>
 *         &lt;element name="ServiceExecutionTeamParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsServiceExecutionTeamParty" minOccurs="0"/>
 *         &lt;element name="SalesUnitParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsSalesUnitParty" minOccurs="0"/>
 *         &lt;element name="SalesAndServiceBusinessArea" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseSalesAndServiceBusinessArea" minOccurs="0"/>
 *         &lt;element name="TimePointTerms" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseTimePointTerms" minOccurs="0"/>
 *         &lt;element name="DurationTerms" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseDurationTerms" minOccurs="0"/>
 *         &lt;element name="RequestedFulfilmentPeriod" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseRequestedFulfilmentPeriod" minOccurs="0"/>
 *         &lt;element name="ServicePointLocation" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsServicePointLocation" minOccurs="0"/>
 *         &lt;element name="ServiceTerms" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseServiceTerms" minOccurs="0"/>
 *         &lt;element name="MainIncidentServiceIssueCategory" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseMainIncidentServiceIssueCategory" minOccurs="0"/>
 *         &lt;element name="MainObjectPartServiceIssueCategory" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseMainObjectPartServiceIssueCategory" minOccurs="0"/>
 *         &lt;element name="MainCauseServiceIssueCategory" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseMainCauseServiceIssueCategory" minOccurs="0"/>
 *         &lt;element name="MainActivityServiceIssueCategory" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseMainActivityServiceIssueCategory" minOccurs="0"/>
 *         &lt;element name="MainServiceReferenceObject" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseMainServiceReferenceObject" minOccurs="0"/>
 *         &lt;element name="SolutionProposal" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseSolutionProposal" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Item" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Text" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsResponseText" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AttachmentFolder" type="{http://sap.com/xi/DocumentServices/Global}AccessAttachmentFolder" minOccurs="0"/>
 *         &lt;group ref="{http://sap.com/xi/AP/CustomerExtension/BYD/A0014}Ext00163E08A7A61ED4A8ABB2C796D3093F"/>
 *         &lt;group ref="{http://sap.com/xi/AP/CustomerExtension/BYD/A0014}Ext00163E08A7A61ED4A8ABB7994E504941"/>
 *         &lt;group ref="{http://sap.com/xi/AP/CustomerExtension/BYD/A0014}Ext00163E08A7A61ED4A8ABC4558B4E09B8"/>
 *         &lt;group ref="{http://sap.com/xi/AP/CustomerExtension/BYD/A0014}Ext00163E08A7A61ED4A8DC6715D6E9B076"/>
 *         &lt;group ref="{http://sap.com/xi/AP/CustomerExtension/BYD/A0014}Ext00163E08A7AF1ED4A8AD2AC957C2D4E6"/>
 *         &lt;group ref="{http://sap.com/xi/AP/CustomerExtension/BYD/A0014}Ext00163E08A7AF1EE4A8DCB753C86C3371"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestByElementsResponse", propOrder = {
    "changeStateID",
    "id",
    "uuid",
    "businessTransactionDocumentReference",
    "systemAdministrativeData",
    "mainBusinessProcessVariantTypeCode",
    "processingTypeCode",
    "name",
    "dataOriginTypeCode",
    "lifeCycleStatusCode",
    "escalationStatusCode",
    "approvalStatusCode",
    "itemListServiceRequestExecutionLifeCycleStatusCode",
    "itemListExecutionReleaseStatusCode",
    "itemListFulfilmentProcessingStatusCode",
    "buyerParty",
    "processorParty",
    "servicePerformerParty",
    "endBuyerParty",
    "partnerParty",
    "partnerContactParty",
    "reporterParty",
    "reportedForParty",
    "otherParty",
    "serviceSupportTeamParty",
    "serviceExecutionTeamParty",
    "salesUnitParty",
    "salesAndServiceBusinessArea",
    "timePointTerms",
    "durationTerms",
    "requestedFulfilmentPeriod",
    "servicePointLocation",
    "serviceTerms",
    "mainIncidentServiceIssueCategory",
    "mainObjectPartServiceIssueCategory",
    "mainCauseServiceIssueCategory",
    "mainActivityServiceIssueCategory",
    "mainServiceReferenceObject",
    "solutionProposal",
    "item",
    "text",
    "attachmentFolder",
    "scheduledStartDate",
    "scheduledStartTime",
    "scheduledEndDate",
    "emergencyOrder",
    "scheduledEndTime",
    "relevantforScheduling"
})
public class ServiceRequestByElementsResponse {

    @XmlElement(name = "ChangeStateID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String changeStateID;
    @XmlElement(name = "ID")
    protected BusinessTransactionDocumentID id;
    @XmlElement(name = "UUID")
    protected UUID uuid;
    @XmlElement(name = "BusinessTransactionDocumentReference")
    protected List<ServiceRequestByElementsResponseBusinessTransactionDocumentReference> businessTransactionDocumentReference;
    @XmlElement(name = "SystemAdministrativeData")
    protected SystemAdministrativeData systemAdministrativeData;
    @XmlElement(name = "MainBusinessProcessVariantTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mainBusinessProcessVariantTypeCode;
    @XmlElement(name = "ProcessingTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String processingTypeCode;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "DataOriginTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String dataOriginTypeCode;
    @XmlElement(name = "LifeCycleStatusCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String lifeCycleStatusCode;
    @XmlElement(name = "EscalationStatusCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String escalationStatusCode;
    @XmlElement(name = "ApprovalStatusCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String approvalStatusCode;
    @XmlElement(name = "ItemListServiceRequestExecutionLifeCycleStatusCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String itemListServiceRequestExecutionLifeCycleStatusCode;
    @XmlElement(name = "ItemListExecutionReleaseStatusCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String itemListExecutionReleaseStatusCode;
    @XmlElement(name = "ItemListFulfilmentProcessingStatusCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String itemListFulfilmentProcessingStatusCode;
    @XmlElement(name = "BuyerParty")
    protected ServiceRequestByElementsBuyerParty buyerParty;
    @XmlElement(name = "ProcessorParty")
    protected ServiceRequestByElementsProcessorParty processorParty;
    @XmlElement(name = "ServicePerformerParty")
    protected ServiceRequestByElementsServicePerformerParty servicePerformerParty;
    @XmlElement(name = "EndBuyerParty")
    protected ServiceRequestByElementsEndBuyerParty endBuyerParty;
    @XmlElement(name = "PartnerParty")
    protected ServiceRequestByElementsPartnerParty partnerParty;
    @XmlElement(name = "PartnerContactParty")
    protected List<ServiceRequestByElementsResponsePartnerContactParty> partnerContactParty;
    @XmlElement(name = "ReporterParty")
    protected ServiceRequestByElementsReporterParty reporterParty;
    @XmlElement(name = "ReportedForParty")
    protected ServiceRequestByElementsReportedForParty reportedForParty;
    @XmlElement(name = "OtherParty")
    protected List<ServiceRequestByElementsOtherParty> otherParty;
    @XmlElement(name = "ServiceSupportTeamParty")
    protected ServiceRequestByElementsServiceSupportTeamParty serviceSupportTeamParty;
    @XmlElement(name = "ServiceExecutionTeamParty")
    protected ServiceRequestByElementsServiceExecutionTeamParty serviceExecutionTeamParty;
    @XmlElement(name = "SalesUnitParty")
    protected ServiceRequestByElementsSalesUnitParty salesUnitParty;
    @XmlElement(name = "SalesAndServiceBusinessArea")
    protected ServiceRequestByElementsResponseSalesAndServiceBusinessArea salesAndServiceBusinessArea;
    @XmlElement(name = "TimePointTerms")
    protected ServiceRequestByElementsResponseTimePointTerms timePointTerms;
    @XmlElement(name = "DurationTerms")
    protected ServiceRequestByElementsResponseDurationTerms durationTerms;
    @XmlElement(name = "RequestedFulfilmentPeriod")
    protected ServiceRequestByElementsResponseRequestedFulfilmentPeriod requestedFulfilmentPeriod;
    @XmlElement(name = "ServicePointLocation")
    protected ServiceRequestByElementsServicePointLocation servicePointLocation;
    @XmlElement(name = "ServiceTerms")
    protected ServiceRequestByElementsResponseServiceTerms serviceTerms;
    @XmlElement(name = "MainIncidentServiceIssueCategory")
    protected ServiceRequestByElementsResponseMainIncidentServiceIssueCategory mainIncidentServiceIssueCategory;
    @XmlElement(name = "MainObjectPartServiceIssueCategory")
    protected ServiceRequestByElementsResponseMainObjectPartServiceIssueCategory mainObjectPartServiceIssueCategory;
    @XmlElement(name = "MainCauseServiceIssueCategory")
    protected ServiceRequestByElementsResponseMainCauseServiceIssueCategory mainCauseServiceIssueCategory;
    @XmlElement(name = "MainActivityServiceIssueCategory")
    protected ServiceRequestByElementsResponseMainActivityServiceIssueCategory mainActivityServiceIssueCategory;
    @XmlElement(name = "MainServiceReferenceObject")
    protected ServiceRequestByElementsResponseMainServiceReferenceObject mainServiceReferenceObject;
    @XmlElement(name = "SolutionProposal")
    protected List<ServiceRequestByElementsResponseSolutionProposal> solutionProposal;
    @XmlElement(name = "Item")
    protected List<ServiceRequestByElementsResponseItem> item;
    @XmlElement(name = "Text")
    protected List<ServiceRequestByElementsResponseText> text;
    @XmlElement(name = "AttachmentFolder")
    protected AccessAttachmentFolder attachmentFolder;
    @XmlElement(name = "ScheduledStartDate", namespace = "http://sap.com/xi/AP/CustomerExtension/BYD/A0014")
    protected XMLGregorianCalendar scheduledStartDate;
    @XmlElement(name = "ScheduledStartTime", namespace = "http://sap.com/xi/AP/CustomerExtension/BYD/A0014")
    protected XMLGregorianCalendar scheduledStartTime;
    @XmlElement(name = "ScheduledEndDate", namespace = "http://sap.com/xi/AP/CustomerExtension/BYD/A0014")
    protected XMLGregorianCalendar scheduledEndDate;
    @XmlElement(name = "EmergencyOrder", namespace = "http://sap.com/xi/AP/CustomerExtension/BYD/A0014")
    protected Boolean emergencyOrder;
    @XmlElement(name = "ScheduledEndTime", namespace = "http://sap.com/xi/AP/CustomerExtension/BYD/A0014")
    protected XMLGregorianCalendar scheduledEndTime;
    @XmlElement(name = "RelevantforScheduling", namespace = "http://sap.com/xi/AP/CustomerExtension/BYD/A0014")
    protected Boolean relevantforScheduling;

    /**
     * Gets the value of the changeStateID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangeStateID() {
        return changeStateID;
    }

    /**
     * Sets the value of the changeStateID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangeStateID(String value) {
        this.changeStateID = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessTransactionDocumentID }
     *     
     */
    public BusinessTransactionDocumentID getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessTransactionDocumentID }
     *     
     */
    public void setID(BusinessTransactionDocumentID value) {
        this.id = value;
    }

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setUUID(UUID value) {
        this.uuid = value;
    }

    /**
     * Gets the value of the businessTransactionDocumentReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the businessTransactionDocumentReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBusinessTransactionDocumentReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceRequestByElementsResponseBusinessTransactionDocumentReference }
     * 
     * 
     */
    public List<ServiceRequestByElementsResponseBusinessTransactionDocumentReference> getBusinessTransactionDocumentReference() {
        if (businessTransactionDocumentReference == null) {
            businessTransactionDocumentReference = new ArrayList<ServiceRequestByElementsResponseBusinessTransactionDocumentReference>();
        }
        return this.businessTransactionDocumentReference;
    }

    /**
     * Gets the value of the systemAdministrativeData property.
     * 
     * @return
     *     possible object is
     *     {@link SystemAdministrativeData }
     *     
     */
    public SystemAdministrativeData getSystemAdministrativeData() {
        return systemAdministrativeData;
    }

    /**
     * Sets the value of the systemAdministrativeData property.
     * 
     * @param value
     *     allowed object is
     *     {@link SystemAdministrativeData }
     *     
     */
    public void setSystemAdministrativeData(SystemAdministrativeData value) {
        this.systemAdministrativeData = value;
    }

    /**
     * Gets the value of the mainBusinessProcessVariantTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainBusinessProcessVariantTypeCode() {
        return mainBusinessProcessVariantTypeCode;
    }

    /**
     * Sets the value of the mainBusinessProcessVariantTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainBusinessProcessVariantTypeCode(String value) {
        this.mainBusinessProcessVariantTypeCode = value;
    }

    /**
     * Gets the value of the processingTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessingTypeCode() {
        return processingTypeCode;
    }

    /**
     * Sets the value of the processingTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessingTypeCode(String value) {
        this.processingTypeCode = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the dataOriginTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataOriginTypeCode() {
        return dataOriginTypeCode;
    }

    /**
     * Sets the value of the dataOriginTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataOriginTypeCode(String value) {
        this.dataOriginTypeCode = value;
    }

    /**
     * Gets the value of the lifeCycleStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLifeCycleStatusCode() {
        return lifeCycleStatusCode;
    }

    /**
     * Sets the value of the lifeCycleStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLifeCycleStatusCode(String value) {
        this.lifeCycleStatusCode = value;
    }

    /**
     * Gets the value of the escalationStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEscalationStatusCode() {
        return escalationStatusCode;
    }

    /**
     * Sets the value of the escalationStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEscalationStatusCode(String value) {
        this.escalationStatusCode = value;
    }

    /**
     * Gets the value of the approvalStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprovalStatusCode() {
        return approvalStatusCode;
    }

    /**
     * Sets the value of the approvalStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprovalStatusCode(String value) {
        this.approvalStatusCode = value;
    }

    /**
     * Gets the value of the itemListServiceRequestExecutionLifeCycleStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemListServiceRequestExecutionLifeCycleStatusCode() {
        return itemListServiceRequestExecutionLifeCycleStatusCode;
    }

    /**
     * Sets the value of the itemListServiceRequestExecutionLifeCycleStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemListServiceRequestExecutionLifeCycleStatusCode(String value) {
        this.itemListServiceRequestExecutionLifeCycleStatusCode = value;
    }

    /**
     * Gets the value of the itemListExecutionReleaseStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemListExecutionReleaseStatusCode() {
        return itemListExecutionReleaseStatusCode;
    }

    /**
     * Sets the value of the itemListExecutionReleaseStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemListExecutionReleaseStatusCode(String value) {
        this.itemListExecutionReleaseStatusCode = value;
    }

    /**
     * Gets the value of the itemListFulfilmentProcessingStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemListFulfilmentProcessingStatusCode() {
        return itemListFulfilmentProcessingStatusCode;
    }

    /**
     * Sets the value of the itemListFulfilmentProcessingStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemListFulfilmentProcessingStatusCode(String value) {
        this.itemListFulfilmentProcessingStatusCode = value;
    }

    /**
     * Gets the value of the buyerParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsBuyerParty }
     *     
     */
    public ServiceRequestByElementsBuyerParty getBuyerParty() {
        return buyerParty;
    }

    /**
     * Sets the value of the buyerParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsBuyerParty }
     *     
     */
    public void setBuyerParty(ServiceRequestByElementsBuyerParty value) {
        this.buyerParty = value;
    }

    /**
     * Gets the value of the processorParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsProcessorParty }
     *     
     */
    public ServiceRequestByElementsProcessorParty getProcessorParty() {
        return processorParty;
    }

    /**
     * Sets the value of the processorParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsProcessorParty }
     *     
     */
    public void setProcessorParty(ServiceRequestByElementsProcessorParty value) {
        this.processorParty = value;
    }

    /**
     * Gets the value of the servicePerformerParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsServicePerformerParty }
     *     
     */
    public ServiceRequestByElementsServicePerformerParty getServicePerformerParty() {
        return servicePerformerParty;
    }

    /**
     * Sets the value of the servicePerformerParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsServicePerformerParty }
     *     
     */
    public void setServicePerformerParty(ServiceRequestByElementsServicePerformerParty value) {
        this.servicePerformerParty = value;
    }

    /**
     * Gets the value of the endBuyerParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsEndBuyerParty }
     *     
     */
    public ServiceRequestByElementsEndBuyerParty getEndBuyerParty() {
        return endBuyerParty;
    }

    /**
     * Sets the value of the endBuyerParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsEndBuyerParty }
     *     
     */
    public void setEndBuyerParty(ServiceRequestByElementsEndBuyerParty value) {
        this.endBuyerParty = value;
    }

    /**
     * Gets the value of the partnerParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsPartnerParty }
     *     
     */
    public ServiceRequestByElementsPartnerParty getPartnerParty() {
        return partnerParty;
    }

    /**
     * Sets the value of the partnerParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsPartnerParty }
     *     
     */
    public void setPartnerParty(ServiceRequestByElementsPartnerParty value) {
        this.partnerParty = value;
    }

    /**
     * Gets the value of the partnerContactParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partnerContactParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartnerContactParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceRequestByElementsResponsePartnerContactParty }
     * 
     * 
     */
    public List<ServiceRequestByElementsResponsePartnerContactParty> getPartnerContactParty() {
        if (partnerContactParty == null) {
            partnerContactParty = new ArrayList<ServiceRequestByElementsResponsePartnerContactParty>();
        }
        return this.partnerContactParty;
    }

    /**
     * Gets the value of the reporterParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsReporterParty }
     *     
     */
    public ServiceRequestByElementsReporterParty getReporterParty() {
        return reporterParty;
    }

    /**
     * Sets the value of the reporterParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsReporterParty }
     *     
     */
    public void setReporterParty(ServiceRequestByElementsReporterParty value) {
        this.reporterParty = value;
    }

    /**
     * Gets the value of the reportedForParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsReportedForParty }
     *     
     */
    public ServiceRequestByElementsReportedForParty getReportedForParty() {
        return reportedForParty;
    }

    /**
     * Sets the value of the reportedForParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsReportedForParty }
     *     
     */
    public void setReportedForParty(ServiceRequestByElementsReportedForParty value) {
        this.reportedForParty = value;
    }

    /**
     * Gets the value of the otherParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otherParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOtherParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceRequestByElementsOtherParty }
     * 
     * 
     */
    public List<ServiceRequestByElementsOtherParty> getOtherParty() {
        if (otherParty == null) {
            otherParty = new ArrayList<ServiceRequestByElementsOtherParty>();
        }
        return this.otherParty;
    }

    /**
     * Gets the value of the serviceSupportTeamParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsServiceSupportTeamParty }
     *     
     */
    public ServiceRequestByElementsServiceSupportTeamParty getServiceSupportTeamParty() {
        return serviceSupportTeamParty;
    }

    /**
     * Sets the value of the serviceSupportTeamParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsServiceSupportTeamParty }
     *     
     */
    public void setServiceSupportTeamParty(ServiceRequestByElementsServiceSupportTeamParty value) {
        this.serviceSupportTeamParty = value;
    }

    /**
     * Gets the value of the serviceExecutionTeamParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsServiceExecutionTeamParty }
     *     
     */
    public ServiceRequestByElementsServiceExecutionTeamParty getServiceExecutionTeamParty() {
        return serviceExecutionTeamParty;
    }

    /**
     * Sets the value of the serviceExecutionTeamParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsServiceExecutionTeamParty }
     *     
     */
    public void setServiceExecutionTeamParty(ServiceRequestByElementsServiceExecutionTeamParty value) {
        this.serviceExecutionTeamParty = value;
    }

    /**
     * Gets the value of the salesUnitParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsSalesUnitParty }
     *     
     */
    public ServiceRequestByElementsSalesUnitParty getSalesUnitParty() {
        return salesUnitParty;
    }

    /**
     * Sets the value of the salesUnitParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsSalesUnitParty }
     *     
     */
    public void setSalesUnitParty(ServiceRequestByElementsSalesUnitParty value) {
        this.salesUnitParty = value;
    }

    /**
     * Gets the value of the salesAndServiceBusinessArea property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsResponseSalesAndServiceBusinessArea }
     *     
     */
    public ServiceRequestByElementsResponseSalesAndServiceBusinessArea getSalesAndServiceBusinessArea() {
        return salesAndServiceBusinessArea;
    }

    /**
     * Sets the value of the salesAndServiceBusinessArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsResponseSalesAndServiceBusinessArea }
     *     
     */
    public void setSalesAndServiceBusinessArea(ServiceRequestByElementsResponseSalesAndServiceBusinessArea value) {
        this.salesAndServiceBusinessArea = value;
    }

    /**
     * Gets the value of the timePointTerms property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsResponseTimePointTerms }
     *     
     */
    public ServiceRequestByElementsResponseTimePointTerms getTimePointTerms() {
        return timePointTerms;
    }

    /**
     * Sets the value of the timePointTerms property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsResponseTimePointTerms }
     *     
     */
    public void setTimePointTerms(ServiceRequestByElementsResponseTimePointTerms value) {
        this.timePointTerms = value;
    }

    /**
     * Gets the value of the durationTerms property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsResponseDurationTerms }
     *     
     */
    public ServiceRequestByElementsResponseDurationTerms getDurationTerms() {
        return durationTerms;
    }

    /**
     * Sets the value of the durationTerms property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsResponseDurationTerms }
     *     
     */
    public void setDurationTerms(ServiceRequestByElementsResponseDurationTerms value) {
        this.durationTerms = value;
    }

    /**
     * Gets the value of the requestedFulfilmentPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsResponseRequestedFulfilmentPeriod }
     *     
     */
    public ServiceRequestByElementsResponseRequestedFulfilmentPeriod getRequestedFulfilmentPeriod() {
        return requestedFulfilmentPeriod;
    }

    /**
     * Sets the value of the requestedFulfilmentPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsResponseRequestedFulfilmentPeriod }
     *     
     */
    public void setRequestedFulfilmentPeriod(ServiceRequestByElementsResponseRequestedFulfilmentPeriod value) {
        this.requestedFulfilmentPeriod = value;
    }

    /**
     * Gets the value of the servicePointLocation property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsServicePointLocation }
     *     
     */
    public ServiceRequestByElementsServicePointLocation getServicePointLocation() {
        return servicePointLocation;
    }

    /**
     * Sets the value of the servicePointLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsServicePointLocation }
     *     
     */
    public void setServicePointLocation(ServiceRequestByElementsServicePointLocation value) {
        this.servicePointLocation = value;
    }

    /**
     * Gets the value of the serviceTerms property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsResponseServiceTerms }
     *     
     */
    public ServiceRequestByElementsResponseServiceTerms getServiceTerms() {
        return serviceTerms;
    }

    /**
     * Sets the value of the serviceTerms property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsResponseServiceTerms }
     *     
     */
    public void setServiceTerms(ServiceRequestByElementsResponseServiceTerms value) {
        this.serviceTerms = value;
    }

    /**
     * Gets the value of the mainIncidentServiceIssueCategory property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsResponseMainIncidentServiceIssueCategory }
     *     
     */
    public ServiceRequestByElementsResponseMainIncidentServiceIssueCategory getMainIncidentServiceIssueCategory() {
        return mainIncidentServiceIssueCategory;
    }

    /**
     * Sets the value of the mainIncidentServiceIssueCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsResponseMainIncidentServiceIssueCategory }
     *     
     */
    public void setMainIncidentServiceIssueCategory(ServiceRequestByElementsResponseMainIncidentServiceIssueCategory value) {
        this.mainIncidentServiceIssueCategory = value;
    }

    /**
     * Gets the value of the mainObjectPartServiceIssueCategory property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsResponseMainObjectPartServiceIssueCategory }
     *     
     */
    public ServiceRequestByElementsResponseMainObjectPartServiceIssueCategory getMainObjectPartServiceIssueCategory() {
        return mainObjectPartServiceIssueCategory;
    }

    /**
     * Sets the value of the mainObjectPartServiceIssueCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsResponseMainObjectPartServiceIssueCategory }
     *     
     */
    public void setMainObjectPartServiceIssueCategory(ServiceRequestByElementsResponseMainObjectPartServiceIssueCategory value) {
        this.mainObjectPartServiceIssueCategory = value;
    }

    /**
     * Gets the value of the mainCauseServiceIssueCategory property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsResponseMainCauseServiceIssueCategory }
     *     
     */
    public ServiceRequestByElementsResponseMainCauseServiceIssueCategory getMainCauseServiceIssueCategory() {
        return mainCauseServiceIssueCategory;
    }

    /**
     * Sets the value of the mainCauseServiceIssueCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsResponseMainCauseServiceIssueCategory }
     *     
     */
    public void setMainCauseServiceIssueCategory(ServiceRequestByElementsResponseMainCauseServiceIssueCategory value) {
        this.mainCauseServiceIssueCategory = value;
    }

    /**
     * Gets the value of the mainActivityServiceIssueCategory property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsResponseMainActivityServiceIssueCategory }
     *     
     */
    public ServiceRequestByElementsResponseMainActivityServiceIssueCategory getMainActivityServiceIssueCategory() {
        return mainActivityServiceIssueCategory;
    }

    /**
     * Sets the value of the mainActivityServiceIssueCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsResponseMainActivityServiceIssueCategory }
     *     
     */
    public void setMainActivityServiceIssueCategory(ServiceRequestByElementsResponseMainActivityServiceIssueCategory value) {
        this.mainActivityServiceIssueCategory = value;
    }

    /**
     * Gets the value of the mainServiceReferenceObject property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestByElementsResponseMainServiceReferenceObject }
     *     
     */
    public ServiceRequestByElementsResponseMainServiceReferenceObject getMainServiceReferenceObject() {
        return mainServiceReferenceObject;
    }

    /**
     * Sets the value of the mainServiceReferenceObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestByElementsResponseMainServiceReferenceObject }
     *     
     */
    public void setMainServiceReferenceObject(ServiceRequestByElementsResponseMainServiceReferenceObject value) {
        this.mainServiceReferenceObject = value;
    }

    /**
     * Gets the value of the solutionProposal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the solutionProposal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSolutionProposal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceRequestByElementsResponseSolutionProposal }
     * 
     * 
     */
    public List<ServiceRequestByElementsResponseSolutionProposal> getSolutionProposal() {
        if (solutionProposal == null) {
            solutionProposal = new ArrayList<ServiceRequestByElementsResponseSolutionProposal>();
        }
        return this.solutionProposal;
    }

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceRequestByElementsResponseItem }
     * 
     * 
     */
    public List<ServiceRequestByElementsResponseItem> getItem() {
        if (item == null) {
            item = new ArrayList<ServiceRequestByElementsResponseItem>();
        }
        return this.item;
    }

    /**
     * Gets the value of the text property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the text property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getText().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceRequestByElementsResponseText }
     * 
     * 
     */
    public List<ServiceRequestByElementsResponseText> getText() {
        if (text == null) {
            text = new ArrayList<ServiceRequestByElementsResponseText>();
        }
        return this.text;
    }

    /**
     * Gets the value of the attachmentFolder property.
     * 
     * @return
     *     possible object is
     *     {@link AccessAttachmentFolder }
     *     
     */
    public AccessAttachmentFolder getAttachmentFolder() {
        return attachmentFolder;
    }

    /**
     * Sets the value of the attachmentFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessAttachmentFolder }
     *     
     */
    public void setAttachmentFolder(AccessAttachmentFolder value) {
        this.attachmentFolder = value;
    }

    /**
     * Gets the value of the scheduledStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getScheduledStartDate() {
        return scheduledStartDate;
    }

    /**
     * Sets the value of the scheduledStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setScheduledStartDate(XMLGregorianCalendar value) {
        this.scheduledStartDate = value;
    }

    /**
     * Gets the value of the scheduledStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getScheduledStartTime() {
        return scheduledStartTime;
    }

    /**
     * Sets the value of the scheduledStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setScheduledStartTime(XMLGregorianCalendar value) {
        this.scheduledStartTime = value;
    }

    /**
     * Gets the value of the scheduledEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getScheduledEndDate() {
        return scheduledEndDate;
    }

    /**
     * Sets the value of the scheduledEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setScheduledEndDate(XMLGregorianCalendar value) {
        this.scheduledEndDate = value;
    }

    /**
     * Gets the value of the emergencyOrder property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEmergencyOrder() {
        return emergencyOrder;
    }

    /**
     * Sets the value of the emergencyOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEmergencyOrder(Boolean value) {
        this.emergencyOrder = value;
    }

    /**
     * Gets the value of the scheduledEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getScheduledEndTime() {
        return scheduledEndTime;
    }

    /**
     * Sets the value of the scheduledEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setScheduledEndTime(XMLGregorianCalendar value) {
        this.scheduledEndTime = value;
    }

    /**
     * Gets the value of the relevantforScheduling property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRelevantforScheduling() {
        return relevantforScheduling;
    }

    /**
     * Sets the value of the relevantforScheduling property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRelevantforScheduling(Boolean value) {
        this.relevantforScheduling = value;
    }

}
