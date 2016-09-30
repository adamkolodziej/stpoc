/**
 *
 */
package com.hybris.showcase.ybillingintegration;

import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;


/**
 * @author Rafal Zymla
 *
 */
public class DefaultHandlerResolver implements HandlerResolver
{

	private List<Handler> handlerList;



	@Override

	public List<Handler> getHandlerChain(final PortInfo portInfo)
	{

		return handlerList;

	}



	public void setHandlerList(final List<Handler> handlerList)
	{

		this.handlerList = handlerList;

	}


}
