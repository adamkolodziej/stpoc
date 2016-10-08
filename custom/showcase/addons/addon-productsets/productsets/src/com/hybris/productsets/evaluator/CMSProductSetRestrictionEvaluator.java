/**
 * 
 */
package com.hybris.productsets.evaluator;

import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator;

import com.hybris.productsets.model.CMSProductSetRestrictionModel;
import com.hybris.productsets.model.ProductSetModel;


/**
 * @author mkostic
 * 
 */
public class CMSProductSetRestrictionEvaluator implements CMSRestrictionEvaluator<CMSProductSetRestrictionModel>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator#evaluate(de.hybris.platform.cms2
	 * .model.restrictions.AbstractRestrictionModel, de.hybris.platform.cms2.servicelayer.data.RestrictionData)
	 */
	@Override
	public boolean evaluate(final CMSProductSetRestrictionModel paramT, final RestrictionData paramRestrictionData)
	{
		final ProductSetModel productSet = (ProductSetModel) paramRestrictionData.getValue("productSet");
		return productSet != null && productSet.equals(paramT.getProductSet());
	}

}
