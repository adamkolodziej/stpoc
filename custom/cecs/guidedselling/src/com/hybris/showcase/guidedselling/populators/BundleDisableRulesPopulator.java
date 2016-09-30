package com.hybris.showcase.guidedselling.populators;

import de.hybris.platform.configurablebundleservices.daos.BundleRuleDao;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.configurablebundleservices.model.DisableProductBundleRuleModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.guidedselling.data.BundleComponentData;
import com.hybris.showcase.guidedselling.data.BundleComponentOptionData;
import com.hybris.showcase.guidedselling.data.BundleOfferData;
import com.hybris.showcase.guidedselling.data.BundleOfferPopulatorParameters;


/**
 * Created by miroslaw.szot@sap.com on 2015-03-13.
 */
public class BundleDisableRulesPopulator implements Populator<BundleOfferPopulatorParameters, BundleOfferData>
{
	private BundleRuleDao<DisableProductBundleRuleModel> disableProductBundleRuleDao;
	private ProductService productService;

	@Override
	public void populate(final BundleOfferPopulatorParameters parameters, final BundleOfferData offerData)
			throws ConversionException
	{
		final BundleTemplateModel bundleTemplate = parameters.getBundleTemplate();
		final Set<ProductModel> selectedProducts = new HashSet<>();

		for (final BundleComponentData componentData : offerData.getComponents())
		{
			for (final BundleComponentOptionData optionData : componentData.getOptions())
			{
				if (optionData.isSelected())
				{
					final String code = optionData.getProduct().getCode();
					final ProductModel productModel = getProductService().getProductForCode(code);
					selectedProducts.add(productModel);
				}
			}
		}

		final Map<String, DisableProductBundleRuleModel> disableResultsMap = new HashMap<>();

		final List<BundleTemplateModel> childTemplates = bundleTemplate.getChildTemplates();
		for (final BundleTemplateModel componentModel : childTemplates)
		{
			for (final ProductModel targetProduct : componentModel.getProducts())
			{
				final List<DisableProductBundleRuleModel> disableRules = disableProductBundleRuleDao
						.findBundleRulesByTargetProductAndTemplate(targetProduct, componentModel);
				for (final DisableProductBundleRuleModel disableRule : disableRules)
				{
					final Collection<ProductModel> conditionalProducts = disableRule.getConditionalProducts();
					if (CollectionUtils.containsAny(selectedProducts, conditionalProducts))
					{
						disableResultsMap.put(targetProduct.getCode(), disableRule);
						break;
					}
				}
			}
		}

		for (final BundleComponentData componentData : offerData.getComponents())
		{
			for (final BundleComponentOptionData optionData : componentData.getOptions())
			{
				final DisableProductBundleRuleModel disableRule = disableResultsMap.get(optionData.getProduct().getCode());
				if (disableRule != null)
				{
					optionData.setDisabled(true);
					optionData.setDisableMessage(StringUtils.isNotBlank(disableRule.getName()) ? disableRule.getName() : disableRule
							.getId());
					// CECS-129: Guidedselling - change price - TCO promotion(3 months free) - START
					optionData.setLabel(disableRule.getLabel());
					// CECS-129: Guidedselling - change price - TCO promotion(3 months free) - END
				}
				else
				{
					optionData.setDisabled(false);
				}
			}
		}
	}

	public BundleRuleDao<DisableProductBundleRuleModel> getDisableProductBundleRuleDao()
	{
		return disableProductBundleRuleDao;
	}

	@Required
	public void setDisableProductBundleRuleDao(final BundleRuleDao<DisableProductBundleRuleModel> disableProductBundleRuleDao)
	{
		this.disableProductBundleRuleDao = disableProductBundleRuleDao;
	}

	public ProductService getProductService()
	{
		return productService;
	}

	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}
}
