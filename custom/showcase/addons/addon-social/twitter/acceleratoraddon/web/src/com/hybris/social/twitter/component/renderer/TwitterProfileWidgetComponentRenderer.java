/**
 * 
 */
package com.hybris.social.twitter.component.renderer;

import java.util.HashMap;
import java.util.Map;

import com.hybris.social.twitter.model.TwitterProfileWidgetComponentModel;


/**
 * @author dominik.strzyzewski
 * 
 */
public class TwitterProfileWidgetComponentRenderer extends AbstractTwitterTimelineRenderer<TwitterProfileWidgetComponentModel>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.social.twitter.component.renderer.AbstractTwitterTimelineRenderer#getUrl()
	 */
	@Override
	protected String getUrl(final TwitterProfileWidgetComponentModel component)
	{
		return "https://twitter.com/" + component.getAccount().getScreenName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.social.twitter.component.renderer.AbstractTwitterTimelineRenderer#getAdditionalAttributes()
	 */
	@Override
	protected Map<String, String> getAdditionalAttributes(final TwitterProfileWidgetComponentModel component)
	{
		final Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("data-screen-name", component.getAccount().getScreenName());

		if (component.getExcludeReplies() != null && component.getExcludeReplies().booleanValue())
		{
			attributes.put("data-show-replies", "false");
		}
		else
		{
			attributes.put("data-show-replies", "true");
		}
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
	protected String getLinkDescription(final TwitterProfileWidgetComponentModel component)
	{
		return "Tweets by " + component.getAccount().getScreenName();
	}

}
