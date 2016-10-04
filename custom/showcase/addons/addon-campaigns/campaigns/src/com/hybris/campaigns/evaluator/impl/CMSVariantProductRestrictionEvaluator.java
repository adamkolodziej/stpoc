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
package com.hybris.campaigns.evaluator.impl;

import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.hybris.campaigns.model.CMSVariantProductRestrictionModel;


/**
 * Evaluates whether the current product is either the same or a variant of products assigned to the Restriction.
 * 
 * @author rmcotton
 * 
 */
public class CMSVariantProductRestrictionEvaluator implements CMSRestrictionEvaluator<CMSVariantProductRestrictionModel>
{
	private final static Logger LOG = Logger.getLogger(CMSVariantProductRestrictionEvaluator.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator#evaluate(de.hybris.platform.cms2
	 * .model.restrictions.AbstractRestrictionModel, de.hybris.platform.cms2.servicelayer.data.RestrictionData)
	 */
	@Override
	public boolean evaluate(final CMSVariantProductRestrictionModel restriction, final RestrictionData context)
	{
		if (context == null)
		{
			return true;
		}
		if (context.hasProduct())
		{
			return CollectionUtils.containsAny(restriction.getProductCodes(), getApplicableCodes(context.getProduct()));
		}
		else
		{
			LOG.warn("Could not evaluate CMSVariantProductRestriction. RestrictionData contains no product. Returning false.");
			return false;
		}
	}

	protected Set<String> getApplicableCodes(final ProductModel product)
	{
		if (product instanceof VariantProductModel)
		{
			ProductModel currentProduct = product;
			final Set<String> codes = new HashSet<String>();
			while (currentProduct != null)
			{
				codes.add(currentProduct.getCode());

				if (currentProduct instanceof VariantProductModel)
				{
					currentProduct = ((VariantProductModel) currentProduct).getBaseProduct();
				}
				else
				{
					currentProduct = null;
				}
			}
			return codes;
		}
		else
		{
			return Collections.singleton(product.getCode());
		}
	}



}
