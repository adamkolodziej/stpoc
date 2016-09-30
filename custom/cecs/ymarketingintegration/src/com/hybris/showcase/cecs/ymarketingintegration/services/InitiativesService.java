/**
 *
 */
package com.hybris.showcase.cecs.ymarketingintegration.services;

import de.hybris.platform.core.model.user.CustomerModel;


/**
 * @author n.pavlovic
 *
 */
public interface InitiativesService
{
	void retrieveInitiativesForCustomer(CustomerModel customer);

	void addLocalInitiative(CustomerModel customer, String categoryId);
}
