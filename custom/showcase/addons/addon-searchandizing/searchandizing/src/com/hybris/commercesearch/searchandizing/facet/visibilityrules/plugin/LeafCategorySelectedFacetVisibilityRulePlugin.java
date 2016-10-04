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
package com.hybris.commercesearch.searchandizing.facet.visibilityrules.plugin;

import de.hybris.platform.commercesearch.model.solr.LeafCategorySelectedSolrFacetVisibilityRuleModel;
import de.hybris.platform.commercesearch.searchandizing.facet.visibilityrules.config.AbstractFacetVisibilityRule;
import de.hybris.platform.commercesearch.searchandizing.facet.visibilityrules.evaluators.FacetVisibilityRuleEvaluator;
import de.hybris.platform.commercesearch.searchandizing.facet.visibilityrules.plugin.SolrFacetVisibilityRulePlugin;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchResponse;
import de.hybris.platform.solrfacetsearch.config.AbstractSolrFacetVisibilityRuleData;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.commercesearch.searchandizing.facet.visibilityrules.config.LeafCategorySelectedFacetVisibilityRule;


/**
 * 
 */
public class LeafCategorySelectedFacetVisibilityRulePlugin implements SolrFacetVisibilityRulePlugin
{

	private FacetVisibilityRuleEvaluator<SolrSearchResponse, ProductSearchPageData, LeafCategorySelectedFacetVisibilityRule> evaluator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.searchandizing.search.solrfacetsearch.facetvisibilityrules.plugin.
	 * SolrFacetVisibilityRuleConverter #getComposedType()
	 */
	@Override
	public String getTypeCode()
	{
		return LeafCategorySelectedSolrFacetVisibilityRuleModel._TYPECODE;
	}

	public FacetVisibilityRuleEvaluator<SolrSearchResponse, ProductSearchPageData, LeafCategorySelectedFacetVisibilityRule> getEvaluator()
	{
		return this.evaluator;
	}

	@Required
	public void setEvaluator(
			final FacetVisibilityRuleEvaluator<SolrSearchResponse, ProductSearchPageData, LeafCategorySelectedFacetVisibilityRule> evaluator)
	{
		this.evaluator = evaluator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commercesearch.searchanizing.search.solrfacetsearch.facetvisibilityrules.plugin.
	 * SolrFacetVisibilityRulePlugin
	 * #createVisibilityRule(de.hybris.platform.solrfacetsearch.config.AbstractSolrFacetVisibilityRuleData)
	 */
	@Override
	public AbstractFacetVisibilityRule createVisibilityRule(final AbstractSolrFacetVisibilityRuleData visibilityRuleData)
	{
		return new LeafCategorySelectedFacetVisibilityRule(evaluator, visibilityRuleData.getCondition());
	}
}