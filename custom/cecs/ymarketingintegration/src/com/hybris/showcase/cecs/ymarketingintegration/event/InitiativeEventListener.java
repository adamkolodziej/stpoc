/**
 *
 */
package com.hybris.showcase.cecs.ymarketingintegration.event;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.hybris.showcase.cecs.ymarketingintegration.services.InitiativesService;
import com.hybris.showcase.events.interaction.EventProps;
import com.hybris.showcase.events.interaction.InitiativeEvent;


/**
 * @author marius.bogdan.ionescu@sap.com
 *
 */
public class InitiativeEventListener extends AbstractEventListener<InitiativeEvent>
{
	private final static Logger LOG = Logger.getLogger(InitiativeEventListener.class.getName());

	@Resource(name = "initiativesService")
	private InitiativesService initiativesService;

	@Override
	protected void onEvent(final InitiativeEvent event)
	{
		LOG.info("Triggered on InitiativeEvent.");

		final Map<String, Object> props = (Map) event.getSource();
		initiativesService.addLocalInitiative((CustomerModel) props.get(EventProps.CUSTOMER),
				(String) props.get(EventProps.INITIATIVE_CATEGORY_ID));
	}
}
