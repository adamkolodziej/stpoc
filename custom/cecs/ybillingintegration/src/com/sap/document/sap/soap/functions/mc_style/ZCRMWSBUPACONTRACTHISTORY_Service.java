
package com.sap.document.sap.soap.functions.mc_style;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ZCRM_WS_BUPA_CONTRACT_HISTORY", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", wsdlLocation = "file:/C:/work/Temp/temp/WS%20ZBUPA_CONT_HISTORY.WSDL")
public class ZCRMWSBUPACONTRACTHISTORY_Service
    extends Service
{

    private final static URL ZCRMWSBUPACONTRACTHISTORY_WSDL_LOCATION;
    private final static WebServiceException ZCRMWSBUPACONTRACTHISTORY_EXCEPTION;
    private final static QName ZCRMWSBUPACONTRACTHISTORY_QNAME = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZCRM_WS_BUPA_CONTRACT_HISTORY");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/work/Temp/temp/WS%20ZBUPA_CONT_HISTORY.WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ZCRMWSBUPACONTRACTHISTORY_WSDL_LOCATION = url;
        ZCRMWSBUPACONTRACTHISTORY_EXCEPTION = e;
    }

    public ZCRMWSBUPACONTRACTHISTORY_Service() {
        super(__getWsdlLocation(), ZCRMWSBUPACONTRACTHISTORY_QNAME);
    }

    public ZCRMWSBUPACONTRACTHISTORY_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), ZCRMWSBUPACONTRACTHISTORY_QNAME, features);
    }

    public ZCRMWSBUPACONTRACTHISTORY_Service(URL wsdlLocation) {
        super(wsdlLocation, ZCRMWSBUPACONTRACTHISTORY_QNAME);
    }

    public ZCRMWSBUPACONTRACTHISTORY_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ZCRMWSBUPACONTRACTHISTORY_QNAME, features);
    }

    public ZCRMWSBUPACONTRACTHISTORY_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ZCRMWSBUPACONTRACTHISTORY_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ZCRMWSBUPACONTRACTHISTORY
     */
    @WebEndpoint(name = "ZBupaContHistBinding")
    public ZCRMWSBUPACONTRACTHISTORY getZBupaContHistBinding() {
        return super.getPort(new QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZBupaContHistBinding"), ZCRMWSBUPACONTRACTHISTORY.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ZCRMWSBUPACONTRACTHISTORY
     */
    @WebEndpoint(name = "ZBupaContHistBinding")
    public ZCRMWSBUPACONTRACTHISTORY getZBupaContHistBinding(WebServiceFeature... features) {
        return super.getPort(new QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZBupaContHistBinding"), ZCRMWSBUPACONTRACTHISTORY.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ZCRMWSBUPACONTRACTHISTORY_EXCEPTION!= null) {
            throw ZCRMWSBUPACONTRACTHISTORY_EXCEPTION;
        }
        return ZCRMWSBUPACONTRACTHISTORY_WSDL_LOCATION;
    }

}
