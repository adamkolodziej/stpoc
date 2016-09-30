package com.hybris.mb2bshowcase.c4cintegration.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.mb2bshowcase.c4cintegration.services.C4CServiceException;
import com.hybris.mb2bshowcase.c4cintegration.services.C4CTicketService;
import com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase.*;


/**
 * Service to create and search ticket in C4C
 * 
 * @author ddyrcz
 */
public class DefaultC4CTicketService extends C4CWebService implements C4CTicketService
{
	private static final Logger LOG = Logger.getLogger(DefaultC4CTicketService.class);
	private static final String CREATE_TICKET_SERVICE_CALL_ERROR = "Call C4C maintainBundle for quote web service error.";
	private static final String FIND_TICKET_WEB_SERVICE_CALL_ERROR = "Call C4C find ticket web service error.";
	private static final String CREATE_ACTION = "01";
	public static final String TYPE_CODE_EQUAL_TO = "1";
	public static final String RESPONSE_LIST_SIZE_MSG_ERROR = "Response list size not equals to 1, wrong master ticket ID";

	public static final String HARDCODED_DATA_ORIGIN_TYPE_CODE = "4";
	public static final String HARDCODED_PROCESSING_TYPE_CODE = "SRRQ";
	public static final String INCLUSION_CODE = "I";
	public static final String SLAVE_RELATION_CODE = "1";

	private ManageServiceRequestIn manageServiceRequestIn;
	private QueryServiceRequestIn queryServiceRequestIn;
	private String ticketsQueryEndpointAddress;
	private String ticketsManageEndpointAddress;

	@Override
	public String createAndSendMasterTicket(final String c4cCustomerId, final String headline, final String note)
	{
		try
		{
			final ServiceRequestMaintainRequestBundleMessageSync request = createRequest(c4cCustomerId, headline, note);

			final ServiceRequestMaintainConfirmationBundleMessageSync response = getManageServiceRequestIn().maintainBundle(request);
			if (response.getServiceRequest().size() == 1)
			{
				return response.getServiceRequest().get(0).getID().getValue();
			}
			throw new IndexOutOfBoundsException("The response hasn't one created element");
		}
		catch (final StandardFaultMessage_Exception e)
		{
			// LOG.error(CREATE_TICKET_SERVICE_CALL_ERROR, e);
			throw new C4CServiceException(CREATE_TICKET_SERVICE_CALL_ERROR, e);
		}
	}

	private ServiceRequestMaintainRequestBundleMessageSync createRequest(final String c4cCustomerId, final String headline,
			final String note)
	{
		final ServiceRequestMaintainRequestBundleMessageSync request = new ServiceRequestMaintainRequestBundleMessageSync();
		final ServiceRequestMaintainRequestBundle serviceRequest = createServiceRequest(c4cCustomerId, headline, note);
		request.getServiceRequest().add(serviceRequest);
		return request;
	}

	@Override
	public String getTicketsLifeCycleStatusCode(final String masterTicketId)
	{
		try
		{
			final ServiceRequestByElementsQueryMessageSync request = createServiceRequestByElementsQueryMessageSync(masterTicketId);
			final ServiceRequestByElementsResponseMessageSync response = getQueryServiceRequestIn().findByElements(request);
			return response.getServiceRequest().get(0).getLifeCycleStatusCode();
		}
		catch (final StandardFaultMessage_Exception e)
		{
			throw new C4CServiceException(FIND_TICKET_WEB_SERVICE_CALL_ERROR, e);
		}
	}

	private ServiceRequestByElementsQueryMessageSync createServiceRequestByElementsQueryMessageSync(final String masterTicketId)
	{
		final ServiceRequestByElementsQueryMessageSync request = new ServiceRequestByElementsQueryMessageSync();
		request.setServiceRequestSelectionByElements(createServiceRequestSelectionByElements(masterTicketId));
		request.setProcessingConditions(createProcessingConditions(false));
		return request;
	}

	private QueryProcessingConditions createProcessingConditions(final Boolean value)
	{
		final QueryProcessingConditions processingConditions = new QueryProcessingConditions();
		processingConditions.setQueryHitsUnlimitedIndicator(value);
		return processingConditions;
	}

	private ServiceRequestByElementsQuerySelectionByElements createServiceRequestSelectionByElements(final String masterTicketId)
	{
		final ServiceRequestByElementsQuerySelectionByElements request = new ServiceRequestByElementsQuerySelectionByElements();
		request.getSelectionByID().add(createSelectionByIdentifier(masterTicketId));
		return request;
	}

	private SelectionByIdentifier createSelectionByIdentifier(final String masterTicketId)
	{
		final SelectionByIdentifier criteria = new SelectionByIdentifier();
		criteria.setInclusionExclusionCode(INCLUSION_CODE);
		criteria.setIntervalBoundaryTypeCode(TYPE_CODE_EQUAL_TO);
		criteria.setLowerBoundaryIdentifier(masterTicketId);
		return criteria;
	}

	private ServiceRequestMaintainRequestBundle createServiceRequest(final String c4cCustomerId, final String headline,
			final String note)
	{
		final ServiceRequestMaintainRequestBundle request = new ServiceRequestMaintainRequestBundle();
		request.setActionCode(CREATE_ACTION);
		request.setProcessingTypeCode(HARDCODED_PROCESSING_TYPE_CODE);
		request.setName(headline);
		request.setDataOriginTypeCode(HARDCODED_DATA_ORIGIN_TYPE_CODE);

		final ServiceRequestMaintainRequestBundleText text = new ServiceRequestMaintainRequestBundleText();
		text.setContent(note);

		request.getText().add(text);
		try
		{
			request.setBuyerParty(createBuyerParty(c4cCustomerId));
		}
		catch (final ClassCastException e)
		{
			throw new ClassCastException("Order's user is not a Tricast Customer");
		}

		return request;
	}

	private ServiceRequestMaintainRequestBundleBuyerParty createBuyerParty(final String hybrisUserId)
	{
		final ServiceRequestMaintainRequestBundleBuyerParty request = new ServiceRequestMaintainRequestBundleBuyerParty();
		request.setBusinessPartnerInternalID(hybrisUserId);
		return request;
	}

	@Required
	public void setManageServiceRequestIn(final ManageServiceRequestIn manageServiceRequestIn)
	{
		this.manageServiceRequestIn = manageServiceRequestIn;
	}

	public void setQueryServiceRequestIn(final QueryServiceRequestIn queryServiceRequestIn)
	{
		this.queryServiceRequestIn = queryServiceRequestIn;
	}

	public QueryServiceRequestIn getQueryServiceRequestIn()
	{
		setUpPort(queryServiceRequestIn, ticketsQueryEndpointAddress);
		return queryServiceRequestIn;
	}

	public ManageServiceRequestIn getManageServiceRequestIn()
	{
		setUpPort(manageServiceRequestIn, ticketsManageEndpointAddress);
		return manageServiceRequestIn;
	}

	@Required
	public void setTicketsQueryEndpointAddress(String ticketsQueryEndpointAddress)
	{
		this.ticketsQueryEndpointAddress = ticketsQueryEndpointAddress;
	}

	public String getTicketsQueryEndpointAddress()
	{
		return ticketsQueryEndpointAddress;
	}

	@Required
	public void setTicketsManageEndpointAddress(String ticketsManageEndpointAddress)
	{
		this.ticketsManageEndpointAddress = ticketsManageEndpointAddress;
	}

	public String getTicketsManageEndpointAddress()
	{
		return ticketsManageEndpointAddress;
	}
}