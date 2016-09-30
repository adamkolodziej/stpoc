/**
 * 
 */
package com.hybris.platform.ticketing.facades.impl;


import com.hybris.platform.ticketing.data.TicketAreaData;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.SortData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.ticket.enums.CsEventReason;
import de.hybris.platform.ticket.enums.CsInterventionType;
import de.hybris.platform.ticket.enums.CsTicketCategory;
import de.hybris.platform.ticket.enums.CsTicketPriority;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.TicketBusinessService;
import de.hybris.platform.ticket.service.TicketService;
import de.hybris.platform.ticket.service.impl.DefaultTicketBusinessService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hybris.platform.ticketing.data.TicketData;
import com.hybris.platform.ticketing.data.TicketReasonData;
import com.hybris.platform.ticketing.enums.TicketArea;
import com.hybris.platform.ticketing.facades.TicketFacade;
import org.springframework.beans.factory.annotation.Required;


/**
 * @author bartosz.mekier
 */
public class DefaultTicketFacade implements TicketFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultTicketFacade.class);

	protected TicketService ticketService;
	protected UserService userService;
	protected TicketBusinessService ticketBusinessService;
	protected ModelService modelService;
	protected EnumerationService enumerationService;
	protected CustomerAccountService customerAccountService;
	protected BaseStoreService baseStoreService;
	protected Converter<CsTicketModel, TicketData> ticketConverter;

	@Override
	public SearchPageData<TicketData> getTicketsForCustomer(final String userId, final PageableData pageableData)
	{
		final SearchPageData<TicketData> searchData = new SearchPageData<>();
		final UserModel customer = userService.getUserForUID(userId);

		final List<CsTicketModel> csTickets = ticketService.getTicketsForCustomer(customer);

		List<TicketData> tickets = Converters.convertAll(csTickets, ticketConverter);

		final int pageSize = pageableData.getPageSize();
		if (!tickets.isEmpty())
		{
			final int from = pageableData.getCurrentPage() * pageSize;
			int to = ((pageableData.getCurrentPage() + 1) * pageSize);
			if (to > tickets.size())
			{
				to = tickets.size();
			}
			searchData.setResults(tickets.subList(from, to));
			searchData.setSorts(new ArrayList<>());
			final PaginationData pD = new PaginationData();
			pD.setCurrentPage(pageableData.getCurrentPage());
			int numberOfPages = tickets.size() / pageSize;
			if (tickets.size() % pageSize > 0)
			{
				numberOfPages += 1;
			}
			pD.setNumberOfPages(numberOfPages);
			pD.setTotalNumberOfResults(tickets.size());
			pD.setPageSize(pageSize);
			searchData.setPagination(pD);
		}
		else
		{
			searchData.setResults(tickets);
			searchData.setSorts(new ArrayList<>());
			final PaginationData pD = new PaginationData();
			pD.setCurrentPage(pageableData.getCurrentPage());
			pD.setNumberOfPages(0);
			pD.setTotalNumberOfResults(0);
			pD.setPageSize(pageSize);
			searchData.setPagination(pD);
		}
		return searchData;


	}

	@Override
	public SearchPageData<TicketData> getTicketsForCustomer(final PageableData pageableData)
	{
		final UserModel customer = userService.getCurrentUser();
		return getTicketsForCustomer(customer.getUid(), pageableData);
	}

	@Override
	public TicketData createTicket(final TicketData ticket)
	{
		final CsCustomerEventModel customerEventModel = new CsCustomerEventModel();
		customerEventModel.setText(ticket.getHeadline());
		customerEventModel.setReason(enumerationService.getEnumerationValue(CsEventReason.class, ticket.getReason().getCode()));

		final CsTicketModel ticketModel = modelService.create(CsTicketModel.class);
		ticketModel.setArea(enumerationService.getEnumerationValue(TicketArea.class, ticket.getArea().getCode()));
		ticketModel.setHeadline(ticket.getHeadline());
		ticketModel.setNote(ticket.getNote());
		ticketModel.setCustomer(userService.getCurrentUser());
		ticketModel.setCategory(CsTicketCategory.COMPLAINT);
		ticketModel.setPriority(CsTicketPriority.MEDIUM);

		if (ticket.getOrder() != null)
		{
			final OrderModel ticketOrder = customerAccountService.getOrderForCode(ticket.getOrder().getCode(),
					baseStoreService.getCurrentBaseStore());
			ticketModel.setOrder(ticketOrder);
		}

		ticketBusinessService.createTicket(ticketModel, customerEventModel);

		if (StringUtils.isNotBlank(ticket.getNote()))
		{
			ticketBusinessService.addNoteToTicket(ticketModel, CsInterventionType.EMAIL, CsEventReason.UPDATE, ticket.getNote(),
					Collections.emptyList());
		}

		return ticketConverter.convert(ticketModel);
	}

	@Override
	public List<TicketReasonData> getTicketReasonTypes()
	{
		final List<CsEventReason> reasonTypes = enumerationService.getEnumerationValues(CsEventReason._TYPECODE);

		final List<TicketReasonData> reasonTypeDataList = new ArrayList<>();
		for (final CsEventReason data : reasonTypes)
		{
			reasonTypeDataList.add(getTicketReason(data.getCode()));
		}

		return reasonTypeDataList;
	}

	@Override
	public TicketReasonData getTicketReason(String reason) {
		final TicketReasonData ticketReasonData = new TicketReasonData();
		ticketReasonData.setCode(reason);
		ticketReasonData.setName(enumerationService.getEnumerationName(CsEventReason.valueOf(reason)));
		return ticketReasonData;
	}

	@Override
	public TicketAreaData getTicketArea(String area) {
		final TicketAreaData ticketAreaData = new TicketAreaData();
		ticketAreaData.setCode(area);
		ticketAreaData.setName(enumerationService.getEnumerationName(TicketArea.valueOf(area)));
		return ticketAreaData;
	}

	@Override
	public List<TicketAreaData> getTicketAreas() {
		final List<TicketArea> areas = enumerationService.getEnumerationValues(TicketArea._TYPECODE);

		final List<TicketAreaData> ticketAreaDataList = new ArrayList<>();
		for (final TicketArea data : areas)
		{
			final TicketAreaData ticketAreaData = new TicketAreaData();
			ticketAreaData.setCode(data.getCode());
			ticketAreaData.setName(enumerationService.getEnumerationName(data));
			ticketAreaDataList.add(ticketAreaData);
		}

		return ticketAreaDataList;
	}

	@Override
	public TicketData getTicketForTicketID(final String ticketID)
	{
		if (ticketID != null)
		{
			return ticketConverter.convert(ticketService.getTicketForTicketId(ticketID));
		}
		return null;
	}

	public TicketService getTicketService() {
		return ticketService;
	}

	@Required
	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Required
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public TicketBusinessService getTicketBusinessService() {
		return ticketBusinessService;
	}

	@Required
	public void setTicketBusinessService(TicketBusinessService ticketBusinessService) {
		this.ticketBusinessService = ticketBusinessService;
	}

	public ModelService getModelService() {
		return modelService;
	}

	@Required
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public EnumerationService getEnumerationService() {
		return enumerationService;
	}

	@Required
	public void setEnumerationService(EnumerationService enumerationService) {
		this.enumerationService = enumerationService;
	}

	public CustomerAccountService getCustomerAccountService() {
		return customerAccountService;
	}

	@Required
	public void setCustomerAccountService(CustomerAccountService customerAccountService) {
		this.customerAccountService = customerAccountService;
	}

	public BaseStoreService getBaseStoreService() {
		return baseStoreService;
	}

	@Required
	public void setBaseStoreService(BaseStoreService baseStoreService) {
		this.baseStoreService = baseStoreService;
	}

	public Converter<CsTicketModel, TicketData> getTicketConverter() {
		return ticketConverter;
	}

	@Required
	public void setTicketConverter(Converter<CsTicketModel, TicketData> ticketConverter) {
		this.ticketConverter = ticketConverter;
	}
}
