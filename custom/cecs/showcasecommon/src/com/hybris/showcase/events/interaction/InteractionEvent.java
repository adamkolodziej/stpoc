/**
 *
 */
package com.hybris.showcase.events.interaction;

import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

import java.io.Serializable;
import java.util.Map;


/**
 * @author marius.bogdan.ionescu@sap.com
 *
 */
public class InteractionEvent extends AbstractEvent implements ClusterAwareEvent
{

	Map<String, Object> source;


	public InteractionEvent(final Serializable source)
	{
		super(source);
		if (!(source instanceof Map))
		{
			throw new IllegalArgumentException("Only Map instances supported.");
		}
		this.source = (Map) source;
	}

	public Object getParam(final String key)
	{
		return source.get(key);
	}

	public Object addParam(final String key, final String value)
	{
		return source.put(key, value);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.event.ClusterAwareEvent#publish(int, int)
	 */
	@Override
	public boolean publish(final int arg0, final int arg1)
	{
		// Publish to all nodes
		return true;
	}





}
