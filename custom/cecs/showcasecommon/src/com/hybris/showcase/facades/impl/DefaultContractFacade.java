package com.hybris.showcase.facades.impl;

import com.hybris.showcase.dao.ContractDao;
import com.hybris.showcase.facades.ContractFacade;
import com.hybris.showcase.model.ContractModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.data.ContractData;


public class DefaultContractFacade implements ContractFacade
{
	private ContractDao contractDao;
	private UserService userService;
	private Converter<ContractModel, ContractData> contractConverter;

	@Override
	public ContractData getLatestContract()
	{
		final ContractModel contract = getContractDao().getLatestContractForUser(getUserService().getCurrentUser());
		if (contract != null)
		{
			return getContractConverter().convert(contract);
		}
		else
		{
			return null;
		}
	}

	protected ContractDao getContractDao()
	{
		return contractDao;
	}

	@Required
	public void setContractDao(ContractDao contractDao)
	{
		this.contractDao = contractDao;
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	protected Converter<ContractModel, ContractData> getContractConverter()
	{
		return contractConverter;
	}

	@Required
	public void setContractConverter(Converter<ContractModel, ContractData> contractConverter)
	{
		this.contractConverter = contractConverter;
	}
}
