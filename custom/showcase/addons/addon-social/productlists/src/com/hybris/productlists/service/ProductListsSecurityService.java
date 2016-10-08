/**
 * 
 */
package com.hybris.productlists.service;

import com.hybris.productlists.model.ProductListModel;


/**
 * @author simonhuggins
 * 
 */
public interface ProductListsSecurityService
{


	public String generateGuid();

	public boolean checkPassword(ProductListModel productList, byte[] password);

	/**
	 * Transfers an anonymous list.
	 */
	public boolean transferAnonymousList(ProductListModel productList);
}
