package com.hybris.showcase.dao.impl;

import com.hybris.showcase.model.pages.BlogPageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;

import com.hybris.showcase.dao.BlogArticleDao;
import com.hybris.showcase.model.components.BlogArticleModel;


/**
 * Created by m.golubovic on 22.6.2015..
 */
public class DefaultBlogArticleDao extends DefaultGenericDao<BlogArticleModel> implements BlogArticleDao
{
	/**
	 * @param typecode
	 */
	public static final String BLOG_FOR_PRODUCT = " select {e.pk} from {" + BlogArticleModel._TYPECODE + " AS e JOIN " + ProductModel._TYPECODE
			+ " AS pro ON {e.product}={pro.pk}} where {pro.code} = ?productCode";

	public static final String BLOG_FOR_PAGE_ID = " select {e.pk} from {" + BlogArticleModel._TYPECODE + " AS e JOIN " + BlogPageModel._TYPECODE
			+ " AS blog ON {e.blogpage}={blog.pk}} where {blog.uid} = ?pageID";


	public DefaultBlogArticleDao(final String typecode)
	{
		super(typecode);
	}

	@Override
	public BlogArticleModel getBlogArticleForCode(final String code)
	{
		final Map<String, String> restrictions = new HashMap<>();
		restrictions.put("code", code);
		final List<BlogArticleModel> result = find(restrictions);
		if (CollectionUtils.isNotEmpty(result))
		{
			return result.iterator().next();
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<BlogArticleModel> findBlogArticles()
	{
		return find();
	}

	@Override
	public List<BlogArticleModel> getBlogArticlesForProduct(String productCode) {
		final Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("productCode", productCode);

		final SearchResult<BlogArticleModel> searchResult = getFlexibleSearchService().search(BLOG_FOR_PRODUCT,
				queryParams);

		return searchResult.getResult();
	}

	@Override
	public List<BlogArticleModel> getBlogArticlesForPage(String pageID) {
		final Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("pageID", pageID);

		final SearchResult<BlogArticleModel> searchResult = getFlexibleSearchService().search(BLOG_FOR_PAGE_ID,
				queryParams);

		return searchResult.getResult();
	}
}
