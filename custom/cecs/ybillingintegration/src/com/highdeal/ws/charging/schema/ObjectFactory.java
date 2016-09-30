
package com.highdeal.ws.charging.schema;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.highdeal.ws.charging.schema package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ChargeableItemCheckChargeRequest_QNAME = new QName("http://schema.charging.ws.highdeal.com/", "chargeableItemCheckChargeRequest");
    private final static QName _ChargeableItemChargeMassResponse_QNAME = new QName("http://schema.charging.ws.highdeal.com/", "chargeableItemChargeMassResponse");
    private final static QName _ChargeableItemChargeRequest_QNAME = new QName("http://schema.charging.ws.highdeal.com/", "chargeableItemChargeRequest");
    private final static QName _ChargeableItemChargeBundleRequest_QNAME = new QName("http://schema.charging.ws.highdeal.com/", "chargeableItemChargeBundleRequest");
    private final static QName _ChargeableItemChargeBundleResponse_QNAME = new QName("http://schema.charging.ws.highdeal.com/", "chargeableItemChargeBundleResponse");
    private final static QName _ChargeableItemChargeResponse_QNAME = new QName("http://schema.charging.ws.highdeal.com/", "chargeableItemChargeResponse");
    private final static QName _ChargeableItemCheckChargeResponse_QNAME = new QName("http://schema.charging.ws.highdeal.com/", "chargeableItemCheckChargeResponse");
    private final static QName _ChargeableItemChargeMassRequest_QNAME = new QName("http://schema.charging.ws.highdeal.com/", "chargeableItemChargeMassRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.highdeal.ws.charging.schema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ChargeableItemChargeMassRequest }
     * 
     */
    public ChargeableItemChargeMassRequest createChargeableItemChargeMassRequest() {
        return new ChargeableItemChargeMassRequest();
    }

    /**
     * Create an instance of {@link ChargeableItemChargeBundleResponse }
     * 
     */
    public ChargeableItemChargeBundleResponse createChargeableItemChargeBundleResponse() {
        return new ChargeableItemChargeBundleResponse();
    }

    /**
     * Create an instance of {@link ChargeableItemChargeResponse }
     * 
     */
    public ChargeableItemChargeResponse createChargeableItemChargeResponse() {
        return new ChargeableItemChargeResponse();
    }

    /**
     * Create an instance of {@link ChargeableItemCheckChargeResponse }
     * 
     */
    public ChargeableItemCheckChargeResponse createChargeableItemCheckChargeResponse() {
        return new ChargeableItemCheckChargeResponse();
    }

    /**
     * Create an instance of {@link ChargeableItemChargeMassResponse }
     * 
     */
    public ChargeableItemChargeMassResponse createChargeableItemChargeMassResponse() {
        return new ChargeableItemChargeMassResponse();
    }

    /**
     * Create an instance of {@link ChargeableItemChargeRequest }
     * 
     */
    public ChargeableItemChargeRequest createChargeableItemChargeRequest() {
        return new ChargeableItemChargeRequest();
    }

    /**
     * Create an instance of {@link ChargeableItemChargeBundleRequest }
     * 
     */
    public ChargeableItemChargeBundleRequest createChargeableItemChargeBundleRequest() {
        return new ChargeableItemChargeBundleRequest();
    }

    /**
     * Create an instance of {@link ChargeableItemCheckChargeRequest }
     * 
     */
    public ChargeableItemCheckChargeRequest createChargeableItemCheckChargeRequest() {
        return new ChargeableItemCheckChargeRequest();
    }

    /**
     * Create an instance of {@link ChargingResultOnErrorContext }
     * 
     */
    public ChargingResultOnErrorContext createChargingResultOnErrorContext() {
        return new ChargingResultOnErrorContext();
    }

    /**
     * Create an instance of {@link StateSchedule }
     * 
     */
    public StateSchedule createStateSchedule() {
        return new StateSchedule();
    }

    /**
     * Create an instance of {@link ChargeableItemChargeContext }
     * 
     */
    public ChargeableItemChargeContext createChargeableItemChargeContext() {
        return new ChargeableItemChargeContext();
    }

    /**
     * Create an instance of {@link ChargingOutputContext }
     * 
     */
    public ChargingOutputContext createChargingOutputContext() {
        return new ChargingOutputContext();
    }

    /**
     * Create an instance of {@link StringUniqueIdentifier }
     * 
     */
    public StringUniqueIdentifier createStringUniqueIdentifier() {
        return new StringUniqueIdentifier();
    }

    /**
     * Create an instance of {@link ChargingProcessInfo }
     * 
     */
    public ChargingProcessInfo createChargingProcessInfo() {
        return new ChargingProcessInfo();
    }

    /**
     * Create an instance of {@link ChargedItem }
     * 
     */
    public ChargedItem createChargedItem() {
        return new ChargedItem();
    }

    /**
     * Create an instance of {@link ChargeableItem }
     * 
     */
    public ChargeableItem createChargeableItem() {
        return new ChargeableItem();
    }

    /**
     * Create an instance of {@link Notification }
     * 
     */
    public Notification createNotification() {
        return new Notification();
    }

    /**
     * Create an instance of {@link ChargingResponse }
     * 
     */
    public ChargingResponse createChargingResponse() {
        return new ChargingResponse();
    }

    /**
     * Create an instance of {@link AmountAssignment }
     * 
     */
    public AmountAssignment createAmountAssignment() {
        return new AmountAssignment();
    }

    /**
     * Create an instance of {@link StateScheduleItem }
     * 
     */
    public StateScheduleItem createStateScheduleItem() {
        return new StateScheduleItem();
    }

    /**
     * Create an instance of {@link BaseResponse }
     * 
     */
    public BaseResponse createBaseResponse() {
        return new BaseResponse();
    }

    /**
     * Create an instance of {@link Field }
     * 
     */
    public Field createField() {
        return new Field();
    }

    /**
     * Create an instance of {@link IntegerUniqueIdentifier }
     * 
     */
    public IntegerUniqueIdentifier createIntegerUniqueIdentifier() {
        return new IntegerUniqueIdentifier();
    }

    /**
     * Create an instance of {@link ChargingFailure }
     * 
     */
    public ChargingFailure createChargingFailure() {
        return new ChargingFailure();
    }

    /**
     * Create an instance of {@link ChargeableItemInfo }
     * 
     */
    public ChargeableItemInfo createChargeableItemInfo() {
        return new ChargeableItemInfo();
    }

    /**
     * Create an instance of {@link AccountOperation }
     * 
     */
    public AccountOperation createAccountOperation() {
        return new AccountOperation();
    }

    /**
     * Create an instance of {@link PrepaidAccountInfo }
     * 
     */
    public PrepaidAccountInfo createPrepaidAccountInfo() {
        return new PrepaidAccountInfo();
    }

    /**
     * Create an instance of {@link ChargeableItemUserProperties }
     * 
     */
    public ChargeableItemUserProperties createChargeableItemUserProperties() {
        return new ChargeableItemUserProperties();
    }

    /**
     * Create an instance of {@link Failure }
     * 
     */
    public Failure createFailure() {
        return new Failure();
    }

    /**
     * Create an instance of {@link SenderContext }
     * 
     */
    public SenderContext createSenderContext() {
        return new SenderContext();
    }

    /**
     * Create an instance of {@link ExternalAccountInfo }
     * 
     */
    public ExternalAccountInfo createExternalAccountInfo() {
        return new ExternalAccountInfo();
    }

    /**
     * Create an instance of {@link ChargingResultContext }
     * 
     */
    public ChargingResultContext createChargingResultContext() {
        return new ChargingResultContext();
    }

    /**
     * Create an instance of {@link ChargingResult }
     * 
     */
    public ChargingResult createChargingResult() {
        return new ChargingResult();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link ChargingContractInfo }
     * 
     */
    public ChargingContractInfo createChargingContractInfo() {
        return new ChargingContractInfo();
    }

    /**
     * Create an instance of {@link ChargeableItemProperty }
     * 
     */
    public ChargeableItemProperty createChargeableItemProperty() {
        return new ChargeableItemProperty();
    }

    /**
     * Create an instance of {@link AccountReference }
     * 
     */
    public AccountReference createAccountReference() {
        return new AccountReference();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChargeableItemCheckChargeRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schema.charging.ws.highdeal.com/", name = "chargeableItemCheckChargeRequest")
    public JAXBElement<ChargeableItemCheckChargeRequest> createChargeableItemCheckChargeRequest(ChargeableItemCheckChargeRequest value) {
        return new JAXBElement<ChargeableItemCheckChargeRequest>(_ChargeableItemCheckChargeRequest_QNAME, ChargeableItemCheckChargeRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChargeableItemChargeMassResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schema.charging.ws.highdeal.com/", name = "chargeableItemChargeMassResponse")
    public JAXBElement<ChargeableItemChargeMassResponse> createChargeableItemChargeMassResponse(ChargeableItemChargeMassResponse value) {
        return new JAXBElement<ChargeableItemChargeMassResponse>(_ChargeableItemChargeMassResponse_QNAME, ChargeableItemChargeMassResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChargeableItemChargeRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schema.charging.ws.highdeal.com/", name = "chargeableItemChargeRequest")
    public JAXBElement<ChargeableItemChargeRequest> createChargeableItemChargeRequest(ChargeableItemChargeRequest value) {
        return new JAXBElement<ChargeableItemChargeRequest>(_ChargeableItemChargeRequest_QNAME, ChargeableItemChargeRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChargeableItemChargeBundleRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schema.charging.ws.highdeal.com/", name = "chargeableItemChargeBundleRequest")
    public JAXBElement<ChargeableItemChargeBundleRequest> createChargeableItemChargeBundleRequest(ChargeableItemChargeBundleRequest value) {
        return new JAXBElement<ChargeableItemChargeBundleRequest>(_ChargeableItemChargeBundleRequest_QNAME, ChargeableItemChargeBundleRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChargeableItemChargeBundleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schema.charging.ws.highdeal.com/", name = "chargeableItemChargeBundleResponse")
    public JAXBElement<ChargeableItemChargeBundleResponse> createChargeableItemChargeBundleResponse(ChargeableItemChargeBundleResponse value) {
        return new JAXBElement<ChargeableItemChargeBundleResponse>(_ChargeableItemChargeBundleResponse_QNAME, ChargeableItemChargeBundleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChargeableItemChargeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schema.charging.ws.highdeal.com/", name = "chargeableItemChargeResponse")
    public JAXBElement<ChargeableItemChargeResponse> createChargeableItemChargeResponse(ChargeableItemChargeResponse value) {
        return new JAXBElement<ChargeableItemChargeResponse>(_ChargeableItemChargeResponse_QNAME, ChargeableItemChargeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChargeableItemCheckChargeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schema.charging.ws.highdeal.com/", name = "chargeableItemCheckChargeResponse")
    public JAXBElement<ChargeableItemCheckChargeResponse> createChargeableItemCheckChargeResponse(ChargeableItemCheckChargeResponse value) {
        return new JAXBElement<ChargeableItemCheckChargeResponse>(_ChargeableItemCheckChargeResponse_QNAME, ChargeableItemCheckChargeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChargeableItemChargeMassRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schema.charging.ws.highdeal.com/", name = "chargeableItemChargeMassRequest")
    public JAXBElement<ChargeableItemChargeMassRequest> createChargeableItemChargeMassRequest(ChargeableItemChargeMassRequest value) {
        return new JAXBElement<ChargeableItemChargeMassRequest>(_ChargeableItemChargeMassRequest_QNAME, ChargeableItemChargeMassRequest.class, null, value);
    }

}
