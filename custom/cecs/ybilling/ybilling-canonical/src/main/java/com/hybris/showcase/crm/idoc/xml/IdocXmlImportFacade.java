package com.hybris.showcase.crm.idoc.xml;

import java.io.InputStream;

import com.hybris.datahub.dto.item.ResultData;
import com.hybris.datahub.validation.ValidationException;


/**
 * CECS-587 create DataHub endpoint to load CRM iDoc XML
 * @author Sebastian Weiner
 */
public interface IdocXmlImportFacade
{
	ResultData importIdocXml(final String feedName, final String itemType, final InputStream csvInput) throws ValidationException;
}