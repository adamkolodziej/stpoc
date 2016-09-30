/**
 *
 */
package com.hybris.showcase.cecs.ymarketingintegration.event;

import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;

import java.util.Map;

import org.apache.log4j.Logger;

import com.hybris.showcase.cecs.ymarketingintegration.interaction.Interaction;
import com.hybris.showcase.cecs.ymarketingintegration.interaction.InteractionManager;
import com.hybris.showcase.events.interaction.EventProps;
import com.hybris.showcase.events.interaction.InteractionEvent;


/**
 * @author marius.bogdan.ionescu@sap.com
 */
public class InteractionEventListener extends AbstractEventListener<InteractionEvent>
{
	private final static Logger LOG = Logger.getLogger(InteractionEventListener.class.getName());


	@Override
	protected void onEvent(final InteractionEvent event)
	{
		LOG.info("Triggered on InteractionEvent.");

		final Map<String, Object> props = (Map) event.getSource();

		final Interaction interaction = new Interaction();
		interaction.setUid((String) props.get(EventProps.INTERACTION_CUSTOMER_ID));
		interaction.setIdOrigin((String) props.get(EventProps.INTERACTION_ID_ORIGIN));
		interaction.setCommMedium((String) props.get(EventProps.INTERACTION_COMM_MEDIUM));
		interaction.setInitiativeId((String) props.get(EventProps.INTERACTION_INITIATIVE_ID));
		interaction.setIAType((String) props.get(EventProps.INTERACTION_INTERACTION_TYPE));
		interaction.setSourceDataURL((String) props.get(EventProps.INTERACTION_SOURCE_DATA_URL));
		interaction.setSourceObjectId((String) props.get(EventProps.INTERACTION_SOURCE_OBJECT_ID));
		interaction.setContentTitle((String) props.get(EventProps.INTERACTION_CONTENT_TITLE));
		interaction.setContentData((String) props.get(EventProps.INTERACTION_CONTENT_DATA));
		interaction.setValuation((Integer) props.get(EventProps.INTERACTION_VALUATION));
		interaction.setItemOfInterest((String) props.get(EventProps.INTEREST_ITEM));
		getInteractionManager().postInteraction(interaction);
	}

	public InteractionManager getInteractionManager()
	{
		throw new UnsupportedOperationException("To be overriden by Spring.");
	}
}
