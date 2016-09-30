/**
 * 
 */
package com.hybris.productlists.controllers.pages.create;

import com.hybris.productlists.data.ProductListData;


/**
 * @author craig.wayman
 * 
 */
public class CreateProductListForm
{

	private String name;
//	private List<AddressData> addresses;

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}
//
//	public List<AddressData> getAddresses()
//	{
//		return addresses;
//	}
//
//	public void setAddresses(final List<AddressData> addresses)
//	{
//		this.addresses = addresses;
//	}

	public ProductListData toProductListData()
	{

		final ProductListData productListData = new ProductListData();
		productListData.setName(name);

		return productListData;
	}
}
