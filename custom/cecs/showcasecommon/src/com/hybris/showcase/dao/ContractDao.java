package com.hybris.showcase.dao;

import com.hybris.showcase.model.ContractModel;
import de.hybris.platform.core.model.user.UserModel;


public interface ContractDao
{
	ContractModel getContractForCode(String code);

	ContractModel getLatestContractForUser(UserModel user);
}
