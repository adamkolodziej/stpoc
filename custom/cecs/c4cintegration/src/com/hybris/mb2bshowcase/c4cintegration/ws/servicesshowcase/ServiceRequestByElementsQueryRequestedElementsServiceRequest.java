
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ServiceRequestByElementsQueryRequestedElementsServiceRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestByElementsQueryRequestedElementsServiceRequest">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="businessTransactionDocumentReferenceTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="buyerPartyTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="processorPartyTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="servicePerformerPartyTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="endBuyerPartyTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="otherPartyTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="serviceSupportTeamPartyTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="serviceExecutionTeamPartyTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="salesAndServiceBusinessAreaTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="timePointTermsTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="durationTermsTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="servicePointLocationTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="serviceTermsTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="mainIncidentServiceIssueCategoryTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="mainObjectPartServiceIssueCategoryTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="mainCauseServiceIssueCategoryTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="mainActivityServiceIssueCategoryTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="mainServiceReferenceObjectTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="solutionProposalTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="itemTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="textTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *       &lt;attribute name="attachmentFolderTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestByElementsQueryRequestedElementsServiceRequest", propOrder = {
    "value"
})
public class ServiceRequestByElementsQueryRequestedElementsServiceRequest {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "businessTransactionDocumentReferenceTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String businessTransactionDocumentReferenceTransmissionRequestCode;
    @XmlAttribute(name = "buyerPartyTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String buyerPartyTransmissionRequestCode;
    @XmlAttribute(name = "processorPartyTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String processorPartyTransmissionRequestCode;
    @XmlAttribute(name = "servicePerformerPartyTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String servicePerformerPartyTransmissionRequestCode;
    @XmlAttribute(name = "endBuyerPartyTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String endBuyerPartyTransmissionRequestCode;
    @XmlAttribute(name = "otherPartyTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String otherPartyTransmissionRequestCode;
    @XmlAttribute(name = "serviceSupportTeamPartyTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String serviceSupportTeamPartyTransmissionRequestCode;
    @XmlAttribute(name = "serviceExecutionTeamPartyTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String serviceExecutionTeamPartyTransmissionRequestCode;
    @XmlAttribute(name = "salesAndServiceBusinessAreaTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String salesAndServiceBusinessAreaTransmissionRequestCode;
    @XmlAttribute(name = "timePointTermsTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String timePointTermsTransmissionRequestCode;
    @XmlAttribute(name = "durationTermsTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String durationTermsTransmissionRequestCode;
    @XmlAttribute(name = "servicePointLocationTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String servicePointLocationTransmissionRequestCode;
    @XmlAttribute(name = "serviceTermsTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String serviceTermsTransmissionRequestCode;
    @XmlAttribute(name = "mainIncidentServiceIssueCategoryTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mainIncidentServiceIssueCategoryTransmissionRequestCode;
    @XmlAttribute(name = "mainObjectPartServiceIssueCategoryTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mainObjectPartServiceIssueCategoryTransmissionRequestCode;
    @XmlAttribute(name = "mainCauseServiceIssueCategoryTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mainCauseServiceIssueCategoryTransmissionRequestCode;
    @XmlAttribute(name = "mainActivityServiceIssueCategoryTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mainActivityServiceIssueCategoryTransmissionRequestCode;
    @XmlAttribute(name = "mainServiceReferenceObjectTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mainServiceReferenceObjectTransmissionRequestCode;
    @XmlAttribute(name = "solutionProposalTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String solutionProposalTransmissionRequestCode;
    @XmlAttribute(name = "itemTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String itemTransmissionRequestCode;
    @XmlAttribute(name = "textTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String textTransmissionRequestCode;
    @XmlAttribute(name = "attachmentFolderTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String attachmentFolderTransmissionRequestCode;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the businessTransactionDocumentReferenceTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessTransactionDocumentReferenceTransmissionRequestCode() {
        return businessTransactionDocumentReferenceTransmissionRequestCode;
    }

    /**
     * Sets the value of the businessTransactionDocumentReferenceTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessTransactionDocumentReferenceTransmissionRequestCode(String value) {
        this.businessTransactionDocumentReferenceTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the buyerPartyTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuyerPartyTransmissionRequestCode() {
        return buyerPartyTransmissionRequestCode;
    }

    /**
     * Sets the value of the buyerPartyTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuyerPartyTransmissionRequestCode(String value) {
        this.buyerPartyTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the processorPartyTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessorPartyTransmissionRequestCode() {
        return processorPartyTransmissionRequestCode;
    }

    /**
     * Sets the value of the processorPartyTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessorPartyTransmissionRequestCode(String value) {
        this.processorPartyTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the servicePerformerPartyTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicePerformerPartyTransmissionRequestCode() {
        return servicePerformerPartyTransmissionRequestCode;
    }

    /**
     * Sets the value of the servicePerformerPartyTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicePerformerPartyTransmissionRequestCode(String value) {
        this.servicePerformerPartyTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the endBuyerPartyTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndBuyerPartyTransmissionRequestCode() {
        return endBuyerPartyTransmissionRequestCode;
    }

    /**
     * Sets the value of the endBuyerPartyTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndBuyerPartyTransmissionRequestCode(String value) {
        this.endBuyerPartyTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the otherPartyTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherPartyTransmissionRequestCode() {
        return otherPartyTransmissionRequestCode;
    }

    /**
     * Sets the value of the otherPartyTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherPartyTransmissionRequestCode(String value) {
        this.otherPartyTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the serviceSupportTeamPartyTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceSupportTeamPartyTransmissionRequestCode() {
        return serviceSupportTeamPartyTransmissionRequestCode;
    }

    /**
     * Sets the value of the serviceSupportTeamPartyTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceSupportTeamPartyTransmissionRequestCode(String value) {
        this.serviceSupportTeamPartyTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the serviceExecutionTeamPartyTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceExecutionTeamPartyTransmissionRequestCode() {
        return serviceExecutionTeamPartyTransmissionRequestCode;
    }

    /**
     * Sets the value of the serviceExecutionTeamPartyTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceExecutionTeamPartyTransmissionRequestCode(String value) {
        this.serviceExecutionTeamPartyTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the salesAndServiceBusinessAreaTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesAndServiceBusinessAreaTransmissionRequestCode() {
        return salesAndServiceBusinessAreaTransmissionRequestCode;
    }

    /**
     * Sets the value of the salesAndServiceBusinessAreaTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesAndServiceBusinessAreaTransmissionRequestCode(String value) {
        this.salesAndServiceBusinessAreaTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the timePointTermsTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimePointTermsTransmissionRequestCode() {
        return timePointTermsTransmissionRequestCode;
    }

    /**
     * Sets the value of the timePointTermsTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimePointTermsTransmissionRequestCode(String value) {
        this.timePointTermsTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the durationTermsTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDurationTermsTransmissionRequestCode() {
        return durationTermsTransmissionRequestCode;
    }

    /**
     * Sets the value of the durationTermsTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDurationTermsTransmissionRequestCode(String value) {
        this.durationTermsTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the servicePointLocationTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicePointLocationTransmissionRequestCode() {
        return servicePointLocationTransmissionRequestCode;
    }

    /**
     * Sets the value of the servicePointLocationTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicePointLocationTransmissionRequestCode(String value) {
        this.servicePointLocationTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the serviceTermsTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceTermsTransmissionRequestCode() {
        return serviceTermsTransmissionRequestCode;
    }

    /**
     * Sets the value of the serviceTermsTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTermsTransmissionRequestCode(String value) {
        this.serviceTermsTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the mainIncidentServiceIssueCategoryTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainIncidentServiceIssueCategoryTransmissionRequestCode() {
        return mainIncidentServiceIssueCategoryTransmissionRequestCode;
    }

    /**
     * Sets the value of the mainIncidentServiceIssueCategoryTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainIncidentServiceIssueCategoryTransmissionRequestCode(String value) {
        this.mainIncidentServiceIssueCategoryTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the mainObjectPartServiceIssueCategoryTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainObjectPartServiceIssueCategoryTransmissionRequestCode() {
        return mainObjectPartServiceIssueCategoryTransmissionRequestCode;
    }

    /**
     * Sets the value of the mainObjectPartServiceIssueCategoryTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainObjectPartServiceIssueCategoryTransmissionRequestCode(String value) {
        this.mainObjectPartServiceIssueCategoryTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the mainCauseServiceIssueCategoryTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainCauseServiceIssueCategoryTransmissionRequestCode() {
        return mainCauseServiceIssueCategoryTransmissionRequestCode;
    }

    /**
     * Sets the value of the mainCauseServiceIssueCategoryTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainCauseServiceIssueCategoryTransmissionRequestCode(String value) {
        this.mainCauseServiceIssueCategoryTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the mainActivityServiceIssueCategoryTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainActivityServiceIssueCategoryTransmissionRequestCode() {
        return mainActivityServiceIssueCategoryTransmissionRequestCode;
    }

    /**
     * Sets the value of the mainActivityServiceIssueCategoryTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainActivityServiceIssueCategoryTransmissionRequestCode(String value) {
        this.mainActivityServiceIssueCategoryTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the mainServiceReferenceObjectTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainServiceReferenceObjectTransmissionRequestCode() {
        return mainServiceReferenceObjectTransmissionRequestCode;
    }

    /**
     * Sets the value of the mainServiceReferenceObjectTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainServiceReferenceObjectTransmissionRequestCode(String value) {
        this.mainServiceReferenceObjectTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the solutionProposalTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolutionProposalTransmissionRequestCode() {
        return solutionProposalTransmissionRequestCode;
    }

    /**
     * Sets the value of the solutionProposalTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolutionProposalTransmissionRequestCode(String value) {
        this.solutionProposalTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the itemTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemTransmissionRequestCode() {
        return itemTransmissionRequestCode;
    }

    /**
     * Sets the value of the itemTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemTransmissionRequestCode(String value) {
        this.itemTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the textTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextTransmissionRequestCode() {
        return textTransmissionRequestCode;
    }

    /**
     * Sets the value of the textTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextTransmissionRequestCode(String value) {
        this.textTransmissionRequestCode = value;
    }

    /**
     * Gets the value of the attachmentFolderTransmissionRequestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttachmentFolderTransmissionRequestCode() {
        return attachmentFolderTransmissionRequestCode;
    }

    /**
     * Sets the value of the attachmentFolderTransmissionRequestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttachmentFolderTransmissionRequestCode(String value) {
        this.attachmentFolderTransmissionRequestCode = value;
    }

}
