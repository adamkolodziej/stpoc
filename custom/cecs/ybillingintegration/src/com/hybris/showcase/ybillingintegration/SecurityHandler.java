/**
 *
 */
package com.hybris.showcase.ybillingintegration;

import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.util.Set;
import java.util.TreeSet;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;


/**
 * @author Rafal Zymla
 *
 */
public class SecurityHandler implements SOAPHandler<SOAPMessageContext>
{

	private final static Logger log = Logger.getLogger(SecurityHandler.class);

	private ConfigurationService configurationService;

	private static final String WS_CC_USERNAME_KEY = "ybillingintegration.cc.ws.username";
	private static final String WS_CC_PASSWD_KEY = "ybillingintegration.cc.ws.password";

	@Override
	public boolean handleMessage(final SOAPMessageContext context)
	{
		final Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (outbound)
		{
			try
			{
				final SOAPMessage msg = context.getMessage();
				final SOAPPart sp = msg.getSOAPPart();
				final SOAPEnvelope envelope = sp.getEnvelope();
				SOAPHeader header = envelope.getHeader();
				if (header == null)
				{
					log.info("No header found");
					header = envelope.addHeader();
				}
				//				final WSSecHeader wsheader = new WSSecHeader();
				//				// wsheader.setMustUnderstand(false);
				//				wsheader.insertSecurityHeader(sp);
				//				final WSSecUsernameToken token = new WSSecUsernameToken();
				//				token.setPasswordType(WSConstants.PASSWORD_TEXT);
				//				final String userName = configurationService.getConfiguration().getString(WS_CC_USERNAME_KEY);
				//				final String passwd = configurationService.getConfiguration().getString(WS_CC_PASSWD_KEY);
				//				token.setUserInfo(userName, passwd);
				//				token.build(sp, wsheader);
				final String uri = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
				final SOAPFactory factory = SOAPFactory.newInstance();
				final SOAPElement securityElem = factory.createElement("Security", "wsse", uri);
				final SOAPElement userElem = factory.createElement("UsernameToken", "wsse", uri);
				final SOAPElement username = factory.createElement("Username", "wsse", uri);
				username.setTextContent(configurationService.getConfiguration().getString(WS_CC_USERNAME_KEY));
				final SOAPElement password = factory.createElement("Password", "wsse", uri);
				password.setTextContent(configurationService.getConfiguration().getString(WS_CC_PASSWD_KEY));
				userElem.addChildElement(username);
				userElem.addChildElement(password);
				securityElem.addChildElement(userElem);
				header.addChildElement(securityElem);


			}
			catch (final Exception e)
			{
				e.printStackTrace();
			}
		}
		return true;
	}


	@Override
	public boolean handleFault(final SOAPMessageContext context)
	{
		return true;
	}

	@Override
	public void close(final MessageContext context)
	{
		// no-op
	}

	@Override
	public Set<QName> getHeaders()
	{
		return new TreeSet<QName>();
	}

	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}
}