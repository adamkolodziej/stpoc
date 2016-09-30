package com.hybris.showcase.dao;

import de.hybris.platform.servicelayer.internal.dao.GenericDao;

import java.util.List;

import com.hybris.showcase.model.components.BlogArticleModel;


/**
 * Created by m.golubovic on 22.6.2015..
 */
public interface BlogArticleDao extends GenericDao<BlogArticleModel>
{
	BlogArticleModel getBlogArticleForCode(final String code);

	List<BlogArticleModel> findBlogArticles();

	List<BlogArticleModel> getBlogArticlesForProduct(final String productCode);

	List<BlogArticleModel> getBlogArticlesForPage(final String pageID);
}
