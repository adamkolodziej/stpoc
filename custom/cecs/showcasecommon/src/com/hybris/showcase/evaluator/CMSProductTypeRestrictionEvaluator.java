/**
 *
 */
package com.hybris.showcase.evaluator;

import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator;

import org.apache.log4j.Logger;

import com.hybris.showcase.model.restrictions.CMSProductTypeRestrictionModel;


/**
 * @author i324339
 *
 */
public class CMSProductTypeRestrictionEvaluator implements CMSRestrictionEvaluator<CMSProductTypeRestrictionModel>
{
	private final static Logger LOG = Logger.getLogger(CMSProductTypeRestrictionEvaluator.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator#evaluate(de.hybris.platform.cms2.
	 * model.restrictions.AbstractRestrictionModel, de.hybris.platform.cms2.servicelayer.data.RestrictionData)
	 */

	@Override
	public boolean evaluate(final CMSProductTypeRestrictionModel productTypeRestrictionModel, final RestrictionData context)
	{
		if (context == null)
		{
			return true;
		}
		if (context.hasProduct())
		{
			return productTypeRestrictionModel.getProductType().getCode().equals(context.getProduct().getItemtype());
		}
		else
		{
			LOG.warn("Could not evaluate CMSProductTypeRestriction. RestrictionData contains no product. Returning false.");
			return false;
		}
	}

}
