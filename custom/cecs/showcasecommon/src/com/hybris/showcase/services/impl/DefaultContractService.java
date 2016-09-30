/**
 *
 */
package com.hybris.showcase.services.impl;

import de.hybris.platform.core.model.user.UserModel;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.dao.ContractDao;
import com.hybris.showcase.model.ContractModel;
import com.hybris.showcase.services.ContractService;


/**
 * @author Adrian Sbarcea (SAP - i309441)
 *
 */
public class DefaultContractService implements ContractService
{
	private ContractDao contractDao;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hybris.showcase.services.ContractService#getContractForCode(java.lang.String)
	 */
	@Override
	public ContractModel getContractForCode(final String code)
	{
		return getContractDao().getContractForCode(code);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hybris.showcase.services.ContractService#getLatestContractForUser(de.hybris.platform.core.model.user.
	 * UserModel)
	 */
	@Override
	public ContractModel getLatestContractForUser(final UserModel user)
	{
		return getContractDao().getLatestContractForUser(user);
	}

	/**
	 * @return the contractDao
	 */
	public ContractDao getContractDao()
	{
		return contractDao;
	}

	/**
	 * @param contractDao
	 *           the contractDao to set
	 */
	@Required
	public void setContractDao(final ContractDao contractDao)
	{
		this.contractDao = contractDao;
	}

}
