package com.hybris.showcase.crm.idoc.xml.rest.resources;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.datahub.dto.item.ResultData;
import com.hybris.datahub.validation.ValidationException;
import com.hybris.showcase.crm.idoc.xml.IdocXmlImportFacade;

/**
 * CECS-587 create DataHub endpoint to load CRM iDoc XML
 */
@Path("/data-feeds/{feedName}/items/RawConfigurableProduct/idoc")
@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
public class IdocXmlImportResource
{
	private static final Logger logger = LoggerFactory.getLogger(IdocXmlImportResource.class);

	private IdocXmlImportFacade idocXmlImportFacade;

	@POST
	public Response importIdocXmlFile(@PathParam("feedName") final String feedName, final InputStream in) throws ValidationException
	{
		String type = "RawConfigurableProduct";
		logger.info("Received request to import IDOC data for type " + type + " into data feed " + feedName);

		final ResultData result = idocXmlImportFacade.importIdocXml(feedName, type, in);

		return Response.ok(result).build();
	}

	public IdocXmlImportFacade getIdocXmlImportFacade() {
		return idocXmlImportFacade;
	}

	@Required
	public void setIdocXmlImportFacade(IdocXmlImportFacade idocXmlImportFacade) {
		this.idocXmlImportFacade = idocXmlImportFacade;
	}
}
