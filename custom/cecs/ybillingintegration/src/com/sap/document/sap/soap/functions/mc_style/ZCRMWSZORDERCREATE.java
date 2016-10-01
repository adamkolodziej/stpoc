
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
@WebService(name = "ZCRM_WS_ZORDER_CREATE", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    CreateOrderObjectFactory.class
})
public interface ZCRMWSZORDERCREATE {


    /**
     * 
     * @param parameters
     * @return
     *     returns com.sap.document.sap.soap.functions.mc_style.ZcrmIsxBtxApiOrderCreateResponse
     */
    @WebMethod(operationName = "ZcrmIsxBtxApiOrderCreate", action = "urn:sap-com:document:sap:soap:functions:mc-style:ZCRM_WS_ZORDER_CREATE:ZcrmIsxBtxApiOrderCreateRequest")
    @WebResult(name = "ZcrmIsxBtxApiOrderCreateResponse", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", partName = "parameter")
    public ZcrmIsxBtxApiOrderCreateResponse zcrmIsxBtxApiOrderCreate(
        @WebParam(name = "ZcrmIsxBtxApiOrderCreate", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", partName = "parameters")
        ZcrmIsxBtxApiOrderCreate parameters);

}