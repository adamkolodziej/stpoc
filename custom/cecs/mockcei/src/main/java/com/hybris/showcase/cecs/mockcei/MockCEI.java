package com.hybris.showcase.cecs.mockcei;

import org.apache.commons.io.IOUtils;
import org.mockserver.integration.ClientAndServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


public class MockCEI {

    public static final int MOCK_SERVER_PORT = 9500;

    private String initiativesStaticResponse;
    private String metadataStaticResponse;

    private String initiative1Response;
    private String initiative2Response;
    private String initiative3Response;
    private String initiative4Response;

    private ClientAndServer mockServer;

    public static void main(final String[] args) throws InterruptedException, IOException {
        final MockCEI mockProcess = new MockCEI();
        mockProcess.run();
    }

    public void run() throws IOException {
        initData();
        mockServer = ClientAndServer.startClientAndServer(MOCK_SERVER_PORT);

        mockServer.when(
                request().withMethod("GET").withPath("/sap/opu/odata/sap/CUAN_COMMON_SRV/Initiatives/"))
                .respond(
                        response().withStatusCode(200).withHeader("Content-Type", "application/xml").withBody(initiativesStaticResponse)
                );

        mockServer.when(
                request().withMethod("GET").withPath("/sap/opu/odata/sap/CUAN_COMMON_SRV/$metadata"))
                .respond(
                        response().withStatusCode(200).withHeader("Content-Type", "application/xml").withBody(metadataStaticResponse)
                );

        mockServer.when(
                request().withMethod("GET").withPath("/sap/opu/odata/sap/CUAN_COMMON_SRV/Initiatives('0000000001')/"))
                .respond(
                        response().withStatusCode(200).withHeader("Content-Type", "application/xml").withBody(initiative1Response)
        );
        mockServer.when(
                request().withMethod("GET").withPath("/sap/opu/odata/sap/CUAN_COMMON_SRV/Initiatives('0000000002')/"))
                .respond(
                        response().withStatusCode(200).withHeader("Content-Type", "application/xml").withBody(initiative2Response)
        );
        mockServer.when(
                request().withMethod("GET").withPath("/sap/opu/odata/sap/CUAN_COMMON_SRV/Initiatives('0000000003')/"))
                .respond(
                        response().withStatusCode(200).withHeader("Content-Type", "application/xml").withBody(initiative3Response)
        );
        mockServer.when(
                request().withMethod("GET").withPath("/sap/opu/odata/sap/CUAN_COMMON_SRV/Initiatives('0000000004')/"))
                .respond(
                        response().withStatusCode(200).withHeader("Content-Type", "application/xml").withBody(initiative4Response)
                );
    }

	private void initData() throws IOException
	{
		initiativesStaticResponse = readResponseFile("/data/initiatives.xml");
		metadataStaticResponse = readResponseFile("/data/metadata.xml");
        initiative4Response = readResponseFile("/data/initiatives/0000000004.xml");
		initiative3Response = readResponseFile("/data/initiatives/0000000003.xml");
		initiative2Response = readResponseFile("/data/initiatives/0000000002.xml");
		initiative1Response = readResponseFile("/data/initiatives/0000000001.xml");
	}

	private String readResponseFile(final String path) throws IOException
	{
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		IOUtils.copy(this.getClass().getResourceAsStream(path), bos);
		return bos.toString();
	}

}

