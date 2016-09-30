package com.sap.hybris.reco.addon.be;

import de.hybris.platform.sap.core.bol.backend.BackendBusinessObject;

import com.sap.hybris.reco.addon.dao.InteractionContext;
import com.sap.hybris.reco.addon.dao.ProductRecommendation;
import com.sap.hybris.reco.addon.bo.RecommendationContextProvider;

import java.util.List;

/**
 * @author SAP AG
 * 
 */
public interface ProductRecommendationManagerBackend extends BackendBusinessObject
{
	/**
	 * Get Product Recommendations from hybris Marketing based on current context
	 * @param context 
	 * @return List<ProductRecommendation>
	 */
	public List<ProductRecommendation> getProductRecommendation(RecommendationContextProvider context);
	
	/**
	 * Post the used interaction to the hybris Marketing.
	 * @param context 
	 */
	public void postInteraction(InteractionContext context);
}