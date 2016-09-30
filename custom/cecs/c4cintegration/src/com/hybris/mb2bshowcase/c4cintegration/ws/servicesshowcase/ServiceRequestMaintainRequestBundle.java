
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServiceRequestMaintainRequestBundle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestMaintainRequestBundle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID" minOccurs="0"/>
 *         &lt;element name="ChangeStateID" type="{http://sap.com/xi/AP/Common/GDT}ChangeStateID" minOccurs="0"/>
 *         &lt;element name="ID" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentID" minOccurs="0"/>
 *         &lt;element name="UUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/>
 *         &lt;element name="BusinessTransactionDocumentReference" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleBusinessTransactionDocumentReference" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ProcessingTypeCode" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentProcessingTypeCode" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_EXTENDED_Name" minOccurs="0"/>
 *         &lt;element name="DataOriginTypeCode" type="{http://sap.com/xi/AP/Common/GDT}CustomerTransactionDocumentDataOriginTypeCode" minOccurs="0"/>
 *         &lt;element name="LifeCycleStatusCode" type="{http://sap.com/xi/AP/Common/GDT}ServiceRequestLifeCycleStatusCode" minOccurs="0"/>
 *         &lt;element name="EscalationStatusCode" type="{http://sap.com/xi/AP/Common/GDT}EscalationStatusCode" minOccurs="0"/>
 *         &lt;element name="ApprovalStatusCode" type="{http://sap.com/xi/AP/Common/GDT}ApprovalStatusCode" minOccurs="0"/>
 *         &lt;element name="BuyerParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleBuyerParty" minOccurs="0"/>
 *         &lt;element name="EndBuyerParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleEndBuyerParty" minOccurs="0"/>
 *         &lt;element name="PartnerParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundlePartnerParty" minOccurs="0"/>
 *         &lt;element name="PartnerContactParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundlePartnerContactParty" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ReporterParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleReporterParty" minOccurs="0"/>
 *         &lt;element name="ReportedForParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleReportedForParty" minOccurs="0"/>
 *         &lt;element name="ProcessorParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleProcessorParty" minOccurs="0"/>
 *         &lt;element name="ServicePerformerParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleServicePerformerParty" minOccurs="0"/>
 *         &lt;element name="OtherParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleOtherParty" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ServiceSupportTeamParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleServiceSupportTeamParty" minOccurs="0"/>
 *         &lt;element name="ServiceExecutionTeamParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleServiceExecutionTeamParty" minOccurs="0"/>
 *         &lt;element name="SalesUnitParty" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleSalesUnitParty" minOccurs="0"/>
 *         &lt;element name="SalesAndServiceBusinessArea" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleSalesAndServiceBusinessArea" minOccurs="0"/>
 *         &lt;element name="TimePointTerms" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleTimePointTerms" minOccurs="0"/>
 *         &lt;element name="DurationTerms" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleDurationTerms" minOccurs="0"/>
 *         &lt;element name="RequestedFulfilmentPeriod" type="{http://sap.com/xi/AP/Common/GDT}UPPEROPEN_LOCALNORMALISED_DateTimePeriod" minOccurs="0"/>
 *         &lt;element name="ServiceTerms" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleServiceTerms" minOccurs="0"/>
 *         &lt;element name="MainIncidentServiceIssueCategory" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleMainIncidentServiceIssueCategory" minOccurs="0"/>
 *         &lt;element name="MainObjectPartServiceIssueCategory" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleMainObjectPartServiceIssueCategory" minOccurs="0"/>
 *         &lt;element name="MainCauseServiceIssueCategory" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleMainCauseServiceIssueCategory" minOccurs="0"/>
 *         &lt;element name="MainActivityServiceIssueCategory" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleMainActivityServiceIssueCategory" minOccurs="0"/>
 *         &lt;element name="MainServiceReferenceObject" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleMainServiceReferenceObject" minOccurs="0"/>
 *         &lt;element name="SolutionProposal" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleSolutionProposal" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Item" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Text" type="{http://sap.com/xi/A1S/Global}ServiceRequestMaintainRequestBundleText" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AttachmentFolder" type="{http://sap.com/xi/DocumentServices/Global}MaintenanceAttachmentFolder" minOccurs="0"/>
 *         &lt;group ref="{http://sap.com/xi/AP/CustomerExtension/BYD/A0014}Ext00163E08A7A61ED4A8ABAE74F96F8935"/>
 *         &lt;group ref="{http://sap.com/xi/AP/CustomerExtension/BYD/A0014}Ext00163E08A7A61ED4A8ABB53E2882C940"/>
 *         &lt;group ref="{http://sap.com/xi/AP/CustomerExtension/BYD/A0014}Ext00163E08A7A61ED4A8ABC1C3511589AE"/>
 *         &lt;group ref="{http://sap.com/xi/AP/CustomerExtension/BYD/A0014}Ext00163E08A7AF1ED4A8AD2834ED3D94E4"/>
 *       &lt;/sequence>
 *       &lt;attribute name="actionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" />
 *       &lt;attribute name="businessTransactionDocumentReferenceListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" />
 *       &lt;attribute name="partnerContactPartyListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" />
 *       &lt;attribute name="otherPartyListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" />
 *       &lt;attribute name="solutionProposalListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" />
 *       &lt;attribute name="itemListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" />
 *       &lt;attribute name="textListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestMaintainRequestBundle", propOrder = {
    "objectNodeSenderTechnicalID",
    "changeStateID",
    "id",
    "uuid",
    "businessTransactionDocumentReference",
    "processingTypeCode",
    "name",
    "dataOriginTypeCode",
    "lifeCycleStatusCode",
    "escalationStatusCode",
    "approvalStatusCode",
    "buyerParty",
    "endBuyerParty",
    "partnerParty",
    "partnerContactParty",
    "reporterParty",
    "reportedForParty",
    "processorParty",
    "servicePerformerParty",
    "otherParty",
    "serviceSupportTeamParty",
    "serviceExecutionTeamParty",
    "salesUnitParty",
    "salesAndServiceBusinessArea",
    "timePointTerms",
    "durationTerms",
    "requestedFulfilmentPeriod",
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
    "scheduledEndTime"
})
public class ServiceRequestMaintainRequestBundle {

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String objectNodeSenderTechnicalID;
    @XmlElement(name = "ChangeStateID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String changeStateID;
    @XmlElement(name = "ID")
    protected BusinessTransactionDocumentID id;
    @XmlElement(name = "UUID")
    protected UUID uuid;
    @XmlElement(name = "BusinessTransactionDocumentReference")
    protected List<ServiceRequestMaintainRequestBundleBusinessTransactionDocumentReference> businessTransactionDocumentReference;
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
    @XmlElement(name = "BuyerParty")
    protected ServiceRequestMaintainRequestBundleBuyerParty buyerParty;
    @XmlElement(name = "EndBuyerParty")
    protected ServiceRequestMaintainRequestBundleEndBuyerParty endBuyerParty;
    @XmlElement(name = "PartnerParty")
    protected ServiceRequestMaintainRequestBundlePartnerParty partnerParty;
    @XmlElement(name = "PartnerContactParty")
    protected List<ServiceRequestMaintainRequestBundlePartnerContactParty> partnerContactParty;
    @XmlElement(name = "ReporterParty")
    protected ServiceRequestMaintainRequestBundleReporterParty reporterParty;
    @XmlElement(name = "ReportedForParty")
    protected ServiceRequestMaintainRequestBundleReportedForParty reportedForParty;
    @XmlElement(name = "ProcessorParty")
    protected ServiceRequestMaintainRequestBundleProcessorParty processorParty;
    @XmlElement(name = "ServicePerformerParty")
    protected ServiceRequestMaintainRequestBundleServicePerformerParty servicePerformerParty;
    @XmlElement(name = "OtherParty")
    protected List<ServiceRequestMaintainRequestBundleOtherParty> otherParty;
    @XmlElement(name = "ServiceSupportTeamParty")
    protected ServiceRequestMaintainRequestBundleServiceSupportTeamParty serviceSupportTeamParty;
    @XmlElement(name = "ServiceExecutionTeamParty")
    protected ServiceRequestMaintainRequestBundleServiceExecutionTeamParty serviceExecutionTeamParty;
    @XmlElement(name = "SalesUnitParty")
    protected ServiceRequestMaintainRequestBundleSalesUnitParty salesUnitParty;
    @XmlElement(name = "SalesAndServiceBusinessArea")
    protected ServiceRequestMaintainRequestBundleSalesAndServiceBusinessArea salesAndServiceBusinessArea;
    @XmlElement(name = "TimePointTerms")
    protected ServiceRequestMaintainRequestBundleTimePointTerms timePointTerms;
    @XmlElement(name = "DurationTerms")
    protected ServiceRequestMaintainRequestBundleDurationTerms durationTerms;
    @XmlElement(name = "RequestedFulfilmentPeriod")
    protected UPPEROPENLOCALNORMALISEDDateTimePeriod requestedFulfilmentPeriod;
    @XmlElement(name = "ServiceTerms")
    protected ServiceRequestMaintainRequestBundleServiceTerms serviceTerms;
    @XmlElement(name = "MainIncidentServiceIssueCategory")
    protected ServiceRequestMaintainRequestBundleMainIncidentServiceIssueCategory mainIncidentServiceIssueCategory;
    @XmlElement(name = "MainObjectPartServiceIssueCategory")
    protected ServiceRequestMaintainRequestBundleMainObjectPartServiceIssueCategory mainObjectPartServiceIssueCategory;
    @XmlElement(name = "MainCauseServiceIssueCategory")
    protected ServiceRequestMaintainRequestBundleMainCauseServiceIssueCategory mainCauseServiceIssueCategory;
    @XmlElement(name = "MainActivityServiceIssueCategory")
    protected ServiceRequestMaintainRequestBundleMainActivityServiceIssueCategory mainActivityServiceIssueCategory;
    @XmlElement(name = "MainServiceReferenceObject")
    protected ServiceRequestMaintainRequestBundleMainServiceReferenceObject mainServiceReferenceObject;
    @XmlElement(name = "SolutionProposal")
    protected List<ServiceRequestMaintainRequestBundleSolutionProposal> solutionProposal;
    @XmlElement(name = "Item")
    protected List<ServiceRequestMaintainRequestBundleItem> item;
    @XmlElement(name = "Text")
    protected List<ServiceRequestMaintainRequestBundleText> text;
    @XmlElement(name = "AttachmentFolder")
    protected MaintenanceAttachmentFolder attachmentFolder;
    @XmlElement(name = "ScheduledStartDate", namespace = "http://sap.com/xi/AP/CustomerExtension/BYD/A0014")
    protected XMLGregorianCalendar scheduledStartDate;
    @XmlElement(name = "ScheduledStartTime", namespace = "http://sap.com/xi/AP/CustomerExtension/BYD/A0014")
    protected XMLGregorianCalendar scheduledStartTime;
    @XmlElement(name = "ScheduledEndDate", namespace = "http://sap.com/xi/AP/CustomerExtension/BYD/A0014")
    protected XMLGregorianCalendar scheduledEndDate;
    @XmlElement(name = "ScheduledEndTime", namespace = "http://sap.com/xi/AP/CustomerExtension/BYD/A0014")
    protected XMLGregorianCalendar scheduledEndTime;
    @XmlAttribute(name = "actionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;
    @XmlAttribute(name = "businessTransactionDocumentReferenceListCompleteTransmissionIndicator")
    protected Boolean businessTransactionDocumentReferenceListCompleteTransmissionIndicator;
    @XmlAttribute(name = "partnerContactPartyListCompleteTransmissionIndicator")
    protected Boolean partnerContactPartyListCompleteTransmissionIndicator;
    @XmlAttribute(name = "otherPartyListCompleteTransmissionIndicator")
    protected Boolean otherPartyListCompleteTransmissionIndicator;
    @XmlAttribute(name = "solutionProposalListCompleteTransmissionIndicator")
    protected Boolean solutionProposalListCompleteTransmissionIndicator;
    @XmlAttribute(name = "itemListCompleteTransmissionIndicator")
    protected Boolean itemListCompleteTransmissionIndicator;
    @XmlAttribute(name = "textListCompleteTransmissionIndicator")
    protected Boolean textListCompleteTransmissionIndicator;

    /**
     * Gets the value of the objectNodeSenderTechnicalID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectNodeSenderTechnicalID() {
        return objectNodeSenderTechnicalID;
    }

    /**
     * Sets the value of the objectNodeSenderTechnicalID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectNodeSenderTechnicalID(String value) {
        this.objectNodeSenderTechnicalID = value;
    }

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
     * {@link ServiceRequestMaintainRequestBundleBusinessTransactionDocumentReference }
     * 
     * 
     */
    public List<ServiceRequestMaintainRequestBundleBusinessTransactionDocumentReference> getBusinessTransactionDocumentReference() {
        if (businessTransactionDocumentReference == null) {
            businessTransactionDocumentReference = new ArrayList<ServiceRequestMaintainRequestBundleBusinessTransactionDocumentReference>();
        }
        return this.businessTransactionDocumentReference;
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
     * Gets the value of the buyerParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleBuyerParty }
     *     
     */
    public ServiceRequestMaintainRequestBundleBuyerParty getBuyerParty() {
        return buyerParty;
    }

    /**
     * Sets the value of the buyerParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleBuyerParty }
     *     
     */
    public void setBuyerParty(ServiceRequestMaintainRequestBundleBuyerParty value) {
        this.buyerParty = value;
    }

    /**
     * Gets the value of the endBuyerParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleEndBuyerParty }
     *     
     */
    public ServiceRequestMaintainRequestBundleEndBuyerParty getEndBuyerParty() {
        return endBuyerParty;
    }

    /**
     * Sets the value of the endBuyerParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleEndBuyerParty }
     *     
     */
    public void setEndBuyerParty(ServiceRequestMaintainRequestBundleEndBuyerParty value) {
        this.endBuyerParty = value;
    }

    /**
     * Gets the value of the partnerParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundlePartnerParty }
     *     
     */
    public ServiceRequestMaintainRequestBundlePartnerParty getPartnerParty() {
        return partnerParty;
    }

    /**
     * Sets the value of the partnerParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundlePartnerParty }
     *     
     */
    public void setPartnerParty(ServiceRequestMaintainRequestBundlePartnerParty value) {
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
     * {@link ServiceRequestMaintainRequestBundlePartnerContactParty }
     * 
     * 
     */
    public List<ServiceRequestMaintainRequestBundlePartnerContactParty> getPartnerContactParty() {
        if (partnerContactParty == null) {
            partnerContactParty = new ArrayList<ServiceRequestMaintainRequestBundlePartnerContactParty>();
        }
        return this.partnerContactParty;
    }

    /**
     * Gets the value of the reporterParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleReporterParty }
     *     
     */
    public ServiceRequestMaintainRequestBundleReporterParty getReporterParty() {
        return reporterParty;
    }

    /**
     * Sets the value of the reporterParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleReporterParty }
     *     
     */
    public void setReporterParty(ServiceRequestMaintainRequestBundleReporterParty value) {
        this.reporterParty = value;
    }

    /**
     * Gets the value of the reportedForParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleReportedForParty }
     *     
     */
    public ServiceRequestMaintainRequestBundleReportedForParty getReportedForParty() {
        return reportedForParty;
    }

    /**
     * Sets the value of the reportedForParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleReportedForParty }
     *     
     */
    public void setReportedForParty(ServiceRequestMaintainRequestBundleReportedForParty value) {
        this.reportedForParty = value;
    }

    /**
     * Gets the value of the processorParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleProcessorParty }
     *     
     */
    public ServiceRequestMaintainRequestBundleProcessorParty getProcessorParty() {
        return processorParty;
    }

    /**
     * Sets the value of the processorParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleProcessorParty }
     *     
     */
    public void setProcessorParty(ServiceRequestMaintainRequestBundleProcessorParty value) {
        this.processorParty = value;
    }

    /**
     * Gets the value of the servicePerformerParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleServicePerformerParty }
     *     
     */
    public ServiceRequestMaintainRequestBundleServicePerformerParty getServicePerformerParty() {
        return servicePerformerParty;
    }

    /**
     * Sets the value of the servicePerformerParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleServicePerformerParty }
     *     
     */
    public void setServicePerformerParty(ServiceRequestMaintainRequestBundleServicePerformerParty value) {
        this.servicePerformerParty = value;
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
     * {@link ServiceRequestMaintainRequestBundleOtherParty }
     * 
     * 
     */
    public List<ServiceRequestMaintainRequestBundleOtherParty> getOtherParty() {
        if (otherParty == null) {
            otherParty = new ArrayList<ServiceRequestMaintainRequestBundleOtherParty>();
        }
        return this.otherParty;
    }

    /**
     * Gets the value of the serviceSupportTeamParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleServiceSupportTeamParty }
     *     
     */
    public ServiceRequestMaintainRequestBundleServiceSupportTeamParty getServiceSupportTeamParty() {
        return serviceSupportTeamParty;
    }

    /**
     * Sets the value of the serviceSupportTeamParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleServiceSupportTeamParty }
     *     
     */
    public void setServiceSupportTeamParty(ServiceRequestMaintainRequestBundleServiceSupportTeamParty value) {
        this.serviceSupportTeamParty = value;
    }

    /**
     * Gets the value of the serviceExecutionTeamParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleServiceExecutionTeamParty }
     *     
     */
    public ServiceRequestMaintainRequestBundleServiceExecutionTeamParty getServiceExecutionTeamParty() {
        return serviceExecutionTeamParty;
    }

    /**
     * Sets the value of the serviceExecutionTeamParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleServiceExecutionTeamParty }
     *     
     */
    public void setServiceExecutionTeamParty(ServiceRequestMaintainRequestBundleServiceExecutionTeamParty value) {
        this.serviceExecutionTeamParty = value;
    }

    /**
     * Gets the value of the salesUnitParty property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleSalesUnitParty }
     *     
     */
    public ServiceRequestMaintainRequestBundleSalesUnitParty getSalesUnitParty() {
        return salesUnitParty;
    }

    /**
     * Sets the value of the salesUnitParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleSalesUnitParty }
     *     
     */
    public void setSalesUnitParty(ServiceRequestMaintainRequestBundleSalesUnitParty value) {
        this.salesUnitParty = value;
    }

    /**
     * Gets the value of the salesAndServiceBusinessArea property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleSalesAndServiceBusinessArea }
     *     
     */
    public ServiceRequestMaintainRequestBundleSalesAndServiceBusinessArea getSalesAndServiceBusinessArea() {
        return salesAndServiceBusinessArea;
    }

    /**
     * Sets the value of the salesAndServiceBusinessArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleSalesAndServiceBusinessArea }
     *     
     */
    public void setSalesAndServiceBusinessArea(ServiceRequestMaintainRequestBundleSalesAndServiceBusinessArea value) {
        this.salesAndServiceBusinessArea = value;
    }

    /**
     * Gets the value of the timePointTerms property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleTimePointTerms }
     *     
     */
    public ServiceRequestMaintainRequestBundleTimePointTerms getTimePointTerms() {
        return timePointTerms;
    }

    /**
     * Sets the value of the timePointTerms property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleTimePointTerms }
     *     
     */
    public void setTimePointTerms(ServiceRequestMaintainRequestBundleTimePointTerms value) {
        this.timePointTerms = value;
    }

    /**
     * Gets the value of the durationTerms property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleDurationTerms }
     *     
     */
    public ServiceRequestMaintainRequestBundleDurationTerms getDurationTerms() {
        return durationTerms;
    }

    /**
     * Sets the value of the durationTerms property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleDurationTerms }
     *     
     */
    public void setDurationTerms(ServiceRequestMaintainRequestBundleDurationTerms value) {
        this.durationTerms = value;
    }

    /**
     * Gets the value of the requestedFulfilmentPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link UPPEROPENLOCALNORMALISEDDateTimePeriod }
     *     
     */
    public UPPEROPENLOCALNORMALISEDDateTimePeriod getRequestedFulfilmentPeriod() {
        return requestedFulfilmentPeriod;
    }

    /**
     * Sets the value of the requestedFulfilmentPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link UPPEROPENLOCALNORMALISEDDateTimePeriod }
     *     
     */
    public void setRequestedFulfilmentPeriod(UPPEROPENLOCALNORMALISEDDateTimePeriod value) {
        this.requestedFulfilmentPeriod = value;
    }

    /**
     * Gets the value of the serviceTerms property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleServiceTerms }
     *     
     */
    public ServiceRequestMaintainRequestBundleServiceTerms getServiceTerms() {
        return serviceTerms;
    }

    /**
     * Sets the value of the serviceTerms property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleServiceTerms }
     *     
     */
    public void setServiceTerms(ServiceRequestMaintainRequestBundleServiceTerms value) {
        this.serviceTerms = value;
    }

    /**
     * Gets the value of the mainIncidentServiceIssueCategory property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleMainIncidentServiceIssueCategory }
     *     
     */
    public ServiceRequestMaintainRequestBundleMainIncidentServiceIssueCategory getMainIncidentServiceIssueCategory() {
        return mainIncidentServiceIssueCategory;
    }

    /**
     * Sets the value of the mainIncidentServiceIssueCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleMainIncidentServiceIssueCategory }
     *     
     */
    public void setMainIncidentServiceIssueCategory(ServiceRequestMaintainRequestBundleMainIncidentServiceIssueCategory value) {
        this.mainIncidentServiceIssueCategory = value;
    }

    /**
     * Gets the value of the mainObjectPartServiceIssueCategory property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleMainObjectPartServiceIssueCategory }
     *     
     */
    public ServiceRequestMaintainRequestBundleMainObjectPartServiceIssueCategory getMainObjectPartServiceIssueCategory() {
        return mainObjectPartServiceIssueCategory;
    }

    /**
     * Sets the value of the mainObjectPartServiceIssueCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleMainObjectPartServiceIssueCategory }
     *     
     */
    public void setMainObjectPartServiceIssueCategory(ServiceRequestMaintainRequestBundleMainObjectPartServiceIssueCategory value) {
        this.mainObjectPartServiceIssueCategory = value;
    }

    /**
     * Gets the value of the mainCauseServiceIssueCategory property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleMainCauseServiceIssueCategory }
     *     
     */
    public ServiceRequestMaintainRequestBundleMainCauseServiceIssueCategory getMainCauseServiceIssueCategory() {
        return mainCauseServiceIssueCategory;
    }

    /**
     * Sets the value of the mainCauseServiceIssueCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleMainCauseServiceIssueCategory }
     *     
     */
    public void setMainCauseServiceIssueCategory(ServiceRequestMaintainRequestBundleMainCauseServiceIssueCategory value) {
        this.mainCauseServiceIssueCategory = value;
    }

    /**
     * Gets the value of the mainActivityServiceIssueCategory property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleMainActivityServiceIssueCategory }
     *     
     */
    public ServiceRequestMaintainRequestBundleMainActivityServiceIssueCategory getMainActivityServiceIssueCategory() {
        return mainActivityServiceIssueCategory;
    }

    /**
     * Sets the value of the mainActivityServiceIssueCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleMainActivityServiceIssueCategory }
     *     
     */
    public void setMainActivityServiceIssueCategory(ServiceRequestMaintainRequestBundleMainActivityServiceIssueCategory value) {
        this.mainActivityServiceIssueCategory = value;
    }

    /**
     * Gets the value of the mainServiceReferenceObject property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequestMaintainRequestBundleMainServiceReferenceObject }
     *     
     */
    public ServiceRequestMaintainRequestBundleMainServiceReferenceObject getMainServiceReferenceObject() {
        return mainServiceReferenceObject;
    }

    /**
     * Sets the value of the mainServiceReferenceObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequestMaintainRequestBundleMainServiceReferenceObject }
     *     
     */
    public void setMainServiceReferenceObject(ServiceRequestMaintainRequestBundleMainServiceReferenceObject value) {
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
     * {@link ServiceRequestMaintainRequestBundleSolutionProposal }
     * 
     * 
     */
    public List<ServiceRequestMaintainRequestBundleSolutionProposal> getSolutionProposal() {
        if (solutionProposal == null) {
            solutionProposal = new ArrayList<ServiceRequestMaintainRequestBundleSolutionProposal>();
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
     * {@link ServiceRequestMaintainRequestBundleItem }
     * 
     * 
     */
    public List<ServiceRequestMaintainRequestBundleItem> getItem() {
        if (item == null) {
            item = new ArrayList<ServiceRequestMaintainRequestBundleItem>();
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
     * {@link ServiceRequestMaintainRequestBundleText }
     * 
     * 
     */
    public List<ServiceRequestMaintainRequestBundleText> getText() {
        if (text == null) {
            text = new ArrayList<ServiceRequestMaintainRequestBundleText>();
        }
        return this.text;
    }

    /**
     * Gets the value of the attachmentFolder property.
     * 
     * @return
     *     possible object is
     *     {@link MaintenanceAttachmentFolder }
     *     
     */
    public MaintenanceAttachmentFolder getAttachmentFolder() {
        return attachmentFolder;
    }

    /**
     * Sets the value of the attachmentFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link MaintenanceAttachmentFolder }
     *     
     */
    public void setAttachmentFolder(MaintenanceAttachmentFolder value) {
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
     * Gets the value of the actionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionCode() {
        return actionCode;
    }

    /**
     * Sets the value of the actionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionCode(String value) {
        this.actionCode = value;
    }

    /**
     * Gets the value of the businessTransactionDocumentReferenceListCompleteTransmissionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBusinessTransactionDocumentReferenceListCompleteTransmissionIndicator() {
        return businessTransactionDocumentReferenceListCompleteTransmissionIndicator;
    }

    /**
     * Sets the value of the businessTransactionDocumentReferenceListCompleteTransmissionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBusinessTransactionDocumentReferenceListCompleteTransmissionIndicator(Boolean value) {
        this.businessTransactionDocumentReferenceListCompleteTransmissionIndicator = value;
    }

    /**
     * Gets the value of the partnerContactPartyListCompleteTransmissionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPartnerContactPartyListCompleteTransmissionIndicator() {
        return partnerContactPartyListCompleteTransmissionIndicator;
    }

    /**
     * Sets the value of the partnerContactPartyListCompleteTransmissionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPartnerContactPartyListCompleteTransmissionIndicator(Boolean value) {
        this.partnerContactPartyListCompleteTransmissionIndicator = value;
    }

    /**
     * Gets the value of the otherPartyListCompleteTransmissionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOtherPartyListCompleteTransmissionIndicator() {
        return otherPartyListCompleteTransmissionIndicator;
    }

    /**
     * Sets the value of the otherPartyListCompleteTransmissionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOtherPartyListCompleteTransmissionIndicator(Boolean value) {
        this.otherPartyListCompleteTransmissionIndicator = value;
    }

    /**
     * Gets the value of the solutionProposalListCompleteTransmissionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSolutionProposalListCompleteTransmissionIndicator() {
        return solutionProposalListCompleteTransmissionIndicator;
    }

    /**
     * Sets the value of the solutionProposalListCompleteTransmissionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSolutionProposalListCompleteTransmissionIndicator(Boolean value) {
        this.solutionProposalListCompleteTransmissionIndicator = value;
    }

    /**
     * Gets the value of the itemListCompleteTransmissionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isItemListCompleteTransmissionIndicator() {
        return itemListCompleteTransmissionIndicator;
    }

    /**
     * Sets the value of the itemListCompleteTransmissionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setItemListCompleteTransmissionIndicator(Boolean value) {
        this.itemListCompleteTransmissionIndicator = value;
    }

    /**
     * Gets the value of the textListCompleteTransmissionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTextListCompleteTransmissionIndicator() {
        return textListCompleteTransmissionIndicator;
    }

    /**
     * Sets the value of the textListCompleteTransmissionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTextListCompleteTransmissionIndicator(Boolean value) {
        this.textListCompleteTransmissionIndicator = value;
    }

}
