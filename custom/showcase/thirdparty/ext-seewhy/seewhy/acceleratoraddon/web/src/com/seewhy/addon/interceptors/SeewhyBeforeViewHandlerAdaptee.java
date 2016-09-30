/**
 *
 */
package com.seewhy.addon.interceptors;

import de.hybris.platform.acceleratorservices.controllers.page.PageType;
import de.hybris.platform.acceleratorservices.storefront.data.JavaScriptVariableData;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.addonsupport.config.javascript.JavaScriptVariableDataFactory;
import de.hybris.platform.addonsupport.interceptors.BeforeViewHandlerAdaptee;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.strategies.CustomerNameStrategy;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;

import com.seewhy.addon.common.SeewhyAddToCartData;
import com.seewhy.addon.common.SeewhyContextData;
import com.seewhy.addon.common.SeewhyEmailData;
import com.seewhy.addon.common.SeewhyGeneralData;
import com.seewhy.addon.common.SeewhyInMemoryRepository;
import com.seewhy.addon.common.SeewhyProductBrowsedData;
import com.seewhy.addon.common.SeewhyReviewData;
import com.seewhy.addon.constants.SeewhyConstants;


/**
 * @author I310444
 *
 */
public class SeewhyBeforeViewHandlerAdaptee implements BeforeViewHandlerAdaptee
{
	private static final Logger LOG = Logger.getLogger(SeewhyBeforeViewHandlerAdaptee.class);

	private ProductService productService = null;
	private UserService userService = null;
	private SessionService sessionService = null;
	private SeewhyInMemoryRepository seewhyInMemoryRepository = null;
	private CustomerNameStrategy customerNameStrategy = null;
	private Populator productPricePopulator = null;
	private Populator productPopulator = null;
	private Populator productCategoriesPopulator = null;

	/**
	 * This implementation of BeforeViewHandlerAdaptee retrieves additional 'seewhy action' details from the model and
	 * supplied services, this is done based on key information set up in the Seewhy In-Memory repository at various
	 * interception points in the request lifecycle (Add To Cart hook, Order Placed hook, Before Controller adaptee...).
	 * The gathered data is sent to the Seewhy client-side code either as JSON or JS AddOn variables.
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @param viewName
	 * @throws Exception
	 * @return
	 */
	@Override
	public String beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelMap model,
			final String viewName) throws Exception
	{
		String strNewViewName = null;

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee beforeView called.");
			logHybrisModelData(model);
		}

		//Assume the resulting view is the view passed in.
		strNewViewName = viewName;

		//The context for distinguishing our request data is the session id.
		if (sessionService != null && sessionService.getCurrentSession() != null)
		{
			String strContext = null;

			strContext = sessionService.getCurrentSession().getSessionId();

			try
			{
				//HashMaps for caching ProductModel and ProductData instances, so that we don't ake multiple calls per request.
				final HashMap<String, ProductModel> hmProductModels = new HashMap();
				final HashMap<String, ProductData> hmProductDataInstances = new HashMap();
				String strModelProductCode = null;

				if (LOG.isDebugEnabled() == true)
				{
					LOG.debug("SeewhyBeforeViewHandlerAdaptee beforeView. strContext: " + strContext);
				}

				//Retrieve the product code from the model if it is available.
				strModelProductCode = retrieveModelProductCode(model);

				//Add the additional 'general' details to the context data.
				populateActionGeneralData(strContext, model);

				//Add the additional 'email captured' details to the context data.
				populateActionEmailCapturedData(strContext);

				//Add the additional 'product browsed' details to the context data.
				populateActionProductBrowsedData(strContext, viewName, model, strModelProductCode, hmProductModels,
						hmProductDataInstances, request);

				//Add the additional 'product reviewed' details to the context data.
				populateActionProductReviewedAdditionalData(strContext, model, hmProductModels, hmProductDataInstances, request);

				//Add the additional 'add to cart' details to the context data.
				populateActionAddToCartAdditionalData(strContext, model, hmProductModels, hmProductDataInstances, request);

				//Add the additional 'order placed' details to the context data.
				populateActionOrderPlacedAdditionalData();

				if (LOG.isDebugEnabled() == true)
				{
					//We should have amassed all the data we need now, let's have a look at it.
					logSeewhyInMemoryRepositoryForContext(strContext);
				}

				//Populate the Seewhy JS Add On Variables in the Model with th details of the Seewhy actions.
				populateJSAddOnVariables(model, strContext);

				//If necessary, update the viewname so that the additional Seewhy client-side functionality is called.
				strNewViewName = constructViewName(viewName, strModelProductCode);
			}
			finally
			{
				//Remove the Seewhy context data from memory.
				this.seewhyInMemoryRepository.removeVariables(strContext);
			}
		}

		//Return the (possibly modified) viewname.
		return strNewViewName;
	}

	/**
	 * Returns the product code from the product found in the model, if there is one. This is used as the basis for
	 * 'product browsed' actions which are only discovered at the 'before view handler' level (we don't have any other
	 * hooks into core Hybris for discovering this action).
	 *
	 * @param model
	 * @return
	 */
	private String retrieveModelProductCode(final ModelMap model)
	{
		ProductData productData = null;
		String strModelProductCode = null;

		productData = (ProductData) model.get(SeewhyConstants.MODEL_ENTRY_PRODUCT);

		if (productData != null)
		{
			strModelProductCode = productData.getCode();
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee getModelProductCode. strModelProductCode: " + strModelProductCode);
		}

		return strModelProductCode;
	}

	/**
	 * Retrieves the ProductModel given a product code. Will search the internal (to this thread) cache before using the
	 * ProductService.
	 *
	 * @param hmProductModels
	 * @param strCode
	 * @return
	 */
	private ProductModel retrieveProductModelForCode(final HashMap<String, ProductModel> hmProductModels, final String strCode)
	{
		ProductModel productModel = null;

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee retrieveProductModelForCode. strCode: " + strCode);
		}

		//Possibly unecessary synchronization as the hmProductModels instance is currently an automatic variable,
		//so can't be accessed by other threads, but who knows how the code will evolve!
		synchronized (hmProductModels)
		{
			productModel = hmProductModels.get(strCode);

			if (productModel == null)
			{
				productModel = productService.getProductForCode(strCode);
				if (productModel != null)
				{
					if (LOG.isDebugEnabled() == true)
					{
						LOG.debug("SeewhyBeforeViewHandlerAdaptee retrieveProductModelForCode. Retrieved from ProductService. strCode: "
								+ strCode);
					}

					hmProductModels.put(strCode, productModel);
				}
			}
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee retrieveProductModelForCode. productModel: " + productModel);
		}

		return productModel;
	}

	/**
	 * Retrieves the ProductData given a ProductModel. Will search the internal (to this thread) cache before using the
	 * Product Populators. We don't use the ProductData that is possibly in the model as we don't know how that has been
	 * populated (which populators have been used).
	 *
	 * @param hmProductDataInstances
	 * @param productModel
	 * @return
	 */
	private ProductData retrieveProductDataForProductModel(final HashMap<String, ProductData> hmProductDataInstances,
			final ProductModel productModel)
	{
		ProductData productData = null;

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee retrieveProductDataForProductModel. productModel.getCode(): "
					+ productModel.getCode());
		}

		//Possibly unecessary synchronization as the hmProductDataInstances instance is currently an automatic variable,
		//so can't be accessed by other threads, but who knows how the code will evolve!
		synchronized (hmProductDataInstances)
		{
			productData = hmProductDataInstances.get(productModel.getCode());

			if (productData == null)
			{
				productData = constructProductDataInstance(productModel);

				if (productData != null)
				{
					if (LOG.isDebugEnabled() == true)
					{
						LOG.debug("SeewhyBeforeViewHandlerAdaptee retrieveProductDataForProductModel. Retrieved from ProductPricePopulator. productModel.getCode(): "
								+ productModel.getCode());
					}

					hmProductDataInstances.put(productModel.getCode(), productData);
				}
			}
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee retrieveProductDataForProductModel. productData: " + productData);
		}

		return productData;
	}

	/**
	 * Constructs the product page URL.
	 *
	 * @param request
	 * @param productData
	 * @return
	 */
	private String constructProductURL(final HttpServletRequest request, final ProductData productData)
	{
		final StringBuffer sbProductURL = new StringBuffer("");

		if (request != null)
		{
			if (LOG.isDebugEnabled() == true)
			{
				LOG.debug("SeewhyBeforeViewHandlerAdaptee getProductURL. request.getRequestURI(): " + request.getRequestURI());
				LOG.debug("SeewhyBeforeViewHandlerAdaptee getProductURL. request.getRequestURL(): " + request.getRequestURL());
				LOG.debug("SeewhyBeforeViewHandlerAdaptee getProductURL. request.getScheme(): " + request.getScheme());
				LOG.debug("SeewhyBeforeViewHandlerAdaptee getProductURL. request.getServerName(): " + request.getServerName());
				LOG.debug("SeewhyBeforeViewHandlerAdaptee getProductURL. request.getServerPort(): " + request.getServerPort());
				LOG.debug("SeewhyBeforeViewHandlerAdaptee getProductURL. request.getServletPath(): " + request.getServletPath());
				LOG.debug("SeewhyBeforeViewHandlerAdaptee getProductURL. request.getServletContext(): " + request.getServletContext());
				LOG.debug("SeewhyBeforeViewHandlerAdaptee getProductURL. request.getContextPath(): " + request.getContextPath());
			}

			sbProductURL.append(request.getScheme());
			sbProductURL.append("://");
			sbProductURL.append(request.getServerName());
			sbProductURL.append(":");
			sbProductURL.append(request.getServerPort());
			sbProductURL.append(request.getContextPath());
		}

		sbProductURL.append(productData.getUrl());

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee getProductURL. sbProductURL.toString(): " + sbProductURL.toString());
		}

		return sbProductURL.toString();
	}

	/**
	 * Constructs the Seewhy 'Return To Cart' Link.
	 *
	 * @param request
	 * @return
	 */
	private String constructReturnToLinkURL(final HttpServletRequest request)
	{
		final StringBuffer sbReturnToLinkURL = new StringBuffer("");

		if (request != null)
		{
			sbReturnToLinkURL.append(request.getScheme());
			sbReturnToLinkURL.append("://");
			sbReturnToLinkURL.append(request.getServerName());
			sbReturnToLinkURL.append(":");
			sbReturnToLinkURL.append(request.getServerPort());
			sbReturnToLinkURL.append(request.getContextPath());
			sbReturnToLinkURL.append(SeewhyConstants.CORE_URL_SUFFIX_CART);
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee getReturnToLinkURL. sbReturnToLinkURL.toString(): "
					+ sbReturnToLinkURL.toString());
		}

		return sbReturnToLinkURL.toString();
	}

	/**
	 * Construct the product image URL.
	 *
	 * @param request
	 * @param imageData
	 * @return
	 */
	private String constructProductImageURL(final HttpServletRequest request, final ImageData imageData)
	{
		final StringBuffer sbProductImageURL = new StringBuffer("");

		if (request != null)
		{
			sbProductImageURL.append(request.getScheme());
			sbProductImageURL.append("://");
			sbProductImageURL.append(request.getServerName());
			sbProductImageURL.append(":");
			sbProductImageURL.append(request.getServerPort());
		}

		sbProductImageURL.append(imageData.getUrl());

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee getProductImageURL. sbProductImageURL.toString(): "
					+ sbProductImageURL.toString());
		}

		return sbProductImageURL.toString();
	}

	/**
	 * Retrieve the category name for the given product. Try to find the category name in the model, if it's not present
	 * retrieve from the product itself.
	 *
	 * @param model
	 * @param productData
	 * @return
	 */
	private String retrieveCategoryNameForProduct(final ModelMap model, final ProductData productData)
	{
		String strCategoryName = null;

		//Get the product category.
		strCategoryName = retrieveCategoryNameFromModel(model);
		if (strCategoryName == null || strCategoryName.equalsIgnoreCase("") == true)
		{
			Collection cltCategories = null;
			cltCategories = productData.getCategories();
			if (cltCategories != null)
			{
				final Iterator itr2 = cltCategories.iterator();
				while (itr2.hasNext() == true)
				{
					CategoryData categoryData = null;
					//CategoryModel categoryModel = null;

					categoryData = (CategoryData) itr2.next();

					if (LOG.isDebugEnabled() == true)
					{
						LOG.debug("SeewhyBeforeViewHandlerAdaptee retrieveCategoryNameForProduct. categoryData: "
								+ categoryData.getCode() + "/" + categoryData.getName() + "/" + categoryData.getDescription());
					}

					//Not sure if there can be multiple of these returned here, I guess so as it's a collection!
					//I've only ever seen one returned, need to look into this.
					strCategoryName = categoryData.getName();
				}
			}
		}

		if (strCategoryName == null)
		{
			strCategoryName = "";
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee retrieveCategoryNameForProduct. strCategoryName: " + strCategoryName);
		}

		return strCategoryName;
	}

	/**
	 * Retrieve the Image URL for a product. Currently uses the product images set up via the PrimaryImagePopulator.
	 *
	 * @param productData
	 * @param request
	 * @return
	 */
	private String retrieveImageURLForProduct(final ProductData productData, final HttpServletRequest request)
	{
		Collection cltImages = null;
		String strProductImageURL = null;

		//Retrieve the image URL for this product.
		cltImages = productData.getImages();
		if (cltImages != null && cltImages.size() > 0)
		{
			ImageData imageData = null;

			//Image we want is the first one as we're using the PrimaryImagePopulator via the ProductPopulator.
			imageData = (ImageData) cltImages.iterator().next();

			strProductImageURL = constructProductImageURL(request, imageData);
		}

		if (strProductImageURL == null)
		{
			strProductImageURL = "";
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee retrieveImageURLForProduct. strProductImageURL: " + strProductImageURL);
		}

		return strProductImageURL;
	}

	/**
	 * Retrieve the 'Base Product' product code for a given product. Returns a blank string if the product is not a
	 * variant.
	 *
	 * @param productModel
	 * @return
	 */
	private String retrieveBaseProductCodeForProduct(final ProductModel productModel)
	{
		String strBaseProductCode = null;

		if (productModel instanceof VariantProductModel)
		{
			ProductModel baseProductModel = null;
			VariantProductModel variantProductModel = null;

			variantProductModel = (VariantProductModel) productModel;
			baseProductModel = variantProductModel.getBaseProduct();

			strBaseProductCode = baseProductModel.getCode();
		}

		if (strBaseProductCode == null)
		{
			strBaseProductCode = "";
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee retrieveBaseProductCodeForProduct. strBaseProductCode: " + strBaseProductCode);
		}

		return strBaseProductCode;
	}

	/**
	 * Sets the product details in the given SeewhyContextData, called for multiple Seewhy Actions.
	 *
	 * @param strProductCode
	 * @param seewhyContextData
	 * @param hmProductModels
	 * @param hmProductDataInstances
	 * @param model
	 * @param request
	 */
	private void populateProductDetailsInContextData(final String strProductCode, final SeewhyContextData seewhyContextData,
			final HashMap<String, ProductModel> hmProductModels, final HashMap<String, ProductData> hmProductDataInstances,
			final ModelMap model, final HttpServletRequest request)
	{
		ProductModel productModel = null;

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee populateProductDetailsInContextData. strProductCode: " + strProductCode);
		}

		//Retrieve the ProductModel
		productModel = retrieveProductModelForCode(hmProductModels, strProductCode);

		if (productModel != null)
		{
			ProductData productData = null;

			//Retrieve the productdata. We don't currently use the productdata instance that may be present
			//in the model as we don't know which populators have been used in its construction.
			productData = retrieveProductDataForProductModel(hmProductDataInstances, productModel);

			//Retrieve product description.
			seewhyContextData.setDataItem(SeewhyConstants.SEEWHY_ACTION_GENERIC_PRODUCTDESC, productModel.getSummary());

			//Retrieve product name.
			seewhyContextData.setDataItem(SeewhyConstants.SEEWHY_ACTION_GENERIC_PRODUCTNAME, productModel.getName());

			//Retrieve product price.
			seewhyContextData.setDataItem(SeewhyConstants.SEEWHY_ACTION_GENERIC_PRODUCTPRICEVALUE,
					(productData.getPrice() != null ? "" + productData.getPrice().getValue() : ""));

			//Retrieve price currency.
			seewhyContextData.setDataItem(SeewhyConstants.SEEWHY_ACTION_GENERIC_PRODUCTPRICECURRENCY,
					(productData.getPrice() != null ? productData.getPrice().getCurrencyIso() : ""));

			//Retrieve product page URL.
			seewhyContextData.setDataItem(SeewhyConstants.SEEWHY_ACTION_GENERIC_PRODUCTURL,
					constructProductURL(request, productData));

			//Retrieve product average rating.
			seewhyContextData.setDataItem(SeewhyConstants.SEEWHY_ACTION_GENERIC_PRODUCTAVGREVIEWRATING,
					(productData.getAverageRating() != null ? "" + productData.getAverageRating() : ""));

			//Retrieve the image URL for this product.
			seewhyContextData.setDataItem(SeewhyConstants.SEEWHY_ACTION_GENERIC_PRODUCTIMAGEURL,
					retrieveImageURLForProduct(productData, request));

			//Retrieve the product category.
			seewhyContextData.setDataItem(SeewhyConstants.SEEWHY_ACTION_GENERIC_CATEGORYNAME,
					retrieveCategoryNameForProduct(model, productData));

			//Retrieve the 'base product' product code.
			seewhyContextData.setDataItem(SeewhyConstants.SEEWHY_ACTION_GENERIC_BASEPRODUCTCODE,
					retrieveBaseProductCodeForProduct(productModel));
		}
		else
		{
			//Don't throw an exception, let the rest of the product suite function even if Seewhy is floundering!
			//Inform of the error though.
			LOG.error("No ProductModel found for code: " + strProductCode);
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee populateProductDetailsInContextData. seewhyContextData: "
					+ seewhyContextData.toString());
		}
	}

	/**
	 * Construct and populate a ProductData instance for the given product model.
	 *
	 * @param productModel
	 * @return
	 */
	private ProductData constructProductDataInstance(final ProductModel productModel)
	{
		final ProductData productData = new ProductData();

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee constructProductDataInstance. productModel.getCode(): "
					+ productModel.getCode());
		}

		//Populators to use in the construction of the ProductData instance.
		productPricePopulator.populate(productModel, productData);
		productPopulator.populate(productModel, productData);
		productCategoriesPopulator.populate(productModel, productData);

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee constructProductDataInstance. productData.getPrice(): "
					+ productData.getPrice());
		}

		return productData;
	}

	/**
	 * Add the details discovered at the 'before view' stage to those discovered when the 'product review' request was
	 * intercepted. Remember: a product review may not have taken place at all!
	 *
	 * @param strContext
	 * @param model
	 * @param hmProductModels
	 * @param hmProductDataInstances
	 * @param request
	 */
	private void populateActionProductReviewedAdditionalData(final String strContext, final ModelMap model,
			final HashMap<String, ProductModel> hmProductModels, final HashMap<String, ProductData> hmProductDataInstances,
			final HttpServletRequest request)
	{
		SeewhyReviewData seewhyReviewData = null;

		//Populate Review data
		seewhyReviewData = (SeewhyReviewData) seewhyInMemoryRepository.getVariable(strContext,
				SeewhyConstants.SEEWHY_ACTION_PRODUCT_REVIEWED);

		if (seewhyReviewData != null)
		{
			populateProductDetailsInContextData(seewhyReviewData.getCode(), seewhyReviewData, hmProductModels,
					hmProductDataInstances, model, request);
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee populateActionProductReviewedAdditionalData. seewhyReviewData: "
					+ seewhyReviewData);
		}
	}

	/**
	 * Add the details discovered at the 'before view' stage to those discovered when the 'add to cart' request was
	 * intercepted. Remember: an 'add to cart' request may not have taken place at all!
	 *
	 * @param strContext
	 * @param model
	 * @param hmProductModels
	 * @param hmProductDataInstances
	 * @param request
	 */
	private void populateActionAddToCartAdditionalData(final String strContext, final ModelMap model,
			final HashMap<String, ProductModel> hmProductModels, final HashMap<String, ProductData> hmProductDataInstances,
			final HttpServletRequest request)
	{
		SeewhyAddToCartData seewhyAddToCartData = null;

		//Populate Add To Cart data
		seewhyAddToCartData = (SeewhyAddToCartData) seewhyInMemoryRepository.getVariable(strContext,
				SeewhyConstants.SEEWHY_ACTION_ADD_TO_CART);

		if (seewhyAddToCartData != null)
		{
			//Retrieve the 'return to' link.
			seewhyAddToCartData.setReturnToLink(constructReturnToLinkURL(request));

			populateProductDetailsInContextData(seewhyAddToCartData.getCode(), seewhyAddToCartData, hmProductModels,
					hmProductDataInstances, model, request);
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee populateActionAddToCartAdditionalData. seewhyAddToCartData: "
					+ seewhyAddToCartData);
		}
	}

	/**
	 * Add the details discovered at the 'before view' stage to those discovered when the 'place order' request was
	 * intercepted. Remember: an 'add to cart' request may not have taken place at all!
	 *
	 * NOTE: This is just a placeholder at the moment, nothing new has been discovered at this stage.
	 */
	private void populateActionOrderPlacedAdditionalData()
	{
		//Placeholder, currently all context data for this action is added in the SeewhyCommercePlaceOrderMethodHook class.
	}

	/**
	 * Adds the details of all of the Seewhy actions that have occurred to the JSAddOnVariables. These are subsequently
	 * made available to the client as JavaScript variables or as part of a JSON response depending on the resulting
	 * view.
	 *
	 * @param model
	 * @param strContext
	 */
	private void populateJSAddOnVariables(final ModelMap model, final String strContext)
	{
		SeewhyContextData seewhyContextData = null;
		ArrayList alSeewhyJSAddOnVariables = null;

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee populateJSAddOnVariables called.");
		}

		//Retrieve the JS AddOn variables from the model.
		alSeewhyJSAddOnVariables = retrieveSeewhyJSAddOnVariables(model);

		for (final String strSeewhyAction : SeewhyConstants.SEEWHY_ACTIONS)
		{
			seewhyContextData = (SeewhyContextData) seewhyInMemoryRepository.getVariable(strContext, strSeewhyAction);
			if (seewhyContextData != null)
			{
				//Update the JS AddOn variables with the details or each Seewhy action that has occurred.
				populateJSAddOnVariablesForAction(alSeewhyJSAddOnVariables, seewhyContextData, strSeewhyAction);
			}
		}
	}

	/**
	 * Adds the details of the Seewhy action to the JSAddOnVariables.
	 *
	 * @param alSeewhyJSAddOnVariables
	 * @param seewhyContextDate
	 * @param strVariablePrefix
	 */
	private void populateJSAddOnVariablesForAction(final ArrayList alSeewhyJSAddOnVariables,
			final SeewhyContextData seewhyContextDate, final String strVariablePrefix)
	{
		JavaScriptVariableData javaScriptVariableData = null;
		HashMap hmDataItems = null;
		Map.Entry entry = null;
		Iterator itr = null;

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee populateJSAddOnVariablesForAction. strVariablePrefix: " + strVariablePrefix);
		}

		if (seewhyContextDate != null)
		{
			//Add the variable that indicates that data for this action is available.
			javaScriptVariableData = JavaScriptVariableDataFactory.create(strVariablePrefix,
					SeewhyConstants.SEEWHY_JSADDONSVARIABLES_ENTRY_VALUE_TRUE);
			alSeewhyJSAddOnVariables.add(javaScriptVariableData);

			if (LOG.isDebugEnabled() == true)
			{
				LOG.debug("SeewhyBeforeViewHandlerAdaptee populateJSAddOnVariablesForAction. Adding: "
						+ javaScriptVariableData.getQualifier() + "/" + javaScriptVariableData.getValue());
			}

			//Get all of the variables (key/value pairs) for this action.
			hmDataItems = seewhyContextDate.getDataItems();

			//Iterator through all of the variables and add them to the JS AddOn variable.
			itr = hmDataItems.entrySet().iterator();
			while (itr.hasNext() == true)
			{
				String strEscapedValue = null;

				entry = (Map.Entry) itr.next();

				strEscapedValue = StringEscapeUtils.escapeJavaScript((String) entry.getValue());

				javaScriptVariableData = JavaScriptVariableDataFactory.create(strVariablePrefix + "." + entry.getKey(),
						strEscapedValue);
				alSeewhyJSAddOnVariables.add(javaScriptVariableData);

				if (LOG.isDebugEnabled() == true)
				{
					LOG.debug("SeewhyBeforeViewHandlerAdaptee populateJSAddOnVariablesForAction. Adding: "
							+ javaScriptVariableData.getQualifier() + "/" + javaScriptVariableData.getValue());
				}
			}
		}
	}

	/**
	 * Retrieves the Seewhy AddOn JS variables from the model.
	 *
	 * @param model
	 * @return
	 */
	private ArrayList retrieveSeewhyJSAddOnVariables(final ModelMap model)
	{
		HashMap hmAllJSAddOnVariables = null;
		ArrayList alSeeWhyJSAddOnVariables = null;

		hmAllJSAddOnVariables = (HashMap) model.get(SeewhyConstants.MODEL_ENTRY_JSADDONSVARIABLES);

		if (hmAllJSAddOnVariables != null)
		{
			alSeeWhyJSAddOnVariables = (ArrayList) hmAllJSAddOnVariables.get(SeewhyConstants.EXTENSIONNAME);
		}

		return alSeeWhyJSAddOnVariables;
	}

	/**
	 * Constructs the viewname to return from this Before view handler. There are currently two circumstances where we
	 * wish to modify the returned view, this is to allow our JS variables or JSON to be received and acted upon in the
	 * client code.
	 *
	 * @param viewName
	 * @param strModelProductCode
	 * @return
	 */
	private String constructViewName(final String viewName, final String strModelProductCode)
	{
		String strNewViewName = viewName;

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee constructViewName. viewName: " + viewName);
		}

		if (isProductAddedToCartOccurring(viewName) == true)
		{
			//If we're adding a product to the cart use this view...
			strNewViewName = SeewhyConstants.SEEWHY_VIEWNAME_ADDTOCARTPOPUP;
		}
		else if (isProductQuickBrowseOccurring(viewName, strModelProductCode) == true)
		{
			//If we're about to 'quick view' a product use this view...
			strNewViewName = SeewhyConstants.SEEWHY_VIEWNAME_QUICKVIEWPOPUP;
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee constructViewName. strNewViewName: " + strNewViewName);
		}

		return strNewViewName;
	}

	/**
	 * Are we currently processing a 'product standard browse' request (not 'quick view').
	 *
	 * @param strPageType
	 * @param strModelProductCode
	 * @return
	 */
	private boolean isProductStandardBrowseOccurring(final String strPageType, final String strModelProductCode)
	{
		boolean blnStandardBrowse = false;

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee isProductStandardBrowseOccurring. strPageType: " + strPageType);
		}

		if (strPageType != null && strPageType.equalsIgnoreCase(PageType.PRODUCT.name()) == true && strModelProductCode != null)
		{
			blnStandardBrowse = true;
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee isProductStandardBrowseOccurring. blnStandardBrowse: " + blnStandardBrowse);
		}

		return blnStandardBrowse;
	}

	/**
	 * Are we currently processing an 'add to cart' request. Possibly this should just look for the existence of the Add
	 * to Cart Seewhy action in the Seewhy in memory repository?
	 *
	 * @param viewName
	 * @return
	 */
	private boolean isProductAddedToCartOccurring(final String viewName)
	{
		boolean blnStandardBrowse = false;

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee isProductAddedToCartOccurring. viewName: " + viewName);
		}

		if (viewName != null && viewName.compareTo(SeewhyConstants.CORE_VIEWNAME_ADDTOCARTPOPUP) == 0)
		{
			blnStandardBrowse = true;
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee isProductAddedToCartOccurring. blnStandardBrowse: " + blnStandardBrowse);
		}

		return blnStandardBrowse;
	}

	/**
	 * Are we currently processing a 'quick view product browse' request (not 'standard').
	 *
	 * @param viewName
	 * @param strModelProductCode
	 * @return
	 */
	private boolean isProductQuickBrowseOccurring(final String viewName, final String strModelProductCode)
	{
		boolean blnQuickViewBrowse = false;

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee isProductQuickBrowseOccurring. viewName: " + viewName);
		}

		if (viewName != null && viewName.compareTo(SeewhyConstants.CORE_VIEWNAME_QUICKVIEWPOPUP) == 0
				&& strModelProductCode != null)
		{
			blnQuickViewBrowse = true;
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee isProductQuickBrowseOccurring. blnQuickViewBrowse: " + blnQuickViewBrowse);
		}

		return blnQuickViewBrowse;
	}

	/**
	 * Populates the In-memory repository with the data discovered at the before view handler stage for the 'General'
	 * action. The 'General' action is not really an action at all, it's general state information that is required by
	 * the Seewhy client code.
	 *
	 * @param strContext
	 * @param model
	 */
	private void populateActionGeneralData(final String strContext, final ModelMap model)
	{
		String strPageType = null;
		SeewhyGeneralData seewhyGeneralData = null;

		//Retrieve the pagetype.
		strPageType = (String) model.get(SeewhyConstants.MODEL_ENTRY_PAGETYPE);
		if (strPageType == null)
		{
			strPageType = "";
		}

		//Create the 'general' action context data.
		seewhyGeneralData = new SeewhyGeneralData();

		//Populate the general action context data.
		seewhyGeneralData.setPageType(strPageType);

		//Let the client know the server context key (for email functionality in JS).
		seewhyGeneralData.setServerContext(strContext);

		//Add the general action context data to the in-memory repository.
		this.seewhyInMemoryRepository.setVariable(strContext, SeewhyConstants.SEEWHY_ACTION_GENERAL, seewhyGeneralData);

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee beforeView populateActionGeneralData. seewhyGeneralData: " + seewhyGeneralData);
		}
	}

	/**
	 * Retrieves the category name for the given product from the model (if possible). Uses the 'categoryName' entry, and
	 * if required the 'breadcrumbs' entry.
	 *
	 * @param model
	 */
	private String retrieveCategoryNameFromModel(final ModelMap model)
	{
		String strCategoryName = null;

		//Try the 'categoryName' entry...
		strCategoryName = (String) model.get(SeewhyConstants.MODEL_ENTRY_CATEGORYNAME);

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee beforeView retrieveCategoryNameFromModel. strCategoryName from model: "
					+ strCategoryName);
		}

		//If the categoryName is not populated try the breadcrumbs entry...
		if (strCategoryName == null || strCategoryName.trim().equalsIgnoreCase("") == true)
		{
			strCategoryName = retrieveCategoryNameFromBreadcrumbs(model);

			if (LOG.isDebugEnabled() == true)
			{
				LOG.debug("SeewhyBeforeViewHandlerAdaptee beforeView retrieveCategoryNameFromModel. strCategoryName from breadcrumbs: "
						+ strCategoryName);
			}
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee beforeView retrieveCategoryNameFromModel. strCategoryName: " + strCategoryName);
		}

		return strCategoryName;
	}

	/**
	 * Retrieves the category name from the breadcrumbs entry in the model.
	 *
	 * @param model
	 * @return
	 */
	private String retrieveCategoryNameFromBreadcrumbs(final ModelMap model)
	{
		List listBreadcrumbs = null;
		String strCategoryName = null;

		//Retrieve the breadcrumbs entry from the model.
		listBreadcrumbs = (List) model.get(SeewhyConstants.MODEL_ENTRY_BREADCRUMBS);

		if (listBreadcrumbs != null && listBreadcrumbs.size() > 0)
		{
			final Iterator itr = listBreadcrumbs.iterator();
			Breadcrumb breadcrumb = null;

			while (itr.hasNext() == true)
			{
				breadcrumb = (Breadcrumb) itr.next();

				if (LOG.isDebugEnabled() == true)
				{
					LOG.debug("SeewhyBeforeViewHandlerAdaptee beforeView called retrieveCategoryNameFromBreadcrumbs NEW. breadcrumb.getName() = "
							+ breadcrumb.getName());
					LOG.debug("SeewhyBeforeViewHandlerAdaptee beforeView called retrieveCategoryNameFromBreadcrumbs NEW. breadcrumb.getCategoryCode() = "
							+ breadcrumb.getCategoryCode());
					LOG.debug("SeewhyBeforeViewHandlerAdaptee beforeView called retrieveCategoryNameFromBreadcrumbs NEW. breadcrumb.getLinkClass() = "
							+ breadcrumb.getLinkClass());
					LOG.debug("SeewhyBeforeViewHandlerAdaptee beforeView called retrieveCategoryNameFromBreadcrumbs NEW. breadcrumb.getUrl() = "
							+ breadcrumb.getUrl());
				}

				//The product entry in the breadcrumb list has no category code...
				if (breadcrumb.getCategoryCode() != null)
				{
					//..so the category name we want is from the last entry on the list that has a non null category code.
					strCategoryName = breadcrumb.getName();
				}
			}
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee beforeView called retrieveCategoryNameFromBreadcrumbs. strCategoryName: "
					+ strCategoryName);
		}

		return strCategoryName;
	}

	/**
	 * Populates the In-memory repository with the data discovered at the before view handler stage for the 'Product
	 * Browsed' action.
	 *
	 * @param strContext
	 * @param viewName
	 * @param model
	 * @param strModelProductCode
	 * @param hmProductModels
	 * @param hmProductDataInstances
	 * @param request
	 */
	private void populateActionProductBrowsedData(final String strContext, final String viewName, final ModelMap model,
			final String strModelProductCode, final HashMap<String, ProductModel> hmProductModels,
			final HashMap<String, ProductData> hmProductDataInstances, final HttpServletRequest request)
	{
		int iBrowseType = SeewhyConstants.SEEWHY_ACTION_PRODUCT_BROWSED_NONE;
		String strPageType = null;
		SeewhyProductBrowsedData seewhyProductBrowsedData = null;

		strPageType = (String) model.get(SeewhyConstants.MODEL_ENTRY_PAGETYPE);

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee populateActionProductBrowsedData. strPageType: " + strPageType);
			LOG.debug("SeewhyBeforeViewHandlerAdaptee populateActionProductBrowsedData. strModelProductCode: " + strModelProductCode);
		}

		//Determine what type of product browse (if any) is occurring.
		if (isProductStandardBrowseOccurring(strPageType, strModelProductCode) == true)
		{
			iBrowseType = SeewhyConstants.SEEWHY_ACTION_PRODUCT_BROWSED_STANDARD;
		}
		else if (isProductQuickBrowseOccurring(viewName, strModelProductCode) == true)
		{
			iBrowseType = SeewhyConstants.SEEWHY_ACTION_PRODUCT_BROWSED_QUICKVIEW;
		}

		//If product browse is occurring...
		if (iBrowseType != SeewhyConstants.SEEWHY_ACTION_PRODUCT_BROWSED_NONE)
		{
			//...set-up the product browse context data.
			seewhyProductBrowsedData = new SeewhyProductBrowsedData();

			seewhyProductBrowsedData.setCode(strModelProductCode);
			seewhyProductBrowsedData.setBrowseType("" + iBrowseType);

			this.seewhyInMemoryRepository.setVariable(strContext, SeewhyConstants.SEEWHY_ACTION_PRODUCT_BROWSED,
					seewhyProductBrowsedData);

			populateProductDetailsInContextData(seewhyProductBrowsedData.getCode(), seewhyProductBrowsedData, hmProductModels,
					hmProductDataInstances, model, request);
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee populateActionProductBrowsedData. seewhyProductBrowsedData: "
					+ seewhyProductBrowsedData);
		}
	}

	/**
	 * Populates the In-memory repository with the data discovered at the before view handler stage for the 'Email
	 * Captured' action.
	 *
	 * @param strContext
	 */
	private void populateActionEmailCapturedData(final String strContext)
	{
		SeewhyEmailData seewhyEmailData = null;
		UserModel userModel = null;

		userModel = userService.getCurrentUser();
		if (userModel != null)
		{
			String strEmail = null;
			seewhyEmailData = new SeewhyEmailData();

			if (LOG.isDebugEnabled() == true)
			{
				LOG.debug("SeewhyBeforeViewHandlerAdaptee populateActionEmailCapturedData. userModel.getUid(): " + userModel.getUid());
				LOG.debug("SeewhyBeforeViewHandlerAdaptee populateActionEmailCapturedData. userModel.getDisplayName(): "
						+ userModel.getDisplayName());
			}

			final boolean isAnonymousUser = userService.isAnonymousUser(userModel);

			//The email address is...
			// For an anonymous user: the substring after the '|' character in the usermodel Uid.
			// For a known user: the usermodel Uid.
			strEmail = isAnonymousUser ? StringUtils.substringAfter(userModel.getUid(), "|") : userModel.getUid();

			//If we've got an email address...
			if (strEmail != null && strEmail.trim().equalsIgnoreCase("") == false)
			{
				//...search for the first and last name.
				String[] arrNameParts = null;
				String strFirstName = "";
				String strLastName = "";

				if (LOG.isDebugEnabled() == true)
				{
					LOG.debug("SeewhyBeforeViewHandlerAdaptee populateActionEmailCapturedData. strEmail: " + strEmail);
				}

				arrNameParts = customerNameStrategy.splitName(userModel.getName());

				if (arrNameParts != null && arrNameParts.length > 0)
				{
					strFirstName = arrNameParts[0];
					strLastName = arrNameParts[arrNameParts.length - 1];
				}

				if (LOG.isDebugEnabled() == true)
				{
					LOG.debug("SeewhyBeforeViewHandlerAdaptee populateActionEmailCapturedData. strFirstName: " + strFirstName);
					LOG.debug("SeewhyBeforeViewHandlerAdaptee populateActionEmailCapturedData. strLastName: " + strLastName);
				}

				seewhyEmailData.setEmailAddress(strEmail);
				seewhyEmailData.setFirstName(strFirstName);
				seewhyEmailData.setLastName(strLastName);

				this.seewhyInMemoryRepository.setVariable(strContext, SeewhyConstants.SEEWHY_ACTION_EMAIL_CAPTURED, seewhyEmailData);
			}
		}

		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee populateActionEmailCapturedData. seewhyEmailData: " + seewhyEmailData);
		}
	}

	/**
	 *
	 * @return
	 */
	public UserService getUserService()
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee getUserService called: " + userService);
		}

		return userService;
	}

	//@Required
	/**
	 *
	 * @param userService
	 */
	public void setUserService(final UserService userService)
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee setUserService called: " + userService);
		}

		this.userService = userService;
	}

	/**
	 *
	 * @return
	 */
	public SessionService getSessionService()
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee getSessionService called: " + sessionService);
		}

		return sessionService;
	}

	//@Required
	/**
	 *
	 * @param sessionService
	 */
	public void setSessionService(final SessionService sessionService)
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee setSessionService called: " + sessionService);
		}

		this.sessionService = sessionService;
	}

	/**
	 *
	 * @return
	 */
	public ProductService getProductService()
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee getProductService called: " + productService);
		}

		return productService;
	}

	//@Required
	/**
	 *
	 * @param productService
	 */
	public void setProductService(final ProductService productService)
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee setProductService called: " + productService);
		}

		this.productService = productService;
	}

	/**
	 *
	 * @return
	 */
	public CustomerNameStrategy getCustomerNameStrategy()
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee getCustomerNameStrategy called: " + customerNameStrategy);
		}

		return customerNameStrategy;
	}

	/**
	 *
	 * @param customerNameStrategy
	 */
	public void setCustomerNameStrategy(final CustomerNameStrategy customerNameStrategy)
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee setCustomerNameStrategy called: " + customerNameStrategy);
		}

		this.customerNameStrategy = customerNameStrategy;
	}

	/**
	 *
	 * @return
	 */
	public SeewhyInMemoryRepository getSeewhyInMemoryRepository()
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee getSeewhyInMemoryRepository called: " + seewhyInMemoryRepository);
		}

		return seewhyInMemoryRepository;
	}

	//@Required
	/**
	 *
	 * @param seewhyInMemoryRepository
	 */
	public void setSeewhyInMemoryRepository(final SeewhyInMemoryRepository seewhyInMemoryRepository)
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee setSeewhyInMemoryRepository called: " + seewhyInMemoryRepository);
		}

		this.seewhyInMemoryRepository = seewhyInMemoryRepository;
	}

	/**
	 *
	 * @return
	 */
	public Populator getProductPricePopulator()
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee getProductPricePopulator called: " + productPricePopulator);
		}

		return productPricePopulator;
	}

	/**
	 *
	 * @param m_ProductPricePopulator
	 */
	public void setProductPricePopulator(final Populator productPricePopulator)
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee setProductPricePopulator called: " + productPricePopulator);
		}

		this.productPricePopulator = productPricePopulator;
	}

	/**
	 *
	 * @return
	 */
	public Populator getProductPopulator()
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee getProductPopulator called: " + productPopulator);
		}

		return productPopulator;
	}

	/**
	 *
	 * @param productPopulator
	 */
	public void setProductPopulator(final Populator productPopulator)
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee setProductPopulator called: " + productPopulator);
		}

		this.productPopulator = productPopulator;
	}

	/**
	 *
	 * @return
	 */
	public Populator getProductCategoriesPopulator()
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee getProductCategoriesPopulator called: " + productCategoriesPopulator);
		}

		return productCategoriesPopulator;
	}

	/**
	 *
	 * @param productCategoriesPopulator
	 */
	public void setProductCategoriesPopulator(final Populator productCategoriesPopulator)
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyBeforeViewHandlerAdaptee setProductCategoriesPopulator called: " + productCategoriesPopulator);
		}

		this.productCategoriesPopulator = productCategoriesPopulator;
	}


	/**
	 * Logs the current content of the model. For debug purposes only.
	 *
	 * @param model
	 */
	private void logHybrisModelData(final ModelMap model)
	{
		Map.Entry entry = null;
		final Iterator itr = model.entrySet().iterator();
		while (itr.hasNext() == true)
		{
			entry = (Map.Entry) itr.next();

			LOG.debug("SeewhyBeforeViewHandlerAdaptee beforeView called logHybrisModelData NEW. Entry = " + entry.getKey() + "/"
					+ entry.getValue() + "/" + (entry.getValue() == null ? "<null>" : entry.getValue().getClass().getName()));

			if (((String) entry.getKey()).compareTo(SeewhyConstants.MODEL_ENTRY_BREADCRUMBS) == 0)
			{
				final List listBreadcrumbs = (List) entry.getValue();

				LOG.debug("SeewhyBeforeViewHandlerAdaptee beforeView called logHybrisModelData NEW. About to create breadcrumbs");

				final Iterator itr2 = listBreadcrumbs.iterator();
				Breadcrumb breadcrumb = null;

				while (itr2.hasNext() == true)
				{
					breadcrumb = (Breadcrumb) itr2.next();

					LOG.debug("SeewhyBreadcrumbs beforeView called logHybrisModelData NEW. breadcrumb.getCategoryCode() = "
							+ breadcrumb.getCategoryCode());
					LOG.debug("SeewhyBreadcrumbs beforeView called logHybrisModelData NEW. breadcrumb.getName() = "
							+ breadcrumb.getName());
				}

			}
		}
	}

	/**
	 * Logs the current content of the Seewhy In-memory Repository for a particular context. For debug purposes only.
	 *
	 * @param strContext
	 */
	private void logSeewhyInMemoryRepositoryForContext(final String strContext)
	{
		HashMap hmVariables = null;
		Iterator itr = null;
		Map.Entry entry = null;

		hmVariables = this.seewhyInMemoryRepository.getContextVariables(strContext);

		if (hmVariables != null)
		{
			itr = hmVariables.entrySet().iterator();

			while (itr.hasNext() == true)
			{
				entry = (Map.Entry) itr.next();
				LOG.debug("SeewhyBeforeViewHandlerAdaptee logSeewhyInMemoryRepositoryForContext. key/value: " + entry.getKey() + "/"
						+ entry.getValue());
			}
		}
	}
}
