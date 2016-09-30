package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the
 * com.sap.document.sap.soap.functions.mc_style package.
 * <p>
 * An CreateBusinessAgreementObjectFactory allows you to programatically construct new instances of the Java
 * representation for XML content. The Java representation of XML content can consist of schema derived interfaces and
 * classes representing the binding of schema type definitions, element declarations and model groups. Factory methods
 * for each of these are provided in this class.
 *
 */
@XmlRegistry
public class CreateBusinessAgreementObjectFactory
{

	private final static QName _ZcrmBuagCreateException_QNAME = new QName("urn:sap-com:document:sap:soap:functions:mc-style",
			"ZcrmBuagCreate.Exception");

	/**
	 * Create a new CreateBusinessAgreementObjectFactory that can be used to create new instances of schema derived
	 * classes for package: com.sap.document.sap.soap.functions.mc_style
	 *
	 */
	public CreateBusinessAgreementObjectFactory()
	{
	}

	/**
	 * Create an instance of {@link ZcrmBuagCreateResponse }
	 *
	 */
	public ZcrmBuagCreateResponse createZcrmBuagCreateResponse()
	{
		return new ZcrmBuagCreateResponse();
	}

	/**
	 * Create an instance of {@link Bapibus1006130GnrlDataExp }
	 *
	 */
	public Bapibus1006130GnrlDataExp createBapibus1006130GnrlDataExp()
	{
		return new Bapibus1006130GnrlDataExp();
	}

	/**
	 * Create an instance of {@link Bapibus1006130SpecDataExp }
	 *
	 */
	public Bapibus1006130SpecDataExp createBapibus1006130SpecDataExp()
	{
		return new Bapibus1006130SpecDataExp();
	}

	/**
	 * Create an instance of {@link Bapiret2T }
	 *
	 */
	public Bapiret2T createBapiret2T()
	{
		return new Bapiret2T();
	}

	/**
	 * Create an instance of {@link ZcrmBuagCreate }
	 *
	 */
	public ZcrmBuagCreate createZcrmBuagCreate()
	{
		return new ZcrmBuagCreate();
	}

	/**
	 * Create an instance of {@link CrmtBuagTemplStruc }
	 *
	 */
	public CrmtBuagTemplStruc createCrmtBuagTemplStruc()
	{
		return new CrmtBuagTemplStruc();
	}

	/**
	 * Create an instance of {@link Bapibus1006130RelDataT }
	 *
	 */
	public Bapibus1006130RelDataT createBapibus1006130RelDataT()
	{
		return new Bapibus1006130RelDataT();
	}

	/**
	 * Create an instance of {@link ZcrmBuagCreateRfcException }
	 *
	 */
	public ZcrmBuagCreateRfcException createZcrmBuagCreateRfcException()
	{
		return new ZcrmBuagCreateRfcException();
	}

	/**
	 * Create an instance of {@link RfcExceptionMessage }
	 *
	 */
	public RfcExceptionMessage createRfcExceptionMessage()
	{
		return new RfcExceptionMessage();
	}

	/**
	 * Create an instance of {@link Bapibus1006130RelData }
	 *
	 */
	public Bapibus1006130RelData createBapibus1006130RelData()
	{
		return new Bapibus1006130RelData();
	}

	/**
	 * Create an instance of {@link Bapiret2 }
	 *
	 */
	public Bapiret2 createBapiret2()
	{
		return new Bapiret2();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ZcrmBuagCreateRfcException }{@code >}}
	 *
	 */
	@XmlElementDecl(namespace = "urn:sap-com:document:sap:soap:functions:mc-style", name = "ZcrmBuagCreate.Exception")
	public JAXBElement<ZcrmBuagCreateRfcException> createZcrmBuagCreateException(final ZcrmBuagCreateRfcException value)
	{
		return new JAXBElement<ZcrmBuagCreateRfcException>(_ZcrmBuagCreateException_QNAME, ZcrmBuagCreateRfcException.class, null,
				value);
	}

}
