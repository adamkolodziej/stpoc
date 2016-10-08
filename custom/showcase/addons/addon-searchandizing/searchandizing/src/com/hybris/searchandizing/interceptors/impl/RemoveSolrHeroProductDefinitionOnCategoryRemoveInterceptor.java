/**
 *
 */
package com.hybris.searchandizing.interceptors.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercesearch.model.SolrHeroProductDefinitionModel;
import de.hybris.platform.commercesearch.searchandizing.heroproduct.HeroProductDefinitionService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;

import org.springframework.beans.factory.annotation.Required;


/**
 * @author c.stefanache
 *
 */
public class RemoveSolrHeroProductDefinitionOnCategoryRemoveInterceptor implements RemoveInterceptor
{
	private HeroProductDefinitionService heroProductDefinitionService;

	@Override
	public void onRemove(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (model instanceof CategoryModel)
		{
			final CategoryModel category = (CategoryModel) model;
			final SolrHeroProductDefinitionModel orfanToBeHeroProductDefinition = heroProductDefinitionService
					.getSolrHeroProductDefinitionForCategory(category);

			if (orfanToBeHeroProductDefinition != null)
			{
				ctx.getModelService().remove(orfanToBeHeroProductDefinition);
			}

		}
	}

	@Required
	public void setHeroProductDefinitionService(final HeroProductDefinitionService heroProductDefinitionService)
	{
		this.heroProductDefinitionService = heroProductDefinitionService;
	}

}
