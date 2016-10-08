/**
 * 
 */
package com.hybris.productlists.service.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.jalo.JaloObjectNoLongerValidException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.productlists.daos.ProductListsDAO;
import com.hybris.productlists.enums.ProductListType;
import com.hybris.productlists.model.ProductListModel;
import com.hybris.productlists.service.ProductListsService;
import com.hybris.productlists.service.ProductListsSessionService;


/**
 * @author simonhuggins
 * 
 */
@SuppressWarnings("unused")
public class DefaultProductListsSessionService implements ProductListsSessionService
{

	private static final Logger LOG = Logger.getLogger(DefaultProductListsSessionService.class);
	public static String SESSION_PRODUCT_LIST_PARAMETER_NAME = "productlists";

	/** Dependencies */
	private ProductListsDAO productListsDAO;
	private ModelService modelService;
	private SessionService sessionService;
	private BaseStoreService baseStoreService;
	private BaseSiteService baseSiteService;
	private ProductListsService productListsService;
	private UserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.productlists.service.ProductListsSessionService#getProductListByGuid(java.lang.String)
	 */
	@Override
	public ProductListModel getProductListByGuid(final String guid)
	{
		return productListsDAO.getProductListByGuid(guid);
	}

	@Override
	public List<ProductListModel> getProductListsForLoggedInUser()
	{
		return getProductListsForLoggedInUser(null);
	}

	@Override
	public List<ProductListModel> getProductListsForLoggedInUser(final ProductListType productListTypeFilter)
	{
		final PrincipalModel user = getLoggedInUser();
		if (user == null)
		{
			return null;
		}

		final BaseSiteModel site = getCurrentBaseSite();

		// No Base site in session is okay as we can fall back to base store

		final BaseStoreModel store = getCurrentBaseStore();

		if (store == null)
		{
			throw new RuntimeException("No base store in session");
		}

		return productListsDAO.getProductListForOwner(user, site, store, productListTypeFilter);
	}


	public PrincipalModel getLoggedInUser()
	{
		return (!userService.isAnonymousUser(userService.getCurrentUser()) ? userService.getCurrentUser() : null);
	}

	private BaseStoreModel getCurrentBaseStore()
	{

		return baseStoreService.getCurrentBaseStore();

	}

	private BaseSiteModel getCurrentBaseSite()
	{

		return baseSiteService.getCurrentBaseSite();

	}

	/**
	 * @param productListsDAO
	 *           the productListsDAO to set
	 */
	@Required
	public void setProductListsDAO(final ProductListsDAO productListsDAO)
	{
		this.productListsDAO = productListsDAO;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}


	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * @param baseStoreService
	 *           the baseStoreService to set
	 */
	@Required
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/**
	 * @param baseSiteService
	 *           the baseSiteService to set
	 */
	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	@Required
	public void setProductListsService(final ProductListsService productListsService)
	{
		this.productListsService = productListsService;
	}

	@Override
	public boolean hasSessionProductLists()
	{
		try
		{
			final List<ProductListModel> sessionProductList = sessionService.getAttribute(SESSION_PRODUCT_LIST_PARAMETER_NAME);
			return sessionProductList != null && !sessionProductList.isEmpty();
		}
		catch (final JaloObjectNoLongerValidException ex)
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info("Session Product List no longer valid. Removing from session. hasSessionProductList will return false. "
						+ ex.getMessage());
			}
			sessionService.removeAttribute(SESSION_PRODUCT_LIST_PARAMETER_NAME);
			return false;
		}
	}

	@Override
	public void setSessionProductLists(final List<ProductListModel> productLists)
	{
		if (productLists == null)
		{
			removeSessionProductLists();
		}
		else
		{
			sessionService.setAttribute(SESSION_PRODUCT_LIST_PARAMETER_NAME, productLists);
		}
	}

	public void removeSessionProductLists()
	{
		if (hasSessionProductLists())
		{
			final List<ProductListModel> sessionProductLists = getSessionProductLists();
			modelService.remove(sessionProductLists);
			sessionService.removeAttribute(SESSION_PRODUCT_LIST_PARAMETER_NAME);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.service.ProductListsSessionService#getSessionProductList(com.hybris.productlists.enums
	 * .ProductListType)
	 */
	@Override
	public List<ProductListModel> getSessionProductLists()
	{
		return sessionService.getAttribute(SESSION_PRODUCT_LIST_PARAMETER_NAME);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.service.ProductListsSessionService#addSessionProductList(com.hybris.productlists.model
	 * .ProductListModel)
	 */
	@Override
	public void addSessionProductList(final ProductListModel list)
	{
		final List<ProductListModel> lists = getSessionProductLists();

		if (lists != null)
		{
			if (!lists.contains(list))
			{
				final List<ProductListModel> newList = new LinkedList<ProductListModel>(lists);
				newList.add(list);
				setSessionProductLists(newList);
			}
		}
		else
		{
			final List<ProductListModel> newList = new ArrayList<ProductListModel>(1);
			newList.add(list);
			setSessionProductLists(newList);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.service.ProductListsSessionService#getSessionProductLists(com.hybris.productlists.enums
	 * .ProductListType)
	 */
	@Override
	public List<ProductListModel> getSessionProductLists(final ProductListType productListTypeFilter)
	{
		final List<ProductListModel> candidates = getSessionProductLists();

		if (productListTypeFilter != null)
		{
			final List<ProductListModel> r = new LinkedList<ProductListModel>();
			for (final ProductListModel c : candidates)
			{
				if (c.getType().equals(productListTypeFilter))
				{
					r.add(c);
				}
			}
			return r;
		}
		else
		{
			return candidates;
		}
	}


}
