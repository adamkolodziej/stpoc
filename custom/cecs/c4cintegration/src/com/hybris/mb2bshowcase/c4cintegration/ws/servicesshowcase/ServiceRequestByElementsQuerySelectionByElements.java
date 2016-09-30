
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceRequestByElementsQuerySelectionByElements complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestByElementsQuerySelectionByElements">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SelectionByID" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByMainBusinessProcessVariantTypeCode" type="{http://sap.com/xi/AP/Common/Global}SelectionByCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByProcessingTypeCode" type="{http://sap.com/xi/AP/Common/Global}SelectionByCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByName" type="{http://sap.com/xi/AP/Common/Global}SelectionByName" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByDataOriginTypeCode" type="{http://sap.com/xi/AP/Common/Global}SelectionByCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByLifeCycleStatusCode" type="{http://sap.com/xi/AP/Common/Global}SelectionByCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByEscalationStatusCode" type="{http://sap.com/xi/AP/Common/Global}SelectionByCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByApprovalStatusCode" type="{http://sap.com/xi/AP/Common/Global}SelectionByCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByItemListServiceRequestExecutionLifeCycleStatusCode" type="{http://sap.com/xi/AP/Common/Global}SelectionByCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByItemListFulfilmentProcessingStatusCode" type="{http://sap.com/xi/AP/Common/Global}SelectionByCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByBusinessTransactionDocumentReferenceTypeCode" type="{http://sap.com/xi/AP/Common/Global}SelectionByCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByBusinessTransactionDocumentReferenceID" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionBySystemAdministrativeDataCreationDateTime" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsQuerySelectionByGlobalDateTime" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionBySystemAdministrativeDataLastChangeDateTime" type="{http://sap.com/xi/A1S/Global}ServiceRequestByElementsQuerySelectionByGlobalDateTime" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByBuyerPartyID" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByProcessorPartyID" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByServicePerformerPartyID" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByServiceSupportTeamID" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByServiceExecutionTeamID" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByPartyID" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByPartyRoleCode" type="{http://sap.com/xi/AP/Common/Global}SelectionByCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByRequestInitialReceiptTimePoint" type="{http://sap.com/xi/AP/Common/Global}SelectionByDateTime" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByServiceTermsServicePriorityCode" type="{http://sap.com/xi/AP/Common/Global}SelectionByCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByServiceTermsServiceIssueCategoryID" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByServiceTermsChangedByCustomerIndicator" type="{http://sap.com/xi/AP/Common/Global}SelectionByIndicator" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByServiceTermsServiceExecutionRelevanceIndicator" type="{http://sap.com/xi/AP/Common/Global}SelectionByIndicator" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByServiceTermsServiceRequestClassificationCode" type="{http://sap.com/xi/AP/Common/Global}SelectionByCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByServiceTermsUserLifeCycleStatusCode" type="{http://sap.com/xi/AP/Common/Global}SelectionByCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByMainIncidentServiceIssueCategoryID" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByMainActivityServiceIssueCategoryID" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByMainCauseServiceIssueCategoryID" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByMainObjectPartServiceIssueCategoryID" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByMainServiceReferenceObjectMaterialID" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByMainServiceReferenceObjectIndividualProductSerialID" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByExternalKnowledgeBaseArticleID" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByItemDescription" type="{http://sap.com/xi/AP/Common/Global}SelectionByText" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByItemProductID" type="{http://sap.com/xi/AP/Common/Global}SelectionByCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionByItemUserServiceTransactionProcessingTypeCode" type="{http://sap.com/xi/AP/Common/Global}SelectionByCode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelectionBySearchText" type="{http://sap.com/xi/AP/Common/GDT}SearchText" minOccurs="0"/>
 *         &lt;group ref="{http://sap.com/xi/AP/CustomerExtension/BYD/A0014}Ext00163E08A7A61ED4A8ABB155FFF9093F"/>
 *         &lt;group ref="{http://sap.com/xi/AP/CustomerExtension/BYD/A0014}Ext00163E08A7A61ED4A8ABB6102ED2C940"/>
 *         &lt;group ref="{http://sap.com/xi/AP/CustomerExtension/BYD/A0014}Ext00163E08A7A61ED4A8ABC2C4FC57C9AF"/>
 *         &lt;group ref="{http://sap.com/xi/AP/CustomerExtension/BYD/A0014}Ext00163E08A7A61ED4A8DC644855EDB073"/>
 *         &lt;group ref="{http://sap.com/xi/AP/CustomerExtension/BYD/A0014}Ext00163E08A7AF1ED4A8AD28C1B43954E4"/>
 *         &lt;group ref="{http://sap.com/xi/AP/CustomerExtension/BYD/A0014}Ext00163E08A7AF1EE4A8DCB5EA7DE33371"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestByElementsQuerySelectionByElements", propOrder = {
    "selectionByID",
    "selectionByMainBusinessProcessVariantTypeCode",
    "selectionByProcessingTypeCode",
    "selectionByName",
    "selectionByDataOriginTypeCode",
    "selectionByLifeCycleStatusCode",
    "selectionByEscalationStatusCode",
    "selectionByApprovalStatusCode",
    "selectionByItemListServiceRequestExecutionLifeCycleStatusCode",
    "selectionByItemListFulfilmentProcessingStatusCode",
    "selectionByBusinessTransactionDocumentReferenceTypeCode",
    "selectionByBusinessTransactionDocumentReferenceID",
    "selectionBySystemAdministrativeDataCreationDateTime",
    "selectionBySystemAdministrativeDataLastChangeDateTime",
    "selectionByBuyerPartyID",
    "selectionByProcessorPartyID",
    "selectionByServicePerformerPartyID",
    "selectionByServiceSupportTeamID",
    "selectionByServiceExecutionTeamID",
    "selectionByPartyID",
    "selectionByPartyRoleCode",
    "selectionByRequestInitialReceiptTimePoint",
    "selectionByServiceTermsServicePriorityCode",
    "selectionByServiceTermsServiceIssueCategoryID",
    "selectionByServiceTermsChangedByCustomerIndicator",
    "selectionByServiceTermsServiceExecutionRelevanceIndicator",
    "selectionByServiceTermsServiceRequestClassificationCode",
    "selectionByServiceTermsUserLifeCycleStatusCode",
    "selectionByMainIncidentServiceIssueCategoryID",
    "selectionByMainActivityServiceIssueCategoryID",
    "selectionByMainCauseServiceIssueCategoryID",
    "selectionByMainObjectPartServiceIssueCategoryID",
    "selectionByMainServiceReferenceObjectMaterialID",
    "selectionByMainServiceReferenceObjectIndividualProductSerialID",
    "selectionByExternalKnowledgeBaseArticleID",
    "selectionByItemDescription",
    "selectionByItemProductID",
    "selectionByItemUserServiceTransactionProcessingTypeCode",
    "selectionBySearchText",
    "scheduledStartDate9WVLT0YP0XZ2GHZIFXHWYA21U",
    "scheduledStartTime9WVLT0YP0XZ2GHZIFXHWYA21U",
    "scheduledEndDate9WVLT0YP0XZ2GHZIFXHWYA21U",
    "emergencyOrder9WVLT0YP0XZ2GHZIFXHWYA21U",
    "scheduledEndTime9WVLT0YP0XZ2GHZIFXHWYA21U",
    "relevantforScheduling9WVLT0YP0XZ2GHZIFXHWYA21U"
})
public class ServiceRequestByElementsQuerySelectionByElements {

    @XmlElement(name = "SelectionByID")
    protected List<SelectionByIdentifier> selectionByID;
    @XmlElement(name = "SelectionByMainBusinessProcessVariantTypeCode")
    protected List<SelectionByCode> selectionByMainBusinessProcessVariantTypeCode;
    @XmlElement(name = "SelectionByProcessingTypeCode")
    protected List<SelectionByCode> selectionByProcessingTypeCode;
    @XmlElement(name = "SelectionByName")
    protected List<SelectionByName> selectionByName;
    @XmlElement(name = "SelectionByDataOriginTypeCode")
    protected List<SelectionByCode> selectionByDataOriginTypeCode;
    @XmlElement(name = "SelectionByLifeCycleStatusCode")
    protected List<SelectionByCode> selectionByLifeCycleStatusCode;
    @XmlElement(name = "SelectionByEscalationStatusCode")
    protected List<SelectionByCode> selectionByEscalationStatusCode;
    @XmlElement(name = "SelectionByApprovalStatusCode")
    protected List<SelectionByCode> selectionByApprovalStatusCode;
    @XmlElement(name = "SelectionByItemListServiceRequestExecutionLifeCycleStatusCode")
    protected List<SelectionByCode> selectionByItemListServiceRequestExecutionLifeCycleStatusCode;
    @XmlElement(name = "SelectionByItemListFulfilmentProcessingStatusCode")
    protected List<SelectionByCode> selectionByItemListFulfilmentProcessingStatusCode;
    @XmlElement(name = "SelectionByBusinessTransactionDocumentReferenceTypeCode")
    protected List<SelectionByCode> selectionByBusinessTransactionDocumentReferenceTypeCode;
    @XmlElement(name = "SelectionByBusinessTransactionDocumentReferenceID")
    protected List<SelectionByIdentifier> selectionByBusinessTransactionDocumentReferenceID;
    @XmlElement(name = "SelectionBySystemAdministrativeDataCreationDateTime")
    protected List<ServiceRequestByElementsQuerySelectionByGlobalDateTime> selectionBySystemAdministrativeDataCreationDateTime;
    @XmlElement(name = "SelectionBySystemAdministrativeDataLastChangeDateTime")
    protected List<ServiceRequestByElementsQuerySelectionByGlobalDateTime> selectionBySystemAdministrativeDataLastChangeDateTime;
    @XmlElement(name = "SelectionByBuyerPartyID")
    protected List<SelectionByIdentifier> selectionByBuyerPartyID;
    @XmlElement(name = "SelectionByProcessorPartyID")
    protected List<SelectionByIdentifier> selectionByProcessorPartyID;
    @XmlElement(name = "SelectionByServicePerformerPartyID")
    protected List<SelectionByIdentifier> selectionByServicePerformerPartyID;
    @XmlElement(name = "SelectionByServiceSupportTeamID")
    protected List<SelectionByIdentifier> selectionByServiceSupportTeamID;
    @XmlElement(name = "SelectionByServiceExecutionTeamID")
    protected List<SelectionByIdentifier> selectionByServiceExecutionTeamID;
    @XmlElement(name = "SelectionByPartyID")
    protected List<SelectionByIdentifier> selectionByPartyID;
    @XmlElement(name = "SelectionByPartyRoleCode")
    protected List<SelectionByCode> selectionByPartyRoleCode;
    @XmlElement(name = "SelectionByRequestInitialReceiptTimePoint")
    protected List<SelectionByDateTime> selectionByRequestInitialReceiptTimePoint;
    @XmlElement(name = "SelectionByServiceTermsServicePriorityCode")
    protected List<SelectionByCode> selectionByServiceTermsServicePriorityCode;
    @XmlElement(name = "SelectionByServiceTermsServiceIssueCategoryID")
    protected List<SelectionByIdentifier> selectionByServiceTermsServiceIssueCategoryID;
    @XmlElement(name = "SelectionByServiceTermsChangedByCustomerIndicator")
    protected List<SelectionByIndicator> selectionByServiceTermsChangedByCustomerIndicator;
    @XmlElement(name = "SelectionByServiceTermsServiceExecutionRelevanceIndicator")
    protected List<SelectionByIndicator> selectionByServiceTermsServiceExecutionRelevanceIndicator;
    @XmlElement(name = "SelectionByServiceTermsServiceRequestClassificationCode")
    protected List<SelectionByCode> selectionByServiceTermsServiceRequestClassificationCode;
    @XmlElement(name = "SelectionByServiceTermsUserLifeCycleStatusCode")
    protected List<SelectionByCode> selectionByServiceTermsUserLifeCycleStatusCode;
    @XmlElement(name = "SelectionByMainIncidentServiceIssueCategoryID")
    protected List<SelectionByIdentifier> selectionByMainIncidentServiceIssueCategoryID;
    @XmlElement(name = "SelectionByMainActivityServiceIssueCategoryID")
    protected List<SelectionByIdentifier> selectionByMainActivityServiceIssueCategoryID;
    @XmlElement(name = "SelectionByMainCauseServiceIssueCategoryID")
    protected List<SelectionByIdentifier> selectionByMainCauseServiceIssueCategoryID;
    @XmlElement(name = "SelectionByMainObjectPartServiceIssueCategoryID")
    protected List<SelectionByIdentifier> selectionByMainObjectPartServiceIssueCategoryID;
    @XmlElement(name = "SelectionByMainServiceReferenceObjectMaterialID")
    protected List<SelectionByIdentifier> selectionByMainServiceReferenceObjectMaterialID;
    @XmlElement(name = "SelectionByMainServiceReferenceObjectIndividualProductSerialID")
    protected List<SelectionByIdentifier> selectionByMainServiceReferenceObjectIndividualProductSerialID;
    @XmlElement(name = "SelectionByExternalKnowledgeBaseArticleID")
    protected List<SelectionByIdentifier> selectionByExternalKnowledgeBaseArticleID;
    @XmlElement(name = "SelectionByItemDescription")
    protected List<SelectionByText> selectionByItemDescription;
    @XmlElement(name = "SelectionByItemProductID")
    protected List<SelectionByCode> selectionByItemProductID;
    @XmlElement(name = "SelectionByItemUserServiceTransactionProcessingTypeCode")
    protected List<SelectionByCode> selectionByItemUserServiceTransactionProcessingTypeCode;
    @XmlElement(name = "SelectionBySearchText")
    protected String selectionBySearchText;
    @XmlElement(name = "ScheduledStartDate_9WVLT0YP0XZ2GHZIFXHWYA21U", namespace = "http://sap.com/xi/AP/CustomerExtension/BYD/A0014")
    protected ExtSelectionByDate scheduledStartDate9WVLT0YP0XZ2GHZIFXHWYA21U;
    @XmlElement(name = "ScheduledStartTime_9WVLT0YP0XZ2GHZIFXHWYA21U", namespace = "http://sap.com/xi/AP/CustomerExtension/BYD/A0014")
    protected ExtSelectionByTime scheduledStartTime9WVLT0YP0XZ2GHZIFXHWYA21U;
    @XmlElement(name = "ScheduledEndDate_9WVLT0YP0XZ2GHZIFXHWYA21U", namespace = "http://sap.com/xi/AP/CustomerExtension/BYD/A0014")
    protected ExtSelectionByDate scheduledEndDate9WVLT0YP0XZ2GHZIFXHWYA21U;
    @XmlElement(name = "EmergencyOrder_9WVLT0YP0XZ2GHZIFXHWYA21U", namespace = "http://sap.com/xi/AP/CustomerExtension/BYD/A0014")
    protected ExtSelectionByIndicator emergencyOrder9WVLT0YP0XZ2GHZIFXHWYA21U;
    @XmlElement(name = "ScheduledEndTime_9WVLT0YP0XZ2GHZIFXHWYA21U", namespace = "http://sap.com/xi/AP/CustomerExtension/BYD/A0014")
    protected ExtSelectionByTime scheduledEndTime9WVLT0YP0XZ2GHZIFXHWYA21U;
    @XmlElement(name = "RelevantforScheduling_9WVLT0YP0XZ2GHZIFXHWYA21U", namespace = "http://sap.com/xi/AP/CustomerExtension/BYD/A0014")
    protected ExtSelectionByIndicator relevantforScheduling9WVLT0YP0XZ2GHZIFXHWYA21U;

    /**
     * Gets the value of the selectionByID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getSelectionByID() {
        if (selectionByID == null) {
            selectionByID = new ArrayList<SelectionByIdentifier>();
        }
        return this.selectionByID;
    }

    /**
     * Gets the value of the selectionByMainBusinessProcessVariantTypeCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByMainBusinessProcessVariantTypeCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByMainBusinessProcessVariantTypeCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByCode }
     * 
     * 
     */
    public List<SelectionByCode> getSelectionByMainBusinessProcessVariantTypeCode() {
        if (selectionByMainBusinessProcessVariantTypeCode == null) {
            selectionByMainBusinessProcessVariantTypeCode = new ArrayList<SelectionByCode>();
        }
        return this.selectionByMainBusinessProcessVariantTypeCode;
    }

    /**
     * Gets the value of the selectionByProcessingTypeCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByProcessingTypeCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByProcessingTypeCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByCode }
     * 
     * 
     */
    public List<SelectionByCode> getSelectionByProcessingTypeCode() {
        if (selectionByProcessingTypeCode == null) {
            selectionByProcessingTypeCode = new ArrayList<SelectionByCode>();
        }
        return this.selectionByProcessingTypeCode;
    }

    /**
     * Gets the value of the selectionByName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByName }
     * 
     * 
     */
    public List<SelectionByName> getSelectionByName() {
        if (selectionByName == null) {
            selectionByName = new ArrayList<SelectionByName>();
        }
        return this.selectionByName;
    }

    /**
     * Gets the value of the selectionByDataOriginTypeCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByDataOriginTypeCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByDataOriginTypeCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByCode }
     * 
     * 
     */
    public List<SelectionByCode> getSelectionByDataOriginTypeCode() {
        if (selectionByDataOriginTypeCode == null) {
            selectionByDataOriginTypeCode = new ArrayList<SelectionByCode>();
        }
        return this.selectionByDataOriginTypeCode;
    }

    /**
     * Gets the value of the selectionByLifeCycleStatusCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByLifeCycleStatusCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByLifeCycleStatusCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByCode }
     * 
     * 
     */
    public List<SelectionByCode> getSelectionByLifeCycleStatusCode() {
        if (selectionByLifeCycleStatusCode == null) {
            selectionByLifeCycleStatusCode = new ArrayList<SelectionByCode>();
        }
        return this.selectionByLifeCycleStatusCode;
    }

    /**
     * Gets the value of the selectionByEscalationStatusCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByEscalationStatusCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByEscalationStatusCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByCode }
     * 
     * 
     */
    public List<SelectionByCode> getSelectionByEscalationStatusCode() {
        if (selectionByEscalationStatusCode == null) {
            selectionByEscalationStatusCode = new ArrayList<SelectionByCode>();
        }
        return this.selectionByEscalationStatusCode;
    }

    /**
     * Gets the value of the selectionByApprovalStatusCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByApprovalStatusCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByApprovalStatusCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByCode }
     * 
     * 
     */
    public List<SelectionByCode> getSelectionByApprovalStatusCode() {
        if (selectionByApprovalStatusCode == null) {
            selectionByApprovalStatusCode = new ArrayList<SelectionByCode>();
        }
        return this.selectionByApprovalStatusCode;
    }

    /**
     * Gets the value of the selectionByItemListServiceRequestExecutionLifeCycleStatusCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByItemListServiceRequestExecutionLifeCycleStatusCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByItemListServiceRequestExecutionLifeCycleStatusCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByCode }
     * 
     * 
     */
    public List<SelectionByCode> getSelectionByItemListServiceRequestExecutionLifeCycleStatusCode() {
        if (selectionByItemListServiceRequestExecutionLifeCycleStatusCode == null) {
            selectionByItemListServiceRequestExecutionLifeCycleStatusCode = new ArrayList<SelectionByCode>();
        }
        return this.selectionByItemListServiceRequestExecutionLifeCycleStatusCode;
    }

    /**
     * Gets the value of the selectionByItemListFulfilmentProcessingStatusCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByItemListFulfilmentProcessingStatusCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByItemListFulfilmentProcessingStatusCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByCode }
     * 
     * 
     */
    public List<SelectionByCode> getSelectionByItemListFulfilmentProcessingStatusCode() {
        if (selectionByItemListFulfilmentProcessingStatusCode == null) {
            selectionByItemListFulfilmentProcessingStatusCode = new ArrayList<SelectionByCode>();
        }
        return this.selectionByItemListFulfilmentProcessingStatusCode;
    }

    /**
     * Gets the value of the selectionByBusinessTransactionDocumentReferenceTypeCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByBusinessTransactionDocumentReferenceTypeCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByBusinessTransactionDocumentReferenceTypeCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByCode }
     * 
     * 
     */
    public List<SelectionByCode> getSelectionByBusinessTransactionDocumentReferenceTypeCode() {
        if (selectionByBusinessTransactionDocumentReferenceTypeCode == null) {
            selectionByBusinessTransactionDocumentReferenceTypeCode = new ArrayList<SelectionByCode>();
        }
        return this.selectionByBusinessTransactionDocumentReferenceTypeCode;
    }

    /**
     * Gets the value of the selectionByBusinessTransactionDocumentReferenceID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByBusinessTransactionDocumentReferenceID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByBusinessTransactionDocumentReferenceID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getSelectionByBusinessTransactionDocumentReferenceID() {
        if (selectionByBusinessTransactionDocumentReferenceID == null) {
            selectionByBusinessTransactionDocumentReferenceID = new ArrayList<SelectionByIdentifier>();
        }
        return this.selectionByBusinessTransactionDocumentReferenceID;
    }

    /**
     * Gets the value of the selectionBySystemAdministrativeDataCreationDateTime property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionBySystemAdministrativeDataCreationDateTime property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionBySystemAdministrativeDataCreationDateTime().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceRequestByElementsQuerySelectionByGlobalDateTime }
     * 
     * 
     */
    public List<ServiceRequestByElementsQuerySelectionByGlobalDateTime> getSelectionBySystemAdministrativeDataCreationDateTime() {
        if (selectionBySystemAdministrativeDataCreationDateTime == null) {
            selectionBySystemAdministrativeDataCreationDateTime = new ArrayList<ServiceRequestByElementsQuerySelectionByGlobalDateTime>();
        }
        return this.selectionBySystemAdministrativeDataCreationDateTime;
    }

    /**
     * Gets the value of the selectionBySystemAdministrativeDataLastChangeDateTime property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionBySystemAdministrativeDataLastChangeDateTime property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionBySystemAdministrativeDataLastChangeDateTime().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceRequestByElementsQuerySelectionByGlobalDateTime }
     * 
     * 
     */
    public List<ServiceRequestByElementsQuerySelectionByGlobalDateTime> getSelectionBySystemAdministrativeDataLastChangeDateTime() {
        if (selectionBySystemAdministrativeDataLastChangeDateTime == null) {
            selectionBySystemAdministrativeDataLastChangeDateTime = new ArrayList<ServiceRequestByElementsQuerySelectionByGlobalDateTime>();
        }
        return this.selectionBySystemAdministrativeDataLastChangeDateTime;
    }

    /**
     * Gets the value of the selectionByBuyerPartyID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByBuyerPartyID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByBuyerPartyID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getSelectionByBuyerPartyID() {
        if (selectionByBuyerPartyID == null) {
            selectionByBuyerPartyID = new ArrayList<SelectionByIdentifier>();
        }
        return this.selectionByBuyerPartyID;
    }

    /**
     * Gets the value of the selectionByProcessorPartyID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByProcessorPartyID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByProcessorPartyID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getSelectionByProcessorPartyID() {
        if (selectionByProcessorPartyID == null) {
            selectionByProcessorPartyID = new ArrayList<SelectionByIdentifier>();
        }
        return this.selectionByProcessorPartyID;
    }

    /**
     * Gets the value of the selectionByServicePerformerPartyID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByServicePerformerPartyID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByServicePerformerPartyID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getSelectionByServicePerformerPartyID() {
        if (selectionByServicePerformerPartyID == null) {
            selectionByServicePerformerPartyID = new ArrayList<SelectionByIdentifier>();
        }
        return this.selectionByServicePerformerPartyID;
    }

    /**
     * Gets the value of the selectionByServiceSupportTeamID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByServiceSupportTeamID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByServiceSupportTeamID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getSelectionByServiceSupportTeamID() {
        if (selectionByServiceSupportTeamID == null) {
            selectionByServiceSupportTeamID = new ArrayList<SelectionByIdentifier>();
        }
        return this.selectionByServiceSupportTeamID;
    }

    /**
     * Gets the value of the selectionByServiceExecutionTeamID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByServiceExecutionTeamID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByServiceExecutionTeamID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getSelectionByServiceExecutionTeamID() {
        if (selectionByServiceExecutionTeamID == null) {
            selectionByServiceExecutionTeamID = new ArrayList<SelectionByIdentifier>();
        }
        return this.selectionByServiceExecutionTeamID;
    }

    /**
     * Gets the value of the selectionByPartyID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByPartyID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByPartyID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getSelectionByPartyID() {
        if (selectionByPartyID == null) {
            selectionByPartyID = new ArrayList<SelectionByIdentifier>();
        }
        return this.selectionByPartyID;
    }

    /**
     * Gets the value of the selectionByPartyRoleCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByPartyRoleCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByPartyRoleCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByCode }
     * 
     * 
     */
    public List<SelectionByCode> getSelectionByPartyRoleCode() {
        if (selectionByPartyRoleCode == null) {
            selectionByPartyRoleCode = new ArrayList<SelectionByCode>();
        }
        return this.selectionByPartyRoleCode;
    }

    /**
     * Gets the value of the selectionByRequestInitialReceiptTimePoint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByRequestInitialReceiptTimePoint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByRequestInitialReceiptTimePoint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByDateTime }
     * 
     * 
     */
    public List<SelectionByDateTime> getSelectionByRequestInitialReceiptTimePoint() {
        if (selectionByRequestInitialReceiptTimePoint == null) {
            selectionByRequestInitialReceiptTimePoint = new ArrayList<SelectionByDateTime>();
        }
        return this.selectionByRequestInitialReceiptTimePoint;
    }

    /**
     * Gets the value of the selectionByServiceTermsServicePriorityCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByServiceTermsServicePriorityCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByServiceTermsServicePriorityCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByCode }
     * 
     * 
     */
    public List<SelectionByCode> getSelectionByServiceTermsServicePriorityCode() {
        if (selectionByServiceTermsServicePriorityCode == null) {
            selectionByServiceTermsServicePriorityCode = new ArrayList<SelectionByCode>();
        }
        return this.selectionByServiceTermsServicePriorityCode;
    }

    /**
     * Gets the value of the selectionByServiceTermsServiceIssueCategoryID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByServiceTermsServiceIssueCategoryID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByServiceTermsServiceIssueCategoryID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getSelectionByServiceTermsServiceIssueCategoryID() {
        if (selectionByServiceTermsServiceIssueCategoryID == null) {
            selectionByServiceTermsServiceIssueCategoryID = new ArrayList<SelectionByIdentifier>();
        }
        return this.selectionByServiceTermsServiceIssueCategoryID;
    }

    /**
     * Gets the value of the selectionByServiceTermsChangedByCustomerIndicator property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByServiceTermsChangedByCustomerIndicator property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByServiceTermsChangedByCustomerIndicator().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIndicator }
     * 
     * 
     */
    public List<SelectionByIndicator> getSelectionByServiceTermsChangedByCustomerIndicator() {
        if (selectionByServiceTermsChangedByCustomerIndicator == null) {
            selectionByServiceTermsChangedByCustomerIndicator = new ArrayList<SelectionByIndicator>();
        }
        return this.selectionByServiceTermsChangedByCustomerIndicator;
    }

    /**
     * Gets the value of the selectionByServiceTermsServiceExecutionRelevanceIndicator property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByServiceTermsServiceExecutionRelevanceIndicator property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByServiceTermsServiceExecutionRelevanceIndicator().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIndicator }
     * 
     * 
     */
    public List<SelectionByIndicator> getSelectionByServiceTermsServiceExecutionRelevanceIndicator() {
        if (selectionByServiceTermsServiceExecutionRelevanceIndicator == null) {
            selectionByServiceTermsServiceExecutionRelevanceIndicator = new ArrayList<SelectionByIndicator>();
        }
        return this.selectionByServiceTermsServiceExecutionRelevanceIndicator;
    }

    /**
     * Gets the value of the selectionByServiceTermsServiceRequestClassificationCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByServiceTermsServiceRequestClassificationCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByServiceTermsServiceRequestClassificationCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByCode }
     * 
     * 
     */
    public List<SelectionByCode> getSelectionByServiceTermsServiceRequestClassificationCode() {
        if (selectionByServiceTermsServiceRequestClassificationCode == null) {
            selectionByServiceTermsServiceRequestClassificationCode = new ArrayList<SelectionByCode>();
        }
        return this.selectionByServiceTermsServiceRequestClassificationCode;
    }

    /**
     * Gets the value of the selectionByServiceTermsUserLifeCycleStatusCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByServiceTermsUserLifeCycleStatusCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByServiceTermsUserLifeCycleStatusCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByCode }
     * 
     * 
     */
    public List<SelectionByCode> getSelectionByServiceTermsUserLifeCycleStatusCode() {
        if (selectionByServiceTermsUserLifeCycleStatusCode == null) {
            selectionByServiceTermsUserLifeCycleStatusCode = new ArrayList<SelectionByCode>();
        }
        return this.selectionByServiceTermsUserLifeCycleStatusCode;
    }

    /**
     * Gets the value of the selectionByMainIncidentServiceIssueCategoryID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByMainIncidentServiceIssueCategoryID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByMainIncidentServiceIssueCategoryID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getSelectionByMainIncidentServiceIssueCategoryID() {
        if (selectionByMainIncidentServiceIssueCategoryID == null) {
            selectionByMainIncidentServiceIssueCategoryID = new ArrayList<SelectionByIdentifier>();
        }
        return this.selectionByMainIncidentServiceIssueCategoryID;
    }

    /**
     * Gets the value of the selectionByMainActivityServiceIssueCategoryID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByMainActivityServiceIssueCategoryID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByMainActivityServiceIssueCategoryID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getSelectionByMainActivityServiceIssueCategoryID() {
        if (selectionByMainActivityServiceIssueCategoryID == null) {
            selectionByMainActivityServiceIssueCategoryID = new ArrayList<SelectionByIdentifier>();
        }
        return this.selectionByMainActivityServiceIssueCategoryID;
    }

    /**
     * Gets the value of the selectionByMainCauseServiceIssueCategoryID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByMainCauseServiceIssueCategoryID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByMainCauseServiceIssueCategoryID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getSelectionByMainCauseServiceIssueCategoryID() {
        if (selectionByMainCauseServiceIssueCategoryID == null) {
            selectionByMainCauseServiceIssueCategoryID = new ArrayList<SelectionByIdentifier>();
        }
        return this.selectionByMainCauseServiceIssueCategoryID;
    }

    /**
     * Gets the value of the selectionByMainObjectPartServiceIssueCategoryID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByMainObjectPartServiceIssueCategoryID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByMainObjectPartServiceIssueCategoryID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getSelectionByMainObjectPartServiceIssueCategoryID() {
        if (selectionByMainObjectPartServiceIssueCategoryID == null) {
            selectionByMainObjectPartServiceIssueCategoryID = new ArrayList<SelectionByIdentifier>();
        }
        return this.selectionByMainObjectPartServiceIssueCategoryID;
    }

    /**
     * Gets the value of the selectionByMainServiceReferenceObjectMaterialID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByMainServiceReferenceObjectMaterialID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByMainServiceReferenceObjectMaterialID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getSelectionByMainServiceReferenceObjectMaterialID() {
        if (selectionByMainServiceReferenceObjectMaterialID == null) {
            selectionByMainServiceReferenceObjectMaterialID = new ArrayList<SelectionByIdentifier>();
        }
        return this.selectionByMainServiceReferenceObjectMaterialID;
    }

    /**
     * Gets the value of the selectionByMainServiceReferenceObjectIndividualProductSerialID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByMainServiceReferenceObjectIndividualProductSerialID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByMainServiceReferenceObjectIndividualProductSerialID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getSelectionByMainServiceReferenceObjectIndividualProductSerialID() {
        if (selectionByMainServiceReferenceObjectIndividualProductSerialID == null) {
            selectionByMainServiceReferenceObjectIndividualProductSerialID = new ArrayList<SelectionByIdentifier>();
        }
        return this.selectionByMainServiceReferenceObjectIndividualProductSerialID;
    }

    /**
     * Gets the value of the selectionByExternalKnowledgeBaseArticleID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByExternalKnowledgeBaseArticleID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByExternalKnowledgeBaseArticleID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getSelectionByExternalKnowledgeBaseArticleID() {
        if (selectionByExternalKnowledgeBaseArticleID == null) {
            selectionByExternalKnowledgeBaseArticleID = new ArrayList<SelectionByIdentifier>();
        }
        return this.selectionByExternalKnowledgeBaseArticleID;
    }

    /**
     * Gets the value of the selectionByItemDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByItemDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByItemDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByText }
     * 
     * 
     */
    public List<SelectionByText> getSelectionByItemDescription() {
        if (selectionByItemDescription == null) {
            selectionByItemDescription = new ArrayList<SelectionByText>();
        }
        return this.selectionByItemDescription;
    }

    /**
     * Gets the value of the selectionByItemProductID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByItemProductID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByItemProductID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByCode }
     * 
     * 
     */
    public List<SelectionByCode> getSelectionByItemProductID() {
        if (selectionByItemProductID == null) {
            selectionByItemProductID = new ArrayList<SelectionByCode>();
        }
        return this.selectionByItemProductID;
    }

    /**
     * Gets the value of the selectionByItemUserServiceTransactionProcessingTypeCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByItemUserServiceTransactionProcessingTypeCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByItemUserServiceTransactionProcessingTypeCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByCode }
     * 
     * 
     */
    public List<SelectionByCode> getSelectionByItemUserServiceTransactionProcessingTypeCode() {
        if (selectionByItemUserServiceTransactionProcessingTypeCode == null) {
            selectionByItemUserServiceTransactionProcessingTypeCode = new ArrayList<SelectionByCode>();
        }
        return this.selectionByItemUserServiceTransactionProcessingTypeCode;
    }

    /**
     * Gets the value of the selectionBySearchText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectionBySearchText() {
        return selectionBySearchText;
    }

    /**
     * Sets the value of the selectionBySearchText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectionBySearchText(String value) {
        this.selectionBySearchText = value;
    }

    /**
     * Gets the value of the scheduledStartDate9WVLT0YP0XZ2GHZIFXHWYA21U property.
     * 
     * @return
     *     possible object is
     *     {@link ExtSelectionByDate }
     *     
     */
    public ExtSelectionByDate getScheduledStartDate9WVLT0YP0XZ2GHZIFXHWYA21U() {
        return scheduledStartDate9WVLT0YP0XZ2GHZIFXHWYA21U;
    }

    /**
     * Sets the value of the scheduledStartDate9WVLT0YP0XZ2GHZIFXHWYA21U property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtSelectionByDate }
     *     
     */
    public void setScheduledStartDate9WVLT0YP0XZ2GHZIFXHWYA21U(ExtSelectionByDate value) {
        this.scheduledStartDate9WVLT0YP0XZ2GHZIFXHWYA21U = value;
    }

    /**
     * Gets the value of the scheduledStartTime9WVLT0YP0XZ2GHZIFXHWYA21U property.
     * 
     * @return
     *     possible object is
     *     {@link ExtSelectionByTime }
     *     
     */
    public ExtSelectionByTime getScheduledStartTime9WVLT0YP0XZ2GHZIFXHWYA21U() {
        return scheduledStartTime9WVLT0YP0XZ2GHZIFXHWYA21U;
    }

    /**
     * Sets the value of the scheduledStartTime9WVLT0YP0XZ2GHZIFXHWYA21U property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtSelectionByTime }
     *     
     */
    public void setScheduledStartTime9WVLT0YP0XZ2GHZIFXHWYA21U(ExtSelectionByTime value) {
        this.scheduledStartTime9WVLT0YP0XZ2GHZIFXHWYA21U = value;
    }

    /**
     * Gets the value of the scheduledEndDate9WVLT0YP0XZ2GHZIFXHWYA21U property.
     * 
     * @return
     *     possible object is
     *     {@link ExtSelectionByDate }
     *     
     */
    public ExtSelectionByDate getScheduledEndDate9WVLT0YP0XZ2GHZIFXHWYA21U() {
        return scheduledEndDate9WVLT0YP0XZ2GHZIFXHWYA21U;
    }

    /**
     * Sets the value of the scheduledEndDate9WVLT0YP0XZ2GHZIFXHWYA21U property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtSelectionByDate }
     *     
     */
    public void setScheduledEndDate9WVLT0YP0XZ2GHZIFXHWYA21U(ExtSelectionByDate value) {
        this.scheduledEndDate9WVLT0YP0XZ2GHZIFXHWYA21U = value;
    }

    /**
     * Gets the value of the emergencyOrder9WVLT0YP0XZ2GHZIFXHWYA21U property.
     * 
     * @return
     *     possible object is
     *     {@link ExtSelectionByIndicator }
     *     
     */
    public ExtSelectionByIndicator getEmergencyOrder9WVLT0YP0XZ2GHZIFXHWYA21U() {
        return emergencyOrder9WVLT0YP0XZ2GHZIFXHWYA21U;
    }

    /**
     * Sets the value of the emergencyOrder9WVLT0YP0XZ2GHZIFXHWYA21U property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtSelectionByIndicator }
     *     
     */
    public void setEmergencyOrder9WVLT0YP0XZ2GHZIFXHWYA21U(ExtSelectionByIndicator value) {
        this.emergencyOrder9WVLT0YP0XZ2GHZIFXHWYA21U = value;
    }

    /**
     * Gets the value of the scheduledEndTime9WVLT0YP0XZ2GHZIFXHWYA21U property.
     * 
     * @return
     *     possible object is
     *     {@link ExtSelectionByTime }
     *     
     */
    public ExtSelectionByTime getScheduledEndTime9WVLT0YP0XZ2GHZIFXHWYA21U() {
        return scheduledEndTime9WVLT0YP0XZ2GHZIFXHWYA21U;
    }

    /**
     * Sets the value of the scheduledEndTime9WVLT0YP0XZ2GHZIFXHWYA21U property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtSelectionByTime }
     *     
     */
    public void setScheduledEndTime9WVLT0YP0XZ2GHZIFXHWYA21U(ExtSelectionByTime value) {
        this.scheduledEndTime9WVLT0YP0XZ2GHZIFXHWYA21U = value;
    }

    /**
     * Gets the value of the relevantforScheduling9WVLT0YP0XZ2GHZIFXHWYA21U property.
     * 
     * @return
     *     possible object is
     *     {@link ExtSelectionByIndicator }
     *     
     */
    public ExtSelectionByIndicator getRelevantforScheduling9WVLT0YP0XZ2GHZIFXHWYA21U() {
        return relevantforScheduling9WVLT0YP0XZ2GHZIFXHWYA21U;
    }

    /**
     * Sets the value of the relevantforScheduling9WVLT0YP0XZ2GHZIFXHWYA21U property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtSelectionByIndicator }
     *     
     */
    public void setRelevantforScheduling9WVLT0YP0XZ2GHZIFXHWYA21U(ExtSelectionByIndicator value) {
        this.relevantforScheduling9WVLT0YP0XZ2GHZIFXHWYA21U = value;
    }

}
