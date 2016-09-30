/**
 * 
 */
package com.hybris.platform.ticketing.forms;

/**
 * @author zsolt.jenei
 * 
 */
public class TicketForm
{
	String reason;
	String area;
	String headline;
	String note;
	String ticketID;
	String orderID;

	public String getReason()
	{
		return reason;
	}

	public void setReason(final String reason)
	{
		this.reason = reason;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(final String area)
	{
		this.area = area;
	}

	public String getHeadline()
	{
		return headline;
	}

	public void setHeadline(final String headline)
	{
		this.headline = headline;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(final String note)
	{
		this.note = note;
	}

	public String getTicketID()
	{
		return ticketID;
	}

	public void setTicketID(final String ticketID)
	{
		this.ticketID = ticketID;
	}

	public String getOrderID()
	{
		return orderID;
	}

	public void setOrderID(final String orderID)
	{
		this.orderID = orderID;
	}

}
