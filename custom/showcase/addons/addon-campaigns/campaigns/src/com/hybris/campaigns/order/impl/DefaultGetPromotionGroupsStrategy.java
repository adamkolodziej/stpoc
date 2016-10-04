/**
 * 
 */
package com.hybris.campaigns.order.impl;

import de.hybris.platform.promotions.model.PromotionGroupModel;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.campaigns.order.GetPromotionGroupsStrategy;


/**
 * @author Richard Cotton
 * 
 */
public class DefaultGetPromotionGroupsStrategy implements GetPromotionGroupsStrategy
{
	private BaseSiteService baseSiteService;

	@Override
	public Collection<PromotionGroupModel> getPromotionGroups()
	{
		final Collection<PromotionGroupModel> promotionGroupModels = new ArrayList<PromotionGroupModel>();
		final PromotionGroupModel defaultPromotionGroup = getBaseSiteService().getCurrentBaseSite().getDefaultPromotionGroup();
		if (defaultPromotionGroup != null)
		{
			promotionGroupModels.add(getBaseSiteService().getCurrentBaseSite().getDefaultPromotionGroup());
		}
		return promotionGroupModels;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}


	public BaseSiteService getBaseSiteService()
	{
		return this.baseSiteService;
	}
}
