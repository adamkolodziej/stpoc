/**
 * 
 */
package com.hybris.social.common.web;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.StringUtils;

import com.hybris.social.common.model.IFrameModel;


/**
 * @author rmcotton
 * 
 */
public class IFrameTag extends SimpleTagSupport
{
	private IFrameModel iframe;

	private String name;
	private String id;

	@Override
	public void doTag() throws JspException, IOException
	{
		getJspContext().getOut().write(buildIFrameHTML());
	}

	protected String buildIFrameHTML()
	{
		final StringBuilder html = new StringBuilder();
		html.append("<iframe src=\"").append(getIframe().getUrl()).append("\" ");

		if (StringUtils.isNotBlank(getId()))
		{
			html.append("id=\"").append(getId()).append("\" ");
		}
		if (StringUtils.isNotBlank(getName()))
		{
			html.append("name=\"").append(getName()).append("\" ");
		}
		if (StringUtils.isNotBlank(getIframe().getTitle()))
		{
			html.append("title=\"").append(getIframe().getTitle()).append("\" ");
		}
		if (StringUtils.isNotBlank(getIframe().getCssClass()))
		{
			html.append("class=\"").append(getIframe().getCssClass()).append("\" ");
		}
		if (StringUtils.isNotBlank(getIframe().getCssStyle()))
		{
			html.append("style=\"").append(getIframe().getCssStyle()).append("\" ");
		}
		if (getIframe().getWidth() != null)
		{
			html.append("width=\"").append(getIframe().getWidth()).append("\" ");
		}
		if (getIframe().getHeight() != null)
		{
			html.append("height=\"").append(getIframe().getHeight()).append("\" ");
		}
		if (getIframe().getMarginWidth() != null)
		{
			html.append("marginWidth=\"").append(getIframe().getMarginWidth()).append("\" ");
		}
		if (getIframe().getMarginHeight() != null)
		{
			html.append("marginHeight=\"").append(getIframe().getMarginHeight()).append("\" ");
		}
		if (getIframe().getBorder() != null)
		{
			html.append("frameborder=\"").append(Boolean.TRUE.equals(getIframe().getBorder()) ? "1" : "0").append("\" ");
		}
		final Boolean scrollbars = getIframe().getScrollbars();
		String scrolling = "auto";
		if (Boolean.TRUE.equals(scrollbars))
		{
			scrolling = "yes";
		}
		else if (Boolean.FALSE.equals(scrollbars))
		{
			scrolling = "no";
		}
		html.append("scrolling=\"").append(scrolling).append("\" ");

		html.append("></iframe>");
		return html.toString();
	}

	public IFrameModel getIframe()
	{
		return iframe;
	}

	public void setIframe(final IFrameModel iframe)
	{
		this.iframe = iframe;
	}

	public void setId(final String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return this.id;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}
}
