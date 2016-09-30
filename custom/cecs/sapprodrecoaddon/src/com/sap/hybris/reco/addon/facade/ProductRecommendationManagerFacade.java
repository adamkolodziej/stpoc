package com.sap.hybris.reco.addon.facade;

import java.util.List;

import de.hybris.platform.commercefacades.product.data.ProductReferenceData;

import com.sap.hybris.reco.addon.bo.RecommendationContextProvider;
import com.sap.hybris.reco.addon.dao.InteractionContext;

/**
 * @author SAP AG
 * 
 */
public interface ProductRecommendationManagerFacade
{
	/**
	 * @param context
	 * @return list of recommended products
	 */
	public List<ProductReferenceData> getProductRecommendation(RecommendationContextProvider context);
	
	/**
	 * @param context
	 */
	public void postInteraction(InteractionContext context);

	/**
	 * @return RecommendationContextProvider
	 */
	public RecommendationContextProvider createRecommendationContextProvider();
	
	/**
	 * Get the logged in User ID
	 * @return String
	 */
	public String getSessionUserId();
	
}
