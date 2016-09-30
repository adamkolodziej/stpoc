package com.hybris.showcase.cecs.ymarketingintegration.dao;

import com.hybris.showcase.cecs.ymarketingintegration.model.CEIInitiativeModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.Collection;


public interface CEIInitiativeDAO
{

	Collection<CEIInitiativeModel> findInitiativesForUser(UserModel user);

}
