
package com.highdeal.ws.charging;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import com.highdeal.ws.charging.schema.ChargeableItemChargeBundleRequest;
import com.highdeal.ws.charging.schema.ChargeableItemChargeBundleResponse;
import com.highdeal.ws.charging.schema.ChargeableItemChargeMassRequest;
import com.highdeal.ws.charging.schema.ChargeableItemChargeMassResponse;
import com.highdeal.ws.charging.schema.ChargeableItemChargeRequest;
import com.highdeal.ws.charging.schema.ChargeableItemChargeResponse;
import com.highdeal.ws.charging.schema.ChargeableItemCheckChargeRequest;
import com.highdeal.ws.charging.schema.ChargeableItemCheckChargeResponse;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ChargeableItemChargingServices", targetNamespace = "http://charging.ws.highdeal.com/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    com.highdeal.ws.charging.schema.ObjectFactory.class,
    com.highdeal.ws.schema.ObjectFactory.class
})
public interface ChargeableItemChargingServices {


    /**
     * 
     * @param request
     * @return
     *     returns com.highdeal.ws.charging.schema.ChargeableItemChargeResponse
     */
    @WebMethod(action = "chargeableItemCharge")
    @WebResult(name = "chargeableItemChargeResponse", targetNamespace = "http://schema.charging.ws.highdeal.com/", partName = "response")
    public ChargeableItemChargeResponse chargeableItemCharge(
        @WebParam(name = "chargeableItemChargeRequest", targetNamespace = "http://schema.charging.ws.highdeal.com/", partName = "request")
        ChargeableItemChargeRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns com.highdeal.ws.charging.schema.ChargeableItemChargeMassResponse
     */
    @WebMethod(action = "chargeableItemChargeMass")
    @WebResult(name = "chargeableItemChargeMassResponse", targetNamespace = "http://schema.charging.ws.highdeal.com/", partName = "response")
    public ChargeableItemChargeMassResponse chargeableItemChargeMass(
        @WebParam(name = "chargeableItemChargeMassRequest", targetNamespace = "http://schema.charging.ws.highdeal.com/", partName = "request")
        ChargeableItemChargeMassRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns com.highdeal.ws.charging.schema.ChargeableItemChargeBundleResponse
     */
    @WebMethod(action = "chargeableItemChargeBundle")
    @WebResult(name = "chargeableItemChargeBundleResponse", targetNamespace = "http://schema.charging.ws.highdeal.com/", partName = "response")
    public ChargeableItemChargeBundleResponse chargeableItemChargeBundle(
        @WebParam(name = "chargeableItemChargeBundleRequest", targetNamespace = "http://schema.charging.ws.highdeal.com/", partName = "request")
        ChargeableItemChargeBundleRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns com.highdeal.ws.charging.schema.ChargeableItemCheckChargeResponse
     */
    @WebMethod(action = "chargeableItemCheckCharge")
    @WebResult(name = "chargeableItemCheckChargeResponse", targetNamespace = "http://schema.charging.ws.highdeal.com/", partName = "response")
    public ChargeableItemCheckChargeResponse chargeableItemCheckCharge(
        @WebParam(name = "chargeableItemCheckChargeRequest", targetNamespace = "http://schema.charging.ws.highdeal.com/", partName = "request")
        ChargeableItemCheckChargeRequest request);

}
