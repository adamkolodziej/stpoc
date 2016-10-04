/**
 * 
 */
package com.hybris.social.twitter.component.renderer;

import de.hybris.platform.acceleratorcms.component.renderer.CMSComponentRenderer;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.hybris.social.twitter.enums.TwitterWidgetChrome;
import com.hybris.social.twitter.model.AbstractTwitterWidgetComponentModel;
import com.hybris.social.twitter.model.TwitterAccountModel;


/**
 * @author dominik.strzyzewski
 * 
 */
public abstract class AbstractTwitterTimelineRenderer<C extends AbstractTwitterWidgetComponentModel> implements
		CMSComponentRenderer<C>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.acceleratorcms.component.renderer.CMSComponentRenderer#renderComponent(javax.servlet.jsp.
	 * PageContext, de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel)
	 */
	@Override
	public void renderComponent(final PageContext pageContext, final C component) throws ServletException, IOException
	{
		final JspWriter out = pageContext.getOut();
		out.write(buildHTML(pageContext, component));
	}

	private String buildHTML(final PageContext pageContext, final C component)
	{
		final StringBuilder html = new StringBuilder();
		html.append("<div class=\"twitter\">");

		buildLink(component, html);

		html.append("</div>");
		return html.toString();
	}

	private void buildLink(final C component, final StringBuilder html)
	{
		html.append("	<a class=\"twitter-timeline\"");
		addCommonAttributes(component, html);
		addCustomAttributes(component, html);
		html.append(">").append(getLinkDescription(component)).append("</a>");
	}

	private void addCommonAttributes(final C component, final StringBuilder html)
	{
		html.append(" href=\"").append(getUrl(component)).append("\" ");
		html.append(" data-widget-id=\"").append(component.getWidgetId()).append("\"  ");

		if (component.getTheme() != null)
		{
			html.append(" data-theme=\"").append(component.getTheme().getCode()).append("\" ");
		}
		if (component.getLinksColor() != null)
		{
			html.append(" data-link-color=\"").append(component.getLinksColor()).append("\" ");
		}
		if (component.getBorderColor() != null)
		{
			html.append(" data-border-color=\"").append(component.getBorderColor()).append("\" ");
		}

		if (!component.getChrome().isEmpty())
		{
			html.append(" data-chrome=\"");
			for (final TwitterWidgetChrome chrome : component.getChrome())
			{
				html.append(chrome.getCode()).append(" ");
			}
			html.append("\" ");
		}
		if (!component.getRelatedAccounts().isEmpty())
		{
			html.append(" data-related=\"");
			for (final Iterator<TwitterAccountModel> i = component.getRelatedAccounts().iterator(); i.hasNext();)
			{
				html.append(i.next().getScreenName());
				if (i.hasNext())
				{
					html.append(",");
				}
			}
			html.append("\" ");
		}
		if (component.getNumberOfTweets() != null)
		{
			html.append(" data-tweet-limit=\"").append(component.getNumberOfTweets()).append("\"  ");
		}
		if (component.getWidth() != null)
		{
			html.append(" width=\"").append(component.getWidth()).append("\" ");
		}
		if (component.getHeight() != null)
		{
			html.append(" height=\"").append(component.getHeight()).append("\" ");
		}
		if (component.getAriaPolite() != null)
		{
			html.append(" data-aria-polite=\"").append(component.getAriaPolite().booleanValue() ? "polite" : "assertive")
					.append("\" ");
		}
	}

	private void addCustomAttributes(final C component, final StringBuilder html)
	{
		final Map<String, String> attributes = getAdditionalAttributes(component);
		for (final Entry<String, String> entry : attributes.entrySet())
		{
			html.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\" ");
		}
	}

	protected abstract String getUrl(C component);

	protected Map<String, String> getAdditionalAttributes(final C component)
	{
		return Collections.emptyMap();
	}

	protected abstract String getLinkDescription(final C component);

}
