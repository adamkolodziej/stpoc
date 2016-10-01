
package com.sap.document.sap.soap.functions.mc_style;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.9-b130926.1035 Generated source version: 2.2
 *
 */
@WebService(name = "ZCRM_WS_BAPI_BUPA_FRG0030_ADD", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso(
{ AddBillingDataObjectFactory.class })
public interface ZCRMWSBAPIBUPAFRG0030ADD
{


	/**
	 * 
	 * @param parameters
	 * @return returns com.sap.document.sap.soap.functions.mc_style.ZbapiBupaFrg0030AddResponse
	 */
	@WebMethod(operationName = "ZbapiBupaFrg0030Add", action = "urn:sap-com:document:sap:soap:functions:mc-style:ZCRM_WS_BAPI_BUPA_FRG0030_ADD:ZbapiBupaFrg0030AddRequest")
	@WebResult(name = "ZbapiBupaFrg0030AddResponse", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", partName = "parameter")
	public ZbapiBupaFrg0030AddResponse zbapiBupaFrg0030Add(
			@WebParam(name = "ZbapiBupaFrg0030Add", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", partName = "parameters") ZbapiBupaFrg0030Add parameters);

}