/**
 * 
 */
package com.hybris.platform.ticketing.facades.converters.populator;

import de.hybris.platform.commercefacades.order.converters.populator.OrderPopulator;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.ticket.enums.CsEventReason;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import de.hybris.platform.ticket.events.model.CsTicketEventModel;
import de.hybris.platform.ticket.model.CsTicketModel;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hybris.platform.ticketing.data.TicketAreaData;
import com.hybris.platform.ticketing.data.TicketData;
import com.hybris.platform.ticketing.data.TicketReasonData;
import com.hybris.platform.ticketing.enums.TicketArea;
import org.springframework.beans.factory.annotation.Required;


/**
 * Default ticket populator
 * 
 * @author bartosz.mekier
 */
public class TicketPopulator implements Populator<CsTicketModel, TicketData>
{
	private static final Logger LOG = Logger.getLogger(TicketPopulator.class);

	private OrderPopulator orderPopulator;

	protected EnumerationService enumerationService;

	@Override
	public void populate(final CsTicketModel source, final TicketData target) throws ConversionException
	{
		target.setTicketID(source.getTicketID());
		target.setHeadline(source.getHeadline());
		target.setNote(source.getNote());

		if (source.getArea() != null)
		{
			target.setArea(getTicketArea(source.getArea().getCode()));
		}

		final List<CsTicketEventModel> events = source.getEvents();
		final CsEventReason csEventReason = events.stream()
				.filter(e -> e instanceof CsCustomerEventModel)
				.findAny()
				.map(e -> ((CsCustomerEventModel) e).getReason())
				.get();

		if (csEventReason != null)
		{
			target.setReason(getTicketReason(csEventReason.getCode()));
		}

		if (source.getOrder() != null)
		{
			final OrderData orderData = new OrderData();
			orderPopulator.populate((OrderModel) source.getOrder(), orderData);
			target.setOrder(orderData);
		}

		target.setDateCreated(source.getCreationtime());
		target.setC4cAttemptTicketStatusCode(enumerationService.getEnumerationName(source.getState()));
	}

	public TicketReasonData getTicketReason(String reason)
	{
		final TicketReasonData ticketReasonData = new TicketReasonData();
		ticketReasonData.setCode(reason);
		ticketReasonData.setName(enumerationService.getEnumerationName(CsEventReason.valueOf(reason)));
		return ticketReasonData;
	}

	public TicketAreaData getTicketArea(String area)
	{
		final TicketAreaData ticketAreaData = new TicketAreaData();
		ticketAreaData.setCode(area);
		ticketAreaData.setName(enumerationService.getEnumerationName(TicketArea.valueOf(area)));
		return ticketAreaData;
	}

	public EnumerationService getEnumerationService()
	{
		return enumerationService;
	}

	@Required
	public void setEnumerationService(final EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}

	public OrderPopulator getOrderPopulator() {
		return orderPopulator;
	}

	@Required
	public void setOrderPopulator(OrderPopulator orderPopulator) {
		this.orderPopulator = orderPopulator;
	}
}
