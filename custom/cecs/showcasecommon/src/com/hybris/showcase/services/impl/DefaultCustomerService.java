/**
 *
 */
package com.hybris.showcase.services.impl;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.dao.CustomerDao;
import com.hybris.showcase.services.CustomerService;


/**
 * @author Rafal Zymla
 *
 */
public class DefaultCustomerService implements CustomerService
{
	private CustomerDao customerDao;

	@Override
	public List<CustomerModel> getCustomersWithLoginEnabled()
	{
		return customerDao.getCustomersWithLoginEnabled();
	}

	@Required
	public void setCustomerDao(final CustomerDao customerDao)
	{
		this.customerDao = customerDao;
	}

	@Override
	public Collection<CustomerModel> getCustomers()
	{
		return customerDao.getCustomers();
	}

}
