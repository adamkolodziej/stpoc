/**
 *
 */
package com.hybris.showcase.renderers.cms;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;

import java.util.Map;

import javax.servlet.jsp.PageContext;

import com.hybris.showcase.model.components.BlogNewsletterComponentModel;


/**
 *
 */
public class BlogNewsletterComponentRenderer extends DefaultAddOnCMSComponentRenderer<BlogNewsletterComponentModel>
{

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final BlogNewsletterComponentModel component)
	{
		// FIXME Hardcoded. When requirements arrive, rework it.
		final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);

		return variables;
	}

}
