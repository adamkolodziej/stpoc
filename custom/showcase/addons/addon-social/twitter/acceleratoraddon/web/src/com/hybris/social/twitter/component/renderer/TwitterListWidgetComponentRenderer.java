/**
 * 
 */
package com.hybris.social.twitter.component.renderer;

import java.util.HashMap;
import java.util.Map;

import com.hybris.social.twitter.model.TwitterListWidgetComponentModel;


/**
 * @author dominik.strzyzewski
 * 
 */
public class TwitterListWidgetComponentRenderer extends AbstractTwitterTimelineRenderer<TwitterListWidgetComponentModel>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.social.twitter.component.renderer.AbstractTwitterTimelineRenderer#getUrl(com.hybris.social.twitter.
	 * model.AbstractTwitterWidgetComponentModel)
	 */
	@Override
	protected String getUrl(final TwitterListWidgetComponentModel component)
	{
		return "https://twitter.com/" + component.getAccount().getScreenName() + "/" + component.getListName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.social.twitter.component.renderer.AbstractTwitterTimelineRenderer#getAdditionalAttributes(com.hybris
	 * .social.twitter.model.AbstractTwitterWidgetComponentModel)
	 */
	@Override
	protected Map<String, String> getAdditionalAttributes(final TwitterListWidgetComponentModel component)
	{
		final Map<String, String> attributes = new HashMap<>();

		attributes.put("data-list-owner-screen-name", component.getAccount().getScreenName());
		attributes.put("data-list-slug", component.getListName());

		return attributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.social.twitter.component.renderer.AbstractTwitterTimelineRenderer#getLinkDescription(com.hybris.social
	 * .twitter.model.AbstractTwitterWidgetComponentModel)
	 */
	@Override
	protected String getLinkDescription(final TwitterListWidgetComponentModel component)
	{
		return "Tweets from @" + component.getAccount().getScreenName() + "/" + component.getListName();
	}

}
