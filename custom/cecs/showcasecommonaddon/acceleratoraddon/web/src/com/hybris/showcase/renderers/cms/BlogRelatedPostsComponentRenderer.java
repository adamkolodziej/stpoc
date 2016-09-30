/**
 *
 */
package com.hybris.showcase.renderers.cms;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;

import java.util.Map;

import javax.servlet.jsp.PageContext;

import com.hybris.showcase.model.components.BlogRelatedPostsComponentModel;


/**
 *
 */
public class BlogRelatedPostsComponentRenderer extends DefaultAddOnCMSComponentRenderer<BlogRelatedPostsComponentModel>
{

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext,
			final BlogRelatedPostsComponentModel component)
	{
		// FIXME Hardcoded. When requirements arrive, rework it.
		final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);

		variables.put("displayType", component.getBlogRelatedPostsDisplayType().toString());
		variables.put("maxPosts", component.getMaxPosts()); // TODO unused. Store maximum number of posts to be shown. Use it when requirements arrive.

		return variables;
	}
}
