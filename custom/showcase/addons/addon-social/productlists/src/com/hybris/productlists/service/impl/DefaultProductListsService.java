/**
 * 
 */
package com.hybris.productlists.service.impl;

import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.Preconditions;
import com.hybris.productlists.daos.ProductListsDAO;
import com.hybris.productlists.model.ProductListEntryModel;
import com.hybris.productlists.model.ProductListModel;
import com.hybris.productlists.service.ProductListsSecurityService;
import com.hybris.productlists.service.ProductListsService;


/** Default implementation of the ProductListsService
 * 
 * @author craig.wayman */
public class DefaultProductListsService implements ProductListsService
{

	/** Dependencies */
	private ProductListsDAO productListsDAO;
	private ModelService modelService;
	private BaseStoreService baseStoreService;
	private BaseSiteService baseSiteService;
	private EnumerationService enumerationService;
	private ProductListsSecurityService productListsSecurityService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.service.ProductListsService#createProductList(com.hybris.productlists.model.ProductListModel
	 * )
	 */
	@Override
	public ProductListModel createProductList(final ProductListModel productList)
	{
		if (productList != null)
		{

			// If no GUID supplied generate
			if (StringUtils.isBlank(productList.getGuid()))
			{
				productList.setGuid(getProductListsSecurityService().generateGuid());
				productList.setCode(productList.getGuid());
			}

			// Defaults
			if (productList.getType() == null)
			{
				productList.setType(com.hybris.productlists.enums.ProductListType.WISHLIST);
			}
			if (null == productList.getName())
			{
				productList.setName(getEnumerationService().getEnumerationName(productList.getType()));
			}
			if (null == productList.getSite())
			{
				productList.setSite(getBaseSiteService().getCurrentBaseSite());
			}
			if (null == productList.getStore())
			{
				productList.setStore(getBaseStoreService().getCurrentBaseStore());
			}

			updateProductListModel(productList);
			return productList;
		}
		else
		{
			throw new IllegalArgumentException("The productlist to create was null");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.service.ProductListsService#addProduct(com.hybris.productlists.model.ProductListModel,
	 * com.hybris.productlists.model.ProductListEntryModel)
	 */
	@Override
	public ProductListModel addProduct(final ProductListModel productList, final ProductListEntryModel entry)
	{
		if (productList == null)
		{
			throw new IllegalArgumentException("ProductList argument was null");
		}
		if (entry == null)
		{
			throw new IllegalArgumentException("Entry argument was null");
		}

		final List<ProductListModel> productLists = getProductListsDAO().getProductList(getBaseSiteService().getCurrentBaseSite(),
				getBaseStoreService().getCurrentBaseStore());

		if (productLists.size() == 0)
		{
			throw new RuntimeException("Could not find productlist to add entry too!");
		}

		if (null == productList.getProductListEntries()
				|| (null != productList.getProductListEntries() && productList.getProductListEntries().isEmpty()))
		{
			productList.setProductListEntries(new ArrayList<ProductListEntryModel>());
		}
		final List newEntriesList = new ArrayList(productList.getProductListEntries());
		newEntriesList.add(entry);
		productList.setProductListEntries(newEntriesList);
		updateProductListModel(productList);

		return productList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.productlists.service.ProductListsService#updateProductDesiredAmount(com.hybris.productlists.model.
	 * ProductListModel, com.hybris.productlists.model.ProductListEntryModel, java.lang.Integer)
	 */
	@Override
	public void updateProductDesiredAmount(final ProductListModel productList, final ProductListEntryModel entry,
			final Integer desiredAmount)
	{
		Preconditions.checkNotNull("Product List should not be null", productList);
		Preconditions.checkNotNull("Entry should not be null", entry);
		Preconditions.checkNotNull("Desired Amount should not be null", desiredAmount);

		final Integer appliedDesiredAmount = (desiredAmount.intValue() <= 0 ? Integer.valueOf(0) : desiredAmount);
		entry.setDesired(appliedDesiredAmount);
		updateProductListEntryModel(entry);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.service.ProductListsService#updateProductReceivedAmount(com.hybris.productlists.model.
	 * ProductListModel, com.hybris.productlists.model.ProductListEntryModel, java.lang.Integer)
	 */
	@Override
	public void updateProductReceivedAmount(final ProductListModel productList, final ProductListEntryModel entry,
			final Integer receivedAmount)
	{
		Preconditions.checkNotNull(productList, "ProductList argument should not be null");
		Preconditions.checkNotNull(entry, "Entry argument should not be null");

		entry.setReceived(new Integer(entry.getReceived().intValue() + receivedAmount.intValue()));
		final Integer newDesired = entry.getDesired().intValue() - receivedAmount.intValue() < 0 ? Integer.valueOf(0) : Integer
				.valueOf(entry.getDesired().intValue() - receivedAmount.intValue());
		entry.setDesired(newDesired);
		updateProductListEntryModel(entry);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.service.ProductListsService#updateProductOrder(com.hybris.productlists.model.ProductListModel
	 * , com.hybris.productlists.model.ProductListEntryModel, com.hybris.productlists.model.ProductListEntryModel)
	 */
	@Override
	public void updateProductOrder(final ProductListModel productList, final ProductListEntryModel before,
			final ProductListEntryModel entryToMove)
	{
		//We can let the before argument be null as this will dictate moving the priority to the top of the list
		if (productList == null)
		{
			throw new IllegalArgumentException("ProductList argument was null");
		}
		if (entryToMove == null)
		{
			throw new IllegalArgumentException("Entry argument was null");
		}

		if (productList.getProductListEntries() != null)
		{
			final List<ProductListEntryModel> reorderedList = new ArrayList<>();

			for (final ProductListEntryModel entry : productList.getProductListEntries())
			{
				if (entry != entryToMove)
				{
					//skip add
					if (before != null && entry == before)
					{
						reorderedList.add(entryToMove);
					}
					reorderedList.add(entry);
				}
			}
			if (before == null)
			{
				reorderedList.add(entryToMove);
			}
			productList.setProductListEntries(reorderedList);
			getModelService().save(productList);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.service.ProductListsService#getProductList(de.hybris.platform.basecommerce.model.site.
	 * BaseSiteModel, de.hybris.platform.store.BaseStoreModel)
	 */
	@Override
	public List<ProductListModel> getProductList()
	{
		final List<ProductListModel> productLists = getProductListsDAO().getProductList(getBaseSiteService().getCurrentBaseSite(),
				getBaseStoreService().getCurrentBaseStore());

		return productLists;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.service.ProductListsService#getHiddenProductListEntries(com.hybris.productlists.model.
	 * ProductListModel)
	 */
	@Override
	public List<ProductListEntryModel> getHiddenProductListEntries(final ProductListModel productList)
	{
		return productListsDAO.getHiddenProductListEntries(productList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.service.ProductListsService#hasProduct(com.hybris.productlists.model.ProductListModel,
	 * java.lang.String)
	 */
	@Override
	public Boolean hasProduct(final ProductListModel productList, final String code)
	{
		if (productList == null)
		{
			return Boolean.FALSE;
		}
		else if (StringUtils.isBlank(code))
		{
			return Boolean.FALSE;
		}
		else if (productList.getProductListEntries() == null)
		{
			return Boolean.FALSE;
		}
		else if (productList.getProductListEntries().isEmpty())
		{
			return Boolean.FALSE;
		}
		else
		{
			for (final ProductListEntryModel entry : productList.getProductListEntries())
			{
				if (entry.getProduct() != null && code.equals(entry.getProduct().getCode()))
				{
					return Boolean.TRUE;
				}
			}
		}

		return Boolean.FALSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.service.ProductListsService#getProductListEntryFromList(com.hybris.productlists.model.
	 * ProductListModel, java.lang.String)
	 */
	@Override
	public ProductListEntryModel getProductListEntryFromList(final ProductListModel productList, final String code)
	{
		if (productList == null || StringUtils.isBlank(code) || productList.getProductListEntries() == null
				|| productList.getProductListEntries().isEmpty())
		{
			return null;
		}
		else
		{
			for (final ProductListEntryModel entry : productList.getProductListEntries())
			{
				if (entry.getProduct() != null && code.equals(entry.getProduct().getCode()))
				{
					return entry;
				}
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.productlists.service.ProductListsService#updateProductListModel(com.hybris.productlists.model.
	 * ProductListModel)
	 */
	@Override
	public void updateProductListModel(final ProductListModel productListModel)
	{
		getModelService().save(productListModel);
		getModelService().refresh(productListModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.service.ProductListsService#updateProductListEntryModel(com.hybris.productlists.model.
	 * ProductListEntryModel)
	 */
	@Override
	public void updateProductListEntryModel(final ProductListEntryModel productListEntryModel)
	{
		getModelService().save(productListEntryModel);
		getModelService().refresh(productListEntryModel);
	}

	/** @param productListsDAO
	 *           the productListsDAO to set */
	@Required
	public void setProductListsDAO(final ProductListsDAO productListsDAO)
	{
		this.productListsDAO = productListsDAO;
	}

	/** @param modelService
	 *           the modelService to set */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/** @param baseStoreService
	 *           the baseStoreService to set */
	@Required
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/** @param baseSiteService
	 *           the baseSiteService to set */
	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}



	public EnumerationService getEnumerationService()
	{
		return enumerationService;
	}

	@Required
	public void setEnumerationService(final EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
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

	/** @return the productListsDAO */
	public ProductListsDAO getProductListsDAO()
	{
		return productListsDAO;
	}

	/** @return the modelService */
	public ModelService getModelService()
	{
		return modelService;
	}

	/** @return the baseStoreService */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/** @return the baseSiteService */
	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}




}
