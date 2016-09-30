/**
 *
 */
package com.hybris.showcase.guidedselling.daos.impl;

import de.hybris.platform.configurablebundleservices.daos.impl.AbstractBundleRuleDao;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hybris.showcase.guidedselling.daos.ChangeProductPercentPriceBundleRuleDao;
import com.hybris.showcase.guidedselling.model.ChangeProductPercentPriceBundleRuleModel;


public class DefaultChangeProductPercentPriceBundleRuleDao extends AbstractBundleRuleDao<ChangeProductPercentPriceBundleRuleModel>
		implements ChangeProductPercentPriceBundleRuleDao
{

	@Override
	public List<ChangeProductPercentPriceBundleRuleModel> findBundleRulesByTargetProduct(final ProductModel targetProduct,
			final BundleTemplateModel bundleTemplate)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("targetProduct", targetProduct);
		ServicesUtil.validateParameterNotNullStandardMessage("bundleTemplate", bundleTemplate);

		final Map params = new HashMap();
		params.put("bundleTemplate", bundleTemplate);
		params.put("product", targetProduct);

		final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(getFindBundleRulesByTargetProductAndTemplateQuery(),
				params);

		final SearchResult results = search(flexibleSearchQuery);
		return results.getResult();
	}

	@Override
	public String getFindBundleRulesByTargetProductQuery()
	{
		return "SELECT {rule:pk} FROM {ChangeProductPercentPriceBundleRule AS rule JOIN AbstractBundleRulesTargetProductsRelation AS targetRel ON {targetRel:source}={rule:pk}} WHERE {targetRel:target}=?product";
	}

	@Override
	public String getFindBundleRulesByTargetProductAndTemplateQuery()
	{
		return "SELECT {rule:pk} FROM {ChangeProductPercentPriceBundleRule AS rule JOIN AbstractBundleRulesTargetProductsRelation AS targetRel ON {targetRel:source}={rule:pk}} WHERE {targetRel:target}=?product AND {rule:bundleTemplate}=?bundleTemplate";
	}

	@Override
	public String getFindBundleRulesByProductAndRootTemplateQuery()
	{
		return "SELECT DISTINCT {rule:pk} FROM {ChangeProductPercentPriceBundleRule AS rule JOIN BundleTemplate AS templ ON {templ:PK}={rule:bundleTemplate} JOIN AbstractBundleRulesTargetProductsRelation AS targetRel ON {targetRel:source}={rule:PK} JOIN AbstractBundleRulesConditionalProductsRelation AS condRel ON {condRel:source}={rule:PK}} WHERE {templ:parentTemplate}=?rootBundleTemplate AND  ({targetRel:target}=?product OR {condRel:target}=?product)";
	}

}
