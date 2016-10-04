/**
 * 
 */
package com.hybris.campaigns.order;

import de.hybris.platform.promotions.model.PromotionGroupModel;

import java.util.Collection;


/**
 * A strategy to inject into the Cart Service that allows is to define all the available promotions groups at cart
 * calculation time
 * 
 * @author brendan
 * 
 */
public interface GetPromotionGroupsStrategy
{
	public Collection<PromotionGroupModel> getPromotionGroups();
}
