/**
 * 
 */
package com.hybris.showcase.renderers.cms;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.model.SocialAccountBodySubComponentModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.service.FacebookAuthenticationService;


/**
 * @author I307113
 * 
 */
public class SocialAccountBodySubComponentRenderer extends DefaultAddOnCMSComponentRenderer<SocialAccountBodySubComponentModel>
{
	protected static final String BIG_SIZE_IMAGE_CODE = "BIG";

	private FacebookAuthenticationService facebookAuthenticationService;
	private UserService userService;

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext,
			final SocialAccountBodySubComponentModel component)
	{
		final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);
		final CustomerModel customerModel = (CustomerModel) getUserService().getCurrentUser();
		final String twitterUserProfileId = customerModel.getTwitterUserProfileId();

		FacebookUserModel facebookUser = getFacebookAuthenticationService().getCurrentFacebookUser();
		// if user not logged in by fb, but have logged in previous, then use saved fb user data
		if (facebookUser == null)
		{
			final Set<FacebookUserModel> facebookUserFromDb = getFacebookAuthenticationService().getLinkedFacebookUsersForCustomer(
					customerModel);
			if (!facebookUserFromDb.isEmpty())
			{
				facebookUser = facebookUserFromDb.iterator().next();
			}
		}

		variables.put("fbUser", facebookUser);
		variables.put("size", BIG_SIZE_IMAGE_CODE);
		variables.put("twitterUserProfileId", twitterUserProfileId);

		return variables;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public FacebookAuthenticationService getFacebookAuthenticationService()
	{
		return facebookAuthenticationService;
	}

	@Required
	public void setFacebookAuthenticationService(final FacebookAuthenticationService facebookAuthenticationService)
	{
		this.facebookAuthenticationService = facebookAuthenticationService;
	}


}
