/**
 * 
 */
package com.sap.hybris.reco.addon.bo;

import java.util.List;

import com.sap.hybris.reco.addon.bo.RecommendationContextProvider;
import com.sap.hybris.reco.addon.dao.InteractionContext;
import com.sap.hybris.reco.addon.dao.ProductRecommendation;

import de.hybris.platform.sap.core.bol.businessobject.BusinessObject;


/**
 * @author SAP AG
 * 
 */
public interface ProductRecommendationManager extends BusinessObject
{
	/**
	 * @param context
	 * @return list of recommended products
	 */
	public List<ProductRecommendation> getProductRecommendation(RecommendationContextProvider context);
	/**
	 * @param context 
	 */
	public void postInteraction(InteractionContext context);
}
