/**
 *
 */
package com.hybris.showcase.dao;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Collection;
import java.util.List;



/**
 * @author Rafal Zymla
 *
 */
public interface CustomerDao
{

	List<CustomerModel> getCustomersWithLoginEnabled();

	Collection<CustomerModel> getCustomers();

}
