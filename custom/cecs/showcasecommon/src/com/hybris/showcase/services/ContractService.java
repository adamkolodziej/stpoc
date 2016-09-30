/**
 *
 */
package com.hybris.showcase.services;

import de.hybris.platform.core.model.user.UserModel;

import com.hybris.showcase.model.ContractModel;


/**
 * @author Adrian Sbarcea (SAP - i309441)
 *
 */
public interface ContractService
{
	ContractModel getContractForCode(String code);

	ContractModel getLatestContractForUser(UserModel user);
}
