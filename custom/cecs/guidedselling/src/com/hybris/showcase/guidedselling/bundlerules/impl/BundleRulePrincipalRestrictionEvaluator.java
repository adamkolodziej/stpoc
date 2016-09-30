/**
 * 
 */
package com.hybris.showcase.guidedselling.bundlerules.impl;

import de.hybris.platform.configurablebundleservices.model.ChangeProductPriceBundleRuleModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.DiscountValue;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.guidedselling.bundlerules.BundleRuleRestrictionEvaluator;
import com.hybris.showcase.guidedselling.model.AbstractBundleRuleRestrictionModel;
import com.hybris.showcase.guidedselling.model.BundlePrincipalRestrictionModel;


public class BundleRulePrincipalRestrictionEvaluator implements BundleRuleRestrictionEvaluator
{
	private UserService userService;

	@Override
	public boolean accept(final AbstractBundleRuleRestrictionModel restriction)
	{
		final BundlePrincipalRestrictionModel principalRestriction = (BundlePrincipalRestrictionModel) restriction;
		final UserModel user = getUserService().getCurrentUser();
		final Collection<PrincipalModel> principals = principalRestriction.getPrincipals();
		if (principals.contains(user))
		{
			return true;
		}
		final Set<UserGroupModel> groups = getUserService().getAllUserGroupsForUser(user);
		if (CollectionUtils.containsAny(principals, groups))
		{
			return true;
		}
		return false;
	}

	@Override
	public Class<? extends AbstractBundleRuleRestrictionModel> getEvaluatedRestrictionClass()
	{
		return BundlePrincipalRestrictionModel.class;
	}

	@Override
	public void applyDiscount(final AbstractBundleRuleRestrictionModel restriction, final AbstractOrderEntryModel entry,
			final ChangeProductPriceBundleRuleModel priceRule, final DiscountValue discount)
	{
		// no op
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}
}
