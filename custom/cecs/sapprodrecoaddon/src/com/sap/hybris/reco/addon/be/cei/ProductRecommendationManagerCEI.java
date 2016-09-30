package com.sap.hybris.reco.addon.be.cei;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;

import de.hybris.platform.sap.core.bol.backend.BackendType;
import de.hybris.platform.sap.core.bol.backend.jco.BackendBusinessObjectBaseJCo;
import de.hybris.platform.sap.core.jco.connection.JCoConnection;
import de.hybris.platform.sap.core.jco.exceptions.BackendException;

import com.sap.hybris.reco.addon.be.ProductRecommendationManagerBackend;
import com.sap.hybris.reco.addon.bo.RecommendationContextProvider;
import com.sap.hybris.reco.addon.constants.SapprodrecoaddonConstants;
import com.sap.hybris.reco.addon.dao.InteractionContext;
import com.sap.hybris.reco.addon.dao.ProductRecommendation;
import com.sap.hybris.reco.common.util.HMCConfigurationReader;
import com.sap.hybris.reco.constants.SapproductrecommendationConstants;

/**
 * @author SAP AG
 * 
 */
@BackendType("CEI")
public class ProductRecommendationManagerCEI extends BackendBusinessObjectBaseJCo implements ProductRecommendationManagerBackend
{
	private final static Logger LOG = Logger.getLogger( ProductRecommendationManagerCEI.class.getName() );
	private static String JCO_STATELESS = "JCoStateless";
	private ProductService productService;
	protected HMCConfigurationReader configuration;
	
	private final static String ERROR = "E";
	private final static String INFO = "I";
	
	
	/**
	 * @param context
	 */
	public void postInteraction(final InteractionContext context)
	{
		if(configuration.getUsage().equals(SapproductrecommendationConstants.SCENARIO))
		{
			postInteractionByScenario(context);
		}
	}
	
	private void postInteractionByScenario(final InteractionContext context)
	{
		final JCoConnection jCoConnection = getJcoConnection();
	
		try
		{
			String functionName = "";
			functionName = "PROD_RECO_POST_IA_FOR_SCENARIO";
			
			final JCoFunction function = jCoConnection.getFunction(functionName);
			final JCoParameterList importParameterList = function.getImportParameterList();
			final JCoTable interactions = importParameterList.getTable("IT_INTERACTIONS");
			interactions.appendRow();
			interactions.setValue("SCENARIO_ID", context.getScenarioId());
			interactions.setValue("USER_ID", context.getUserId()); 
			interactions.setValue("USER_TYPE", context.getUserType());
			interactions.setValue("IA_TYPE",SapprodrecoaddonConstants.CLICKTHROUGH);
			interactions.setValue("TIMESTAMP", context.getTimestamp());
			interactions.setValue("SOURCE_OBJECT_ID", context.getSourceObjectId());
			
			final JCoTable leadingObjects = interactions.getTable("IPRODUCTS");
			leadingObjects.appendRow();
			leadingObjects.setValue("OBJECT_TYPE", context.getProductType());
			leadingObjects.setValue("OBJECT_ID", context.getProductId());
			leadingObjects.setValue("PRODUCT_NAV_URL", context.getProductNavURL());
			leadingObjects.setValue("PRODUCT_IMAGE_URL", context.getProductImageURL());
												
			jCoConnection.execute(function);

			final JCoParameterList exportParameterList = function.getExportParameterList();

			final JCoTable failed_interactions = exportParameterList.getTable("ET_FAILED_INTERACTIONS");
			final JCoTable messages = exportParameterList.getTable("ET_MESSAGES");
						
			if (!messages.isEmpty())
			{
				logJCoMessages(messages, INFO);
			}
		}
		catch (final BackendException e)
		{
			LOG.error("", e);
		}
		finally
		{
			
		}
		
	}
	
	/**
	 * @param context
	 */
	public List<ProductRecommendation> getProductRecommendation(final RecommendationContextProvider context)
	{				
		if(configuration.getUsage().equals(SapproductrecommendationConstants.SCENARIO))
		{
			return getProductRecommendationByScenario(context);
		}else if(configuration.getUsage().equals(SapproductrecommendationConstants.MODELTYPE)){
			return getProductRecommendationByModelType(context);
		}
		return null;
	}
	
	/**
	 * @param context
	 * @return list of recommended products
	 */
	public List<ProductRecommendation> getProductRecommendationByModelType(final RecommendationContextProvider context)
	{		
		final List<ProductRecommendation> result = new ArrayList<ProductRecommendation>();
      
		//build JCoConneciton
		final JCoConnection jCoConnection = getJcoConnection();
		
		final String recoType = context.getRecotype();
		final String itemType= context.getItemDataSourceType();
		final String productId = context.getProductId();
		final String includeCart = context.getIncludeCart();
		try
		{
			final JCoFunction function = jCoConnection.getFunction("PROD_RECO_GET_RECOMMENDATIONS");
			final JCoParameterList importParameterList = function.getImportParameterList();
			final JCoTable recommenders = importParameterList.getTable("IT_RECOMMENDERS");
			recommenders.appendRow();
			recommenders.setValue("MODEL_TYPE", recoType);
			
			final JCoTable leadingObjects = recommenders.getTable("LEADING_OBJECTS");
			if ( productId != null && !productId.equals("") && !productId.equalsIgnoreCase("null"))
			{
				leadingObjects.appendRow();
				leadingObjects.setValue("ITEM_ID", productId);
				leadingObjects.setValue("ITEM_TYPE", itemType);
			}
			final JCoTable cartEntries = recommenders.getTable("BASKET_OBJECTS");
			
			for (final String cartItem : context.getCartItems())
			{
				cartEntries.appendRow();
				cartEntries.setValue("ITEM_ID", cartItem);
				cartEntries.setValue("ITEM_TYPE", itemType);
				if (includeCart.equalsIgnoreCase(Boolean.toString(Boolean.TRUE)))
				{
					leadingObjects.appendRow();
					leadingObjects.setValue("ITEM_ID", cartItem);
					leadingObjects.setValue("ITEM_TYPE", itemType);
				}
			}

			importParameterList.setValue("IV_USER_ID", context.getUserId());
			importParameterList.setValue("IV_USER_TYPE", context.getUserType());
			
			jCoConnection.execute(function);

			final JCoParameterList exportParameterList = function.getExportParameterList();

			final JCoTable results = exportParameterList.getTable("ET_RESULTS");

			if (!results.isEmpty())
			{
				final int len = results.getNumRows();
				for (int i = 0; i < len; i++)
				{
					results.setRow(i);
					String recommendationType="";
					final String recommendationId = results.getString("ITEM_ID");
					recommendationType = results.getString("MODEL_TYPE");
					final ProductRecommendation productRecommendation = createProductRecommedation(recommendationId,
							recommendationType);
					if (productRecommendation != null)
					{
						result.add(productRecommendation);
					}
				}
			}
			final JCoTable messages = exportParameterList.getTable("ET_MESSAGES");
			
			if (!messages.isEmpty())
			{
				logJCoMessages(messages, INFO);
			}
		}
		catch (final BackendException e)
		{
			LOG.error("", e);
		}
		finally
		{
		}

		return result;	
	}

	/**
	 * @param context
	 * @return list of recommended products
	 */
	public List<ProductRecommendation> getProductRecommendationByScenario(final RecommendationContextProvider context)
	{		
		final List<ProductRecommendation> result = new ArrayList<ProductRecommendation>();
      
		final JCoConnection jCoConnection = getJcoConnection();
		
		final String recoType = context.getRecotype();
		final String itemType= context.getItemDataSourceType();
		final String productId = context.getProductId();
		final String includeCart = context.getIncludeCart();
		
		try
		{
			final JCoFunction function = jCoConnection.getFunction("PROD_RECO_GET_RECO_BY_SCENARIO");
			final JCoParameterList importParameterList = function.getImportParameterList();
			final JCoTable recommenders = importParameterList.getTable("IT_RECOMMENDERS");
			recommenders.appendRow();
			recommenders.setValue("SCENARIO_ID", recoType);
			
			final JCoTable leadingObjects = recommenders.getTable("LEADING_OBJECTS");
			if ( productId != null && !productId.equals("") && !productId.equalsIgnoreCase("null"))
			{
				leadingObjects.appendRow();
				leadingObjects.setValue("ITEM_ID", productId);
				leadingObjects.setValue("ITEM_TYPE", itemType);
			}
			
			final JCoTable cartEntries = recommenders.getTable("BASKET_OBJECTS");
			for (final String cartItem : context.getCartItems())
			{
				cartEntries.appendRow();
				cartEntries.setValue("ITEM_ID", cartItem);
				cartEntries.setValue("ITEM_TYPE", itemType);
				if (includeCart.equalsIgnoreCase(Boolean.toString(Boolean.TRUE)))
				{
					leadingObjects.appendRow();
					leadingObjects.setValue("ITEM_ID", cartItem);
					leadingObjects.setValue("ITEM_TYPE", itemType);
				}
			}

			importParameterList.setValue("IV_USER_ID", context.getUserId());
			importParameterList.setValue("IV_USER_TYPE", context.getUserType());
						
			jCoConnection.execute(function);

			final JCoParameterList exportParameterList = function.getExportParameterList();

			final JCoTable results = exportParameterList.getTable("ET_RESULTS");

			if (!results.isEmpty())
			{
				final int len = results.getNumRows();
				for (int i = 0; i < len; i++)
				{
					results.setRow(i);
					String recommendationType="";
					final String recommendationId = results.getString("ITEM_ID");
					recommendationType = results.getString("SCENARIO_ID");
			
					final ProductRecommendation productRecommendation = createProductRecommedation(recommendationId,
							recommendationType);
					if (productRecommendation != null)
					{
						result.add(productRecommendation);
					}
				}
			}
			final JCoTable messages = exportParameterList.getTable("ET_MESSAGES");
			
			if (!messages.isEmpty())
			{
				logJCoMessages(messages, INFO);
			}
		}
		catch (final BackendException e)
		{
			LOG.error("", e);
		}
		finally
		{
		}

		return result;	
	}
	
	private JCoConnection getJcoConnection()
	{
		JCoConnection jCoConnection;
		if (configuration.getRfcDestinationId() == null)
		{
			jCoConnection = getDefaultJCoConnection();
		}
		else
		{
			jCoConnection = getJCoConnection(JCO_STATELESS, configuration.getRfcDestinationId());
		}

		try
		{
			if (jCoConnection.isBackendAvailable() == false)
			{
				LOG.error("RFC - " + configuration.getRfcDestinationId() + " backend is not available");
			}
		}
		catch (final BackendException e)
		{
			LOG.error("", e);
		}
		return jCoConnection;
	}
	
	/**
	 * @param result
	 */
	private ProductRecommendation createProductRecommedation(final String productId, final String type)
	{
		try
		{
			final ProductModel product = getProductService().getProductForCode(productId);
			final ProductRecommendation productRecommendation = new ProductRecommendation();
			productRecommendation.setProduct(product);
			return productRecommendation;
		}
		catch (final UnknownIdentifierException exception)
		{
			return null;
		}
	}

	/**
	 * @return the productService
	 */
	public ProductService getProductService()
	{
		return productService;
	}

	/**
	 * @param productService
	 *           the productService to set
	 */
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}


	/**
	 * @return hmc configuration reader  
	 */
	public HMCConfigurationReader getConfiguration()
	{
		return configuration;
	}

	/**
	 * @param configuration
	 */
	public void setConfiguration(final HMCConfigurationReader configuration)
	{
		this.configuration = configuration;
	}
	
	private void logJCoMessages(final JCoTable table, final String level){
		final int len = table.getNumRows();
		for (int i = 0; i < len; i++)
		{
			table.setRow(i);
			final String msgId = table.getString("ID");
			final String msg = table.getString("MESSAGE");
			if(level.equals(ERROR)){
				LOG.error(msgId + " " + msg);
			}
			else if(level.equals(INFO)){
				LOG.info(msgId + " " + msg);
			}
		}
	}

}
