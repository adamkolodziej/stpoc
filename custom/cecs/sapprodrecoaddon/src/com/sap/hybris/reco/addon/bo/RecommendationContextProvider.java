package com.sap.hybris.reco.addon.bo;

import java.util.List;

import com.sap.hybris.reco.model.CMSSAPRecommendationComponentModel;



/**
 * @author SAP AG
 * 
 */
public interface RecommendationContextProvider
{

	/**
	 * @param cartItem
	 */
	public void addCartItem(final String cartItem);

	/**
	 * @param productId
	 *           the productId to set
	 */
	public void setProductId(final String productId);

	/**
	 * @return userId
	 */
	public String getUserId();
	
	/**
	 * @param userId
	 */
	public void setUserId(final String userId);
	
	/**
	 * @return userType
	 */
	public String getUserType();
		
	/**
	 * @param userType
	 */
	public void setUserType(final String userType);

	/**
	 * @return productId
	 */
	public String getProductId();

	/**
	 * @return cartItems
	 */
	public List<String> getCartItems();

	/**
	 * @return componentModel
	 */
	public CMSSAPRecommendationComponentModel getComponentModel();
	
	/**
	 * @param component
	 */
	public void setComponentModel(CMSSAPRecommendationComponentModel component);
	
	/**
	 * @return usage
	 */
	public String getUsage();
	
	/**
	 * @param usage
	 */
	public void setUsage(String usage);
	
	/**
	 * @return recoType
	 */
	public String getRecotype();
	
	/**
	 * @param recotype
	 */
	public void setRecotype(String recotype);
	
	/**
	 * @return itemType
	 */
	public String getItemDataSourceType();
	
	/**
	 * @param itemType
	 */
	public void setItemDataSourceType(String itemType);
	
	/**
	 * @return includeCart
	 */
	public String getIncludeCart();
	
	/**
	 * @param includeCart
	 */
	public void setIncludeCart(String includeCart);

}
