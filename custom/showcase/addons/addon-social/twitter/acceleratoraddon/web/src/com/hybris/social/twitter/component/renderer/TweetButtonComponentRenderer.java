/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 * 
 */
package com.hybris.social.twitter.component.renderer;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.jsp.PageContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.common.url.SharedPageUrlStrategy;
import com.hybris.social.twitter.model.AbstractShareTweetButtonComponentModel;
import com.hybris.social.twitter.model.AbstractTweetButtonComponentModel;
import com.hybris.social.twitter.model.TweetHashtagButtonComponentModel;
import com.hybris.social.twitter.model.TweetLinkButtonComponentModel;
import com.hybris.social.twitter.model.TweetMentionButtonComponentModel;
import com.hybris.social.twitter.model.TwitterAccountModel;
import com.hybris.social.urlshortening.enums.UrlShorteningProvider;
import com.hybris.social.urlshortening.service.provider.UrlShorteningProviderConfigurationException;
import com.hybris.social.urlshortening.service.provider.UrlShorteningProviderException;


/**
 * Renders the html for a twitter tweet button according to the configuration on the provided cms component.
 * 
 * @author rmcotton
 * 
 */
public class TweetButtonComponentRenderer extends AbstractTwitterButtonComponentRenderer<AbstractTweetButtonComponentModel>
{
	private static final Logger LOG = Logger.getLogger(TweetButtonComponentRenderer.class);
	private SharedPageUrlStrategy sharePageUrlStrategy;

	@Override
	protected String getHrefParameterString(final PageContext pageContext, final AbstractTweetButtonComponentModel component)
	{
		final List<KeyValue> values = getHrefParameters(pageContext, component);
		return super.buildParameterString(values);
	}

	protected List<KeyValue> getHrefParameters(final PageContext pageContext, final AbstractTweetButtonComponentModel component)
	{
		final List<KeyValue> values = new LinkedList<KeyValue>();
		addIfNotBlank(values, "screen_name", getScreenName(component));
		addIfNotBlank(values, "text", getTweetText(component));
		addIfNotBlank(values, "button_hashtag", getHashtag(component));
		return values;
	}

	@Override
	protected List<KeyValue> getDataParameters(final PageContext pageContext, final AbstractTweetButtonComponentModel component)
	{
		final List<KeyValue> values = new LinkedList<KeyValue>();
		addIfNotBlank(values, "data-url", getUrlToShare(pageContext, component));
		addIfNotBlank(values, "data-counturl", getCountUrl(pageContext, component));
		addIfNotBlank(values, "data-count", getCount(component));
		addIfNotBlank(values, "data-lang", getLang());
		addIfNotBlank(values, "data-size", getButtonSize(component));
		addIfNotBlank(values, "data-hashtags", getHashtags(component));
		addIfNotBlank(values, "data-via", getVia(component));
		addIfNotBlank(values, "data-related", getRelated(component));
		return values;
	}

	protected String getRelated(final AbstractTweetButtonComponentModel component)
	{
		final List<TwitterAccountModel> related = new LinkedList<TwitterAccountModel>(component.getRecommended());
		if (CollectionUtils.isNotEmpty(related))
		{
			final StringBuilder output = new StringBuilder();
			for (final ListIterator<TwitterAccountModel> li = related.listIterator(); li.hasNext();)
			{
				final TwitterAccountModel account = li.next();
				output.append(account.getScreenName());
				if (StringUtils.isNotBlank(account.getSummary()))
				{
					output.append(":").append(account.getSummary());
				}
				if (li.hasNext())
				{
					output.append(",");
				}
			}
			return output.toString();
		}
		return null;
	}

	protected String getVia(final AbstractTweetButtonComponentModel component)
	{
		if (isShareLinkButton(component))
		{
			if (((TweetLinkButtonComponentModel) component).getVia() != null)
			{
				return ((TweetLinkButtonComponentModel) component).getVia().getScreenName();
			}
		}
		return null;
	}

	protected String getHashtags(final AbstractTweetButtonComponentModel component)
	{
		if (isShareLinkButton(component))
		{
			final List<String> hashtags = ((TweetLinkButtonComponentModel) component).getHashtags();
			if (CollectionUtils.isEmpty(hashtags))
			{
				return null;
			}
			final StringBuilder result = new StringBuilder();
			for (final ListIterator<String> li = hashtags.listIterator(); li.hasNext();)
			{
				result.append(li.next());
				if (li.hasNext())
				{
					result.append(",");
				}
			}
			return result.toString();
		}
		return null;
	}

	protected String getButtonSize(final AbstractTweetButtonComponentModel component)
	{
		return component.getButtonSize().getCode();
	}


	protected String getUrlToShare(final PageContext pageContext, final AbstractTweetButtonComponentModel component)
	{
		if (component instanceof AbstractShareTweetButtonComponentModel)
		{
			final AbstractShareTweetButtonComponentModel shareComponent = (AbstractShareTweetButtonComponentModel) component;
			if (StringUtils.isNotBlank(shareComponent.getShortUrlToShare()))
			{
				return shareComponent.getShortUrlToShare();
			}
			else
			{
				if (Boolean.TRUE.equals(shareComponent.getAutoShortenUrl()) && StringUtils.isNotBlank(shareComponent.getUrlToShare()))
				{
					return shorten(shareComponent.getUrlToShare(), shareComponent.getUrlShorteningProvider());
				}
				else if (Boolean.TRUE.equals(shareComponent.getAutoShortenUrl())
						&& StringUtils.isBlank(shareComponent.getUrlToShare()))
				{
					return shorten(getSharedPageUrlStrategy().getUrl(pageContext), shareComponent.getUrlShorteningProvider());
				}
				else if (StringUtils.isNotBlank(shareComponent.getUrlToShare()))
				{
					return shareComponent.getUrlToShare();
				}
			}
		}
		return null;
	}

	protected String shorten(final String url, final UrlShorteningProvider provider)
	{
		try
		{
			return getUrlShorteningService().shorten(url, provider);
		}
		catch (final UrlShorteningProviderConfigurationException e)
		{
			LOG.warn("unable to shorten URL due to incomplete configuration:" + e.getMessage());
		}
		catch (final UrlShorteningProviderException e)
		{
			LOG.error("unable to shorten URL", e);
		}
		catch (final Exception e)
		{
			LOG.error("unexpected error shortening URL", e);
		}
		return null;
	}

	protected String getCountUrl(final PageContext pageContext, final AbstractTweetButtonComponentModel component)
	{
		if (component instanceof AbstractShareTweetButtonComponentModel)
		{
			final AbstractShareTweetButtonComponentModel shareComponent = (AbstractShareTweetButtonComponentModel) component;
			return StringUtils.isNotBlank(shareComponent.getUrlToShare()) ? shareComponent.getUrlToShare() : null;
		}
		return null;
	}

	protected String getCount(final AbstractTweetButtonComponentModel component)
	{
		if (isShareLinkButton(component))
		{
			return ((TweetLinkButtonComponentModel) component).getCountBoxPosition().getCode();
		}
		return null;
	}

	protected String getScreenName(final AbstractTweetButtonComponentModel component)
	{
		if (isMentionButton(component))
		{
			return ((TweetMentionButtonComponentModel) component).getTweetTo().getScreenName();
		}
		return null;
	}

	protected String getTweetText(final AbstractTweetButtonComponentModel component)
	{
		return component.getTweetText();
	}

	protected String getHashtag(final AbstractTweetButtonComponentModel component)
	{
		if (isHashtagButton(component))
		{
			return ((TweetHashtagButtonComponentModel) component).getHashtag();
		}
		return null;
	}

	@Override
	protected String getBaseHref(final AbstractTweetButtonComponentModel component)
	{
		if (isShareLinkButton(component))
		{
			return getConfiguration().getString("socialplugins.tweet.share.base.href", "https://twitter.com/share");
		}
		else if (isHashtagButton(component))
		{
			return getConfiguration().getString("socialplugins.tweet.hashtag.base.href", "https://twitter.com/intent/tweet");
		}
		else if (isMentionButton(component))
		{
			return getConfiguration().getString("socialplugins.tweet.mention.base.href", "https://twitter.com/intent/tweet");
		}
		else
		{
			throw newUnsupportedComponentTypeException(component);
		}
	}

	protected boolean isShareLinkButton(final AbstractTweetButtonComponentModel component)
	{
		return component.getClass().equals(TweetLinkButtonComponentModel.class);
	}

	protected boolean isHashtagButton(final AbstractTweetButtonComponentModel component)
	{
		return component.getClass().equals(TweetHashtagButtonComponentModel.class);
	}

	protected boolean isMentionButton(final AbstractTweetButtonComponentModel component)
	{
		return component.getClass().equals(TweetMentionButtonComponentModel.class);
	}

	@Override
	protected String getCssClass(final AbstractTweetButtonComponentModel component)
	{
		if (isShareLinkButton(component))
		{
			return "twitter-share-button";
		}
		else if (isHashtagButton(component))
		{
			return "twitter-hashtag-button";
		}
		else if (isMentionButton(component))
		{
			return "twitter-mention-button";
		}
		else
		{
			throw newUnsupportedComponentTypeException(component);
		}
	}

	protected RuntimeException newUnsupportedComponentTypeException(final AbstractTweetButtonComponentModel component)
	{
		return new IllegalArgumentException("unsupported component type [" + component.getClass() + "]");
	}

	/**
	 * @return the sharePageUrlStrategy
	 */
	public SharedPageUrlStrategy getSharedPageUrlStrategy()
	{
		return sharePageUrlStrategy;
	}

	/**
	 * @param sharePageUrlStrategy
	 *           the sharePageUrlStrategy to set
	 */
	@Required
	public void setSharedPageUrlStrategy(final SharedPageUrlStrategy sharePageUrlStrategy)
	{
		this.sharePageUrlStrategy = sharePageUrlStrategy;
	}



}
