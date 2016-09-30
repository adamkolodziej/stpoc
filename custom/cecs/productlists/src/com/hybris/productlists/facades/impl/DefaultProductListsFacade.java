/**
 * 
 */
package com.hybris.productlists.facades.impl;

import com.hybris.addon.common.converters.ConfigurableConverter;
import com.hybris.addon.common.converters.ConfigurableConverters;
import com.hybris.productlists.data.ListToCartModification;
import com.hybris.productlists.data.ListToCartModificationData;
import com.hybris.productlists.data.ProductListData;
import com.hybris.productlists.data.ProductListEntryData;
import com.hybris.productlists.enums.ProductListType;
import com.hybris.productlists.facades.ProductListsFacade;
import com.hybris.productlists.model.PrincipalListOwnerModel;
import com.hybris.productlists.model.ProductListEntryModel;
import com.hybris.productlists.model.ProductListModel;
import com.hybris.productlists.model.ProductListOwnerModel;
import com.hybris.productlists.service.ProductListsCartService;
import com.hybris.productlists.service.ProductListsSecurityService;
import com.hybris.productlists.service.ProductListsService;
import com.hybris.productlists.service.ProductListsSessionService;
import com.hybris.productlists.service.impl.DefaultProductListsSessionService;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * The product list facade used by the user interface and the OCC to provide the user operations on the UI
 * 
 * @author craig.wayman
 */
public class DefaultProductListsFacade implements ProductListsFacade
{
	private ProductListsService productListsService;
	private ProductListsSecurityService productListsSecurityService;
	private ProductListsSessionService productListsSessionService;
	private ProductListsCartService productListsCartService;
	private ProductService productService;
	private UserService userService;
	private CartService cartService;
	private CommerceCartService commerceCartService;

	private ConfigurableConverter<ProductListModel, ProductListData, ProductOption> productListDataConverter;
	private ConfigurableConverter<ProductListEntryModel, ProductListEntryData, ProductOption> productListEntryDataConverter;
	private Converter<ProductListData, ProductListModel> productListModelConverter;
	private Converter<ListToCartModification, ListToCartModificationData> listToCartModificationDataConverter;

	private SessionService sessionService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.productlists.facades.ProductListsFacade#getAllSessionProductLists()
	 */
	@Override
	public List<ProductListData> getAllSessionProductLists(final Collection<ProductOption> options)
	{
		return getSessionProductLists(null, options);
	}

	@Override
	public List<ProductListData> getOrCreateSessionProductLists(final ProductListType type, final Collection<ProductOption> options)
	{
		final List<ProductListData> productLists = getSessionProductLists(type, options);
		if (CollectionUtils.isEmpty(productLists))
		{
			final ProductListData prototype = new ProductListData();
			prototype.setType(type);

			final String guid = createProductList(prototype);
			getProductListsSessionService().addSessionProductList(getProductListsSessionService().getProductListByGuid(guid));
			return getSessionProductLists(type, options);
		}
		return productLists;
	}

	@Override
	public List<ProductListData> getSessionProductLists(final ProductListType type, final Collection<ProductOption> options)
	{
		List<ProductListModel> productListModels;

		if (hasSessionProductLists())
		{
			productListModels = getProductListsSessionService().getSessionProductLists(type);
		}
		else
		{
			productListModels = getProductListsSessionService().getProductListsForLoggedInUser();
			if (!CollectionUtils.isEmpty(productListModels))
			{
				getProductListsSessionService().setSessionProductLists(productListModels);
				productListModels = getProductListsSessionService().getSessionProductLists(type);
			}

		}
		if (CollectionUtils.isEmpty(productListModels))
		{
			return Collections.EMPTY_LIST;
		}
		return ConfigurableConverters.convertAll(productListModels, getProductListDataConverter(), options);
	}

	@Override
	public void addToProductList(final String guid, final String productCode, final Integer quantity)
	{
		updateProductList(guid, productCode, quantity, true);
	}

	@Override
	public void updateProductList(final String guid, final String productCode, final Integer quantity)
	{
		updateProductList(guid, productCode, quantity, false);
	}

	/**
	 * @param productCode
	 * @param quantity
	 * @param merge
	 */
	private void updateProductList(final String guid, final String productCode, final Integer quantity, final boolean merge)
	{
		if (StringUtils.isBlank(productCode))
		{
			throw new IllegalArgumentException("There is no product code for the Product List");
		}
		if (null == quantity)
		{
			throw new IllegalArgumentException("There is no quantity for the Product List");
		}
		if (quantity.intValue() < 0)
		{
			throw new IllegalArgumentException("Quantity cannot be less that 0");
		}

		final ProductModel product = getProductService().getProductForCode(productCode);
		final ProductListModel productListModel = getProductListsSessionService().getProductListByGuid(guid);
		final ProductListEntryModel productListEntry;

		if (merge && getProductListsService().hasProduct(productListModel, productCode).booleanValue())
		{
			//get product and update the desired amount
			productListEntry = getProductListsService().getProductListEntryFromList(productListModel, productCode);
			getProductListsService().updateProductDesiredAmount(productListModel, productListEntry,
					new Integer(productListEntry.getDesired().intValue() + quantity.intValue()));
		}
		else if (!merge && getProductListsService().hasProduct(productListModel, productCode).booleanValue())
		{
			//get product and update the desired amount
			productListEntry = getProductListsService().getProductListEntryFromList(productListModel, productCode);
			getProductListsService().updateProductDesiredAmount(productListModel, productListEntry, quantity);
		}
		else
		{
			//add a new product
			productListEntry = new ProductListEntryModel();
			productListEntry.setDesired(quantity);
			productListEntry.setProduct(product);
			productListEntry.setReceived(new Integer(0));
			productListEntry.setProductList(productListModel);
			getProductListsService().addProduct(productListModel, productListEntry);
		}
	}

	@Override
	public boolean hasSessionProductLists()
	{
		return getProductListsSessionService().hasSessionProductLists();
	}

	@Override
	public ProductListData getProductList(final String productListGuid, final Collection<ProductOption> options)
	{
		final ProductListModel productlists = getProductListsSessionService().getProductListByGuid(productListGuid);
		if (productlists == null)
		{
			return null;
		}
		return getProductListDataConverter().convert(productlists, options);
	}


	@Override
	public ProductListData restoreSavedProductList(final String productListGuid, final Collection<ProductOption> options)
	{
		final ProductListModel productlists = getProductListsSessionService().getProductListByGuid(productListGuid);
		if (productlists == null)
		{
			return null;
		}
		getProductListsSessionService().addSessionProductList(productlists);
		return getProductListDataConverter().convert(productlists, options);
	}

	@Override
	public String createProductList(final ProductListData productListData)
	{
		final ProductListModel model = getProductListModelConverter().convert(productListData);

		final UserModel userModel = getUserService().getCurrentUser();
		if (!getUserService().isAnonymousUser(userModel))
		{
			final PrincipalListOwnerModel listOwner = new PrincipalListOwnerModel();
			listOwner.setPrincipal(userModel);
			final List<ProductListOwnerModel> owners = new ArrayList<ProductListOwnerModel>();
			owners.add(listOwner);
			model.setListOwners(owners);
		}

		// CECS addition
		getSessionService().removeAttribute(DefaultProductListsSessionService.SESSION_PRODUCT_LIST_PARAMETER_NAME); // using just setProductList from ProductListsSessionService doesn't work

		final ProductListModel returnedModel = getProductListsService().createProductList(model);
		return returnedModel.getGuid();
	}

	@Override
	public void updateProductListName(final String guid, final String name)
	{
		final ProductListModel productListModel = getProductListsSessionService().getProductListByGuid(guid);

		productListModel.setName(name);

		getProductListsService().updateProductListModel(productListModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.productlists.facades.ProductListsFacade#updateNotes(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean updateNotes(final String productListGuid, final String productCode, final String notes)
	{
		final ProductListModel productListModel = getProductListsSessionService().getProductListByGuid(productListGuid);
		final ProductListEntryModel productListEntryModel = getProductListsService().getProductListEntryFromList(productListModel,
				productCode);
		if (productListEntryModel != null)
		{
			productListEntryModel.setComment(notes);
			getProductListsService().updateProductListEntryModel(productListEntryModel);
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.productlists.facades.ProductListsFacade#moveToCart(java.lang.String, java.lang.String,
	 * java.lang.Integer)
	 */
	@Override
	public ListToCartModificationData moveToCart(final String productListGuid, final String productCode, final Integer quantity)
			throws CommerceCartModificationException
	{
		final ProductModel product = getProductService().getProductForCode(productCode);
		final ProductListModel list = getProductListsSessionService().getProductListByGuid(productListGuid);
		return getListToCartModificationDataConverter().convert(
				getProductListsCartService().moveItemToCart(list, getCartService().getSessionCart(), product, quantity.longValue(),
						product.getUnit(), false));
	}

	@Override
	public void updateProductOrder(final String productListGuid, final String beforeProductCode, final String productToMoveCode)
	{
		final ProductListModel productListModel = getProductListsSessionService().getProductListByGuid(productListGuid);
		final ProductListEntryModel before = getProductListsService().getProductListEntryFromList(productListModel,
				beforeProductCode);
		final ProductListEntryModel entry = getProductListsService().getProductListEntryFromList(productListModel,
				productToMoveCode);

		productListsService.updateProductOrder(productListModel, before, entry);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.productlists.facades.ProductListsFacade#getListEntry(java.lang.String, java.lang.String)
	 */
	@Override
	public ProductListEntryData getListEntry(final String productListGuid, final String productCode,
			final Collection<ProductOption> options)
	{
		final ProductListModel productListModel = getProductListsSessionService().getProductListByGuid(productListGuid);
		if (productListModel != null)
		{
			final ProductListEntryModel entry = getProductListsService().getProductListEntryFromList(productListModel, productCode);
			if (entry != null)
			{
				return getProductListEntryDataConverter().convert(entry, options);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.productlists.facades.ProductListsFacade#onLogin()
	 */
	@Override
	public void onLogin()
	{
		if (hasSessionProductLists())
		{
			final List<ProductListModel> productListModels = getProductListsSessionService().getSessionProductLists();
			for (final ProductListModel pl : productListModels)
			{
				getProductListsSecurityService().transferAnonymousList(pl);
			}
		}
		getProductListsSessionService().setSessionProductLists(getProductListsSessionService().getProductListsForLoggedInUser());
	}

	@Required
	public void setProductListsService(final ProductListsService productListsService)
	{
		this.productListsService = productListsService;
	}

	@Required
	public void setProductListDataConverter(
			final ConfigurableConverter<ProductListModel, ProductListData, ProductOption> productListDataConverter)
	{
		this.productListDataConverter = productListDataConverter;
	}

	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	@Required
	public void setProductListsSessionService(final ProductListsSessionService productListsSessionService)
	{
		this.productListsSessionService = productListsSessionService;
	}

	@Required
	public void setProductListModelConverter(final Converter<ProductListData, ProductListModel> productListModelConverter)
	{
		this.productListModelConverter = productListModelConverter;
	}

	public ProductListsCartService getProductListsCartService()
	{
		return productListsCartService;
	}

	@Required
	public void setProductListsCartService(final ProductListsCartService productListsCartService)
	{
		this.productListsCartService = productListsCartService;
	}

	@Required
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

	public CartService getCartService()
	{
		return this.cartService;
	}


	/** @return the productListsService */
	public ProductListsService getProductListsService()
	{
		return productListsService;
	}

	/** @return the productListsSessionService */
	public ProductListsSessionService getProductListsSessionService()
	{
		return productListsSessionService;
	}

	/** @return the productService */
	public ProductService getProductService()
	{
		return productService;
	}

	/** @return the userService */
	public UserService getUserService()
	{
		return userService;
	}

	/** @return the productListDataConverter */
	public ConfigurableConverter<ProductListModel, ProductListData, ProductOption> getProductListDataConverter()
	{
		return productListDataConverter;
	}

	/** @return the productListModelConverter */
	public Converter<ProductListData, ProductListModel> getProductListModelConverter()
	{
		return productListModelConverter;
	}

	public ConfigurableConverter<ProductListEntryModel, ProductListEntryData, ProductOption> getProductListEntryDataConverter()
	{
		return productListEntryDataConverter;
	}

	@Required
	public void setProductListEntryDataConverter(
			final ConfigurableConverter<ProductListEntryModel, ProductListEntryData, ProductOption> productListEntryDataConverter)
	{
		this.productListEntryDataConverter = productListEntryDataConverter;
	}

	public CommerceCartService getCommerceCartService()
	{
		return commerceCartService;
	}

	@Required
	public void setCommerceCartService(final CommerceCartService commerceCartService)
	{
		this.commerceCartService = commerceCartService;
	}

	public Converter<ListToCartModification, ListToCartModificationData> getListToCartModificationDataConverter()
	{
		return listToCartModificationDataConverter;
	}

	@Required
	public void setListToCartModificationDataConverter(
			final Converter<ListToCartModification, ListToCartModificationData> listToCartModificationDataConverter)
	{
		this.listToCartModificationDataConverter = listToCartModificationDataConverter;
	}

	public ProductListsSecurityService getProductListsSecurityService()
	{
		return productListsSecurityService;
	}

	@Required
	public void setProductListsSecurityService(final ProductListsSecurityService productListsSecurityService)
	{
		this.productListsSecurityService = productListsSecurityService;
	}

	public SessionService getSessionService() {
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService) {
		this.sessionService = sessionService;
	}
}
