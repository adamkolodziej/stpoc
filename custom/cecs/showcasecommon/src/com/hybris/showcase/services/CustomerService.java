/**
 *
 */
package com.hybris.showcase.services;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Collection;
import java.util.List;


/**
 * @author Rafal Zymla
 *
 */
public interface CustomerService
{

	List<CustomerModel> getCustomersWithLoginEnabled();

	Collection<CustomerModel> getCustomers();

}