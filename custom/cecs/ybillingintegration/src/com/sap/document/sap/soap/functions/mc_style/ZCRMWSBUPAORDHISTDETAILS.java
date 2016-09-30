
package com.sap.document.sap.soap.functions.mc_style;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ZCRM_WS_BUPA_ORD_HIST_DETAILS", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    OrderDetailsObjectFactory.class
})
public interface ZCRMWSBUPAORDHISTDETAILS {


    /**
     * 
     * @param parameters
     * @return
     *     returns com.sap.document.sap.soap.functions.mc_style.ZcrmBupaOrderHistDetailsResponse
     */
    @WebMethod(operationName = "ZcrmBupaOrderHistDetails", action = "urn:sap-com:document:sap:soap:functions:mc-style:ZCRM_WS_BUPA_ORD_HIST_DETAILS:ZcrmBupaOrderHistDetailsRequest")
    @WebResult(name = "ZcrmBupaOrderHistDetailsResponse", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", partName = "parameter")
    public ZcrmBupaOrderHistDetailsResponse zcrmBupaOrderHistDetails(
        @WebParam(name = "ZcrmBupaOrderHistDetails", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", partName = "parameters")
        ZcrmBupaOrderHistDetails parameters);

}
