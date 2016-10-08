/**
 * 
 */
package com.hybris.social.twitter.component.renderer;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.hybris.social.twitter.model.TwitterFavesWidgetComponentModel;


/**
 * @author dominik.strzyzewski
 * 
 */
public class TwitterFavesWidgetComponentRenderer extends AbstractTwitterTimelineRenderer<TwitterFavesWidgetComponentModel>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.social.twitter.component.renderer.AbstractTwitterTimelineRenderer#getUrl(com.hybris.social.twitter.
	 * model.AbstractTwitterWidgetComponentModel)
	 */
	@Override
	protected String getUrl(final TwitterFavesWidgetComponentModel component)
	{
		return "https://twitter.com/" + component.getAccount().getScreenName() + "/favorites";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.social.twitter.component.renderer.AbstractTwitterTimelineRenderer#getAdditionalAttributes(com.hybris
	 * .social.twitter.model.AbstractTwitterWidgetComponentModel)
	 */
	@Override
	protected Map<String, String> getAdditionalAttributes(final TwitterFavesWidgetComponentModel component)
	{
		return ImmutableMap.of("data-favorites-screen-name", component.getAccount().getScreenName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.social.twitter.component.renderer.AbstractTwitterTimelineRenderer#getLinkDescription(com.hybris.social
	 * .twitter.model.AbstractTwitterWidgetComponentModel)
	 */
	@Override
	protected String getLinkDescription(final TwitterFavesWidgetComponentModel component)
	{
		return "Favourite Tweets by @" + component.getAccount().getScreenName();
	}

}
