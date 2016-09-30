/**
 * 
 */
package com.hybris.productlists.daos.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.store.BaseStoreModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.hybris.productlists.daos.ProductListsDAO;
import com.hybris.productlists.enums.ProductListType;
import com.hybris.productlists.model.ProductListEntryModel;
import com.hybris.productlists.model.ProductListModel;


/**
 * Default implementation of the ProductListsDAO interface.
 * 
 * @author craig.wayman
 */
@Component(value = "productListsDAO")
public class DefaultProductListsDAO implements ProductListsDAO
{

	private static final Logger LOG = Logger.getLogger(DefaultProductListsDAO.class);

	/**
	 * We use hybris' FlexibleSearchService for running queries against the database
	 * 
	 * @see "https://wiki.hybris.com/display/release5/FlexibleSearch"
	 */
	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<ProductListModel> getProductList(final BaseSiteModel baseSite, final BaseStoreModel baseStore)
	{
		// @formatter:off

		final String queryString = "SELECT {pl:" + ProductListModel.PK + "}" + " FROM {" + ProductListModel._TYPECODE + " AS pl} "
				+ " WHERE {pl:" + ProductListModel.SITE + "}=?site" + " AND {pl:" + ProductListModel.STORE + "}=?store";

		// @formatter:on

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("site", baseSite);
		query.addQueryParameter("store", baseStore);
		return flexibleSearchService.<ProductListModel> search(query).getResult();

	}

	@Override
	public List<ProductListModel> getProductListBy(final Comparator comparator, final BaseSiteModel baseSite,
			final BaseStoreModel baseStore)
	{

		final List<ProductListModel> productListModel = getProductList(baseSite, baseStore);

		if (null != comparator)
		{
			for (final ProductListModel list : productListModel)
			{
				final List<ProductListEntryModel> listToSort = new ArrayList<ProductListEntryModel>(list.getProductListEntries());
				Collections.sort(listToSort, comparator);
				list.setProductListEntries(listToSort);
			}
		}

		return productListModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.productlists.daos.ProductListsDAO#getHiddenProductListEntries(com.hybris.productlists.model.
	 * ProductListModel)
	 */
	@Override
	public List<ProductListEntryModel> getHiddenProductListEntries(final ProductListModel productList)
	{

		// @formatter:off

		final String queryString = "SELECT {ple:" + ProductListEntryModel.PK + "} AS ple " + "WHERE {ple:"
				+ ProductListEntryModel.PRIORITY + "} < 0" + " AND {ple:" + ProductListEntryModel.PRODUCT + "}=?productList"
				+ " ORDER BY {ple:" + ProductListEntryModel.PK + "}";

		// @formatter:on

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("productList", productList);
		return flexibleSearchService.<ProductListEntryModel> search(query).getResult();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.daos.ProductListsDAO#getProductListByGuid(com.hybris.productlists.model.ProductListModel)
	 */
	@Override
	public ProductListModel getProductListByGuid(final String guid)
	{
		// @formatter:off

		final String queryString = "SELECT {pl:" + ProductListModel.PK + "}" + " FROM {" + ProductListModel._TYPECODE + " AS pl }"
				+ " WHERE {pl:" + ProductListModel.GUID + "} = ?prodlistguid";

		// @formatter:on

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("prodlistguid", guid);
		final List<ProductListModel> results = flexibleSearchService.<ProductListModel> search(query).getResult();
		return results.size() > 0 ? results.get(0) : null;
	}

	/*
	 * (non-Javadoc) Allow base Site to be NULL as a fallback - also productListTypeFilter is optional
	 * 
	 * @see
	 * com.hybris.productlists.daos.ProductListsDAO#getProductListForPrincipal(de.hybris.platform.core.model.security
	 * .PrincipalModel, de.hybris.platform.basecommerce.model.site.BaseSiteModel,
	 * de.hybris.platform.store.BaseStoreModel, com.hybris.productlists.enums.productListTypeFilter)
	 */
	@Override
	public List<ProductListModel> getProductListForOwner(final PrincipalModel principal, final BaseSiteModel baseSite,
			final BaseStoreModel baseStore, final ProductListType productListTypeFilter)
	{
		// @formatter:off

		//final String queryString = "SELECT {pl:pk} FROM {ProductList AS pl}  JOIN { ProductList2ProductListOwner AS rel1 ON pl:pk = rel1:source} JOIN {PrincipalListOwner AS plo ON plo:pk = rel1:target} JOIN {Principal AS pr ON plo:principal = pr:pk} WHERE {pr:pk = ?user AND pl:site=?site AND pl:store=?store AND pl:type=?listType }";

		final String queryString = "SELECT {pl:pk} FROM {" + "ProductList AS pl "
				+ "JOIN ProductList2ProductListOwner AS rel1 ON {pl:pk} = {rel1:source} "
				+ "JOIN PrincipalListOwner AS plo ON {plo:pk} = {rel1:target} "
				+ "JOIN Principal AS pr ON {plo:principal} = {pr:pk} " + "}" + "WHERE " + "{pr:pk} = ?user"
		/*
		 * + (baseSite == null ? "" : "AND {pl:" + ProductListModel.SITE + "=?site}") + (baseStore == null ? "" :
		 * "AND {pl:" + ProductListModel.STORE + "=?store} ")
		 */;

		//				"SELECT {pl:" + ProductListModel.PK + "}" + " FROM {" + ProductListModel._TYPECODE + " AS pl} "
		//				+ " JOIN { " + PrincipalListOwnerModel._PRODUCTLIST2PRODUCTLISTOWNER + " AS rel1" + " ON pl:" + ProductListModel.PK
		//				+ " = rel1:source}" + " JOIN {" + PrincipalListOwnerModel._TYPECODE + " AS plo " + "ON plo:"
		//				+ PrincipalListOwnerModel.PK + " = rel1:target} " + "JOIN {" + PrincipalModel._TYPECODE + " AS pr " + "ON plo:"
		//				+ PrincipalListOwnerModel.PRINCIPAL + " = pr:" + PrincipalModel.PK + "} " + "WHERE {pr:" + PrincipalModel.PK
		//				+ " = ?user " + (baseSite == null ? "" : "AND pl:" + ProductListModel.SITE + "=?site ")
		//				+ (baseStore == null ? "" : "AND pl:" + ProductListModel.STORE + "=?store ")
		//				+ (productListTypeFilter == null ? "" : "AND pl:" + ProductListModel.TYPE + "=?listType } ");

		// @formatter:on

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("user", principal);
		/*
		 * if (baseSite != null) { query.addQueryParameter("site", baseSite); } if (baseStore != null) {
		 * query.addQueryParameter("store", baseStore); } if (productListTypeFilter != null) {
		 * query.addQueryParameter("listType", productListTypeFilter); }
		 */

		return flexibleSearchService.<ProductListModel> search(query).getResult();

	}

	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
