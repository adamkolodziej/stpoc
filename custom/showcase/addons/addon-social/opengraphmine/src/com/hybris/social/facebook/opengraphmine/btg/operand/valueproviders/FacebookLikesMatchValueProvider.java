/**
 * 
 */
package com.hybris.social.facebook.opengraphmine.btg.operand.valueproviders;

import de.hybris.platform.btg.condition.operand.valueproviders.CollectionOperandValueProvider;
import de.hybris.platform.btg.enums.BTGConditionEvaluationScope;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.facebook.opengraphmine.btg.operand.types.FacebookLikesSet;
import com.hybris.social.facebook.opengraphmine.model.BTGFacebookLikesOperandModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookPageModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.service.FacebookAuthenticationService;


/**
 * This extracts user like information from their local facebook profile copy. If the assigned user is a facebook user
 * then the likes are extracted directly, if the assigned user a Customer then first all the linked facebook user
 * accounts are looked up and then the likes add from each account.
 * 
 * @author rmcotton
 */
public class FacebookLikesMatchValueProvider implements CollectionOperandValueProvider<BTGFacebookLikesOperandModel>
{
	private FacebookAuthenticationService facebookAuthenticationService;

	@Override
	public Class getValueType(final BTGFacebookLikesOperandModel operand)
	{
		return FacebookLikesSet.class;
	}

	@Override
	public Class getAtomicValueType(final BTGFacebookLikesOperandModel operand)
	{
		return String.class;
	}

	@Override
	public Object getValue(final BTGFacebookLikesOperandModel operand, final UserModel user,
			final BTGConditionEvaluationScope evaluationScope)
	{
		if (user instanceof FacebookUserModel)
		{
			return getLikes((FacebookUserModel) user);
		}
		else if (getFacebookAuthenticationService().getCurrentFacebookUser() != null)
		{
			return getLikes(getFacebookAuthenticationService().getCurrentFacebookUser());
		}
		else if (user instanceof CustomerModel)
		{
			final LinkedHashSet<String> likes = new LinkedHashSet<String>();
			for (final FacebookUserModel fbUser : getFacebookAuthenticationService().getLinkedFacebookUsersForCustomer(
					(CustomerModel) user))
			{
				likes.addAll(getLikes(fbUser));
			}
			return likes;
		}
		return Collections.emptyList();

	}

	protected Collection<String> getLikes(final FacebookUserModel user)
	{
		// TODO Should add liked posts from newsfeed to the mix
		final List<String> likes = new ArrayList<String>(user.getLikes().size());
		for (final FacebookPageModel page : user.getLikes())
		{
			if (StringUtils.isNotBlank(page.getName()))
			{
				likes.add(page.getName());
			}
		}
		return new FacebookLikesSet(likes);
	}

	@Required
	public void setFacebookAuthenticationService(final FacebookAuthenticationService facebookAuthenticationService)
	{
		this.facebookAuthenticationService = facebookAuthenticationService;
	}

	public FacebookAuthenticationService getFacebookAuthenticationService()
	{
		return this.facebookAuthenticationService;
	}

}
