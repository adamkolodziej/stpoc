/**
 * 
 */
package com.sap.hybris.reco.addon.bo.impl;

import java.util.List;

import org.apache.log4j.Logger;

import de.hybris.platform.sap.core.bol.businessobject.BackendInterface;
import de.hybris.platform.sap.core.bol.businessobject.BusinessObjectBase;
import de.hybris.platform.sap.core.jco.exceptions.BackendException;

import com.sap.hybris.reco.addon.be.ProductRecommendationManagerBackend;
import com.sap.hybris.reco.addon.bo.ProductRecommendationManager;
import com.sap.hybris.reco.addon.bo.RecommendationContextProvider;
import com.sap.hybris.reco.addon.dao.InteractionContext;
import com.sap.hybris.reco.addon.dao.ProductRecommendation;


/**
 * @author SAP AG
 * 
 */
@BackendInterface(ProductRecommendationManagerBackend.class)
public class ProductRecommendationManagerImpl extends BusinessObjectBase implements ProductRecommendationManager
{
	private static final Logger LOG = Logger.getLogger(ProductRecommendationManagerImpl.class);

	public List<ProductRecommendation> getProductRecommendation(final RecommendationContextProvider context)
	{
		try
		{
			return ((ProductRecommendationManagerBackend) getBackendBusinessObject()).getProductRecommendation(context);
		}
		catch (final BackendException e)
		{
			LOG.error("", e);
		}
		return null;
	}

	/**
	 * @param context
	 */
	public void postInteraction(InteractionContext context)
	{
		try
		{
			((ProductRecommendationManagerBackend) getBackendBusinessObject()).postInteraction(context);
		}
		catch (final BackendException e)
		{
			LOG.error("", e);
		}		
	}
}
