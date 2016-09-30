/**
 * 
 */
package com.sap.hybris.reco.addon.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.sap.hybris.reco.addon.bo.RecommendationContextProvider;
import com.sap.hybris.reco.model.CMSSAPRecommendationComponentModel;


/**
 * @author SAP AG
 * 
 */
public class DefaultRecommendationContextProviderImpl implements RecommendationContextProvider
{
	private String productId;
	private String itemType;
	private String includeCart;
	private String userId;
	private String userType;
	private String recotype;
	private String usage;
	
	private final List<String> cartItems = new ArrayList<String>();
	private CMSSAPRecommendationComponentModel componentModel;

	public String getProductId()
	{
		return productId;
	}

	public String getUserId()
	{
		return userId;
	}

	public List<String> getCartItems()
	{
		return cartItems;
	}

	public void addCartItem(final String cartItem)
	{
		cartItems.add(cartItem);
	}

	public void setProductId(final String productId)
	{
		this.productId = productId;
	}

	public void setUserId(final String userId)
	{
		this.userId = userId;
	}

	public CMSSAPRecommendationComponentModel getComponentModel()
	{
		return componentModel;
	}

	public void setComponentModel(final CMSSAPRecommendationComponentModel componentModel)
	{
		this.componentModel = componentModel;
	}

	public String getItemDataSourceType()
	{
		return itemType;
	}

	public void setItemDataSourceType(String itemType)
	{
      this.itemType = itemType;
	}

	public String getIncludeCart()
	{
      return includeCart;
	}

	public void setIncludeCart(String includeCart)
	{
      this.includeCart = includeCart;
	}

	public String getRecotype()
	{
		return recotype;
	}

	public void setRecotype(String recotype)
	{
		this.recotype = recotype;
	}
	
	public String getUsage()
	{
		return usage;
	}

	public void setUsage(String usage)
	{
		this.usage = usage;
	}

	public String getUserType()
	{
		return userType;
	}

	public void setUserType(String userType)
	{		
		this.userType = userType;
	}

}