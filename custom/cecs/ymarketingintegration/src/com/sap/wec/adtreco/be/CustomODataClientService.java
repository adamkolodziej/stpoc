/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.sap.wec.adtreco.be;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.olingo.odata2.api.commons.HttpStatusCodes;
import org.apache.olingo.odata2.api.edm.Edm;
import org.apache.olingo.odata2.api.edm.EdmEntityContainer;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderException;
import org.apache.olingo.odata2.api.ep.EntityProviderReadProperties;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;
import org.apache.olingo.odata2.api.exception.ODataException;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.Properties;


/**
 * CECS-426 proxy issue in SDC<br/>
 * Clone of com.sap.wec.adtreco.be.ODataClientService.
 * To handle proxy issues. All Customizations annotated.
 */
public class CustomODataClientService extends ODataClientService
{
	private static final String PROXY_HOST = "proxyhost";
	private static final String PROXY_PORT = "proxyport";
	private static final String SEPARATOR = "/";

	public static final String HTTP_METHOD_PUT = "PUT";
	public static final String HTTP_METHOD_POST = "POST";
	public static final String HTTP_METHOD_GET = "GET";
	public static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
	public static final String HTTP_HEADER_ACCEPT = "Accept";
	public static final String APPLICATION_JSON = "application/json";
	public static final String APPLICATION_XML = "application/xml";

	//Connection times in 1 sec
	private static final int CONNECTION_TIMEOUT = 5000;
	private static final int READ_TIMEOUT_DT = 5000;
	private static final int READ_TIMEOUT_RT = 5000;

	private static final Logger LOG = Logger.getLogger(CustomODataClientService.class);
	private final Properties properties = new Properties();

	//Server Cache for OData Metadata
	private static Edm METADATA_CACHE;

	// CECS-426 proxy issue in SDC
	private boolean skipDefaultProxy;

	private HttpURLConnection initializeConnection(final String absoluteUri, final String contentType, final String httpMethod,
												   final String user, final String password) throws IOException
	{
		LOG.info("Initialize connection");
		final URL url = new URL(absoluteUri);
		HttpURLConnection connection;

		// CECS-426 proxy issue in SDC
		if( skipDefaultProxy ) {
			connection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
		}
		else if (getProxyHost() != null)
		{
			final Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(getProxyHost(), getProxyPort()));
			connection = (HttpURLConnection) url.openConnection(proxy);
		}
		else
		{
			connection = (HttpURLConnection) url.openConnection();
		}
		if (connection != null)
		{
			connection.setRequestProperty(HTTP_HEADER_ACCEPT, contentType);
			connection.setRequestMethod(httpMethod);
			connection.setConnectTimeout(CONNECTION_TIMEOUT);
			connection.setReadTimeout(READ_TIMEOUT_RT);

			if (httpMethod.equals(HTTP_METHOD_POST) || httpMethod.equals(HTTP_METHOD_PUT))
			{
				connection.setDoOutput(true);
				connection.setRequestProperty(HTTP_HEADER_CONTENT_TYPE, contentType);
			}

			if (user != null)
			{
				String authorization = "Basic ";
				authorization += new String(Base64.encodeBase64((user + ":" + password).getBytes()));
				connection.setRequestProperty("Authorization", authorization);
			}
		}
		LOG.info("End of initialize connection");
		return connection;
	}

	public String getProxyHost()
	{
		return properties.getProperty(PROXY_HOST);
	}

	public int getProxyPort()
	{
		final String value = properties.getProperty(PROXY_PORT);

		try
		{
			return Integer.parseInt(value);
		}
		catch (final Exception e)
		{
			throw new RuntimeException("Invalid proxy port value '" + value + "'");
		}
	}

	public InputStream execute(final String relativeUri, final String contentType, final String httpMethod, final String user,
								final String password) throws IOException, MalformedURLException
	{
		final HttpURLConnection connection = initializeConnection(relativeUri, contentType, httpMethod, user, password);
		connection.connect();
		checkStatus(connection);
		final InputStream content = connection.getInputStream();
		return content;
	}

	private HttpStatusCodes checkStatus(final HttpURLConnection connection) throws IOException
	{
		final HttpStatusCodes httpStatusCode = HttpStatusCodes.fromStatusCode(connection.getResponseCode());
		if (400 <= httpStatusCode.getStatusCode() && httpStatusCode.getStatusCode() <= 599)
		{
			final String msg = "Http Connection failed with status " + httpStatusCode.getStatusCode() + " "
					+ httpStatusCode.toString() + "'.";
			throw new RuntimeException(msg);
		}
		return httpStatusCode;
	}

	public ODataFeed readFeed(final String serviceUri, final String contentType, final String entitySetName, final String user,
							  final String password) throws IOException, ODataException, URISyntaxException
	{
		return readFeed(serviceUri, contentType, entitySetName, null, null, null, user, password);
	}

	public ODataFeed readFeed(final String serviceUri, final String contentType, final String entitySetName, final String select,
							  final String filter, final String expand, final String user, final String password) throws ODataException,
			URISyntaxException, IOException
	{
		final String absoluteUri = createUri(serviceUri, entitySetName, null, expand, select, filter);

		EdmEntityContainer entityContainer = null;
		InputStream content = null;
		ODataFeed oDF = null;
		try
		{
			LOG.info("Start of read (feed) ");
			final Edm edm = this.readEdm(serviceUri, user, password);
			entityContainer = edm.getDefaultEntityContainer();
			content = execute(absoluteUri, contentType, HTTP_METHOD_GET, user, password);
			oDF = EntityProvider.readFeed(contentType, entityContainer.getEntitySet(entitySetName), content,
					EntityProviderReadProperties.init().build());
			LOG.info("End of read (feed) ");
		}
		catch (final MalformedURLException e)
		{
			LOG.error("HTTP Destination is not configured correctly", e);
		}
		catch (final SocketTimeoutException e)
		{
			LOG.error("Connection to CEI backend system has timed-out. System not reachable.", e);
		}
		finally
		{
			if (content != null)
			{
				try
				{
					content.close();
				}
				catch (final IOException e)
				{
					// TODO Auto-generated catch block
					// LOG.error(e);
					LOG.error("Come on, learn to use Logger...", e);
				}
			}
		}

		return oDF;
	}

	public ODataEntry readEntry(final String serviceUri, final String contentType, final String entitySetName,
								final String select, final String filter, final String keyValue, final String user, final String password)
			throws ODataException, URISyntaxException, IOException
	{

		final String absoluteUri = createUri(serviceUri, entitySetName, keyValue, "TargetGroup", select, filter);
		InputStream content = null;
		ODataEntry oDE = null;
		try
		{
			LOG.info("Start of read (Entry) ");
			final Edm edm = this.readEdm(serviceUri, user, password);
			final EdmEntityContainer entityContainer = edm.getDefaultEntityContainer();
			content = execute(absoluteUri, APPLICATION_XML, HTTP_METHOD_GET, user, password);
			oDE = EntityProvider.readEntry(contentType, entityContainer.getEntitySet(entitySetName), content,
					EntityProviderReadProperties.init().build());
			LOG.info("End of read (feed) ");
		}
		catch (final MalformedURLException e)
		{
			if (content != null){
				content.close();
			}
			LOG.error("HTTP Destination is not configured correctly", e);
		}
		catch (final SocketTimeoutException e)
		{
			if (content != null){
				content.close();
			}
			LOG.error("Connection to CEI backend system has timed-out. System not reachable.", e);
		}
		if (content != null){
			content.close();
		}
		return oDE;
	}

	protected Edm readEdm(final String serviceUrl, final String user, final String password) throws IOException,
			MalformedURLException
	{
		if (METADATA_CACHE == null)
		{
			InputStream content = null;
			content = execute(serviceUrl + SEPARATOR + "$metadata", APPLICATION_XML, HTTP_METHOD_GET, user, password);
			if (content != null)
			{
				try
				{
					METADATA_CACHE = EntityProvider.readMetadata(content, false);
				}
				catch (final EntityProviderException e)
				{
					content.close();
					LOG.error("Error reading metadata", e);
				}
				finally
				{
					if (content != null)
					{
						content.close();
					}
				}
			}
		}
		return METADATA_CACHE;
	}

	private String createUri(final String serviceUri, final String entitySetName, final String id, final String expand,
							 final String select, final String filter) throws URISyntaxException
	{
		UriBuilder uriBuilder = null;
		if (id == null)
		{
			uriBuilder = UriBuilder.serviceUri(serviceUri, entitySetName);
		}
		else
		{
			uriBuilder = UriBuilder.serviceUri(serviceUri, entitySetName, id);
		}

		uriBuilder = uriBuilder.addQuery("$expand", expand);
		uriBuilder = uriBuilder.addQuery("$select", select);
		uriBuilder = uriBuilder.addQuery("$filter", filter);

		final String absoluteURI = new URI(null, uriBuilder.build(), null).toASCIIString();

		if( LOG.isDebugEnabled() ) {
			LOG.debug("Odata URI: " + absoluteURI);
		}
		return absoluteURI;
	}

	private static class UriBuilder
	{
		private final StringBuilder uri;
		private final StringBuilder query;

		private UriBuilder(final String serviceUri, final String entitySetName)
		{
			uri = new StringBuilder(serviceUri).append(SEPARATOR).append(entitySetName);
			query = new StringBuilder();
		}

		public static UriBuilder serviceUri(final String serviceUri, final String entitySetName, final String id)
		{
			final UriBuilder b = new UriBuilder(serviceUri, entitySetName);
			return b.id(id);
		}

		public static UriBuilder serviceUri(final String serviceUri, final String entitySetName)
		{
			return new UriBuilder(serviceUri, entitySetName);
		}

		private UriBuilder id(final String id)
		{
			if (id == null)
			{
				throw new IllegalArgumentException("Null is not an allowed id");
			}
			uri.append("(").append(id).append(")");
			return this;
		}

		public UriBuilder addQuery(final String queryParameter, final String value)
		{
			if (value != null)
			{
				if (query.length() == 0)
				{
					query.append("/?");
				}
				else
				{
					query.append("&");
				}
				query.append(queryParameter).append("=").append(value);
			}
			return this;
		}

		public String build()
		{
			return uri.toString() + query.toString();
		}
	}

	// CECS-426 proxy issue in SDC
	public boolean isSkipDefaultProxy() {
		return skipDefaultProxy;
	}

	// CECS-426 proxy issue in SDC
	public void setSkipDefaultProxy(boolean skipDefaultProxy) {
		this.skipDefaultProxy = skipDefaultProxy;
	}
}
